(function(System) {
	'use strict';
	System.register([], function($n4Export) {
		var TestDIComponent;
		TestDIComponent = function TestDIComponent() {};
		$n4Export('TestDIComponent', TestDIComponent);
		return {
			setters: [],
			execute: function() {
				$makeClass(TestDIComponent, Object, [], {}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'TestDIComponent',
						origin: 'eu.numberfour.mangelhaft',
						fqn: 'n4.mangel.mangeltypes.TestDIComponent.TestDIComponent',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [],
						consumedMembers: [],
						annotations: [
							new N4Annotation({
								name: 'GenerateInjector',
								details: []
							})
						]
					});
					return metaClass;
				});
				Object.defineProperty(TestDIComponent, '$di', {
					value: {
						binders: [],
						fieldsInjectedTypes: []
					}
				});
			}
		};
	});
})(typeof module !== 'undefined' && module.exports ? require('n4js-node/index').System(require, module) : System);
//# sourceMappingURL=TestDIComponent.map
