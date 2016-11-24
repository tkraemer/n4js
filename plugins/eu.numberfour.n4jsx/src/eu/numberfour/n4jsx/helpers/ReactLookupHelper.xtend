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
package eu.numberfour.n4jsx.helpers

import com.google.inject.Inject
import eu.numberfour.n4js.ts.types.TClassifier
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.resource.IEObjectDescription
import org.eclipse.xtext.scoping.IScope
import org.eclipse.xtext.scoping.IScopeProvider

/**
 * This helper provides utilities for looking up React definitions
 */
class ReactLookupHelper {
	
	@Inject
	IScopeProvider scopeProvider

	def lookUpReactClassifier (EObject context, EReference reference, String reactName, String reactModuleName) {
		val IScope scope = scopeProvider.getScope(context, reference)
		val IEObjectDescription eod = scope.allElements.findFirst [ e |
			val classifier = e.EObjectOrProxy;
			if (classifier instanceof TClassifier) {
				return reactName == classifier.exportedName && reactModuleName == classifier.containingModule.qualifiedName
			}
			return false;
		]
		
		if (eod === null) 
			return null

		val classifier = eod.EObjectOrProxy;
		if (classifier.eIsProxy) {
			EcoreUtil2.resolve(classifier, context.eResource)
		}
		return classifier as TClassifier;
	}

}
