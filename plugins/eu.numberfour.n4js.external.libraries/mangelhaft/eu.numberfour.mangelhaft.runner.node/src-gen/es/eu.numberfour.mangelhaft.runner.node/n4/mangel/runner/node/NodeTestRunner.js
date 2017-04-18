(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft/n4/mangel/Test',
		'eu.numberfour.mangelhaft/n4/mangel/TestController',
		'eu.numberfour.mangelhaft/n4/mangel/mangeltypes/TestDIComponent',
		'eu.numberfour.mangelhaft.reporter.console/n4/mangel/reporter/console/ConsoleReporter',
		'eu.numberfour.mangelhaft.reporter.xunit/n4/mangel/reporter/xunit/XUnitReporter',
		'eu.numberfour.mangelhaft.runner.node/n4/mangel/runner/node/NodeTestCLI',
		'n4js.lang/n4js/lang/N4Injector',
		'@node/fs',
		'@@cjs/cli-color/index'
	], function($n4Export) {
		var FIXME1, FIXME2, IFIXME, IFIXME2, TestController, TestDIComponent, ConsoleReporter, XUnitReporter, NodeTestCLI, N4Injector, lib_fs, cli_color_, NodeTestRunner, TestBinder, Root, parentinj, root, main;
		NodeTestRunner = function NodeTestRunner() {
			this.controller = undefined;
			this.consoleReporter = undefined;
			this.xunitReporter = undefined;
		};
		TestBinder = function TestBinder() {};
		Root = function Root() {
			this.runner = undefined;
		};
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTest) {
					FIXME1 = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTest.FIXME1;
					FIXME2 = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTest.FIXME2;
					IFIXME = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTest.IFIXME;
					IFIXME2 = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTest.IFIXME2;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTestController) {
					TestController = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTestController.TestController;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fmangeltypes_u002fTestDIComponent) {
					TestDIComponent = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fmangeltypes_u002fTestDIComponent.TestDIComponent;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002ereporter_u002econsole_n4_u002fmangel_u002freporter_u002fconsole_u002fConsoleReporter) {
					ConsoleReporter = $_import_eu_u002enumberfour_u002emangelhaft_u002ereporter_u002econsole_n4_u002fmangel_u002freporter_u002fconsole_u002fConsoleReporter.ConsoleReporter;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002ereporter_u002exunit_n4_u002fmangel_u002freporter_u002fxunit_u002fXUnitReporter) {
					XUnitReporter = $_import_eu_u002enumberfour_u002emangelhaft_u002ereporter_u002exunit_n4_u002fmangel_u002freporter_u002fxunit_u002fXUnitReporter.XUnitReporter;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002erunner_u002enode_n4_u002fmangel_u002frunner_u002fnode_u002fNodeTestCLI) {
					NodeTestCLI = $_import_eu_u002enumberfour_u002emangelhaft_u002erunner_u002enode_n4_u002fmangel_u002frunner_u002fnode_u002fNodeTestCLI.default;
				},
				function($_import_n4js_u002elang_n4js_u002flang_u002fN4Injector) {
					N4Injector = $_import_n4js_u002elang_n4js_u002flang_u002fN4Injector.N4Injector;
				},
				function($_import_n4js_u002druntime_u002dnode_fs) {
					lib_fs = $_import_n4js_u002druntime_u002dnode_fs;
				},
				function($_import_cli_u002dcolor_index) {
					cli_color_ = $_import_cli_u002dcolor_index;
				}
			],
			execute: function() {
				$makeClass(NodeTestRunner, N4Object, [], {
					run: {
						value: function run___n4() {
							return $spawn(function *() {
								let options = NodeTestCLI.parseCommandLine(), testCatalog;
								if (!options) {
									return;
								}
								if (options.testCatalog.startsWith("http://")) {
									let req = (yield fetch(options.testCatalog, {
										headers: {
											'Content-Type': "application/vnd.n4.ide.assemble_test_catalog_req.tm+json"
										}
									}));
									testCatalog = ((yield req.json()));
								} else {
									testCatalog = {
										testDescriptors: JSON.parse((yield $n4promisifyFunction(lib_fs.readFile, [
											options.testCatalog,
											{
												encoding: "UTF-8"
											}
										], false, false)))
									};
								}
								if (options.filter && options.filter.length) {
									const filters = options.filter.slice(0).sort();
									const descriptors = [];
									for(let info of testCatalog.testDescriptors) {
										let methods = new Set();
										for(let filter of filters) {
											const $destruct0 = $sliceToArrayForDestruct((filter.split("#")), 2), fqnFilter = $destruct0[0], methodFilter = $destruct0[1];
											if (!fqnFilter || info.fqn.indexOf(fqnFilter) >= 0) {
												if (methodFilter) {
													for(let method of info.testMethods) {
														if (method.indexOf(methodFilter) >= 0) {
															methods.add(method);
														}
													}
												} else {
													methods = new Set(info.testMethods);
													break;
												}
											}
										}
										if (methods.size > 0) {
											info.testMethods = Array.from(methods);
											descriptors.push(info);
										}
									}
									testCatalog.testDescriptors = descriptors;
								}
								this.consoleReporter.cliColor = cli_color_.default;
								let reporters = [
									this.consoleReporter
								];
								if (options.xunitReportFile) {
									this.xunitReporter.spec = options;
									reporters.push(this.xunitReporter);
								}
								this.controller.reporters = reporters;
								let resGroups = (yield this.controller.runGroups(testCatalog, 420187));
								if ((resGroups.failures !== 0) || (resGroups.errors !== 0)) {
									throw new Error(("Test run failed with " + resGroups.errors + " errors and " + resGroups.failures + " failures."));
								}
							}.apply(this, arguments));
						}
					},
					controller: {
						value: undefined,
						writable: true
					},
					consoleReporter: {
						value: undefined,
						writable: true
					},
					xunitReporter: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'NodeTestRunner',
						origin: 'eu.numberfour.mangelhaft.runner.node',
						fqn: 'n4.mangel.runner.node.NodeTestRunner.NodeTestRunner',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'controller',
								isStatic: false,
								annotations: [
									new N4Annotation({
										name: 'Inject',
										details: []
									})
								]
							}),
							new N4DataField({
								name: 'consoleReporter',
								isStatic: false,
								annotations: [
									new N4Annotation({
										name: 'Inject',
										details: []
									})
								]
							}),
							new N4DataField({
								name: 'xunitReporter',
								isStatic: false,
								annotations: [
									new N4Annotation({
										name: 'Inject',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'run',
								isStatic: false,
								jsFunction: instanceProto['run'],
								annotations: []
							})
						],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				Object.defineProperty(NodeTestRunner, '$di', {
					value: {
						fieldsInjectedTypes: [
							{
								name: 'controller',
								type: TestController
							},
							{
								name: 'consoleReporter',
								type: ConsoleReporter
							},
							{
								name: 'xunitReporter',
								type: XUnitReporter
							}
						]
					}
				});
				$makeClass(TestBinder, N4Object, [], {}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'TestBinder',
						origin: 'eu.numberfour.mangelhaft.runner.node',
						fqn: 'n4.mangel.runner.node.NodeTestRunner.TestBinder',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [],
						consumedMembers: [],
						annotations: [
							new N4Annotation({
								name: 'Bind',
								details: [
									'IFIXME',
									'FIXME1'
								]
							}),
							new N4Annotation({
								name: 'Bind',
								details: [
									'IFIXME2',
									'FIXME2'
								]
							}),
							new N4Annotation({
								name: 'Binder',
								details: []
							})
						]
					});
					return metaClass;
				});
				Object.defineProperty(TestBinder, '$di', {
					value: {
						bindings: [
							{
								from: IFIXME,
								to: FIXME1
							},
							{
								from: IFIXME2,
								to: FIXME2
							}
						],
						methodBindings: [],
						fieldsInjectedTypes: []
					}
				});
				$makeClass(Root, N4Object, [], {
					runner: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'Root',
						origin: 'eu.numberfour.mangelhaft.runner.node',
						fqn: 'n4.mangel.runner.node.NodeTestRunner.Root',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'runner',
								isStatic: false,
								annotations: [
									new N4Annotation({
										name: 'Inject',
										details: []
									})
								]
							})
						],
						consumedMembers: [],
						annotations: [
							new N4Annotation({
								name: 'GenerateInjector',
								details: []
							}),
							new N4Annotation({
								name: 'WithParentInjector',
								details: [
									'TestDIComponent'
								]
							}),
							new N4Annotation({
								name: 'UseBinder',
								details: [
									'TestBinder'
								]
							})
						]
					});
					return metaClass;
				});
				Object.defineProperty(Root, '$di', {
					value: {
						parent: TestDIComponent,
						binders: [
							TestBinder
						],
						fieldsInjectedTypes: [
							{
								name: 'runner',
								type: NodeTestRunner
							}
						]
					}
				});
				parentinj = N4Injector.of(TestDIComponent);
				root = N4Injector.of(Root, parentinj).create(Root);
				main = root.runner;
				$n4Export('default', main);
			}
		};
	});
})(typeof module !== 'undefined' && module.exports ? require('n4js-node/index').System(require, module) : System);
//# sourceMappingURL=NodeTestRunner.map
