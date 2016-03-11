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
package eu.numberfour.n4js.scoping.accessModifiers;

import org.eclipse.xtext.resource.IEObjectDescription;

import eu.numberfour.n4js.validation.IssueCodes;
import eu.numberfour.n4js.ts.typeRefs.ConstructorTypeRef;

/**
 * Error-Description of invisible constructor-access
 */
public class InvisibleCtorDescription extends InvisibleMemberDescription {

	private final ConstructorTypeRef typeRef;

	/**
	 * @param delegate
	 *            the constructor
	 * @param type
	 *            reference to the constructor - used for better error messages.
	 */
	public InvisibleCtorDescription(IEObjectDescription delegate, ConstructorTypeRef type) {
		super(delegate);
		this.typeRef = type;
	}

	@Override
	public String getMessage() {
		String containerName = typeRef.staticType().getTypeAsString();
		return IssueCodes.getMessageForVIS_NEW_CANNOT_INSTANTIATE_INVISIBLE_CONSTRUCTOR(containerName);
	}

	@Override
	public String getIssueCode() {
		return IssueCodes.VIS_NEW_CANNOT_INSTANTIATE_INVISIBLE_CONSTRUCTOR;
	}
}
