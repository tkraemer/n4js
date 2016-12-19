(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft/n4/mangel/mangeltypes/ITestReporter',
		'eu.numberfour.mangelhaft/n4/mangel/mangeltypes/TestSpy'
	], function($n4Export) {
		var ITestReporter, TestSpy, CliColor, ConsoleReporter;
		CliColor = function CliColor() {};
		$n4Export('CliColor', CliColor);
		ConsoleReporter = function ConsoleReporter() {
			this.timeoutBuffer = 1000 * 30;
			this.logger = function() {
				var messages = Array.prototype.slice.call(arguments, 0);
				console.log.apply(console, messages);
			};
			this.buffered = false;
			this.buffer = [];
			this.cliColor = new CliColor();
			this.spy = undefined;
			ITestReporter.$fieldInit(this, undefined, {
				timeoutBuffer: undefined,
				logger: undefined,
				buffered: undefined,
				buffer: undefined,
				cliColor: undefined,
				spy: undefined
			});
		};
		$n4Export('ConsoleReporter', ConsoleReporter);
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
				$makeClass(CliColor, N4Object, [], {
					red: {
						value: function red___n4(str) {
							return str;
						}
					},
					green: {
						value: function green___n4(str) {
							return str;
						}
					},
					cyan: {
						value: function cyan___n4(str) {
							return str;
						}
					},
					yellow: {
						value: function yellow___n4(str) {
							return str;
						}
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'CliColor',
						origin: 'eu.numberfour.mangelhaft.reporter.console',
						fqn: 'n4.mangel.reporter.console.ConsoleReporter.CliColor',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4Method({
								name: 'red',
								isStatic: false,
								jsFunction: instanceProto['red'],
								annotations: []
							}),
							new N4Method({
								name: 'green',
								isStatic: false,
								jsFunction: instanceProto['green'],
								annotations: []
							}),
							new N4Method({
								name: 'cyan',
								isStatic: false,
								jsFunction: instanceProto['cyan'],
								annotations: []
							}),
							new N4Method({
								name: 'yellow',
								isStatic: false,
								jsFunction: instanceProto['yellow'],
								annotations: []
							})
						],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				$makeClass(ConsoleReporter, N4Object, [
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
								let indent = 0;
								this.spy.testingStarted.add((function(numAllGroups, sid, numAllTests) {
									this.logger.call(this, ("" + "   ".repeat(indent) + "Begin tests"));
								}).bind(this));
								this.spy.parameterizedGroupsStarted.add((function(group) {
									++indent;
									this.logger.call(this, ("" + "   ".repeat(indent) + " Parameterized Group " + group.name + ":"));
								}).bind(this));
								this.spy.groupStarted.add((function(group) {
									++indent;
									let name = group.parameterizedName ? group.parameterizedName : "Group " + group.name;
									this.logger.call(this, ("" + "   ".repeat(indent) + " " + name + ":"));
								}).bind(this));
								this.spy.testFinished.add((function(group, test, testResult) {
									let unsuccessString = "FAIL";
									++indent;
									if (!testResult) {
										let err = new Error("testResult is null in handleTestFinished");
										console.error(this.constructor.n4type.fqn, test ? test.name : "unknown test", err, err.stack);
										return true;
									}
									switch(testResult.testStatus) {
										case 'PASSED':
											{
												this.logger.call(this, ("" + "   ".repeat(indent) + " " + test.name + " : " + this.cliColor.green("OK") + ""));
												break;
											}
										case 'ERROR':
											unsuccessString = this.cliColor.red("ERROR");
										case 'FAILED':
											{
												let trace;
												try {
													trace = this.cliColor.red(testResult && testResult.trace && testResult.trace.length ? testResult.trace.join("\n") : "NO TRACE");
												} catch(er) {
													this.logger(er, this.cliColor.red(typeof (testResult.trace)));
													trace = testResult.trace.toString();
												}
												this.logger(("" + "   ".repeat(indent) + " " + test.name + " :  " + unsuccessString + ""));
												this.logger(("" + "   ".repeat(indent + 1) + " " + this.cliColor.red(testResult.message) + ""));
												this.logger(("" + "   ".repeat(indent + 1) + " Stack: " + trace.split(/\n/).join("\n" + "   ".repeat(indent + 1)) + ""));
												break;
											}
										case 'SKIPPED_PRECONDITION':
											{
												this.logger.call(this, ("" + "   ".repeat(indent) + " " + test.name + " : " + this.cliColor.cyan("SKIPPED_PRECONDITION") + ""));
												break;
											}
										case 'SKIPPED_NOT_IMPLEMENTED':
											{
												this.logger.call(this, ("" + "   ".repeat(indent) + " " + test.name + " : " + this.cliColor.cyan("SKIPPED_NOT_IMPLEMENTED") + ""));
												break;
											}
										case 'SKIPPED':
											{
												this.logger.call(this, ("" + "   ".repeat(indent) + " " + test.name + " : " + this.cliColor.yellow("SKIPPED") + ""));
												break;
											}
									}
									--indent;
								}).bind(this));
								this.spy.groupFinished.add((function(group) {
									--indent;
								}).bind(this));
								this.spy.parameterizedGroupsFinished.add((function(group) {
									--indent;
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
					cliColor: {
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
								name: 'cliColor',
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
