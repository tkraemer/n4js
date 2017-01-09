(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft.test/n4/mangel/test/helper/BaseJoe'
	], function($n4Export) {
		var BaseJoe, Joe;
		Joe = function Joe(stuffProvider) {
			BaseJoe.prototype.constructor.call(this, stuffProvider);
		};
		$n4Export('Joe', Joe);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_u002etest_n4_u002fmangel_u002ftest_u002fhelper_u002fBaseJoe) {
					BaseJoe = $_import_eu_u002enumberfour_u002emangelhaft_u002etest_n4_u002fmangel_u002ftest_u002fhelper_u002fBaseJoe.BaseJoe;
				}
			],
			execute: function() {
				$makeClass(Joe, BaseJoe, [], {}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'Joe',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.Joe.Joe',
						n4superType: BaseJoe.n4type,
						allImplementedInterfaces: [
							'n4.mangel.test.helper.IJoe.IJoe'
						],
						ownedMembers: [],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				Object.defineProperty(Joe, '$di', {
					value: {
						superType: BaseJoe,
						fieldsInjectedTypes: []
					}
				});
			}
		};
	});
})(typeof module !== 'undefined' && module.exports ? require('n4js-node/index').System(require, module) : System);
//# sourceMappingURL=Joe.map
