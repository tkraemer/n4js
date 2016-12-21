(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft.assert/n4/mangel/assert/Assert',
		'eu.numberfour.mangelhaft.assert/n4/mangel/precondition/Precondition'
	], function($n4Export) {
		var Assert, Precondition, Simple;
		Simple = function Simple() {
			this.location = undefined;
			this.drink = undefined;
		};
		$n4Export('Simple', Simple);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssert) {
					Assert = $_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssert.Assert;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fprecondition_u002fPrecondition) {
					Precondition = $_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fprecondition_u002fPrecondition.Precondition;
				}
			],
			execute: function() {
				$makeClass(Simple, N4Object, [], {
					testParameterParty: {
						value: function testParameterParty___n4() {
							if (this.location === "party") {
								Assert.strictEqual(this.drink, "beer");
							} else {
								Precondition.fail("This parameterized test does not cover party");
							}
						}
					},
					testParameterWork: {
						value: function testParameterWork___n4() {
							if (this.location === "work") {
								Assert.strictEqual(this.drink, "coffee");
							} else {
								Precondition.fail("This parameterized test does not cover work");
							}
						}
					},
					beforeAllStuff: {
						value: function beforeAllStuff___n4() {}
					},
					beforeStuff: {
						value: function beforeStuff___n4() {}
					},
					testSomeStuff: {
						value: function testSomeStuff___n4() {
							Assert.isTrue(true, "someStuff");
						}
					},
					testSomeOtherStuff: {
						value: function testSomeOtherStuff___n4() {
							Assert.isTrue(true, "some other Stuff");
						}
					},
					testSomeOtherStuff2: {
						value: function testSomeOtherStuff2___n4() {
							Assert.isTrue(true, "some other Stuff");
						}
					},
					testSomeOtherStuff3: {
						value: function testSomeOtherStuff3___n4() {
							Assert.fail("nah mate");
						}
					},
					location: {
						value: undefined,
						writable: true
					},
					drink: {
						value: undefined,
						writable: true
					}
				}, {
					foo: {
						value: function foo___n4() {
							return [
								[
									"party",
									"beer"
								],
								[
									"work",
									"coffee"
								]
							];
						}
					}
				}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'Simple',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.Simple.Simple',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4Method({
								name: 'foo',
								isStatic: true,
								jsFunction: staticProto['foo'],
								annotations: [
									new N4Annotation({
										name: 'Parameters',
										details: []
									})
								]
							}),
							new N4DataField({
								name: 'location',
								isStatic: false,
								annotations: [
									new N4Annotation({
										name: 'Parameter',
										details: []
									})
								]
							}),
							new N4DataField({
								name: 'drink',
								isStatic: false,
								annotations: [
									new N4Annotation({
										name: 'Parameter',
										details: [
											'1'
										]
									})
								]
							}),
							new N4Method({
								name: 'testParameterParty',
								isStatic: false,
								jsFunction: instanceProto['testParameterParty'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'testParameterWork',
								isStatic: false,
								jsFunction: instanceProto['testParameterWork'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'beforeAllStuff',
								isStatic: false,
								jsFunction: instanceProto['beforeAllStuff'],
								annotations: [
									new N4Annotation({
										name: 'BeforeAll',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'beforeStuff',
								isStatic: false,
								jsFunction: instanceProto['beforeStuff'],
								annotations: [
									new N4Annotation({
										name: 'Before',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'testSomeStuff',
								isStatic: false,
								jsFunction: instanceProto['testSomeStuff'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'testSomeOtherStuff',
								isStatic: false,
								jsFunction: instanceProto['testSomeOtherStuff'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'testSomeOtherStuff2',
								isStatic: false,
								jsFunction: instanceProto['testSomeOtherStuff2'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									}),
									new N4Annotation({
										name: 'Ignore',
										details: [
											'just dont do it'
										]
									})
								]
							}),
							new N4Method({
								name: 'testSomeOtherStuff3',
								isStatic: false,
								jsFunction: instanceProto['testSomeOtherStuff3'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									}),
									new N4Annotation({
										name: 'Fixme',
										details: [
											'I dare you to try'
										]
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
//# sourceMappingURL=Simple.map
