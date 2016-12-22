(function(System) {
	'use strict';
	System.register([], function($n4Export) {
		var IJoe;
		IJoe = {};
		$n4Export('IJoe', IJoe);
		return {
			setters: [],
			execute: function() {
				IJoe.$fieldInit = function IJoe_fieldInit(target, spec, mixinExclusion) {};
				IJoe.$methods = {};
				$makeInterface(IJoe, function(instanceProto, staticProto) {
					var metaClass = new N4Interface({
						name: 'IJoe',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.IJoe.IJoe',
						n4superType: undefined,
						allImplementedInterfaces: [],
						ownedMembers: [],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
			}
		};
	});
})(typeof module !== 'undefined' && module.exports ? require('n4js-node/index').System(require, module) : System);
//# sourceMappingURL=IJoe.map
