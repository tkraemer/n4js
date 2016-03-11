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
package eu.numberfour.n4js.generator;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.multibindings.Multibinder;

import eu.numberfour.n4js.generator.common.ISubGenerator;

/**
 *         API
 */
public class CompositeGeneratorModule implements Module {

	/*
	 * (non-Javadoc)
	 *
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	public void configure(Binder binder) {
		Multibinder<ISubGenerator> generatorBinder = Multibinder.newSetBinder(binder, ISubGenerator.class);

		Class<? extends ISubGenerator> clazz = null;
		for (SubGeneratorType type : SubGeneratorType.values()) {
			clazz = type.getSubGeneratorClass();
			if (clazz != null) {
				generatorBinder.addBinding().to(clazz);
			}
		}
	}
}
