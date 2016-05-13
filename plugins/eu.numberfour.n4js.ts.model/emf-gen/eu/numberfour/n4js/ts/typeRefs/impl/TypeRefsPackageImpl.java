/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.typeRefs.impl;

import eu.numberfour.n4js.ts.typeRefs.BaseTypeRef;
import eu.numberfour.n4js.ts.typeRefs.BoundThisTypeRef;
import eu.numberfour.n4js.ts.typeRefs.ClassifierTypeRef;
import eu.numberfour.n4js.ts.typeRefs.ComposedTypeRef;
import eu.numberfour.n4js.ts.typeRefs.ConstructorTypeRef;
import eu.numberfour.n4js.ts.typeRefs.DeferredTypeRef;
import eu.numberfour.n4js.ts.typeRefs.EnumTypeRef;
import eu.numberfour.n4js.ts.typeRefs.ExistentialTypeRef;
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExprOrRef;
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExpression;
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeRef;
import eu.numberfour.n4js.ts.typeRefs.IntersectionTypeExpression;
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef;
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRefStructural;
import eu.numberfour.n4js.ts.typeRefs.StaticBaseTypeRef;
import eu.numberfour.n4js.ts.typeRefs.StructuralTypeRef;
import eu.numberfour.n4js.ts.typeRefs.ThisTypeRef;
import eu.numberfour.n4js.ts.typeRefs.ThisTypeRefNominal;
import eu.numberfour.n4js.ts.typeRefs.ThisTypeRefStructural;
import eu.numberfour.n4js.ts.typeRefs.TypeArgument;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeRefsFactory;
import eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage;
import eu.numberfour.n4js.ts.typeRefs.TypeVariableMapping;
import eu.numberfour.n4js.ts.typeRefs.UnionTypeExpression;
import eu.numberfour.n4js.ts.typeRefs.UnknownTypeRef;
import eu.numberfour.n4js.ts.typeRefs.Wildcard;

import eu.numberfour.n4js.ts.types.TypesPackage;

import eu.numberfour.n4js.ts.types.impl.TypesPackageImpl;

import java.lang.Iterable;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TypeRefsPackageImpl extends EPackageImpl implements TypeRefsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass staticBaseTypeRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass baseTypeRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass composedTypeRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unionTypeExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass intersectionTypeExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass thisTypeRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass thisTypeRefNominalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass thisTypeRefStructuralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass boundThisTypeRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterizedTypeRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass structuralTypeRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterizedTypeRefStructuralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass existentialTypeRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unknownTypeRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constructorTypeRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classifierTypeRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass enumTypeRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeArgumentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wildcardEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass functionTypeExprOrRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass functionTypeRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass functionTypeExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deferredTypeRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeVariableMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType parameterizedTypeRefIterableEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TypeRefsPackageImpl() {
		super(eNS_URI, TypeRefsFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link TypeRefsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TypeRefsPackage init() {
		if (isInited) return (TypeRefsPackage)EPackage.Registry.INSTANCE.getEPackage(TypeRefsPackage.eNS_URI);

		// Obtain or create and register package
		TypeRefsPackageImpl theTypeRefsPackage = (TypeRefsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TypeRefsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TypeRefsPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		TypesPackageImpl theTypesPackage = (TypesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI) instanceof TypesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI) : TypesPackage.eINSTANCE);

		// Create package meta-data objects
		theTypeRefsPackage.createPackageContents();
		theTypesPackage.createPackageContents();

		// Initialize created meta-data
		theTypeRefsPackage.initializePackageContents();
		theTypesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTypeRefsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TypeRefsPackage.eNS_URI, theTypeRefsPackage);
		return theTypeRefsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTypeRef() {
		return typeRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTypeRef_NullModifier() {
		return (EAttribute)typeRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTypeRef_UndefModifier() {
		return (EAttribute)typeRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getTypeRef__GetModifiersAsString() {
		return typeRefEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getTypeRef__IsFinalByType() {
		return typeRefEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getTypeRef__IsTreeHierarchyType() {
		return typeRefEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getTypeRef__IsDynamic() {
		return typeRefEClass.getEOperations().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getTypeRef__IsExistential() {
		return typeRefEClass.getEOperations().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getTypeRef__IsGeneric() {
		return typeRefEClass.getEOperations().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getTypeRef__IsParameterized() {
		return typeRefEClass.getEOperations().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getTypeRef__IsRaw() {
		return typeRefEClass.getEOperations().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getTypeRef__GetDeclaredUpperBound() {
		return typeRefEClass.getEOperations().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getTypeRef__GetDeclaredLowerBound() {
		return typeRefEClass.getEOperations().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getTypeRef__GetTypeArgs() {
		return typeRefEClass.getEOperations().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getTypeRef__UndefinedOrNull() {
		return typeRefEClass.getEOperations().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getTypeRef__GetTypeRefAsString() {
		return typeRefEClass.getEOperations().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getTypeRef__ToString() {
		return typeRefEClass.getEOperations().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getTypeRef__GetDeclaredType() {
		return typeRefEClass.getEOperations().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getTypeRef__IsTopType() {
		return typeRefEClass.getEOperations().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getTypeRef__IsBottomType() {
		return typeRefEClass.getEOperations().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getTypeRef__GetTypingStrategy() {
		return typeRefEClass.getEOperations().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getTypeRef__GetStructuralMembers() {
		return typeRefEClass.getEOperations().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getTypeRef__IsUseSiteStructuralTyping() {
		return typeRefEClass.getEOperations().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getTypeRef__IsDefSiteStructuralTyping() {
		return typeRefEClass.getEOperations().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStaticBaseTypeRef() {
		return staticBaseTypeRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBaseTypeRef() {
		return baseTypeRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBaseTypeRef_Dynamic() {
		return (EAttribute)baseTypeRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getBaseTypeRef__GetModifiersAsString() {
		return baseTypeRefEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComposedTypeRef() {
		return composedTypeRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComposedTypeRef_CachedComposedMembers() {
		return (EReference)composedTypeRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComposedTypeRef_OriginalComposedTypeRef() {
		return (EReference)composedTypeRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComposedTypeRef_TypeRefs() {
		return (EReference)composedTypeRefEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getComposedTypeRef__IsDynamic() {
		return composedTypeRefEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getComposedTypeRef__GetTypeRefAsString() {
		return composedTypeRefEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnionTypeExpression() {
		return unionTypeExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getUnionTypeExpression__GetTypeRefAsString() {
		return unionTypeExpressionEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntersectionTypeExpression() {
		return intersectionTypeExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getIntersectionTypeExpression__GetTypeRefAsString() {
		return intersectionTypeExpressionEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getThisTypeRef() {
		return thisTypeRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getThisTypeRef__GetTypeRefAsString() {
		return thisTypeRefEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getThisTypeRef__GetTypingStrategy() {
		return thisTypeRefEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getThisTypeRef__GetStructuralMembers() {
		return thisTypeRefEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getThisTypeRef__IsUseSiteStructuralTyping() {
		return thisTypeRefEClass.getEOperations().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getThisTypeRefNominal() {
		return thisTypeRefNominalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getThisTypeRefStructural() {
		return thisTypeRefStructuralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getThisTypeRefStructural_DefinedTypingStrategy() {
		return (EAttribute)thisTypeRefStructuralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getThisTypeRefStructural__GetTypingStrategy() {
		return thisTypeRefStructuralEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getThisTypeRefStructural__SetTypingStrategy__TypingStrategy() {
		return thisTypeRefStructuralEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getThisTypeRefStructural__IsUseSiteStructuralTyping() {
		return thisTypeRefStructuralEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getThisTypeRefStructural__GetStructuralMembers() {
		return thisTypeRefStructuralEClass.getEOperations().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getThisTypeRefStructural__GetTypeRefAsString() {
		return thisTypeRefStructuralEClass.getEOperations().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBoundThisTypeRef() {
		return boundThisTypeRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBoundThisTypeRef_ActualThisTypeRef() {
		return (EReference)boundThisTypeRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBoundThisTypeRef_DefinedTypingStrategy() {
		return (EAttribute)boundThisTypeRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getBoundThisTypeRef__GetTypingStrategy() {
		return boundThisTypeRefEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getBoundThisTypeRef__SetTypingStrategy__TypingStrategy() {
		return boundThisTypeRefEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getBoundThisTypeRef__GetTypeRefAsString() {
		return boundThisTypeRefEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getBoundThisTypeRef__GetDeclaredUpperBound() {
		return boundThisTypeRefEClass.getEOperations().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getBoundThisTypeRef__IsDefSiteStructuralTyping() {
		return boundThisTypeRefEClass.getEOperations().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getBoundThisTypeRef__IsUseSiteStructuralTyping() {
		return boundThisTypeRefEClass.getEOperations().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getBoundThisTypeRef__GetStructuralMembers() {
		return boundThisTypeRefEClass.getEOperations().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParameterizedTypeRef() {
		return parameterizedTypeRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParameterizedTypeRef_DeclaredType() {
		return (EReference)parameterizedTypeRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParameterizedTypeRef_TypeArgs() {
		return (EReference)parameterizedTypeRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParameterizedTypeRef_DefinedTypingStrategy() {
		return (EAttribute)parameterizedTypeRefEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getParameterizedTypeRef__GetTypingStrategy() {
		return parameterizedTypeRefEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getParameterizedTypeRef__ContainsWildcards() {
		return parameterizedTypeRefEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getParameterizedTypeRef__GetTypeRefAsString() {
		return parameterizedTypeRefEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getParameterizedTypeRef__IsParameterized() {
		return parameterizedTypeRefEClass.getEOperations().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getParameterizedTypeRef__IsGeneric() {
		return parameterizedTypeRefEClass.getEOperations().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getParameterizedTypeRef__IsRaw() {
		return parameterizedTypeRefEClass.getEOperations().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getParameterizedTypeRef__ContainsUnboundTypeVariables() {
		return parameterizedTypeRefEClass.getEOperations().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getParameterizedTypeRef__IsUseSiteStructuralTyping() {
		return parameterizedTypeRefEClass.getEOperations().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getParameterizedTypeRef__IsDefSiteStructuralTyping() {
		return parameterizedTypeRefEClass.getEOperations().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStructuralTypeRef() {
		return structuralTypeRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStructuralTypeRef_AstStructuralMembers() {
		return (EReference)structuralTypeRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStructuralTypeRef_StructuralType() {
		return (EReference)structuralTypeRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStructuralTypeRef_GenStructuralMembers() {
		return (EReference)structuralTypeRefEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStructuralTypeRef_PostponedSubstitutions() {
		return (EReference)structuralTypeRefEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getStructuralTypeRef__GetTypingStrategy() {
		return structuralTypeRefEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getStructuralTypeRef__SetTypingStrategy__TypingStrategy() {
		return structuralTypeRefEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getStructuralTypeRef__GetStructuralMembers() {
		return structuralTypeRefEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getStructuralTypeRef__HasPostponedSubstitutionFor__TypeVariable() {
		return structuralTypeRefEClass.getEOperations().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParameterizedTypeRefStructural() {
		return parameterizedTypeRefStructuralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getParameterizedTypeRefStructural__GetTypingStrategy() {
		return parameterizedTypeRefStructuralEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getParameterizedTypeRefStructural__SetTypingStrategy__TypingStrategy() {
		return parameterizedTypeRefStructuralEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getParameterizedTypeRefStructural__GetStructuralMembers() {
		return parameterizedTypeRefStructuralEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getParameterizedTypeRefStructural__GetTypeRefAsString() {
		return parameterizedTypeRefStructuralEClass.getEOperations().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExistentialTypeRef() {
		return existentialTypeRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExistentialTypeRef_Wildcard() {
		return (EReference)existentialTypeRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExistentialTypeRef_BoundTypeVariable() {
		return (EReference)existentialTypeRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getExistentialTypeRef__IsExistential() {
		return existentialTypeRefEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getExistentialTypeRef__IsGeneric() {
		return existentialTypeRefEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getExistentialTypeRef__IsParameterized() {
		return existentialTypeRefEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getExistentialTypeRef__GetTypeRefAsString() {
		return existentialTypeRefEClass.getEOperations().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnknownTypeRef() {
		return unknownTypeRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getUnknownTypeRef__GetTypeRefAsString() {
		return unknownTypeRefEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstructorTypeRef() {
		return constructorTypeRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getConstructorTypeRef__GetTypeRefAsString() {
		return constructorTypeRefEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassifierTypeRef() {
		return classifierTypeRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassifierTypeRef_StaticTypeRef() {
		return (EReference)classifierTypeRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getClassifierTypeRef__GetTypeRefAsString() {
		return classifierTypeRefEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getClassifierTypeRef__StaticType() {
		return classifierTypeRefEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnumTypeRef() {
		return enumTypeRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnumTypeRef_EnumType() {
		return (EReference)enumTypeRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getEnumTypeRef__GetTypeRefAsString() {
		return enumTypeRefEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTypeArgument() {
		return typeArgumentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getTypeArgument__GetTypeRefAsString() {
		return typeArgumentEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getTypeArgument__ContainsWildcards() {
		return typeArgumentEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getTypeArgument__ContainsUnboundTypeVariables() {
		return typeArgumentEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWildcard() {
		return wildcardEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWildcard_DeclaredUpperBound() {
		return (EReference)wildcardEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWildcard_DeclaredLowerBound() {
		return (EReference)wildcardEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getWildcard__GetDeclaredOrImplicitUpperBounds() {
		return wildcardEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getWildcard__GetTypeRefAsString() {
		return wildcardEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFunctionTypeExprOrRef() {
		return functionTypeExprOrRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getFunctionTypeExprOrRef__GetDeclaredThisType() {
		return functionTypeExprOrRefEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getFunctionTypeExprOrRef__GetFunctionType() {
		return functionTypeExprOrRefEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getFunctionTypeExprOrRef__GetTypeVars() {
		return functionTypeExprOrRefEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getFunctionTypeExprOrRef__GetTypeVarUpperBounds__TypeVariable() {
		return functionTypeExprOrRefEClass.getEOperations().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getFunctionTypeExprOrRef__GetFpars() {
		return functionTypeExprOrRefEClass.getEOperations().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getFunctionTypeExprOrRef__GetReturnTypeRef() {
		return functionTypeExprOrRefEClass.getEOperations().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getFunctionTypeExprOrRef__IsGeneric() {
		return functionTypeExprOrRefEClass.getEOperations().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getFunctionTypeExprOrRef__IsRaw() {
		return functionTypeExprOrRefEClass.getEOperations().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getFunctionTypeExprOrRef__GetFparForArgIdx__int() {
		return functionTypeExprOrRefEClass.getEOperations().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getFunctionTypeExprOrRef__GetTypeRefAsString() {
		return functionTypeExprOrRefEClass.getEOperations().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFunctionTypeRef() {
		return functionTypeRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getFunctionTypeRef__GetDeclaredThisType() {
		return functionTypeRefEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getFunctionTypeRef__GetTypeVars() {
		return functionTypeRefEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getFunctionTypeRef__GetTypeVarUpperBounds__TypeVariable() {
		return functionTypeRefEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getFunctionTypeRef__GetFpars() {
		return functionTypeRefEClass.getEOperations().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getFunctionTypeRef__GetReturnTypeRef() {
		return functionTypeRefEClass.getEOperations().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFunctionTypeExpression() {
		return functionTypeExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFunctionTypeExpression_Binding() {
		return (EAttribute)functionTypeExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFunctionTypeExpression_DeclaredType() {
		return (EReference)functionTypeExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFunctionTypeExpression_DeclaredThisType() {
		return (EReference)functionTypeExpressionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFunctionTypeExpression_OwnedTypeVars() {
		return (EReference)functionTypeExpressionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFunctionTypeExpression_UnboundTypeVars() {
		return (EReference)functionTypeExpressionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFunctionTypeExpression_UnboundTypeVarsUpperBounds() {
		return (EReference)functionTypeExpressionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFunctionTypeExpression_Fpars() {
		return (EReference)functionTypeExpressionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFunctionTypeExpression_ReturnTypeRef() {
		return (EReference)functionTypeExpressionEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getFunctionTypeExpression__GetTypeVars() {
		return functionTypeExpressionEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getFunctionTypeExpression__GetTypeVarUpperBounds__TypeVariable() {
		return functionTypeExpressionEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeferredTypeRef() {
		return deferredTypeRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getDeferredTypeRef__GetTypeRefAsString() {
		return deferredTypeRefEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTypeVariableMapping() {
		return typeVariableMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeVariableMapping_TypeVar() {
		return (EReference)typeVariableMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeVariableMapping_TypeArg() {
		return (EReference)typeVariableMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getParameterizedTypeRefIterable() {
		return parameterizedTypeRefIterableEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeRefsFactory getTypeRefsFactory() {
		return (TypeRefsFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		typeRefEClass = createEClass(TYPE_REF);
		createEAttribute(typeRefEClass, TYPE_REF__NULL_MODIFIER);
		createEAttribute(typeRefEClass, TYPE_REF__UNDEF_MODIFIER);
		createEOperation(typeRefEClass, TYPE_REF___GET_MODIFIERS_AS_STRING);
		createEOperation(typeRefEClass, TYPE_REF___IS_FINAL_BY_TYPE);
		createEOperation(typeRefEClass, TYPE_REF___IS_TREE_HIERARCHY_TYPE);
		createEOperation(typeRefEClass, TYPE_REF___IS_DYNAMIC);
		createEOperation(typeRefEClass, TYPE_REF___IS_EXISTENTIAL);
		createEOperation(typeRefEClass, TYPE_REF___IS_GENERIC);
		createEOperation(typeRefEClass, TYPE_REF___IS_PARAMETERIZED);
		createEOperation(typeRefEClass, TYPE_REF___IS_RAW);
		createEOperation(typeRefEClass, TYPE_REF___GET_DECLARED_UPPER_BOUND);
		createEOperation(typeRefEClass, TYPE_REF___GET_DECLARED_LOWER_BOUND);
		createEOperation(typeRefEClass, TYPE_REF___GET_TYPE_ARGS);
		createEOperation(typeRefEClass, TYPE_REF___UNDEFINED_OR_NULL);
		createEOperation(typeRefEClass, TYPE_REF___GET_TYPE_REF_AS_STRING);
		createEOperation(typeRefEClass, TYPE_REF___TO_STRING);
		createEOperation(typeRefEClass, TYPE_REF___GET_DECLARED_TYPE);
		createEOperation(typeRefEClass, TYPE_REF___IS_TOP_TYPE);
		createEOperation(typeRefEClass, TYPE_REF___IS_BOTTOM_TYPE);
		createEOperation(typeRefEClass, TYPE_REF___GET_TYPING_STRATEGY);
		createEOperation(typeRefEClass, TYPE_REF___GET_STRUCTURAL_MEMBERS);
		createEOperation(typeRefEClass, TYPE_REF___IS_USE_SITE_STRUCTURAL_TYPING);
		createEOperation(typeRefEClass, TYPE_REF___IS_DEF_SITE_STRUCTURAL_TYPING);

		staticBaseTypeRefEClass = createEClass(STATIC_BASE_TYPE_REF);

		baseTypeRefEClass = createEClass(BASE_TYPE_REF);
		createEAttribute(baseTypeRefEClass, BASE_TYPE_REF__DYNAMIC);
		createEOperation(baseTypeRefEClass, BASE_TYPE_REF___GET_MODIFIERS_AS_STRING);

		composedTypeRefEClass = createEClass(COMPOSED_TYPE_REF);
		createEReference(composedTypeRefEClass, COMPOSED_TYPE_REF__CACHED_COMPOSED_MEMBERS);
		createEReference(composedTypeRefEClass, COMPOSED_TYPE_REF__ORIGINAL_COMPOSED_TYPE_REF);
		createEReference(composedTypeRefEClass, COMPOSED_TYPE_REF__TYPE_REFS);
		createEOperation(composedTypeRefEClass, COMPOSED_TYPE_REF___IS_DYNAMIC);
		createEOperation(composedTypeRefEClass, COMPOSED_TYPE_REF___GET_TYPE_REF_AS_STRING);

		unionTypeExpressionEClass = createEClass(UNION_TYPE_EXPRESSION);
		createEOperation(unionTypeExpressionEClass, UNION_TYPE_EXPRESSION___GET_TYPE_REF_AS_STRING);

		intersectionTypeExpressionEClass = createEClass(INTERSECTION_TYPE_EXPRESSION);
		createEOperation(intersectionTypeExpressionEClass, INTERSECTION_TYPE_EXPRESSION___GET_TYPE_REF_AS_STRING);

		thisTypeRefEClass = createEClass(THIS_TYPE_REF);
		createEOperation(thisTypeRefEClass, THIS_TYPE_REF___GET_TYPE_REF_AS_STRING);
		createEOperation(thisTypeRefEClass, THIS_TYPE_REF___GET_TYPING_STRATEGY);
		createEOperation(thisTypeRefEClass, THIS_TYPE_REF___GET_STRUCTURAL_MEMBERS);
		createEOperation(thisTypeRefEClass, THIS_TYPE_REF___IS_USE_SITE_STRUCTURAL_TYPING);

		thisTypeRefNominalEClass = createEClass(THIS_TYPE_REF_NOMINAL);

		thisTypeRefStructuralEClass = createEClass(THIS_TYPE_REF_STRUCTURAL);
		createEAttribute(thisTypeRefStructuralEClass, THIS_TYPE_REF_STRUCTURAL__DEFINED_TYPING_STRATEGY);
		createEOperation(thisTypeRefStructuralEClass, THIS_TYPE_REF_STRUCTURAL___GET_TYPING_STRATEGY);
		createEOperation(thisTypeRefStructuralEClass, THIS_TYPE_REF_STRUCTURAL___SET_TYPING_STRATEGY__TYPINGSTRATEGY);
		createEOperation(thisTypeRefStructuralEClass, THIS_TYPE_REF_STRUCTURAL___IS_USE_SITE_STRUCTURAL_TYPING);
		createEOperation(thisTypeRefStructuralEClass, THIS_TYPE_REF_STRUCTURAL___GET_STRUCTURAL_MEMBERS);
		createEOperation(thisTypeRefStructuralEClass, THIS_TYPE_REF_STRUCTURAL___GET_TYPE_REF_AS_STRING);

		boundThisTypeRefEClass = createEClass(BOUND_THIS_TYPE_REF);
		createEReference(boundThisTypeRefEClass, BOUND_THIS_TYPE_REF__ACTUAL_THIS_TYPE_REF);
		createEAttribute(boundThisTypeRefEClass, BOUND_THIS_TYPE_REF__DEFINED_TYPING_STRATEGY);
		createEOperation(boundThisTypeRefEClass, BOUND_THIS_TYPE_REF___GET_TYPING_STRATEGY);
		createEOperation(boundThisTypeRefEClass, BOUND_THIS_TYPE_REF___SET_TYPING_STRATEGY__TYPINGSTRATEGY);
		createEOperation(boundThisTypeRefEClass, BOUND_THIS_TYPE_REF___GET_TYPE_REF_AS_STRING);
		createEOperation(boundThisTypeRefEClass, BOUND_THIS_TYPE_REF___GET_DECLARED_UPPER_BOUND);
		createEOperation(boundThisTypeRefEClass, BOUND_THIS_TYPE_REF___IS_DEF_SITE_STRUCTURAL_TYPING);
		createEOperation(boundThisTypeRefEClass, BOUND_THIS_TYPE_REF___IS_USE_SITE_STRUCTURAL_TYPING);
		createEOperation(boundThisTypeRefEClass, BOUND_THIS_TYPE_REF___GET_STRUCTURAL_MEMBERS);

		parameterizedTypeRefEClass = createEClass(PARAMETERIZED_TYPE_REF);
		createEReference(parameterizedTypeRefEClass, PARAMETERIZED_TYPE_REF__DECLARED_TYPE);
		createEReference(parameterizedTypeRefEClass, PARAMETERIZED_TYPE_REF__TYPE_ARGS);
		createEAttribute(parameterizedTypeRefEClass, PARAMETERIZED_TYPE_REF__DEFINED_TYPING_STRATEGY);
		createEOperation(parameterizedTypeRefEClass, PARAMETERIZED_TYPE_REF___GET_TYPING_STRATEGY);
		createEOperation(parameterizedTypeRefEClass, PARAMETERIZED_TYPE_REF___CONTAINS_WILDCARDS);
		createEOperation(parameterizedTypeRefEClass, PARAMETERIZED_TYPE_REF___GET_TYPE_REF_AS_STRING);
		createEOperation(parameterizedTypeRefEClass, PARAMETERIZED_TYPE_REF___IS_PARAMETERIZED);
		createEOperation(parameterizedTypeRefEClass, PARAMETERIZED_TYPE_REF___IS_GENERIC);
		createEOperation(parameterizedTypeRefEClass, PARAMETERIZED_TYPE_REF___IS_RAW);
		createEOperation(parameterizedTypeRefEClass, PARAMETERIZED_TYPE_REF___CONTAINS_UNBOUND_TYPE_VARIABLES);
		createEOperation(parameterizedTypeRefEClass, PARAMETERIZED_TYPE_REF___IS_USE_SITE_STRUCTURAL_TYPING);
		createEOperation(parameterizedTypeRefEClass, PARAMETERIZED_TYPE_REF___IS_DEF_SITE_STRUCTURAL_TYPING);

		structuralTypeRefEClass = createEClass(STRUCTURAL_TYPE_REF);
		createEReference(structuralTypeRefEClass, STRUCTURAL_TYPE_REF__AST_STRUCTURAL_MEMBERS);
		createEReference(structuralTypeRefEClass, STRUCTURAL_TYPE_REF__STRUCTURAL_TYPE);
		createEReference(structuralTypeRefEClass, STRUCTURAL_TYPE_REF__GEN_STRUCTURAL_MEMBERS);
		createEReference(structuralTypeRefEClass, STRUCTURAL_TYPE_REF__POSTPONED_SUBSTITUTIONS);
		createEOperation(structuralTypeRefEClass, STRUCTURAL_TYPE_REF___GET_TYPING_STRATEGY);
		createEOperation(structuralTypeRefEClass, STRUCTURAL_TYPE_REF___SET_TYPING_STRATEGY__TYPINGSTRATEGY);
		createEOperation(structuralTypeRefEClass, STRUCTURAL_TYPE_REF___GET_STRUCTURAL_MEMBERS);
		createEOperation(structuralTypeRefEClass, STRUCTURAL_TYPE_REF___HAS_POSTPONED_SUBSTITUTION_FOR__TYPEVARIABLE);

		parameterizedTypeRefStructuralEClass = createEClass(PARAMETERIZED_TYPE_REF_STRUCTURAL);
		createEOperation(parameterizedTypeRefStructuralEClass, PARAMETERIZED_TYPE_REF_STRUCTURAL___GET_TYPING_STRATEGY);
		createEOperation(parameterizedTypeRefStructuralEClass, PARAMETERIZED_TYPE_REF_STRUCTURAL___SET_TYPING_STRATEGY__TYPINGSTRATEGY);
		createEOperation(parameterizedTypeRefStructuralEClass, PARAMETERIZED_TYPE_REF_STRUCTURAL___GET_STRUCTURAL_MEMBERS);
		createEOperation(parameterizedTypeRefStructuralEClass, PARAMETERIZED_TYPE_REF_STRUCTURAL___GET_TYPE_REF_AS_STRING);

		existentialTypeRefEClass = createEClass(EXISTENTIAL_TYPE_REF);
		createEReference(existentialTypeRefEClass, EXISTENTIAL_TYPE_REF__WILDCARD);
		createEReference(existentialTypeRefEClass, EXISTENTIAL_TYPE_REF__BOUND_TYPE_VARIABLE);
		createEOperation(existentialTypeRefEClass, EXISTENTIAL_TYPE_REF___IS_EXISTENTIAL);
		createEOperation(existentialTypeRefEClass, EXISTENTIAL_TYPE_REF___IS_GENERIC);
		createEOperation(existentialTypeRefEClass, EXISTENTIAL_TYPE_REF___IS_PARAMETERIZED);
		createEOperation(existentialTypeRefEClass, EXISTENTIAL_TYPE_REF___GET_TYPE_REF_AS_STRING);

		unknownTypeRefEClass = createEClass(UNKNOWN_TYPE_REF);
		createEOperation(unknownTypeRefEClass, UNKNOWN_TYPE_REF___GET_TYPE_REF_AS_STRING);

		constructorTypeRefEClass = createEClass(CONSTRUCTOR_TYPE_REF);
		createEOperation(constructorTypeRefEClass, CONSTRUCTOR_TYPE_REF___GET_TYPE_REF_AS_STRING);

		classifierTypeRefEClass = createEClass(CLASSIFIER_TYPE_REF);
		createEReference(classifierTypeRefEClass, CLASSIFIER_TYPE_REF__STATIC_TYPE_REF);
		createEOperation(classifierTypeRefEClass, CLASSIFIER_TYPE_REF___GET_TYPE_REF_AS_STRING);
		createEOperation(classifierTypeRefEClass, CLASSIFIER_TYPE_REF___STATIC_TYPE);

		enumTypeRefEClass = createEClass(ENUM_TYPE_REF);
		createEReference(enumTypeRefEClass, ENUM_TYPE_REF__ENUM_TYPE);
		createEOperation(enumTypeRefEClass, ENUM_TYPE_REF___GET_TYPE_REF_AS_STRING);

		typeArgumentEClass = createEClass(TYPE_ARGUMENT);
		createEOperation(typeArgumentEClass, TYPE_ARGUMENT___GET_TYPE_REF_AS_STRING);
		createEOperation(typeArgumentEClass, TYPE_ARGUMENT___CONTAINS_WILDCARDS);
		createEOperation(typeArgumentEClass, TYPE_ARGUMENT___CONTAINS_UNBOUND_TYPE_VARIABLES);

		wildcardEClass = createEClass(WILDCARD);
		createEReference(wildcardEClass, WILDCARD__DECLARED_UPPER_BOUND);
		createEReference(wildcardEClass, WILDCARD__DECLARED_LOWER_BOUND);
		createEOperation(wildcardEClass, WILDCARD___GET_DECLARED_OR_IMPLICIT_UPPER_BOUNDS);
		createEOperation(wildcardEClass, WILDCARD___GET_TYPE_REF_AS_STRING);

		functionTypeExprOrRefEClass = createEClass(FUNCTION_TYPE_EXPR_OR_REF);
		createEOperation(functionTypeExprOrRefEClass, FUNCTION_TYPE_EXPR_OR_REF___GET_DECLARED_THIS_TYPE);
		createEOperation(functionTypeExprOrRefEClass, FUNCTION_TYPE_EXPR_OR_REF___GET_FUNCTION_TYPE);
		createEOperation(functionTypeExprOrRefEClass, FUNCTION_TYPE_EXPR_OR_REF___GET_TYPE_VARS);
		createEOperation(functionTypeExprOrRefEClass, FUNCTION_TYPE_EXPR_OR_REF___GET_TYPE_VAR_UPPER_BOUNDS__TYPEVARIABLE);
		createEOperation(functionTypeExprOrRefEClass, FUNCTION_TYPE_EXPR_OR_REF___GET_FPARS);
		createEOperation(functionTypeExprOrRefEClass, FUNCTION_TYPE_EXPR_OR_REF___GET_RETURN_TYPE_REF);
		createEOperation(functionTypeExprOrRefEClass, FUNCTION_TYPE_EXPR_OR_REF___IS_GENERIC);
		createEOperation(functionTypeExprOrRefEClass, FUNCTION_TYPE_EXPR_OR_REF___IS_RAW);
		createEOperation(functionTypeExprOrRefEClass, FUNCTION_TYPE_EXPR_OR_REF___GET_FPAR_FOR_ARG_IDX__INT);
		createEOperation(functionTypeExprOrRefEClass, FUNCTION_TYPE_EXPR_OR_REF___GET_TYPE_REF_AS_STRING);

		functionTypeRefEClass = createEClass(FUNCTION_TYPE_REF);
		createEOperation(functionTypeRefEClass, FUNCTION_TYPE_REF___GET_DECLARED_THIS_TYPE);
		createEOperation(functionTypeRefEClass, FUNCTION_TYPE_REF___GET_TYPE_VARS);
		createEOperation(functionTypeRefEClass, FUNCTION_TYPE_REF___GET_TYPE_VAR_UPPER_BOUNDS__TYPEVARIABLE);
		createEOperation(functionTypeRefEClass, FUNCTION_TYPE_REF___GET_FPARS);
		createEOperation(functionTypeRefEClass, FUNCTION_TYPE_REF___GET_RETURN_TYPE_REF);

		functionTypeExpressionEClass = createEClass(FUNCTION_TYPE_EXPRESSION);
		createEAttribute(functionTypeExpressionEClass, FUNCTION_TYPE_EXPRESSION__BINDING);
		createEReference(functionTypeExpressionEClass, FUNCTION_TYPE_EXPRESSION__DECLARED_TYPE);
		createEReference(functionTypeExpressionEClass, FUNCTION_TYPE_EXPRESSION__DECLARED_THIS_TYPE);
		createEReference(functionTypeExpressionEClass, FUNCTION_TYPE_EXPRESSION__OWNED_TYPE_VARS);
		createEReference(functionTypeExpressionEClass, FUNCTION_TYPE_EXPRESSION__UNBOUND_TYPE_VARS);
		createEReference(functionTypeExpressionEClass, FUNCTION_TYPE_EXPRESSION__UNBOUND_TYPE_VARS_UPPER_BOUNDS);
		createEReference(functionTypeExpressionEClass, FUNCTION_TYPE_EXPRESSION__FPARS);
		createEReference(functionTypeExpressionEClass, FUNCTION_TYPE_EXPRESSION__RETURN_TYPE_REF);
		createEOperation(functionTypeExpressionEClass, FUNCTION_TYPE_EXPRESSION___GET_TYPE_VARS);
		createEOperation(functionTypeExpressionEClass, FUNCTION_TYPE_EXPRESSION___GET_TYPE_VAR_UPPER_BOUNDS__TYPEVARIABLE);

		deferredTypeRefEClass = createEClass(DEFERRED_TYPE_REF);
		createEOperation(deferredTypeRefEClass, DEFERRED_TYPE_REF___GET_TYPE_REF_AS_STRING);

		typeVariableMappingEClass = createEClass(TYPE_VARIABLE_MAPPING);
		createEReference(typeVariableMappingEClass, TYPE_VARIABLE_MAPPING__TYPE_VAR);
		createEReference(typeVariableMappingEClass, TYPE_VARIABLE_MAPPING__TYPE_ARG);

		// Create data types
		parameterizedTypeRefIterableEDataType = createEDataType(PARAMETERIZED_TYPE_REF_ITERABLE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		TypesPackage theTypesPackage = (TypesPackage)EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		typeRefEClass.getESuperTypes().add(this.getTypeArgument());
		staticBaseTypeRefEClass.getESuperTypes().add(this.getTypeRef());
		baseTypeRefEClass.getESuperTypes().add(this.getStaticBaseTypeRef());
		composedTypeRefEClass.getESuperTypes().add(this.getStaticBaseTypeRef());
		unionTypeExpressionEClass.getESuperTypes().add(this.getComposedTypeRef());
		intersectionTypeExpressionEClass.getESuperTypes().add(this.getComposedTypeRef());
		thisTypeRefEClass.getESuperTypes().add(this.getBaseTypeRef());
		thisTypeRefNominalEClass.getESuperTypes().add(this.getThisTypeRef());
		thisTypeRefStructuralEClass.getESuperTypes().add(this.getThisTypeRef());
		thisTypeRefStructuralEClass.getESuperTypes().add(this.getStructuralTypeRef());
		boundThisTypeRefEClass.getESuperTypes().add(this.getThisTypeRef());
		boundThisTypeRefEClass.getESuperTypes().add(this.getStructuralTypeRef());
		parameterizedTypeRefEClass.getESuperTypes().add(this.getBaseTypeRef());
		parameterizedTypeRefStructuralEClass.getESuperTypes().add(this.getParameterizedTypeRef());
		parameterizedTypeRefStructuralEClass.getESuperTypes().add(this.getStructuralTypeRef());
		existentialTypeRefEClass.getESuperTypes().add(this.getTypeRef());
		unknownTypeRefEClass.getESuperTypes().add(this.getTypeRef());
		constructorTypeRefEClass.getESuperTypes().add(this.getClassifierTypeRef());
		classifierTypeRefEClass.getESuperTypes().add(this.getBaseTypeRef());
		enumTypeRefEClass.getESuperTypes().add(this.getBaseTypeRef());
		wildcardEClass.getESuperTypes().add(this.getTypeArgument());
		functionTypeExprOrRefEClass.getESuperTypes().add(this.getStaticBaseTypeRef());
		functionTypeRefEClass.getESuperTypes().add(this.getParameterizedTypeRef());
		functionTypeRefEClass.getESuperTypes().add(this.getFunctionTypeExprOrRef());
		functionTypeExpressionEClass.getESuperTypes().add(this.getFunctionTypeExprOrRef());
		deferredTypeRefEClass.getESuperTypes().add(this.getTypeRef());

		// Initialize classes, features, and operations; add parameters
		initEClass(typeRefEClass, TypeRef.class, "TypeRef", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTypeRef_NullModifier(), theTypesPackage.getNullModifier(), "nullModifier", null, 0, 1, TypeRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTypeRef_UndefModifier(), theTypesPackage.getUndefModifier(), "undefModifier", null, 0, 1, TypeRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getTypeRef__GetModifiersAsString(), theEcorePackage.getEString(), "getModifiersAsString", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getTypeRef__IsFinalByType(), theEcorePackage.getEBoolean(), "isFinalByType", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getTypeRef__IsTreeHierarchyType(), theEcorePackage.getEBoolean(), "isTreeHierarchyType", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getTypeRef__IsDynamic(), theEcorePackage.getEBoolean(), "isDynamic", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getTypeRef__IsExistential(), theEcorePackage.getEBoolean(), "isExistential", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getTypeRef__IsGeneric(), theEcorePackage.getEBoolean(), "isGeneric", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getTypeRef__IsParameterized(), theEcorePackage.getEBoolean(), "isParameterized", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getTypeRef__IsRaw(), theEcorePackage.getEBoolean(), "isRaw", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getTypeRef__GetDeclaredUpperBound(), this.getParameterizedTypeRef(), "getDeclaredUpperBound", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getTypeRef__GetDeclaredLowerBound(), this.getParameterizedTypeRef(), "getDeclaredLowerBound", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getTypeRef__GetTypeArgs(), this.getTypeArgument(), "getTypeArgs", 0, -1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getTypeRef__UndefinedOrNull(), theEcorePackage.getEBoolean(), "undefinedOrNull", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getTypeRef__GetTypeRefAsString(), theEcorePackage.getEString(), "getTypeRefAsString", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getTypeRef__ToString(), theEcorePackage.getEString(), "toString", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getTypeRef__GetDeclaredType(), theTypesPackage.getType(), "getDeclaredType", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getTypeRef__IsTopType(), theEcorePackage.getEBoolean(), "isTopType", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getTypeRef__IsBottomType(), theEcorePackage.getEBoolean(), "isBottomType", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getTypeRef__GetTypingStrategy(), theTypesPackage.getTypingStrategy(), "getTypingStrategy", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getTypeRef__GetStructuralMembers(), theTypesPackage.getTStructMember(), "getStructuralMembers", 0, -1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getTypeRef__IsUseSiteStructuralTyping(), theEcorePackage.getEBoolean(), "isUseSiteStructuralTyping", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getTypeRef__IsDefSiteStructuralTyping(), theEcorePackage.getEBoolean(), "isDefSiteStructuralTyping", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(staticBaseTypeRefEClass, StaticBaseTypeRef.class, "StaticBaseTypeRef", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(baseTypeRefEClass, BaseTypeRef.class, "BaseTypeRef", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBaseTypeRef_Dynamic(), theEcorePackage.getEBoolean(), "dynamic", "false", 0, 1, BaseTypeRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getBaseTypeRef__GetModifiersAsString(), theEcorePackage.getEString(), "getModifiersAsString", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(composedTypeRefEClass, ComposedTypeRef.class, "ComposedTypeRef", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComposedTypeRef_CachedComposedMembers(), theTypesPackage.getTMember(), null, "cachedComposedMembers", null, 0, -1, ComposedTypeRef.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComposedTypeRef_OriginalComposedTypeRef(), this.getComposedTypeRef(), null, "originalComposedTypeRef", null, 0, 1, ComposedTypeRef.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComposedTypeRef_TypeRefs(), this.getTypeRef(), null, "typeRefs", null, 0, -1, ComposedTypeRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getComposedTypeRef__IsDynamic(), theEcorePackage.getEBoolean(), "isDynamic", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getComposedTypeRef__GetTypeRefAsString(), theEcorePackage.getEString(), "getTypeRefAsString", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(unionTypeExpressionEClass, UnionTypeExpression.class, "UnionTypeExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEOperation(getUnionTypeExpression__GetTypeRefAsString(), theEcorePackage.getEString(), "getTypeRefAsString", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(intersectionTypeExpressionEClass, IntersectionTypeExpression.class, "IntersectionTypeExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEOperation(getIntersectionTypeExpression__GetTypeRefAsString(), theEcorePackage.getEString(), "getTypeRefAsString", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(thisTypeRefEClass, ThisTypeRef.class, "ThisTypeRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEOperation(getThisTypeRef__GetTypeRefAsString(), theEcorePackage.getEString(), "getTypeRefAsString", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getThisTypeRef__GetTypingStrategy(), theTypesPackage.getTypingStrategy(), "getTypingStrategy", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getThisTypeRef__GetStructuralMembers(), theTypesPackage.getTStructMember(), "getStructuralMembers", 0, -1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getThisTypeRef__IsUseSiteStructuralTyping(), theEcorePackage.getEBoolean(), "isUseSiteStructuralTyping", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(thisTypeRefNominalEClass, ThisTypeRefNominal.class, "ThisTypeRefNominal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(thisTypeRefStructuralEClass, ThisTypeRefStructural.class, "ThisTypeRefStructural", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getThisTypeRefStructural_DefinedTypingStrategy(), theTypesPackage.getTypingStrategy(), "definedTypingStrategy", null, 0, 1, ThisTypeRefStructural.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getThisTypeRefStructural__GetTypingStrategy(), theTypesPackage.getTypingStrategy(), "getTypingStrategy", 0, 1, !IS_UNIQUE, IS_ORDERED);

		EOperation op = initEOperation(getThisTypeRefStructural__SetTypingStrategy__TypingStrategy(), null, "setTypingStrategy", 0, 1, !IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theTypesPackage.getTypingStrategy(), "typingStrategy", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getThisTypeRefStructural__IsUseSiteStructuralTyping(), theEcorePackage.getEBoolean(), "isUseSiteStructuralTyping", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getThisTypeRefStructural__GetStructuralMembers(), theTypesPackage.getTStructMember(), "getStructuralMembers", 0, -1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getThisTypeRefStructural__GetTypeRefAsString(), theEcorePackage.getEString(), "getTypeRefAsString", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(boundThisTypeRefEClass, BoundThisTypeRef.class, "BoundThisTypeRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBoundThisTypeRef_ActualThisTypeRef(), this.getParameterizedTypeRef(), null, "actualThisTypeRef", null, 0, 1, BoundThisTypeRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBoundThisTypeRef_DefinedTypingStrategy(), theTypesPackage.getTypingStrategy(), "definedTypingStrategy", null, 0, 1, BoundThisTypeRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getBoundThisTypeRef__GetTypingStrategy(), theTypesPackage.getTypingStrategy(), "getTypingStrategy", 0, 1, !IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getBoundThisTypeRef__SetTypingStrategy__TypingStrategy(), null, "setTypingStrategy", 0, 1, !IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theTypesPackage.getTypingStrategy(), "typingStrategy", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getBoundThisTypeRef__GetTypeRefAsString(), theEcorePackage.getEString(), "getTypeRefAsString", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getBoundThisTypeRef__GetDeclaredUpperBound(), this.getParameterizedTypeRef(), "getDeclaredUpperBound", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getBoundThisTypeRef__IsDefSiteStructuralTyping(), theEcorePackage.getEBoolean(), "isDefSiteStructuralTyping", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getBoundThisTypeRef__IsUseSiteStructuralTyping(), theEcorePackage.getEBoolean(), "isUseSiteStructuralTyping", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getBoundThisTypeRef__GetStructuralMembers(), theTypesPackage.getTStructMember(), "getStructuralMembers", 0, -1, !IS_UNIQUE, IS_ORDERED);

		initEClass(parameterizedTypeRefEClass, ParameterizedTypeRef.class, "ParameterizedTypeRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParameterizedTypeRef_DeclaredType(), theTypesPackage.getType(), null, "declaredType", null, 0, 1, ParameterizedTypeRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getParameterizedTypeRef_TypeArgs(), this.getTypeArgument(), null, "typeArgs", null, 0, -1, ParameterizedTypeRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParameterizedTypeRef_DefinedTypingStrategy(), theTypesPackage.getTypingStrategy(), "definedTypingStrategy", null, 0, 1, ParameterizedTypeRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getParameterizedTypeRef__GetTypingStrategy(), theTypesPackage.getTypingStrategy(), "getTypingStrategy", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getParameterizedTypeRef__ContainsWildcards(), theEcorePackage.getEBoolean(), "containsWildcards", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getParameterizedTypeRef__GetTypeRefAsString(), theEcorePackage.getEString(), "getTypeRefAsString", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getParameterizedTypeRef__IsParameterized(), theEcorePackage.getEBoolean(), "isParameterized", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getParameterizedTypeRef__IsGeneric(), theEcorePackage.getEBoolean(), "isGeneric", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getParameterizedTypeRef__IsRaw(), theEcorePackage.getEBoolean(), "isRaw", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getParameterizedTypeRef__ContainsUnboundTypeVariables(), theEcorePackage.getEBoolean(), "containsUnboundTypeVariables", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getParameterizedTypeRef__IsUseSiteStructuralTyping(), theEcorePackage.getEBoolean(), "isUseSiteStructuralTyping", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getParameterizedTypeRef__IsDefSiteStructuralTyping(), theEcorePackage.getEBoolean(), "isDefSiteStructuralTyping", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(structuralTypeRefEClass, StructuralTypeRef.class, "StructuralTypeRef", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStructuralTypeRef_AstStructuralMembers(), theTypesPackage.getTStructMember(), null, "astStructuralMembers", null, 0, -1, StructuralTypeRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStructuralTypeRef_StructuralType(), theTypesPackage.getTStructuralType(), null, "structuralType", null, 0, 1, StructuralTypeRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStructuralTypeRef_GenStructuralMembers(), theTypesPackage.getTStructMember(), null, "genStructuralMembers", null, 0, -1, StructuralTypeRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStructuralTypeRef_PostponedSubstitutions(), this.getTypeVariableMapping(), null, "postponedSubstitutions", null, 0, -1, StructuralTypeRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getStructuralTypeRef__GetTypingStrategy(), theTypesPackage.getTypingStrategy(), "getTypingStrategy", 0, 1, !IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getStructuralTypeRef__SetTypingStrategy__TypingStrategy(), null, "setTypingStrategy", 0, 1, !IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theTypesPackage.getTypingStrategy(), "typingStrategy", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getStructuralTypeRef__GetStructuralMembers(), theTypesPackage.getTStructMember(), "getStructuralMembers", 0, -1, !IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getStructuralTypeRef__HasPostponedSubstitutionFor__TypeVariable(), theEcorePackage.getEBoolean(), "hasPostponedSubstitutionFor", 0, 1, !IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theTypesPackage.getTypeVariable(), "typeVar", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(parameterizedTypeRefStructuralEClass, ParameterizedTypeRefStructural.class, "ParameterizedTypeRefStructural", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEOperation(getParameterizedTypeRefStructural__GetTypingStrategy(), theTypesPackage.getTypingStrategy(), "getTypingStrategy", 0, 1, !IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getParameterizedTypeRefStructural__SetTypingStrategy__TypingStrategy(), null, "setTypingStrategy", 0, 1, !IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theTypesPackage.getTypingStrategy(), "typingStrategy", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getParameterizedTypeRefStructural__GetStructuralMembers(), theTypesPackage.getTStructMember(), "getStructuralMembers", 0, -1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getParameterizedTypeRefStructural__GetTypeRefAsString(), theEcorePackage.getEString(), "getTypeRefAsString", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(existentialTypeRefEClass, ExistentialTypeRef.class, "ExistentialTypeRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExistentialTypeRef_Wildcard(), this.getWildcard(), null, "wildcard", null, 0, 1, ExistentialTypeRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExistentialTypeRef_BoundTypeVariable(), theTypesPackage.getTypeVariable(), null, "boundTypeVariable", null, 0, 1, ExistentialTypeRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getExistentialTypeRef__IsExistential(), theEcorePackage.getEBoolean(), "isExistential", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getExistentialTypeRef__IsGeneric(), theEcorePackage.getEBoolean(), "isGeneric", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getExistentialTypeRef__IsParameterized(), theEcorePackage.getEBoolean(), "isParameterized", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getExistentialTypeRef__GetTypeRefAsString(), theEcorePackage.getEString(), "getTypeRefAsString", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(unknownTypeRefEClass, UnknownTypeRef.class, "UnknownTypeRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEOperation(getUnknownTypeRef__GetTypeRefAsString(), theEcorePackage.getEString(), "getTypeRefAsString", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(constructorTypeRefEClass, ConstructorTypeRef.class, "ConstructorTypeRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEOperation(getConstructorTypeRef__GetTypeRefAsString(), theEcorePackage.getEString(), "getTypeRefAsString", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(classifierTypeRefEClass, ClassifierTypeRef.class, "ClassifierTypeRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClassifierTypeRef_StaticTypeRef(), this.getStaticBaseTypeRef(), null, "staticTypeRef", null, 0, 1, ClassifierTypeRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getClassifierTypeRef__GetTypeRefAsString(), theEcorePackage.getEString(), "getTypeRefAsString", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getClassifierTypeRef__StaticType(), theTypesPackage.getType(), "staticType", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(enumTypeRefEClass, EnumTypeRef.class, "EnumTypeRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEnumTypeRef_EnumType(), theTypesPackage.getTEnum(), null, "enumType", null, 0, 1, EnumTypeRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getEnumTypeRef__GetTypeRefAsString(), theEcorePackage.getEString(), "getTypeRefAsString", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(typeArgumentEClass, TypeArgument.class, "TypeArgument", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEOperation(getTypeArgument__GetTypeRefAsString(), theEcorePackage.getEString(), "getTypeRefAsString", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getTypeArgument__ContainsWildcards(), theEcorePackage.getEBoolean(), "containsWildcards", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getTypeArgument__ContainsUnboundTypeVariables(), theEcorePackage.getEBoolean(), "containsUnboundTypeVariables", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(wildcardEClass, Wildcard.class, "Wildcard", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWildcard_DeclaredUpperBound(), this.getTypeRef(), null, "declaredUpperBound", null, 0, 1, Wildcard.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWildcard_DeclaredLowerBound(), this.getTypeRef(), null, "declaredLowerBound", null, 0, 1, Wildcard.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getWildcard__GetDeclaredOrImplicitUpperBounds(), this.getTypeRef(), "getDeclaredOrImplicitUpperBounds", 0, -1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getWildcard__GetTypeRefAsString(), theEcorePackage.getEString(), "getTypeRefAsString", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(functionTypeExprOrRefEClass, FunctionTypeExprOrRef.class, "FunctionTypeExprOrRef", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEOperation(getFunctionTypeExprOrRef__GetDeclaredThisType(), this.getTypeRef(), "getDeclaredThisType", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getFunctionTypeExprOrRef__GetFunctionType(), theTypesPackage.getTFunction(), "getFunctionType", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getFunctionTypeExprOrRef__GetTypeVars(), theTypesPackage.getTypeVariable(), "getTypeVars", 0, -1, !IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getFunctionTypeExprOrRef__GetTypeVarUpperBounds__TypeVariable(), this.getTypeRef(), "getTypeVarUpperBounds", 0, -1, !IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theTypesPackage.getTypeVariable(), "typeVar", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getFunctionTypeExprOrRef__GetFpars(), theTypesPackage.getTFormalParameter(), "getFpars", 0, -1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getFunctionTypeExprOrRef__GetReturnTypeRef(), this.getTypeRef(), "getReturnTypeRef", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getFunctionTypeExprOrRef__IsGeneric(), theEcorePackage.getEBoolean(), "isGeneric", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getFunctionTypeExprOrRef__IsRaw(), theEcorePackage.getEBoolean(), "isRaw", 0, 1, !IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getFunctionTypeExprOrRef__GetFparForArgIdx__int(), theTypesPackage.getTFormalParameter(), "getFparForArgIdx", 0, 1, !IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEInt(), "argIndex", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getFunctionTypeExprOrRef__GetTypeRefAsString(), theEcorePackage.getEString(), "getTypeRefAsString", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(functionTypeRefEClass, FunctionTypeRef.class, "FunctionTypeRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEOperation(getFunctionTypeRef__GetDeclaredThisType(), this.getTypeRef(), "getDeclaredThisType", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getFunctionTypeRef__GetTypeVars(), theTypesPackage.getTypeVariable(), "getTypeVars", 0, -1, !IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getFunctionTypeRef__GetTypeVarUpperBounds__TypeVariable(), this.getTypeRef(), "getTypeVarUpperBounds", 0, -1, !IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theTypesPackage.getTypeVariable(), "typeVar", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getFunctionTypeRef__GetFpars(), theTypesPackage.getTFormalParameter(), "getFpars", 0, -1, !IS_UNIQUE, IS_ORDERED);

		initEOperation(getFunctionTypeRef__GetReturnTypeRef(), this.getTypeRef(), "getReturnTypeRef", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(functionTypeExpressionEClass, FunctionTypeExpression.class, "FunctionTypeExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFunctionTypeExpression_Binding(), theEcorePackage.getEBoolean(), "binding", null, 0, 1, FunctionTypeExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFunctionTypeExpression_DeclaredType(), theTypesPackage.getTFunction(), null, "declaredType", null, 0, 1, FunctionTypeExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFunctionTypeExpression_DeclaredThisType(), this.getTypeRef(), null, "declaredThisType", null, 0, 1, FunctionTypeExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFunctionTypeExpression_OwnedTypeVars(), theTypesPackage.getTypeVariable(), null, "ownedTypeVars", null, 0, -1, FunctionTypeExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFunctionTypeExpression_UnboundTypeVars(), theTypesPackage.getTypeVariable(), null, "unboundTypeVars", null, 0, -1, FunctionTypeExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFunctionTypeExpression_UnboundTypeVarsUpperBounds(), this.getTypeRef(), null, "unboundTypeVarsUpperBounds", null, 0, -1, FunctionTypeExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFunctionTypeExpression_Fpars(), theTypesPackage.getTFormalParameter(), null, "fpars", null, 0, -1, FunctionTypeExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFunctionTypeExpression_ReturnTypeRef(), this.getTypeRef(), null, "returnTypeRef", null, 0, 1, FunctionTypeExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getFunctionTypeExpression__GetTypeVars(), theTypesPackage.getTypeVariable(), "getTypeVars", 0, -1, !IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getFunctionTypeExpression__GetTypeVarUpperBounds__TypeVariable(), this.getTypeRef(), "getTypeVarUpperBounds", 0, -1, !IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theTypesPackage.getTypeVariable(), "typeVar", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(deferredTypeRefEClass, DeferredTypeRef.class, "DeferredTypeRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEOperation(getDeferredTypeRef__GetTypeRefAsString(), theEcorePackage.getEString(), "getTypeRefAsString", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(typeVariableMappingEClass, TypeVariableMapping.class, "TypeVariableMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTypeVariableMapping_TypeVar(), theTypesPackage.getTypeVariable(), null, "typeVar", null, 0, 1, TypeVariableMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTypeVariableMapping_TypeArg(), this.getTypeArgument(), null, "typeArg", null, 0, 1, TypeVariableMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize data types
		initEDataType(parameterizedTypeRefIterableEDataType, Iterable.class, "ParameterizedTypeRefIterable", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS, "java.lang.Iterable<eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef>");

		// Create resource
		createResource(eNS_URI);
	}

} //TypeRefsPackageImpl
