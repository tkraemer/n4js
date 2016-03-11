(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft.assert/n4/mangel/assert/Assert',
		'eu.numberfour.mangelhaft.assert/n4/mangel/precondition/PreconditionNotMet'
	], function($n4Export) {
		var Assert, PreconditionNotMet, Precondition;
		Precondition = function Precondition() {
			Assert.prototype.constructor.call(this);
		};
		$n4Export('Precondition', Precondition);
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
				$makeClass(Precondition, Assert, [], {}, {
					fail_: {
						value: function fail____n4(actual, expected, message, operator, stackStartFunction) {
							let error = new PreconditionNotMet(message);
							throw error;
						}
					}
				}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'Precondition',
						origin: 'eu.numberfour.mangelhaft.assert',
						fqn: 'n4.mangel.precondition.Precondition.Precondition',
						n4superType: Assert.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4Method({
								name: 'fail_',
								isStatic: true,
								jsFunction: staticProto['fail_'],
								annotations: []
							})
						],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				Object.defineProperty(Precondition, '$di', {
					value: {
						superType: Assert,
						fieldsInjectedTypes: []
					}
				});
			}
		};
	});
})(typeof module !== 'undefined' && module.exports ? require('n4js-node/index').System(require, module) : System);
//# sourceMappingURL=Precondition.map
