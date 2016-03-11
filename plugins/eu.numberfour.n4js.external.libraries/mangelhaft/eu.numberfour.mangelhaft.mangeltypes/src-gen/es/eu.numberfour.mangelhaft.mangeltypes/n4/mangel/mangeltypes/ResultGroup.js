(function(System) {
	'use strict';
	System.register([], function($n4Export) {
		var ResultGroup;
		ResultGroup = function ResultGroup(spec) {
			this.description = spec && 'description' in spec ? spec.description : undefined;
			this.testResults = spec && 'testResults' in spec ? spec.testResults : undefined;
			this.successes = spec && 'successes' in spec ? spec.successes : 0;
			this.failures = spec && 'failures' in spec ? spec.failures : 0;
			if (spec) {}
		};
		$n4Export('ResultGroup', ResultGroup);
		return {
			setters: [],
			execute: function() {
				$makeClass(ResultGroup, Object, [], {
					description: {
						value: undefined,
						writable: true
					},
					testResults: {
						value: undefined,
						writable: true
					},
					successes: {
						value: undefined,
						writable: true
					},
					failures: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'ResultGroup',
						origin: 'eu.numberfour.mangelhaft.mangeltypes',
						fqn: 'n4.mangel.mangeltypes.ResultGroup.ResultGroup',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'description',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'testResults',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'successes',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'failures',
								isStatic: false,
								annotations: []
							}),
							new N4Method({
								name: 'constructor',
								isStatic: false,
								jsFunction: instanceProto['constructor'],
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
//# sourceMappingURL=ResultGroup.map
