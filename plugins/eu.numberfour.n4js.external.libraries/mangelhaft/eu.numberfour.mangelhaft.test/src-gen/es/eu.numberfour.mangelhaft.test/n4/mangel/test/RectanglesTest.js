(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft.assert/n4/mangel/assert/Assert'
	], function($n4Export) {
		var Assert, Rectangle, RectanglesTest;
		Rectangle = function Rectangle(length, width) {
			this.length = 0;
			this.width = 0;
			this.area = 0;
			this.disposed = false;
			this.length = length;
			this.width = width;
			this.area = length * width;
		};
		RectanglesTest = function RectanglesTest() {
			this.length = undefined;
			this.width = undefined;
			this.area = undefined;
			this.currentRect = undefined;
			this.icons = undefined;
			this.environment = undefined;
		};
		$n4Export('RectanglesTest', RectanglesTest);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssert) {
					Assert = $_import_eu_u002enumberfour_u002emangelhaft_u002eassert_n4_u002fmangel_u002fassert_u002fAssert.Assert;
				}
			],
			execute: function() {
				$makeClass(Rectangle, N4Object, [], {
					dispose: {
						value: function dispose___n4() {
							return $spawn(function*() {
								this.disposed = true;
							}.apply(this, arguments));
						}
					},
					length: {
						value: undefined,
						writable: true
					},
					width: {
						value: undefined,
						writable: true
					},
					area: {
						value: undefined,
						writable: true
					},
					disposed: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'Rectangle',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.RectanglesTest.Rectangle',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'length',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'width',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'area',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'disposed',
								isStatic: false,
								annotations: []
							}),
							new N4Method({
								name: 'constructor',
								isStatic: false,
								jsFunction: instanceProto['constructor'],
								annotations: []
							}),
							new N4Method({
								name: 'dispose',
								isStatic: false,
								jsFunction: instanceProto['dispose'],
								annotations: []
							})
						],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				$makeClass(RectanglesTest, N4Object, [], {
					getEnvironment: {
						value: function getEnvironment___n4() {
							this.environment = {};
						}
					},
					loadIcons: {
						value: function loadIcons___n4() {
							this.icons = [
								"icon1",
								"icon2",
								"icon3"
							];
						}
					},
					releaseEnvironment: {
						value: function releaseEnvironment___n4() {
							this.environment = null;
						}
					},
					freeIcons: {
						value: function freeIcons___n4() {
							this.icons = null;
						}
					},
					createRectangleInstanceForTest: {
						value: function createRectangleInstanceForTest___n4() {
							Assert.isNullOrUndefined(this.currentRect, "currentRect should be reset in @After");
							this.currentRect = new Rectangle(this.length, this.width);
							this.area = this.length * this.width;
						}
					},
					disposeTestRectangleIfNeeded: {
						value: function disposeTestRectangleIfNeeded___n4() {
							return $spawn(function*() {
								if (!this.currentRect.disposed) {
									(yield this.currentRect.dispose());
								}
								this.currentRect = null;
							}.apply(this, arguments));
						}
					},
					areaIsSet: {
						value: function areaIsSet___n4() {
							Assert.isTrue(this.currentRect.area != 0, "rectangle's area has been set");
						}
					},
					areaIsSetCorrectly: {
						value: function areaIsSetCorrectly___n4() {
							Assert.strictEqual(this.currentRect.area, this.area, "rectangle's area has been set Correctly");
						}
					},
					disposeMethodShouldWork: {
						value: function disposeMethodShouldWork___n4() {
							return $spawn(function*() {
								Assert.isFalse(this.currentRect.disposed, "initial rectangle not in disposed state");
								(yield this.currentRect.dispose());
								Assert.isTrue(this.currentRect.disposed, "dispose function has been called");
							}.apply(this, arguments));
						}
					},
					iconsLoaded: {
						value: function iconsLoaded___n4() {
							Assert.deepEqual(this.icons, [
								"icon1",
								"icon2",
								"icon3"
							], "icons equivalent to expected");
						}
					},
					length: {
						value: undefined,
						writable: true
					},
					width: {
						value: undefined,
						writable: true
					},
					area: {
						value: undefined,
						writable: true
					},
					currentRect: {
						value: undefined,
						writable: true
					},
					icons: {
						value: undefined,
						writable: true
					},
					environment: {
						value: undefined,
						writable: true
					}
				}, {
					getSomeParameters: {
						value: function getSomeParameters___n4() {
							return [
								[
									4,
									5
								],
								[
									3,
									2
								],
								[
									6000,
									8
								]
							];
						}
					}
				}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'RectanglesTest',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.RectanglesTest.RectanglesTest',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4Method({
								name: 'getSomeParameters',
								isStatic: true,
								jsFunction: staticProto['getSomeParameters'],
								annotations: [
									new N4Annotation({
										name: 'Parameters',
										details: [
											'{index}: length: {0}, width: {1}'
										]
									})
								]
							}),
							new N4DataField({
								name: 'length',
								isStatic: false,
								annotations: [
									new N4Annotation({
										name: 'Parameter',
										details: []
									})
								]
							}),
							new N4DataField({
								name: 'width',
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
							new N4DataField({
								name: 'area',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'currentRect',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'icons',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'environment',
								isStatic: false,
								annotations: []
							}),
							new N4Method({
								name: 'getEnvironment',
								isStatic: false,
								jsFunction: instanceProto['getEnvironment'],
								annotations: [
									new N4Annotation({
										name: 'BeforeAll',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'loadIcons',
								isStatic: false,
								jsFunction: instanceProto['loadIcons'],
								annotations: [
									new N4Annotation({
										name: 'BeforeAll',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'releaseEnvironment',
								isStatic: false,
								jsFunction: instanceProto['releaseEnvironment'],
								annotations: [
									new N4Annotation({
										name: 'AfterAll',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'freeIcons',
								isStatic: false,
								jsFunction: instanceProto['freeIcons'],
								annotations: [
									new N4Annotation({
										name: 'AfterAll',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'createRectangleInstanceForTest',
								isStatic: false,
								jsFunction: instanceProto['createRectangleInstanceForTest'],
								annotations: [
									new N4Annotation({
										name: 'Before',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'disposeTestRectangleIfNeeded',
								isStatic: false,
								jsFunction: instanceProto['disposeTestRectangleIfNeeded'],
								annotations: [
									new N4Annotation({
										name: 'After',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'areaIsSet',
								isStatic: false,
								jsFunction: instanceProto['areaIsSet'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'areaIsSetCorrectly',
								isStatic: false,
								jsFunction: instanceProto['areaIsSetCorrectly'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'disposeMethodShouldWork',
								isStatic: false,
								jsFunction: instanceProto['disposeMethodShouldWork'],
								annotations: [
									new N4Annotation({
										name: 'Test',
										details: []
									}),
									new N4Annotation({
										name: 'Description',
										details: [
											'Dispose method should be called and object disposed'
										]
									})
								]
							}),
							new N4Method({
								name: 'iconsLoaded',
								isStatic: false,
								jsFunction: instanceProto['iconsLoaded'],
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
//# sourceMappingURL=RectanglesTest.map
