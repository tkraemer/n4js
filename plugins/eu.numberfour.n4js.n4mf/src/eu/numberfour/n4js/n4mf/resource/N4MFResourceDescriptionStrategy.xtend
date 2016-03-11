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
package eu.numberfour.n4js.n4mf.resource

import com.google.common.collect.ImmutableMap
import com.google.inject.Singleton
import eu.numberfour.n4js.n4mf.ProjectDescription
import java.util.Collections
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.resource.EObjectDescription
import org.eclipse.xtext.resource.IEObjectDescription
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionStrategy
import org.eclipse.xtext.util.IAcceptor

import static extension com.google.common.base.Strings.nullToEmpty
import eu.numberfour.n4js.n4mf.ProjectType

/**
 * Customized resource description strategy for storing additional user data on the descriptions.
 */
@Singleton
class N4MFResourceDescriptionStrategy extends DefaultResourceDescriptionStrategy {

	/** The key of the user data for retrieving the project type as a string. */
	public static val PROJECT_TYPE_KEY = 'prjectType';

	/** The key of the user data for retrieving the artifact ID. */
	public static val PROJECT_ID_KEY = 'prjectId';

	/** The key of the user data for retrieving the library dependencies for a particular project. */
	public static val LIB_DEPENDENCIES_KEY = 'libDependencies';

	/** Key for the project implementation ID value. {@code null} value will be mapped to empty string. */
	public static val IMPLEMENTATION_ID_KEY = 'implementationId';

	@Override
	override createEObjectDescriptions(EObject eObject, IAcceptor<IEObjectDescription> acceptor) {

		if (null === eObject) {
			return false;
		}

		if (null === getQualifiedNameProvider()) {
			return false;
		}

		val qualifiedName = qualifiedNameProvider.getFullyQualifiedName(eObject);
		if (qualifiedName !== null) {
			val userData = eObject.userData;
			acceptor.accept(new EObjectDescription(qualifiedName, eObject, userData));
		}

		return true;
	}

	private dispatch def getUserData(EObject object) {
		Collections.<String, String>emptyMap;
	}

	private dispatch def getUserData(ProjectDescription it) {
		val builder = ImmutableMap.builder;
		builder.put(PROJECT_TYPE_KEY, '''«projectType»''');
		builder.put(PROJECT_ID_KEY, artifactId.nullToEmpty);
		builder.put(IMPLEMENTATION_ID_KEY, implementationId.nullToEmpty);
		return builder.build;
	}

	/**
	 * Optionally returns with the project type extracted from the user data of the given EObject description argument.
	 */
	static def getProjectType(IEObjectDescription it) {
		if (null === it) {
			return null;
		}
		val typeLiteral = it.getUserData(PROJECT_TYPE_KEY);
		if (null === typeLiteral) {
			return null;
		}
		return ProjectType.get(typeLiteral);
	}

	/**
	 * Optionally returns with the project artifact ID extracted from the user data of the given EObject description argument.
	 */
	static def getProjectId(IEObjectDescription it) {
		if (null === it) {
			return null;
		}
		return it.getUserData(PROJECT_ID_KEY);
	}

}
