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
package eu.numberfour.n4js.misc

import com.google.inject.Inject
import com.google.inject.Singleton
import eu.numberfour.n4js.n4JS.ForStatement
import eu.numberfour.n4js.n4JS.N4JSASTUtils
import eu.numberfour.n4js.n4JS.VariableBinding
import eu.numberfour.n4js.n4JS.VariableDeclaration
import eu.numberfour.n4js.scoping.accessModifiers.VisibilityAwareMemberScope
import eu.numberfour.n4js.scoping.members.MemberScopingHelper
import eu.numberfour.n4js.scoping.utils.AbstractDescriptionWithError
import eu.numberfour.n4js.typesystem.TypeSystemHelper
import eu.numberfour.n4js.utils.ContainerTypesHelper
import eu.numberfour.n4js.xsemantics.N4JSTypeSystem
import eu.numberfour.n4js.ts.conversions.ComputedPropertyNameValueConverter
import eu.numberfour.n4js.ts.scoping.builtin.BuiltInTypeScope
import eu.numberfour.n4js.ts.typeRefs.ComposedTypeRef
import eu.numberfour.n4js.ts.typeRefs.IntersectionTypeExpression
import eu.numberfour.n4js.ts.typeRefs.TypeArgument
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.typeRefs.UnionTypeExpression
import eu.numberfour.n4js.ts.types.ContainerType
import eu.numberfour.n4js.ts.types.PrimitiveType
import eu.numberfour.n4js.ts.types.TField
import eu.numberfour.n4js.ts.types.TGetter
import eu.numberfour.n4js.ts.types.TMethod
import eu.numberfour.n4js.ts.types.util.AllSuperTypeRefsCollector
import eu.numberfour.n4js.ts.utils.TypeUtils
import it.xsemantics.runtime.RuleEnvironment
import java.util.Map
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.naming.QualifiedName
import org.eclipse.xtext.scoping.IScope

import static extension eu.numberfour.n4js.misc.DestructNode.arePositional
import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*

/**
 * Helper for dealing with destructuring patterns. For more details on destructuring patterns,
 * see documentation of class {@link DestructNode}.
 */
@Singleton
class DestructureHelper {

	@Inject private N4JSTypeSystem ts;
	@Inject private TypeSystemHelper tsh;
	@Inject private ContainerTypesHelper containerTypesHelper;
	@Inject private MemberScopingHelper memberScopingHelper;


	/**
	 * Infers the type of a variable declaration within a destructuring pattern from the value to be
	 * destructured and/or the default value given in the pattern.
	 * <p>
	 * Returns <code>null</code> if <code>vdecl</code> has an explicitly declared type or in case of error.
	 */
	public def TypeRef getTypeOfVariableDeclarationInDestructuringPattern(RuleEnvironment G, VariableDeclaration vdecl) {
		if(vdecl.declaredTypeRef!==null)
			return null;

		val root = N4JSASTUtils.getRootOfDestructuringPattern(vdecl.eContainer);
		if(root===null)
			return null;

		val rootParent = root.eContainer;
		if(rootParent instanceof VariableBinding) {

			val rootParent2 = rootParent.eContainer;
			val isLocatedUnderForInOf = rootParent2 instanceof ForStatement
					&& N4JSASTUtils.isDestructuringForStatement(rootParent2 as ForStatement);

			val rootNode = if(isLocatedUnderForInOf) {
				DestructNode.unify(rootParent2 as ForStatement)
			} else {
				DestructNode.unify(rootParent);
			};

			if(rootNode!==null) {
				val valueTypePerNode = newHashMap;
				buildValueTypesMap(G, rootNode, valueTypePerNode, rootParent.pattern);
				val node = rootNode.stream().filter[variableDeclaration===vdecl].findFirst.orElse(null);
				return valueTypePerNode.get(node);
			}
		}

		return null;
	}


	/**
	 * For a unified destructuring pattern created with one of the unify() methods in {@link DestructNode}, this method will
	 * return a mapping of the pattern's nodes to their value type, i.e. to the type of their value to be destructured.
	 * The returned map might not contain key/value pairs for all nodes (in case of error) but won't contain <code>null</code>
	 * values.
	 *
	 * @param G  rule environment (used for type inference).
	 * @param rootNode  root of the destructuring pattern.
	 * @param addHere  types for each node will be added here.
	 * @param contextObj  context object (used for member scoping and obtaining the containing resource or resource set).
	 */
	public def void buildValueTypesMap(RuleEnvironment G, DestructNode rootNode, Map<DestructNode,TypeRef> addHere, EObject contextObj) {
		if(rootNode?.defaultExpr===null) {
			return;
		}
		// in the root node, the defaultExpr is the main value to be destructured and
		// the node's type is simply the type of that value (nothing needs to be destructured yet)
		var valueTypeRef = ts.type(G,rootNode.defaultExpr).value;
		// special case: ForStatement
		// we might have something like for([a,b] of expr){} (in which case rootNode.defaultExpr points to the 'expr')
		if(rootNode.defaultExpr.eContainer instanceof ForStatement) {
			// valueTypeRef is currently the type of 'expr' and thus something like Iterable<T>
			// -> we are interested in the T
			valueTypeRef = extractIterableElementTypeUB(G, valueTypeRef);
		}
		// now, valueTypeRef is the type of the root value to be destructured by rootNode.nestedNodes (or null if invalid)
		if(valueTypeRef!==null) {
			addHere.put(rootNode, valueTypeRef);
			val nestedNodes = rootNode.nestedNodes;
			if(nestedNodes!==null && !nestedNodes.empty) {
				buildValueTypesMap(G, nestedNodes, valueTypeRef, addHere, contextObj);
			}
		}
	}

	private def void buildValueTypesMap(RuleEnvironment G, DestructNode[] nodes, TypeRef valueTypeRef, Map<DestructNode,TypeRef> addHere, EObject contextObj) {
		// for all non-root nodes, we have to destructure the type of the value,
		// depending on whether we are in an array or object destructuring (sub-)pattern
		if(nodes.arePositional) {
			// positional
			val elementTypeRefs = extractIterableElementTypesUBs(G,valueTypeRef).toList;
			if(!elementTypeRefs.empty) {
				val maxIdx = elementTypeRefs.size-1;
				for(var idx=0;idx<nodes.size;idx++) {
					val currNode = nodes.get(idx);
					val currValueTypeRef = elementTypeRefs.get(Math.min(idx,maxIdx));
					addTypeAndContinueWithChildren(G, currNode, currValueTypeRef, addHere, contextObj);
				}
			}
		}
		else {
			// non-positional
			val memberScope = createMemberScopeForPropertyAccess(valueTypeRef, contextObj, false); // do not check visibility
			for(currNode : nodes) {
				val currValueTypeRef = getPropertyTypeForNode(G, valueTypeRef, memberScope, currNode.propName, null);
				if(currValueTypeRef!==null) {
					addTypeAndContinueWithChildren(G, currNode, currValueTypeRef, addHere, contextObj);
				}
			}
		}
	}

	/**
	 * Create a new member scope for use with method {@link getPropertyTypeForNode(IScope,String)}.
	 *
	 * @param valueTypeRef
	 *               type of the value to be destructured.
	 * @param contextObj
	 *               context object used for (a) obtaining context resource and (b) visibility checking.
	 * @param checkVisibility
	 *               if true, the member scope will be wrapped in a {@link VisibilityAwareMemberScope}; if
	 *               false, method {@link getPropertyTypeForNode(IScope,String)} will <b>never</b> return
	 *               {@link #INVISIBLE_MEMBER}.
	 */
	public def IScope createMemberScopeForPropertyAccess(TypeRef receiverTypeRef, EObject propertyAccess, boolean checkVisibility) {
		return memberScopingHelper.createMemberScopeFor(receiverTypeRef,propertyAccess,checkVisibility,false)
	}

	/**
	 * Returns type of a property within an object destructuring pattern or <code>null</code> if property does not exist.
	 * In case the property exists but is not available (e.g. not visible), an error message is appended to given
	 * StringBuffer 'errorMessage' (optional).
	 *
	 * @param parentValueTypeRef
	 *              value type of the parent node.
	 * @param parentMemberScope
	 *              a member scope as returned by method {@link #createMemberScopeForPropertyAccess(TypeRef,EObject,boolean)}.
	 * @param propName
	 *              name of property to look up.
	 * @param errorMessage
	 *              a string buffer where the error message will be stored in case the property exists but is not readable
	 *              or <code>null</code> if the caller is not interested in receiving error messages.
	 */
	public def TypeRef getPropertyTypeForNode(RuleEnvironment G, TypeRef parentValueTypeRef, IScope parentMemberScope, String propName, StringBuffer errorMessage) {
		if(parentValueTypeRef===null || parentMemberScope===null) {
			return null;
		}
		val mDesc = parentMemberScope.getSingleElement(QualifiedName.create(propName));
		if(mDesc instanceof AbstractDescriptionWithError) {
			if(errorMessage!==null) {
				errorMessage.append(mDesc.message);
			}
		}
		val m = mDesc?.getEObjectOrProxy();
		if(m!==null && !m.eIsProxy) {
			val result = switch(m) {
				TField: m.typeRef
				TGetter: m.declaredTypeRef
			};
			if(result!==null) {
				// substitute type variables in 'result'
				val G2 = G.wrap;
				tsh.addSubstitutions(G2, parentValueTypeRef);
				val resultSubst = ts.substTypeVariables(G2, result).value;
				val resultSubstUB = if(resultSubst!==null) ts.upperBound(G2, resultSubst).value;
				return resultSubstUB;
			}
		}
		return null;
	}

	/**
	 * Following code factored out from #buildTypesMap(), because it is common to the positional
	 * and non-positional case. IMPORTANT: this method must also be called if valueTypeRef===null,
	 * because there might be a default expression in 'currNode' that provides a type.
	 */
	private def void addTypeAndContinueWithChildren(RuleEnvironment G, DestructNode currNode, TypeRef valueTypeRef, Map<DestructNode,TypeRef> addHere, EObject contextObj) {
		val actualValueTypeRef = if(currNode.rest) {
			G.arrayTypeRef(valueTypeRef) // wrap in Array<> if rest operator used
		} else {
			valueTypeRef
		};
		val currTypeRef = mergeWithTypeOfDefaultExpression(G,actualValueTypeRef,currNode);
		if(currTypeRef!==null) {
			// add type of currNode
			addHere.put(currNode,currTypeRef);
			// continue with children of currNode
			if(currNode.nestedNodes!==null && !currNode.nestedNodes.empty) {
				// TODO should do this also if currTypeRef===null (because lower-level default expressions may provide further types)
				buildValueTypesMap(G, currNode.nestedNodes, currTypeRef, addHere, contextObj);
			}
		}
	}

	/**
	 * Infers type of the default expression of 'currNode' and merges it with the given valueTypeRef.
	 * Both the given value type and inferred expression type may be null and then this returns null.
	 */
	private def TypeRef mergeWithTypeOfDefaultExpression(RuleEnvironment G, TypeRef valueTypeRef, DestructNode node) {
		val exprTypeRefRaw = if(node.defaultExpr!==null) ts.type(G, node.defaultExpr).value;
		val isNullOrUndef = if(exprTypeRefRaw!==null) ts.subtypeSucceeded(G,exprTypeRefRaw,G.undefinedTypeRef) || ts.subtypeSucceeded(G,exprTypeRefRaw,G.nullTypeRef);
		val exprTypeRef = if(exprTypeRefRaw!==null && !isNullOrUndef) ts.upperBound(G,exprTypeRefRaw).value;
		if(valueTypeRef!==null && exprTypeRef!==null) {
			// we have to merge the two types ...
			// (the small optimization with the subtype checks should be done by #createUnionType(), but isn't)
			return if(ts.subtypeSucceeded(G,valueTypeRef,exprTypeRef)) {
				exprTypeRef
			} else if(ts.subtypeSucceeded(G,exprTypeRef,valueTypeRef)) {
				valueTypeRef
			} else {
				tsh.createUnionType(G, valueTypeRef, exprTypeRef)
			};
		}
		else if(valueTypeRef!==null) {
			return valueTypeRef;
		}
		else if(exprTypeRef!==null) {
			return exprTypeRef;
		}
		return null;
	}




	/**
	 * Same as {@link #extractIterableElementTypes(RuleEnvironment,TypeRef)}, but returns the upper bounds.
	 */
	public def Iterable<TypeRef> extractIterableElementTypesUBs(RuleEnvironment G, TypeRef typeRef) {
		return extractIterableElementTypes(G,typeRef).map[ts.upperBound(G,it).value];
	}

	/**
	 * Given a type that is or (directly or indirectly) implements one of the Iterable or IterableN built-in types,
	 * this method will return the type of the first N elements returned by the Iterable's iterator. The last returned
	 * type will be the type of all remaining elements (if any).
	 *
	 * Never returns <code>null</code> but may return an empty result if 'typeRef' does not implement any of the
	 * Iterable or IterableN interfaces. Usually never returns a result longer than {@link BuiltInTypeScope#ITERABLE_N__MAX_LEN},
	 * but if there are invalid type references with too many arguments, this might happen.
	 */
	public def Iterable<TypeArgument> extractIterableElementTypes(RuleEnvironment G, TypeRef typeRef) {
		return extractIterableElementTypes(G, typeRef, true);
	}

	/**
	 * Same as {@link #extractIterableElementType(RuleEnvironment,TypeRef)}, but returns the upper bound.
	 */
	public def TypeRef extractIterableElementTypeUB(RuleEnvironment G, TypeRef typeRef) {
		return extractIterableElementTypes(G, typeRef, false).map[ts.upperBound(G,it).value].head;
	}

	/**
	 * Given a type that is or (directly or indirectly) implements the Iterable built-in types, this method will
	 * return the type of the elements returned by the Iterable's iterator.
	 *
	 * Returns <code>null</code> if 'typeRef' does not implement Iterable.
	 */
	public def TypeArgument extractIterableElementType(RuleEnvironment G, TypeRef typeRef) {
		return extractIterableElementTypes(G, typeRef, false).head;
	}

	private def Iterable<TypeArgument> extractIterableElementTypes(RuleEnvironment G, TypeRef typeRef, boolean includeIterableN) {
		val result = prim_extractIterableElementTypes(G, typeRef, includeIterableN);
		if(result===null || result.empty) {
			return #[];
		}
		// substitute type variables in result
		val G2 = G.wrap;
		tsh.addSubstitutions(G2,typeRef);
		val resultSubst = result.map[ts.substTypeVariables(G2,it).value];
		return resultSubst;
	}

	private def Iterable<? extends TypeRef> prim_extractIterableElementTypes(RuleEnvironment G, TypeRef typeRef, boolean includeIterableN) {
		var Iterable<? extends TypeRef> result = null;
		val declType = typeRef?.declaredType;
		if(declType===G.iterableType || (includeIterableN && G.isIterableN(declType))) {
			// simple: typeRef directly points to Iterable<> or an IterableN<>
			result = typeRef.typeArgs.toUpperBounds(G);
		} else if(declType instanceof PrimitiveType) {
			// note: the 'elementType' property we read in the next line is also used with instances of TObjectPrototype
			// (e.g. upper-case 'String'), but we need not and should not handle those within this block, because those
			// types are expected to be structural subtypes of Iterable<>, which is handled below in the if-block for
			// ContainerType<?>
			val elementType = declType.elementType;
			if(elementType!==null) {
				// Type#getElementType() returns non-null for certain primitive types that are "array-like" (see entity
				// ArrayLike in Types.xcore); currently, this applies only to 'string'
				result = #[ TypeUtils.copy(elementType) ];
			}
		} else if(typeRef instanceof ComposedTypeRef) {
			val results = newArrayList;
			for(containedTypeRef : typeRef.typeRefs) {
				val currResult = prim_extractIterableElementTypes(G, containedTypeRef, includeIterableN);
				if(currResult.empty) {
					// one of the types in the ComposedTypeRef does not implement Iterable/IterableN at all
					// -> the entire composed type ref must be treated as if it did not implement them at all
					return #[];
				}
				results.add(currResult);
			}
			if(!results.empty) {
				result = mergeListsOfTypeRefs(G, typeRef.getClass, results);
			}
		} else if(declType instanceof ContainerType<?>) {
			// step 1: look for Iterable<> and IterableN<...> among super types
			// (the IterableN<> are nominal, so they must be implemented explicitly anyway; Iterable<> is
			// structural but may still be implemented explicitly which would be simpler for us)
			val results = newArrayList;
			for(superTypeRef : AllSuperTypeRefsCollector.collect(declType)) {
				if(superTypeRef?.declaredType===G.iterableType || (includeIterableN && G.isIterableN(superTypeRef))) {
					// next if() is important: sorts out the super-type references to IterableN-1 in IterableN
					// (but only required if including the IterableN)
					val isContainedInIterable = G.isIterableN(superTypeRef.eContainer);
					if(!(includeIterableN && isContainedInIterable)) {
						results.add(superTypeRef.typeArgs.toUpperBounds(G));
					}
				}
			}
			if(!results.empty) {
				// for results.size>=2 this covers a corner case:
				// a type implements several of the IterableN interfaces, e.g. Iterable2<A,B> and Iterable3<X,Y,Z>
				// -> here we assume intersection{A,X} for 1st element, intersection{B,Y} for 2nd, and intersection{B,Z}
				// for 3rd and all later elements.
				result = mergeListsOfTypeRefs(G, IntersectionTypeExpression, results);
			}
			// if Iterable<> / IterableN<...> was not found (i.e. not implemented nominally):
			// step 2: look for Iterable<T> structurally (Iterable<> is structural, so need not be implemented explicitly)
			// -> we actually look for a member "public Iterator<T> [Symbol.iterator]() {...}"
			// and are interested in the type argument T
			if(result===null) {
				val res = G.get(Resource);
				if(res instanceof Resource) {
					val m = containerTypesHelper.fromContext(res).findMember(declType,ComputedPropertyNameValueConverter.SYMBOL_ITERATOR_MANGLED,false,false);
					if(m instanceof TMethod) {
						result = m.returnTypeRef?.typeArgs.toUpperBounds(G); // no problem if we set 'result' to null (it's the default anyway)
					}
					else if(m instanceof TGetter) {
						result = m.declaredTypeRef?.typeArgs.toUpperBounds(G); // no problem if we set 'result' to null (it's the default anyway)
					}
				}
				else {
					throw new IllegalArgumentException("no or invalid Resource defined in rule environment G")
				}
			}
		}
		if(result===null || result.empty) {
			return #[];
		}
		return result;
	}

	private def Iterable<TypeRef> toUpperBounds(Iterable<TypeArgument> typeArgs, RuleEnvironment G) {
		typeArgs.map[ts.upperBound(G,it).value]
	}

	private def Iterable<? extends TypeRef> mergeListsOfTypeRefs(RuleEnvironment G, Class<? extends ComposedTypeRef> type, Iterable<? extends TypeRef>... iterablesToMerge) {
		val rs = iterablesToMerge.size;
		if(rs===0) {
			return emptyList;
		}
		else if(rs===1) {
			return iterablesToMerge.head;
		}
		else {
			val listsToMerge = iterablesToMerge.map[toList].toList;
			val maxNumOfElems = listsToMerge.map[size].reduce[a,b|Math.max(a,b)].intValue;
			val result = newArrayList;
			val types_of_element_i_across_results = newArrayOfSize(rs);
			for(var i=0;i<maxNumOfElems;i++) {
				// collect all types of element at index 'i'
				for(var j=0;j<rs;j++) {
					val result_j = listsToMerge.get(j);
					if(result_j===null || result_j.empty) {
						throw new IllegalArgumentException("iterablesToMerge may not contain null values or empty iterables");
					}
					val idxSafe = Math.min(i,result_j.size-1);
					val type_of_element_i_in_result_j = result_j.get(idxSafe);
					types_of_element_i_across_results.set(j, type_of_element_i_in_result_j);
				}
				// combine them into a union or intersection type
				val type_of_element_i = if(UnionTypeExpression.isAssignableFrom(type)) {
					tsh.createUnionType(G,types_of_element_i_across_results)
				} else if(IntersectionTypeExpression.isAssignableFrom(type)) {
					tsh.createIntersectionType(G,types_of_element_i_across_results)
				} else {
					throw new IllegalArgumentException("unknown subtype of ComposedTypeRef: "+type?.name)
				};
				// add this type to main result
				result += type_of_element_i;
			}
			return result;
		}
	}
}
