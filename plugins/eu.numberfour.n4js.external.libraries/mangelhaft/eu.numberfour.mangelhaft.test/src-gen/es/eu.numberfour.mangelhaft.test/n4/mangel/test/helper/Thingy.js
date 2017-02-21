(function(System) {
	'use strict';
	System.register([], function($n4Export) {
		var Privacy, Dum, Thing, Thingy, Stuff;
		Privacy = function Privacy() {};
		$n4Export('Privacy', Privacy);
		Dum = function Dum() {};
		Thing = function Thing(dum) {
			this.dum = undefined;
		};
		Thingy = function Thingy(thing) {
			this.thing = undefined;
			this.thing = thing;
		};
		$n4Export('Thingy', Thingy);
		Stuff = function Stuff() {};
		$n4Export('Stuff', Stuff);
		return {
			setters: [],
			execute: function() {
				$makeClass(Privacy, N4Object, [], {
					someOtherFunction: {
						value: function someOtherFunction___n4() {}
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'Privacy',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.Thingy.Privacy',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4Method({
								name: 'constructor',
								isStatic: false,
								jsFunction: instanceProto['constructor'],
								annotations: []
							}),
							new N4Method({
								name: 'someOtherFunction',
								isStatic: false,
								jsFunction: instanceProto['someOtherFunction'],
								annotations: []
							})
						],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				$makeClass(Dum, N4Object, [], {}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'Dum',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.Thingy.Dum',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				$makeClass(Thing, N4Object, [], {
					dum: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'Thing',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.Thingy.Thing',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'dum',
								isStatic: false,
								annotations: [
									new N4Annotation({
										name: 'Inject',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'constructor',
								isStatic: false,
								jsFunction: instanceProto['constructor'],
								annotations: []
							})
						],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				Object.defineProperty(Thing, '$di', {
					value: {
						fieldsInjectedTypes: [
							{
								name: 'dum',
								type: Dum
							}
						]
					}
				});
				$makeClass(Thingy, N4Object, [], {
					thing: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'Thingy',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.Thingy.Thingy',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'thing',
								isStatic: false,
								annotations: []
							}),
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
				Object.defineProperty(Thingy, '$di', {
					value: {
						injectCtorParams: [
							{
								name: 'thing',
								type: Thing
							}
						],
						fieldsInjectedTypes: []
					}
				});
				$makeClass(Stuff, N4Object, [], {}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'Stuff',
						origin: 'eu.numberfour.mangelhaft.test',
						fqn: 'n4.mangel.test.helper.Thingy.Stuff',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
			}
		};
	});
})(typeof module !== 'undefined' && module.exports ? require('n4js-node/index').System(require, module) : System);
//# sourceMappingURL=Thingy.map
