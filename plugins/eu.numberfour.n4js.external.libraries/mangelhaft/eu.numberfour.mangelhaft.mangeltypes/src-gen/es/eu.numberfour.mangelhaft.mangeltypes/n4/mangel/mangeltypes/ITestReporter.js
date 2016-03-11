(function(System) {
	'use strict';
	System.register([], function($n4Export) {
		var ITestReporter;
		ITestReporter = {};
		$n4Export('ITestReporter', ITestReporter);
		return {
			setters: [],
			execute: function() {
				ITestReporter.$fieldInit = function ITestReporter_fieldInit(target, spec, mixinExclusion) {
					if (spec) {
						if (!(mixinExclusion.hasOwnProperty('spy') || target.hasOwnProperty('spy'))) {
							target.spy = 'spy' in spec ? spec.spy : undefined;
						}
					} else {
						if (!(mixinExclusion.hasOwnProperty('spy') || target.hasOwnProperty('spy'))) {
							target.spy = undefined;
						}
					}
				};
				ITestReporter.$methods = {};
				$makeInterface(ITestReporter, function(instanceProto, staticProto) {
					var metaClass = new N4Interface({
						name: 'ITestReporter',
						origin: 'eu.numberfour.mangelhaft.mangeltypes',
						fqn: 'n4.mangel.mangeltypes.ITestReporter.ITestReporter',
						n4superType: undefined,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'spy',
								isStatic: false,
								annotations: []
							}),
							new N4Method({
								name: 'register',
								isStatic: false,
								jsFunction: instanceProto['register'],
								annotations: []
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
//# sourceMappingURL=ITestReporter.map
