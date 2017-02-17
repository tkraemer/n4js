package eu.numberfour.n4jsx.ui.organize.imports;
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

import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Strings;

import eu.numberfour.n4js.ui.organize.imports.IReferenceFilter;
import eu.numberfour.n4js.ui.organize.imports.ReferenceProxyInfo;

/**
 * N4JSX needs to filter cross refs that are HTML tags, since we did not add
 * proper N4ST definitions for them and instead we suppress error on cross ref
 * proxy in the dedicated validator).
 *
 * Long live the Hack!
 */
public class N4JSXReferencesFilter implements IReferenceFilter {

	private static boolean FILTER_DEBUG = false;

	/** @deprecated TODO IDE-2520 remove */
	@Deprecated
	private static void logme(Object data) {
		if (FILTER_DEBUG)
			System.out.println(data);
	}

	@Override
	public boolean test(ReferenceProxyInfo proxyInfo) {
		boolean filterTest = isToBeIgnored(proxyInfo);
		logme("::: " + proxyInfo.eobject.getClass().getName() + " :: " + proxyInfo.name + " : filtered = "
				+ filterTest);
		return !filterTest;
	}

	/**
	 * placeholder implementation for JSX. In general languages should inject
	 * their own filter to filter the list. For N4JS that does not matter much,
	 * but sub languages should provide those to deal with their special
	 * handling. e.g. N4JSX needs provide filter to filter out html tags.
	 */
	private boolean isToBeIgnored(ReferenceProxyInfo proxyInfo) {
		logme("  ");
		logme(" . is JSX?:");
		boolean res = false;
		EObject eo = proxyInfo.eobject;
		String className = eo.getClass().getName();
		if (eo.getClass().getName().contains(".n4jsx.")) {
			res = true;
			logme(" .. yes, " + className);
		} else {
			logme(" .. no, " + className);
			EObject container = eo.eContainer();
			logme(" ... is container JSX?:");
			if (container != null) {
				String containerClassName = container.getClass().getName();
				if (containerClassName.contains(".n4jsx.")) {
					res = true;
					logme(" .... yes, " + containerClassName);
				} else {
					logme(" .... no, " + containerClassName);
				}
			} else {
				logme(" .... container was null");
			}
		}

		if (res) {
			String usedName = proxyInfo.name;

			logme(" . inspect based on name: " + usedName);
			if (Strings.isNullOrEmpty(usedName)) {
				res = false;
			} else {
				res = Character.isLowerCase(usedName.charAt(0));
			}
			logme(" .. name test passed " + res);
		}

		return res;
	}

}
