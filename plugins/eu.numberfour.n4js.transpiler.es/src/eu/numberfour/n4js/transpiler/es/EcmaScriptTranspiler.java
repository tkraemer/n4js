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
package eu.numberfour.n4js.transpiler.es;

import java.io.IOException;
import java.io.Writer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.google.inject.Provider;

import eu.numberfour.n4js.projectModel.ProjectUtils;
import eu.numberfour.n4js.resource.N4JSResource;
import eu.numberfour.n4js.transpiler.AbstractTranspiler;
import eu.numberfour.n4js.transpiler.Transformation;
import eu.numberfour.n4js.transpiler.TranspilerState;
import eu.numberfour.n4js.transpiler.es.transform.ApiImplStubGenerationTransformation;
import eu.numberfour.n4js.transpiler.es.transform.ArrowFunction_Part1_Transformation;
import eu.numberfour.n4js.transpiler.es.transform.ArrowFunction_Part2_Transformation;
import eu.numberfour.n4js.transpiler.es.transform.BlockTransformation;
import eu.numberfour.n4js.transpiler.es.transform.ClassDeclarationTransformation;
import eu.numberfour.n4js.transpiler.es.transform.DependencyInjectionTransformation;
import eu.numberfour.n4js.transpiler.es.transform.DestructuringTransformation;
import eu.numberfour.n4js.transpiler.es.transform.EnumAccessTransformation;
import eu.numberfour.n4js.transpiler.es.transform.EnumDeclarationTransformation;
import eu.numberfour.n4js.transpiler.es.transform.ExpressionTransformation;
import eu.numberfour.n4js.transpiler.es.transform.FormalParameterTransformation;
import eu.numberfour.n4js.transpiler.es.transform.FunctionDeclarationTransformation;
import eu.numberfour.n4js.transpiler.es.transform.InterfaceDeclarationTransformation;
import eu.numberfour.n4js.transpiler.es.transform.MemberPatchingTransformation;
import eu.numberfour.n4js.transpiler.es.transform.ModuleWrappingTransformation;
import eu.numberfour.n4js.transpiler.es.transform.SanitizeImportsTransformation;
import eu.numberfour.n4js.transpiler.es.transform.StaticPolyfillTransformation;
import eu.numberfour.n4js.transpiler.es.transform.SuperLiteralTransformation;
import eu.numberfour.n4js.transpiler.es.transform.TrimTransformation;
import eu.numberfour.n4js.utils.ResourceType;

/**
 * Transpiles N4JS to ECMAScript. The exact language version of the target code depends on configuration parameters, so
 * this class covers transpilation to plain ES5, ES5 with selected additional ES6 features and full ES6.
 * <p>
 * NOTE: the current implementation only supports transpilation to "ES5 with selected additional ES6 features".
 */
public class EcmaScriptTranspiler extends AbstractTranspiler {

	@Inject
	private Provider<DestructuringTransformation> destructuringTransformation;
	@Inject
	private Provider<MemberPatchingTransformation> memberPatchingTransformationProvider;
	@Inject
	private Provider<ApiImplStubGenerationTransformation> apiImplStubGenerationTransformationProvider;
	@Inject
	private Provider<StaticPolyfillTransformation> staticPolyfillTransformationProvider;
	@Inject
	private Provider<SuperLiteralTransformation> superLiteralTransformationProvider;
	@Inject
	private Provider<ExpressionTransformation> expressionTransformationProvider;
	@Inject
	private Provider<EnumAccessTransformation> enumAccessTransformationProvider;
	@Inject
	private Provider<DependencyInjectionTransformation> dependencyInjectionTransformation;
	@Inject
	private Provider<ClassDeclarationTransformation> classDeclarationTransformationProvider;
	@Inject
	private Provider<InterfaceDeclarationTransformation> interfaceDeclarationTransformationProvider;
	@Inject
	private Provider<EnumDeclarationTransformation> enumDeclarationTransformationProvider;
	@Inject
	private Provider<FunctionDeclarationTransformation> functionDeclarationTransformationProvider;
	@Inject
	private Provider<TrimTransformation> trimTransformation;
	@Inject
	private Provider<SanitizeImportsTransformation> sanitizeImportsTransformationProvider;
	@Inject
	private Provider<ModuleWrappingTransformation> moduleWrappingTransformationProvider;
	@Inject
	private Provider<BlockTransformation> blockTransformationProvider;
	@Inject
	private Provider<FormalParameterTransformation> formalParameterTransformationProvider;
	@Inject
	private Provider<ArrowFunction_Part1_Transformation> arrowFunction_Part1_TransformationProvider;
	@Inject
	private Provider<ArrowFunction_Part2_Transformation> arrowFunction_Part2_TransformationProvider;

	@Inject
	private ProjectUtils projectUtils;

	/**
	 * Returns the AST transformations to be executed for the resource to transpile in the given transpiler state, in
	 * the order they should be executed.
	 * <ul>
	 * <li>For now, we always execute the same transformations in the same order and transformations are hard-coded.
	 * <li>Later, we will have some form of registration and order and selection of transformation to execute may depend
	 * on the given transpiler state (i.e. the resource to transpile) or some configuration parameters.
	 * </ul>
	 */
	@Override
	protected Transformation[] computeTransformationsToBeExecuted(TranspilerState state) {
		return new Transformation[] {
				staticPolyfillTransformationProvider.get(),
				memberPatchingTransformationProvider.get(),
				apiImplStubGenerationTransformationProvider.get(),
				destructuringTransformation.get(),
				superLiteralTransformationProvider.get(),
				expressionTransformationProvider.get(),
				enumAccessTransformationProvider.get(),
				dependencyInjectionTransformation.get(),
				classDeclarationTransformationProvider.get(),
				interfaceDeclarationTransformationProvider.get(),
				enumDeclarationTransformationProvider.get(),
				functionDeclarationTransformationProvider.get(),
				arrowFunction_Part1_TransformationProvider.get(),
				blockTransformationProvider.get(),
				formalParameterTransformationProvider.get(),
				arrowFunction_Part2_TransformationProvider.get(),
				trimTransformation.get(),
				sanitizeImportsTransformationProvider.get(),
				moduleWrappingTransformationProvider.get()
		};
	}

	/**
	 * General entry-point. Overridden to handle plain-JS-wrapping without transforming.
	 */
	@Override
	public void transpile(N4JSResource resource, Writer outCode, Optional<SourceMapInfo> optSourceMapInfo) {
		if (noTranspile(resource)) {
			doWrapAndWrite(resource, outCode);
		} else {
			super.transpile(resource, outCode, optSourceMapInfo);
		}
	}

	/**
	 * Take the content of resource
	 *
	 * @param resource
	 *            JS-code snippet which will be treated as text.
	 * @param outCode
	 *            writer to output to.
	 */
	private void doWrapAndWrite(N4JSResource resource, Writer outCode) {
		// check if wrapping really applies.
		boolean moduleWrapping = projectUtils.isModuleWrappingEnabled(resource.getURI());

		// get script
		EObject script = resource.getContents().get(0);

		// obtain text
		CharSequence scriptAsText = NodeModelUtils.getNode(script).getRootNode().getText();

		// wrap and write
		String decorated = (moduleWrapping ? ModuleWrappingTransformation.wrapPlainJSCode(scriptAsText)
				: scriptAsText).toString();
		try {

			outCode.write(decorated);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Depending on the File-Extension determines if there should be any transformation.
	 *
	 * @param eResource
	 *            N4JS-Resource to check.
	 * @return true if the code should only be wrapped.
	 */
	private boolean noTranspile(N4JSResource eResource) {
		ResourceType resourceType = ResourceType.getResourceType(eResource);
		return resourceType.equals(ResourceType.JS);
	}

}
