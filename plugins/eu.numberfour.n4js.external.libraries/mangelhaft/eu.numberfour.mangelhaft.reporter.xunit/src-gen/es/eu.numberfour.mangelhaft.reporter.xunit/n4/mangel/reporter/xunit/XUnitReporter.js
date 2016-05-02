(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft.mangeltypes/n4/mangel/mangeltypes/ITestReporter',
		'eu.numberfour.mangelhaft.mangeltypes/n4/mangel/mangeltypes/TestSpy',
		'@node/fs',
		'@node/os'
	], function($n4Export) {
		var ITestReporter, TestSpy, lib_fs, os, xmlBuilder, isOK, XUnitReportSpec, XUnitReporter;
		isOK = function isOK(testResult) {
			var status = testResult.testStatus;
			return status === 'PASSED' || status.startsWith('SKIPPED');
		};
		XUnitReportSpec = function XUnitReportSpec() {
			this.xunitReportFile = undefined;
			this.xunitReportName = undefined;
			this.xunitReportPackage = undefined;
		};
		$n4Export('XUnitReportSpec', XUnitReportSpec);
		XUnitReporter = function XUnitReporter() {
			this.spy = undefined;
			this.spec = {};
			this.startTime = undefined;
			this.resultsMap = new Map();
			ITestReporter.$fieldInit(this, undefined, {
				spy: undefined,
				spec: undefined,
				startTime: undefined,
				resultsMap: undefined
			});
		};
		$n4Export('XUnitReporter', XUnitReporter);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fITestReporter) {
					ITestReporter = $_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fITestReporter.ITestReporter;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fTestSpy) {
					TestSpy = $_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fTestSpy.TestSpy;
				},
				function($_import_n4js_u002druntime_u002dnode_fs) {
					lib_fs = $_import_n4js_u002druntime_u002dnode_fs;
				},
				function($_import_n4js_u002druntime_u002dnode_os) {
					os = $_import_n4js_u002druntime_u002dnode_os;
				}
			],
			execute: function() {
				xmlBuilder = System._nodeRequire("xmlbuilder");
				$makeClass(XUnitReportSpec, Object, [], {
					xunitReportFile: {
						value: undefined,
						writable: true
					},
					xunitReportName: {
						value: undefined,
						writable: true
					},
					xunitReportPackage: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'XUnitReportSpec',
						origin: 'eu.numberfour.mangelhaft.reporter.xunit',
						fqn: 'n4.mangel.reporter.xunit.XUnitReporter.XUnitReportSpec',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'xunitReportFile',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'xunitReportName',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'xunitReportPackage',
								isStatic: false,
								annotations: []
							})
						],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				$makeClass(XUnitReporter, Object, [
					ITestReporter
				], {
					testingStarted: {
						value: function testingStarted___n4(numAllGroups, sid, numAllTests) {
							this.startTime = new Date();
						}
					},
					testFinished: {
						value: function testFinished___n4(group, test, testResult) {
							this.resultsMap.set(group.name + "#" + test.name, testResult);
						}
					},
					testingFinished: {
						value: function testingFinished___n4(resultGroups) {
							return $spawn(function*() {
								let spec = this.spec, endTime = Date.now(), xml = xmlBuilder.create("testsuites"), suite = xml.ele("testsuite", {
									name: spec.xunitReportName,
									package: spec.xunitReportPackage,
									timestamp: this.startTime.toISOString().substring(0, 19),
									id: 0,
									hostname: os.hostname()
								}), suiteProps = suite.ele("properties");
								suiteProps.ele("property", {
									name: "os.platform",
									value: os.platform()
								});
								suiteProps.ele("property", {
									name: "node.version",
									value: process.version
								});
								let skipped = 0, errors = 0, failures = 0;
								Array.from(this.resultsMap.keys()).sort().forEach((function(k) {
									let result = this.resultsMap.get(k);
									var hashIndex = k.lastIndexOf("#"), spec = suite.ele("testcase", {
										name: k.substring(hashIndex + 1),
										time: result.elapsedTime / 1000,
										classname: k.substring(0, hashIndex)
									});
									if (isOK(result)) {
										if (result.testStatus.startsWith('SKIPPED')) {
											spec.ele("skipped", {});
											++skipped;
										}
									} else {
										++failures;
										if (result.testStatus === 'ERROR') {
											++errors;
										}
										spec.ele(result.testStatus === 'FAILED' ? "failure" : "error", {
											type: "",
											message: result.message
										}).text((result.trace || []).join("\n"));
									}
								}).bind(this));
								suite.att("tests", this.resultsMap.size);
								suite.att("errors", errors);
								suite.att("skipped", skipped);
								suite.att("failures", failures);
								suite.att("time", (endTime - this.startTime.getTime()) / 1000);
								if (spec.xunitReportFile) {
									(yield $n4promisifyFunction(lib_fs.writeFile, [
										this.spec.xunitReportFile,
										xml.end({
											pretty: true
										}),
										{
											encoding: "UTF-8"
										}
									], false, false));
								}
							}.apply(this, arguments));
						}
					},
					register: {
						value: function register___n4() {
							return $spawn(function*() {
								this.spy.testingStarted.add(this.testingStarted.bind(this));
								this.spy.testFinished.add(this.testFinished.bind(this));
								this.spy.testingFinished.add(this.testingFinished.bind(this));
								return this;
							}.apply(this, arguments));
						}
					},
					spy: {
						value: undefined,
						writable: true
					},
					spec: {
						value: undefined,
						writable: true
					},
					startTime: {
						value: undefined,
						writable: true
					},
					resultsMap: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'XUnitReporter',
						origin: 'eu.numberfour.mangelhaft.reporter.xunit',
						fqn: 'n4.mangel.reporter.xunit.XUnitReporter.XUnitReporter',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [
							'n4.mangel.mangeltypes.ITestReporter.ITestReporter'
						],
						ownedMembers: [
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
							new N4DataField({
								name: 'spec',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'startTime',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'resultsMap',
								isStatic: false,
								annotations: []
							}),
							new N4Method({
								name: 'testingStarted',
								isStatic: false,
								jsFunction: instanceProto['testingStarted'],
								annotations: []
							}),
							new N4Method({
								name: 'testFinished',
								isStatic: false,
								jsFunction: instanceProto['testFinished'],
								annotations: []
							}),
							new N4Method({
								name: 'testingFinished',
								isStatic: false,
								jsFunction: instanceProto['testingFinished'],
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
				Object.defineProperty(XUnitReporter, '$di', {
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
//# sourceMappingURL=XUnitReporter.map
