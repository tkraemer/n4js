(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft.assert/n4/mangel/assert/AssertionError',
		'eu.numberfour.mangelhaft.assert/n4/mangel/precondition/PreconditionNotMet',
		'eu.numberfour.mangelhaft.assert/n4/mangel/assert/Decycle'
	], function($n4Export) {
		var AssertionError, PreconditionNotMet, decycle, getTypeOf, getTypeName, _isInstanceOf, DEFAULT_TIMEOUT, DEFAULT_POLLING_INTERVAL, replacer, truncate, isUndefinedOrNull, isArguments, pSlice, Object_keys, objEquiv, _deepEqual, expectedException, Assert, deepEqual;
		getTypeOf = function getTypeOf(type) {
			return ((type)["n4type"] || null);
		};
		getTypeName = function getTypeName(type) {
			let t = getTypeOf(type);
			return t ? t.fqn : "";
		};
		_isInstanceOf = function _isInstanceOf(target, type) {
			if (typeof target !== "object") {
				return false;
			}
			if (typeof type === "object") {
				return getTypeOf((target).constructor).allImplementedInterfaces.indexOf(getTypeName(type)) >= 0;
			}
			return $instanceof(target, type);
		};
		replacer = function replacer(key, value) {
			if (value === undefined) {
				return '' + value;
			}
			if (typeof value === 'number' && (isNaN(value) || !isFinite(value))) {
				return (value).toString();
			}
			if (typeof value === 'function' || $instanceof(value, RegExp)) {
				return (value).toString();
			}
			return value;
		};
		truncate = function truncate(s, n) {
			if (typeof s == 'string') {
				return s.length < n ? s : s.slice(0, n);
			} else {
				return s;
			}
		};
		isUndefinedOrNull = function isUndefinedOrNull(value) {
			return value === null || value === undefined;
		};
		isArguments = function isArguments(object) {
			return Object.getPrototypeOf({}).toString.call(object) == '[object Arguments]';
		};
		objEquiv = function objEquiv(aAny, bAny, ignorePrototype) {
			let a = aAny, b = bAny, ka, kb, key, i;
			if (isUndefinedOrNull(a) || isUndefinedOrNull(b)) {
				return false;
			}
			if (!ignorePrototype && (a["prototype"] !== b["prototype"])) {
				return false;
			}
			if (isArguments(a)) {
				if (!isArguments(b)) {
					return false;
				}
				a = pSlice.call(a);
				b = pSlice.call(b);
				return _deepEqual(a, b, ignorePrototype);
			}
			try {
				ka = Object_keys(a);
				kb = Object_keys(b);
			} catch(e) {
				return false;
			}
			if (ka.length != kb.length) {
				return false;
			}
			ka.sort();
			kb.sort();
			for(i = ka.length - 1;i >= 0;i--) {
				if (ka[i] != kb[i]) {
					return false;
				}
			}
			for(i = ka.length - 1;i >= 0;i--) {
				key = ka[i];
				if (!_deepEqual(a[key], b[key], ignorePrototype)) {
					return false;
				}
			}
			return true;
		};
		_deepEqual = function _deepEqual(actual, expected, ignorePrototype) {
			if (actual === expected) {
				return true;
			} else if ($instanceof(actual, Date) && $instanceof(expected, Date)) {
				return (actual).getTime() === (expected).getTime();
			} else if ($instanceof(actual, RegExp) && $instanceof(expected, RegExp)) {
				let actualRegExp = actual, expectedRegExp = expected;
				return actualRegExp.source === expectedRegExp.source && actualRegExp.global === expectedRegExp.global && actualRegExp.multiline === expectedRegExp.multiline && actualRegExp.lastIndex === expectedRegExp.lastIndex && actualRegExp.ignoreCase === expectedRegExp.ignoreCase;
			} else if (typeof actual != 'object' && typeof expected != 'object') {
				return actual == expected;
			} else {
				return objEquiv(actual, expected, ignorePrototype);
			}
		};
		expectedException = function expectedException(actual, expected) {
			if (typeof expected === "string") {
				return ((typeof actual) === expected);
			}
			if (Object.getPrototypeOf({}).toString.call(expected) == '[object RegExp]') {
				return (expected).test((actual).toString());
			} else if ($instanceof(actual, expected)) {
				return true;
			} else if (typeof expected === "function" && (expected).call({}, actual) === true) {
				return true;
			}
			return false;
		};
		Assert = function Assert() {};
		$n4Export('Assert', Assert);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssertionError) {
					AssertionError = $_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssertionError.AssertionError;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fprecondition_u002fPreconditionNotMet) {
					PreconditionNotMet = $_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fprecondition_u002fPreconditionNotMet.PreconditionNotMet;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fDecycle) {
					decycle = $_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fDecycle.decycle;
				}
			],
			execute: function() {
				DEFAULT_TIMEOUT = 1000;
				$n4Export('DEFAULT_TIMEOUT', DEFAULT_TIMEOUT);
				DEFAULT_POLLING_INTERVAL = 50;
				$n4Export('DEFAULT_POLLING_INTERVAL', DEFAULT_POLLING_INTERVAL);
				pSlice = [].slice;
				Object_keys = typeof Object.keys === 'function' ? Object.keys : function(obj) {
					let keys = [];
					for(let key in obj) {
						keys.push(key);
					}
					return keys;
				};
				;
				$makeClass(Assert, Object, [], {}, {
					rethrowIfSpecialError: {
						value: function rethrowIfSpecialError___n4(error, expected) {
							let objError, ctor;
							;
							if (error !== null && typeof error === "object") {
								objError = error;
								ctor = objError.constructor;
								if (ctor !== expected && this.RETHROWN_SPECIAL_ERROR_TYPES.indexOf(ctor) !== -1) {
									throw error;
								}
							}
						}
					},
					fail_: {
						value: function fail____n4(actual, expected, message, operator, stackStartFunction) {
							let error = new AssertionError({
								message: message,
								actual: actual,
								expected: expected,
								operator: operator,
								stackStartFunction: stackStartFunction
							});
							throw error;
						}
					},
					fail: {
						value: function fail___n4(message, error) {
							this.fail_(error, null, message, null, this.fail);
						}
					},
					ok: {
						value: function ok___n4(value, message) {
							if (!!!value) {
								this.fail_(value, true, message, '==', this.ok);
							}
						}
					},
					equal: {
						value: function equal___n4(actual, expected, message) {
							if (actual != expected) {
								this.fail_(actual, expected, message, '==', this.equal);
							}
						}
					},
					greaterThan: {
						value: function greaterThan___n4(actual, expected, message) {
							if (!(actual > expected)) {
								this.fail_(actual, expected, message, '>', this.greaterThan);
							}
						}
					},
					lessThan: {
						value: function lessThan___n4(actual, expected, message) {
							if (!(actual < expected)) {
								this.fail_(actual, expected, message, '<', this.lessThan);
							}
						}
					},
					greaterThanOrEqual: {
						value: function greaterThanOrEqual___n4(actual, expected, message) {
							if (!(actual >= expected)) {
								this.fail_(actual, expected, message, '>', this.greaterThanOrEqual);
							}
						}
					},
					lessThanOrEqual: {
						value: function lessThanOrEqual___n4(actual, expected, message) {
							if (!(actual <= expected)) {
								this.fail_(actual, expected, message, '<', this.lessThanOrEqual);
							}
						}
					},
					isNull: {
						value: function isNull___n4(actual, message) {
							if (actual !== null) {
								this.fail_(actual, null, message, "=== null", this.isNull);
							}
						}
					},
					isUndefined: {
						value: function isUndefined___n4(actual, message) {
							let undef;
							if (actual !== undef) {
								this.fail_(actual, null, message, "=== undefined", this.isUndefined);
							}
						}
					},
					isNotNull: {
						value: function isNotNull___n4(actual, message) {
							if (actual === null) {
								this.fail_(actual, null, message, "!== null", this.isNotNull);
							}
						}
					},
					isNotUndefined: {
						value: function isNotUndefined___n4(actual, message) {
							let undef;
							if (actual === undef) {
								this.fail_(actual, null, message, "!== undefined", this.isNotUndefined);
							}
						}
					},
					isNullOrUndefined: {
						value: function isNullOrUndefined___n4(actual, message) {
							let undef;
							if (!(actual === null || actual === undef)) {
								this.fail_(actual, null, message, "=== null || undefined", this.isNullOrUndefined);
							}
						}
					},
					isNotNullOrUndefined: {
						value: function isNotNullOrUndefined___n4(actual, message) {
							let undef;
							if ((actual === null || actual === undefined)) {
								this.fail_(actual, null, message, "!== null || undefined", this.isNotNullOrUndefined);
							}
						}
					},
					notEqual: {
						value: function notEqual___n4(actual, expected, message) {
							if (actual == expected) {
								this.fail_(actual, expected, message, '!=', this.notEqual);
							}
						}
					},
					deepEqual: {
						value: function deepEqual___n4(actual, expected, message, ignorePrototype) {
							actual = decycle(actual);
							expected = decycle(expected);
							if (!_deepEqual(actual, expected, ignorePrototype)) {
								try {
									message += "\n " + JSON.stringify({
										actual: actual,
										expected: expected
									}, null, "    ");
								} catch(err) {
									message += "\n unable to calculate diff";
								}
								this.fail_(actual, expected, message, 'deepEqual', this.deepEqual);
							}
						}
					},
					notDeepEqual: {
						value: function notDeepEqual___n4(actual, expected, message, ignorePrototype) {
							if (_deepEqual(actual, expected, ignorePrototype)) {
								this.fail_(actual, expected, message, 'NotDeepEqual', this.deepEqual);
							}
						}
					},
					strictEqual: {
						value: function strictEqual___n4(actual, expected, message) {
							if (!(actual === expected)) {
								this.fail_(actual, expected, message, '===', this.strictEqual);
							}
						}
					},
					notStrictEqual: {
						value: function notStrictEqual___n4(actual, expected, message) {
							if (!(actual !== expected)) {
								this.fail_(actual, expected, message, '!==', this.notStrictEqual);
							}
						}
					},
					isTrue: {
						value: function isTrue___n4(actual, message) {
							if (!(!!actual)) {
								this.fail_(actual, true, message, "== true", this.isTrue);
							}
						}
					},
					isFalse: {
						value: function isFalse___n4(actual, message) {
							if (!!actual) {
								this.fail_(actual, true, message, "!== true", this.isFalse);
							}
						}
					},
					isInstanceOf: {
						value: function isInstanceOf___n4(actual, expected, message) {
							if (!_isInstanceOf(actual, expected)) {
								let actualName = getTypeName((actual).constructor), expectedName = getTypeName(expected);
								this.fail_(actualName, expectedName, message, "instanceof", this.isInstanceOf);
							}
						}
					},
					isNotInstanceOf: {
						value: function isNotInstanceOf___n4(actual, expected, message) {
							if (_isInstanceOf(actual, expected)) {
								let actualName = getTypeName((actual).constructor), expectedName = getTypeName(expected);
								this.fail_(actualName, expectedName, message, "not instanceof", this.isInstanceOf);
							}
						}
					},
					throws: {
						value: function throws___n4(testFunction, error, message) {
							let actual, threw = false;
							try {
								testFunction();
							} catch(e) {
								threw = true;
								actual = e;
							}
							if (!threw) {
								this.fail_(null, null, message, "throws", this.throws);
							}
							return this.thrownCheck(true, actual, error, message, "throws", this.throws);
						}
					},
					doesNotThrow: {
						value: function doesNotThrow___n4(testFunction, error, message) {
							let actual, threw = false;
							try {
								testFunction();
							} catch(e) {
								threw = true;
								actual = e;
								this.rethrowIfSpecialError(actual, error);
							}
							if (threw) {
								this.fail_(actual, null, message, "does not throw", this.doesNotThrow);
							}
						}
					},
					throwsAsync: {
						value: function throwsAsync___n4(testFunction, expectedErrorType, message) {
							return $spawn(function*() {
								let actual, threw = false;
								;
								try {
									(yield testFunction());
								} catch(e) {
									threw = true;
									actual = e;
								}
								if (!threw) {
									this.fail_(null, null, message, "throws", this.throwsAsync);
								}
								return this.thrownCheck(true, actual, expectedErrorType, message, "throws", this.throwsAsync);
							}.apply(this, arguments));
						}
					},
					doesNotThrowAsync: {
						value: function doesNotThrowAsync___n4(testFunction, error, message) {
							return $spawn(function*() {
								let actual, threw = false;
								try {
									(yield testFunction());
								} catch(e) {
									threw = true;
									actual = e;
									this.rethrowIfSpecialError(actual, error);
								}
								if (threw) {
									this.fail_(actual, null, message, "does not throw", this.doesNotThrowAsync);
								}
							}.apply(this, arguments));
						}
					},
					waitForCondition: {
						value: function waitForCondition___n4(resultFn, message, timeoutMillisecs, intervalMillisecs) {
							timeoutMillisecs = timeoutMillisecs || DEFAULT_TIMEOUT;
							intervalMillisecs = intervalMillisecs || DEFAULT_POLLING_INTERVAL;
							return new Promise(function(resolveFn, rejectFn) {
								timeoutMillisecs += Date.now();
								let handle = setInterval(function() {
									try {
										let res = resultFn();
										if (res) {
											clearInterval(handle);
											resolveFn(res);
										} else {
											let now = Date.now();
											if (now >= timeoutMillisecs) {
												throw new Error(message ? message + ": condition timeout" : "condition timeout");
											}
										}
									} catch(exc) {
										clearInterval(handle);
										rejectFn(exc);
									}
								}, intervalMillisecs);
							});
						}
					},
					thrownCheck: {
						value: function thrownCheck___n4(shouldThrow, actual, expected, message, operator, stackStartFunction) {
							this.rethrowIfSpecialError(actual, expected);
							message = (expected && (expected).name ? ' (' + (expected).name + ').' : '.') + (message ? ' ' + message : '.');
							if (!shouldThrow && expectedException(actual, expected)) {
								this.fail_(actual, expected, 'Got unwanted exception' + message, operator, stackStartFunction);
							}
							if ((shouldThrow && expected != null && !expectedException(actual, expected)) || (!shouldThrow && actual != null)) {
								this.fail_("" + actual, expected, "thrown error of wrong type " + message, operator, stackStartFunction);
							}
							return actual;
						}
					},
					RETHROWN_SPECIAL_ERROR_TYPES: {
						value: undefined,
						writable: true
					}
				}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'Assert',
						origin: 'eu.numberfour.mangelhaft.assert',
						fqn: 'n4.mangel.assert.Assert.Assert',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'RETHROWN_SPECIAL_ERROR_TYPES',
								isStatic: true,
								annotations: []
							}),
							new N4Method({
								name: 'rethrowIfSpecialError',
								isStatic: true,
								jsFunction: staticProto['rethrowIfSpecialError'],
								annotations: []
							}),
							new N4Method({
								name: 'fail_',
								isStatic: true,
								jsFunction: staticProto['fail_'],
								annotations: []
							}),
							new N4Method({
								name: 'fail',
								isStatic: true,
								jsFunction: staticProto['fail'],
								annotations: []
							}),
							new N4Method({
								name: 'ok',
								isStatic: true,
								jsFunction: staticProto['ok'],
								annotations: []
							}),
							new N4Method({
								name: 'equal',
								isStatic: true,
								jsFunction: staticProto['equal'],
								annotations: []
							}),
							new N4Method({
								name: 'greaterThan',
								isStatic: true,
								jsFunction: staticProto['greaterThan'],
								annotations: []
							}),
							new N4Method({
								name: 'lessThan',
								isStatic: true,
								jsFunction: staticProto['lessThan'],
								annotations: []
							}),
							new N4Method({
								name: 'greaterThanOrEqual',
								isStatic: true,
								jsFunction: staticProto['greaterThanOrEqual'],
								annotations: []
							}),
							new N4Method({
								name: 'lessThanOrEqual',
								isStatic: true,
								jsFunction: staticProto['lessThanOrEqual'],
								annotations: []
							}),
							new N4Method({
								name: 'isNull',
								isStatic: true,
								jsFunction: staticProto['isNull'],
								annotations: []
							}),
							new N4Method({
								name: 'isUndefined',
								isStatic: true,
								jsFunction: staticProto['isUndefined'],
								annotations: []
							}),
							new N4Method({
								name: 'isNotNull',
								isStatic: true,
								jsFunction: staticProto['isNotNull'],
								annotations: []
							}),
							new N4Method({
								name: 'isNotUndefined',
								isStatic: true,
								jsFunction: staticProto['isNotUndefined'],
								annotations: []
							}),
							new N4Method({
								name: 'isNullOrUndefined',
								isStatic: true,
								jsFunction: staticProto['isNullOrUndefined'],
								annotations: []
							}),
							new N4Method({
								name: 'isNotNullOrUndefined',
								isStatic: true,
								jsFunction: staticProto['isNotNullOrUndefined'],
								annotations: []
							}),
							new N4Method({
								name: 'notEqual',
								isStatic: true,
								jsFunction: staticProto['notEqual'],
								annotations: []
							}),
							new N4Method({
								name: 'deepEqual',
								isStatic: true,
								jsFunction: staticProto['deepEqual'],
								annotations: []
							}),
							new N4Method({
								name: 'notDeepEqual',
								isStatic: true,
								jsFunction: staticProto['notDeepEqual'],
								annotations: []
							}),
							new N4Method({
								name: 'strictEqual',
								isStatic: true,
								jsFunction: staticProto['strictEqual'],
								annotations: []
							}),
							new N4Method({
								name: 'notStrictEqual',
								isStatic: true,
								jsFunction: staticProto['notStrictEqual'],
								annotations: []
							}),
							new N4Method({
								name: 'isTrue',
								isStatic: true,
								jsFunction: staticProto['isTrue'],
								annotations: []
							}),
							new N4Method({
								name: 'isFalse',
								isStatic: true,
								jsFunction: staticProto['isFalse'],
								annotations: []
							}),
							new N4Method({
								name: 'isInstanceOf',
								isStatic: true,
								jsFunction: staticProto['isInstanceOf'],
								annotations: []
							}),
							new N4Method({
								name: 'isNotInstanceOf',
								isStatic: true,
								jsFunction: staticProto['isNotInstanceOf'],
								annotations: []
							}),
							new N4Method({
								name: 'throws',
								isStatic: true,
								jsFunction: staticProto['throws'],
								annotations: []
							}),
							new N4Method({
								name: 'doesNotThrow',
								isStatic: true,
								jsFunction: staticProto['doesNotThrow'],
								annotations: []
							}),
							new N4Method({
								name: 'throwsAsync',
								isStatic: true,
								jsFunction: staticProto['throwsAsync'],
								annotations: []
							}),
							new N4Method({
								name: 'doesNotThrowAsync',
								isStatic: true,
								jsFunction: staticProto['doesNotThrowAsync'],
								annotations: []
							}),
							new N4Method({
								name: 'waitForCondition',
								isStatic: true,
								jsFunction: staticProto['waitForCondition'],
								annotations: []
							}),
							new N4Method({
								name: 'thrownCheck',
								isStatic: true,
								jsFunction: staticProto['thrownCheck'],
								annotations: []
							})
						],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				Assert.RETHROWN_SPECIAL_ERROR_TYPES = [
					N4ApiNotImplementedError,
					PreconditionNotMet
				];
				deepEqual = _deepEqual;
				$n4Export('deepEqual', deepEqual);
			}
		};
	});
})(typeof module !== 'undefined' && module.exports ? require('n4js-node/index').System(require, module) : System);
//# sourceMappingURL=Assert.map
