(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft/n4/mangel/Test',
		'eu.numberfour.mangelhaft.assert/n4/mangel/assert/Assert',
		'eu.numberfour.mangelhaft.test/n4/mangel/test/helper/FIXMEMock'
	], function($n4Export) {
		var IFIXME, IFIXME2, Assert, FIXME2Mock, HiddenDIC, FIXME3, DirectTestBinder, DirectInjectionIncompatibleParent;
		HiddenDIC = function HiddenDIC() {};
		FIXME3 = function FIXME3() {
			IFIXME.$fieldInit(this, undefined, {
				logStr: undefined
			});
		};
		DirectTestBinder = function DirectTestBinder() {};
		DirectInjectionIncompatibleParent = function DirectInjectionIncompatibleParent() {};
		$n4Export('DirectInjectionIncompatibleParent', DirectInjectionIncompatibleParent);
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
				}
			],
			execute: function() {
				$makeClass(HiddenDIC, N4Object, [], {}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'HiddenDIC',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.DirectInjectionIncompatibleParent.HiddenDIC',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [],
						consumedMembers: [],
						annotations: [
							new N4Annotation({
								name: 'GenerateInjector',
								details: []
							})
						]
					});
					return metaClass;
				});
				Object.defineProperty(HiddenDIC, '$di', {
					value: {
						binders: [],
						fieldsInjectedTypes: []
					}
				});
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
						fqn: 'n4.mangel.test.DirectInjectionIncompatibleParent.FIXME3',
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
						fqn: 'n4.mangel.test.DirectInjectionIncompatibleParent.DirectTestBinder',
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
				$makeClass(DirectInjectionIncompatibleParent, N4Object, [], {
					skippedTest: {
						value: function skippedTest___n4() {
							Assert.fail("Should never be reached");
						}
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'DirectInjectionIncompatibleParent',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.DirectInjectionIncompatibleParent.DirectInjectionIncompatibleParent',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4Method({
								name: 'skippedTest',
								isStatic: false,
								jsFunction: instanceProto['skippedTest'],
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
									'HiddenDIC'
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
				Object.defineProperty(DirectInjectionIncompatibleParent, '$di', {
					value: {
						parent: HiddenDIC,
						binders: [
							DirectTestBinder
						],
						fieldsInjectedTypes: []
					}
				});
			}
		};
	});
})(typeof module !== 'undefined' && module.exports ? require('n4js-node/index').System(require, module) : System);
//# sourceMappingURL=DirectInjectionIncompatibleParent.map
