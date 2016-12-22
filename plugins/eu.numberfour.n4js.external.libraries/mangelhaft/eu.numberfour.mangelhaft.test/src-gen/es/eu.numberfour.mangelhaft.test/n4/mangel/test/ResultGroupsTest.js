(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft/n4/mangel/InstrumentedTest',
		'eu.numberfour.mangelhaft/n4/mangel/TestExecutor',
		'eu.numberfour.mangelhaft.assert/n4/mangel/assert/Assert',
		'eu.numberfour.mangelhaft/n4/mangel/mangeltypes/ResultGroups',
		'eu.numberfour.mangelhaft/n4/mangel/mangeltypes/TestSpy',
		'eu.numberfour.mangelhaft.test/n4/mangel/test/helper/TestExecutorTestsHelpers',
		'n4js.lang/n4js/lang/N4Injector'
	], function($n4Export) {
		var InstrumentedTest, TestExecutor, Assert, ResultGroups, TestSpy, MixedBag, N4Injector, SubTestSpy, TestBinder, TestSubject, ResultGroupsTest;
		SubTestSpy = function SubTestSpy() {
			TestSpy.prototype.constructor.call(this);
		};
		TestBinder = function TestBinder() {};
		TestSubject = function TestSubject() {
			this.executor = undefined;
		};
		ResultGroupsTest = function ResultGroupsTest(parentInjector) {
			this.subject = undefined;
			this.parentInjector = undefined;
			this.resultGroups = undefined;
			this.parentInjector = parentInjector;
		};
		$n4Export('ResultGroupsTest', ResultGroupsTest);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fInstrumentedTest) {
					InstrumentedTest = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fInstrumentedTest.InstrumentedTest;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTestExecutor) {
					TestExecutor = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTestExecutor.TestExecutor;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssert) {
					Assert = $_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssert.Assert;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fmangeltypes_u002fResultGroups) {
					ResultGroups = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fmangeltypes_u002fResultGroups.ResultGroups;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fmangeltypes_u002fTestSpy) {
					TestSpy = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fmangeltypes_u002fTestSpy.TestSpy;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002etest_n4_u002fmangel_u002ftest_u002fhelper_u002fTestExecutorTestsHelpers) {
					MixedBag = $_import_eu_u002enumberfour_u002emangelhaft_u002etest_n4_u002fmangel_u002ftest_u002fhelper_u002fTestExecutorTestsHelpers.MixedBag;
				},
				function($_import_n4js_u002elang_n4js_u002flang_u002fN4Injector) {
					N4Injector = $_import_n4js_u002elang_n4js_u002flang_u002fN4Injector.N4Injector;
				}
			],
			execute: function() {
				$makeClass(SubTestSpy, TestSpy, [], {}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'SubTestSpy',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.ResultGroupsTest.SubTestSpy',
						n4superType: TestSpy.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				Object.defineProperty(SubTestSpy, '$di', {
					value: {
						superType: TestSpy,
						fieldsInjectedTypes: []
					}
				});
				$makeClass(TestBinder, N4Object, [], {}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'TestBinder',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.ResultGroupsTest.TestBinder',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [],
						consumedMembers: [],
						annotations: [
							new N4Annotation({
								name: 'Binder',
								details: []
							}),
							new N4Annotation({
								name: 'Bind',
								details: [
									'TestSpy',
									'SubTestSpy'
								]
							})
						]
					});
					return metaClass;
				});
				Object.defineProperty(TestBinder, '$di', {
					value: {
						bindings: [
							{
								from: TestSpy,
								to: SubTestSpy
							}
						],
						methodBindings: [],
						fieldsInjectedTypes: []
					}
				});
				$makeClass(TestSubject, N4Object, [], {
					executor: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'TestSubject',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.ResultGroupsTest.TestSubject',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'executor',
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
						binders: [
							TestBinder
						],
						fieldsInjectedTypes: [
							{
								name: 'executor',
								type: TestExecutor
							}
						]
					}
				});
				$makeClass(ResultGroupsTest, N4Object, [], {
					setup: {
						value: function setup___n4() {
							return $spawn(function*() {
								let injector = N4Injector.of(TestSubject, this.parentInjector, new TestBinder());
								let tests = [];
								this.subject = injector.create(TestSubject);
								tests.push(new InstrumentedTest().load(MixedBag).setTestObject(new MixedBag()));
								this.resultGroups = (yield this.subject.executor.runTestsAsync(tests));
								this.resultGroups = ResultGroups.concat(this.resultGroups, this.resultGroups);
							}.apply(this, arguments));
						}
					},
					resultGroupsTotalFailuresTest: {
						value: function resultGroupsTotalFailuresTest___n4() {
							Assert.strictEqual(this.resultGroups.failures, 2);
						}
					},
					resultGroupsTotalErrorsTest: {
						value: function resultGroupsTotalErrorsTest___n4() {
							Assert.strictEqual(this.resultGroups.errors, 2);
						}
					},
					resultGroupsTotalSuccessesTest: {
						value: function resultGroupsTotalSuccessesTest___n4() {
							Assert.strictEqual(this.resultGroups.successes, 2);
						}
					},
					resultGroupsTotalSkipsTest: {
						value: function resultGroupsTotalSkipsTest___n4() {
							Assert.strictEqual(this.resultGroups.skipped, 6);
						}
					},
					resultGroupTotalFailuresTest: {
						value: function resultGroupTotalFailuresTest___n4() {
							Assert.strictEqual(this.resultGroups.results[0].failures, 1);
						}
					},
					resultGroupTotalErrorsTest: {
						value: function resultGroupTotalErrorsTest___n4() {
							Assert.strictEqual(this.resultGroups.results[0].errors, 1);
						}
					},
					resultGroupTotalSuccessesTest: {
						value: function resultGroupTotalSuccessesTest___n4() {
							Assert.strictEqual(this.resultGroups.results[0].successes, 1);
						}
					},
					resultGroupTotalSkipsTest: {
						value: function resultGroupTotalSkipsTest___n4() {
							Assert.strictEqual(this.resultGroups.results[0].skipped, 3);
						}
					},
					subject: {
						value: undefined,
						writable: true
					},
					parentInjector: {
						value: undefined,
						writable: true
					},
					resultGroups: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'ResultGroupsTest',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.ResultGroupsTest.ResultGroupsTest',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'subject',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'parentInjector',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'resultGroups',
								isStatic: false,
								annotations: []
							}),
							new N4Method({
								name: 'constructor',
								isStatic: false,
								jsFunction: instanceProto['constructor'],
								annotations: []
							}),
							new N4Method({
								name: 'setup',
								isStatic: false,
								jsFunction: instanceProto['setup'],
								annotations: [
									new N4Annotation({
										name: 'Before',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'resultGroupsTotalFailuresTest',
								isStatic: false,
								jsFunction: instanceProto['resultGroupsTotalFailuresTest'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'resultGroupsTotalErrorsTest',
								isStatic: false,
								jsFunction: instanceProto['resultGroupsTotalErrorsTest'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'resultGroupsTotalSuccessesTest',
								isStatic: false,
								jsFunction: instanceProto['resultGroupsTotalSuccessesTest'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'resultGroupsTotalSkipsTest',
								isStatic: false,
								jsFunction: instanceProto['resultGroupsTotalSkipsTest'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'resultGroupTotalFailuresTest',
								isStatic: false,
								jsFunction: instanceProto['resultGroupTotalFailuresTest'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'resultGroupTotalErrorsTest',
								isStatic: false,
								jsFunction: instanceProto['resultGroupTotalErrorsTest'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'resultGroupTotalSuccessesTest',
								isStatic: false,
								jsFunction: instanceProto['resultGroupTotalSuccessesTest'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'resultGroupTotalSkipsTest',
								isStatic: false,
								jsFunction: instanceProto['resultGroupTotalSkipsTest'],
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
			}
		};
	});
})(typeof module !== 'undefined' && module.exports ? require('n4js-node/index').System(require, module) : System);
//# sourceMappingURL=ResultGroupsTest.map
