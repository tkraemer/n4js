/*
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   NumberFour AG - Initial API and implementation
 */
/*eslint-disable new-cap */
/*global N4ApiNotImplementedError */

(function() {
    "use strict";

    var options = n4.runtimeOptions,
        testMode = options["test-mode"],
        windows = typeof process !== "undefined" && !!process.platform.match(/^win/),
        ideExecData = options.ideExecData,
        apisuffix_re = options["api-prj-suffix-re"];

    if (apisuffix_re) {
        apisuffix_re = new RegExp(apisuffix_re);
    }

    n4.handleMainModule = function(system) {
        var mod = options["main"] || options["exec"],
            mainSym;

        // Check for module to startup: main=<module-path>[:export]
        if (!mod) {
            return Promise.reject(new Error("No main nor exec option given!"));
        }

        var mainSymIndex = mod.lastIndexOf(":");
        if (mainSymIndex > 0) {
            mainSym = mod.substr(mainSymIndex + 1);
            mod = mod.substring(0, mainSymIndex);
        }
        if (!mainSym) {
            mainSym = "default";
        }

        function onload(main) {
            if (options["main"]) { // exec main runner only for "main" arg, not for "exec" arg
                main = main[mainSym];
                if (!main) {
                    throw new Error("export \"" + mainSym + "\" not found on module " + mod);
                }
                // Execute Main runner, be tolerant about its type:
                if (typeof main === "function" && main.n4type && !main.run /* no static run() */) {
                    main = new main();
                }
                if (main && main.run) {
                    main = main.run();
                }
            }
            return main;
        }

        if (options.debug) {
            console.log("## Loading " + mod);
        }
        return (system || System).import(mod).then(function(exp) {
            System.throwPendingError(exp);
            if (n4.stylesheetsReady) { // i.e. web
                // Wait til the last moment to check whether all stylesheets have been loaded:
                return new Promise(function(resolveFn, rejectFn) {
                    n4.stylesheetsReady(function() {
                        try {
                            resolveFn(onload(exp));
                        } catch (exc) {
                            rejectFn(exc);
                        }
                    });
                });
            }
            return onload(exp);
        });
    };

    /**
     * Checks whether the given module is exporting as an ES module, otherwise wraps it up as such.
     */
    function cjsCreateModule(mod) {
        if (mod.__esModule) {
            return mod;
        } else {
            var ret = {};
            for (var prop of Object.getOwnPropertyNames(mod)) {
                Object.defineProperty(ret, prop, Object.getOwnPropertyDescriptor(mod, prop));
            }
            Object.defineProperties(ret, {
                "__esModule": {
                    value: true
                },
                "default": {
                    value: mod
                },
                "__useDefault": {
                    value: true
                }
            });
            return ret;
        }
    }
    n4.cjsCreateModule = cjsCreateModule;
    n4.cjsModulesPrefix_re = /@(@cjs|node)\//;

    /**
     * Maps package identifiers regarding the project mapping map (currently only given by IDE),
     * especially maps API projects to their implementation project.
     */
    function mapPackageId(pkg) {
        var ret = pkg;
        if (ideExecData) {
            if (ideExecData.projectNameMapping) {
                ret = ideExecData.projectNameMapping[pkg] || pkg;
            }
        } else if (apisuffix_re) {
            ret = pkg.replace(apisuffix_re, "");
        }
        if (options.debug && ret !== pkg) {
            console.log("> API-Impl mapping", pkg, "to", ret);
        }
        return ret;
    }
    function mapModulePath(id) {
        var index = id.indexOf("/");
        if (index >= 0) {
            id = mapPackageId(id.substring(0, index)) + id.substring(index);
        }
        return id;
    }
    n4.mapModulePath = mapModulePath;

    System.normalize = function(name, parentName, parentAddress) {
        return new Promise(function(resolveFn) {
            if (System._nodeRequire) {
                var cjs = name.replace(n4.cjsModulesPrefix_re, "");
                if (cjs.length !== name.length) { // i.e. node/npm; we actually install the module synchronously
                    var mod = cjsCreateModule(System._nodeRequire(cjs));
                    System.set(name, System.newModule(mod));
                    resolveFn(name);
                }
            }
            resolveFn(mapModulePath(name));
        });
    };

    System.locate = function(load) { // is actually NOT called for node/npm modules which are installed in the normalize step
        var md = load.metadata,
            path = load.name;

        md.format = "register"; // enforce for every module

        if (this._nodeRequire) { // we use NODE_PATH for resolution
            try {
                path = this._nodeRequire.resolve(path);
                if (windows) {
                    path = "/" + path.replace(/\\/g, "/");
                }
                path = "file://" + /*encodeURI*/(path); // TODO systemJS should decodeURI() on the file URL
            } catch (exc) {
                if (!testMode) {
                    return Promise.reject(exc);
                }
                md.nodejs_fileNotFound = true;
            }
        } else { // browser
            path += ".js";
        }

        return path;
    };

    System.throwPendingError = function(module) {
        if (module.__moduleError) {
            throw module.__moduleError;
        }
        return module;
    };

    function interceptDeclare(origDeclareFn, exportFn) {
        var decl,
            moduleError,
            setterInterceptFn = function(origSetter, impMod) {
                if (impMod.__moduleError) {
                    moduleError = exportFn("__moduleError", impMod.__moduleError);
                } else {
                    origSetter(impMod);
                }
            };

        try {
            decl = origDeclareFn(exportFn);
        } catch (exc) {
            moduleError = exportFn("__moduleError", exc);
        }

        return {
            setters: (decl && decl.setters || []).map(function(setter) { return setterInterceptFn.bind(null, setter); }),
            execute: function() {
                if (!moduleError) {
                    try {
                        decl.execute();
                    } catch (exc) {
                        moduleError = exportFn("__moduleError", exc);
                    }
                }
            }
        };
    }

    if (testMode) {
        //
        // In test mode we intercept the fetch and register.
        // We always instantiate unimplemented modules, to not break the loading phase, since it's not exception safe.
        // We serve module stubs from LDE to not run into CSP issues on web.
        //

        var systemFetch = System.fetch.bind(System),
            systemRegister = System.register.bind(System);

        System.fetch = function(load) {
            if (load.metadata.nodejs_fileNotFound && !load.name.startsWith("404/") /* intentionally excluded for NOT_FOUND, i.e. no fallback module */) {
                return "System.register([], function() { throw new N4ApiNotImplementedError('Not implemented: " + load.name + "'); });\n";
            }
            return systemFetch(load);
        };

        System.register = function(deps, declareFn) {
            systemRegister(deps, interceptDeclare.bind(null, declareFn));
        };
    }

}());
