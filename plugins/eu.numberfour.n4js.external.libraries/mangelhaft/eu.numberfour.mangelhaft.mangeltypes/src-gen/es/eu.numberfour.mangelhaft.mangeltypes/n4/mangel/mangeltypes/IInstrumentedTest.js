(function(System) {
	'use strict';
	System.register([], function($n4Export) {
		var IInstrumentedTest;
		IInstrumentedTest = {};
		$n4Export('IInstrumentedTest', IInstrumentedTest);
		return {
			setters: [],
			execute: function() {
				IInstrumentedTest.$fieldInit = function IInstrumentedTest_fieldInit(target, spec, mixinExclusion) {
					if (spec) {
						if (!(mixinExclusion.hasOwnProperty('name') || target.hasOwnProperty('name'))) {
							target.name = 'name' in spec ? spec.name : undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('tests') || target.hasOwnProperty('tests'))) {
							target.tests = 'tests' in spec ? spec.tests : undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('beforeAlls') || target.hasOwnProperty('beforeAlls'))) {
							target.beforeAlls = 'beforeAlls' in spec ? spec.beforeAlls : undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('afterAlls') || target.hasOwnProperty('afterAlls'))) {
							target.afterAlls = 'afterAlls' in spec ? spec.afterAlls : undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('befores') || target.hasOwnProperty('befores'))) {
							target.befores = 'befores' in spec ? spec.befores : undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('afters') || target.hasOwnProperty('afters'))) {
							target.afters = 'afters' in spec ? spec.afters : undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('testObject') || target.hasOwnProperty('testObject'))) {
							target.testObject = 'testObject' in spec ? spec.testObject : undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('fqn') || target.hasOwnProperty('fqn'))) {
							target.fqn = 'fqn' in spec ? spec.fqn : undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('parent') || target.hasOwnProperty('parent'))) {
							target.parent = 'parent' in spec ? spec.parent : null;
						}
						if (!(mixinExclusion.hasOwnProperty('child') || target.hasOwnProperty('child'))) {
							target.child = 'child' in spec ? spec.child : null;
						}
						if (!(mixinExclusion.hasOwnProperty('error') || target.hasOwnProperty('error'))) {
							target.error = 'error' in spec ? spec.error : undefined;
						}
					} else {
						if (!(mixinExclusion.hasOwnProperty('name') || target.hasOwnProperty('name'))) {
							target.name = undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('tests') || target.hasOwnProperty('tests'))) {
							target.tests = undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('beforeAlls') || target.hasOwnProperty('beforeAlls'))) {
							target.beforeAlls = undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('afterAlls') || target.hasOwnProperty('afterAlls'))) {
							target.afterAlls = undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('befores') || target.hasOwnProperty('befores'))) {
							target.befores = undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('afters') || target.hasOwnProperty('afters'))) {
							target.afters = undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('testObject') || target.hasOwnProperty('testObject'))) {
							target.testObject = undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('fqn') || target.hasOwnProperty('fqn'))) {
							target.fqn = undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('parent') || target.hasOwnProperty('parent'))) {
							target.parent = null;
						}
						if (!(mixinExclusion.hasOwnProperty('child') || target.hasOwnProperty('child'))) {
							target.child = null;
						}
						if (!(mixinExclusion.hasOwnProperty('error') || target.hasOwnProperty('error'))) {
							target.error = undefined;
						}
					}
				};
				IInstrumentedTest.$methods = {};
				$makeInterface(IInstrumentedTest, function(instanceProto, staticProto) {
					var metaClass = new N4Interface({
						name: 'IInstrumentedTest',
						origin: 'eu.numberfour.mangelhaft.mangeltypes',
						fqn: 'n4.mangel.mangeltypes.IInstrumentedTest.IInstrumentedTest',
						n4superType: undefined,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'name',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'tests',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'beforeAlls',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'afterAlls',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'befores',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'afters',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'testObject',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'fqn',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'parent',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'child',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'error',
								isStatic: false,
								annotations: []
							}),
							new N4Method({
								name: 'filterTests',
								isStatic: false,
								jsFunction: instanceProto['filterTests'],
								annotations: []
							}),
							new N4Method({
								name: 'load',
								isStatic: false,
								jsFunction: instanceProto['load'],
								annotations: []
							}),
							new N4Method({
								name: 'setTestObject',
								isStatic: false,
								jsFunction: instanceProto['setTestObject'],
								annotations: []
							}),
							new N4Method({
								name: 'setError',
								isStatic: false,
								jsFunction: instanceProto['setError'],
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
//# sourceMappingURL=IInstrumentedTest.map
