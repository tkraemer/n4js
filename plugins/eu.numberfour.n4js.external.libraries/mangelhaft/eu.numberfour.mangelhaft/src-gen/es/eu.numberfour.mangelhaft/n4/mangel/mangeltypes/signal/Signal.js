(function(System) {
	'use strict';
	System.register([], function($n4Export) {
		var validateListener, Signal, SignalBinding;
		validateListener = function validateListener(listener, fnName) {
			if (typeof listener !== 'function') {
				throw new Error('listener is a required param of {fn}() and should be a TCallback.'.replace('{fn}', fnName));
			}
		};
		Signal = function Signal() {
			this.memorize = false;
			this.bindings = [];
			this.previousParams = null;
			this.shouldPropagate = true;
			this.active = true;
		};
		$n4Export('Signal', Signal);
		SignalBinding = function SignalBinding(spec) {
			this.listener = spec && 'listener' in spec ? spec.listener : undefined;
			this.context = spec && 'context' in spec ? spec.context : undefined;
			this.signal = spec && 'signal' in spec ? spec.signal : undefined;
			this.isOnce = spec && 'isOnce' in spec ? spec.isOnce : false;
			this.priority = spec && 'priority' in spec ? spec.priority : 0;
			this.active = spec && 'active' in spec ? spec.active : true;
			this.params = spec && 'params' in spec ? spec.params : null;
			if (spec) {}
		};
		return {
			setters: [],
			execute: function() {
				$makeClass(Signal, N4Object, [], {
					registerListener: {
						value: function registerListener___n4(listener, isOnce, listenerContext, priority) {
							priority = priority || 0;
							let prevIndex = this.indexOfListener(listener, listenerContext), binding;
							if (prevIndex !== -1) {
								binding = this.bindings[prevIndex];
								if (binding.isOnce !== isOnce) {
									throw new Error('You cannot add' + (isOnce ? '' : 'Once') + '() then add' + (!isOnce ? '' : 'Once') + '() the same listener without removing the relationship first.');
								}
							} else {
								binding = new SignalBinding({
									signal: this,
									listener: listener,
									isOnce: isOnce,
									context: listenerContext,
									priority: priority
								});
								this.addBinding(binding);
							}
							if (this.memorize && this.previousParams) {
								binding.execute(this.previousParams);
							}
							return binding;
						}
					},
					addBinding: {
						value: function addBinding___n4(binding) {
							let n = this.bindings.length;
							do {
								--n;
							} while(this.bindings[n] && binding.priority <= this.bindings[n].priority);
							this.bindings.splice(n + 1, 0, binding);
						}
					},
					indexOfListener: {
						value: function indexOfListener___n4(listener, context) {
							let n = this.bindings.length, cur;
							while(n--) {
								cur = this.bindings[n];
								if (cur.listener === listener && cur.context === context) {
									return n;
								}
							}
							return -1;
						}
					},
					has: {
						value: function has___n4(listener, context) {
							return this.indexOfListener(listener, context) !== -1;
						}
					},
					add: {
						value: function add___n4(listener, listenerContext, priority) {
							validateListener(listener, 'add');
							return this.registerListener(listener, false, listenerContext, priority);
						}
					},
					addOnce: {
						value: function addOnce___n4(listener, listenerContext, priority) {
							validateListener(listener, 'addOnce');
							return this.registerListener(listener, true, listenerContext, priority);
						}
					},
					remove: {
						value: function remove___n4(listener, context) {
							validateListener(listener, 'remove');
							let i = this.indexOfListener(listener, context);
							if (i !== -1) {
								this.bindings[i].destroy();
								this.bindings.splice(i, 1);
							}
							return listener;
						}
					},
					removeAll: {
						value: function removeAll___n4() {
							let n = this.bindings.length;
							while(n--) {
								this.bindings[n].destroy();
							}
							this.bindings.length = 0;
							return this;
						}
					},
					getNumListeners: {
						value: function getNumListeners___n4() {
							return this.bindings.length;
						}
					},
					halt: {
						value: function halt___n4() {
							this.shouldPropagate = false;
							return this;
						}
					},
					dispatch: {
						value: function dispatch___n4(params) {
							return $spawn(function*() {
								if (!this.active) {
									return;
								}
								let paramsArr = params.slice(), n = this.bindings.length, bindings;
								if (this.memorize) {
									this.previousParams = paramsArr;
								}
								if (!n) {
									return;
								}
								bindings = this.bindings.slice();
								this.shouldPropagate = true;
								for(let executeNext = true;executeNext && n > 0;--n) {
									executeNext = false;
									if (bindings[n - 1] && this.shouldPropagate) {
										let res = (yield Promise.resolve(bindings[n - 1].execute(paramsArr)));
										executeNext = res !== false;
									}
								}
								return true;
							}.apply(this, arguments));
						}
					},
					forget: {
						value: function forget___n4() {
							this.previousParams = null;
							return this;
						}
					},
					dispose: {
						value: function dispose___n4() {
							this.removeAll();
							delete this.bindings;
							delete this.previousParams;
						}
					},
					toString: {
						value: function toString___n4() {
							return '[Signal active:' + this.active + ' numListeners:' + this.getNumListeners() + ']';
						}
					},
					memorize: {
						value: undefined,
						writable: true
					},
					bindings: {
						value: undefined,
						writable: true
					},
					previousParams: {
						value: undefined,
						writable: true
					},
					shouldPropagate: {
						value: undefined,
						writable: true
					},
					active: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'Signal',
						origin: 'eu.numberfour.mangelhaft',
						fqn: 'n4.mangel.mangeltypes.signal.Signal.Signal',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'memorize',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'bindings',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'previousParams',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'shouldPropagate',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'active',
								isStatic: false,
								annotations: []
							}),
							new N4Method({
								name: 'registerListener',
								isStatic: false,
								jsFunction: instanceProto['registerListener'],
								annotations: []
							}),
							new N4Method({
								name: 'addBinding',
								isStatic: false,
								jsFunction: instanceProto['addBinding'],
								annotations: []
							}),
							new N4Method({
								name: 'indexOfListener',
								isStatic: false,
								jsFunction: instanceProto['indexOfListener'],
								annotations: []
							}),
							new N4Method({
								name: 'has',
								isStatic: false,
								jsFunction: instanceProto['has'],
								annotations: []
							}),
							new N4Method({
								name: 'add',
								isStatic: false,
								jsFunction: instanceProto['add'],
								annotations: []
							}),
							new N4Method({
								name: 'addOnce',
								isStatic: false,
								jsFunction: instanceProto['addOnce'],
								annotations: []
							}),
							new N4Method({
								name: 'remove',
								isStatic: false,
								jsFunction: instanceProto['remove'],
								annotations: []
							}),
							new N4Method({
								name: 'removeAll',
								isStatic: false,
								jsFunction: instanceProto['removeAll'],
								annotations: []
							}),
							new N4Method({
								name: 'getNumListeners',
								isStatic: false,
								jsFunction: instanceProto['getNumListeners'],
								annotations: []
							}),
							new N4Method({
								name: 'halt',
								isStatic: false,
								jsFunction: instanceProto['halt'],
								annotations: []
							}),
							new N4Method({
								name: 'dispatch',
								isStatic: false,
								jsFunction: instanceProto['dispatch'],
								annotations: []
							}),
							new N4Method({
								name: 'forget',
								isStatic: false,
								jsFunction: instanceProto['forget'],
								annotations: []
							}),
							new N4Method({
								name: 'dispose',
								isStatic: false,
								jsFunction: instanceProto['dispose'],
								annotations: []
							}),
							new N4Method({
								name: 'toString',
								isStatic: false,
								jsFunction: instanceProto['toString'],
								annotations: []
							})
						],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				$makeClass(SignalBinding, N4Object, [], {
					execute: {
						value: function execute___n4(paramsArr) {
							let handlerReturn, params;
							if (this.active && !!this.listener) {
								params = this.params ? this.params.concat(paramsArr) : paramsArr;
								handlerReturn = this.listener.apply(this.context, params);
								if (this.isOnce) {
									this.detach();
								}
							}
							return handlerReturn;
						}
					},
					detach: {
						value: function detach___n4() {
							return this.isBound() ? this.signal.remove(this.listener, this.context) : null;
						}
					},
					isBound: {
						value: function isBound___n4() {
							return (!!this.signal && !!this.listener);
						}
					},
					destroy: {
						value: function destroy___n4() {
							delete this.signal;
							delete this.listener;
							delete this.context;
						}
					},
					toString: {
						value: function toString___n4() {
							return '[SignalBinding isOnce:' + this.isOnce + ', isBound:' + this.isBound() + ', active:' + this.active + ']';
						}
					},
					listener: {
						value: undefined,
						writable: true
					},
					context: {
						value: undefined,
						writable: true
					},
					signal: {
						value: undefined,
						writable: true
					},
					isOnce: {
						value: undefined,
						writable: true
					},
					priority: {
						value: undefined,
						writable: true
					},
					active: {
						value: undefined,
						writable: true
					},
					params: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'SignalBinding',
						origin: 'eu.numberfour.mangelhaft',
						fqn: 'n4.mangel.mangeltypes.signal.Signal.SignalBinding',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [],
						ownedMembers: [
							new N4DataField({
								name: 'listener',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'context',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'signal',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'isOnce',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'priority',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'active',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'params',
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
								name: 'execute',
								isStatic: false,
								jsFunction: instanceProto['execute'],
								annotations: []
							}),
							new N4Method({
								name: 'detach',
								isStatic: false,
								jsFunction: instanceProto['detach'],
								annotations: []
							}),
							new N4Method({
								name: 'isBound',
								isStatic: false,
								jsFunction: instanceProto['isBound'],
								annotations: []
							}),
							new N4Method({
								name: 'destroy',
								isStatic: false,
								jsFunction: instanceProto['destroy'],
								annotations: []
							}),
							new N4Method({
								name: 'toString',
								isStatic: false,
								jsFunction: instanceProto['toString'],
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
//# sourceMappingURL=Signal.map
