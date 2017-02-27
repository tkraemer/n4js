(function(System) {
	'use strict';
	System.register([], function($n4Export) {
		var IInstrumentedTest;
		IInstrumentedTest = {};
		$n4Export('IInstrumentedTest', IInstrumentedTest);
		return {
			setters: [],
			execute: function() {
				IInstrumentedTest.$fieldInit = function IInstrumentedTest_fieldInit(spec, mixinExclusion) {
					if (spec) {
						if (!(mixinExclusion.hasOwnProperty('name') || this.hasOwnProperty('name'))) {
							this.name = 'name' in spec ? spec.name : undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('tests') || this.hasOwnProperty('tests'))) {
							this.tests = 'tests' in spec ? spec.tests : undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('beforeAlls') || this.hasOwnProperty('beforeAlls'))) {
							this.beforeAlls = 'beforeAlls' in spec ? spec.beforeAlls : undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('afterAlls') || this.hasOwnProperty('afterAlls'))) {
							this.afterAlls = 'afterAlls' in spec ? spec.afterAlls : undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('befores') || this.hasOwnProperty('befores'))) {
							this.befores = 'befores' in spec ? spec.befores : undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('afters') || this.hasOwnProperty('afters'))) {
							this.afters = 'afters' in spec ? spec.afters : undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('testObject') || this.hasOwnProperty('testObject'))) {
							this.testObject = 'testObject' in spec ? spec.testObject : undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('fqn') || this.hasOwnProperty('fqn'))) {
							this.fqn = 'fqn' in spec ? spec.fqn : undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('parent') || this.hasOwnProperty('parent'))) {
							this.parent = 'parent' in spec ? spec.parent : null;
						}
						if (!(mixinExclusion.hasOwnProperty('child') || this.hasOwnProperty('child'))) {
							this.child = 'child' in spec ? spec.child : null;
						}
						if (!(mixinExclusion.hasOwnProperty('error') || this.hasOwnProperty('error'))) {
							this.error = 'error' in spec ? spec.error : undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('parameterizedName') || this.hasOwnProperty('parameterizedName'))) {
							this.parameterizedName = 'parameterizedName' in spec ? spec.parameterizedName : undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('parameterizedTests') || this.hasOwnProperty('parameterizedTests'))) {
							this.parameterizedTests = 'parameterizedTests' in spec ? spec.parameterizedTests : undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('hasParameterizedTests') || this.hasOwnProperty('hasParameterizedTests'))) {
							this.hasParameterizedTests = 'hasParameterizedTests' in spec ? spec.hasParameterizedTests : undefined;
						}
					} else {
						if (!(mixinExclusion.hasOwnProperty('name') || this.hasOwnProperty('name'))) {
							this.name = undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('tests') || this.hasOwnProperty('tests'))) {
							this.tests = undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('beforeAlls') || this.hasOwnProperty('beforeAlls'))) {
							this.beforeAlls = undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('afterAlls') || this.hasOwnProperty('afterAlls'))) {
							this.afterAlls = undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('befores') || this.hasOwnProperty('befores'))) {
							this.befores = undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('afters') || this.hasOwnProperty('afters'))) {
							this.afters = undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('testObject') || this.hasOwnProperty('testObject'))) {
							this.testObject = undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('fqn') || this.hasOwnProperty('fqn'))) {
							this.fqn = undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('parent') || this.hasOwnProperty('parent'))) {
							this.parent = null;
						}
						if (!(mixinExclusion.hasOwnProperty('child') || this.hasOwnProperty('child'))) {
							this.child = null;
						}
						if (!(mixinExclusion.hasOwnProperty('error') || this.hasOwnProperty('error'))) {
							this.error = undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('parameterizedName') || this.hasOwnProperty('parameterizedName'))) {
							this.parameterizedName = undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('parameterizedTests') || this.hasOwnProperty('parameterizedTests'))) {
							this.parameterizedTests = undefined;
						}
						if (!(mixinExclusion.hasOwnProperty('hasParameterizedTests') || this.hasOwnProperty('hasParameterizedTests'))) {
							this.hasParameterizedTests = undefined;
						}
					}
				};
				IInstrumentedTest.$methods = {};
				$makeInterface(IInstrumentedTest, function(instanceProto, staticProto) {
					var metaClass = new N4Interface({
						name: 'IInstrumentedTest',
						origin: 'eu.numberfour.mangelhaft',
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
							new N4DataField({
								name: 'parameterizedName',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'parameterizedTests',
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
							}),
							new N4DataField({
								name: 'hasParameterizedTests',
								isStatic: false,
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
