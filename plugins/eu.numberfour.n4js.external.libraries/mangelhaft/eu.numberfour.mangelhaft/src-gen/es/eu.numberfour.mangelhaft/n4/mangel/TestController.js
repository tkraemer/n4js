(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft/n4/mangel/InstrumentedTest',
		'eu.numberfour.mangelhaft/n4/mangel/TestExecutor',
		'eu.numberfour.mangelhaft.mangeltypes/n4/mangel/mangeltypes/ResultGroup',
		'eu.numberfour.mangelhaft.mangeltypes/n4/mangel/mangeltypes/ResultGroups',
		'eu.numberfour.mangelhaft.mangeltypes/n4/mangel/mangeltypes/TestFunctionType',
		'eu.numberfour.mangelhaft.mangeltypes/n4/mangel/mangeltypes/TestMethodDescriptor',
		'eu.numberfour.mangelhaft.mangeltypes/n4/mangel/mangeltypes/TestSpy',
		'eu.numberfour.mangelhaft.assert/n4/mangel/precondition/PreconditionNotMet',
		'n4js.lang/n4js/lang/N4Injector'
	], function($n4Export) {
		var InstrumentedTest, TestExecutor, ResultGroup, ResultGroups, TestFunctionType, TestMethodDescriptor, TestSpy, PreconditionNotMet, N4Injector, TestController;
		TestController = function TestController() {
			this.spy = undefined;
			this.executor = undefined;
			this.injector = undefined;
			this.reportersVal = [];
		};
		$n4Export('TestController', TestController);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fInstrumentedTest) {
					InstrumentedTest = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fInstrumentedTest.InstrumentedTest;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTestExecutor) {
					TestExecutor = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTestExecutor.TestExecutor;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fResultGroup) {
					ResultGroup = $_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fResultGroup.ResultGroup;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fResultGroups) {
					ResultGroups = $_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fResultGroups.ResultGroups;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fTestFunctionType) {
					TestFunctionType = $_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fTestFunctionType.TestFunctionType;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fTestMethodDescriptor) {
					TestMethodDescriptor = $_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fTestMethodDescriptor.TestMethodDescriptor;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fTestSpy) {
					TestSpy = $_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fTestSpy.TestSpy;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fprecondition_u002fPreconditionNotMet) {
					PreconditionNotMet = $_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fprecondition_u002fPreconditionNotMet.PreconditionNotMet;
				},
				function($_import_n4js_u002elang_n4js_u002flang_u002fN4Injector) {
					N4Injector = $_import_n4js_u002elang_n4js_u002flang_u002fN4Injector.N4Injector;
				}
			],
			execute: function() {
				$makeClass(TestController, Object, [], {
					errorGroup: {
						value: function errorGroup___n4(info, loadPath, testObject, originalError) {
							return $spawn(function*() {
								let emptyTest, error = originalError ? originalError : new Error("could not load test " + loadPath), that = this, testResult, testResults = [], unknownTest = new TestMethodDescriptor({
									name: "",
									type: TestFunctionType.TEST,
									value: function() {}
								});
								;
								if (!testObject) {
									testObject = new InstrumentedTest();
									info.module = info.module || "";
									info.fqn = info.fqn || info.module.replace(/\//g, ".") + ".*";
									testObject.load(N4Object, info).setTestObject(new N4Object());
									testObject.tests = info.testMethods ? info.testMethods.map(function(methName) {
										return new TestMethodDescriptor({
											name: methName,
											type: TestFunctionType.TEST,
											value: function() {}
										});
									}) : [
										unknownTest
									];
								}
								(yield this.spy.groupStarted.dispatch([
									testObject
								]));
								for(let test of testObject.tests) {
									(yield that.spy.testStarted.dispatch([
										testObject,
										test
									]));
									testResult = TestExecutor.generateFailureTestResult(error, "could not load test " + loadPath);
									testResults.push(testResult);
									(yield that.spy.testFinished.dispatch([
										testObject,
										test,
										testResult
									]));
								}
								(yield this.spy.groupFinished.dispatch([
									testObject,
									new ResultGroup(testResults, info.fqn)
								]));
								return true;
							}.apply(this, arguments));
						}
					},
					instrument: {
						value: function instrument___n4(info) {
							return $spawn(function*() {
								let parts, ctorName, groupModule, groupCTORs, groupCTOR, instrumentedTestObjects = [], instrumentedTestObject, moduleName;
								;
								parts = info.fqn.split("\.");
								ctorName = parts.pop();
								moduleName = parts.join("/");
								try {
									groupModule = System.throwPendingError((yield System.import(info.origin + "/" + moduleName)));
								} catch(ex) {
									(yield this.errorGroup(info, info.origin + "/" + moduleName, null, ex));
									return null;
								}
								groupCTORs = [
									groupModule[ctorName]
								];
								if (groupCTORs) {
									instrumentedTestObjects = [];
									for(groupCTOR of groupCTORs) {
										instrumentedTestObject = new InstrumentedTest();
										if (!groupCTOR) {
											(yield this.errorGroup(info, info.origin + "/" + moduleName, null, new Error("Empty object loaded (is the test class exported?)")));
											continue;
										} else {
											instrumentedTestObject.load(groupCTOR, info);
											if (info.testMethods) {
												instrumentedTestObject.filterTests(info.testMethods);
											}
											try {
												let testClass = groupCTOR, diClass = testClass;
												let testInjector;
												let testType = testClass.n4type;
												for(;testType;testType = testType.n4superType, diClass = (diClass)["__proto__"]) {
													if (testType.allAnnotations("GenerateInjector").length) {
														if (testType.allAnnotations("WithParentInjector").length) {
															if (!this.injector.canBeParentOf(diClass)) {
																throw new PreconditionNotMet("Test called with incompatible parent injector");
															}
															testInjector = N4Injector.of.call(N4Injector, diClass, this.injector);
														} else {
															testInjector = N4Injector.of.call(N4Injector, diClass);
														}
														break;
													}
												}
												if (!testType) {
													testInjector = this.injector;
												}
												instrumentedTestObject.setTestObject(testInjector.create(testClass));
											} catch(ex2) {
												instrumentedTestObject.setTestObject(new N4Object()).setError(ex2);
											}
										}
										instrumentedTestObjects.push(instrumentedTestObject);
									}
								} else {
									(yield this.errorGroup(info, info.origin + "/" + parts.join("/")));
									return null;
								}
								let arr = ((yield Promise.all(instrumentedTestObjects))).filter((function(item) {
									return item !== null;
								}).bind(this));
								return (arr);
							}.apply(this, arguments));
						}
					},
					runGroups: {
						value: function runGroups___n4(testInfoObject, numTests) {
							return $spawn(function*() {
								if (!testInfoObject) {
									throw new Error("TestController::runGroups called with a null testInfoObject");
								}
								let executor = this.executor, that = this, reses = [], res, testInfos = testInfoObject.testDescriptors, batchedTestInfos = [], ii = 0, groupCTORs, modules, testInfosBatch, instrumentedTestsBatch2d, instrumentedTestsBatch, fixme;
								;
								if (numTests === undefined) {
									numTests = testInfoObject.testDescriptors.reduce(function(acc, info) {
										return acc + info.testMethods.length;
									}, 0);
								}
								testInfoObject.testDescriptors = testInfoObject.testDescriptors.sort((function(x, y) {
									let xVal = x.fqn ? x.fqn : x.module, yVal = y.fqn ? y.fqn : y.module;
									return xVal.localeCompare(yVal);
								}).bind(this));
								try {
									(yield this.spy.testingStarted.dispatch([
										testInfos.length,
										testInfoObject.sessionId,
										numTests
									]));
								} catch(ex) {
									console.log("testingStarted.dispatch is bad", ex);
								}
								for(ii = 0;ii < testInfos.length;ii += TestController.MAX_GROUPS_PER_TEST_BATCH) {
									batchedTestInfos.push(testInfos.slice(ii, ii + TestController.MAX_GROUPS_PER_TEST_BATCH));
								}
								for(ii = 0, instrumentedTestsBatch = [];ii < batchedTestInfos.length;++ii, instrumentedTestsBatch = []) {
									testInfosBatch = batchedTestInfos[ii];
									try {
										fixme = (yield ((yield Promise.all(testInfosBatch.map(this.instrument.bind(this)).filter(function(test) {
											return test !== null;
										})))));
										instrumentedTestsBatch2d = fixme;
										instrumentedTestsBatch = (Array.prototype.concat.apply([], instrumentedTestsBatch2d));
										res = (yield executor.runTestsAsync(instrumentedTestsBatch));
										reses.push(res);
									} catch(er) {
										console.error(er);
										throw er;
									}
								}
								;
								res = ResultGroups.concatArray(reses);
								(yield this.spy.testingFinished.dispatch([
									res
								]));
								return res;
							}.apply(this, arguments));
						}
					},
					reporters: {
						set: function setReporters___n4(reporters) {
							reporters.forEach(function(reporter) {
								let dummy = reporter.register();
							});
							this.reportersVal = reporters;
						}
					},
					spy: {
						value: undefined,
						writable: true
					},
					executor: {
						value: undefined,
						writable: true
					},
					injector: {
						value: undefined,
						writable: true
					},
					reportersVal: {
						value: undefined,
						writable: true
					}
				}, {
					MAX_GROUPS_PER_TEST_BATCH: {
						value: undefined,
						writable: true
					}
				}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'TestController',
						origin: 'eu.numberfour.mangelhaft',
						fqn: 'n4.mangel.TestController.TestController',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'MAX_GROUPS_PER_TEST_BATCH',
								isStatic: true,
								annotations: []
							}),
							new N4DataField({
								name: 'spy',
								isStatic: false,
								annotations: [
									new N4Annotation({
										name: 'Inject',
										details: []
									})
								]
							}),
							new N4DataField({
								name: 'executor',
								isStatic: false,
								annotations: [
									new N4Annotation({
										name: 'Inject',
										details: []
									})
								]
							}),
							new N4DataField({
								name: 'injector',
								isStatic: false,
								annotations: [
									new N4Annotation({
										name: 'Inject',
										details: []
									})
								]
							}),
							new N4DataField({
								name: 'reportersVal',
								isStatic: false,
								annotations: []
							}),
							new N4Accessor({
								name: 'reporters',
								getter: false,
								isStatic: false,
								annotations: []
							}),
							new N4Method({
								name: 'errorGroup',
								isStatic: false,
								jsFunction: instanceProto['errorGroup'],
								annotations: []
							}),
							new N4Method({
								name: 'instrument',
								isStatic: false,
								jsFunction: instanceProto['instrument'],
								annotations: []
							}),
							new N4Method({
								name: 'runGroups',
								isStatic: false,
								jsFunction: instanceProto['runGroups'],
								annotations: []
							})
						],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				TestController.MAX_GROUPS_PER_TEST_BATCH = 10;
				Object.defineProperty(TestController, '$di', {
					value: {
						fieldsInjectedTypes: [
							{
								name: 'spy',
								type: TestSpy
							},
							{
								name: 'executor',
								type: TestExecutor
							},
							{
								name: 'injector',
								type: N4Injector
							}
						]
					}
				});
			}
		};
	});
})(typeof module !== 'undefined' && module.exports ? require('n4js-node/index').System(require, module) : System);
//# sourceMappingURL=TestController.map
