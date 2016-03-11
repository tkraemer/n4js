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
package eu.numberfour.n4js.tester.server.resources.tests;

import static eu.numberfour.n4js.tester.server.HttpConstants.SC_UNPROCESSABLE_ENTITY;
import static eu.numberfour.n4js.tester.server.resources.ContentType.END_TEST;
import static eu.numberfour.n4js.tester.server.resources.HttpMethod.POST;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.inject.Inject;

import eu.numberfour.n4js.tester.domain.TestResult;
import eu.numberfour.n4js.tester.events.TestEndedEvent;
import eu.numberfour.n4js.tester.events.TestEvent;
import eu.numberfour.n4js.tester.server.resources.ClientResourceException;
import eu.numberfour.n4js.tester.server.resources.Resource;

/**
 * Implementation of the {@code end test} RESTful resource.
 * <p>
 * <tt>HTTP PUT/n4.ide/testing/sessions/{sessionId}/tests/{testId}/end/</tt>
 */
@Resource(path = "/{sessionId}/tests/{testId}/end/", method = POST, requestContentType = END_TEST)
public class EndTestResource extends TestResource {

	@Inject
	private ObjectMapper mapper;

	@Override
	protected TestEvent createEvent(final String sessionId, final String testId, final String body)
			throws ClientResourceException {

		final AtomicReference<TestResult> result = new AtomicReference<>();
		try {
			result.set(mapper.readValue(body, TestResult.class));
		} catch (JsonMappingException | JsonParseException e) {
			throw new ClientResourceException(SC_UNPROCESSABLE_ENTITY);
		} catch (final IOException e) {
			throw new ClientResourceException(SC_BAD_REQUEST);
		}
		return new TestEndedEvent(sessionId, testId, result.get());
	}

}
