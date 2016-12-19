(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft.runner.node/n4/mangel/runner/node/NodeTestCLI',
		'@node/child_process',
		'@node/fs',
		'@node/os',
		'@node/path',
		'@@cjs/n4mf-parser/index',
		'@@cjs/n4js-cli/index'
	], function($n4Export) {
		var NodeTestCLI, child_process, lib_fs, os, lib_path, n4mf_parser, n4js_cli, MANIFEST_N4MF, requireResolve, createTempDir, detectProjectDir, coreLibs, rtLibs, mangelhaftNpmDeps, targetPlatformStub, NodeTestAPI;
		requireResolve = function requireResolve(id) {
			let sysObj = System;
			return sysObj["_nodeRequire"]["resolve"](id);
		};
		$n4Export('requireResolve', requireResolve);
		createTempDir = function createTempDir() {
			return $spawn(function*() {
				let prefix = lib_path.join(os.tmpdir(), "n4js-mangelhaft-");
				return $n4promisifyFunction(lib_fs.mkdtemp, [
					prefix
				], false, false);
			}.apply(this, arguments));
		};
		$n4Export('createTempDir', createTempDir);
		detectProjectDir = function detectProjectDir(npm) {
			let p = requireResolve(("" + npm + "/package.json"));
			return p.substring(0, p.lastIndexOf(lib_path.sep));
		};
		NodeTestAPI = function NodeTestAPI() {};
		$n4Export('default', NodeTestAPI);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_u002erunner_u002enode_n4_u002fmangel_u002frunner_u002fnode_u002fNodeTestCLI) {
					NodeTestCLI = $_import_eu_u002enumberfour_u002emangelhaft_u002erunner_u002enode_n4_u002fmangel_u002frunner_u002fnode_u002fNodeTestCLI.default;
				},
				function($_import_n4js_u002druntime_u002dnode_child_process) {
					child_process = $_import_n4js_u002druntime_u002dnode_child_process;
				},
				function($_import_n4js_u002druntime_u002dnode_fs) {
					lib_fs = $_import_n4js_u002druntime_u002dnode_fs;
				},
				function($_import_n4js_u002druntime_u002dnode_os) {
					os = $_import_n4js_u002druntime_u002dnode_os;
				},
				function($_import_n4js_u002druntime_u002dnode_path) {
					lib_path = $_import_n4js_u002druntime_u002dnode_path;
				},
				function($_import_n4mf_u002dparser_index) {
					n4mf_parser = $_import_n4mf_u002dparser_index;
				},
				function($_import_n4js_u002dcli_index) {
					n4js_cli = $_import_n4js_u002dcli_index;
				}
			],
			execute: function() {
				MANIFEST_N4MF = "manifest.n4mf";
				coreLibs = [
					"n4js.lang",
					"eu.numberfour.mangelhaft.assert"
				];
				rtLibs = [
					"n4js-runtime-es2015",
					"n4js-runtime-esnext",
					"n4js-runtime-fetch",
					"n4js-runtime-n4",
					"n4js-runtime-v8",
					"n4js-runtime-node"
				];
				mangelhaftNpmDeps = [
					"json-cycle"
				];
				targetPlatformStub = {
					location: [
						{
							config: {},
							projects: {},
							repoType: "npm"
						}
					]
				};
				$makeClass(NodeTestAPI, N4Object, [], {}, {
					exec: {
						value: function exec___n4(options) {
							return $spawn(function*() {
								let idToManifest = new Map(), prjToManifest = new Map();
								var addManifest = function addManifest(dir) {
									return $spawn(function*() {
										let manifest = (yield n4mf_parser.readManifest(lib_path.join(dir, MANIFEST_N4MF)));
										idToManifest.set(manifest["ProjectId"], manifest);
										prjToManifest.set(dir, manifest);
									}.apply(this, arguments));
								};
								for(let dir of (options.projects || [])) {
									(yield addManifest(dir));
								}
								for(let dir of (options.scan || [])) {
									for(let p of (yield $n4promisifyFunction(lib_fs.readdir, [
										dir
									], false, false))) {
										let prj = lib_path.join(dir, p), stat = (yield $n4promisifyFunction(lib_fs.stat, [
											prj
										], false, false));
										if (stat.isDirectory() && lib_fs.existsSync(lib_path.join(prj, MANIFEST_N4MF))) {
											(yield addManifest(prj));
										}
									}
								}
								for(let id of coreLibs) {
									if (!idToManifest.has(id)) {
										(yield addManifest(detectProjectDir(id)));
									}
								}
								if (options.debug) {
									console.log("projects:", Array.from(prjToManifest.keys()));
								}
								if (options.compile) {
									let n4jscTempDir = (yield createTempDir());
									if (options.testCatalog.startsWith("http://")) {
										options.testCatalog = lib_path.join(n4jscTempDir, "catalog.json");
									}
									if (!options.targetPlatformInstallLocation) {
										options.targetPlatformInstallLocation = lib_path.join(n4jscTempDir, "targetPlatform-install");
									}
									let targetPlatform = targetPlatformStub;
									if (options.targetPlatformFile) {
										targetPlatform = JSON.parse((yield $n4promisifyFunction(lib_fs.readFile, [
											options.targetPlatformFile,
											{
												encoding: "UTF-8"
											}
										], false, false)));
									}
									let npmDeps = targetPlatform.location[0].projects;
									for(let dep of mangelhaftNpmDeps) {
										if (!npmDeps[dep]) {
											npmDeps[dep] = {};
										}
									}
									options.targetPlatformFile = lib_path.join(n4jscTempDir, "targetplatform.n4tp");
									(yield $n4promisifyFunction(lib_fs.writeFile, [
										options.targetPlatformFile,
										JSON.stringify(targetPlatform, null, 2),
										{
											encoding: "UTF-8"
										}
									], false, false));
									let projects = Array.from(prjToManifest.keys()).concat(rtLibs.filter((function(id) {
										return !idToManifest.has(id);
									}).bind(this)).map(detectProjectDir));
									(yield n4js_cli.n4jsc({
										t: "projects",
										testCatalogFile: options.testCatalog,
										targetPlatformFile: options.targetPlatformFile,
										targetPlatformInstallLocation: options.targetPlatformInstallLocation,
										targetPlatformSkipInstall: options.targetPlatformSkipInstall,
										nodejsLocation: process.execArgv[0],
										debug: options.debug
									}, projects));
								}
								let nodePath = [];
								for(let $destructStep$0 of prjToManifest) {
									let $destruct0 = $sliceToArrayForDestruct(($destructStep$0), 2), dir = $destruct0[0], manifest = $destruct0[1];
									let out = manifest["Output"];
									if (out) {
										nodePath.push(lib_path.join(dir, manifest["Output"], "es"));
									}
								}
								if (options.targetPlatformInstallLocation) {
									nodePath.push(lib_path.join(options.targetPlatformInstallLocation, "node_modules"));
								}
								if (process.env.NODE_PATH) {
									nodePath.push(process.env.NODE_PATH);
								}
								let env = Object.assign({}, process.env, {
									NODE_PATH: nodePath.join(lib_path.delimiter)
								});
								let n4type = this.n4type, consoleTestRunner = n4type.fqn.split(".");
								consoleTestRunner.pop();
								consoleTestRunner.pop();
								consoleTestRunner.unshift(n4type.origin);
								consoleTestRunner.push("NodeTestRunner:");
								let args = NodeTestCLI.toCommandLine(options);
								args.unshift(consoleTestRunner.join("/"));
								(options.define || []).forEach((function(kv) {
									let pair = kv.split("=");
									env["N4JS_RT_" + pair[0].replace(/-/g, "_")] = (pair[1] || "");
								}).bind(this));
								if (options.debug) {
									console.log("env:", env);
									console.log("args:", args);
								}
								(yield new Promise((function(resolve, reject) {
									let execArgv = process.execArgv.slice();
									if (options.inspect) {
										execArgv.push(("--inspect=" + options.inspect + ""), "--debug-brk");
									}
									child_process.fork(requireResolve("n4js-node/n4js-cli"), args, {
										execArgv: execArgv,
										env: env
									}).on("close", (function(code) {
										if (code === 0) {
											resolve(code);
										} else {
											reject(code);
										}
									}).bind(this));
								}).bind(this)));
							}.apply(this, arguments));
						}
					}
				}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'NodeTestAPI',
						origin: 'eu.numberfour.mangelhaft.runner.node',
						fqn: 'n4.mangel.runner.node.NodeTestAPI.NodeTestAPI',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4Method({
								name: 'exec',
								isStatic: true,
								jsFunction: staticProto['exec'],
								annotations: []
							})
						],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
			}
		};
	});
})(typeof module !== 'undefined' && module.exports ? require('n4js-node/index').System(require, module) : System);
//# sourceMappingURL=NodeTestAPI.map
