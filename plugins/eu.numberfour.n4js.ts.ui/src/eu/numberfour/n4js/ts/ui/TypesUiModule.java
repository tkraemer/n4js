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
package eu.numberfour.n4js.ts.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.documentation.IEObjectDocumentationProvider;
import org.eclipse.xtext.resource.SynchronizedXtextResourceSet;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.ui.editor.hover.IEObjectHoverProvider;
import org.eclipse.xtext.ui.editor.model.IResourceForEditorInputFactory;

import eu.numberfour.n4js.ts.findReferences.TargetURIKey;
import eu.numberfour.n4js.ts.ui.labeling.TypesDocumentationProvider;
import eu.numberfour.n4js.ts.ui.labeling.TypesHoverProvider;
import eu.numberfour.n4js.ts.ui.navigation.URIBasedStorageEditorInputFactory;
import eu.numberfour.n4js.ts.ui.search.BuiltinSchemeAwareTargetURIKey;
import eu.numberfour.n4js.ts.ui.search.LabellingReferenceFinder;
import eu.numberfour.n4js.ts.ui.search.LabellingReferenceQueryExecutor;

/**
 * Use this class to register components to be used within the IDE.
 */
@SuppressWarnings("restriction")
public class TypesUiModule extends eu.numberfour.n4js.ts.ui.AbstractTypesUiModule {
	/**
	 * Create a new UIModule in the given plugin.
	 */
	public TypesUiModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	/**
	 * De-configure the hard coded built in scheme from the runtime bundle.
	 */
	public Class<? extends XtextResourceSet> bindXtextResourceSet() {
		return SynchronizedXtextResourceSet.class;
	}

	/**
	 * Bind custom IEObjectHoverProvider.
	 */
	public Class<? extends IEObjectHoverProvider> bindIEObjectHoverProvider() {
		return TypesHoverProvider.class;
	}

	/**
	 * Bind custom IEObjectHoverProvider.
	 */
	public Class<? extends IEObjectDocumentationProvider> bindIEObjectDocumentationProvider() {
		return TypesDocumentationProvider.class;
	}

	/**
	 * Bind an implementation can handle find references to builtin types.
	 */
	public Class<? extends TargetURIKey> bindTargetURIKey() {
		return BuiltinSchemeAwareTargetURIKey.class;
	}

	/**
	 * Bind the {@link org.eclipse.xtext.ui.editor.findrefs.ReferenceQueryExecutor} that maps to types.
	 */
	public Class<? extends org.eclipse.xtext.ui.editor.findrefs.ReferenceQueryExecutor> bindReferenceQueryExecutor() {
		return LabellingReferenceQueryExecutor.class;
	}

	/**
	 * Bind the {@link org.eclipse.xtext.ui.editor.findrefs.IReferenceFinder} that find references solely to types (and
	 * TVariables, IdentifiableElement and TEnumLiterals).
	 */
	public Class<? extends org.eclipse.xtext.ui.editor.findrefs.IReferenceFinder> bindIReferenceFinder() {
		return LabellingReferenceFinder.class;
	}

	@Override
	public Class<? extends IResourceForEditorInputFactory> bindIResourceForEditorInputFactory() {
		return URIBasedStorageEditorInputFactory.class;
	}
}
