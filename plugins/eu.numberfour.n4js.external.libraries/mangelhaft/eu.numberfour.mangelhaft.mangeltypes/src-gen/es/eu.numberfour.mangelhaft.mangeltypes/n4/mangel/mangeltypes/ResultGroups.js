(function(System) {
	'use strict';
	System.register([], function($n4Export) {
		var ResultGroups;
		ResultGroups = function ResultGroups(spec) {
			this.results = spec && 'results' in spec ? spec.results : undefined;
			this.successes = spec && 'successes' in spec ? spec.successes : 0;
			this.failures = spec && 'failures' in spec ? spec.failures : 0;
			if (spec) {}
		};
		$n4Export('ResultGroups', ResultGroups);
		return {
			setters: [],
			execute: function() {
				$makeClass(ResultGroups, Object, [], {
					results: {
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
				}, {
					concat: {
						value: function concat___n4() {
							var resultGroups = Array.prototype.slice.call(arguments, 0);
							return ResultGroups.concatArray(resultGroups);
						}
					},
					concatArray: {
						value: function concatArray___n4(resultGroupss) {
							return resultGroupss.reduce(function(acc, resultGroups) {
								if (acc) {
									acc.results = acc.results.concat(resultGroups.results);
									acc.successes += resultGroups.successes;
									acc.failures += resultGroups.failures;
								}
								return acc;
							}, new ResultGroups({
								results: [],
								successes: 0,
								failures: 0
							}));
						}
					}
				}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'ResultGroups',
						origin: 'eu.numberfour.mangelhaft.mangeltypes',
						fqn: 'n4.mangel.mangeltypes.ResultGroups.ResultGroups',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'results',
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
							}),
							new N4Method({
								name: 'concat',
								isStatic: true,
								jsFunction: staticProto['concat'],
								annotations: []
							}),
							new N4Method({
								name: 'concatArray',
								isStatic: true,
								jsFunction: staticProto['concatArray'],
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
//# sourceMappingURL=ResultGroups.map
