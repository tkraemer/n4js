(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft.assert/n4/mangel/assert/AssertionError',
		'eu.numberfour.mangelhaft.mangeltypes/n4/mangel/mangeltypes/ResultGroup',
		'eu.numberfour.mangelhaft.mangeltypes/n4/mangel/mangeltypes/ResultGroups',
		'eu.numberfour.mangelhaft.mangeltypes/n4/mangel/mangeltypes/TestResult',
		'eu.numberfour.mangelhaft.mangeltypes/n4/mangel/mangeltypes/TestSpy',
		'eu.numberfour.mangelhaft.assert/n4/mangel/precondition/PreconditionNotMet'
	], function($n4Export) {
		var AssertionError, ResultGroup, ResultGroups, TestResult, TestSpy, PreconditionNotMet, TestExecutor;
		TestExecutor = function TestExecutor(spy) {
			this.spy = undefined;
			this.spy = spy;
		};
		$n4Export('TestExecutor', TestExecutor);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssertionError) {
					AssertionError = $_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssertionError.AssertionError;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fResultGroup) {
					ResultGroup = $_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fResultGroup.ResultGroup;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fResultGroups) {
					ResultGroups = $_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fResultGroups.ResultGroups;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fTestResult) {
					TestResult = $_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fTestResult.TestResult;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fTestSpy) {
					TestSpy = $_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fTestSpy.TestSpy;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fprecondition_u002fPreconditionNotMet) {
					PreconditionNotMet = $_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fprecondition_u002fPreconditionNotMet.PreconditionNotMet;
				}
			],
			execute: function() {
				$makeClass(TestExecutor, Object, [], {
					handleFixme: {
						value: function handleFixme___n4(testObject, testRes) {
							if (testObject.fixme) {
								if (testRes.testStatus === 'PASSED') {
									testRes.testStatus = 'FAILED';
									testRes.message = "Test marked with @Fixme annotation but was successful. Issue blocking test has probably been fixed. Try removing annotation.";
									if (testObject.fixmeReason != null) {
										testRes.message += " (reason was '" + testObject.fixmeReason + "')";
									}
									testRes.trace = [
										String(testRes)
									];
								} else if (testRes.testStatus === 'FAILED' || testRes.testStatus === 'ERROR') {
									testRes.testStatus = 'SKIPPED_FIXME';
									testRes.message = testObject.fixmeReason;
									testRes.actual = testRes.expected = testRes.trace = null;
								}
							}
							return testRes;
						}
					},
					callAll: {
						value: function callAll___n4(instrumentedTest, testMethodDescriptors) {
							return $spawn(function*() {
								let results = [];
								var runTest = function runTest(testMethodDescriptor) {
									return $spawn(function*() {
										let timeoutId, testResult;
										function doPromise(resolve, reject) {
											return $spawn(function*() {
												let res;
												timeoutId = setTimeout(function() {
													reject(new Error("Test object" + testMethodDescriptor.name + "timed out after" + testMethodDescriptor.timeout + " milliseconds"));
												}, testMethodDescriptor.timeout);
												try {
													res = (yield testMethodDescriptor.value.call(instrumentedTest.testObject));
												} catch(error) {
													reject(error);
												} finally {
													clearTimeout(timeoutId);
												}
												resolve(res);
												(yield undefined);
												return;
											}.bind(this));
										}
										testResult = (yield new Promise(doPromise));
										(yield testResult);
										return;
									}.bind(this));
								};
								if (testMethodDescriptors) {
									results = (((yield Promise.all(testMethodDescriptors.map(runTest)))));
								}
								(yield results);
								return;
							}.bind(this));
						}
					},
					getAncestorTestMethods: {
						value: function getAncestorTestMethods___n4(iTest, testMethodName) {
							let testMethods = [], nodeTestMethods, node = iTest;
							;
							while(node.parent) {
								node = node.parent;
							}
							do {
								nodeTestMethods = (node)[testMethodName];
								if (nodeTestMethods && nodeTestMethods.length) {
									testMethods = testMethods.concat(nodeTestMethods);
								}
							} while(node = node.child);
							return testMethods;
						}
					},
					runTestAsync: {
						value: function runTestAsync___n4(instrumentedTest) {
							return $spawn(function*() {
								(yield this.runTestsAsync([
									instrumentedTest
								]));
								return;
							}.bind(this));
						}
					},
					runTestsAsync: {
						value: function runTestsAsync___n4(instrumentedTests) {
							return $spawn(function*() {
								let totalSuccesses = 0, totalFailures = 0, rgs, that = this, ranTestGroups;
								;
								var runGroup = function runGroup(iTest) {
									return $spawn(function*() {
										let rg, testResults = [], beforeAlls = that.getAncestorTestMethods(iTest, "beforeAlls"), befores = that.getAncestorTestMethods(iTest, "befores"), afters = that.getAncestorTestMethods(iTest, "afters").reverse(), afterAlls = that.getAncestorTestMethods(iTest, "afterAlls").reverse(), numTests, ii, start, end;
										;
										(yield that.spy.groupStarted.dispatch([
											iTest
										]));
										if (iTest.error) {
											testResults = (yield that.errorTests(iTest, iTest.error));
										} else {
											try {
												(yield that.callAll(iTest, beforeAlls));
												numTests = iTest.tests.length;
												for(ii = 0;ii < numTests;++ii) {
													let testObject = iTest.tests[ii], testRes;
													try {
														(yield that.spy.testStarted.dispatch([
															iTest,
															testObject
														]));
														start = new Date().getTime();
														if (testObject.ignore) {
															testRes = new TestResult({
																testStatus: 'SKIPPED_IGNORE',
																message: testObject.ignoreReason,
																description: testObject.name
															});
														} else {
															(yield that.callAll(iTest, befores));
															(yield that.callAll(iTest, [
																testObject
															]));
															testRes = new TestResult({
																testStatus: 'PASSED',
																description: testObject.name
															});
															(yield that.callAll(iTest, afters));
														}
														end = new Date().getTime();
													} catch(er) {
														let err = er;
														end = new Date().getTime();
														testRes = TestExecutor.generateFailureTestResult(err, testObject.name);
													}
													testRes.elapsedTime = end - start;
													testRes = that.handleFixme(testObject, testRes);
													(yield that.spy.testFinished.dispatch([
														iTest,
														testObject,
														testRes,
														(function() {
															return $spawn(function*() {
																let allTests = iTest.tests;
																;
																iTest.tests = [
																	testObject
																];
																try {
																	(yield that.runTestsAsync([
																		iTest
																	]));
																} finally {
																	iTest.tests = allTests;
																}
																(yield undefined);
																return;
															}.bind(this));
														}).bind(this)
													]));
													testResults.push(testRes);
												}
												(yield that.callAll(iTest, afterAlls));
											} catch(error) {
												let results = (yield that.errorTests(iTest, error));
												testResults = testResults.concat(results);
											}
										}
										let successes = testResults.reduce(function(successes, testResult) {
											if (testResult.testStatus === 'PASSED') {
												successes = successes + 1;
											}
											return successes;
										}, 0), failures = testResults.length - successes;
										;
										rg = new ResultGroup({
											testResults: testResults,
											description: iTest.name,
											failures: failures,
											successes: successes
										});
										(yield that.spy.groupFinished.dispatch([
											iTest,
											rg
										]));
										(yield rg);
										return;
									}.bind(this));
								};
								let result = [];
								for(let test of instrumentedTests) {
									if (test) {
										let testRes = (yield runGroup(test));
										result.push(testRes);
									}
								}
								let ranResultGroups = result, successes = ranResultGroups.reduce(function(successes, resultGroup) {
									successes = successes + resultGroup.successes;
									return successes;
								}, 0), failures = ranResultGroups.reduce(function(failures, resultGroup) {
									failures = failures + resultGroup.failures;
									return failures;
								}, 0), results = new ResultGroups({
									results: ranResultGroups,
									failures: failures,
									successes: successes
								});
								(yield results);
								return;
							}.bind(this));
						}
					},
					errorTests: {
						value: function errorTests___n4(instrumentedTest, error) {
							return $spawn(function*() {
								let len = instrumentedTest.tests.length, testResult, testResults = [], test, ii;
								;
								for(ii = 0;ii < len;++ii) {
									test = instrumentedTest.tests[ii];
									(yield this.spy.testStarted.dispatch([
										instrumentedTest,
										test
									]));
									testResult = TestExecutor.generateFailureTestResult(error, test.name);
									testResult.elapsedTime = 0;
									(yield this.spy.testFinished.dispatch([
										instrumentedTest,
										test,
										testResult
									]));
									testResults.push(testResult);
								}
								(yield testResults);
								return;
							}.bind(this));
						}
					},
					spy: {
						value: undefined,
						writable: true
					}
				}, {
					getStringFromAbiguous: {
						value: function getStringFromAbiguous___n4(thing) {
							let str;
							if (thing === null) {
								str = "null";
							} else if (typeof thing === "object") {
								str = Object.prototype.hasOwnProperty.call(thing, "toString") ? "" + thing : "prototypeless object";
							} else {
								str = "" + thing;
							}
							return str;
						}
					},
					generateFailureTestResult: {
						value: function generateFailureTestResult___n4(ex, description) {
							if (!ex) {
								ex = new Error("Unknown error: " + description);
							} else if (typeof ex === "string") {
								ex = new Error(ex);
							}
							let e = ex instanceof AssertionError ? ex : ex, reason = e.toString(), tr, status, trace;
							;
							if (reason.charAt(0) === "[") {
								reason = e["name"] ? e["name"] + ": " + description : description;
							}
							if (ex instanceof AssertionError) {
								status = 'FAILED';
							} else if (ex instanceof PreconditionNotMet) {
								status = 'SKIPPED_PRECONDITION';
							} else if (ex instanceof N4ApiNotImplementedError) {
								status = 'SKIPPED_NOT_IMPLEMENTED';
							} else {
								status = 'ERROR';
							}
							if (!(e["actual"] == null && e["expected"] === undefined) && e["operator"] == null) {
								reason += "( " + e["actual"] + " not " + e["operator"] + " " + e["expected"] + " )";
							} else if (e["message"]) {
								reason = String(e);
							}
							if (e["stack"]) {
								if (typeof e["stack"] === "string") {
									trace = (e["stack"]).split("\n");
								} else if (Array.isArray(e["stack"])) {
									trace = e["stack"];
								} else {
									trace = [
										(e["stack"]).toString()
									];
								}
								trace = trace.map(function(line) {
									return line.trim();
								});
							}
							tr = new TestResult({
								testStatus: status,
								message: reason,
								trace: trace,
								description: description,
								expected: e.hasOwnProperty("expected") ? this.getStringFromAbiguous(e["expected"]) : undefined,
								actual: e.hasOwnProperty("actual") ? this.getStringFromAbiguous(e["actual"]) : undefined
							});
							return tr;
						}
					}
				}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'TestExecutor',
						origin: 'eu.numberfour.mangelhaft',
						fqn: 'n4.mangel.TestExecutor.TestExecutor',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
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
							new N4Method({
								name: 'constructor',
								isStatic: false,
								jsFunction: instanceProto['constructor'],
								annotations: []
							}),
							new N4Method({
								name: 'getStringFromAbiguous',
								isStatic: true,
								jsFunction: staticProto['getStringFromAbiguous'],
								annotations: []
							}),
							new N4Method({
								name: 'generateFailureTestResult',
								isStatic: true,
								jsFunction: staticProto['generateFailureTestResult'],
								annotations: []
							}),
							new N4Method({
								name: 'handleFixme',
								isStatic: false,
								jsFunction: instanceProto['handleFixme'],
								annotations: []
							}),
							new N4Method({
								name: 'callAll',
								isStatic: false,
								jsFunction: instanceProto['callAll'],
								annotations: []
							}),
							new N4Method({
								name: 'getAncestorTestMethods',
								isStatic: false,
								jsFunction: instanceProto['getAncestorTestMethods'],
								annotations: []
							}),
							new N4Method({
								name: 'runTestAsync',
								isStatic: false,
								jsFunction: instanceProto['runTestAsync'],
								annotations: []
							}),
							new N4Method({
								name: 'runTestsAsync',
								isStatic: false,
								jsFunction: instanceProto['runTestsAsync'],
								annotations: []
							}),
							new N4Method({
								name: 'errorTests',
								isStatic: false,
								jsFunction: instanceProto['errorTests'],
								annotations: []
							})
						],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				Object.defineProperty(TestExecutor, '$di', {
					value: {
						fieldsInjectedTypes: [
							{
								name: 'spy',
								type: TestSpy
							}
						]
					}
				});
			}
		};
	});
})(typeof module !== 'undefined' && module.exports ? require('n4js-node/index').System(require, module) : System);
//# sourceMappingURL=TestExecutor.map
