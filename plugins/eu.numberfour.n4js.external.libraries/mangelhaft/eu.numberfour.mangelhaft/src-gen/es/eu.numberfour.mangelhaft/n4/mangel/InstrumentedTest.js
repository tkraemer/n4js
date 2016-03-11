(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft.mangeltypes/n4/mangel/mangeltypes/IInstrumentedTest',
		'eu.numberfour.mangelhaft.mangeltypes/n4/mangel/mangeltypes/TestFunctionType',
		'eu.numberfour.mangelhaft.mangeltypes/n4/mangel/mangeltypes/TestMethodDescriptor'
	], function($n4Export) {
		var IInstrumentedTest, TestFunctionType, TestMethodDescriptor, getAllPropertyNames, InstrumentedTest;
		getAllPropertyNames = function getAllPropertyNames(objProt, propNames) {
			propNames = propNames || new Map();
			let names = Object.getOwnPropertyNames(objProt);
			names.forEach(function(name) {
				propNames.set(name, true);
			});
			return propNames;
		};
		InstrumentedTest = function InstrumentedTest() {
			this.name = undefined;
			this.tests = [];
			this.beforeAlls = [];
			this.afterAlls = [];
			this.befores = [];
			this.afters = [];
			this.testObject = undefined;
			this.fqn = "";
			this.parent = null;
			this.child = null;
			this.error = undefined;
			this.classIgnoreAnnotation = undefined;
			IInstrumentedTest.$fieldInit(this, undefined, {
				name: undefined,
				tests: undefined,
				beforeAlls: undefined,
				afterAlls: undefined,
				befores: undefined,
				afters: undefined,
				testObject: undefined,
				fqn: undefined,
				parent: undefined,
				child: undefined,
				error: undefined,
				classIgnoreAnnotation: undefined
			});
		};
		$n4Export('InstrumentedTest', InstrumentedTest);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fIInstrumentedTest) {
					IInstrumentedTest = $_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fIInstrumentedTest.IInstrumentedTest;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fTestFunctionType) {
					TestFunctionType = $_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fTestFunctionType.TestFunctionType;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fTestMethodDescriptor) {
					TestMethodDescriptor = $_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fTestMethodDescriptor.TestMethodDescriptor;
				}
			],
			execute: function() {
				$makeClass(InstrumentedTest, Object, [
					IInstrumentedTest
				], {
					filterTests: {
						value: function filterTests___n4(testNames) {
							this.tests = this.tests.filter(function(test) {
								return testNames.indexOf(test.name) !== -1;
							});
						}
					},
					getTestMethodDescriptors: {
						value: function getTestMethodDescriptors___n4(meths, tftype) {
							let that = this;
							return meths.map(function(methodDescriptor) {
								let desc = methodDescriptor.anyAnnotation("Description"), fixmeAnnotation = methodDescriptor.anyAnnotation("Fixme"), ignoreAnnotation = that.classIgnoreAnnotation ? that.classIgnoreAnnotation : methodDescriptor.anyAnnotation("Ignore"), timeoutAnnotation = methodDescriptor.anyAnnotation("Timeout"), timeout = timeoutAnnotation && timeoutAnnotation.details ? parseInt(timeoutAnnotation.details.pop()) : 60 * 1000, name, description, details = desc ? desc.details : [], ignoreReason = ignoreAnnotation ? ignoreAnnotation.details.join(" ") : "", fixmeReason = fixmeAnnotation ? fixmeAnnotation.details.join(" ") : "";
								;
								description = details.length ? details.join(" ") : "";
								name = methodDescriptor.name;
								return new TestMethodDescriptor({
									timeout: timeout,
									description: description,
									ignore: !!ignoreAnnotation,
									ignoreReason: ignoreReason,
									fixme: !!fixmeAnnotation,
									fixmeReason: fixmeReason,
									name: name,
									value: methodDescriptor.jsFunction,
									type: tftype
								});
							});
						}
					},
					setTestObject: {
						value: function setTestObject___n4(test) {
							this.testObject = test;
							return this;
						}
					},
					setError: {
						value: function setError___n4(error) {
							this.error = error;
							return this;
						}
					},
					load: {
						value: function load___n4(testClass, info) {
							let that = this, props = getAllPropertyNames(testClass).keys(), parentClass = Object.getPrototypeOf(testClass);
							;
							this.classIgnoreAnnotation = testClass.n4type.allAnnotations("Ignore")[0];
							this.beforeAlls = this.getTestMethodDescriptors(testClass.n4type.methodsWithAnnotation("BeforeAll", true, false, false), TestFunctionType.BEFORE_ALL);
							this.afterAlls = this.getTestMethodDescriptors(testClass.n4type.methodsWithAnnotation("AfterAll", true, false, false), TestFunctionType.AFTER_ALL);
							this.befores = this.getTestMethodDescriptors(testClass.n4type.methodsWithAnnotation("Before", true, false, false), TestFunctionType.BEFORE_TEST);
							this.afters = this.getTestMethodDescriptors(testClass.n4type.methodsWithAnnotation("After", true, false, false), TestFunctionType.AFTER_TEST);
							this.tests = this.getTestMethodDescriptors(testClass.n4type.methodsWithAnnotation("Test", true, true, false), TestFunctionType.TEST);
							if (info) {
								this.fqn = info.fqn;
							}
							this.fqn = this.fqn || testClass.n4type.fqn;
							this.name = this.fqn;
							if (info && info.testMethods && info.testMethods.length) {
								this.tests = this.tests.filter(function(test) {
									return info.testMethods.indexOf(test.name) !== -1;
								});
							}
							if (parentClass !== Object) {
								this.parent = new InstrumentedTest().load(parentClass);
								this.parent.child = this;
							}
							return this;
						}
					},
					name: {
						value: undefined,
						writable: true
					},
					tests: {
						value: undefined,
						writable: true
					},
					beforeAlls: {
						value: undefined,
						writable: true
					},
					afterAlls: {
						value: undefined,
						writable: true
					},
					befores: {
						value: undefined,
						writable: true
					},
					afters: {
						value: undefined,
						writable: true
					},
					testObject: {
						value: undefined,
						writable: true
					},
					fqn: {
						value: undefined,
						writable: true
					},
					parent: {
						value: undefined,
						writable: true
					},
					child: {
						value: undefined,
						writable: true
					},
					error: {
						value: undefined,
						writable: true
					},
					classIgnoreAnnotation: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'InstrumentedTest',
						origin: 'eu.numberfour.mangelhaft',
						fqn: 'n4.mangel.InstrumentedTest.InstrumentedTest',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [
							'n4.mangel.mangeltypes.IInstrumentedTest.IInstrumentedTest'
						],
						ownedMembers: [
							new N4DataField({
								name: 'name',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'tests',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'beforeAlls',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'afterAlls',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'befores',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'afters',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'testObject',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'fqn',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'parent',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'child',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'error',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'classIgnoreAnnotation',
								isStatic: false,
								annotations: []
							}),
							new N4Method({
								name: 'filterTests',
								isStatic: false,
								jsFunction: instanceProto['filterTests'],
								annotations: []
							}),
							new N4Method({
								name: 'getTestMethodDescriptors',
								isStatic: false,
								jsFunction: instanceProto['getTestMethodDescriptors'],
								annotations: []
							}),
							new N4Method({
								name: 'setTestObject',
								isStatic: false,
								jsFunction: instanceProto['setTestObject'],
								annotations: []
							}),
							new N4Method({
								name: 'setError',
								isStatic: false,
								jsFunction: instanceProto['setError'],
								annotations: []
							}),
							new N4Method({
								name: 'load',
								isStatic: false,
								jsFunction: instanceProto['load'],
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
//# sourceMappingURL=InstrumentedTest.map
