(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft.test/n4/mangel/test/notimpl/ForMissingImplTest2'
	], function($n4Export) {
		var ForMissingImplTest2, ForMissingImplTest;
		ForMissingImplTest = function ForMissingImplTest() {
			ForMissingImplTest2.prototype.constructor.call(this);
		};
		$n4Export('ForMissingImplTest', ForMissingImplTest);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_u002etest_n4_u002fmangel_u002ftest_u002fnotimpl_u002fForMissingImplTest2) {
					ForMissingImplTest2 = $_import_eu_u002enumberfour_u002emangelhaft_u002etest_n4_u002fmangel_u002ftest_u002fnotimpl_u002fForMissingImplTest2.ForMissingImplTest2;
				}
			],
			execute: function() {
				$makeClass(ForMissingImplTest, ForMissingImplTest2, [], {}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'ForMissingImplTest',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.notimpl.ForMissingImplTest.ForMissingImplTest',
						n4superType: undefined,
						allImplementedInterfaces: [],
						ownedMembers: [],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				Object.defineProperty(ForMissingImplTest, '$di', {
					value: {
						superType: ForMissingImplTest2,
						fieldsInjectedTypes: []
					}
				});
			}
		};
	});
})(typeof module !== 'undefined' && module.exports ? require('n4js-node/index').System(require, module) : System);
//# sourceMappingURL=ForMissingImplTest.map
