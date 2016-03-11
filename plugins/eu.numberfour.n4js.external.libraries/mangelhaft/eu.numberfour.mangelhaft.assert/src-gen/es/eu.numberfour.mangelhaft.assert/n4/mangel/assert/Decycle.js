(function(System) {
	'use strict';
	System.register([], function($n4Export) {
		var decycle;
		return {
			setters: [],
			execute: function() {
				decycle = function(object) {
					let objects = [], paths = [];
					return (function derez(value, path) {
						let i, name, nu;
						if (typeof value === 'object' && value !== null && !($instanceof(value, Boolean)) && !($instanceof(value, Date)) && !($instanceof(value, Number)) && !($instanceof(value, RegExp)) && !($instanceof(value, String))) {
							for(i = 0;i < objects.length;i += 1) {
								if (objects[i] === value) {
									return {
										$ref: paths[i]
									};
								}
							}
							objects.push(value);
							paths.push(path);
							if (Object.prototype.toString.call(value) === '[object Array]') {
								nu = [];
								for(i = 0;i < value.length;i += 1) {
									nu[i] = derez(value[i], path + '[' + i + ']');
								}
							} else {
								nu = {};
								for(name in value) {
									if (Object.prototype.hasOwnProperty.call(value, name)) {
										nu[name] = derez(value[name], path + '[' + JSON.stringify(name) + ']');
									}
								}
							}
							return nu;
						}
						return value;
					}(object, '$'));
				};
				$n4Export('decycle', decycle);
			}
		};
	});
})(typeof module !== 'undefined' && module.exports ? require('n4js-node/index').System(require, module) : System);
//# sourceMappingURL=Decycle.map
