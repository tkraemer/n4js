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
/*global N4ApiNotImplementedError */

(function() {
    "use strict";

    var options = n4.runtimeOptions,
        testMode = options["test-mode"],
        sjsSetters = Symbol("sjs setters");

    function exportFn(exp, k, v) {
        if (exp[k] !== v) {
            exp[k] = v;
            exp[sjsSetters].forEach(function(setter) {
                setter(exp);
            });
        }
        return v;
    }

    var esModules = new WeakMap();

    function getESModule(mod) {
        if (mod.__esModule) {
            return mod;
        }
        var esMod = esModules.get(mod);
        if (!esMod) {
            esMod = n4.cjsCreateModule(mod);
            esModules.set(mod, esMod);
        }
        return esMod;
    }

    function mapId(id) {
        id = id.replace(n4.cjsModulesPrefix_re, "");
        return n4.mapModulePath(id);
    }

    /**
     * Mocks/emulates System(JS) ES2015 loader interface.
     */
    function Loader(req_, mod) {
        var req = this._nodeRequire = function(id) {
                id = mapId(id);
                if (testMode) {
                    try {
                        id = req_.resolve(id);
                    } catch (exc) {
                        if (!id.startsWith("404/")) { // intentionally excluded for NOT_FOUND, i.e. no API-not-impl
                            throw new N4ApiNotImplementedError("Not implemented: " + id);
                        }
                    }
                }
                return req_(id);
            };
        req.cache = req_.cache;
        req.resolve = req_.resolve;

        this.mod = mod;
    }
    Loader.prototype = {
        _commonJS: true,

        register: function(deps, declareFn) {
            var exp = this.mod.exports;

            Object.defineProperty(exp, "__esModule", { value: true });
            exp[sjsSetters] = [];

            var req = this._nodeRequire,
                decl = declareFn.call(null, exportFn.bind(null, exp));
            if (decl) {
                (decl.setters || []).forEach(function(setter, i) {
                    var imp = getESModule(req(deps[i])),
                        setters = imp[sjsSetters];
                    if (setters) {
                        setters.push(setter);
                    }
                    setter(imp);
                }, this);
                decl.execute();
            }
        },

        registerDynamic: function(deps, executingRequire, declareFn) {
            var req = this._nodeRequire,
                mod = this.mod;
            if (!executingRequire) {
                deps.forEach(req);
            }
            mod.exports = declareFn.call(null, req, mod.exports, mod) || mod.exports;
        },

        normalize: function(name) {
            name = mapId(name);
            name = this._nodeRequire.resolve(name); // normalizes into require.cache path
            return Promise.resolve(name);
        },
        delete: function(normalizedName) {
            delete this._nodeRequire.cache[normalizedName];
        },
        has: function(normalizedName) {
            return !!this._nodeRequire.cache[normalizedName];
        },
        get: function(normalizedName) {
            var mod = this._nodeRequire.cache[normalizedName];
            // in sync with SystemJS: either the System.register'ed ES module or the __useDefault tagged CJS exports:
            return mod ? mod.exports : null;
        },
        import: function(name) {
            var req = this._nodeRequire;
            return new Promise(function(resolveFn) {
                resolveFn(req(name));
            });
        },

        throwPendingError: function(mod) {
            if (mod.__moduleError) {
                throw mod.__moduleError;
            }
            return mod;
        }
    };

    exports.Loader = Loader;
})();
