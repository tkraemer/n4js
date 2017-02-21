(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft.assert/n4/mangel/assert/Assert',
		'eu.numberfour.mangelhaft.assert/n4/mangel/precondition/Precondition',
		'eu.numberfour.mangelhaft.assert/n4/mangel/precondition/PreconditionNotMet'
	], function($n4Export) {
		var Assert, Precondition, PreconditionNotMet, PreconditionTest;
		PreconditionTest = function PreconditionTest() {
			Precondition.equal(1, 1, "never skipped");
		};
		$n4Export('PreconditionTest', PreconditionTest);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssert) {
					Assert = $_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssert.Assert;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fprecondition_u002fPrecondition) {
					Precondition = $_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fprecondition_u002fPrecondition.Precondition;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fprecondition_u002fPreconditionNotMet) {
					PreconditionNotMet = $_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fprecondition_u002fPreconditionNotMet.PreconditionNotMet;
				}
			],
			execute: function() {
				$makeClass(PreconditionTest, N4Object, [], {
					preconditionEqual: {
						value: function preconditionEqual___n4() {
							Assert.doesNotThrow(function() {
								Precondition.equal(1, 1, "1 and 1 are truthy");
							}, PreconditionNotMet, "1 and 1 are truthy");
						}
					},
					preconditionEqualTruthy: {
						value: function preconditionEqualTruthy___n4() {
							Assert.doesNotThrow(function() {
								Precondition.equal(1, "1", "1 and '1' are truthily equal");
							}, null, "1 and '1' are truthy");
						}
					},
					preconditionEqualFailing: {
						value: function preconditionEqualFailing___n4() {
							Assert.throws(function() {
								Precondition.equal(1, "2", "1 and '2' are not equal even truthily");
							}, PreconditionNotMet, "Should throw PreconditionNotMet");
						}
					},
					preconditionNotEqual: {
						value: function preconditionNotEqual___n4() {
							Assert.doesNotThrow(function() {
								Precondition.notEqual(1, 2, "1 and 2 not equal");
							}, PreconditionNotMet, "should not throw since 1 and 2 are not equal");
						}
					},
					preconditionNotEqualTruthy: {
						value: function preconditionNotEqualTruthy___n4() {
							Assert.doesNotThrow(function() {
								Precondition.notEqual(1, "2", "1 and '2' are truthily not equal");
							}, null, "precondition should pass");
						}
					},
					preconditionNotEqualFailing: {
						value: function preconditionNotEqualFailing___n4() {
							Assert.throws(function() {
								Precondition.notEqual(1, "1", "1 and '1' are truthily equal");
							}, PreconditionNotMet, "Should throw PreconditionNotMet");
						}
					},
					preconditionStrictEqual: {
						value: function preconditionStrictEqual___n4() {
							Assert.doesNotThrow(function() {
								Precondition.equal(1, 1, "");
							}, null, "1 and 1 are equal");
						}
					},
					preconditionStrictEqualTruthy: {
						value: function preconditionStrictEqualTruthy___n4() {
							Assert.throws(function() {
								Precondition.strictEqual(1, "1", "");
							}, PreconditionNotMet, "1 and '1' are truthy but not equal");
						}
					},
					preconditionStrictEqualFailing: {
						value: function preconditionStrictEqualFailing___n4() {
							Assert.throws(function() {
								Precondition.strictEqual(1, "2", "1 and '2' are not equal even truthily");
							}, PreconditionNotMet, "Should throw PreconditionNotMet");
						}
					},
					preconditionStrictNotEqual: {
						value: function preconditionStrictNotEqual___n4() {
							Assert.doesNotThrow(function() {
								Precondition.notEqual(1, 2, "");
							}, null, "1 and 2 are not equal");
						}
					},
					preconditionNotStrictEqualTruthyFailing: {
						value: function preconditionNotStrictEqualTruthyFailing___n4() {
							Assert.doesNotThrow(function() {
								Precondition.notStrictEqual(1, "1", "");
							}, PreconditionNotMet, "1 and '1' are truthy but not equal");
						}
					},
					preconditionNotStrictEqualFailing: {
						value: function preconditionNotStrictEqualFailing___n4() {
							Assert.throws(function() {
								Precondition.notStrictEqual(1, 1, "1 and 1 are not not equal even truthily");
							}, PreconditionNotMet, "Should throw PreconditionNotMet");
						}
					},
					skippedTestInNode: {
						value: function skippedTestInNode___n4() {
							Precondition.notEqual(n4.runtimeInfo.platformId, 'nodejs', "check for node.js");
						}
					},
					skippedTestInBrowser: {
						value: function skippedTestInBrowser___n4() {
							Precondition.notEqual(n4.runtimeInfo.platformId, 'web', "check web");
						}
					},
					preconditionThrows: {
						value: function preconditionThrows___n4() {
							try {
								Precondition.throws(function() {
									throw new Error("Oops");
								}, Error);
							} catch(e) {
								Assert.fail("Assert.throws should not throw iff callback does throw with correct type");
							}
						}
					},
					preconditionThrowsWithWrongError: {
						value: function preconditionThrowsWithWrongError___n4() {
							let error = null;
							try {
								Precondition.throws(function() {
									throw "oops";
								}, Error);
							} catch(e) {
								error = e;
							}
							Assert.isTrue(error !== null && error instanceof PreconditionNotMet, "throws should throw if wrong error type");
						}
					},
					preconditionDoesNotThrow: {
						value: function preconditionDoesNotThrow___n4() {
							let error = null;
							try {
								Precondition.doesNotThrow(function() {
									throw "oops";
								}, Error);
							} catch(e) {
								error = e;
							}
							Assert.isTrue(error !== null, "doesNotThrow should throw if callback throws");
						}
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'PreconditionTest',
						origin: 'eu.numberfour.mangelhaft.assert.test',
						fqn: 'n4.mangel.assert.test.precondition.PreconditionTest.PreconditionTest',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4Method({
								name: 'constructor',
								isStatic: false,
								jsFunction: instanceProto['constructor'],
								annotations: []
							}),
							new N4Method({
								name: 'preconditionEqual',
								isStatic: false,
								jsFunction: instanceProto['preconditionEqual'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'preconditionEqualTruthy',
								isStatic: false,
								jsFunction: instanceProto['preconditionEqualTruthy'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'preconditionEqualFailing',
								isStatic: false,
								jsFunction: instanceProto['preconditionEqualFailing'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'preconditionNotEqual',
								isStatic: false,
								jsFunction: instanceProto['preconditionNotEqual'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'preconditionNotEqualTruthy',
								isStatic: false,
								jsFunction: instanceProto['preconditionNotEqualTruthy'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'preconditionNotEqualFailing',
								isStatic: false,
								jsFunction: instanceProto['preconditionNotEqualFailing'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'preconditionStrictEqual',
								isStatic: false,
								jsFunction: instanceProto['preconditionStrictEqual'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'preconditionStrictEqualTruthy',
								isStatic: false,
								jsFunction: instanceProto['preconditionStrictEqualTruthy'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'preconditionStrictEqualFailing',
								isStatic: false,
								jsFunction: instanceProto['preconditionStrictEqualFailing'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'preconditionStrictNotEqual',
								isStatic: false,
								jsFunction: instanceProto['preconditionStrictNotEqual'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'preconditionNotStrictEqualTruthyFailing',
								isStatic: false,
								jsFunction: instanceProto['preconditionNotStrictEqualTruthyFailing'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'preconditionNotStrictEqualFailing',
								isStatic: false,
								jsFunction: instanceProto['preconditionNotStrictEqualFailing'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'skippedTestInNode',
								isStatic: false,
								jsFunction: instanceProto['skippedTestInNode'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'skippedTestInBrowser',
								isStatic: false,
								jsFunction: instanceProto['skippedTestInBrowser'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'preconditionThrows',
								isStatic: false,
								jsFunction: instanceProto['preconditionThrows'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'preconditionThrowsWithWrongError',
								isStatic: false,
								jsFunction: instanceProto['preconditionThrowsWithWrongError'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'preconditionDoesNotThrow',
								isStatic: false,
								jsFunction: instanceProto['preconditionDoesNotThrow'],
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
//# sourceMappingURL=PreconditionTest.map
