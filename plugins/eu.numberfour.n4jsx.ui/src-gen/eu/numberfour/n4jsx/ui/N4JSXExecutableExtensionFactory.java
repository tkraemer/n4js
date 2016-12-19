/*
 * generated by Xtext 2.10.0
 */
package eu.numberfour.n4jsx.ui;

import com.google.inject.Injector;
import eu.numberfour.n4jsx.ui.internal.N4JSXActivator;
import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class N4JSXExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return N4JSXActivator.getInstance().getBundle();
	}
	
	@Override
	protected Injector getInjector() {
		return N4JSXActivator.getInstance().getInjector(N4JSXActivator.EU_NUMBERFOUR_N4JSX_N4JSX);
	}
	
}
