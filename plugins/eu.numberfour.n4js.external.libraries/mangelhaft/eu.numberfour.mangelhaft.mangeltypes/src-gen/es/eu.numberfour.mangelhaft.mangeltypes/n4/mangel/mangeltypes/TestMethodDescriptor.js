(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft.mangeltypes/n4/mangel/mangeltypes/Nvp'
	], function($n4Export) {
		var Nvp, TypedNvp, TestMethodDescriptor;
		TypedNvp = function TypedNvp(spec) {
			Nvp.prototype.constructor.call(this, spec);
			this.type = spec && 'type' in spec ? spec.type : undefined;
			if (spec) {}
		};
		TestMethodDescriptor = function TestMethodDescriptor(spec) {
			TypedNvp.prototype.constructor.call(this, spec);
			this.timeout = spec && 'timeout' in spec ? spec.timeout : 60 * 1000;
			this.description = spec && 'description' in spec ? spec.description : undefined;
			this.ignore = spec && 'ignore' in spec ? spec.ignore : undefined;
			this.fixme = spec && 'fixme' in spec ? spec.fixme : undefined;
			this.fixmeReason = spec && 'fixmeReason' in spec ? spec.fixmeReason : undefined;
			this.ignoreReason = spec && 'ignoreReason' in spec ? spec.ignoreReason : undefined;
			if (spec) {}
		};
		$n4Export('TestMethodDescriptor', TestMethodDescriptor);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fNvp) {
					Nvp = $_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fNvp.Nvp;
				}
			],
			execute: function() {
				$makeClass(TypedNvp, Nvp, [], {
					type: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'TypedNvp',
						origin: 'eu.numberfour.mangelhaft.mangeltypes',
						fqn: 'n4.mangel.mangeltypes.TestMethodDescriptor.TypedNvp',
						n4superType: Nvp.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'type',
								isStatic: false,
								annotations: []
							})
						],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				Object.defineProperty(TypedNvp, '$di', {
					value: {
						superType: Nvp,
						fieldsInjectedTypes: []
					}
				});
				$makeClass(TestMethodDescriptor, TypedNvp, [], {
					timeout: {
						value: undefined,
						writable: true
					},
					description: {
						value: undefined,
						writable: true
					},
					ignore: {
						value: undefined,
						writable: true
					},
					fixme: {
						value: undefined,
						writable: true
					},
					fixmeReason: {
						value: undefined,
						writable: true
					},
					ignoreReason: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'TestMethodDescriptor',
						origin: 'eu.numberfour.mangelhaft.mangeltypes',
						fqn: 'n4.mangel.mangeltypes.TestMethodDescriptor.TestMethodDescriptor',
						n4superType: TypedNvp.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'timeout',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'description',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'ignore',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'fixme',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'fixmeReason',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'ignoreReason',
								isStatic: false,
								annotations: []
							})
						],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				Object.defineProperty(TestMethodDescriptor, '$di', {
					value: {
						superType: TypedNvp,
						fieldsInjectedTypes: []
					}
				});
			}
		};
	});
})(typeof module !== 'undefined' && module.exports ? require('n4js-node/index').System(require, module) : System);
//# sourceMappingURL=TestMethodDescriptor.map
