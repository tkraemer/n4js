(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft.mangeltypes/n4/mangel/mangeltypes/ITestReporter',
		'eu.numberfour.mangelhaft.mangeltypes/n4/mangel/mangeltypes/TestSpy'
	], function($n4Export) {
		var ITestReporter, TestSpy, cli_color, ConsoleReporter;
		ConsoleReporter = function ConsoleReporter() {
			this.timeoutBuffer = 1000 * 30;
			this.logger = function() {
				var messages = Array.prototype.slice.call(arguments, 0);
				console.log.apply(console, messages);
			};
			this.buffered = false;
			this.buffer = [];
			this.spy = undefined;
			ITestReporter.$fieldInit(this, undefined, {
				timeoutBuffer: undefined,
				logger: undefined,
				buffered: undefined,
				buffer: undefined,
				spy: undefined
			});
		};
		$n4Export('ConsoleReporter', ConsoleReporter);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fITestReporter) {
					ITestReporter = $_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fITestReporter.ITestReporter;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fTestSpy) {
					TestSpy = $_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fTestSpy.TestSpy;
				}
			],
			execute: function() {
				cli_color = System._nodeRequire("cli-color");
				$makeClass(ConsoleReporter, Object, [
					ITestReporter
				], {
					setLogger: {
						value: function setLogger___n4(logger) {
							this.logger = logger;
							return this;
						}
					},
					setBuffered: {
						value: function setBuffered___n4(shouldBuffer) {
							let that = this;
							this.buffered = shouldBuffer;
							if (shouldBuffer === true) {
								this.logger = function() {
									var messages = Array.prototype.slice.call(arguments, 0);
									that.buffer.push(messages);
								};
							}
							return this;
						}
					},
					register: {
						value: function register___n4() {
							return $spawn(function*() {
								let sessionId = null;
								this.spy.testingStarted.add((function(numAllGroups, sid, numAllTests) {
									this.logger.call(this, "Begin tests");
								}).bind(this));
								this.spy.groupStarted.add((function(group) {
									this.logger.call(this, [
										"  ",
										"Group",
										group.name,
										":"
									].join(" "));
								}).bind(this));
								this.spy.testFinished.add((function(group, test, testResult) {
									let unsuccessString = "FAIL";
									if (!testResult) {
										let err = new Error("testResult is null in handleTestFinished");
										console.error(this.constructor.n4type.fqn, test ? test.name : "unknown test", err, err.stack);
										return true;
									}
									switch(testResult.testStatus) {
										case 'PASSED':
											{
												this.logger.call(this, [
													"  ",
													"  ",
													test.name,
													":",
													cli_color.green("OK")
												].join(" "));
												break;
											}
										case 'ERROR':
											unsuccessString = cli_color.red("ERROR");
										case 'FAILED':
											{
												let trace;
												try {
													trace = cli_color.red(testResult && testResult.trace && testResult.trace.length ? testResult.trace.join("\n") : "NO TRACE");
												} catch(er) {
													this.logger(er, cli_color.red(typeof (testResult.trace)));
													trace = testResult.trace.toString();
												}
												this.logger([
													"  ",
													"  ",
													test.name,
													":",
													unsuccessString
												].join(" "));
												this.logger([
													"  ",
													"  ",
													"  ",
													cli_color.red(testResult.message)
												].join(" "));
												this.logger([
													"  ",
													"  ",
													"  ",
													"Stack:",
													trace.split(/\n/).join("\n                ")
												].join(" "));
												break;
											}
										case 'SKIPPED_PRECONDITION':
											{
												this.logger.call(this, [
													"  ",
													"  ",
													test.name,
													":",
													cli_color.cyan("SKIPPED_PRECONDITION")
												].join(" "));
												break;
											}
										case 'SKIPPED_NOT_IMPLEMENTED':
											{
												this.logger.call(this, [
													"  ",
													"  ",
													test.name,
													":",
													cli_color.cyan("SKIPPED_NOT_IMPLEMENTED")
												].join(" "));
												break;
											}
										case 'SKIPPED':
											{
												this.logger.call(this, [
													"  ",
													"  ",
													test.name,
													":",
													cli_color.yellow("SKIPPED")
												].join(" "));
												break;
											}
									}
								}).bind(this));
								this.spy.testingFinished.add((function(resultGroups) {
									return $spawn(function*() {
										this.logger.call(this, "Tests done.");
									}.apply(this, arguments));
								}).bind(this));
								return this;
							}.apply(this, arguments));
						}
					},
					timeoutBuffer: {
						value: undefined,
						writable: true
					},
					logger: {
						value: undefined,
						writable: true
					},
					buffered: {
						value: undefined,
						writable: true
					},
					buffer: {
						value: undefined,
						writable: true
					},
					spy: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'ConsoleReporter',
						origin: 'eu.numberfour.mangelhaft.reporter.console',
						fqn: 'n4.mangel.reporter.console.ConsoleReporter.ConsoleReporter',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [
							'n4.mangel.mangeltypes.ITestReporter.ITestReporter'
						],
						ownedMembers: [
							new N4DataField({
								name: 'timeoutBuffer',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'logger',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'buffered',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'buffer',
								isStatic: false,
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
								name: 'setLogger',
								isStatic: false,
								jsFunction: instanceProto['setLogger'],
								annotations: []
							}),
							new N4Method({
								name: 'setBuffered',
								isStatic: false,
								jsFunction: instanceProto['setBuffered'],
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
				Object.defineProperty(ConsoleReporter, '$di', {
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
//# sourceMappingURL=ConsoleReporter.map
