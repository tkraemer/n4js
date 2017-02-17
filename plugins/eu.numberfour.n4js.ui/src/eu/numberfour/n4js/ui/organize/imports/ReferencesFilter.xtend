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
package eu.numberfour.n4js.ui.organize.imports

import org.eclipse.emf.ecore.EObject

//TODO 
/**
 * 
 */
class ReferencesFilter {

	private static boolean FILTER_DEBUG = false;

	def private static logme(Object data) {
		if (FILTER_DEBUG) println(data)
	}

	def static boolean filterFunction(Pair<EObject, String> proxyInfo) {

			val filterTest = proxyInfo.isToBeIgnored
			logme("::: " + proxyInfo.key.class.name + " :: " + proxyInfo.value + " : filtered = " + filterTest)
			return !filterTest
	}

	/** placeholder implementation for JSX.
	 * In general languages should inject their own filter to filter the list.
	 * For N4JS that does not matter much, but sub languages should provide those to deal with their special handling.
	 * e.g. N4JSX needs provide filter to filter out html tags.
	 */
	def static private boolean isToBeIgnored(Pair<EObject, String> proxyInfo) {
		logme("  ")
		logme(" . is JSX?:")
		var res = false;
		val eo = proxyInfo.key
		if (eo.class.name.contains(".n4jsx.")) {
			res = true
			logme(" .. yes, " + eo.class.name)
		} else {
			logme(" .. no, " + eo.class.name)
			val container = eo.eContainer;
			logme(" ... is container JSX?:")
			if (container !== null) {
				if (container.class.name.contains(".n4jsx.")) {
					res = true
					logme(" .... yes, " + container.class.name)
				} else {
					logme(" .... no, " + container.class.name)
				}
			} else {
				logme(" .... container was null")
			}
		}

		if (res) {
			val String usedName = proxyInfo.value

			logme(" . inspect based on name: " + usedName)
			if (usedName === null || usedName.length <= 0) {
				res = false
			} else {
				res = Character.isLowerCase(usedName.charAt(0));
			}
			logme(" .. name test passed " + res)
		}

		return res;
	}
}
