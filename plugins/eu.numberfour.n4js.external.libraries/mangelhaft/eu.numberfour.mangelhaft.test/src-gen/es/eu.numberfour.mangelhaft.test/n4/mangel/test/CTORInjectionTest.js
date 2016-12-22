(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft.assert/n4/mangel/assert/Assert',
		'eu.numberfour.mangelhaft.test/n4/mangel/test/helper/Thingy',
		'n4js.lang/n4js/lang/N4Injector'
	], function($n4Export) {
		var Assert, Thingy, N4Injector, TestSubject, CTORInjectionTest;
		TestSubject = function TestSubject() {
			this.thingy = undefined;
		};
		CTORInjectionTest = function CTORInjectionTest() {
			this.subject = undefined;
		};
		$n4Export('CTORInjectionTest', CTORInjectionTest);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssert) {
					Assert = $_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssert.Assert;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002etest_n4_u002fmangel_u002ftest_u002fhelper_u002fThingy) {
					Thingy = $_import_eu_u002enumberfour_u002emangelhaft_u002etest_n4_u002fmangel_u002ftest_u002fhelper_u002fThingy.Thingy;
				},
				function($_import_n4js_u002elang_n4js_u002flang_u002fN4Injector) {
					N4Injector = $_import_n4js_u002elang_n4js_u002flang_u002fN4Injector.N4Injector;
				}
			],
			execute: function() {
				$makeClass(TestSubject, N4Object, [], {
					thingy: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'TestSubject',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.CTORInjectionTest.TestSubject',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'thingy',
								isStatic: false,
								annotations: [
									new N4Annotation({
										name: 'Inject',
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
							})
						]
					});
					return metaClass;
				});
				Object.defineProperty(TestSubject, '$di', {
					value: {
						binders: [],
						fieldsInjectedTypes: [
							{
								name: 'thingy',
								type: Thingy
							}
						]
					}
				});
				$makeClass(CTORInjectionTest, N4Object, [], {
					testSimpleDi: {
						value: function testSimpleDi___n4() {
							Assert.doesNotThrow(function() {
								N4Injector.of(TestSubject);
							}, null, "Injection should not throw when using constructor injection");
						}
					},
					subject: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'CTORInjectionTest',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.CTORInjectionTest.CTORInjectionTest',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'subject',
								isStatic: false,
								annotations: []
							}),
							new N4Method({
								name: 'testSimpleDi',
								isStatic: false,
								jsFunction: instanceProto['testSimpleDi'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
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
//# sourceMappingURL=CTORInjectionTest.map
