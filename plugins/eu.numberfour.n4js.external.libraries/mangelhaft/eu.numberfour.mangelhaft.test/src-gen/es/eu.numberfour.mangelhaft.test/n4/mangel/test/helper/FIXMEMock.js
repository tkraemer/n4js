(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft/n4/mangel/Test'
	], function($n4Export) {
		var IFIXME2, FIXME2Mock;
		FIXME2Mock = function FIXME2Mock() {
			IFIXME2.$fieldInit.call(this, undefined, {
				logStr: undefined
			});
		};
		$n4Export('FIXME2Mock', FIXME2Mock);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTest) {
					IFIXME2 = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTest.IFIXME2;
				}
			],
			execute: function() {
				$makeClass(FIXME2Mock, N4Object, [
					IFIXME2
				], {
					logStr: {
						get: function getLogStr___n4() {
							return "FIXME joe from Test";
						}
					},
					party: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'FIXME2Mock',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.FIXMEMock.FIXME2Mock',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [
							'n4.mangel.Test.IFIXME2'
						],
						ownedMembers: [
							new N4Accessor({
								name: 'logStr',
								getter: true,
								isStatic: false,
								annotations: []
							})
						],
						consumedMembers: [
							new N4DataField({
								name: 'party',
								isStatic: false,
								annotations: []
							})
						],
						annotations: []
					});
					return metaClass;
				});
			}
		};
	});
})(typeof module !== 'undefined' && module.exports ? require('n4js-node/index').System(require, module) : System);
//# sourceMappingURL=FIXMEMock.map
