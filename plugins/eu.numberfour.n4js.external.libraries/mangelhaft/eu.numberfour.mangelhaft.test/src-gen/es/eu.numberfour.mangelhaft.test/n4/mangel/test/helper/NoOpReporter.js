(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft/n4/mangel/mangeltypes/ITestReporter',
		'eu.numberfour.mangelhaft/n4/mangel/mangeltypes/TestSpy'
	], function($n4Export) {
		var ITestReporter, TestSpy, NoOpReporter;
		NoOpReporter = function NoOpReporter() {
			this.spy = undefined;
			ITestReporter.$fieldInit(this, undefined, {
				argCalls: undefined,
				spy: undefined
			});
		};
		$n4Export('NoOpReporter', NoOpReporter);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fmangeltypes_u002fITestReporter) {
					ITestReporter = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fmangeltypes_u002fITestReporter.ITestReporter;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fmangeltypes_u002fTestSpy) {
					TestSpy = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fmangeltypes_u002fTestSpy.TestSpy;
				}
			],
			execute: function() {
				$makeClass(NoOpReporter, N4Object, [
					ITestReporter
				], {
					register: {
						value: function register___n4() {
							return $spawn(function*() {
								this.spy.testingStarted.add(function(numAllGroups, sessionId, numAllTests) {
									var $capturedArgs = arguments;
									NoOpReporter.argCalls.push({
										testingStarted: $capturedArgs
									});
								});
								this.spy.groupStarted.add(function(group) {
									var $capturedArgs = arguments;
									NoOpReporter.argCalls.push({
										groupStarted: $capturedArgs
									});
								});
								this.spy.testStarted.add(function(group, test) {
									var $capturedArgs = arguments;
									NoOpReporter.argCalls.push({
										testStarted: $capturedArgs
									});
								});
								this.spy.testFinished.add(function(group, test) {
									var $capturedArgs = arguments;
									NoOpReporter.argCalls.push({
										testStarted: $capturedArgs
									});
								});
								this.spy.groupFinished.add(function(group) {
									var $capturedArgs = arguments;
									NoOpReporter.argCalls.push({
										groupStarted: $capturedArgs
									});
								});
								this.spy.testingFinished.add(function(resultGroups) {
									var $capturedArgs = arguments;
									NoOpReporter.argCalls.push({
										testingFinished: $capturedArgs
									});
								});
								return this;
							}.apply(this, arguments));
						}
					},
					spy: {
						value: undefined,
						writable: true
					}
				}, {
					argCalls: {
						value: undefined,
						writable: true
					}
				}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'NoOpReporter',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.NoOpReporter.NoOpReporter',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [
							'n4.mangel.mangeltypes.ITestReporter.ITestReporter'
						],
						ownedMembers: [
							new N4DataField({
								name: 'argCalls',
								isStatic: true,
								annotations: []
							}),
							new N4DataField({
								name: 'spy',
								isStatic: false,
								annotations: [
									new N4Annotation({
										name: 'Inject',
										details: []
									})
								]
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
				NoOpReporter.argCalls = [];
				Object.defineProperty(NoOpReporter, '$di', {
					value: {
						fieldsInjectedTypes: [
							{
								name: 'spy',
								type: TestSpy
							}
						]
					}
				});
			}
		};
	});
})(typeof module !== 'undefined' && module.exports ? require('n4js-node/index').System(require, module) : System);
//# sourceMappingURL=NoOpReporter.map
