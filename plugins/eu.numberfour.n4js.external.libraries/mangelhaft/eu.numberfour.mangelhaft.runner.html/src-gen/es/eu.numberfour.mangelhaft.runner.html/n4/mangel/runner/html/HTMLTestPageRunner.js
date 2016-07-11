(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft/n4/mangel/Test',
		'eu.numberfour.mangelhaft/n4/mangel/TestController',
		'eu.numberfour.mangelhaft/n4/mangel/mangeltypes/TestDIComponent',
		'eu.numberfour.mangelhaft.reporter.console/n4/mangel/reporter/console/ConsoleReporter',
		'eu.numberfour.mangelhaft.reporter.html/n4/mangel/reporter/html/HTMLReporter',
		'n4js.lang/n4js/lang/N4Injector'
	], function($n4Export) {
		var FIXME1, FIXME2, IFIXME, IFIXME2, TestController, TestDIComponent, ConsoleReporter, HTMLReporter, N4Injector, getParm, HTMLTestPageRunner, TestBinder, Root, parentinj, root, main;
		getParm = function getParm(name) {
			let names = new RegExp('[?&]' + encodeURIComponent(name) + '=([^&]*)').exec(location.search);
			if (names) {
				return decodeURIComponent(names[1]);
			}
			return "";
		};
		HTMLTestPageRunner = function HTMLTestPageRunner() {
			this.consoleReporter = undefined;
			this.htmlReporter = undefined;
			this.controller = undefined;
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
				function($_import_eu_u002enumberfour_u002emangelhaft_u002ereporter_u002ehtml_n4_u002fmangel_u002freporter_u002fhtml_u002fHTMLReporter) {
					HTMLReporter = $_import_eu_u002enumberfour_u002emangelhaft_u002ereporter_u002ehtml_n4_u002fmangel_u002freporter_u002fhtml_u002fHTMLReporter.HTMLReporter;
				},
				function($_import_n4js_u002elang_n4js_u002flang_u002fN4Injector) {
					N4Injector = $_import_n4js_u002elang_n4js_u002flang_u002fN4Injector.N4Injector;
				}
			],
			execute: function() {
				$makeClass(HTMLTestPageRunner, Object, [], {
					run: {
						value: function run___n4() {
							return $spawn(function*() {
								let groupsStr = getParm("groups"), groupsFilter = getParm("filter"), groupsArray, testsStr = getParm("tests"), testsArray, reporterStr = getParm("reporter"), endpoint = getParm("endpoint"), tests, buffer = [], resultGroups;
								;
								try {
									tests = ((yield ((yield window.fetch("/test-catalog.json", {
										headers: {
											'Content-Type': "application/vnd.n4.ide.assemble_test_catalog_req.tm+json"
										}
									}))).json()));
								} catch(ex) {
									document.body.innerHTML = "<h3>Could not fetch the test catalog. (Try restarting the LDE and reloading)";
									return;
								}
								this.controller.reporters = [
									this.htmlReporter,
									this.consoleReporter
								];
								if (groupsStr) {
									groupsArray = groupsStr.split(",").map(function(group) {
										return group.trim();
									});
								}
								if (testsStr) {
									testsArray = testsStr.split(",").map(function(test) {
										return test.trim();
									});
								}
								if (groupsFilter) {
									tests.testDescriptors = tests.testDescriptors.filter(function(testInfo) {
										let name = testInfo.fqn;
										return name.indexOf(groupsFilter) === 0;
									});
									if (testsArray && testsArray.length && tests.testDescriptors.length === 1) {
										tests.testDescriptors[0].testMethods = testsArray;
									}
								}
								try {
									resultGroups = (yield this.controller.runGroups(tests));
									buffer.forEach(function(logEntry) {
										console.log.apply(console, logEntry);
									});
								} catch(exc) {
									let er = exc;
									console.error(er.stack);
									document.body.innerText += er.stack;
									throw exc;
								}
							}.apply(this, arguments));
						}
					},
					consoleReporter: {
						value: undefined,
						writable: true
					},
					htmlReporter: {
						value: undefined,
						writable: true
					},
					controller: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'HTMLTestPageRunner',
						origin: 'eu.numberfour.mangelhaft.runner.html',
						fqn: 'n4.mangel.runner.html.HTMLTestPageRunner.HTMLTestPageRunner',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
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
								name: 'htmlReporter',
								isStatic: false,
								annotations: [
									new N4Annotation({
										name: 'Inject',
										details: []
									})
								]
							}),
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
				Object.defineProperty(HTMLTestPageRunner, '$di', {
					value: {
						fieldsInjectedTypes: [
							{
								name: 'consoleReporter',
								type: ConsoleReporter
							},
							{
								name: 'htmlReporter',
								type: HTMLReporter
							},
							{
								name: 'controller',
								type: TestController
							}
						]
					}
				});
				$makeClass(TestBinder, Object, [], {}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'TestBinder',
						origin: 'eu.numberfour.mangelhaft.runner.html',
						fqn: 'n4.mangel.runner.html.HTMLTestPageRunner.TestBinder',
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
				$makeClass(Root, Object, [], {
					runner: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'Root',
						origin: 'eu.numberfour.mangelhaft.runner.html',
						fqn: 'n4.mangel.runner.html.HTMLTestPageRunner.Root',
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
								type: HTMLTestPageRunner
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
//# sourceMappingURL=HTMLTestPageRunner.map
