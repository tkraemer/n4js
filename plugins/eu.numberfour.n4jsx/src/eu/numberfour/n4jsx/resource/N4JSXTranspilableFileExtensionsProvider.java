package eu.numberfour.n4jsx.resource;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.unmodifiableCollection;

import eu.numberfour.n4js.resource.TranspilableFileExtensionsProvider;
import eu.numberfour.n4jsx.N4JSXGlobals;

/**
 * This provider enables .n4jsx and .jsx for generated source
 */
public class N4JSXTranspilableFileExtensionsProvider
		implements TranspilableFileExtensionsProvider {

	@Override
	public Iterable<String> getTranspilableFileExtensions() {
		return unmodifiableCollection(newHashSet(
				N4JSXGlobals.N4JSX_FILE_EXTENSION, N4JSXGlobals.JSX_FILE_EXTENSION));
	}

}
