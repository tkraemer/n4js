(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft.reporter.xunit/n4/mangel/reporter/xunit/XUnitReporter'
	], function($n4Export) {
		var XUnitReportSpec, NodeTestOptions;
		NodeTestOptions = function NodeTestOptions() {
			XUnitReportSpec.prototype.constructor.call(this);
			this.scan = undefined;
			this.projects = undefined;
			this.filter = undefined;
			this.define = undefined;
			this.compile = undefined;
			this.testCatalog = undefined;
			this.targetPlatformFile = undefined;
			this.targetPlatformInstallLocation = undefined;
			this.targetPlatformSkipInstall = undefined;
			this.debug = undefined;
		};
		$n4Export('default', NodeTestOptions);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_u002ereporter_u002exunit_n4_u002fmangel_u002freporter_u002fxunit_u002fXUnitReporter) {
					XUnitReportSpec = $_import_eu_u002enumberfour_u002emangelhaft_u002ereporter_u002exunit_n4_u002fmangel_u002freporter_u002fxunit_u002fXUnitReporter.XUnitReportSpec;
				}
			],
			execute: function() {
				$makeClass(NodeTestOptions, XUnitReportSpec, [], {
					scan: {
						value: undefined,
						writable: true
					},
					projects: {
						value: undefined,
						writable: true
					},
					filter: {
						value: undefined,
						writable: true
					},
					define: {
						value: undefined,
						writable: true
					},
					compile: {
						value: undefined,
						writable: true
					},
					testCatalog: {
						value: undefined,
						writable: true
					},
					targetPlatformFile: {
						value: undefined,
						writable: true
					},
					targetPlatformInstallLocation: {
						value: undefined,
						writable: true
					},
					targetPlatformSkipInstall: {
						value: undefined,
						writable: true
					},
					debug: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'NodeTestOptions',
						origin: 'eu.numberfour.mangelhaft.runner.node',
						fqn: 'n4.mangel.runner.node.NodeTestOptions.NodeTestOptions',
						n4superType: XUnitReportSpec.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'scan',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'projects',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'filter',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'define',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'compile',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'testCatalog',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'targetPlatformFile',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'targetPlatformInstallLocation',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'targetPlatformSkipInstall',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'debug',
								isStatic: false,
								annotations: []
							})
						],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				Object.defineProperty(NodeTestOptions, '$di', {
					value: {
						superType: XUnitReportSpec,
						fieldsInjectedTypes: []
					}
				});
			}
		};
	});
})(typeof module !== 'undefined' && module.exports ? require('n4js-node/index').System(require, module) : System);
//# sourceMappingURL=NodeTestOptions.map
