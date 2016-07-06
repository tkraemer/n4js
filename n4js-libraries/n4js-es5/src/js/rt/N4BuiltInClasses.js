/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   NumberFour AG - Initial API and implementation
 */
/*eslint-disable no-proto, new-cap */
/*global $makeClass */

(function (global) {
    "use strict";

    /**
     * Similar to {@link $makeClass} without n4type meta class.
     *
     * @param ctor - The constructor function
     * @param superCtor - The constructor function of the super class
     * @param instanceMethods - An object holding the methods for the class instance and mixed in methods
     * @param staticMethods - An object holding the descriptors for the class static methods
     */
    function $makeN4BuiltInClass(ctor, superCtor, instanceMethods, staticMethods) {
        if (typeof superCtor === "function") {
            ctor.__proto__ = superCtor;
        }

        if (superCtor === Error) {
            instanceMethods.stack = { get: function() { return this.$__n4err.stack; } };
            instanceMethods.message = { get: function() { return this.$__n4err.message; } };
            instanceMethods.name = { get: function() { return ctor.n4type.name; } };
        }

        Object.defineProperties(ctor, staticMethods);

        var proto = Object.create(superCtor.prototype, instanceMethods);
        Object.defineProperty(proto, "constructor", { value: ctor });

        ctor.prototype = proto;
    }

    /**
     * Create meta information for N4BuiltInClasses i.e.
     * - bind n4Type property of ctor to instance of (meta) N4Class
     * - bind ownedMembers (meta) N4Class to that instance
     *
     * @param ctor - The constructor function of N4BuiltInClass that will be enriched with meta info
     * @param n4type - The instance of N4Class describing meta info of given N4BuiltInClass (specified by provided ctor)
     */
    function $createMetaInfo(ctor, metaClass) {
        //bind n4Type property metaclass instance
        Object.defineProperty(ctor, 'n4type', {
            value: metaClass,
            enumerable: false
        });

        //bind meta class instance members to that instance (so member->owner)
        //(owner->member relation is set when creating instance of the meta class)
        metaClass.ownedMembers.forEach(function (m) {
            Object.defineProperty(m, 'owner', {
                value: metaClass,
                enumerable: false
            });
        });

        //bind owned N4Methods (of the meta class) to Functions (their constructors) and viceVersa
        metaClass.ownedMembers.forEach(function (m) {
            if (m instanceof N4Method) {
                Object.defineProperty(m, 'jsFunction', {
                    value: m.constructor,
                    enumerable: false
                });
                m.constructor.n4member = m;
            }
        });
    }

    function defineTargetProp(annotation) {
        Object.defineProperty(annotation, "target", { value: this, enumerable: false });
    }
    function setTargetOfAnnotations(target) {
        if (target.annotations) {
            target.annotations.forEach(defineTargetProp, target);
        }
    }

    // ===== define functions used later as ctors of N4BuiltInClasses

    var N4Object = function N4Object(spec) {
        Object.prototype.constructor.call(this, spec);
        // defined in types model, added by $createMetaInfo
        /* public static N4Class get n4type() { return null; } */
    };

    var N4Element = function N4Element(spec) {
        Object.prototype.constructor.call(this, spec);
        this.origin = spec.origin;
        this.annotations = spec.annotations || [];
        setTargetOfAnnotations(this);
    };

    var N4NamedElement = function N4NamedElement(spec) {
        N4Element.prototype.constructor.call(this, spec);
        this.name = spec.name;
    };

    var N4Type = function N4Type(spec) {
        N4NamedElement.prototype.constructor.call(this, spec);
        this.fqn = spec.fqn;
    };

    var N4Classifier = function N4Classifier(spec) {
        N4Type.prototype.constructor.call(this, spec);
        this.n4superType = spec.n4superType;
        this.allImplementedInterfaces = spec.allImplementedInterfaces;
        this.annotations = spec.annotations;
        this.ownedMembers = spec.ownedMembers || [];
        this.consumedMembers = spec.consumedMembers || []; //we know it is empty array

        this.ownedMembers.forEach(function(m) {
            Object.defineProperty(m, 'owner', { value: this, enumerable: false });
            setTargetOfAnnotations(m);
        }, this);
    };

    var N4Class = function N4Class(spec) {
        N4Classifier.prototype.constructor.call(this, spec);
    };

    var N4Interface = function N4Interface(spec) {
        N4Classifier.prototype.constructor.call(this, spec);
    };

    var N4Member = function N4Member(spec) {
        N4NamedElement.prototype.constructor.call(this, spec);
        this.owner = spec.owner;
        this.isStatic = spec.isStatic || false;
    };

    var N4Method = function N4Method(spec) {
        N4Member.prototype.constructor.call(this, spec);
        this.jsFunction = spec.jsFunction;
    };

    var N4Field = function N4Field(spec) {
        N4Member.prototype.constructor.call(this, spec);
    };

    var N4DataField = function N4DataField(spec) {
        N4Member.prototype.constructor.call(this, spec);
    };

    var N4Accessor = function N4Accessor(spec) {
        N4Member.prototype.constructor.call(this, spec);
        this.getter = spec.getter;
    };

    var N4EnumType = function N4EnumType(spec) {
        N4Type.prototype.constructor.call(this, spec);
    };

    var N4Enum = function N4Enum(spec) {
        Object.prototype.constructor.call(this, spec);
    };

    var N4StringBasedEnumType = function N4StringBasedEnumType(spec) {
        N4Type.prototype.constructor.call(this, spec);
    };

    var N4StringBasedEnum = function N4StringBasedEnum(spec) {
        Object.prototype.constructor.call(this, spec);
    };

    var N4Annotation = function N4Annotation(spec) {
        Object.prototype.constructor.call(this, spec);
        this.name = spec.name;
        this.details = spec.details;
        this.target = spec.target;
    };

    var N4Injector = function N4Injector(spec) {
        Object.prototype.constructor.call(this, spec);
        this.injectorFqn = spec.injectorFqn;
        this.injectorName = spec.injectorName;
        this.singletons = spec.singletons;
        this.implicitBindings = spec.implicitBindings;
        this.definedBindings = spec.definedBindings;
        this.definedSingletonBindingFqns = spec.definedSingletonBindingFqns;
        this.parentInjector = spec.parentInjector;
    };

    //used only internally by N4Injector
    var DataMap = function DataMap(spec) {
        Object.prototype.constructor.call(this, spec);
        this.keys = [];
        this.values = Object.create(null);
    };

    var N4Provider = function N4Provider(spec) {
        Object.prototype.constructor.call(this, spec);
    };

    var DIConfigurationError = function DIConfigurationError(spec) {
        this.$__n4err = new Error(spec);
    };

    var DIUnsatisfiedBindingError = function DIUnsatisfiedBindingError(unsatisfiedBinding, spec) {
        DIConfigurationError.prototype.constructor.call(this, spec);
        this.binding = unsatisfiedBinding;
    };

    var N4ApiNotImplementedError = function N4ApiNotImplementedError(spec) {
        this.$__n4err = new Error(spec);
    };

    // ===== make N4BuiltInClasses (transform ctor functions to work as Classes)

    $makeN4BuiltInClass(N4Object, Object, {}, {});

    $makeN4BuiltInClass(N4Element, Object, {
        hasAnnotation: {
            value: function
            hasAnnotation(name) {
                return this.annotations.some(function (a) {
                    return a.name === name;
                });
            }
        },
        anyAnnotation: {
            value: function
            anyAnnotation(name) {
                for (var i = this.annotations.length - 1; i >= 0; i--) {
                    var a = this.annotations[i];
                    if (a.name === name) {
                        return a;
                    }
                }
                return null;
            }
        },
        allAnnotations: {
            value: function
            allAnnotations(name) {
                return this.annotations.filter(function (a) {
                    return a.name === name;
                });
            }
        }
    }, {});

    $makeN4BuiltInClass(N4NamedElement, N4Element, {}, {});

    $makeN4BuiltInClass(N4Type, N4NamedElement, {
        isClass: {
            get: function
            getIsClass() {
                return false;
            }
        },
       isInterface: {
            get: function
            getIsInterface() {
                return false;
            }
        },
        isEnum: {
            get: function
            getIsEnum() {
                return false;
            }
        }
    }, {
        of: {
            value: function
            of(n4object) {
                return n4object.constructor.n4type;
            }
        }
    });

    $makeN4BuiltInClass(N4Classifier, N4Type, {
        members: {
            value: function
            members(consumed, inherited, _static) {
                var arr = [];
                var self = this;
                if (_static === true) {
                    arr = arr.concat(this.ownedMembers.filter(function (m) {
                        return m.isStatic;
                    }));
                } else {
                    arr = arr.concat(this.ownedMembers);
                }

                if (consumed === true) {
                    arr = arr.concat(this.consumedMembers);
                }

                if (inherited === true) {
                    if (typeof this.n4superType === 'undefined') {
                        return arr;
                    }
                    var tmp = this.n4superType.members(consumed, inherited, _static);
                    arr = arr.concat(tmp);
                }

                return arr;
            }
        },
        membersWithAnnotation: {
            value: function
            membersWithAnnotation(name, consumed, inherited, _static) {
                var arr = [];
                var self = this;
                if (_static === true) {
                    arr = arr.concat(this.ownedMembers.filter(function (m) {
                        return m.isStatic;
                    }));
                } else {
                    arr = arr.concat(this.ownedMembers);
                }

                if (consumed === true) {
                    arr = arr.concat(this.consumedMembers);
                }

                if (inherited === true) {
                    if (typeof this.n4superType === 'undefined') {
                        return arr;
                    }
                    var tmp = this.n4superType.membersWithAnnotation(name, consumed, inherited, _static);
                    arr = arr.concat(tmp);
                }

                return arr.filter(function (m) {
                    return m.hasAnnotation(name);
                });
            }
        },
        dataFields: {
            value: function
            dataFields(consumed, inherited, _static) {
                var arr = [];
                var self = this;
                var tmp = [];
                if (_static === true) {
                    this.ownedMembers.forEach(function (m) {
                        if (m instanceof N4DataField && m.isStatic) {
                            tmp.push(m);
                        }
                    });
                } else {
                    this.ownedMembers.forEach(function (m) {
                        if (m instanceof N4DataField) {
                            tmp.push(m);
                        }
                    });
                }
                arr = arr.concat(tmp);
                tmp = [];

                if (consumed === true) {
                    this.consumedMembers.forEach(function (m) {
                        if (m instanceof N4DataField && m.isStatic) {
                            tmp.push(m);
                        }
                    });
                    arr = arr.concat(tmp);
                    tmp = [];
                }

                if (inherited === true) {
                    if (typeof this.n4superType === 'undefined') {
                        return arr;
                    }
                    var tmp2 = this.n4superType.dataFields(consumed, inherited, _static);
                    arr = arr.concat(tmp2);
                }

                return arr;
            }
        },
        dataFieldsWithAnnotation: {
            value: function
            dataFieldsWithAnnotation(name, consumed, inherited, _static) {
                var arr = [];
                var self = this;
                var tmp = [];
                if (_static === true) {
                    this.ownedMembers.forEach(function (m) {
                        if (m instanceof N4DataField && m.isStatic) {
                            tmp.push(m);
                        }
                    });
                } else {
                    this.ownedMembers.forEach(function (m) {
                        if (m instanceof N4DataField) {
                            tmp.push(m);
                        }
                    });
                }
                arr = arr.concat(tmp);
                tmp = [];

                if (consumed === true) {
                    this.consumedMembers.forEach(function (m) {
                        if (m instanceof N4DataField) {
                            tmp.push(m);
                        }
                    });
                    arr = arr.concat(tmp);
                    tmp = [];
                }

                if (inherited === true) {
                    if (typeof this.n4superType === 'undefined') {
                        return arr;
                    }
                    var tmp2 = this.n4superType.dataFieldsWithAnnotation(name, consumed, inherited, _static);
                    arr = arr.concat(tmp2);
                }

                return arr.filter(function (df) {
                    return df.hasAnnotation(name);
                });
            }
        },
        methods: {
            value: function
            methods(consumed, inherited, _static) {
                var arr = [];
                var self = this;
                var tmp = [];
                if (_static === true) {
                    this.ownedMembers.forEach(function (m) {
                        if (m instanceof N4Method && m.isStatic) {
                            tmp.push(m);
                        }
                    });
                } else {
                    this.ownedMembers.forEach(function (m) {
                        if (m instanceof N4Method) {
                            tmp.push(m);
                        }
                    });
                }
                arr = arr.concat(tmp);
                tmp = [];

                if (consumed === true) {
                    this.consumedMembers.forEach(function (m) {
                        if (m instanceof N4Method) {
                            tmp.push(m);
                        }
                    });
                    arr = arr.concat(tmp);
                    tmp = [];
                }

                if (inherited === true) {
                    if (typeof this.n4superType === 'undefined') {
                        return arr;
                    }
                    var tmp2 = this.n4superType.methods(consumed, inherited, _static);
                    arr = arr.concat(tmp2);
                }

                return arr;
            }
        },
        methodsWithAnnotation: {
            value: function
            methodsWithAnnotation(name, consumed, inherited, _static) {
                var arr = [];
                var self = this;
                var tmp = [];
                if (_static === true) {
                    this.ownedMembers.forEach(function (m) {
                        if (m instanceof N4Method && m.isStatic) {
                            tmp.push(m);
                        }
                    });
                } else {
                    this.ownedMembers.forEach(function (m) {
                        if (m instanceof N4Method) {
                            tmp.push(m);
                        }
                    });
                }
                arr = arr.concat(tmp);
                tmp = [];

                /*get consumed*/
                if (consumed === true) {
                    this.consumedMembers.forEach(function (m) {
                        if (m instanceof N4Method) {
                            tmp.push(m);
                        }
                    });
                    arr = arr.concat(tmp);
                    tmp = [];
                }

                /*get inherited, */
                if (inherited === true) {
                    /*top level element (should be N4Object) has no supertype*/
                    if (typeof this.n4superType === 'undefined') {
                        return arr;
                    }
                    var tmp2 = this.n4superType.methods(consumed, inherited, _static);
                    arr = arr.concat(tmp2);
                }

                return arr.filter(function (m) {
                    return m.hasAnnotation(name);
                });
            }
        }
    }, {});

    $makeN4BuiltInClass(N4Class, N4Classifier, {
        isClass: {
            get: function
            getIsClass() {
                return true;
            }
        }
    }, {});

    $makeN4BuiltInClass(N4Interface, N4Classifier, {
        isInterface: {
            get: function
            getIsInterface() {
                return true;
            }
        }
    }, {});

    $makeN4BuiltInClass(N4Member, N4NamedElement, {}, {});

    $makeN4BuiltInClass(N4Method, N4Member, {}, {});

    $makeN4BuiltInClass(N4Field, N4Member, {}, {});

    $makeN4BuiltInClass(N4DataField, N4Member, {}, {});

    $makeN4BuiltInClass(N4Accessor, N4Member, {
        isGetter: {
            value: function
            isGetter() {
                return this.getter;
            }
        },
        isSetter: {
            value: function
            isSetter() {
                return !this.getter;
            }
        }
    }, {});

    $makeN4BuiltInClass(N4EnumType, N4Type, {
        isEnum: {
            get: function
            getIsEnum() {
                return true;
            }
        }
    }, {});

    $makeN4BuiltInClass(N4Enum, Object, {
        toString: {
            value: function toString() {
                return this.value;
            }
        }
    }, {
        findLiteralByName: {
            value: function findLiteralByName(name) {
                for (var i = this.literals.length - 1; i >= 0; i--) {
                    if (this.literals[i].name === name) {
                        return this.literals[i];
                    }
                }
                return undefined;
            }
        },
        findLiteralByValue: {
            value: function findLiteralByValue(value) {
                for (var i = this.literals.length - 1; i >= 0; i--) {
                    if (this.literals[i].value === value) {
                        return this.literals[i];
                    }
                }
                return undefined;
            }
        }
    });

    $makeN4BuiltInClass(N4StringBasedEnumType, N4Type, {
        isEnum: {
            get: function
            getIsEnum() {
                return true;
            }
        }
    }, {});

    $makeN4BuiltInClass(N4StringBasedEnum, String, {
        toString: {
            value: function toString() {
                return this.value;
            }
        }
    }, {
        findLiteralByValue: {
            value: function findLiteralByValue(value) {
                for (var i = this.literals.length - 1; i >= 0; i--) {
                    if (this.literals[i] === value) {
                        return this.literals[i];
                    }
                }
                return undefined;
            }
        }
    });

    $makeN4BuiltInClass(N4Annotation, Object, {}, {});

    $makeN4BuiltInClass(DIConfigurationError, Error, {}, {});

    $makeN4BuiltInClass(DIUnsatisfiedBindingError, DIConfigurationError, {
        unsatisfiedBinding: {
            get: function unsatisfiedBinding() {
                return this.binding;
            }
        }
    }, {});

    $makeN4BuiltInClass(N4ApiNotImplementedError, Error, {}, {});

    $makeN4BuiltInClass(DataMap, Object, {
        saveValue: {
            value: function saveValue(key, val) {
                var keyIndex;
                var index = this.keys.indexOf(key);
                if (index === -1) {
                    this.keys.push(key);
                }
                keyIndex = this.keys.lastIndexOf(key).toString();
                this.values[keyIndex] = val;
            }
        },
        loadValue: {
            value: function loadValue(key) {
                var val;
                var index = this.keys.indexOf(key);
                if (index !== -1) {
                    val = this.values[index.toString()];
                }
                return val;
            }
        }
    }, {});

    $makeN4BuiltInClass(N4Injector, Object, {
        create: {
            value: function create(ctr) {
                var fqn = this.getFqnForCtr(ctr);
                if (!fqn) {
                    throw new DIConfigurationError('Cannot resolve FQN for ' + ctr + '.');
                }
                if (fqn === N4Type.of(this).fqn) {
                    return this;
                }
                var fact;
                var scope;
                var singleton = this.isSingleton(ctr);
                var scopedInjector;
                if (singleton) {
                    scope = this.getSingletonScope(ctr);
                    scopedInjector = this.getInjectorForScope(scope);
                }
                if (scopedInjector && fqn in scopedInjector.singletons) {
                    return scopedInjector.singletons[fqn];
                }
                if (singleton) {
                    fact = scopedInjector.lookupFactoryFor(fqn);
                    if (fact) {
                        scopedInjector.singletons[fqn] = fact.create(scopedInjector);
                        return scopedInjector.singletons[fqn];
                    }
                } else {
                    fact = this.lookupFactoryFor(fqn);
                    if (fact) {
                        return fact.create(this);
                    }
                }
                if (!ctr.$di) {
                    var typeInstance;
                    fact = { create: function() { return new ctr(); } };
                    try {
                        typeInstance = fact.create();
                    } catch (te) {
                        throw new DIUnsatisfiedBindingError(ctr, 'Type ' + fqn + ' was not bounded to any other instantiable type.');
                    }
                    this.implicitBindings.saveValue(fqn, {fqn: fqn, fact: fact});
                    if (singleton) {
                        scopedInjector = this.getInjectorForScope(scope);
                        scopedInjector.singletons[fqn] = typeInstance;
                    }
                    return typeInstance;
                } else {//consider side effects of doing and not doing the registration, especially with singletons and Child-Parent injectors
                    fact = ctr.$di;
                    if (fact && fact.create) {
                        return fact.create(this);
                    }
                }
                throw new DIUnsatisfiedBindingError(ctr, 'Type ' + fqn + ' was not bounded to any other instantiable type. Tried to inject a type where injection is not supported (yet). Probably a binder.');
            }
        },
        getInjectorForScope: {
            value: function(scope) {
                if (scope === this.injectorFqn) {
                    return this;
                }
                if (this.parentInjector.length > 0) {
                    var parent = this.parentInjector[0];
                    while (parent) {
                        if (scope === parent.injectorFqn) {
                            return parent;
                        }
                        if (parent.parentInjector.length > 0) {
                            parent = parent.parentInjector[0];
                        } else {
                            return parent;
                        }
                    }
                }
                return this;
            }
        },
        isSingleton: {
            value: function(ctr) {
                var singleton = false;
                if (ctr && ctr.n4type) {
                    var annotations = ctr.n4type.annotations;
                    if (annotations) {
                        for (var i = 0; i < annotations.length; i++) {
                            if (annotations[i].name === "Singleton") {
                                return true;
                            }
                        }
                    }
                    var fqn = this.getFqnForCtr(ctr);
                    if (fqn) {
                        var inj = this;
                        while (inj) {
                            if (fqn && inj.definedSingletonBindingFqns) {
                                var toBinding = inj.definedSingletonBindingFqns[fqn];
                                if (toBinding) {
                                    return true;
                                }
                            }
                            if (inj.parentInjector && inj.parentInjector.length > 0) {
                                inj = inj.parentInjector[0];
                            } else { // jakub TODO: what about multiple parent injectors?
                                break;
                            }
                        }
                    }
                }
                return singleton;
            }
        },
        getFqnForCtr: {
            value: function(ctr) {
                if (ctr && ctr.n4type) {
                    return ctr.n4type.fqn;
                }
                return undefined;
            }
        },
        providerOf: {
            value: function(ctr, depth) {
                var inj = this;
                var provider = this.createAnonymousProvider(function() { return inj.create(ctr); });
                function defineParent(childProvider) {
                    return inj.createAnonymousProvider(function() { return childProvider; });
                }
                for (var i = 1; i < depth; i++) {
                    provider = defineParent(provider);
                }
                return provider;
            }
        },
        getSingletonScope: {
            value: function(ctr) {
                var fqn = this.getFqnForCtr(ctr);
                var scope;
                var singleton = this.isSingleton(ctr);
                if (!fqn) {
                    throw new DIConfigurationError('Cannot resolve FQN for ' + ctr + '.');
                }
                if (singleton) {
                    var inj = this;
                    while (inj) {
                        if (inj.definedBindings) {
                            if (inj.definedBindings.keys) {
                                for (var i = 0; i < inj.definedBindings.keys.length; i++) {
                                    if (inj.definedBindings.keys[i] === fqn) {
                                        return inj.injectorFqn;
                                    } else {
                                        var fromFqn = inj.definedBindings.keys[i];
                                        var injectors = this.currentToParentInjectorList();
                                        for (var j = 0; j < injectors.length; j++) {
                                            if (injectors[j].definedSingletonBindingFqns) {
                                                if (injectors[j].definedSingletonBindingFqns[fromFqn] === fqn) {
                                                    return injectors[j].injectorFqn;
                                                } else {
                                                    if (injectors[j].definedSingletonBindingFqns[fqn] === fromFqn) {
                                                        return inj.injectorFqn;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (inj.parentInjector && inj.parentInjector.length > 0) {
                            inj = inj.parentInjector[0];
                        } else { // jakub TODO: what about multiple parent injectors?
                            break;
                        }
                    }
                }
                return scope;
            }
        },
        currentToParentInjectorList: {
            value: function() {
                var injectors = [];
                var inj = this;
                while (inj) {
                    injectors.push(inj);
                    if (inj.parentInjector && inj.parentInjector.length > 0) {
                        inj = inj.parentInjector[0];
                    } else { // jakub TODO: what about multiple parent injectors?
                        break;
                    }
                }
                return injectors;
            }
        },
        registerImplicitBinding: {
            value: function(ctr, fact) {
                var fqn = this.getFqnForCtr(ctr);
                if (fqn) {
                    this.implicitBindings.saveValue(fqn, fact);
                } else {
                    throw new DIConfigurationError('Cannot resolve FQN for ' + ctr + '.');
                }
            }
        },
        recursivelyInjectValues: {
            value: function(ctr, _instance) {
                var $tmpCtr = ctr;
                var $stop = false;
                do {
                    $stop = !$tmpCtr || !$tmpCtr.prototype || !$tmpCtr.prototype.__proto__ || !$tmpCtr.prototype.__proto__.constructor;
                    if (!$stop) {
                        $tmpCtr = $tmpCtr.prototype.__proto__.constructor;
                        if ($tmpCtr.$di) {
                            $tmpCtr.$di.injectValues(this, _instance);
                            $stop = true;
                        }
                    }
                } while (!$stop);
            }
        },
        registerDefinedBinding: {
            value: function(ctr, fact) {
                var fqn = this.getFqnForCtr(ctr);
                if (fqn) {
                    var currentValue = this.definedBindings.loadValue(fqn);
                    if (currentValue !== undefined) {
                        throw new DIUnsatisfiedBindingError('Error while trying to bind type ' + fqn + '. Type is already bounded.', ctr);
                    }
                    this.definedBindings.saveValue(fqn, fact);
                } else {
                    throw new DIConfigurationError('Cannot resolve FQN for ' + ctr + '.');
                }
            }
        },
        setParentInjector: {
            value: function(inj) {
                this.parentInjector.push(inj);
            }
        },
        createAnonymousProvider: {
            value: function(getFunc) {
                var $N4Provider = function $N4Provider() {};
                $makeClass($N4Provider, Object,
                        {
                            get: { value: getFunc }
                        },
                        {},
                        function(instanceProto, staticProto) {
                            var metaClass = new N4Interface({
                                name: 'N4Provider',
                                origin: 'n4js-es5',
                                fqn: 'N4BuiltInClasses.N4Provider',
                                n4superType: undefined,
                                allImplementedInterfaces: [],
                                annotations: [],
                                ownedMembers: [new N4Method({name: 'get', jsFunction: instanceProto['get'], annotations: []})],
                                consumedMembers: []
                            });
                            return metaClass;
                        });
                 return new $N4Provider();
            }
        },
        tryGetDefinedBindingFactoryFor: {
            value: function(fqn) {
                var fact;
                var parent;
                var fromFqn = fqn;
                var visitedInjectorFqns = [];
                fact = this.definedBindings.loadValue(fromFqn);
                if (fact && fact.fact) {
                    return fact;
                }
                if (this.parentInjector.length > 0) {
                    parent = this.parentInjector[0];
                }
                while (parent) {
                    if (parent.injectorFqn in visitedInjectorFqns) {
                        return fact;
                    }
                    fact = parent.definedBindings.loadValue(fromFqn);
                    if (fact  && fact.fqn && fact.fact) {
                        visitedInjectorFqns.push(parent.injectorFqn);
                        fromFqn = fact.fqn;
                        if (this.definedBindings.loadValue(fromFqn)) {
                            return this.definedBindings.loadValue(fromFqn);
                        }
                        if (parent === this.parentInjector[0]) {
                            return fact;
                        }
                        parent = this.parentInjector[0];
                    } else {
                        if (parent.parentInjector.length > 0) {
                            parent = parent.parentInjector[0];
                        } else { // jakub TODO: what about multiple parent injectors?
                            break;
                        }
                    }
                }
                return fact;
            }
        },
        lookupFactoryFor: {
            value: function(fqn) {
                var fact;
                fact = this.tryGetDefinedBindingFactoryFor(fqn);
                if (fact && fact.fact) {
                    return fact.fact;
                }
                fact = this.implicitBindings.loadValue(fqn);
                if (fact && fact.fact) {
                    return fact.fact;
                }
                var parent;
                if (this.parentInjector.length > 0) {
                    parent = this.parentInjector[0];
                }
                while (parent) {
                    fact = parent.implicitBindings.loadValue(fqn);
                    if (fact && fact.fact) {
                        return fact.fact;
                    }
                    if (parent.parentInjector.length > 0) {
                        parent = parent.parentInjector[0];
                    } else { // jakub TODO: what about multiple parent injectors?
                        break;
                    }
                }
                return undefined;
            }
        }
    },
    {
        of: {
            value: function of(type, parent, bindings) {
                if (!type) {
                    throw new Error("cannot create N4Injector without root object N4Type");
                }
                var injectorSpec = {
                    injectorFqn: type.n4type.fqn,
                    injectorName: type.n4type.name,
                    singletons: {},
                    implicitBindings: new DataMap(),
                    definedBindings: new DataMap(),
                    definedSingletonBindingFqns: {},
                    parentInjector: []
                };
                var _injector = new N4Injector(injectorSpec);
                if (type.$di && type.$di.register) {
                    type.$di.register(_injector);
                }
                //parent injector info could be compiled into root.$di itself
                var parentInjFQN = function(root){
                    var anns = root.n4type.allAnnotations('WithParentInjector');
                    if (anns.length > 0) {
                        return anns[0].details[0];
                    }
                    return undefined;
                };
                if (parent) {
                    if (parent.setParentInjector) {
                        if (!(parent instanceof N4Injector)) {
                            console.warn("N4JS DI ::", "parent injector does not look like parent injector");
                        }
                        var expectedParent = parentInjFQN(type);
                        if (parent.injectorFqn !== expectedParent) {
                            throw new DIConfigurationError('Injector of ' + parent.injectorFqn + ' was used as parent for injector of ' + type.n4type.fqn + '. Injector of '
                                + type.n4type.fqn + ' accepts only injector of ' + expectedParent + ' as its parent injector.');
                        }
                        _injector.setParentInjector(parent);
                    }
                }
                var binder;
                for (var i = 2; i < arguments.length; i++) {
                    binder = arguments[i];
                    if (!binder) {
                        continue;
                    }
                    if (binder.$di) {
                        binder.$di(_injector);
                    } else {
                        console.warn("N4JS DI ::", "passed argument [" + i + "]", binder, "was not recognized as neither Injector nor Binder");
                    }
                }
                return _injector;
            }
        }
    });

    // ===== add meta information for N4BuiltInClasses

    $createMetaInfo(N4Object,
        new N4Class({
            name: 'N4Object',
            origin: 'n4js-es5',
            fqn: 'N4BuiltInClasses.N4Object',
            n4superType: N4Object.n4type,
            allImplementedInterfaces: [],
            annotations: [],
            ownedMembers: [],
            consumedMemebers: []
        }));

    $createMetaInfo(N4Element,
        new N4Class({
            name: 'N4Element',
            origin: 'n4js-es5',
            fqn: 'N4BuiltInClasses.N4Element',
            n4superType: Object,
            allImplementedInterfaces: [],
            annotations: [],
            ownedMembers: [new N4DataField({
                name: 'annotations'
            }), new N4Method({
                name: 'hasAnnotation',
                jsFunction: N4Element['hasAnnotation']
            }), new N4Method({
                name: 'anyAnnotation',
                jsFunction: N4Element['anyAnnotation']
            }), new N4Method({
                name: 'allAnnotations',
                jsFunction: N4Element['allAnnotations']
            })],
            consumedMemebers: []
        }));

    $createMetaInfo(N4NamedElement,
        new N4Class({
            name: 'N4NamedElement',
            origin: 'n4js-es5',
            fqn: 'N4BuiltInClasses.N4NamedElement',
            n4superType: N4Element.n4type,
            allImplementedInterfaces: [],
            annotations: [],
            ownedMembers: [new N4DataField({
                name: 'name'
            })],
            consumedMemebers: []
        }));

    $createMetaInfo(N4Type,
        new N4Class({
            name: 'N4Type',
            origin: 'n4js-es5',
            fqn: 'N4BuiltInClasses.N4Type',
            n4superType: N4NamedElement.n4type,
            allImplementedInterfaces: [],
            annotations: [],
            ownedMembers: [new N4DataField({
                name: 'fqn'
            }), new N4Method({
                name: 'of',
                jsFunction: N4Type['of'],
                isStatic: true
            }), new N4Accessor({
                name: 'isClass',
                getter: true
            }), new N4Accessor({
                name: 'isInterface',
                getter: true
            }), new N4Accessor({
                name: 'isEnum',
                getter: true
            })],
            consumedMemebers: []
        }));

    $createMetaInfo(N4Classifier,
        new N4Class({
            name: 'N4Classifier',
            origin: 'n4js-es5',
            fqn: 'N4BuiltInClasses.N4Classifier',
            n4superType: N4Type.n4type,
            allImplementedInterfaces: [],
            annotations: [],
            ownedMembers: [new N4DataField({
                name: 'n4superType'
            }), new N4DataField({
                name: 'allImplementedInterfaces'
            }), new N4DataField({
                name: 'ownedMembers'
            }), new N4DataField({
                name: 'consumedMembers'
            }), new N4Method({
                name: 'constructor'
            }), new N4Method({
                name: 'members',
                jsFunction: N4Classifier['members']
            }), new N4Method({
                name: 'membersWithAnnotation',
                jsFunction: N4Classifier['membersWithAnnotation']
            }), new N4Method({
                name: 'dataFields',
                jsFunction: N4Classifier['dataFields']
            }), new N4Method({
                name: 'dataFieldsWithAnnotation',
                jsFunction: N4Classifier['dataFieldsWithAnnotation']
            }), new N4Method({
                name: 'methods',
                jsFunction: N4Classifier['methods']
            }), new N4Method({
                name: 'methodsWithAnnotation',
                jsFunction: N4Classifier['methodsWithAnnotation']
            })],
            consumedMemebers: []
        }));

    $createMetaInfo(N4Class,
        new N4Class({
            name: 'N4Class',
            origin: 'n4js-es5',
            fqn: 'N4BuiltInClasses.N4Class',
            n4superType: N4Classifier.n4type,
            allImplementedInterfaces: [],
            annotations: [],
            ownedMembers: [new N4Accessor({
                name: 'isClass',
                getter: true
            })],
            consumedMemebers: []
        }));

    $createMetaInfo(N4Interface,
        new N4Class({
            name: 'N4Interface',
            origin: 'n4js-es5',
            fqn: 'N4BuiltInClasses.N4Interface',
            n4superType: N4Classifier.n4type,
            allImplementedInterfaces: [],
            annotations: [],
            ownedMembers: [new N4Accessor({
                name: 'isInterface',
                getter: true
            })],
            consumedMemebers: []
        }));

    $createMetaInfo(N4Member,
        new N4Class({
            name: 'N4Member',
            origin: 'n4js-es5',
            fqn: 'N4BuiltInClasses.N4Member',
            n4superType: N4NamedElement.n4type,
            allImplementedInterfaces: [],
            annotations: [],
            ownedMembers: [new N4DataField({
                name: 'owner'
            }), new N4DataField({
                name: 'isStatic'
            })],
            consumedMemebers: []
        }));

    $createMetaInfo(N4Method,
        new N4Class({
            name: 'N4Method',
            origin: 'n4js-es5',
            fqn: 'N4BuiltInClasses.N4Method',
            n4superType: N4Member.n4type,
            allImplementedInterfaces: [],
            annotations: [],
            ownedMembers: [new N4DataField({
                name: 'jsFunction'
            })],
            consumedMemebers: []
        }));

    $createMetaInfo(N4Field,
        new N4Class({
            name: 'N4Field',
            origin: 'n4js-es5',
            fqn: 'N4BuiltInClasses.N4Field',
            n4superType: N4Member.n4type,
            allImplementedInterfaces: [],
            annotations: [],
            ownedMembers: [],
            consumedMemebers: []
        }));

    $createMetaInfo(N4DataField,
        new N4Class({
            name: 'N4DataField',
            origin: 'n4js-es5',
            fqn: 'N4BuiltInClasses.N4DataField',
            n4superType: N4Member.n4type,
            allImplementedInterfaces: [],
            annotations: [],
            ownedMembers: [],
            consumedMemebers: []
        }));

    $createMetaInfo(N4Accessor,
        new N4Class({
            name: 'N4Accessor',
            origin: 'n4js-es5',
            fqn: 'N4BuiltInClasses.N4Accessor',
            n4superType: N4Member.n4type,
            allImplementedInterfaces: [],
            annotations: [],
            ownedMembers: [new N4DataField({
                name: 'getter'
            }), new N4Method({
                name: 'isGetter',
                jsFunction: N4Accessor['isGetter']
            }), new N4Method({
                name: 'isSetter',
                jsFunction: N4Accessor['isSetter']
            })],
            consumedMemebers: []
        }));

    $createMetaInfo(N4EnumType,
        new N4Class({
            name: 'N4EnumType',
            origin: 'n4js-es5',
            fqn: 'N4BuiltInClasses.N4EnumType',
            n4superType: N4Type.n4type,
            allImplementedInterfaces: [],
            annotations: [],
            ownedMembers: [new N4Accessor({
                name: 'isEnum',
                getter: true
            })],
            consumedMemebers: []
        }));

    $createMetaInfo(N4Enum,
        new N4Class({
            name: 'N4Enum',
            origin: 'n4js-es5',
            fqn: 'N4BuiltInClasses.N4Enum',
            n4superType: undefined,
            allImplementedInterfaces: [],
            annotations: [],
            ownedMembers: [new N4Method({
                name: 'toString',
                jsFunction: N4Enum['toString']
            }), new N4Method({
                name: 'valueByName',
                jsFunction: N4Enum['valueByName'],
                isStatic: true
            }), new N4Accessor({
                name: 'name',
                getter: true
            }), new N4Accessor({
                name: 'value',
                getter: true
            }), new N4Accessor({
                name: 'values',
                getter: true,
                isStatic: true
            }), new N4Accessor({
                name: 'n4type',
                getter: true
            })],
            consumedMemebers: []
        }));

    $createMetaInfo(N4StringBasedEnumType,
        new N4Class({
            name: 'N4StringBasedEnumType',
            origin: 'n4js-es5',
            fqn: 'N4BuiltInClasses.N4StringBasedEnumType',
            n4superType: N4Type.n4type,
            allConsumedRoles: [],
            allImplementedInterfaces: [],
            annotations: [],
            ownedMembers: [new N4Accessor({
                name: 'isEnum',
                getter: true
            })],
            consumedMemebers: []
        }));

    $createMetaInfo(N4StringBasedEnum,
        new N4Class({
            name: 'N4StringBasedEnum',
            origin: 'n4js-es5',
            fqn: 'N4BuiltInClasses.N4StringBasedEnum',
            n4superType: undefined,
            allConsumedRoles: [],
            allImplementedInterfaces: [],
            annotations: [],
            ownedMembers: [new N4Method({
                name: 'toString',
                jsFunction: N4StringBasedEnum['toString']
            }), new N4Method({
                name: 'valueByName',
                jsFunction: N4StringBasedEnum['valueByName'],
                isStatic: true
            }), new N4Accessor({
                name: 'name',
                getter: true
            }), new N4Accessor({
                name: 'value',
                getter: true
            }), new N4Accessor({
                name: 'values',
                getter: true,
                isStatic: true
            }), new N4Accessor({
                name: 'n4type',
                getter: true
            })],
            consumedMemebers: []
        }));

    $createMetaInfo(N4Annotation,
        new N4Class({
            name: 'N4Annotation',
            origin: 'n4js-es5',
            fqn: 'N4BuiltInClasses.N4Annotation',
            n4superType: undefined,
            allImplementedInterfaces: [],
            annotations: [],
            ownedMembers: [new N4DataField({
                name: 'name'
            }), new N4DataField({
                name: 'details'
            }), new N4DataField({
                name: 'target'
            })],
            consumedMemebers: []
        }));

    $createMetaInfo(N4Injector,
        new N4Class({
            name: 'N4Injector',
            origin: 'n4js-es5',
            fqn: 'N4BuiltInClasses.N4Injector',
            n4superType: undefined,
            allImplementedInterfaces: [],
            annotations: [],
            ownedMembers: [new N4Method({
                name: 'of',
                jsFunction: N4Injector['of'],
                isStatic: true
            }),
            new N4Method({
                name: 'create',
                jsFunction: N4Injector['create']
            })],
            consumedMemebers: []
        }));

    $createMetaInfo(N4Provider,
        new N4Interface({
            name: 'N4Provider',
            origin: 'n4js-es5',
            fqn: 'N4BuiltInClasses.N4Provider',
            n4superType: undefined,
            allImplementedInterfaces: [],
            annotations: [],
            ownedMembers: [new N4Method({ name: 'get', jsFunction: N4Provider['get'], annotations: [] })],
            consumedMembers: []
        }));

    $createMetaInfo(DIConfigurationError,
        new N4Class({
            name: 'DIConfigurationError',
            origin: 'n4js-es5',
            fqn: 'N4BuiltInClasses.DIConfigurationError',
            n4superType: undefined,
            allImplementedInterfaces: [],
            annotations: [],
            ownedMembers: [],
            consumedMemebers: []
        }));

    $createMetaInfo(DIUnsatisfiedBindingError,
        new N4Class({
            name: 'DIUnsatisfiedBindingError',
            origin: 'n4js-es5',
            fqn: 'N4BuiltInClasses.DIUnsatisfiedBindingError',
            n4superType: DIConfigurationError.n4type,
            allImplementedInterfaces: [],
            annotations: [],
            ownedMembers: [
                new N4Accessor({
                    name: 'unsatisfiedBinding',
                    getter: true,
                    annotations: []
            }), new N4Method({
                    name: 'constructor',
                    jsFunction: DIUnsatisfiedBindingError['constructor'],
                    annotations: []
            })],
            consumedMemebers: []
        }));

    $createMetaInfo(N4ApiNotImplementedError,
        new N4Class({
            name: 'N4ApiNotImplementedError',
            origin: 'N4BuiltInClasses',
            fqn: 'N4BuiltInClasses.N4ApiNotImplementedError',
            n4superType: undefined,
            allImplementedInterfaces: [],
            annotations: [],
            ownedMembers: [],
            consumedMemebers: []
        }));

    //====== prevent modifications at runtime runtime
    Object.freeze(N4Object);
    Object.freeze(N4Element);
    Object.freeze(N4NamedElement);
    Object.freeze(N4Type);
    Object.freeze(N4Classifier);
    Object.freeze(N4Class);
    Object.freeze(N4Interface);
    Object.freeze(N4Member);
    Object.freeze(N4Method);
    Object.freeze(N4Field);
    Object.freeze(N4DataField);
    Object.freeze(N4Accessor);
    Object.freeze(N4EnumType);
    Object.freeze(N4Enum);
    Object.freeze(N4StringBasedEnumType);
    Object.freeze(N4StringBasedEnum);
    Object.freeze(N4Injector);
    Object.freeze(N4Provider);
    Object.freeze(DIConfigurationError);
    Object.freeze(DIUnsatisfiedBindingError);
    Object.freeze(N4ApiNotImplementedError);

    //====== make globally available

    global.N4Object = N4Object;
    global.N4Element = N4Element;
    global.N4NamedElement = N4NamedElement;
    global.N4Type = N4Type;
    global.N4Classifier = N4Classifier;
    global.N4Class = N4Class;
    global.N4Interface = N4Interface;
    global.N4Member = N4Member;
    global.N4Method = N4Method;
    global.N4Field = N4Field;
    global.N4DataField = N4DataField;
    global.N4Accessor = N4Accessor;
    global.N4EnumType = N4EnumType;
    global.N4Enum = N4Enum;
    global.N4StringBasedEnumType = N4StringBasedEnumType;
    global.N4StringBasedEnum = N4StringBasedEnum;
    global.N4Annotation = N4Annotation;
    global.N4Injector = N4Injector;
    global.N4Provider = N4Provider;
    global.DIConfigurationError = DIConfigurationError;
    global.DIUnsatisfiedBindingError = DIUnsatisfiedBindingError;
    global.N4ApiNotImplementedError = N4ApiNotImplementedError;

})(typeof global === "object" ? global : self);
