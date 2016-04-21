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
package eu.numberfour.n4js.tests.scoping

import com.google.inject.Inject
import com.google.inject.Provider
import eu.numberfour.n4js.N4JSInjectorProviderWithIndex
import eu.numberfour.n4js.n4JS.ExpressionStatement
import eu.numberfour.n4js.n4JS.N4JSPackage
import eu.numberfour.n4js.n4JS.ParameterizedCallExpression
import eu.numberfour.n4js.n4JS.ParameterizedPropertyAccessExpression
import eu.numberfour.n4js.n4JS.Script
import eu.numberfour.n4js.resource.UserdataMapper
import eu.numberfour.n4js.ts.types.TClass
import eu.numberfour.n4js.ts.types.TModule
import eu.numberfour.n4js.utils.Log
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.resource.XtextResourceSet
import org.eclipse.xtext.resource.containers.FlatResourceSetBasedAllContainersState
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider
import org.eclipse.xtext.util.StringInputStream
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.*
import eu.numberfour.n4js.resource.N4JSResource

@InjectWith(N4JSInjectorProviderWithIndex)
@RunWith(XtextRunner)
@Log
class N4JSScopingTestWithIndexTest {

	@Inject
	ResourceDescriptionsProvider resourceDescriptionsProvider;

	@Inject Provider<XtextResourceSet> resourceSetProvider

	def assertResourceNotLoaded(ResourceSet rs, URI uri) {
		val resource = rs.getResource(uri, false)
		if (resource !== null && resource.loaded) {
			fail("Resource (and AST) for " + uri + " is not expected to be loaded.");
		}
	}

	@Test
	def void testImportExportMemberDeserialize() {
		doTestImportExportMemberDeserialize("src/eu/numberfour/n4js/tests/scoping/Supplier.n4js", "Supplier",
			"src/eu/numberfour/n4js/tests/scoping/Client.n4js",
//		// If there are any problems here, switch to string-based comparison (eu.numberfour.n4js.resource.UserdataMapper.BINARY = false) 
//      // with this equivalent value:			
//			'''
//				<?xml version="1.0" encoding="ASCII"?>
//				<types:TModule xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:typeRefs="http://www.numberfour.eu/ide/ts/TypeRefs" xmlns:types="http://www.numberfour.eu/ide/ts/Types" qualifiedName="eu.numberfour.n4js.tests.scoping.Supplier" vendorID="eu.numberfour" projectName="eu_numberfour_n4js_lang_tests" moduleLoader="N4JS">
//				  <astElement href="#/0"/>
//				  <topLevelTypes xsi:type="types:TClass" name="Supplier" exportedName="Supplier">
//				    <ownedMembers xsi:type="types:TMethod" name="foo" hasNoBody="true" declaredMemberAccessModifier="public">
//				      <astElement href="#/0/@scriptElements.0/@exportedElement/@ownedMembersRaw.0"/>
//				      <returnTypeRef xsi:type="typeRefs:ParameterizedTypeRef" declaredType="//@topLevelTypes.0"/>
//				    </ownedMembers>
//				    <astElement href="#/0/@scriptElements.0/@exportedElement"/>
//				  </topLevelTypes>
//				</types:TModule>
//			'''
			"iWVtZgoNGgoAAgEBJmh0dHA6Ly93d3cubnVtYmVyZm91ci5ldS9pZGUvdHMvVHlwZXMBJmh0dHA6Ly93d3cubnVtYmVyZm91"+
			"ci5ldS9pZGUvdHMvVHlwZXMCLwEIVE1vZHVsZQILYXN0RWxlbWVudAICJ2h0dHA6Ly93d3cubnVtYmVyZm91ci5ldS9pZGUv"+
			"bjRqcy9ONEpTAidodHRwOi8vd3d3Lm51bWJlcmZvdXIuZXUvaWRlL240anMvTjRKUwIvAQdTY3JpcHQAAwYjbnVsbAMvMAQO"+
			"cXVhbGlmaWVkTmFtZSpldS5udW1iZXJmb3VyLm40anMudGVzdHMuc2NvcGluZy5TdXBwbGllcgUJdmVuZG9ySUQOZXUubnVt"+
			"YmVyZm91cgYMcHJvamVjdE5hbWUeZXVfbnVtYmVyZm91cl9uNGpzX2xhbmdfdGVzdHMHDW1vZHVsZUxvYWRlcgVONEpTDA50"+
			"b3BMZXZlbFR5cGVzAgMBAgdUQ2xhc3MCBW5hbWUJU3VwcGxpZXIDDWV4cG9ydGVkTmFtZQlTdXBwbGllcgYNb3duZWRNZW1i"+
			"ZXJzAgQBAwhUTWV0aG9kAgVuYW1lBGZvbwcLYXN0RWxlbWVudAUCAhRONE1ldGhvZERlY2xhcmF0aW9uAAM5LzAvQHNjcmlw"+
			"dEVsZW1lbnRzLjAvQGV4cG9ydGVkRWxlbWVudC9Ab3duZWRNZW1iZXJzUmF3LjAKDnJldHVyblR5cGVSZWYGAylodHRwOi8v"+
			"d3d3Lm51bWJlcmZvdXIuZXUvaWRlL3RzL1R5cGVSZWZzBClodHRwOi8vd3d3Lm51bWJlcmZvdXIuZXUvaWRlL3RzL1R5cGVS"+
			"ZWZzAi8BFVBhcmFtZXRlcml6ZWRUeXBlUmVmBQ1kZWNsYXJlZFR5cGUDARIKaGFzTm9Cb2R5ARMdZGVjbGFyZWRNZW1iZXJB"+
			"Y2Nlc3NNb2RpZmllcgdwdWJsaWMBCQthc3RFbGVtZW50BwIDE040Q2xhc3NEZWNsYXJhdGlvbgADJi8wL0BzY3JpcHRFbGVt"+
			"ZW50cy4wL0BleHBvcnRlZEVsZW1lbnQBAQE="
			);
	}

	/*
	 * Similar to testImportExportMemberDeserialize, but supplier contains a reference to a built-in type which
	 * must not be stored in the index.
	 */
	@Test
	def void testImportExportMemberDeserializeWithBuiltIn() {
		doTestImportExportMemberDeserialize("src/eu/numberfour/n4js/tests/scoping/SupplierWithBuiltIn.n4js",
			"SupplierWithBuiltIn", "src/eu/numberfour/n4js/tests/scoping/ClientWithBuiltIn.n4js",
//		// If there are any problems here, switch to string-based comparison (eu.numberfour.n4js.resource.UserdataMapper.BINARY = false) 
//      // with this equivalent value:			
//			'''
//				<?xml version="1.0" encoding="ASCII"?>
//				<types:TModule xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:typeRefs="http://www.numberfour.eu/ide/ts/TypeRefs" xmlns:types="http://www.numberfour.eu/ide/ts/Types" qualifiedName="eu.numberfour.n4js.tests.scoping.SupplierWithBuiltIn" vendorID="eu.numberfour" projectName="eu_numberfour_n4js_lang_tests" moduleLoader="N4JS">
//				  <astElement href="#/0"/>
//				  <topLevelTypes xsi:type="types:TClass" name="SupplierWithBuiltIn" exportedName="SupplierWithBuiltIn">
//				    <ownedMembers xsi:type="types:TField" name="s" declaredMemberAccessModifier="public">
//				      <astElement href="#/0/@scriptElements.0/@exportedElement/@ownedMembersRaw.0"/>
//				      <typeRef xsi:type="typeRefs:ParameterizedTypeRef">
//				        <declaredType href="n4scheme:/builtin_js.n4ts#//@types.3"/>
//				      </typeRef>
//				    </ownedMembers>
//				    <ownedMembers xsi:type="types:TMethod" name="foo" hasNoBody="true" declaredMemberAccessModifier="public">
//				      <astElement href="#/0/@scriptElements.0/@exportedElement/@ownedMembersRaw.1"/>
//				      <returnTypeRef xsi:type="typeRefs:ParameterizedTypeRef" declaredType="//@topLevelTypes.0"/>
//				    </ownedMembers>
//				    <astElement href="#/0/@scriptElements.0/@exportedElement"/>
//				  </topLevelTypes>
//				</types:TModule>
//			'''
			"iWVtZgoNGgoAAgEBJmh0dHA6Ly93d3cubnVtYmVyZm91ci5ldS9pZGUvdHMvVHlwZXMBJmh0dHA6Ly93d3cubnVtYmVyZm91ci5ldS9pZGUvdHMvVHlwZXMCLwEIVE1vZHVsZQILYXN0RWxlbWVudAICJ2h0dHA6Ly93d3cubnVtYmVyZm91ci5ldS9pZGUvbjRqcy9ONEpTAidodHRwOi8vd3d3Lm51bWJlcmZvdXIuZXUvaWRlL240anMvTjRKUwIvAQdTY3JpcHQAAwYjbnVsbAMvMAQOcXVhbGlmaWVkTmFtZTVldS5udW1iZXJmb3VyLm40anMudGVzdHMuc2NvcGluZy5TdXBwbGllcldpdGhCdWlsdEluBQl2ZW5kb3JJRA5ldS5udW1iZXJmb3VyBgxwcm9qZWN0TmFtZR5ldV9udW1iZXJmb3VyX240anNfbGFuZ190ZXN0cwcNbW9kdWxlTG9hZGVyBU40SlMMDnRvcExldmVsVHlwZXMCAwECB1RDbGFzcwIFbmFtZRRTdXBwbGllcldpdGhCdWlsdEluAw1leHBvcnRlZE5hbWUUU3VwcGxpZXJXaXRoQnVpbHRJbgYNb3duZWRNZW1iZXJzAwQBAwdURmllbGQCBW5hbWUCcwQLYXN0RWxlbWVudAUCAhNONEZpZWxkRGVjbGFyYXRpb24AAzkvMC9Ac2NyaXB0RWxlbWVudHMuMC9AZXhwb3J0ZWRFbGVtZW50L0Bvd25lZE1lbWJlcnNSYXcuMAkdZGVjbGFyZWRNZW1iZXJBY2Nlc3NNb2RpZmllcgdwdWJsaWMMCHR5cGVSZWYGAylodHRwOi8vd3d3Lm51bWJlcmZvdXIuZXUvaWRlL3RzL1R5cGVSZWZzBClodHRwOi8vd3d3Lm51bWJlcmZvdXIuZXUvaWRlL3RzL1R5cGVSZWZzAi8BFVBhcmFtZXRlcml6ZWRUeXBlUmVmBQ1kZWNsYXJlZFR5cGUHAQQRVE9iamVjdFByb3RvdHlwZQAFGm40c2NoZW1lOi9idWlsdGluX2pzLm40dHMLLy9AdHlwZXMuMwEBCAEFCFRNZXRob2QCBW5hbWUEZm9vBwthc3RFbGVtZW50CQIDFE40TWV0aG9kRGVjbGFyYXRpb24AAzkvMC9Ac2NyaXB0RWxlbWVudHMuMC9AZXhwb3J0ZWRFbGVtZW50L0Bvd25lZE1lbWJlcnNSYXcuMQoOcmV0dXJuVHlwZVJlZgoDAQUDARIKaGFzTm9Cb2R5ARMdZGVjbGFyZWRNZW1iZXJBY2Nlc3NNb2RpZmllcgdwdWJsaWMBCQthc3RFbGVtZW50CwIEE040Q2xhc3NEZWNsYXJhdGlvbgADJi8wL0BzY3JpcHRFbGVtZW50cy4wL0BleHBvcnRlZEVsZW1lbnQBAQE="
			);
	}

	def void doTestImportExportMemberDeserialize(String supplierFileName, String supplierClassName,
		String clientFileName, String supplierUserData) {
		var rs = resourceSetProvider.get

		val supplierJS = rs.URIConverter.normalize(URI.createURI(supplierFileName))
		val supplierResource = rs.createResource(supplierJS)
		supplierResource.load(emptyMap)
		EcoreUtil.resolveAll(supplierResource)
		assertTrue(supplierResource.errors.toString(), supplierResource.errors.empty)
		assertEquals("supplier content count", 2, supplierResource.contents.size)
		assertTrue("supplier content count at position one is Script",
			supplierResource.contents.get(0) instanceof Script)
		assertTrue("supplier content count at position two is TClass",
			supplierResource.contents.get(1) instanceof TModule)

		val resourceDescriptions = resourceDescriptionsProvider.getResourceDescriptions(supplierResource);
		assertFalse("Test that the index has been filled", resourceDescriptions.allResourceDescriptions.empty);

		val eoDescs = resourceDescriptions.getResourceDescription(supplierJS).exportedObjects
		assertEquals("Wrong number, found: " + eoDescs.map[it.name], 2, eoDescs.size)
		assertEquals("Stored user data", supplierUserData,
			eoDescs.iterator.toList.findFirst[name.lastSegment == supplierClassName].getUserData(
				UserdataMapper::USERDATA_KEY_SERIALIZED_SCRIPT))

		rs.resources.forEach[it.unload];

		val clientJS = rs.URIConverter.normalize(URI.createURI(clientFileName))
		val clientResource = rs.createResource(clientJS)
		clientResource.load(emptyMap)
		assertTrue(clientResource.errors.empty)
		assertEquals("client content count", 2, clientResource.contents.size)
		val clientObject = clientResource.contents.get(0)
		assertTrue(clientObject instanceof Script)
		val client = clientObject as Script

		// syntax ok?
		assertTrue(client.eResource.errors.empty)
		assertTrue(clientResource.errors.empty)

		val parameterizedCallExpression = ((client.scriptElements.last as ExpressionStatement).expression as ParameterizedCallExpression);
		assertNotNull(parameterizedCallExpression);
		assertEquals(0, parameterizedCallExpression.arguments.size)
		assertNotNull(parameterizedCallExpression.target)
		assertTrue(parameterizedCallExpression.target instanceof ParameterizedPropertyAccessExpression)

		val propertyAccess = parameterizedCallExpression.target as ParameterizedPropertyAccessExpression
		assertFalse("Should not been loaded (1)", supplierResource.loaded)
		assertFalse("Proxy cannot be resolved, type deserialization not working", propertyAccess.property.eIsProxy)
		assertFalse("Resource should not have been loaded due to index access", supplierResource.loaded)
		assertEquals("foo", propertyAccess.property.name);
	}

	@Test
	def void testDescriptionFromIndexCreatesResource() {
		var rs = resourceSetProvider.get

		val URI supplierJS = rs.URIConverter.normalize(
			URI.createURI("src/eu/numberfour/n4js/tests/scoping/Supplier.n4js"))
		val URI clientJS = rs.URIConverter.normalize(URI.createURI("src/eu/numberfour/n4js/tests/scoping/Client.n4js"))

		var Resource supplierResource = rs.getResource(supplierJS, true)
		supplierResource.load(emptyMap)
		EcoreUtil.resolveAll(supplierResource)
		rs.eAdapters += new MyFlatResourceSetBasedAllContainersState(rs, supplierJS)
		val resourceDescriptions = resourceDescriptionsProvider.getResourceDescriptions(supplierResource);
		resourceDescriptions.getResourceDescription(supplierJS).exportedObjects.forEach[ userDataKeys ]
		assertFalse("Test that the index has been filled", resourceDescriptions.allResourceDescriptions.empty);

		supplierResource.unload
		rs.resources.clear
		assertResourceNotLoaded(rs, supplierJS)

		val clientResource = rs.createResource(clientJS);

		clientResource.load(emptyMap)
		assertTrue(clientResource.errors.empty)
		assertEquals("client content count", 2, clientResource.contents.size)
		val clientObject = clientResource.contents.get(0)
		assertTrue(clientObject instanceof Script)
		val client = clientObject as Script

		assertResourceNotLoaded(rs, supplierJS)

		// syntax ok?
		assertTrue(client.eResource.errors.empty)
		assertTrue(clientResource.errors.empty)

		val parameterizedCallExpression = ((client.scriptElements.last as ExpressionStatement).expression as ParameterizedCallExpression);
		assertNotNull(parameterizedCallExpression);
		assertEquals(0, parameterizedCallExpression.arguments.size)
		assertNotNull(parameterizedCallExpression.target)
		assertTrue(parameterizedCallExpression.target instanceof ParameterizedPropertyAccessExpression)

		val propertyAccess = parameterizedCallExpression.target as ParameterizedPropertyAccessExpression

		// TODO: Should have failed here
		assertResourceNotLoaded(rs, supplierJS)
		propertyAccess.property;
		assertResourceNotLoaded(rs, supplierJS)
		assertFalse("Proxy cannot be resolved, type deserialization not working", propertyAccess.property.eIsProxy)
		assertResourceNotLoaded(rs, supplierJS)
		assertEquals("foo", propertyAccess.property.name);

		assertResourceNotLoaded(rs, supplierJS)
	}

	@Test
	def void testTypeUsedTwice() {
		var rs = resourceSetProvider.get

		val supplierJS = rs.URIConverter.normalize(URI.createURI("src/eu/numberfour/n4js/tests/scoping/Supplier.n4js"))
		var supplierResource = rs.getResource(supplierJS, true)
		supplierResource.load(emptyMap)
		EcoreUtil.resolveAll(supplierResource)
		rs.eAdapters += new MyFlatResourceSetBasedAllContainersState(rs, supplierJS)
		val resourceDescriptions = resourceDescriptionsProvider.getResourceDescriptions(supplierResource);
		assertFalse("Test that the index has been filled", resourceDescriptions.allResourceDescriptions.empty);
		resourceDescriptions.getResourceDescription(supplierJS).exportedObjects.forEach[ userDataKeys ]
		supplierResource.unload
		rs.resources.clear

		val clientJS = URI.createURI("src/eu/numberfour/n4js/tests/scoping/Client.n4js")
		val clientResource = rs.createResource(rs.URIConverter.normalize(clientJS))
		clientResource.load(
			new StringInputStream(
				'''
					import { Supplier } from "eu.numberfour.n4js/tests/scoping/Supplier"
					var a: Supplier;
					var b: Supplier;
					a.foo();
					b.foo();
				'''), emptyMap)

		EcoreUtil.resolveAll(clientResource)
		assertTrue(clientResource.errors.isEmpty)
		supplierResource = rs.getResource(supplierJS, false)
		assertNotNull(supplierResource)
		assertFalse("Resource was loaded transparently due to index access in scope provider", supplierResource.loaded)
	}

	@Test
	def void testASTLoadingSuccessfullyProxifies() {
		var rs = resourceSetProvider.get

		val supplierJS = rs.URIConverter.normalize(URI.createURI("src/eu/numberfour/n4js/tests/scoping/Supplier.n4js"))
		var supplierResource = rs.getResource(supplierJS, true) as N4JSResource
		supplierResource.load(emptyMap)
		supplierResource.performPostProcessing
		rs.eAdapters += new MyFlatResourceSetBasedAllContainersState(rs, supplierJS)
		val resourceDescriptions = resourceDescriptionsProvider.getResourceDescriptions(supplierResource);
		assertFalse("Test that the index has been filled", resourceDescriptions.allResourceDescriptions.empty);
		resourceDescriptions.getResourceDescription(supplierJS).exportedObjects.forEach[userDataKeys]

		rs.resources.forEach[unload]
		rs.resources.clear

		val clientJS = URI.createURI("src/eu/numberfour/n4js/tests/scoping/Client.n4js")
		val clientResource = rs.createResource(rs.URIConverter.normalize(clientJS))
		clientResource.load(emptyMap)

		EcoreUtil.resolveAll(clientResource)

		val client = clientResource.contents.get(0) as Script
		val ParameterizedCallExpression = ((client.scriptElements.last as ExpressionStatement).expression as ParameterizedCallExpression);
		assertNotNull(ParameterizedCallExpression);
		assertEquals(0, ParameterizedCallExpression.arguments.size)
		assertNotNull(ParameterizedCallExpression.target)
		assertTrue(ParameterizedCallExpression.target instanceof ParameterizedPropertyAccessExpression)
		val dotAccess = ParameterizedCallExpression.target as ParameterizedPropertyAccessExpression
		val willBecomeProxy = dotAccess.eGet(N4JSPackage.Literals.PARAMETERIZED_PROPERTY_ACCESS_EXPRESSION__PROPERTY,
			false) as EObject

		assertFalse("property should not be a proxy", willBecomeProxy.eIsProxy)

		supplierResource = rs.getResource(supplierJS, false) as N4JSResource
		assertNotNull(supplierResource)
//		supplierResource.contents.get(0) // trigger AST loading
		assertFalse(supplierResource.loaded)
		assertEquals(2, supplierResource.contents.size)

		// TODO check why this caused a ConcurrentModificationException
		supplierResource.contents.head // trigger AST loading

		assertTrue(willBecomeProxy.eIsProxy)
		assertTrue(supplierResource.loaded)

		// TODO: Should have failed here
		assertFalse("Proxy cannot be resolved, type deserialization not working", dotAccess.property.eIsProxy)
		assertEquals("foo", dotAccess.property.name);

		val exported = supplierResource.contents.last as TModule
		val supplierType = exported.topLevelTypes.head as TClass
		assertSame(supplierType.ownedMembers.head, dotAccess.property)
	}
}

/**
 * We need a faked container state in the test because we want to simulate a resource set that does
 * not contain all resources that are available in the index. In the runtime scenario, this is usually
 * not possible because all resources will be loaded up-front, but the test case deletes them from
 * the resource set as soon as the index was populated.
 *
 * This custom IAllContainersState keeps track of a single resource and convinces the framework that it exists.
 */
class MyFlatResourceSetBasedAllContainersState extends FlatResourceSetBasedAllContainersState {

	URI additionalURI

	new(ResourceSet resourceSet, URI additionalURI) {
		super(resourceSet)
		this.additionalURI = additionalURI
	}

	override isEmpty(String containerHandle) {
		return false
	}

	override getContainedURIs(String containerHandle) {
		val result = newArrayList
		result.addAll(super.getContainedURIs(containerHandle))
		result += additionalURI
		return result
	}

}
