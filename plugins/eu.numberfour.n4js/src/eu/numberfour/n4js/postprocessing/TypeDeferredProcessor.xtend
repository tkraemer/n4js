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
package eu.numberfour.n4js.postprocessing

import com.google.inject.Inject
import com.google.inject.Singleton
import eu.numberfour.n4js.n4JS.ExportedVariableDeclaration
import eu.numberfour.n4js.n4JS.N4FieldDeclaration
import eu.numberfour.n4js.n4JS.N4GetterDeclaration
import eu.numberfour.n4js.n4JS.N4MethodDeclaration
import eu.numberfour.n4js.ts.typeRefs.DeferredTypeRef
import eu.numberfour.n4js.ts.typeRefs.ThisTypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRefsFactory
import eu.numberfour.n4js.ts.types.ContainerType
import eu.numberfour.n4js.ts.types.TMethod
import eu.numberfour.n4js.ts.types.UndefModifier
import eu.numberfour.n4js.ts.utils.TypeUtils
import eu.numberfour.n4js.typesystem.N4JSTypeSystem
import eu.numberfour.n4js.typesystem.TypeSystemHelper
import eu.numberfour.n4js.utils.EcoreUtilN4
import it.xsemantics.runtime.RuleEnvironment
import org.eclipse.emf.ecore.EObject

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*
import eu.numberfour.n4js.n4JS.FormalParameter
import eu.numberfour.n4js.n4JS.FunctionExpression
import eu.numberfour.n4js.n4JS.TypedElement
import eu.numberfour.n4js.ts.types.TypableElement
import eu.numberfour.n4js.n4JS.FunctionDefinition
import eu.numberfour.n4js.n4JS.SetterDeclaration
import eu.numberfour.n4js.ts.types.TypeReferenceContainer

/**
 * Processor for handling {@link DeferredTypeRef}s, <b>except</b> those related to poly expressions, which are handled
 * by the {@link PolyProcessor}s.
 */
@Singleton
package class TypeDeferredProcessor extends AbstractProcessor {

	@Inject
	private N4JSTypeSystem ts;
	@Inject
	private TypeSystemHelper tsh;

	def void handleDeferredTypeRefs_preChildren(RuleEnvironment G, EObject obj, ASTMetaInfoCache cache) {
		// DeferredTypeRefs related to poly expressions should not be handled here (poly computer responsible for this!)
		switch (obj) {
			N4MethodDeclaration: {
				val returnTypeRef = obj.returnTypeRef;
				if (obj.isConstructor) {
					val tCtor = obj.definedType as TMethod;
					if (null !== tCtor) {
						assertTrueIfRigid(cache, "TMethod in TModule should be a constructor", tCtor.isConstructor);
						assertTrueIfRigid(cache, "return type of constructor in TModule should be a DeferredTypeRef",
							tCtor.returnTypeRef instanceof DeferredTypeRef);
						val implicitReturnTypeRef = TypeRefsFactory.eINSTANCE.createThisTypeRef;
						implicitReturnTypeRef.undefModifier = UndefModifier.OPTIONAL;
						val boundThisTypeRef = tsh.bindAndSubstituteThisTypeRef(G, obj, implicitReturnTypeRef);
						EcoreUtilN4.doWithDeliver(false, [
							tCtor.returnTypeRef = TypeUtils.copy(boundThisTypeRef);
						], tCtor);
					}
				} else if (returnTypeRef instanceof ThisTypeRef) {
					val tMethod = obj.definedType as TMethod;
					if (null !== tMethod) {
						assertTrueIfRigid(cache, "return type of TMethod in TModule should be a DeferredTypeRef",
							tMethod.returnTypeRef instanceof DeferredTypeRef);
						val boundThisTypeRef = tsh.bindAndSubstituteThisTypeRef(G, returnTypeRef, returnTypeRef);
						EcoreUtilN4.doWithDeliver(false, [
							tMethod.returnTypeRef = TypeUtils.copy(boundThisTypeRef);
						], tMethod);
					}
				}
			}
			N4GetterDeclaration: {
				val returnTypeRef = obj.declaredTypeRef;
				if (returnTypeRef instanceof ThisTypeRef) {
					val tGetter = obj.definedGetter;
					assertTrueIfRigid(cache, "return type of TGetter in TModule should be a DeferredTypeRef",
						tGetter.declaredTypeRef instanceof DeferredTypeRef);
					val boundThisTypeRef = ts.thisTypeRef(G, returnTypeRef).value; // G |~ methodDecl.returnTypeRef ~> boundThisTypeRef
					EcoreUtilN4.doWithDeliver(false, [
						tGetter.declaredTypeRef = TypeUtils.copy(boundThisTypeRef);
					], tGetter);
				}
			}
		};
	}

	def void handleDeferredTypeRefs_postChildren(RuleEnvironment G, EObject obj, ASTMetaInfoCache cache) {
		// DeferredTypeRefs related to poly expressions should not be handled here (poly computer responsible for this!)
		switch (obj) {
			ExportedVariableDeclaration: {
				if (obj.declaredTypeRef === null) {
					val tVariable = obj.definedVariable;
					if (tVariable !== null) { // note: tVariable===null happens if obj.name===null (see types builder)
						assertTrueIfRigid(cache, "return type of TVariable in TModule should be a DeferredTypeRef",
							tVariable.typeRef instanceof DeferredTypeRef);
						val variableTypeRef = askXsemanticsForType(G, null, obj).value; // delegate to Xsemantics rule typeVariableDeclaration
						val variableTypeRefSane = tsh.sanitizeTypeOfVariableFieldProperty(G, variableTypeRef);
						EcoreUtilN4.doWithDeliver(false, [
							tVariable.typeRef = TypeUtils.copy(variableTypeRefSane);
						], tVariable);
					}
				}
			}
			N4FieldDeclaration: {
				val tField = obj.definedField;
				setTypeRef(obj, tField, G, cache);
			}
			FormalParameter: {
				val parent = obj.eContainer;
				switch (parent) {
					FunctionExpression: {
						// do nothing since its DeferredTypes are computed in PolyProcessor_FunctionExpression
					}
					FunctionDefinition: {
						val tFPar = obj.definedTypeElement;
						if (tFPar.typeRef instanceof DeferredTypeRef) {
							setTypeRef(obj, tFPar, G, cache);
						}
					}
					SetterDeclaration: {
						// do nothing since setters don't have Deferred Types (and cannot have a default initializer)
					}
					default:
						throw new IllegalArgumentException("Unsupported parent type of FormalParameter")
				}
			}
		}
	}
	
	private def <T extends TypableElement & TypedElement> void setTypeRef(T typedElem, TypeReferenceContainer trc, RuleEnvironment G, ASTMetaInfoCache cache) {
		if (typedElem.declaredTypeRef === null) {
			if (trc !== null) { // note: tFPar===null happens if obj.name===null (see types builder)
				assertTrueIfRigid(cache, "return type of "+ typedElem.class.name +" in TModule should be a DeferredTypeRef",
					trc.typeRef instanceof DeferredTypeRef);
				val context = if (trc.eContainer instanceof ContainerType<?>) TypeUtils.createTypeRef(
						trc.eContainer as ContainerType<?>) else null;
				val G_withContext = ts.createRuleEnvironmentForContext(context, G.contextResource);
				val fieldTypeRef = askXsemanticsForType(G_withContext, null, typedElem).value; // delegate to Xsemantics rule typeN4FieldDeclaration
				val fieldTypeRefSubst = ts.substTypeVariables(G_withContext, fieldTypeRef).value;
				val fieldTypeRefSane = tsh.sanitizeTypeOfVariableFieldProperty(G, fieldTypeRefSubst);
				EcoreUtilN4.doWithDeliver(false, [
					trc.typeRef = TypeUtils.copy(fieldTypeRefSane);
				], trc);
			}
		}
	}
}
