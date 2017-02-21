(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft/n4/mangel/Test',
		'eu.numberfour.mangelhaft.assert/n4/mangel/assert/Assert',
		'eu.numberfour.mangelhaft.test/n4/mangel/test/helper/FIXMEMock',
		'eu.numberfour.mangelhaft.assert/n4/mangel/precondition/Precondition'
	], function($n4Export) {
		var IFIXME, IFIXME2, Assert, FIXME2Mock, Precondition, FIXME3, DirectTestBinder, DirectInjectionNoParentTest;
		FIXME3 = function FIXME3() {
			IFIXME.$fieldInit.call(this, undefined, {
				logStr: undefined
			});
		};
		DirectTestBinder = function DirectTestBinder() {};
		DirectInjectionNoParentTest = function DirectInjectionNoParentTest() {
			this.fix = undefined;
			this.fix2 = undefined;
		};
		$n4Export('DirectInjectionNoParentTest', DirectInjectionNoParentTest);
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
				}
			],
			execute: function() {
				$makeClass(FIXME3, N4Object, [
					IFIXME
				], {
					logStr: {
						get: function getLogStr___n4() {
							return "FIXME_local joe from Mangelhaft";
						}
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'FIXME3',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.DirectInjectionNoParentTest.FIXME3',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [
							'n4.mangel.Test.IFIXME'
						],
						ownedMembers: [
							new N4Accessor({
								name: 'logStr',
								getter: true,
								isStatic: false,
								annotations: []
							})
						],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				$makeClass(DirectTestBinder, N4Object, [], {}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'DirectTestBinder',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.DirectInjectionNoParentTest.DirectTestBinder',
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
									'IFIXME',
									'FIXME3'
								]
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
								from: IFIXME,
								to: FIXME3
							},
							{
								from: IFIXME2,
								to: FIXME2Mock
							}
						],
						methodBindings: [],
						fieldsInjectedTypes: []
					}
				});
				$makeClass(DirectInjectionNoParentTest, N4Object, [], {
					preconditionCheck: {
						value: function preconditionCheck___n4() {
							if ($implements(this.fix, 'n4.mangel.Test.IFIXME') === false || $implements(this.fix2, 'n4.mangel.Test.IFIXME2') === false) {
								Precondition.fail("no parent IFIXME bindings", "Tests should not run if parent bindings have not been set");
							}
						}
					},
					fromMangelhaft: {
						value: function fromMangelhaft___n4() {
							Assert.equal(this.fix.logStr, "FIXME_local joe from Mangelhaft", "should be from Mangelhaft");
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
						name: 'DirectInjectionNoParentTest',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.DirectInjectionNoParentTest.DirectInjectionNoParentTest',
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
								name: 'UseBinder',
								details: [
									'DirectTestBinder'
								]
							})
						]
					});
					return metaClass;
				});
				Object.defineProperty(DirectInjectionNoParentTest, '$di', {
					value: {
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
//# sourceMappingURL=DirectInjectionNoParentTest.map
