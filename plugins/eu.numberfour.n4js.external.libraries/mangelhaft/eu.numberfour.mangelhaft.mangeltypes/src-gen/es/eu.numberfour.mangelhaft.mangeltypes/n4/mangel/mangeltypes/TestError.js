(function(System) {
	'use strict';
	System.register([], function($n4Export) {
		var TestError;
		TestError = function TestError(message) {
			var err = new Error(message);
			this.message = err.message;
			this.name = this.constructor.n4type.name;
			Object.defineProperty(this, 'stack', { get: function() { return err.stack; }, set: function(value) { err.stack = value; } });
		};
		$n4Export('TestError', TestError);
		return {
			setters: [],
			execute: function() {
				$makeClass(TestError, Error, [], {}, {
					throw: {
						value: function throw___n4(message) {
							throw new TestError(message);
						}
					}
				}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'TestError',
						origin: 'eu.numberfour.mangelhaft.mangeltypes',
						fqn: 'n4.mangel.mangeltypes.TestError.TestError',
						n4superType: undefined,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4Method({
								name: 'throw',
								isStatic: true,
								jsFunction: staticProto['throw'],
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
//# sourceMappingURL=TestError.map
