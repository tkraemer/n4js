(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft.assert/n4/mangel/assert/Assert',
		'eu.numberfour.mangelhaft/n4/mangel/mangeltypes/TestError'
	], function($n4Export) {
		var Assert, TestError, TestErrorTest;
		TestErrorTest = function TestErrorTest() {};
		$n4Export('TestErrorTest', TestErrorTest);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssert) {
					Assert = $_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssert.Assert;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fmangeltypes_u002fTestError) {
					TestError = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fmangeltypes_u002fTestError.TestError;
				}
			],
			execute: function() {
				$makeClass(TestErrorTest, N4Object, [], {
					throw__throw_throws_exception__basic: {
						value: function throw__throw_throws_exception__basic___n4() {
							Assert.throws((function() {
								TestError.throw("party super hard");
							}).bind(this), null, "TestError.throw should throw");
						}
					},
					throw__throw_throws_exception__typeTestError: {
						value: function throw__throw_throws_exception__typeTestError___n4() {
							let err = Assert.throws((function() {
								TestError.throw("party super hard");
							}).bind(this));
							Assert.isInstanceOf(err, TestError, "thrown error should be a TestError");
						}
					},
					throw__throw_throws_exception__message: {
						value: function throw__throw_throws_exception__message___n4() {
							let err = Assert.throws((function() {
								TestError.throw("party super hard");
							}).bind(this));
							Assert.strictEqual((err).message, "party super hard", "error message should be set");
						}
					},
					throw__throw_throws_exception__nomessage: {
						value: function throw__throw_throws_exception__nomessage___n4() {
							let err = Assert.throws((function() {
								TestError.throw();
							}).bind(this));
							Assert.equal((err).message, "", "error message should not be set");
						}
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'TestErrorTest',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.TestErrorTest.TestErrorTest',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4Method({
								name: 'throw__throw_throws_exception__basic',
								isStatic: false,
								jsFunction: instanceProto['throw__throw_throws_exception__basic'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'throw__throw_throws_exception__typeTestError',
								isStatic: false,
								jsFunction: instanceProto['throw__throw_throws_exception__typeTestError'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'throw__throw_throws_exception__message',
								isStatic: false,
								jsFunction: instanceProto['throw__throw_throws_exception__message'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'throw__throw_throws_exception__nomessage',
								isStatic: false,
								jsFunction: instanceProto['throw__throw_throws_exception__nomessage'],
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
//# sourceMappingURL=TestErrorTest.map
