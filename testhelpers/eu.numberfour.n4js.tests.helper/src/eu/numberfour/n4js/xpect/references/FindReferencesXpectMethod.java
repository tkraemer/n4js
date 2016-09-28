/*******************************************************************************
 * Copyright (c) 2012 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package eu.numberfour.n4js.xpect.references;

import java.util.ArrayList;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.findReferences.IReferenceFinder;
import org.eclipse.xtext.findReferences.TargetURIs;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.junit.runner.RunWith;
import org.xpect.XpectImport;
import org.xpect.parameter.ParameterParser;
import org.xpect.runner.Xpect;
import org.xpect.runner.XpectRunner;
import org.xpect.xtext.lib.setup.XtextStandaloneSetup;
import org.xpect.xtext.lib.setup.XtextWorkspaceSetup;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Provider;

import eu.numberfour.n4js.ts.findReferences.SimpleResourceAccess;
import eu.numberfour.n4js.ts.types.IdentifiableElement;
import eu.numberfour.n4js.xpect.scoping.IN4JSCommaSeparatedValuesExpectation;
import eu.numberfour.n4js.xpect.scoping.N4JSCommaSeparatedValuesExpectation;

@SuppressWarnings("restriction")
@RunWith(XpectRunner.class)
@XpectImport({ XtextStandaloneSetup.class, XtextWorkspaceSetup.class })
public class FindReferencesXpectMethod {

	@Inject
	private Provider<TargetURIs> targetURISetProvider;

	@Inject
	private IReferenceFinder referenceFinder;

	@Inject
	private ResourceDescriptionsProvider resourceDescriptionsProvider;

	/**
	 * Compares scope including resource name and line number.
	 */
	@Xpect
	@ParameterParser(syntax = "('at' arg1=OFFSET)?")
	public void findReferences( //
			@N4JSCommaSeparatedValuesExpectation IN4JSCommaSeparatedValuesExpectation expectation,
			EObject arg1) {
		Resource eResource = arg1.eResource();
		TargetURIs targets = targetURISetProvider.get();
		IResourceDescriptions index = resourceDescriptionsProvider.getResourceDescriptions(eResource);

		final IdentifiableElement targetElem;
		// if (arg1.getEObject() instanceof ParameterizedPropertyAccessExpression
		// && arg1.getCrossEReference() == N4JSPackage.eINSTANCE
		// .getParameterizedPropertyAccessExpression_Property()) {
		// targetElem = ((ParameterizedPropertyAccessExpression) arg1.getEObject()).getProperty();
		// } else if (arg1.getEObject() instanceof IdentifierRef
		// && arg1.getCrossEReference() == N4JSPackage.eINSTANCE.getIdentifierRef_Id()) {
		// targetElem = ((IdentifierRef) arg1.getEObject()).getId();
		// // TODO more cases
		// } else {
		// // error, unsupported region
		// }

		ArrayList<EObject> result = Lists.newArrayList();
		IReferenceFinder.Acceptor acceptor = new IReferenceFinder.Acceptor() {
			@Override
			public void accept(EObject src, URI srcURI, EReference eRef, int idx, EObject tgtOrProxy, URI tgtURI) {
				result.add(src);
			}

			@Override
			public void accept(IReferenceDescription description) {
				throw new UnsupportedOperationException("Should not be called");
			}
		};

		SimpleResourceAccess resourceAccess = new SimpleResourceAccess(eResource.getResourceSet());
		referenceFinder.findAllReferences(targets, resourceAccess, index, acceptor, null);

	}

}
