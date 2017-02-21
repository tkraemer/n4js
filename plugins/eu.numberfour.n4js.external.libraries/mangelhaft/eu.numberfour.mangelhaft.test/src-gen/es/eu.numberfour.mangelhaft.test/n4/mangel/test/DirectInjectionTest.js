(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft/n4/mangel/Test',
		'eu.numberfour.mangelhaft.assert/n4/mangel/assert/Assert',
		'eu.numberfour.mangelhaft.test/n4/mangel/test/helper/FIXMEMock',
		'eu.numberfour.mangelhaft.assert/n4/mangel/precondition/Precondition',
		'eu.numberfour.mangelhaft/n4/mangel/mangeltypes/TestDIComponent'
	], function($n4Export) {
		var IFIXME, IFIXME2, Assert, FIXME2Mock, Precondition, TestDIComponent, DirectTestBinder, DirectInjectionTest;
		DirectTestBinder = function DirectTestBinder() {};
		DirectInjectionTest = function DirectInjectionTest() {
			this.fix = undefined;
			this.fix2 = undefined;
		};
		$n4Export('DirectInjectionTest', DirectInjectionTest);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTest) {
					IFIXME = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTest.IFIXME;
					IFIXME2 = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTest.IFIXME2;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssert) {
					Assert = $_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssert.Assert;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002etest_n4_u002fmangel_u002ftest_u002fhelper_u002fFIXMEMock) {
					FIXME2Mock = $_import_eu_u002enumberfour_u002emangelhaft_u002etest_n4_u002fmangel_u002ftest_u002fhelper_u002fFIXMEMock.FIXME2Mock;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fprecondition_u002fPrecondition) {
					Precondition = $_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fprecondition_u002fPrecondition.Precondition;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fmangeltypes_u002fTestDIComponent) {
					TestDIComponent = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fmangeltypes_u002fTestDIComponent.TestDIComponent;
				}
			],
			execute: function() {
				$makeClass(DirectTestBinder, N4Object, [], {}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'DirectTestBinder',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.DirectInjectionTest.DirectTestBinder',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [],
						consumedMembers: [],
						annotations: [
							new N4Annotation({
								name: 'Binder',
								details: []
							}),
							new N4Annotation({
								name: 'Bind',
								details: [
									'IFIXME2',
									'FIXME2Mock'
								]
							})
						]
					});
					return metaClass;
				});
				Object.defineProperty(DirectTestBinder, '$di', {
					value: {
						bindings: [
							{
								from: IFIXME2,
								to: FIXME2Mock
							}
						],
						methodBindings: [],
						fieldsInjectedTypes: []
					}
				});
				$makeClass(DirectInjectionTest, N4Object, [], {
					preconditionCheck: {
						value: function preconditionCheck___n4() {
							if ($implements(this.fix, 'n4.mangel.Test.IFIXME') === false || $implements(this.fix2, 'n4.mangel.Test.IFIXME2') === false) {
								Precondition.fail("no parent IFIXME bindings", "Tests should not run if parent bindings have not been set");
							}
						}
					},
					fromMangelhaft: {
						value: function fromMangelhaft___n4() {
							Assert.equal(this.fix.logStr, "FIXME joe from Mangelhaft", "should be from Mangelhaft");
						}
					},
					fromMock: {
						value: function fromMock___n4() {
							Assert.equal(this.fix2.logStr, "FIXME joe from Test", "should be from Mock");
						}
					},
					fix: {
						value: undefined,
						writable: true
					},
					fix2: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'DirectInjectionTest',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.DirectInjectionTest.DirectInjectionTest',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'fix',
								isStatic: false,
								annotations: [
									new N4Annotation({
										name: 'Inject',
										details: []
									})
								]
							}),
							new N4DataField({
								name: 'fix2',
								isStatic: false,
								annotations: [
									new N4Annotation({
										name: 'Inject',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'preconditionCheck',
								isStatic: false,
								jsFunction: instanceProto['preconditionCheck'],
								annotations: [
									new N4Annotation({
										name: 'BeforeAll',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'fromMangelhaft',
								isStatic: false,
								jsFunction: instanceProto['fromMangelhaft'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'fromMock',
								isStatic: false,
								jsFunction: instanceProto['fromMock'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							})
						],
						consumedMembers: [],
						annotations: [
							new N4Annotation({
								name: 'GenerateInjector',
								details: []
							}),
							new N4Annotation({
								name: 'WithParentInjector',
								details: [
									'TestDIComponent'
								]
							}),
							new N4Annotation({
								name: 'UseBinder',
								details: [
									'DirectTestBinder'
								]
							})
						]
					});
					return metaClass;
				});
				Object.defineProperty(DirectInjectionTest, '$di', {
					value: {
						parent: TestDIComponent,
						binders: [
							DirectTestBinder
						],
						fieldsInjectedTypes: [
							{
								name: 'fix',
								type: IFIXME
							},
							{
								name: 'fix2',
								type: IFIXME2
							}
						]
					}
				});
			}
		};
	});
})(typeof module !== 'undefined' && module.exports ? require('n4js-node/index').System(require, module) : System);
//# sourceMappingURL=DirectInjectionTest.map
