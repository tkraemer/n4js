(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft.assert/n4/mangel/assert/Assert'
	], function($n4Export) {
		var Assert, TestSubject, PrivateConstructors;
		TestSubject = function TestSubject() {};
		PrivateConstructors = function PrivateConstructors() {};
		$n4Export('PrivateConstructors', PrivateConstructors);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssert) {
					Assert = $_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssert.Assert;
				}
			],
			execute: function() {
				$makeClass(TestSubject, N4Object, [], {
					someOtherFunction: {
						value: function someOtherFunction___n4() {}
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'TestSubject',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.PrivateConstructors.TestSubject',
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
								name: 'someOtherFunction',
								isStatic: false,
								jsFunction: instanceProto['someOtherFunction'],
								annotations: []
							})
						],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				$makeClass(PrivateConstructors, N4Object, [], {
					testPrivateConstructorInsideModule: {
						value: function testPrivateConstructorInsideModule___n4() {
							Assert.doesNotThrow(function() {
								new TestSubject();
							}, null, "Should not be able to call private constructor");
						}
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'PrivateConstructors',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.PrivateConstructors.PrivateConstructors',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4Method({
								name: 'testPrivateConstructorInsideModule',
								isStatic: false,
								jsFunction: instanceProto['testPrivateConstructorInsideModule'],
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
//# sourceMappingURL=PrivateConstructors.map
