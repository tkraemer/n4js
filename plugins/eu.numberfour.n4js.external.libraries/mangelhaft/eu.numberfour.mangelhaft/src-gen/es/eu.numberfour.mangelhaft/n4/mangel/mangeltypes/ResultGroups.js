(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft/n4/mangel/mangeltypes/ResultGroup',
		'eu.numberfour.mangelhaft/n4/mangel/mangeltypes/TestStatus'
	], function($n4Export) {
		var ResultGroup, aggregateTestStatuses, ResultGroups;
		ResultGroups = function ResultGroups(results) {
			this.results = undefined;
			this.successes = 0;
			this.failures = 0;
			this.skipped = 0;
			this.errors = 0;
			this.results = results;
			ResultGroups.accumulateResults(this, results);
		};
		$n4Export('ResultGroups', ResultGroups);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fmangeltypes_u002fResultGroup) {
					ResultGroup = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fmangeltypes_u002fResultGroup.ResultGroup;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fmangeltypes_u002fTestStatus) {
					aggregateTestStatuses = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fmangeltypes_u002fTestStatus.aggregateTestStatuses;
				}
			],
			execute: function() {
				$makeClass(ResultGroups, N4Object, [], {
					aggregate: {
						value: function aggregate___n4() {
							let result;
							let trMap = new Map();
							let description;
							for(let rg of this.results) {
								description = rg.description;
								for(let tr of rg.testResults) {
									if (trMap.has(tr.description)) {
										let oldTr = trMap.get(tr.description);
										let testStatus = aggregateTestStatuses(tr.testStatus, oldTr.testStatus);
										if (testStatus != oldTr.testStatus) {
											trMap.set(tr.description, tr);
										}
									} else {
										trMap.set(tr.description, tr);
									}
								}
							}
							result = new ResultGroup(Array.from(trMap.values()), description);
							return result;
						}
					},
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
					},
					skipped: {
						value: undefined,
						writable: true
					},
					errors: {
						value: undefined,
						writable: true
					}
				}, {
					accumulateResults: {
						value: function accumulateResults___n4(target, results) {
							for(let result of results) {
								target.successes += result.successes;
								target.failures += result.failures;
								target.errors += result.errors;
								target.skipped += result.skipped;
								if (result instanceof ResultGroups) {
									target.results = target.results.concat((result).results);
								}
							}
							return target;
						}
					},
					concat: {
						value: function concat___n4() {
							var resultGroups = Array.prototype.slice.call(arguments, 0);
							return this.concatArray(resultGroups);
						}
					},
					concatArray: {
						value: function concatArray___n4(resultGroupss) {
							return this.accumulateResults(new ResultGroups([]), resultGroupss);
						}
					}
				}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'ResultGroups',
						origin: 'eu.numberfour.mangelhaft',
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
							new N4DataField({
								name: 'skipped',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'errors',
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
								name: 'aggregate',
								isStatic: false,
								jsFunction: instanceProto['aggregate'],
								annotations: []
							}),
							new N4Method({
								name: 'accumulateResults',
								isStatic: true,
								jsFunction: staticProto['accumulateResults'],
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
