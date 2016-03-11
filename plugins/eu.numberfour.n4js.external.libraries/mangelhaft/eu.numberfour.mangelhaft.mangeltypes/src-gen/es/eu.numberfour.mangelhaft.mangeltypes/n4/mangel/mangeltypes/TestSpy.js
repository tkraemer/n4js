(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft.mangeltypes/n4/mangel/mangeltypes/signal/Signal'
	], function($n4Export) {
		var Signal, TestSpy;
		TestSpy = function TestSpy() {
			this.testingStarted = new Signal();
			this.groupStarted = new Signal();
			this.testStarted = new Signal();
			this.testFinished = new Signal();
			this.groupFinished = new Signal();
			this.testingFinished = new Signal();
		};
		$n4Export('TestSpy', TestSpy);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fsignal_u002fSignal) {
					Signal = $_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fsignal_u002fSignal.Signal;
				}
			],
			execute: function() {
				$makeClass(TestSpy, Object, [], {
					reset: {
						value: function reset___n4() {
							let field, sig;
							;
							for(field in this) {
								if (this.hasOwnProperty(field)) {
									sig = (this)[field];
									sig.removeAll().forget();
								}
							}
							return this;
						}
					},
					testingStarted: {
						value: undefined,
						writable: true
					},
					groupStarted: {
						value: undefined,
						writable: true
					},
					testStarted: {
						value: undefined,
						writable: true
					},
					testFinished: {
						value: undefined,
						writable: true
					},
					groupFinished: {
						value: undefined,
						writable: true
					},
					testingFinished: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'TestSpy',
						origin: 'eu.numberfour.mangelhaft.mangeltypes',
						fqn: 'n4.mangel.mangeltypes.TestSpy.TestSpy',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'testingStarted',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'groupStarted',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'testStarted',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'testFinished',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'groupFinished',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'testingFinished',
								isStatic: false,
								annotations: []
							}),
							new N4Method({
								name: 'reset',
								isStatic: false,
								jsFunction: instanceProto['reset'],
								annotations: []
							})
						],
						consumedMembers: [],
						annotations: [
							new N4Annotation({
								name: 'Singleton',
								details: []
							})
						]
					});
					return metaClass;
				});
				Object.defineProperty(TestSpy, '$di', {
					value: {
						scope: 'Singleton',
						fieldsInjectedTypes: []
					}
				});
			}
		};
	});
})(typeof module !== 'undefined' && module.exports ? require('n4js-node/index').System(require, module) : System);
//# sourceMappingURL=TestSpy.map
