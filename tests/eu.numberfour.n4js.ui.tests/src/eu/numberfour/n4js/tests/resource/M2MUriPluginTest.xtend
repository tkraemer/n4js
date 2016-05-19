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
package eu.numberfour.n4js.tests.resource

import com.google.common.base.Optional
import com.google.inject.Inject
import eu.numberfour.n4js.projectModel.IN4JSCore
import eu.numberfour.n4js.resource.N4JSResource
import eu.numberfour.n4js.tests.builder.AbstractBuilderParticipantTest
import eu.numberfour.n4js.ts.typeRefs.BoundThisTypeRef
import eu.numberfour.n4js.ts.typeRefs.DeferredTypeRef
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage
import eu.numberfour.n4js.ts.types.TClass
import eu.numberfour.n4js.ts.types.TMethod
import eu.numberfour.n4js.ts.types.impl.TypeImpl
import eu.numberfour.n4js.ui.projectModel.IN4JSEclipseProject
import eu.numberfour.n4js.utils.M2MUriUtil
import java.util.List
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IProject
import org.eclipse.emf.common.util.URI
import org.eclipse.xtext.builder.builderState.AbstractBuilderState
import org.eclipse.xtext.resource.IResourceDescriptions
import org.eclipse.xtext.resource.impl.ResourceDescriptionsData
import org.junit.Before
import org.junit.Test

/**
 * Tests module-to-module URIs, see {@link M2MUriUtil}.
 */
class M2MUriPluginTest extends AbstractBuilderParticipantTest {

	@Inject
	private IN4JSCore n4jsCore;

	private IProject project;
	private IN4JSEclipseProject projectN4JS;
	private IFile fileA;
	private IFile fileB;
	private IFile fileB2;
	private URI uriA;
	private URI uriB;
	private URI uriB2;


	/**
	 * All tests are based on having type information for two files A.n4js and B.n4js in the Xtext index.
	 * File B2.n4js is only used by some tests.
	 */
	@Before
	def void prepareIndex() {
		if(project!==null) {
			return; // can't make this method static (need instance methods from super class), so can't use @BeforeClass
		}

		project = createJSProject("M2MUriTestProject")
		val srcFolder = configureProjectWithXtext(project)
		val manifest = project.getFile("manifest.n4mf")
		assertMarkers("manifest should have no errors", manifest, 0)

		projectN4JS = n4jsCore.findAllProjects.head as IN4JSEclipseProject;
		assertSame(projectN4JS.getProject, project)

		// create two test files and run builder

		fileA = createTestFile(srcFolder, "A", '''
			export public class A {
				constructor() {}
			}
		''')
		fileB = createTestFile(srcFolder, "B", '''
			import { A } from "A"
			export public class B extends A {}
		''')
		fileB = createTestFile(srcFolder, "B2", '''
			import { A } from "A"
			export public class B2 extends A {}
		''')

		cleanBuild
		waitForAutoBuild

		uriA = projectN4JS.location.appendSegments(#["src", "A.n4js"]);
		uriB = projectN4JS.location.appendSegments(#["src", "B.n4js"]);
		uriB2 = projectN4JS.location.appendSegments(#["src", "B2.n4js"]);

		// we now have type information for files A, B, and B2 in the Xtext index
	}


	@Test
	def void testLoadFromIndexAfterLoadingFromIndex() {
		// (1) load file B from index
		val resourceB = createNewResourceSetAndLoadFileFromIndex(uriB)
		val resourceSet = resourceB.resourceSet
		
		// A.n4js should not be loaded yet
		assertEquals(#["B.n4js"], resourceSet.resources.map[URI.lastSegment])

		// make sure the reference to A was properly created as a m2m URI
		val classB = resourceB.module.topLevelTypes.head as TClass
		val proxyToClassA = classB.superClassRef.declaredTypeNoResolve
		assertTrue(proxyToClassA.eIsProxy)
		val proxyToClassAUri = proxyToClassA.eProxyURI
		assertTrue(M2MUriUtil.isM2MUri(proxyToClassAUri))

		// (2) resolve the reference to A (this should load A.n4js from the index)
		val classA = classB.superClassRef.declaredType as TClass
		assertFalse(classA.eIsProxy)

		// now, A.n4js should be loaded
		assertEquals(#["B.n4js", "A.n4js"], resourceSet.resources.map[URI.lastSegment])
		val resourceA = resourceSet.resources.get(1) as N4JSResource

		// make sure it was loaded from the index (not from the file on disk)
		assertTrue(resourceA.loadedFromDescription)

		// make sure it was post-processed
		// (trivial in this case, because resources loaded from index are always post-processed)
		assertTrue(resourceA.isFullyProcessed)
		assertFalse(classA.ctor.returnTypeRef instanceof DeferredTypeRef)
		assertTrue(classA.ctor.returnTypeRef instanceof BoundThisTypeRef)
	}

	@Test
	def void testUnloadAndReloadAfterLoadingFromIndex() {
		// (1) load files B and B2 from index
		val resources = createNewResourceSetAndLoadFilesFromIndex(uriB, uriB2)
		val resourceB = resources.get(0)
		val resourceB2 = resources.get(1)
		val resourceSet = resourceB.resourceSet

		// (2) load file A by resolving proxies in modules of B and B2
		val classB = resourceB.module.topLevelTypes.head as TClass
		val classB2 = resourceB2.module.topLevelTypes.head as TClass
		val classA_beforeUnload = classB.superClassRef.declaredType
		classB2.superClassRef.declaredType // also resolve the proxy in B2's module (required for tests after unloading)
		assertEquals(#["B.n4js", "B2.n4js", "A.n4js"], resourceSet.resources.map[URI.lastSegment])
		val resourceA_beforeUnload = resourceSet.resources.get(2)

		// (3) unload file A
		resourceA_beforeUnload.unload
		resourceSet.resources.remove(resourceA_beforeUnload)
		assertNull(classA_beforeUnload.eResource)

		// make sure the references in module of B and B2 pointing to A were properly proxified with a m2m URI
		val proxyToClassA_fromB = classB.superClassRef.declaredTypeNoResolve
		assertTrue(proxyToClassA_fromB.eIsProxy)
		val proxyUriToClassA_fromB = proxyToClassA_fromB.eProxyURI
		assertTrue(M2MUriUtil.isM2MUri(proxyUriToClassA_fromB))
		val proxyToClassA_fromB2 = classB2.superClassRef.declaredTypeNoResolve
		assertTrue(proxyToClassA_fromB2.eIsProxy)
		val proxyUriToClassA_fromB2 = proxyToClassA_fromB2.eProxyURI
		assertTrue(M2MUriUtil.isM2MUri(proxyUriToClassA_fromB2))

		// make sure two distinct proxies were created in B and B2 for the reference to A
		assertNotSame(proxyToClassA_fromB, proxyToClassA_fromB2)
		assertNotEquals(proxyUriToClassA_fromB, proxyUriToClassA_fromB2)
		assertEquals(uriB, proxyToClassA_fromB.eProxyURI.trimFragment)
		assertEquals(uriB2, proxyToClassA_fromB2.eProxyURI.trimFragment)
		assertEquals(proxyUriToClassA_fromB.fragment, proxyUriToClassA_fromB2.fragment)

		// (4) resolve both proxies pointing to A thus loading A.n4js from index (again)
		val classA_fromB = classB.superClassRef.declaredType as TClass
		assertFalse(classA_fromB.eIsProxy)
		assertNotSame(classA_fromB, classA_beforeUnload)
		val classA_fromB2 = classB2.superClassRef.declaredType as TClass
		assertFalse(classA_fromB2.eIsProxy)
		assertNotSame(classA_fromB2, classA_beforeUnload)

		// the two distinct proxies must have resolved to the same TClass
		assertSame(classA_fromB, classA_fromB2)
		val classA = classA_fromB

		// now, A.n4js should be loaded (again)
		assertEquals(#["B.n4js", "B2.n4js", "A.n4js"], resourceSet.resources.map[URI.lastSegment])

		// make sure it was loaded from the index (not from the file on disk)
		val resourceA = resourceSet.resources.get(1) as N4JSResource
		assertTrue(resourceA.loadedFromDescription)

		// make sure it was post-processed
		// (trivial in this case, because resources loaded from index are always post-processed)
		assertTrue(resourceA.isFullyProcessed)
		assertFalse(classA.ctor.returnTypeRef instanceof DeferredTypeRef)
		assertTrue(classA.ctor.returnTypeRef instanceof BoundThisTypeRef)
	}

	@Test
	def void testLoadFromSourceAfterLoadingFromIndex() {
		// (1) load file B from index
		val resourceB = createNewResourceSetAndLoadFileFromIndex(uriB)
		val resourceSet = resourceB.resourceSet

		// (2) we want to test proxy resolution in case the target resource is *not* in the index
		// -> temporarily remove info for file A from index
		val index = n4jsCore.getXtextIndex(resourceSet)
		val data = index.resourceDescriptionsData;
		val backupDesc = data.getResourceDescription(uriA)
		data.removeDescription(uriA)

		try {
			// (3) resolve the reference to A (this should load A.n4js from the file on disk)
			val classB = resourceB.module.topLevelTypes.head as TClass
			val classA = classB.superClassRef.declaredType as TClass
			assertFalse(classA.eIsProxy)
	
			// now, A.n4js should be loaded
			assertEquals(#["B.n4js", "A.n4js"], resourceSet.resources.map[URI.lastSegment])
			val resourceA = resourceSet.resources.get(1) as N4JSResource
	
			// make sure it was loaded from the file on disk (not from the index)
			assertFalse(resourceA.loadedFromDescription)
			assertFalse(resourceA.script.eIsProxy)
	
			// make sure it was post-processed
			assertTrue(resourceA.isFullyProcessed)
			assertFalse(classA.ctor.returnTypeRef instanceof DeferredTypeRef)
			assertTrue(classA.ctor.returnTypeRef instanceof BoundThisTypeRef)
		} finally {
			// revert index to its original state
			data.addDescription(uriA, backupDesc);
		}
	}


	def private N4JSResource createNewResourceSetAndLoadFileFromIndex(URI uri) {
		createNewResourceSetAndLoadFilesFromIndex(uri).get(0)
	}
	def private List<N4JSResource> createNewResourceSetAndLoadFilesFromIndex(URI... uris) {
		val resourceSet = n4jsCore.createResourceSet(Optional.of(projectN4JS))
		val index = n4jsCore.getXtextIndex(resourceSet)

		val result = newArrayList
		for(uri : uris) {
			val resource = resourceSet.createResource(uri) as N4JSResource
			val resDesc = index.getResourceDescription(uri)
			assertNotNull(resDesc)
			assertTrue(resource.loadFromDescription(resDesc))
			val module = resource.module
			assertNotNull(module)
			assertFalse(module.eIsProxy)
			result += resource
		}

		return result
	}

	def private TypeImpl getDeclaredTypeNoResolve(ParameterizedTypeRef typeRef) {
		typeRef.eGet(TypeRefsPackage.eINSTANCE.parameterizedTypeRef_DeclaredType,false) as TypeImpl
	}

	def private TMethod getCtor(TClass tClass) {
		tClass.ownedMembers.filter(TMethod).findFirst[isConstructor]
	}

	def private ResourceDescriptionsData getResourceDescriptionsData(IResourceDescriptions resDescs) {
		val field = AbstractBuilderState.declaredFields.findFirst[name=="resourceDescriptionData"]
		field.accessible = true // allow access to private field
		return field.get(resDescs) as ResourceDescriptionsData
	}
}
