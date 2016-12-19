(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft/n4/mangel/Test',
		'eu.numberfour.mangelhaft.assert/n4/mangel/assert/Assert',
		'eu.numberfour.mangelhaft.test/n4/mangel/test/helper/FIXMEMock',
		'eu.numberfour.mangelhaft.assert/n4/mangel/precondition/Precondition',
		'n4js.lang/n4js/lang/N4Injector',
		'eu.numberfour.mangelhaft/n4/mangel/mangeltypes/TestDIComponent'
	], function($n4Export) {
		var IFIXME, IFIXME2, Assert, FIXME2Mock, Precondition, N4Injector, TestDIComponent, TestBinder, TestSubject, TestSubjectNoBind, InjectionTest;
		TestBinder = function TestBinder() {};
		TestSubject = function TestSubject() {
			this.fix = undefined;
			this.fix2 = undefined;
		};
		TestSubjectNoBind = function TestSubjectNoBind() {
			this.fix = undefined;
			this.fix2 = undefined;
		};
		InjectionTest = function InjectionTest() {
			this.subject = undefined;
			this.subjectNoBind = undefined;
			this.parentInjector = undefined;
		};
		$n4Export('InjectionTest', InjectionTest);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTest) {
					IFIXME = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTest.IFIXME;
					IFIXME2 = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTest.IFIXME2;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssert) {
					Assert = $_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssert.Assert;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002etest_n4_u002fmangel_u002ftest_u002fhelper_u002fFIXMEMock) {
					FIXME2Mock = $_import_eu_u002enumberfour_u002emangelhaft_u002etest_n4_u002fmangel_u002ftest_u002fhelper_u002fFIXMEMock.FIXME2Mock;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fprecondition_u002fPrecondition) {
					Precondition = $_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fprecondition_u002fPrecondition.Precondition;
				},
				function($_import_n4js_u002elang_n4js_u002flang_u002fN4Injector) {
					N4Injector = $_import_n4js_u002elang_n4js_u002flang_u002fN4Injector.N4Injector;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fmangeltypes_u002fTestDIComponent) {
					TestDIComponent = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fmangeltypes_u002fTestDIComponent.TestDIComponent;
				}
			],
			execute: function() {
				$makeClass(TestBinder, N4Object, [], {}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'TestBinder',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.InjectionTest.TestBinder',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [],
						consumedMembers: [],
						annotations: [
							new N4Annotation({
								name: 'Bind',
								details: [
									'IFIXME2',
									'FIXME2Mock'
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
								from: IFIXME2,
								to: FIXME2Mock
							}
						],
						methodBindings: [],
						fieldsInjectedTypes: []
					}
				});
				$makeClass(TestSubject, N4Object, [], {
					fix: {
						value: undefined,
						writable: true
					},
					fix2: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'TestSubject',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.InjectionTest.TestSubject',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'fix',
								isStatic: false,
								annotations: [
									new N4Annotation({
										name: 'Inject',
										details: []
									})
								]
							}),
							new N4DataField({
								name: 'fix2',
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
				Object.defineProperty(TestSubject, '$di', {
					value: {
						parent: TestDIComponent,
						binders: [
							TestBinder
						],
						fieldsInjectedTypes: [
							{
								name: 'fix',
								type: IFIXME
							},
							{
								name: 'fix2',
								type: IFIXME2
							}
						]
					}
				});
				$makeClass(TestSubjectNoBind, N4Object, [], {
					fix: {
						value: undefined,
						writable: true
					},
					fix2: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'TestSubjectNoBind',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.InjectionTest.TestSubjectNoBind',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'fix',
								isStatic: false,
								annotations: [
									new N4Annotation({
										name: 'Inject',
										details: []
									})
								]
							}),
							new N4DataField({
								name: 'fix2',
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
							})
						]
					});
					return metaClass;
				});
				Object.defineProperty(TestSubjectNoBind, '$di', {
					value: {
						parent: TestDIComponent,
						binders: [],
						fieldsInjectedTypes: [
							{
								name: 'fix',
								type: IFIXME
							},
							{
								name: 'fix2',
								type: IFIXME2
							}
						]
					}
				});
				$makeClass(InjectionTest, N4Object, [], {
					preconditionCheck: {
						value: function preconditionCheck___n4() {
							try {
								N4Injector.of(TestSubject, this.parentInjector);
							} catch(ex) {
								Precondition.fail("Tests should not run if parent bindings have not been set");
							}
						}
					},
					setupPlain: {
						value: function setupPlain___n4() {
							this.subjectNoBind = N4Injector.of(TestSubjectNoBind, this.parentInjector).create(TestSubjectNoBind);
						}
					},
					setupMock: {
						value: function setupMock___n4() {
							this.subject = N4Injector.of(TestSubject, this.parentInjector, new TestBinder()).create(TestSubject);
						}
					},
					fromMangelhaft: {
						value: function fromMangelhaft___n4() {
							Assert.equal(this.subject.fix.logStr, "FIXME joe from Mangelhaft", "should be from Mangelhaft");
						}
					},
					fromMock: {
						value: function fromMock___n4() {
							Assert.equal(this.subject.fix2.logStr, "FIXME joe from Test", "should be from Mock");
						}
					},
					fromMangelhaftNoBind: {
						value: function fromMangelhaftNoBind___n4() {
							Assert.equal(this.subjectNoBind.fix.logStr, "FIXME joe from Mangelhaft", "shoudl be from Mangelhaft");
						}
					},
					fromMockNoBind: {
						value: function fromMockNoBind___n4() {
							Assert.equal(this.subjectNoBind.fix2.logStr, "FIXME joe from Mangelhaft", "should be from Mangelhaft");
						}
					},
					subject: {
						value: undefined,
						writable: true
					},
					subjectNoBind: {
						value: undefined,
						writable: true
					},
					parentInjector: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'InjectionTest',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.InjectionTest.InjectionTest',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'subject',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'subjectNoBind',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'parentInjector',
								isStatic: false,
								annotations: [
									new N4Annotation({
										name: 'Inject',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'preconditionCheck',
								isStatic: false,
								jsFunction: instanceProto['preconditionCheck'],
								annotations: [
									new N4Annotation({
										name: 'BeforeAll',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'setupPlain',
								isStatic: false,
								jsFunction: instanceProto['setupPlain'],
								annotations: [
									new N4Annotation({
										name: 'Before',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'setupMock',
								isStatic: false,
								jsFunction: instanceProto['setupMock'],
								annotations: [
									new N4Annotation({
										name: 'Before',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'fromMangelhaft',
								isStatic: false,
								jsFunction: instanceProto['fromMangelhaft'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'fromMock',
								isStatic: false,
								jsFunction: instanceProto['fromMock'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'fromMangelhaftNoBind',
								isStatic: false,
								jsFunction: instanceProto['fromMangelhaftNoBind'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'fromMockNoBind',
								isStatic: false,
								jsFunction: instanceProto['fromMockNoBind'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							})
						],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				Object.defineProperty(InjectionTest, '$di', {
					value: {
						fieldsInjectedTypes: [
							{
								name: 'parentInjector',
								type: N4Injector
							}
						]
					}
				});
			}
		};
	});
})(typeof module !== 'undefined' && module.exports ? require('n4js-node/index').System(require, module) : System);
//# sourceMappingURL=InjectionTest.map
