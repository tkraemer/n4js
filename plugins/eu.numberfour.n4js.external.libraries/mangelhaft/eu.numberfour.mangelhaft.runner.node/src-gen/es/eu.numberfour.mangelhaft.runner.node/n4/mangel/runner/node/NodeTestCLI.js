(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft.runner.node/n4/mangel/runner/node/NodeTestAPI',
		'@@cjs/nomnom-patched/nomnom',
		'@node/path'
	], function($n4Export) {
		var NodeTestAPI, lib_nomnom, lib_path, NodeTestCLI, cwd, resolvePath, nomnomOptions, nomnomHelp;
		NodeTestCLI = function NodeTestCLI() {};
		$n4Export('default', NodeTestCLI);
		resolvePath = function resolvePath(p) {
			if (p.startsWith("http://")) {
				return p;
			}
			return lib_path.resolve(cwd, p);
		};
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_u002erunner_u002enode_n4_u002fmangel_u002frunner_u002fnode_u002fNodeTestAPI) {
					NodeTestAPI = $_import_eu_u002enumberfour_u002emangelhaft_u002erunner_u002enode_n4_u002fmangel_u002frunner_u002fnode_u002fNodeTestAPI.default;
				},
				function($_import_nomnom_u002dpatched_nomnom) {
					lib_nomnom = $_import_nomnom_u002dpatched_nomnom;
				},
				function($_import_n4js_u002druntime_u002dnode_path) {
					lib_path = $_import_n4js_u002druntime_u002dnode_path;
				}
			],
			execute: function() {
				$makeClass(NodeTestCLI, Object, [], {}, {
					toCommandLine: {
						value: function toCommandLine___n4(options) {
							let optionsObj = options, res = [];
							Object.keys(optionsObj).forEach((function(k) {
								const info = nomnomOptions[k], v = optionsObj[k];
								if (info && typeof v !== "undefined") {
									k = "--" + k;
									if (Array.isArray(v)) {
										const positionedArg = typeof info.position !== "undefined";
										(v).forEach((function(vv) {
											return positionedArg ? res.push(vv) : res.push(k, vv);
										}).bind(this));
									} else {
										if (typeof v !== "boolean") {
											res.push(k, v);
										} else if (v) {
											res.push(k);
										}
									}
								}
							}).bind(this));
							return res;
						}
					},
					parseCommandLine: {
						value: function parseCommandLine___n4() {
							return lib_nomnom.script("n4js-mangelhaft").help(nomnomHelp).options(nomnomOptions).parse();
						}
					},
					run: {
						value: function run___n4() {
							return $spawn(function*() {
								let options = this.parseCommandLine();
								if (options) {
									(yield NodeTestAPI.exec(options));
								}
							}.apply(this, arguments));
						}
					}
				}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'NodeTestCLI',
						origin: 'eu.numberfour.mangelhaft.runner.node',
						fqn: 'n4.mangel.runner.node.NodeTestCLI.NodeTestCLI',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4Method({
								name: 'toCommandLine',
								isStatic: true,
								jsFunction: staticProto['toCommandLine'],
								annotations: []
							}),
							new N4Method({
								name: 'parseCommandLine',
								isStatic: true,
								jsFunction: staticProto['parseCommandLine'],
								annotations: []
							}),
							new N4Method({
								name: 'run',
								isStatic: true,
								jsFunction: staticProto['run'],
								annotations: []
							})
						],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				cwd = process.cwd();
				nomnomOptions = {
					scan: {
						abbr: "s",
						list: true,
						metavar: "PATH",
						transform: resolvePath,
						help: "Scan for all projects in directory."
					},
					projects: {
						position: 0,
						list: true,
						metavar: "PATH",
						transform: resolvePath,
						help: "Project directories."
					},
					filter: {
						abbr: "f",
						list: true,
						help: "Filter for matching test cases."
					},
					define: {
						abbr: "D",
						list: true,
						help: "Definition of N4JS runtime options per key=value."
					},
					compile: {
						abbr: "c",
						flag: true,
						help: "Compiles the given targets, generating a test catalog."
					},
					testCatalog: {
						default: "http://localhost:8080/n4.ide/testing/sessions/testcatalog",
						metavar: "URL|PATH",
						transform: resolvePath,
						help: "Test catalog (JSON) to be used. Option --compile will write the file, switches to temp file in case of URL."
					},
					targetPlatformFile: {
						abbr: "tp",
						metavar: "PATH",
						transform: resolvePath,
						help: "NPM dependency definitions."
					},
					targetPlatformInstallLocation: {
						abbr: "tl",
						metavar: "PATH",
						transform: resolvePath,
						help: "NPM dependencies install location."
					},
					targetPlatformSkipInstall: {
						flag: true,
						help: "Skips NPM dependency installation, assuming already existing."
					},
					xunitReportFile: {
						metavar: "PATH",
						transform: resolvePath,
						help: "jUnit/xUnit report output file."
					},
					xunitReportName: {
						default: "N4JS Mangelhaft test",
						help: "jUnit/xUnit report name."
					},
					xunitReportPackage: {
						help: "jUnit/xUnit report package name."
					},
					debug: {
						flag: true,
						abbr: "d",
						help: "Debug output."
					}
				};
				nomnomHelp = 'Runs N4JS mangelhaft testing.\n\nBy default n4js-mangelhaft will retrieve the test catalog from a running IDE and just runs the tests.\nOn CI systems, you may want to compile and run the tests in one go:\n\n$ n4js-mangelhaft --compile --xunitReportFile ./jenkins/reports.xml --scan ./projects\n';
			}
		};
	});
})(typeof module !== 'undefined' && module.exports ? require('n4js-node/index').System(require, module) : System);
//# sourceMappingURL=NodeTestCLI.map
