(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft/n4/mangel/TestController',
		'eu.numberfour.mangelhaft.reporter.ide/n4/mangel/reporter/ide/IDEReporter',
		'n4js.lang/n4js/lang/N4Injector',
		'eu.numberfour.mangelhaft/n4/mangel/Test',
		'eu.numberfour.mangelhaft/n4/mangel/mangeltypes/TestDIComponent'
	], function($n4Export) {
		var TestController, IDEReporter, N4Injector, FIXME1, FIXME2, IFIXME, IFIXME2, TestDIComponent, IDENodeTestRunner, TestBinder, Root, parentinj, root, main;
		IDENodeTestRunner = function IDENodeTestRunner() {
			this.controller = undefined;
			this.reporter = undefined;
		};
		TestBinder = function TestBinder() {};
		Root = function Root() {
			this.runner = undefined;
		};
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTestController) {
					TestController = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTestController.TestController;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002ereporter_u002eide_n4_u002fmangel_u002freporter_u002fide_u002fIDEReporter) {
					IDEReporter = $_import_eu_u002enumberfour_u002emangelhaft_u002ereporter_u002eide_n4_u002fmangel_u002freporter_u002fide_u002fIDEReporter.IDEReporter;
				},
				function($_import_n4js_u002elang_n4js_u002flang_u002fN4Injector) {
					N4Injector = $_import_n4js_u002elang_n4js_u002flang_u002fN4Injector.N4Injector;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTest) {
					FIXME1 = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTest.FIXME1;
					FIXME2 = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTest.FIXME2;
					IFIXME = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTest.IFIXME;
					IFIXME2 = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTest.IFIXME2;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fmangeltypes_u002fTestDIComponent) {
					TestDIComponent = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fmangeltypes_u002fTestDIComponent.TestDIComponent;
				}
			],
			execute: function() {
				$makeClass(IDENodeTestRunner, N4Object, [], {
					run: {
						value: function run___n4() {
							return $spawn(function *() {
								try {
									let testCatalog, catalogDef = n4.runtimeOptions["test-catalog"];
									if (typeof catalogDef === "string") {
										let req = (yield fetch(catalogDef, {
											headers: {
												'Content-Type': "application/vnd.n4.ide.assemble_test_catalog_req.tm+json"
											}
										}));
										testCatalog = ((yield req.json()));
									} else {
										testCatalog = catalogDef;
									}
									this.reporter.endpoint = testCatalog.endpoint;
									this.controller.reporters = [
										this.reporter
									];
									(yield this.controller.runGroups(testCatalog, 100));
								} catch(err) {
									let errObj = err;
									console.error(err + "\nstack: " + errObj.stack.replace(/^Error:?\s*/, ""));
									throw err;
								}
							}.apply(this, arguments));
						}
					},
					controller: {
						value: undefined,
						writable: true
					},
					reporter: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'IDENodeTestRunner',
						origin: 'eu.numberfour.mangelhaft.runner.ide',
						fqn: 'n4.mangel.runner.ide.IDENodeTestRunner.IDENodeTestRunner',
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
								name: 'reporter',
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
				Object.defineProperty(IDENodeTestRunner, '$di', {
					value: {
						fieldsInjectedTypes: [
							{
								name: 'controller',
								type: TestController
							},
							{
								name: 'reporter',
								type: IDEReporter
							}
						]
					}
				});
				$makeClass(TestBinder, N4Object, [], {}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'TestBinder',
						origin: 'eu.numberfour.mangelhaft.runner.ide',
						fqn: 'n4.mangel.runner.ide.IDENodeTestRunner.TestBinder',
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
						origin: 'eu.numberfour.mangelhaft.runner.ide',
						fqn: 'n4.mangel.runner.ide.IDENodeTestRunner.Root',
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
								type: IDENodeTestRunner
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
//# sourceMappingURL=IDENodeTestRunner.map
