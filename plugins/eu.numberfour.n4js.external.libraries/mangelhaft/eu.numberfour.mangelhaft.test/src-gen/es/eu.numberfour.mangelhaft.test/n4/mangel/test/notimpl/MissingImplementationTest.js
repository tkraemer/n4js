(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft.test/n4/mangel/test/notimpl/ForMissingImplTest'
	], function($n4Export) {
		var ForMissingImplTest, MissingImplementationTest;
		MissingImplementationTest = function MissingImplementationTest() {};
		$n4Export('MissingImplementationTest', MissingImplementationTest);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_u002etest_n4_u002fmangel_u002ftest_u002fnotimpl_u002fForMissingImplTest) {
					ForMissingImplTest = $_import_eu_u002enumberfour_u002emangelhaft_u002etest_n4_u002fmangel_u002ftest_u002fnotimpl_u002fForMissingImplTest.ForMissingImplTest;
				}
			],
			execute: function() {
				$makeClass(MissingImplementationTest, N4Object, [], {
					testMissingImplWillNotFailAllOtherTests: {
						value: function testMissingImplWillNotFailAllOtherTests___n4() {
							new ForMissingImplTest();
						}
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'MissingImplementationTest',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.notimpl.MissingImplementationTest.MissingImplementationTest',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4Method({
								name: 'testMissingImplWillNotFailAllOtherTests',
								isStatic: false,
								jsFunction: instanceProto['testMissingImplWillNotFailAllOtherTests'],
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
//# sourceMappingURL=MissingImplementationTest.map
