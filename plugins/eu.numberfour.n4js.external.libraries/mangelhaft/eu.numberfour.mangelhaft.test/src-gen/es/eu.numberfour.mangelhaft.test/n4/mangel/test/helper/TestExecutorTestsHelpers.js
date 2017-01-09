(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft/n4/mangel/Test',
		'eu.numberfour.mangelhaft.assert/n4/mangel/assert/Assert',
		'eu.numberfour.mangelhaft.assert/n4/mangel/precondition/Precondition',
		'eu.numberfour.mangelhaft.test/n4/mangel/test/helper/MockTest'
	], function($n4Export) {
		var async, Assert, Precondition, MockTest, BeforeAllErrorMockTest, BeforeErrorMockTest, AfterErrorMockTest, AfterAllErrorMockTest, OneTest, OneTestBeforeAllAfterAll, OneTestOneWithEverything, ChildOfMock, PreconditionTests, MixedBag;
		BeforeAllErrorMockTest = function BeforeAllErrorMockTest() {};
		$n4Export('BeforeAllErrorMockTest', BeforeAllErrorMockTest);
		BeforeErrorMockTest = function BeforeErrorMockTest() {};
		$n4Export('BeforeErrorMockTest', BeforeErrorMockTest);
		AfterErrorMockTest = function AfterErrorMockTest() {};
		$n4Export('AfterErrorMockTest', AfterErrorMockTest);
		AfterAllErrorMockTest = function AfterAllErrorMockTest() {};
		$n4Export('AfterAllErrorMockTest', AfterAllErrorMockTest);
		OneTest = function OneTest() {};
		$n4Export('OneTest', OneTest);
		OneTestBeforeAllAfterAll = function OneTestBeforeAllAfterAll() {};
		$n4Export('OneTestBeforeAllAfterAll', OneTestBeforeAllAfterAll);
		OneTestOneWithEverything = function OneTestOneWithEverything() {};
		$n4Export('OneTestOneWithEverything', OneTestOneWithEverything);
		ChildOfMock = function ChildOfMock() {
			MockTest.prototype.constructor.call(this);
		};
		$n4Export('ChildOfMock', ChildOfMock);
		PreconditionTests = function PreconditionTests() {};
		$n4Export('PreconditionTests', PreconditionTests);
		MixedBag = function MixedBag() {};
		$n4Export('MixedBag', MixedBag);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTest) {
					async = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTest.async;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssert) {
					Assert = $_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssert.Assert;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fprecondition_u002fPrecondition) {
					Precondition = $_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fprecondition_u002fPrecondition.Precondition;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002etest_n4_u002fmangel_u002ftest_u002fhelper_u002fMockTest) {
					MockTest = $_import_eu_u002enumberfour_u002emangelhaft_u002etest_n4_u002fmangel_u002ftest_u002fhelper_u002fMockTest.MockTest;
				}
			],
			execute: function() {
				$makeClass(BeforeAllErrorMockTest, N4Object, [], {
					throwing: {
						value: function throwing___n4() {
							Assert.equal(true, false, "failed assert in beforeAll");
						}
					},
					noOpTest: {
						value: function noOpTest___n4() {}
					},
					noOpTest2: {
						value: function noOpTest2___n4() {}
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'BeforeAllErrorMockTest',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.TestExecutorTestsHelpers.BeforeAllErrorMockTest',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4Method({
								name: 'throwing',
								isStatic: false,
								jsFunction: instanceProto['throwing'],
								annotations: [
									new N4Annotation({
										name: 'BeforeAll',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'noOpTest',
								isStatic: false,
								jsFunction: instanceProto['noOpTest'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'noOpTest2',
								isStatic: false,
								jsFunction: instanceProto['noOpTest2'],
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
				$makeClass(BeforeErrorMockTest, N4Object, [], {
					throwing: {
						value: function throwing___n4() {
							Assert.equal(true, false, "failed assert in before");
						}
					},
					noOpTest: {
						value: function noOpTest___n4() {}
					},
					noOpTest2: {
						value: function noOpTest2___n4() {}
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'BeforeErrorMockTest',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.TestExecutorTestsHelpers.BeforeErrorMockTest',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4Method({
								name: 'throwing',
								isStatic: false,
								jsFunction: instanceProto['throwing'],
								annotations: [
									new N4Annotation({
										name: 'Before',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'noOpTest',
								isStatic: false,
								jsFunction: instanceProto['noOpTest'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'noOpTest2',
								isStatic: false,
								jsFunction: instanceProto['noOpTest2'],
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
				$makeClass(AfterErrorMockTest, N4Object, [], {
					throwing: {
						value: function throwing___n4() {
							Assert.equal(true, false, "failed assert in after");
						}
					},
					noOpTest: {
						value: function noOpTest___n4() {}
					},
					noOpTest2: {
						value: function noOpTest2___n4() {}
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'AfterErrorMockTest',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.TestExecutorTestsHelpers.AfterErrorMockTest',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4Method({
								name: 'throwing',
								isStatic: false,
								jsFunction: instanceProto['throwing'],
								annotations: [
									new N4Annotation({
										name: 'After',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'noOpTest',
								isStatic: false,
								jsFunction: instanceProto['noOpTest'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'noOpTest2',
								isStatic: false,
								jsFunction: instanceProto['noOpTest2'],
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
				$makeClass(AfterAllErrorMockTest, N4Object, [], {
					throwing: {
						value: function throwing___n4() {
							Assert.equal(true, false, "failed assert in afterAll");
						}
					},
					noOpTest: {
						value: function noOpTest___n4() {}
					},
					noOpTest2: {
						value: function noOpTest2___n4() {}
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'AfterAllErrorMockTest',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.TestExecutorTestsHelpers.AfterAllErrorMockTest',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4Method({
								name: 'throwing',
								isStatic: false,
								jsFunction: instanceProto['throwing'],
								annotations: [
									new N4Annotation({
										name: 'AfterAll',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'noOpTest',
								isStatic: false,
								jsFunction: instanceProto['noOpTest'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'noOpTest2',
								isStatic: false,
								jsFunction: instanceProto['noOpTest2'],
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
				$makeClass(OneTest, N4Object, [], {
					smallDelayTest: {
						value: function smallDelayTest___n4() {
							return new Promise(function(resolve, reject) {
								setTimeout.call(this, async(function() {
									resolve("party promise resolved");
								}, reject), 100);
							});
						}
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'OneTest',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.TestExecutorTestsHelpers.OneTest',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4Method({
								name: 'smallDelayTest',
								isStatic: false,
								jsFunction: instanceProto['smallDelayTest'],
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
				$makeClass(OneTestBeforeAllAfterAll, N4Object, [], {
					setup: {
						value: function setup___n4() {
							return new Promise(function(resolve, reject) {
								setTimeout.call(this, async(function() {
									resolve("party promise resolved");
								}, reject), 100);
							});
						}
					},
					smallDelayTest: {
						value: function smallDelayTest___n4() {
							return new Promise(function(resolve, reject) {
								setTimeout.call(this, async(function() {
									resolve("party promise resolved");
								}, reject), 100);
							});
						}
					},
					desetup: {
						value: function desetup___n4() {
							return new Promise(function(resolve, reject) {
								setTimeout.call(this, async(function() {
									resolve("party promise resolved");
								}, reject), 100);
							});
						}
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'OneTestBeforeAllAfterAll',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.TestExecutorTestsHelpers.OneTestBeforeAllAfterAll',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4Method({
								name: 'setup',
								isStatic: false,
								jsFunction: instanceProto['setup'],
								annotations: [
									new N4Annotation({
										name: 'BeforeAll',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'smallDelayTest',
								isStatic: false,
								jsFunction: instanceProto['smallDelayTest'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'desetup',
								isStatic: false,
								jsFunction: instanceProto['desetup'],
								annotations: [
									new N4Annotation({
										name: 'AfterAll',
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
				$makeClass(OneTestOneWithEverything, N4Object, [], {
					testSetup: {
						value: function testSetup___n4() {
							return new Promise(function(resolve, reject) {
								setTimeout.call(this, async(function() {
									resolve("party promise resolved");
								}, reject), 100);
							});
						}
					},
					testDeSetup: {
						value: function testDeSetup___n4() {
							return new Promise(function(resolve, reject) {
								setTimeout.call(this, async(function() {
									resolve("party promise resolved");
								}, reject), 200);
							});
						}
					},
					setup: {
						value: function setup___n4() {
							return new Promise(function(resolve, reject) {
								setTimeout.call(this, async(function() {
									resolve("party promise resolved");
								}, reject), 100);
							});
						}
					},
					smallDelayTest: {
						value: function smallDelayTest___n4() {
							return new Promise(function(resolve, reject) {
								setTimeout.call(this, async(function() {
									resolve("party promise resolved");
								}, reject), 100);
							});
						}
					},
					desetup: {
						value: function desetup___n4() {
							return new Promise(function(resolve, reject) {
								setTimeout.call(this, async(function() {
									resolve("party promise resolved");
								}, reject), 100);
							});
						}
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'OneTestOneWithEverything',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.TestExecutorTestsHelpers.OneTestOneWithEverything',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4Method({
								name: 'testSetup',
								isStatic: false,
								jsFunction: instanceProto['testSetup'],
								annotations: [
									new N4Annotation({
										name: 'Before',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'testDeSetup',
								isStatic: false,
								jsFunction: instanceProto['testDeSetup'],
								annotations: [
									new N4Annotation({
										name: 'After',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'setup',
								isStatic: false,
								jsFunction: instanceProto['setup'],
								annotations: [
									new N4Annotation({
										name: 'BeforeAll',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'smallDelayTest',
								isStatic: false,
								jsFunction: instanceProto['smallDelayTest'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'desetup',
								isStatic: false,
								jsFunction: instanceProto['desetup'],
								annotations: [
									new N4Annotation({
										name: 'AfterAll',
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
				$makeClass(ChildOfMock, MockTest, [], {
					childSmallDelayTest: {
						value: function childSmallDelayTest___n4() {
							return new Promise(function(resolve, reject) {
								setTimeout.call(this, async(function() {
									MockTest.testCount += 1;
									MockTest.called.push("childSmallDelayTest");
									resolve("party promise resolved");
								}, reject), 20);
							});
						}
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'ChildOfMock',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.TestExecutorTestsHelpers.ChildOfMock',
						n4superType: MockTest.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4Method({
								name: 'childSmallDelayTest',
								isStatic: false,
								jsFunction: instanceProto['childSmallDelayTest'],
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
				Object.defineProperty(ChildOfMock, '$di', {
					value: {
						superType: MockTest,
						fieldsInjectedTypes: []
					}
				});
				$makeClass(PreconditionTests, N4Object, [], {
					basicPreconditionFailure: {
						value: function basicPreconditionFailure___n4() {
							Precondition.equal(1, 2, "one and two should be equal for some reason");
						}
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'PreconditionTests',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.TestExecutorTestsHelpers.PreconditionTests',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4Method({
								name: 'basicPreconditionFailure',
								isStatic: false,
								jsFunction: instanceProto['basicPreconditionFailure'],
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
				$makeClass(MixedBag, N4Object, [], {
					success: {
						value: function success___n4() {
							Assert.isTrue(true);
						}
					},
					notASuccess: {
						value: function notASuccess___n4() {
							Assert.isTrue(false);
						}
					},
					reggoError: {
						value: function reggoError___n4() {
							throw new Error("My hamster ate all of my gruel!");
						}
					},
					fixThis: {
						value: function fixThis___n4() {
							Assert.fail("fixme test");
						}
					},
					notImpled: {
						value: function notImpled___n4() {
							throw new N4ApiNotImplementedError("test");
						}
					},
					pnm: {
						value: function pnm___n4() {
							Precondition.fail("precondition test");
						}
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'MixedBag',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.TestExecutorTestsHelpers.MixedBag',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4Method({
								name: 'success',
								isStatic: false,
								jsFunction: instanceProto['success'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'notASuccess',
								isStatic: false,
								jsFunction: instanceProto['notASuccess'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'reggoError',
								isStatic: false,
								jsFunction: instanceProto['reggoError'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'fixThis',
								isStatic: false,
								jsFunction: instanceProto['fixThis'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									}),
									new N4Annotation({
										name: 'Fixme',
										details: [
											'test fixme'
										]
									})
								]
							}),
							new N4Method({
								name: 'notImpled',
								isStatic: false,
								jsFunction: instanceProto['notImpled'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'pnm',
								isStatic: false,
								jsFunction: instanceProto['pnm'],
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
//# sourceMappingURL=TestExecutorTestsHelpers.map
