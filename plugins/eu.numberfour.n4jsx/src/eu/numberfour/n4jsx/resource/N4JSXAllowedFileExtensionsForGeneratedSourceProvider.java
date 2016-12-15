package eu.numberfour.n4jsx.resource;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.unmodifiableCollection;

import eu.numberfour.n4js.resource.AllowedFileExtensionsForGeneratedSourceProvider;

/**
 * This provider enables .n4js and .js for generated source
 */
public class N4JSXAllowedFileExtensionsForGeneratedSourceProvider
		implements AllowedFileExtensionsForGeneratedSourceProvider {

	@Override
	public Iterable<String> getAllowedFileExtensions() {
		return unmodifiableCollection(newHashSet(
				"n4jsx", "jsx"));
	}

}
