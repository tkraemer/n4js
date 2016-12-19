(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft.assert/n4/mangel/assert/Assert',
		'eu.numberfour.mangelhaft.assert/n4/mangel/precondition/PreconditionNotMet'
	], function($n4Export) {
		var Assert, PreconditionNotMet, PreconditionNotMetTest;
		PreconditionNotMetTest = function PreconditionNotMetTest() {};
		$n4Export('PreconditionNotMetTest', PreconditionNotMetTest);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssert) {
					Assert = $_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssert.Assert;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fprecondition_u002fPreconditionNotMet) {
					PreconditionNotMet = $_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fprecondition_u002fPreconditionNotMet.PreconditionNotMet;
				}
			],
			execute: function() {
				$makeClass(PreconditionNotMetTest, N4Object, [], {
					nameTest: {
						value: function nameTest___n4() {
							let pnm = new PreconditionNotMet("doesnt work");
							Assert.equal(pnm.name, "PreconditionNotMet", "name should be PreconditionNotMet");
						}
					},
					messageTest: {
						value: function messageTest___n4() {
							let pnm = new PreconditionNotMet("bad man");
							Assert.equal(pnm.message, "bad man", "should set message");
						}
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'PreconditionNotMetTest',
						origin: 'eu.numberfour.mangelhaft.assert.test',
						fqn: 'n4.mangel.assert.test.precondition.PreconditionNotMetTest.PreconditionNotMetTest',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4Method({
								name: 'nameTest',
								isStatic: false,
								jsFunction: instanceProto['nameTest'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'messageTest',
								isStatic: false,
								jsFunction: instanceProto['messageTest'],
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
//# sourceMappingURL=PreconditionNotMetTest.map
