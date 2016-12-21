(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft.test/n4/mangel/test/helper/IJoe',
		'eu.numberfour.mangelhaft.test/n4/mangel/test/helper/Thingy'
	], function($n4Export) {
		var IJoe, Stuff, BaseJoe;
		BaseJoe = function BaseJoe(stuffProvider) {
			IJoe.$fieldInit(this, undefined, {});
			console.log(stuffProvider.get());
		};
		$n4Export('BaseJoe', BaseJoe);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_u002etest_n4_u002fmangel_u002ftest_u002fhelper_u002fIJoe) {
					IJoe = $_import_eu_u002enumberfour_u002emangelhaft_u002etest_n4_u002fmangel_u002ftest_u002fhelper_u002fIJoe.IJoe;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002etest_n4_u002fmangel_u002ftest_u002fhelper_u002fThingy) {
					Stuff = $_import_eu_u002enumberfour_u002emangelhaft_u002etest_n4_u002fmangel_u002ftest_u002fhelper_u002fThingy.Stuff;
				}
			],
			execute: function() {
				$makeClass(BaseJoe, N4Object, [
					IJoe
				], {}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'BaseJoe',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.BaseJoe.BaseJoe',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [
							'n4.mangel.test.helper.IJoe.IJoe'
						],
						ownedMembers: [
							new N4Method({
								name: 'constructor',
								isStatic: false,
								jsFunction: instanceProto['constructor'],
								annotations: [
									new N4Annotation({
										name: 'Inject',
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
				Object.defineProperty(BaseJoe, '$di', {
					value: {
						injectCtorParams: [
							{
								name: 'stuffProvider',
								type: N4Provider,
								typeVar: {
									type: Stuff
								}
							}
						],
						fieldsInjectedTypes: []
					}
				});
			}
		};
	});
})(typeof module !== 'undefined' && module.exports ? require('n4js-node/index').System(require, module) : System);
//# sourceMappingURL=BaseJoe.map
