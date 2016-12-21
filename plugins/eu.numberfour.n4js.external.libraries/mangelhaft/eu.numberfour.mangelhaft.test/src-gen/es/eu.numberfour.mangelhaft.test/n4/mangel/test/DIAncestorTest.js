(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft.test/n4/mangel/test/helper/Thingy',
		'n4js.lang/n4js/lang/N4Injector'
	], function($n4Export) {
		var Stuff, N4Injector, BaseJoe, Joe, TestSubject, InjectionTest;
		BaseJoe = function BaseJoe(stuffProvider) {};
		$n4Export('BaseJoe', BaseJoe);
		Joe = function Joe(stuffProvider) {
			BaseJoe.prototype.constructor.call(this, stuffProvider);
		};
		$n4Export('Joe', Joe);
		TestSubject = function TestSubject() {
			this.joe = undefined;
		};
		InjectionTest = function InjectionTest() {};
		$n4Export('InjectionTest', InjectionTest);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_u002etest_n4_u002fmangel_u002ftest_u002fhelper_u002fThingy) {
					Stuff = $_import_eu_u002enumberfour_u002emangelhaft_u002etest_n4_u002fmangel_u002ftest_u002fhelper_u002fThingy.Stuff;
				},
				function($_import_n4js_u002elang_n4js_u002flang_u002fN4Injector) {
					N4Injector = $_import_n4js_u002elang_n4js_u002flang_u002fN4Injector.N4Injector;
				}
			],
			execute: function() {
				$makeClass(BaseJoe, N4Object, [], {}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'BaseJoe',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.DIAncestorTest.BaseJoe',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4Method({
								name: 'constructor',
								isStatic: false,
								jsFunction: instanceProto['constructor'],
								annotations: [
									new N4Annotation({
										name: 'Inject',
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
				Object.defineProperty(BaseJoe, '$di', {
					value: {
						injectCtorParams: [
							{
								name: 'stuffProvider',
								type: N4Provider,
								typeVar: {
									type: Stuff
								}
							}
						],
						fieldsInjectedTypes: []
					}
				});
				$makeClass(Joe, BaseJoe, [], {}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'Joe',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.DIAncestorTest.Joe',
						n4superType: BaseJoe.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				Object.defineProperty(Joe, '$di', {
					value: {
						superType: BaseJoe,
						fieldsInjectedTypes: []
					}
				});
				$makeClass(TestSubject, N4Object, [], {
					joe: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'TestSubject',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.DIAncestorTest.TestSubject',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'joe',
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
								name: 'joe',
								type: Joe
							}
						]
					}
				});
				$makeClass(InjectionTest, N4Object, [], {
					setupMock: {
						value: function setupMock___n4() {
							N4Injector.of(TestSubject).create(TestSubject);
						}
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'InjectionTest',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.DIAncestorTest.InjectionTest',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4Method({
								name: 'setupMock',
								isStatic: false,
								jsFunction: instanceProto['setupMock'],
								annotations: []
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
//# sourceMappingURL=DIAncestorTest.map
