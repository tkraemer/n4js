(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft.assert/n4/mangel/assert/Assert',
		'eu.numberfour.mangelhaft.assert/n4/mangel/assert/AssertionError',
		'eu.numberfour.stdlib.model.base.api/n4/model/collections/DataList'
	], function($n4Export) {
		var Assert, AssertionError, DataList, ICreature, Beast, Dog, AssertTest;
		ICreature = {};
		Beast = function Beast() {
			ICreature.$fieldInit(this, undefined, {});
		};
		Dog = function Dog() {
			Beast.prototype.constructor.call(this);
		};
		AssertTest = function AssertTest() {};
		$n4Export('AssertTest', AssertTest);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssert) {
					Assert = $_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssert.Assert;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssertionError) {
					AssertionError = $_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssertionError.AssertionError;
				},
				function($_import_eu_u002enumberfour_u002estdlib_u002emodel_u002ebase_u002eapi_n4_u002fmodel_u002fcollections_u002fDataList) {
					DataList = $_import_eu_u002enumberfour_u002estdlib_u002emodel_u002ebase_u002eapi_n4_u002fmodel_u002fcollections_u002fDataList.DataList;
				}
			],
			execute: function() {
				ICreature.$fieldInit = function ICreature_fieldInit(target, spec, mixinExclusion) {};
				ICreature.$methods = {};
				$makeInterface(ICreature, function(instanceProto, staticProto) {
					var metaClass = new N4Interface({
						name: 'ICreature',
						origin: 'eu.numberfour.mangelhaft.assert.test',
						fqn: 'n4.mangel.assert.test.assert.AssertTest.ICreature',
						n4superType: undefined,
						allImplementedInterfaces: [],
						ownedMembers: [],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				$makeClass(Beast, N4Object, [
					ICreature
				], {}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'Beast',
						origin: 'eu.numberfour.mangelhaft.assert.test',
						fqn: 'n4.mangel.assert.test.assert.AssertTest.Beast',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [
							'n4.mangel.assert.test.assert.AssertTest.ICreature'
						],
						ownedMembers: [],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				$makeClass(Dog, Beast, [], {}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'Dog',
						origin: 'eu.numberfour.mangelhaft.assert.test',
						fqn: 'n4.mangel.assert.test.assert.AssertTest.Dog',
						n4superType: Beast.n4type,
						allImplementedInterfaces: [
							'n4.mangel.assert.test.assert.AssertTest.ICreature'
						],
						ownedMembers: [],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				Object.defineProperty(Dog, '$di', {
					value: {
						superType: Beast,
						fieldsInjectedTypes: []
					}
				});
				$makeClass(AssertTest, N4Object, [], {
					assertEqual: {
						value: function assertEqual___n4() {
							Assert.doesNotThrow(function() {
								Assert.equal(1, 1, "1 and 1 are truthy");
							}, AssertionError, "1 and 1 are truthy");
						}
					},
					assertEqualTruthy: {
						value: function assertEqualTruthy___n4() {
							Assert.doesNotThrow(function() {
								Assert.equal(1, "1", "1 and '1' are truthily equal");
							}, null, "1 and '1' are truthy");
						}
					},
					assertEqualFailing: {
						value: function assertEqualFailing___n4() {
							Assert.throws(function() {
								Assert.equal(1, "2", "1 and '2' are not equal even truthily");
							}, AssertionError, "Should throw AssertionError");
						}
					},
					assertNotEqual: {
						value: function assertNotEqual___n4() {
							Assert.doesNotThrow(function() {
								Assert.notEqual(1, 2, "1 and 2 not equal");
							}, AssertionError, "should not throw since 1 and 2 are not equal");
						}
					},
					assertNotEqualTruthy: {
						value: function assertNotEqualTruthy___n4() {
							Assert.doesNotThrow(function() {
								Assert.notEqual(1, "2", "1 and '2' are truthily not equal");
							}, null, "assert should pass");
						}
					},
					assertNotEqualFailing: {
						value: function assertNotEqualFailing___n4() {
							Assert.throws(function() {
								Assert.notEqual(1, "1", "1 and '1' are truthily equal");
							}, AssertionError, "Should throw AssertionError");
						}
					},
					assertStrictEqual: {
						value: function assertStrictEqual___n4() {
							Assert.doesNotThrow(function() {
								Assert.equal(1, 1, "");
							}, null, "1 and 1 are equal");
						}
					},
					assertStrictEqualTruthy: {
						value: function assertStrictEqualTruthy___n4() {
							Assert.throws(function() {
								Assert.strictEqual(1, "1", "");
							}, AssertionError, "1 and '1' are truthy but not equal");
						}
					},
					assertStrictEqualFailing: {
						value: function assertStrictEqualFailing___n4() {
							Assert.throws(function() {
								Assert.strictEqual(1, "2", "1 and '2' are not equal even truthily");
							}, AssertionError, "Should throw AssertionError");
						}
					},
					assertIsNull: {
						value: function assertIsNull___n4() {
							Assert.doesNotThrow(function() {
								Assert.isNull(null, "null is null");
							}, AssertionError, "null is null");
						}
					},
					assertIsNullFailing: {
						value: function assertIsNullFailing___n4() {
							Assert.throws(function() {
								Assert.isNull(0, "0 is not (strictly) null");
							}, AssertionError, "Should throw AssertionError");
						}
					},
					assertIsUndefined: {
						value: function assertIsUndefined___n4() {
							let undef;
							Assert.doesNotThrow(function() {
								Assert.isUndefined(undef, "undefined is undefined");
							}, AssertionError, "isUndefined should not throw AssertionError here");
						}
					},
					assertIsUndefinedFailing: {
						value: function assertIsUndefinedFailing___n4() {
							Assert.throws(function() {
								Assert.isUndefined(null, "null is not (strictly) undefined");
							}, AssertionError, "isUndefined should throw AssertionError");
						}
					},
					assertIsNotNull: {
						value: function assertIsNotNull___n4() {
							Assert.doesNotThrow(function() {
								Assert.isNotNull(0, "0 is not null");
							}, AssertionError, "isNotNull should not throw AssertionError here");
						}
					},
					assertIsNotNullFailing: {
						value: function assertIsNotNullFailing___n4() {
							Assert.throws(function() {
								Assert.isNotNull(null, "null is null");
							}, AssertionError, "Should throw AssertionError");
						}
					},
					assertIsNotUndefined: {
						value: function assertIsNotUndefined___n4() {
							Assert.doesNotThrow(function() {
								Assert.isNotUndefined(null, "null is not (strictly) undefined");
							}, AssertionError, "isNotUndefined should not throw AssertionError here");
						}
					},
					assertIsNotUndefinedFailing: {
						value: function assertIsNotUndefinedFailing___n4() {
							let undef;
							Assert.throws(function() {
								Assert.isNotUndefined(undef, "undefined is undefined");
							}, AssertionError, "isNotUndefined should throw AssertionError");
						}
					},
					assertIsNullOrUndefined: {
						value: function assertIsNullOrUndefined___n4() {
							Assert.doesNotThrow(function() {
								let undef;
								Assert.isNullOrUndefined(null, "null is null");
								Assert.isNullOrUndefined(undef, "undefined is undefined");
							}, AssertionError, "AssertionError should not be thrown from isNullOrUndefined");
						}
					},
					assertIsNullOrUndefinedFailing: {
						value: function assertIsNullOrUndefinedFailing___n4() {
							Assert.throws(function() {
								Assert.isNullOrUndefined(0, "0 is not (strictly) null nor is it undefined");
							}, AssertionError, "Should throw AssertionError");
						}
					},
					assertIsNotNullOrUndefined: {
						value: function assertIsNotNullOrUndefined___n4() {
							Assert.doesNotThrow(function() {
								Assert.isNotNullOrUndefined(0, "0 is not (strictly) null nor is it undefined");
							}, AssertionError, "isNotNullOrUndefined should not throw AssertionError here");
						}
					},
					assertIsNotNullOrUndefinedFailing: {
						value: function assertIsNotNullOrUndefinedFailing___n4() {
							let undef;
							Assert.throws(function() {
								Assert.isNotNullOrUndefined(null, "undefined is undefined");
							}, AssertionError, "isNotNullOrUndefined should throw AssertionError for null");
							Assert.throws(function() {
								Assert.isNotNullOrUndefined(undef, "undefined is undefined");
							}, AssertionError, "isNotNullOrUndefined should throw AssertionError for undefined");
						}
					},
					assertStrictNotEqual: {
						value: function assertStrictNotEqual___n4() {
							Assert.doesNotThrow(function() {
								Assert.notEqual(1, 2, "");
							}, null, "1 and 2 are not equal");
						}
					},
					assertNotStrictEqualTruthyFailing: {
						value: function assertNotStrictEqualTruthyFailing___n4() {
							Assert.doesNotThrow(function() {
								Assert.notStrictEqual(1, "1", "");
							}, AssertionError, "1 and '1' are truthy but not equal");
						}
					},
					assertNotStrictEqualFailing: {
						value: function assertNotStrictEqualFailing___n4() {
							Assert.throws(function() {
								Assert.notStrictEqual(1, 1, "1 and 1 are not not equal even truthily");
							}, AssertionError, "Should throw AssertionError");
						}
					},
					assertFail: {
						value: function assertFail___n4() {
							let er = Assert.throws(function() {
								Assert.fail("A certain error message");
							}, AssertionError, "Plain fail call should throw AssertionError");
							Assert.equal(er.message, "A certain error message", "plain fail call should preserve message");
						}
					},
					assertInstanceOfInherited: {
						value: function assertInstanceOfInherited___n4() {
							Assert.isInstanceOf(new Dog(), Beast, "Dog should be an instanceof Beast");
						}
					},
					assertInstanceOfArray: {
						value: function assertInstanceOfArray___n4() {
							Assert.doesNotThrow((function() {
								Assert.isInstanceOf([], Array, "builtin type check");
							}).bind(this), null, "[] should be an instance of Array");
						}
					},
					assertThrows: {
						value: function assertThrows___n4() {
							try {
								Assert.throws(function() {
									throw new Error("Oops");
								}, Error);
							} catch(e) {
								Assert.fail("Assert.throws should not throw iff callback does throw with correct type");
							}
						}
					},
					assertThrowsWithWrongError: {
						value: function assertThrowsWithWrongError___n4() {
							let error = null;
							try {
								Assert.throws(function() {
									throw "oops";
								}, Error);
							} catch(e) {
								error = e;
							}
							Assert.isTrue(error !== null && error instanceof AssertionError, "throws should throw if wrong error type");
						}
					},
					assertDoesNotThrow: {
						value: function assertDoesNotThrow___n4() {
							let error = null;
							try {
								Assert.doesNotThrow(function() {
									throw "oops";
								}, Error);
							} catch(e) {
								error = e;
							}
							Assert.isTrue(error !== null, "doesNotThrow should throw if callback throws");
						}
					},
					assertThrowsAsync: {
						value: function assertThrowsAsync___n4() {
							return $spawn(function*() {
								var asyncTest = function asyncTest() {
									return $spawn(function*() {
										return new Promise(function(resolve, reject) {
											setTimeout(function() {
												reject(new Error("badman"));
											}, 1);
										});
									}.apply(this, arguments));
								};
								try {
									(yield Assert.throwsAsync(asyncTest, Error, "method should throw with an Error type"));
								} catch(e) {
									Assert.fail("Assert.throws should not throw iff callback does throw with correct type");
								}
							}.apply(this, arguments));
						}
					},
					assertThrowsAsyncWithWrongError: {
						value: function assertThrowsAsyncWithWrongError___n4() {
							return $spawn(function*() {
								let error = null;
								var asyncTest = function asyncTest() {
									return $spawn(function*() {
										return new Promise(function(resolve, reject) {
											setTimeout(function() {
												reject("badman");
											}, 1);
										});
									}.apply(this, arguments));
								};
								try {
									(yield Assert.throwsAsync(asyncTest, Error, "method should throw with an Error type"));
								} catch(e) {
									error = e;
								}
								Assert.isTrue(error !== null && error instanceof AssertionError, "throws should throw if wrong error type");
							}.apply(this, arguments));
						}
					},
					assertDoesNotThrowAsync: {
						value: function assertDoesNotThrowAsync___n4() {
							return $spawn(function*() {
								let error = null;
								try {
									Assert.doesNotThrow(function() {
										throw "oops";
									}, Error);
								} catch(e) {
									error = e;
								}
								Assert.isTrue(error !== null, "doesNotThrow should throw if callback throws");
							}.apply(this, arguments));
						}
					},
					assertWaitForConditionWork: {
						value: function assertWaitForConditionWork___n4() {
							return $spawn(function*() {
								Assert.equal((yield Assert.waitForCondition(function() {
									return 5;
								}, "wfc works")), 5);
							}.apply(this, arguments));
						}
					},
					assertWaitForConditionTimesOut: {
						value: function assertWaitForConditionTimesOut___n4() {
							return $spawn(function*() {
								(yield Assert.throwsAsync((function() {
									return $spawn(function*() {
										try {
											(yield Assert.waitForCondition(function() {}, "wfc times out", 50, 20));
										} catch(exc) {
											Assert.equal((exc).message, "wfc times out");
											throw exc;
										}
									}.apply(this, arguments));
								}).bind(this)));
							}.apply(this, arguments));
						}
					},
					assertWaitForConditionFails: {
						value: function assertWaitForConditionFails___n4() {
							return $spawn(function*() {
								(yield Assert.throwsAsync((function() {
									return $spawn(function*() {
										try {
											(yield Assert.waitForCondition(function() {
												throw new Error("wfc fails");
											}));
										} catch(exc) {
											Assert.equal((exc).message, "wfc fails");
											throw exc;
										}
									}.apply(this, arguments));
								}).bind(this)));
							}.apply(this, arguments));
						}
					},
					testThrows: {
						value: function testThrows___n4() {
							Assert.throws((function() {
								throw "I was thrown";
							}).bind(this));
						}
					},
					testThrowsMessageNullcheck: {
						value: function testThrowsMessageNullcheck___n4() {
							Assert.throws((function() {
								throw "I was thrown";
							}).bind(this), null, "I was NOT thrown");
						}
					},
					testThrowsString: {
						value: function testThrowsString___n4() {
							Assert.throws((function() {
								throw "I was thrown";
							}).bind(this), 'string', "I was thrown");
						}
					},
					testThrowsNumber: {
						value: function testThrowsNumber___n4() {
							Assert.throws((function() {
								throw 0;
							}).bind(this), 'number', "I was thrown");
						}
					},
					testThrowsFunction: {
						value: function testThrowsFunction___n4() {
							let fcn = function() {};
							Assert.throws((function() {
								throw fcn;
							}).bind(this), 'function', "I was thrown");
						}
					},
					testThrowsObject: {
						value: function testThrowsObject___n4() {
							Assert.throws((function() {
								throw {};
							}).bind(this), 'object', "I was thrown");
						}
					},
					testThrowsSymbol: {
						value: function testThrowsSymbol___n4() {
							Assert.throws((function() {
								throw Symbol.iterator;
							}).bind(this), 'symbol', "I was thrown");
						}
					},
					testThrowsBoolean: {
						value: function testThrowsBoolean___n4() {
							Assert.throws((function() {
								throw false;
							}).bind(this), 'boolean', "I was thrown");
						}
					},
					testThrowsUndefined: {
						value: function testThrowsUndefined___n4() {
							let undef;
							Assert.throws((function() {
								throw undef;
							}).bind(this), 'undefined', "I was thrown");
						}
					},
					testNegativePrimitive: {
						value: function testNegativePrimitive___n4() {
							Assert.throws(function() {
								Assert.throws((function() {
									throw false;
								}).bind(this), 'number', "I was thrown");
							}, AssertionError, "Should throw AssertionError because primitive types don't match");
						}
					},
					testSlbug16CircularRef: {
						value: function testSlbug16CircularRef___n4() {
							let o = Object.create(null);
							o.f = o;
							console.log("o1", o);
							Assert.throws((function() {
								Assert.isNullOrUndefined(o);
							}).bind(this), AssertionError, "Should be AssertionError not TypeError");
						}
					},
					testSlbug8NullPrototype: {
						value: function testSlbug8NullPrototype___n4() {
							let o = Object.create(null);
							Assert.throws((function() {
								Assert.isFalse(o);
							}).bind(this), AssertionError, "Should be AssertionError not TypeError");
						}
					},
					error_adds_message___fail: {
						value: function error_adds_message___fail___n4() {
							try {
								Assert.fail("TESTUM");
							} catch(ex) {
								let e = ex;
								Assert.equal(e.message, "TESTUM", "message should be passed through");
							}
						}
					},
					error_adds_message___default_message: {
						value: function error_adds_message___default_message___n4() {
							try {
								Assert.equal(true, false);
							} catch(ex) {
								let e = ex;
								Assert.isTrue(!!e.message.length, "a sensible default message should be provided");
							}
						}
					},
					rethrows_special___basic: {
						value: function rethrows_special___basic___n4() {
							Assert.throws((function() {
								Assert.throws((function() {
									throw new N4ApiNotImplementedError("test");
								}).bind(this));
							}).bind(this), N4ApiNotImplementedError, "Should be AssertionError not TypeError");
						}
					},
					rethrows_special___expected: {
						value: function rethrows_special___expected___n4() {
							Assert.doesNotThrow((function() {
								Assert.throws((function() {
									throw new N4ApiNotImplementedError("test");
								}).bind(this), N4ApiNotImplementedError);
							}).bind(this), null, "Should be AssertionError not TypeError");
						}
					},
					rethrows_special___doesnot_basic: {
						value: function rethrows_special___doesnot_basic___n4() {
							Assert.throws((function() {
								Assert.doesNotThrow((function() {
									throw new N4ApiNotImplementedError("test");
								}).bind(this));
							}).bind(this), N4ApiNotImplementedError, "Should be AssertionError not TypeError");
						}
					},
					rethrows_special___doesnot_expected: {
						value: function rethrows_special___doesnot_expected___n4() {
							Assert.throws((function() {
								Assert.doesNotThrow((function() {
									throw new N4ApiNotImplementedError("test");
								}).bind(this), N4ApiNotImplementedError);
							}).bind(this), AssertionError, "Should be AssertionError not TypeError");
						}
					},
					rethrows_special___basic_async: {
						value: function rethrows_special___basic_async___n4() {
							return $spawn(function*() {
								(yield Assert.throwsAsync(function() {
									return $spawn(function*() {
										(yield Assert.throwsAsync((function() {
											throw new N4ApiNotImplementedError("test");
										}).bind(this)));
									}.apply(this, arguments));
								}, N4ApiNotImplementedError, "Should be AssertionError not TypeError"));
							}.apply(this, arguments));
						}
					},
					rethrows_special___expected_async: {
						value: function rethrows_special___expected_async___n4() {
							return $spawn(function*() {
								(yield Assert.doesNotThrowAsync(function() {
									return $spawn(function*() {
										(yield Assert.throwsAsync((function() {
											throw new N4ApiNotImplementedError("test");
										}).bind(this), N4ApiNotImplementedError));
									}.apply(this, arguments));
								}, null, "Should be AssertionError not TypeError"));
							}.apply(this, arguments));
						}
					},
					rethrows_special___doesnot_basic_async: {
						value: function rethrows_special___doesnot_basic_async___n4() {
							return $spawn(function*() {
								(yield Assert.throwsAsync(function() {
									return $spawn(function*() {
										(yield Assert.doesNotThrowAsync(function() {
											throw new N4ApiNotImplementedError("test");
										}));
									}.apply(this, arguments));
								}, N4ApiNotImplementedError, "Should be AssertionError not TypeError"));
							}.apply(this, arguments));
						}
					},
					rethrows_special___doesnot_expected_async: {
						value: function rethrows_special___doesnot_expected_async___n4() {
							return $spawn(function*() {
								(yield Assert.throwsAsync((function() {
									return $spawn(function*() {
										(yield Assert.doesNotThrowAsync((function() {
											throw new N4ApiNotImplementedError("test");
										}).bind(this), N4ApiNotImplementedError));
									}.apply(this, arguments));
								}).bind(this), AssertionError, "Should be AssertionError not TypeError"));
							}.apply(this, arguments));
						}
					},
					deep_equal_datalist_cycle_SLBUG_40___equal: {
						value: function deep_equal_datalist_cycle_SLBUG_40___equal___n4() {
							Assert.deepEqual(new DataList([
								1,
								2,
								3
							]), new DataList([
								1,
								2,
								3
							]));
						}
					},
					deep_equal_datalist_cycle_SLBUG_40___not_equal: {
						value: function deep_equal_datalist_cycle_SLBUG_40___not_equal___n4() {
							Assert.throws((function() {
								Assert.deepEqual(new DataList([
									1,
									2,
									7
								]), new DataList([
									1,
									2,
									3
								]));
							}).bind(this), AssertionError, "Datalists are not equal here");
						}
					},
					deep_equal_object_cycle_SLBUG_40___equal_cyclic_refs_to_same_fields: {
						value: function deep_equal_object_cycle_SLBUG_40___equal_cyclic_refs_to_same_fields___n4() {
							let objA = {
								a: {},
								b: {},
								c: null
							};
							let objB = {
								a: {},
								b: {},
								c: null
							};
							objA.c = objA.a;
							objB.c = objB.a;
							Assert.deepEqual(objA, objB);
						}
					},
					deep_equal_object_cycle_SLBUG_40___not_equal_cyclic_refs_to_different_fields: {
						value: function deep_equal_object_cycle_SLBUG_40___not_equal_cyclic_refs_to_different_fields___n4() {
							let objA = {
								a: {},
								b: {},
								c: null
							};
							let objB = {
								a: {},
								b: {},
								c: null
							};
							objA.c = objA.a;
							objB.c = objB.b;
							Assert.throws((function() {
								Assert.deepEqual(objA, objB);
							}).bind(this), AssertionError, "objects have refs to different fields");
						}
					},
					deep_equal_object_cycle_SLBUG_40___not_equal_refs_same_refs_different_values: {
						value: function deep_equal_object_cycle_SLBUG_40___not_equal_refs_same_refs_different_values___n4() {
							let objA = {
								a: {
									jim: "crow"
								},
								b: {},
								c: null
							};
							let objB = {
								a: {},
								b: {},
								c: null
							};
							objA.c = objA.a;
							objB.c = objB.a;
							Assert.throws((function() {
								Assert.deepEqual(objA, objB);
							}).bind(this), AssertionError, "objects should not be equal here");
						}
					},
					deep_equal_array_cycle_SLBUG_40___equal_cyclic_refs_to_same_fields: {
						value: function deep_equal_array_cycle_SLBUG_40___equal_cyclic_refs_to_same_fields___n4() {
							let arrA = [];
							let arrB = [];
							arrA.push([]);
							arrA.push(arrA);
							arrB.push([]);
							arrB.push(arrB);
							Assert.deepEqual(arrA, arrB);
						}
					},
					deep_equal_array_cycle_SLBUG_40___equal_cyclic_refs_to_same_fields_val_change: {
						value: function deep_equal_array_cycle_SLBUG_40___equal_cyclic_refs_to_same_fields_val_change___n4() {
							let arrA = [];
							let arrB = [];
							arrA.push([
								[]
							]);
							arrA.push(arrA);
							arrB.push([]);
							arrB.push(arrB);
							Assert.throws((function() {
								Assert.deepEqual(arrA, arrB);
							}).bind(this), AssertionError);
						}
					},
					deep_equal_array_cycle_SLBUG_40___equal_cyclic_refs_to_same_fields_out_of_order: {
						value: function deep_equal_array_cycle_SLBUG_40___equal_cyclic_refs_to_same_fields_out_of_order___n4() {
							let arrA = [];
							let arrB = [];
							arrA.push([]);
							arrA.push(arrA);
							arrB.push(arrB);
							arrB.push([]);
							Assert.throws((function() {
								Assert.deepEqual(arrA, arrB);
							}).bind(this), AssertionError);
						}
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'AssertTest',
						origin: 'eu.numberfour.mangelhaft.assert.test',
						fqn: 'n4.mangel.assert.test.assert.AssertTest.AssertTest',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4Method({
								name: 'assertEqual',
								isStatic: false,
								jsFunction: instanceProto['assertEqual'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertEqualTruthy',
								isStatic: false,
								jsFunction: instanceProto['assertEqualTruthy'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertEqualFailing',
								isStatic: false,
								jsFunction: instanceProto['assertEqualFailing'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertNotEqual',
								isStatic: false,
								jsFunction: instanceProto['assertNotEqual'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertNotEqualTruthy',
								isStatic: false,
								jsFunction: instanceProto['assertNotEqualTruthy'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertNotEqualFailing',
								isStatic: false,
								jsFunction: instanceProto['assertNotEqualFailing'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertStrictEqual',
								isStatic: false,
								jsFunction: instanceProto['assertStrictEqual'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertStrictEqualTruthy',
								isStatic: false,
								jsFunction: instanceProto['assertStrictEqualTruthy'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertStrictEqualFailing',
								isStatic: false,
								jsFunction: instanceProto['assertStrictEqualFailing'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertIsNull',
								isStatic: false,
								jsFunction: instanceProto['assertIsNull'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertIsNullFailing',
								isStatic: false,
								jsFunction: instanceProto['assertIsNullFailing'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertIsUndefined',
								isStatic: false,
								jsFunction: instanceProto['assertIsUndefined'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertIsUndefinedFailing',
								isStatic: false,
								jsFunction: instanceProto['assertIsUndefinedFailing'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertIsNotNull',
								isStatic: false,
								jsFunction: instanceProto['assertIsNotNull'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertIsNotNullFailing',
								isStatic: false,
								jsFunction: instanceProto['assertIsNotNullFailing'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertIsNotUndefined',
								isStatic: false,
								jsFunction: instanceProto['assertIsNotUndefined'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertIsNotUndefinedFailing',
								isStatic: false,
								jsFunction: instanceProto['assertIsNotUndefinedFailing'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertIsNullOrUndefined',
								isStatic: false,
								jsFunction: instanceProto['assertIsNullOrUndefined'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertIsNullOrUndefinedFailing',
								isStatic: false,
								jsFunction: instanceProto['assertIsNullOrUndefinedFailing'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertIsNotNullOrUndefined',
								isStatic: false,
								jsFunction: instanceProto['assertIsNotNullOrUndefined'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertIsNotNullOrUndefinedFailing',
								isStatic: false,
								jsFunction: instanceProto['assertIsNotNullOrUndefinedFailing'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertStrictNotEqual',
								isStatic: false,
								jsFunction: instanceProto['assertStrictNotEqual'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertNotStrictEqualTruthyFailing',
								isStatic: false,
								jsFunction: instanceProto['assertNotStrictEqualTruthyFailing'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertNotStrictEqualFailing',
								isStatic: false,
								jsFunction: instanceProto['assertNotStrictEqualFailing'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertFail',
								isStatic: false,
								jsFunction: instanceProto['assertFail'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertInstanceOfInherited',
								isStatic: false,
								jsFunction: instanceProto['assertInstanceOfInherited'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertInstanceOfArray',
								isStatic: false,
								jsFunction: instanceProto['assertInstanceOfArray'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertThrows',
								isStatic: false,
								jsFunction: instanceProto['assertThrows'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertThrowsWithWrongError',
								isStatic: false,
								jsFunction: instanceProto['assertThrowsWithWrongError'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertDoesNotThrow',
								isStatic: false,
								jsFunction: instanceProto['assertDoesNotThrow'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertThrowsAsync',
								isStatic: false,
								jsFunction: instanceProto['assertThrowsAsync'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertThrowsAsyncWithWrongError',
								isStatic: false,
								jsFunction: instanceProto['assertThrowsAsyncWithWrongError'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertDoesNotThrowAsync',
								isStatic: false,
								jsFunction: instanceProto['assertDoesNotThrowAsync'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertWaitForConditionWork',
								isStatic: false,
								jsFunction: instanceProto['assertWaitForConditionWork'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertWaitForConditionTimesOut',
								isStatic: false,
								jsFunction: instanceProto['assertWaitForConditionTimesOut'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'assertWaitForConditionFails',
								isStatic: false,
								jsFunction: instanceProto['assertWaitForConditionFails'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'testThrows',
								isStatic: false,
								jsFunction: instanceProto['testThrows'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'testThrowsMessageNullcheck',
								isStatic: false,
								jsFunction: instanceProto['testThrowsMessageNullcheck'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'testThrowsString',
								isStatic: false,
								jsFunction: instanceProto['testThrowsString'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'testThrowsNumber',
								isStatic: false,
								jsFunction: instanceProto['testThrowsNumber'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'testThrowsFunction',
								isStatic: false,
								jsFunction: instanceProto['testThrowsFunction'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'testThrowsObject',
								isStatic: false,
								jsFunction: instanceProto['testThrowsObject'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'testThrowsSymbol',
								isStatic: false,
								jsFunction: instanceProto['testThrowsSymbol'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'testThrowsBoolean',
								isStatic: false,
								jsFunction: instanceProto['testThrowsBoolean'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'testThrowsUndefined',
								isStatic: false,
								jsFunction: instanceProto['testThrowsUndefined'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'testNegativePrimitive',
								isStatic: false,
								jsFunction: instanceProto['testNegativePrimitive'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'testSlbug16CircularRef',
								isStatic: false,
								jsFunction: instanceProto['testSlbug16CircularRef'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'testSlbug8NullPrototype',
								isStatic: false,
								jsFunction: instanceProto['testSlbug8NullPrototype'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'error_adds_message___fail',
								isStatic: false,
								jsFunction: instanceProto['error_adds_message___fail'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'error_adds_message___default_message',
								isStatic: false,
								jsFunction: instanceProto['error_adds_message___default_message'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'rethrows_special___basic',
								isStatic: false,
								jsFunction: instanceProto['rethrows_special___basic'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'rethrows_special___expected',
								isStatic: false,
								jsFunction: instanceProto['rethrows_special___expected'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'rethrows_special___doesnot_basic',
								isStatic: false,
								jsFunction: instanceProto['rethrows_special___doesnot_basic'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'rethrows_special___doesnot_expected',
								isStatic: false,
								jsFunction: instanceProto['rethrows_special___doesnot_expected'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'rethrows_special___basic_async',
								isStatic: false,
								jsFunction: instanceProto['rethrows_special___basic_async'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'rethrows_special___expected_async',
								isStatic: false,
								jsFunction: instanceProto['rethrows_special___expected_async'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'rethrows_special___doesnot_basic_async',
								isStatic: false,
								jsFunction: instanceProto['rethrows_special___doesnot_basic_async'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'rethrows_special___doesnot_expected_async',
								isStatic: false,
								jsFunction: instanceProto['rethrows_special___doesnot_expected_async'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'deep_equal_datalist_cycle_SLBUG_40___equal',
								isStatic: false,
								jsFunction: instanceProto['deep_equal_datalist_cycle_SLBUG_40___equal'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'deep_equal_datalist_cycle_SLBUG_40___not_equal',
								isStatic: false,
								jsFunction: instanceProto['deep_equal_datalist_cycle_SLBUG_40___not_equal'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'deep_equal_object_cycle_SLBUG_40___equal_cyclic_refs_to_same_fields',
								isStatic: false,
								jsFunction: instanceProto['deep_equal_object_cycle_SLBUG_40___equal_cyclic_refs_to_same_fields'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'deep_equal_object_cycle_SLBUG_40___not_equal_cyclic_refs_to_different_fields',
								isStatic: false,
								jsFunction: instanceProto['deep_equal_object_cycle_SLBUG_40___not_equal_cyclic_refs_to_different_fields'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'deep_equal_object_cycle_SLBUG_40___not_equal_refs_same_refs_different_values',
								isStatic: false,
								jsFunction: instanceProto['deep_equal_object_cycle_SLBUG_40___not_equal_refs_same_refs_different_values'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'deep_equal_array_cycle_SLBUG_40___equal_cyclic_refs_to_same_fields',
								isStatic: false,
								jsFunction: instanceProto['deep_equal_array_cycle_SLBUG_40___equal_cyclic_refs_to_same_fields'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'deep_equal_array_cycle_SLBUG_40___equal_cyclic_refs_to_same_fields_val_change',
								isStatic: false,
								jsFunction: instanceProto['deep_equal_array_cycle_SLBUG_40___equal_cyclic_refs_to_same_fields_val_change'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'deep_equal_array_cycle_SLBUG_40___equal_cyclic_refs_to_same_fields_out_of_order',
								isStatic: false,
								jsFunction: instanceProto['deep_equal_array_cycle_SLBUG_40___equal_cyclic_refs_to_same_fields_out_of_order'],
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
//# sourceMappingURL=AssertTest.map
