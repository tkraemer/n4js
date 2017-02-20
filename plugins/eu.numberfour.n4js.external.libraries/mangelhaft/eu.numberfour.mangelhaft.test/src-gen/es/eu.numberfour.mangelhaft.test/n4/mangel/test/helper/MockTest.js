(function(System) {
	'use strict';
	System.register([], function($n4Export) {
		var MockTest;
		MockTest = function MockTest() {};
		$n4Export('MockTest', MockTest);
		return {
			setters: [],
			execute: function() {
				$makeClass(MockTest, N4Object, [], {
					beforeAll1: {
						value: function beforeAll1___n4() {
							MockTest.beforeAllCount += 1;
							MockTest.called.push("beforeAll1");
						}
					},
					beforeAll2: {
						value: function beforeAll2___n4() {
							MockTest.beforeAllCount += 1;
							MockTest.called.push("beforeAll2");
						}
					},
					before1: {
						value: function before1___n4() {
							MockTest.beforeCount += 1;
							MockTest.called.push("before1");
						}
					},
					before2: {
						value: function before2___n4() {
							MockTest.beforeCount += 1;
							MockTest.called.push("before2");
						}
					},
					test1: {
						value: function test1___n4() {
							MockTest.testCount += 1;
							MockTest.called.push("test1");
						}
					},
					test2: {
						value: function test2___n4() {
							MockTest.testCount += 1;
							MockTest.called.push("test2");
						}
					},
					after1: {
						value: function after1___n4() {
							MockTest.afterCount += 1;
							MockTest.called.push("after1");
						}
					},
					after2: {
						value: function after2___n4() {
							MockTest.afterCount += 1;
							MockTest.called.push("after2");
						}
					},
					afterAll1: {
						value: function afterAll1___n4() {
							MockTest.afterCount += 1;
							MockTest.called.push("afterAll1");
						}
					},
					afterAll2: {
						value: function afterAll2___n4() {
							MockTest.afterCount += 1;
							MockTest.called.push("afterAll2");
						}
					}
				}, {
					reset: {
						value: function reset___n4() {
							MockTest.beforeAllCount = MockTest.beforeCount = MockTest.afterAllCount = MockTest.afterCount = MockTest.testCount = 0;
							MockTest.called = [];
						}
					},
					beforeAllCount: {
						value: undefined,
						writable: true
					},
					beforeCount: {
						value: undefined,
						writable: true
					},
					afterAllCount: {
						value: undefined,
						writable: true
					},
					afterCount: {
						value: undefined,
						writable: true
					},
					testCount: {
						value: undefined,
						writable: true
					},
					called: {
						value: undefined,
						writable: true
					}
				}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'MockTest',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.MockTest.MockTest',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'beforeAllCount',
								isStatic: true,
								annotations: []
							}),
							new N4DataField({
								name: 'beforeCount',
								isStatic: true,
								annotations: []
							}),
							new N4DataField({
								name: 'afterAllCount',
								isStatic: true,
								annotations: []
							}),
							new N4DataField({
								name: 'afterCount',
								isStatic: true,
								annotations: []
							}),
							new N4DataField({
								name: 'testCount',
								isStatic: true,
								annotations: []
							}),
							new N4DataField({
								name: 'called',
								isStatic: true,
								annotations: []
							}),
							new N4Method({
								name: 'reset',
								isStatic: true,
								jsFunction: staticProto['reset'],
								annotations: []
							}),
							new N4Method({
								name: 'beforeAll1',
								isStatic: false,
								jsFunction: instanceProto['beforeAll1'],
								annotations: [
									new N4Annotation({
										name: 'BeforeAll',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'beforeAll2',
								isStatic: false,
								jsFunction: instanceProto['beforeAll2'],
								annotations: [
									new N4Annotation({
										name: 'BeforeAll',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'before1',
								isStatic: false,
								jsFunction: instanceProto['before1'],
								annotations: [
									new N4Annotation({
										name: 'Before',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'before2',
								isStatic: false,
								jsFunction: instanceProto['before2'],
								annotations: [
									new N4Annotation({
										name: 'Before',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'test1',
								isStatic: false,
								jsFunction: instanceProto['test1'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'test2',
								isStatic: false,
								jsFunction: instanceProto['test2'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'after1',
								isStatic: false,
								jsFunction: instanceProto['after1'],
								annotations: [
									new N4Annotation({
										name: 'After',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'after2',
								isStatic: false,
								jsFunction: instanceProto['after2'],
								annotations: [
									new N4Annotation({
										name: 'After',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'afterAll1',
								isStatic: false,
								jsFunction: instanceProto['afterAll1'],
								annotations: [
									new N4Annotation({
										name: 'AfterAll',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'afterAll2',
								isStatic: false,
								jsFunction: instanceProto['afterAll2'],
								annotations: [
									new N4Annotation({
										name: 'AfterAll',
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
				MockTest.beforeAllCount = 0;
				MockTest.beforeCount = 0;
				MockTest.afterAllCount = 0;
				MockTest.afterCount = 0;
				MockTest.testCount = 0;
				MockTest.called = [];
			}
		};
	});
})(typeof module !== 'undefined' && module.exports ? require('n4js-node/index').System(require, module) : System);
//# sourceMappingURL=MockTest.map
