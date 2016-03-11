(function(System) {
	'use strict';
	System.register([], function($n4Export) {
		var TestFunctionType;
		TestFunctionType = function TestFunctionType(name, value) {
			this.name = name;
			this.value = value;
		};
		$n4Export('TestFunctionType', TestFunctionType);
		return {
			setters: [],
			execute: function() {
				$makeEnum(TestFunctionType, false, [
					[
						'BEFORE_ALL',
						'BEFORE_ALL'
					],
					[
						'BEFORE_TEST',
						'BEFORE_TEST'
					],
					[
						'AFTER_ALL',
						'AFTER_ALL'
					],
					[
						'AFTER_TEST',
						'AFTER_TEST'
					],
					[
						'TEST',
						'TEST'
					]
				], function(instanceProto, staticProto) {
					var metaClass = new N4EnumType({
						name: 'TestFunctionType',
						origin: 'eu.numberfour.mangelhaft.mangeltypes',
						fqn: 'n4.mangel.mangeltypes.TestFunctionType.TestFunctionType',
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
//# sourceMappingURL=TestFunctionType.map
