(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft/n4/mangel/InstrumentedTest',
		'eu.numberfour.mangelhaft/n4/mangel/TestExecutor',
		'eu.numberfour.mangelhaft.assert/n4/mangel/assert/Assert',
		'eu.numberfour.mangelhaft/n4/mangel/mangeltypes/TestSpy',
		'eu.numberfour.mangelhaft.test/n4/mangel/test/helper/MockTest',
		'eu.numberfour.mangelhaft.test/n4/mangel/test/helper/TestExecutorTestsHelpers',
		'n4js.lang/n4js/lang/N4Injector'
	], function($n4Export) {
		var InstrumentedTest, TestExecutor, Assert, TestSpy, MockTest, BeforeAllErrorMockTest, ChildOfMock, PreconditionTests, N4Injector, SubTestSpy, TestBinder, TestSubject, TestExecutorTests;
		SubTestSpy = function SubTestSpy() {
			TestSpy.prototype.constructor.call(this);
		};
		TestBinder = function TestBinder() {};
		TestSubject = function TestSubject() {
			this.executor = undefined;
		};
		TestExecutorTests = function TestExecutorTests(parentInjector) {
			this.subject = undefined;
			this.parentInjector = undefined;
			this.parentInjector = parentInjector;
		};
		$n4Export('TestExecutorTests', TestExecutorTests);
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
				function($_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fmangeltypes_u002fTestSpy) {
					TestSpy = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fmangeltypes_u002fTestSpy.TestSpy;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002etest_n4_u002fmangel_u002ftest_u002fhelper_u002fMockTest) {
					MockTest = $_import_eu_u002enumberfour_u002emangelhaft_u002etest_n4_u002fmangel_u002ftest_u002fhelper_u002fMockTest.MockTest;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002etest_n4_u002fmangel_u002ftest_u002fhelper_u002fTestExecutorTestsHelpers) {
					BeforeAllErrorMockTest = $_import_eu_u002enumberfour_u002emangelhaft_u002etest_n4_u002fmangel_u002ftest_u002fhelper_u002fTestExecutorTestsHelpers.BeforeAllErrorMockTest;
					ChildOfMock = $_import_eu_u002enumberfour_u002emangelhaft_u002etest_n4_u002fmangel_u002ftest_u002fhelper_u002fTestExecutorTestsHelpers.ChildOfMock;
					PreconditionTests = $_import_eu_u002enumberfour_u002emangelhaft_u002etest_n4_u002fmangel_u002ftest_u002fhelper_u002fTestExecutorTestsHelpers.PreconditionTests;
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
						fqn: 'n4.mangel.test.TestExecutorTests.SubTestSpy',
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
						fqn: 'n4.mangel.test.TestExecutorTests.TestBinder',
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
						fqn: 'n4.mangel.test.TestExecutorTests.TestSubject',
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
				$makeClass(TestExecutorTests, N4Object, [], {
					setupMock: {
						value: function setupMock___n4() {
							let injector = N4Injector.of(TestSubject, this.parentInjector, new TestBinder());
							this.subject = injector.create(TestSubject);
						}
					},
					resetMock: {
						value: function resetMock___n4() {
							MockTest.reset();
						}
					},
					testBeforeAll: {
						value: function testBeforeAll___n4() {
							return $spawn(function *() {
								(yield this.subject.executor.runTestAsync(new InstrumentedTest().load(MockTest).setTestObject(new MockTest())));
								Assert.equal(MockTest.beforeAllCount, 2, "two beforeAlls each called once");
							}.apply(this, arguments));
						}
					},
					testBefore: {
						value: function testBefore___n4() {
							return $spawn(function *() {
								(yield this.subject.executor.runTestAsync(new InstrumentedTest().load(MockTest).setTestObject(new MockTest())));
								Assert.equal(MockTest.beforeCount, 4, "2 befores each called before each test");
							}.apply(this, arguments));
						}
					},
					testTest: {
						value: function testTest___n4() {
							return $spawn(function *() {
								(yield this.subject.executor.runTestAsync(new InstrumentedTest().load(MockTest).setTestObject(new MockTest())));
								Assert.equal(MockTest.testCount, 2, "2 tests each called once");
							}.apply(this, arguments));
						}
					},
					testAfter: {
						value: function testAfter___n4() {
							return $spawn(function *() {
								(yield this.subject.executor.runTestAsync(new InstrumentedTest().load(MockTest).setTestObject(new MockTest())));
								Assert.equal(MockTest.beforeCount, 4, "2 afters each called after each test");
							}.apply(this, arguments));
						}
					},
					testAfterAll: {
						value: function testAfterAll___n4() {
							return $spawn(function *() {
								(yield this.subject.executor.runTestAsync(new InstrumentedTest().load(MockTest).setTestObject(new MockTest())));
								Assert.equal(MockTest.beforeAllCount, 2, "two afterAlls each called once");
							}.apply(this, arguments));
						}
					},
					testExecutionOrder: {
						value: function testExecutionOrder___n4() {
							return $spawn(function *() {
								let methods = [
									"beforeAll1",
									"beforeAll2",
									"before1",
									"before2",
									"test1",
									"after1",
									"after2",
									"before1",
									"before2",
									"test2",
									"after1",
									"after2",
									"afterAll1",
									"afterAll2"
								], positions = [
									[
										0,
										1
									],
									[
										0,
										1
									],
									[
										2,
										3
									],
									[
										2,
										3
									],
									[
										4,
										9
									],
									[
										5,
										6
									],
									[
										5,
										6
									],
									[
										7,
										8
									],
									[
										7,
										8
									],
									[
										4,
										9
									],
									[
										10,
										11
									],
									[
										10,
										11
									],
									[
										12,
										13
									],
									[
										12,
										13
									]
								], called = MockTest.called;
								;
								(yield this.subject.executor.runTestAsync(new InstrumentedTest().load(MockTest).setTestObject(new MockTest())));
								Assert.strictEqual(called.length, methods.length, "Number of methods called");
								Assert.notEqual(called.indexOf("test1"), -1, "test1 called");
								Assert.notEqual(called.indexOf("test1"), -1, "test2 called");
								methods.forEach(function(method, index) {
									let actualIndex, found = false, actualMethods = [];
									;
									for(actualIndex of positions[index]) {
										actualMethods.push(called[actualIndex]);
										if (called[actualIndex] === method) {
											found = true;
											break;
										}
									}
									Assert.isTrue(found, [
										"method ",
										method,
										"should be one of the valid positions (",
										positions[index].toString(),
										")",
										"instead those positions contained",
										actualMethods.toString()
									].join(" "));
								});
							}.apply(this, arguments));
						}
					},
					testExecutionOrderSubclass: {
						value: function testExecutionOrderSubclass___n4() {
							return $spawn(function *() {
								MockTest.reset();
								let tests = [], val, methods = [
									"beforeAll1",
									"beforeAll2",
									"before1",
									"before2",
									"childSmallDelayTest",
									"after1",
									"after2",
									"before1",
									"before2",
									"test1",
									"after1",
									"after2",
									"before1",
									"before2",
									"test2",
									"after1",
									"after2",
									"afterAll1",
									"afterAll2"
								], positions = [
									[
										0,
										1
									],
									[
										0,
										1
									],
									[
										2,
										3
									],
									[
										2,
										3
									],
									[
										4,
										9,
										14
									],
									[
										5,
										6
									],
									[
										5,
										6
									],
									[
										2,
										3
									],
									[
										2,
										3
									],
									[
										4,
										9,
										14
									],
									[
										5,
										6
									],
									[
										5,
										6
									],
									[
										7,
										8
									],
									[
										7,
										8
									],
									[
										4,
										9,
										14
									],
									[
										15,
										16
									],
									[
										15,
										16
									],
									[
										17,
										18
									],
									[
										17,
										18
									]
								], called;
								;
								tests.push(new InstrumentedTest().load(ChildOfMock).setTestObject(new ChildOfMock()));
								val = (yield this.subject.executor.runTestsAsync(tests));
								called = MockTest.called;
								Assert.strictEqual(positions.length, methods.length, "Sanity check. positions and methods parallel arrays same size");
								Assert.strictEqual(called.length, methods.length, "Number of methods called \n" + called.join("\n"));
								Assert.notEqual(called.indexOf("test1"), -1, "test1 called");
								Assert.notEqual(called.indexOf("test1"), -1, "test2 called");
								methods.forEach(function(method, index) {
									let actualIndex, found = false, actualMethods = [];
									;
									for(actualIndex of positions[index]) {
										actualMethods.push(called[actualIndex]);
										if (called[actualIndex] === method) {
											found = true;
											break;
										}
									}
									Assert.isTrue(found, [
										"method ",
										method,
										"should be one of the valid positions (",
										positions[index].toString(),
										")",
										"instead those positions contained",
										actualMethods.toString()
									].join(" "));
								});
							}.apply(this, arguments));
						}
					},
					testBeforeAllErrors: {
						value: function testBeforeAllErrors___n4() {
							return $spawn(function *() {
								let tests = [], val;
								;
								tests.push(new InstrumentedTest().load(BeforeAllErrorMockTest).setTestObject(new BeforeAllErrorMockTest()));
								val = (yield this.subject.executor.runTestsAsync(tests));
								Assert.equal(val.failures, 2, "all tests should fail");
								return true;
							}.apply(this, arguments));
						}
					},
					preconditionBasicFail: {
						value: function preconditionBasicFail___n4() {
							return $spawn(function *() {
								let tests = [], val;
								;
								tests.push(new InstrumentedTest().load(PreconditionTests).setTestObject(new PreconditionTests()));
								val = (yield this.subject.executor.runTestsAsync(tests));
								Assert.equal(val.results[0].testResults[0].testStatus, 'SKIPPED_PRECONDITION');
							}.apply(this, arguments));
						}
					},
					subject: {
						value: undefined,
						writable: true
					},
					parentInjector: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'TestExecutorTests',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.TestExecutorTests.TestExecutorTests',
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
							new N4Method({
								name: 'constructor',
								isStatic: false,
								jsFunction: instanceProto['constructor'],
								annotations: []
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
								name: 'resetMock',
								isStatic: false,
								jsFunction: instanceProto['resetMock'],
								annotations: [
									new N4Annotation({
										name: 'After',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'testBeforeAll',
								isStatic: false,
								jsFunction: instanceProto['testBeforeAll'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'testBefore',
								isStatic: false,
								jsFunction: instanceProto['testBefore'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'testTest',
								isStatic: false,
								jsFunction: instanceProto['testTest'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'testAfter',
								isStatic: false,
								jsFunction: instanceProto['testAfter'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'testAfterAll',
								isStatic: false,
								jsFunction: instanceProto['testAfterAll'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'testExecutionOrder',
								isStatic: false,
								jsFunction: instanceProto['testExecutionOrder'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'testExecutionOrderSubclass',
								isStatic: false,
								jsFunction: instanceProto['testExecutionOrderSubclass'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'testBeforeAllErrors',
								isStatic: false,
								jsFunction: instanceProto['testBeforeAllErrors'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'preconditionBasicFail',
								isStatic: false,
								jsFunction: instanceProto['preconditionBasicFail'],
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
//# sourceMappingURL=TestExecutorTests.map
