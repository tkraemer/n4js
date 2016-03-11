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
package eu.numberfour.n4js.ts.types.util;

import java.util.List;

import com.google.common.collect.Lists;

import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeRefsFactory;
import eu.numberfour.n4js.ts.types.ContainerType;
import eu.numberfour.n4js.ts.types.PrimitiveType;
import eu.numberfour.n4js.ts.types.TClass;
import eu.numberfour.n4js.ts.types.TInterface;

/**
 */
public class AllSuperTypeRefsCollector extends AbstractCompleteHierarchyTraverser<List<ParameterizedTypeRef>> {

	private final List<ParameterizedTypeRef> result;

	/**
	 * Creates a new collector.
	 *
	 * @param type
	 *            the type to start with.
	 */
	public AllSuperTypeRefsCollector(ContainerType<?> type) {
		super(type);
		result = Lists.newArrayList();
	}

	@Override
	protected List<ParameterizedTypeRef> doGetResult() {
		return result;
	}

	@Override
	protected void doProcess(ContainerType<?> containerType) {
		if (containerType instanceof TClass) {
			TClass casted = (TClass) containerType;
			ParameterizedTypeRef superType = casted.getSuperClassRef();
			result.addAll(casted.getImplementedInterfaceRefs());
			if (superType != null) {
				result.add(superType);
			}
		}
		else if (containerType instanceof TInterface) {
			TInterface casted = (TInterface) containerType;
			result.addAll(casted.getSuperInterfaceRefs());
		}
		else if (containerType instanceof PrimitiveType) {
			PrimitiveType assignmentCompatible = ((PrimitiveType) containerType).getAssignmentCompatible();
			if (assignmentCompatible != null) {
				ParameterizedTypeRef typeRef = TypeRefsFactory.eINSTANCE.createParameterizedTypeRef();
				typeRef.setDeclaredType(assignmentCompatible);
				result.add(typeRef);
			}
		}
	}

	/**
	 * Convenience method to create a new instance of {@link AllSuperTypeRefsCollector} and immediately return its
	 * result.
	 *
	 * @param type
	 *            the type to start with.
	 * @return transitive closure of all super classes, consumed roles and implemented interfaces.
	 */
	public static final List<ParameterizedTypeRef> collect(ContainerType<?> type) {
		return new AllSuperTypeRefsCollector(type).getResult();
	}
}
