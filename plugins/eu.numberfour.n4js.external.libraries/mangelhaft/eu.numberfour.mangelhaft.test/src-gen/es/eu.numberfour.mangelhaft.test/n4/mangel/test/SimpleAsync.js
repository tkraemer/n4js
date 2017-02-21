(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft/n4/mangel/Test'
	], function($n4Export) {
		var async, SimpleAsync;
		SimpleAsync = function SimpleAsync() {};
		$n4Export('SimpleAsync', SimpleAsync);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTest) {
					async = $_import_eu_u002enumberfour_u002emangelhaft_n4_u002fmangel_u002fTest.async;
				}
			],
			execute: function() {
				$makeClass(SimpleAsync, N4Object, [], {
					beforeAll1: {
						value: function beforeAll1___n4() {}
					},
					beforeAll2: {
						value: function beforeAll2___n4() {}
					},
					beforeAll3: {
						value: function beforeAll3___n4() {}
					},
					beforeAll4: {
						value: function beforeAll4___n4() {
							return new Promise(function(resolve, reject) {
								setTimeout.call(this, async(function() {
									resolve("hey bro");
								}, reject), 50);
							});
						}
					},
					afterAll1: {
						value: function afterAll1___n4() {}
					},
					before1: {
						value: function before1___n4() {}
					},
					after1: {
						value: function after1___n4() {}
					},
					test1: {
						value: function test1___n4() {
							return new Promise(function(resolve, reject) {
								setTimeout.call(this, async(function() {
									resolve("party promise resolved");
								}, reject), 500);
							});
						}
					},
					test3: {
						value: function test3___n4() {
							const breakpointHere = 4;
						}
					},
					test4: {
						value: function test4___n4() {
							return new Promise(function(resolve, reject) {
								setTimeout.call(this, async(function() {
									resolve("test js error");
								}, reject), 700);
							});
						}
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'SimpleAsync',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.SimpleAsync.SimpleAsync',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4Method({
								name: 'beforeAll1',
								isStatic: false,
								jsFunction: instanceProto['beforeAll1'],
								annotations: [
									new N4Annotation({
										name: 'BeforeAll',
										details: []
									}),
									new N4Annotation({
										name: 'Description',
										details: [
											'set up tests'
										]
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
									}),
									new N4Annotation({
										name: 'Description',
										details: [
											'set up tests 2'
										]
									})
								]
							}),
							new N4Method({
								name: 'beforeAll3',
								isStatic: false,
								jsFunction: instanceProto['beforeAll3'],
								annotations: [
									new N4Annotation({
										name: 'BeforeAll',
										details: []
									}),
									new N4Annotation({
										name: 'Description',
										details: [
											'set up tests3 '
										]
									})
								]
							}),
							new N4Method({
								name: 'beforeAll4',
								isStatic: false,
								jsFunction: instanceProto['beforeAll4'],
								annotations: [
									new N4Annotation({
										name: 'BeforeAll',
										details: []
									}),
									new N4Annotation({
										name: 'Description',
										details: [
											'long async beforeall'
										]
									}),
									new N4Annotation({
										name: 'Timeout',
										details: [
											'100'
										]
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
									}),
									new N4Annotation({
										name: 'Description',
										details: [
											'cleanup tests'
										]
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
									}),
									new N4Annotation({
										name: 'Description',
										details: [
											'setup every test'
										]
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
									}),
									new N4Annotation({
										name: 'Description',
										details: [
											'cleanup every test'
										]
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
									}),
									new N4Annotation({
										name: 'Description',
										details: [
											'do some stuff'
										]
									})
								]
							}),
							new N4Method({
								name: 'test3',
								isStatic: false,
								jsFunction: instanceProto['test3'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									}),
									new N4Annotation({
										name: 'Description',
										details: [
											'synchronous non assert exception'
										]
									})
								]
							}),
							new N4Method({
								name: 'test4',
								isStatic: false,
								jsFunction: instanceProto['test4'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									}),
									new N4Annotation({
										name: 'Description',
										details: [
											'asynchronous non assert exception'
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
//# sourceMappingURL=SimpleAsync.map
