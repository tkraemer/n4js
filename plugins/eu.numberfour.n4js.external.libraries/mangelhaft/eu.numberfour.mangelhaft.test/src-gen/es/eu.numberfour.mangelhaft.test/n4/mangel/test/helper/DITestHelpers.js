(function(System) {
	'use strict';
	System.register([], function($n4Export) {
		var ITires, Fuel, AviationFuel, StandardDiesel, Gasoline, Air, SolidRubber, RadialPly, BiasPly, Solid, Vehicle, OldificationBinder, NewificationBinder;
		ITires = {};
		$n4Export('ITires', ITires);
		Fuel = function Fuel() {};
		$n4Export('Fuel', Fuel);
		AviationFuel = function AviationFuel() {
			Fuel.prototype.constructor.call(this);
		};
		$n4Export('AviationFuel', AviationFuel);
		StandardDiesel = function StandardDiesel() {
			Fuel.prototype.constructor.call(this);
		};
		$n4Export('StandardDiesel', StandardDiesel);
		Gasoline = function Gasoline() {
			Fuel.prototype.constructor.call(this);
		};
		$n4Export('Gasoline', Gasoline);
		Air = function Air() {};
		$n4Export('Air', Air);
		SolidRubber = function SolidRubber() {};
		$n4Export('SolidRubber', SolidRubber);
		RadialPly = function RadialPly() {
			this.filling = undefined;
			ITires.$fieldInit.call(this, undefined, {
				filling: undefined
			});
		};
		$n4Export('RadialPly', RadialPly);
		BiasPly = function BiasPly() {
			this.filling = undefined;
			ITires.$fieldInit.call(this, undefined, {
				filling: undefined
			});
		};
		$n4Export('BiasPly', BiasPly);
		Solid = function Solid() {
			this.filling = undefined;
			ITires.$fieldInit.call(this, undefined, {
				filling: undefined
			});
		};
		$n4Export('Solid', Solid);
		Vehicle = function Vehicle() {
			this.tires = undefined;
			this.fuel = undefined;
		};
		$n4Export('Vehicle', Vehicle);
		OldificationBinder = function OldificationBinder() {};
		$n4Export('OldificationBinder', OldificationBinder);
		NewificationBinder = function NewificationBinder() {};
		$n4Export('NewificationBinder', NewificationBinder);
		return {
			setters: [],
			execute: function() {
				ITires.$fieldInit = function ITires_fieldInit(spec, mixinExclusion) {};
				ITires.$methods = {};
				$makeInterface(ITires, function(instanceProto, staticProto) {
					var metaClass = new N4Interface({
						name: 'ITires',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.DITestHelpers.ITires',
						n4superType: undefined,
						allImplementedInterfaces: [],
						ownedMembers: [],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				$makeClass(Fuel, N4Object, [], {}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'Fuel',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.DITestHelpers.Fuel',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				$makeClass(AviationFuel, Fuel, [], {}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'AviationFuel',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.DITestHelpers.AviationFuel',
						n4superType: Fuel.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				Object.defineProperty(AviationFuel, '$di', {
					value: {
						superType: Fuel,
						fieldsInjectedTypes: []
					}
				});
				$makeClass(StandardDiesel, Fuel, [], {}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'StandardDiesel',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.DITestHelpers.StandardDiesel',
						n4superType: Fuel.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				Object.defineProperty(StandardDiesel, '$di', {
					value: {
						superType: Fuel,
						fieldsInjectedTypes: []
					}
				});
				$makeClass(Gasoline, Fuel, [], {}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'Gasoline',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.DITestHelpers.Gasoline',
						n4superType: Fuel.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				Object.defineProperty(Gasoline, '$di', {
					value: {
						superType: Fuel,
						fieldsInjectedTypes: []
					}
				});
				$makeClass(Air, N4Object, [], {}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'Air',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.DITestHelpers.Air',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				$makeClass(SolidRubber, N4Object, [], {}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'SolidRubber',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.DITestHelpers.SolidRubber',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				$makeClass(RadialPly, N4Object, [
					ITires
				], {
					filling: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'RadialPly',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.DITestHelpers.RadialPly',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [
							'n4.mangel.test.helper.DITestHelpers.ITires'
						],
						ownedMembers: [
							new N4DataField({
								name: 'filling',
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
						annotations: []
					});
					return metaClass;
				});
				Object.defineProperty(RadialPly, '$di', {
					value: {
						fieldsInjectedTypes: [
							{
								name: 'filling',
								type: Air
							}
						]
					}
				});
				$makeClass(BiasPly, N4Object, [
					ITires
				], {
					filling: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'BiasPly',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.DITestHelpers.BiasPly',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [
							'n4.mangel.test.helper.DITestHelpers.ITires'
						],
						ownedMembers: [
							new N4DataField({
								name: 'filling',
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
						annotations: []
					});
					return metaClass;
				});
				Object.defineProperty(BiasPly, '$di', {
					value: {
						fieldsInjectedTypes: [
							{
								name: 'filling',
								type: Air
							}
						]
					}
				});
				$makeClass(Solid, N4Object, [
					ITires
				], {
					filling: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'Solid',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.DITestHelpers.Solid',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [
							'n4.mangel.test.helper.DITestHelpers.ITires'
						],
						ownedMembers: [
							new N4DataField({
								name: 'filling',
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
						annotations: []
					});
					return metaClass;
				});
				Object.defineProperty(Solid, '$di', {
					value: {
						fieldsInjectedTypes: [
							{
								name: 'filling',
								type: SolidRubber
							}
						]
					}
				});
				$makeClass(Vehicle, N4Object, [], {
					tires: {
						value: undefined,
						writable: true
					},
					fuel: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'Vehicle',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.DITestHelpers.Vehicle',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'tires',
								isStatic: false,
								annotations: [
									new N4Annotation({
										name: 'Inject',
										details: []
									})
								]
							}),
							new N4DataField({
								name: 'fuel',
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
						annotations: []
					});
					return metaClass;
				});
				Object.defineProperty(Vehicle, '$di', {
					value: {
						fieldsInjectedTypes: [
							{
								name: 'tires',
								type: ITires
							},
							{
								name: 'fuel',
								type: Fuel
							}
						]
					}
				});
				$makeClass(OldificationBinder, N4Object, [], {}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'OldificationBinder',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.DITestHelpers.OldificationBinder',
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
									'ITires',
									'BiasPly'
								]
							}),
							new N4Annotation({
								name: 'Bind',
								details: [
									'Fuel',
									'Gasoline'
								]
							})
						]
					});
					return metaClass;
				});
				Object.defineProperty(OldificationBinder, '$di', {
					value: {
						bindings: [
							{
								from: ITires,
								to: BiasPly
							},
							{
								from: Fuel,
								to: Gasoline
							}
						],
						methodBindings: [],
						fieldsInjectedTypes: []
					}
				});
				$makeClass(NewificationBinder, N4Object, [], {}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'NewificationBinder',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.DITestHelpers.NewificationBinder',
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
									'ITires',
									'RadialPly'
								]
							}),
							new N4Annotation({
								name: 'Bind',
								details: [
									'Fuel',
									'StandardDiesel'
								]
							})
						]
					});
					return metaClass;
				});
				Object.defineProperty(NewificationBinder, '$di', {
					value: {
						bindings: [
							{
								from: ITires,
								to: RadialPly
							},
							{
								from: Fuel,
								to: StandardDiesel
							}
						],
						methodBindings: [],
						fieldsInjectedTypes: []
					}
				});
			}
		};
	});
})(typeof module !== 'undefined' && module.exports ? require('n4js-node/index').System(require, module) : System);
//# sourceMappingURL=DITestHelpers.map
