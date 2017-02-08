/**
 * Copyright (c) 2017 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   NumberFour AG - Initial API and implementation
 */
package eu.numberfour.n4js.utils;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * A lazily initialized optional value. The value is initialized using a supplier that is called at most once when
 * {@link #get()} is called for the first time. Subsequent calls to {@link #get()} will never call the supplier again
 * even if the supplier returned <code>null</code>.
 */
public class Lazy<T> {
	private T value;
	private Supplier<T> supplier;

	/**
	 * Creates a new instance using the given supplier. This is just a convenience method.
	 *
	 * @param supplier
	 *            the supplier to use
	 * @return the new instance
	 */
	public static <T> Lazy<T> create(Supplier<T> supplier) {
		return new Lazy<>(supplier);
	}

	/**
	 * Creates a new instance that uses the given supplier to initialized the lazily computed value. The given supplier
	 * is called at most once.
	 *
	 * @param supplier
	 *            the supplier to use
	 */
	public Lazy(Supplier<T> supplier) {
		this.supplier = Objects.requireNonNull(supplier);
	}

	/**
	 * Indicate whether this lazy value has been initialized.
	 *
	 * @return <code>true</code> if this value has been initialized and false otherwise
	 */
	public boolean isInitialized() {
		return supplier == null;
	}

	/**
	 * Performs lazy initialization if necessary and returns the result, which may be <code>null</code>.
	 *
	 * @return the lazily initialized value, which may be <code>null</code>
	 */
	public T get() {
		if (!isInitialized()) {
			value = supplier.get();
			supplier = null;
		}
		return value;
	}

	/**
	 * Apply the given function to the value if it has been initialized, otherwise do nothing.
	 *
	 * @param consumer
	 *            the function to apply
	 */
	public void ifPresent(Consumer<T> consumer) {
		if (isInitialized())
			consumer.accept(value);
	}
}
