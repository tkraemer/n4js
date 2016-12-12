/// ***Copyright(c)2016 NumberFour AG.*All rights reserved.This program and the accompanying materials*are made
/// available under the terms of the Eclipse Public License v1.0*which accompanies this distribution,and is available
/// at*http://www.eclipse.org/legal/epl-v10.html
// **Contributors:*NumberFour AG-Initial API and implementation*/
// package eu.numberfour.n4js.validation;
//
// import java.util.HashMap;
// import java.util.Map;
//
// import org.eclipse.emf.ecore.EObject;
//
/// ****/
//
// public class JavaScriptVariantHelper2 {
// private static class ValidationFeatureBase {
// }
//
// private static class ValidationFeature<T> extends ValidationFeatureBase {
// public T get(Map<TableKey, Object> table, String extension) {
// return (T) table.get(new TableKey(extension, this));
// }
// }
//
// public static final ValidationFeature<Boolean> PLAIN_JS = new ValidationFeature<>();
// public static final ValidationFeature<String> VARIANT_MODE = new ValidationFeature<>();
//
// private static class TableKey {
// private final String extension;
// private final ValidationFeatureBase feature;
//
// public TableKey(String extension, ValidationFeatureBase feature) {
// this.extension = extension;
// this.feature = feature;
// }
//
// @Override
// public int hashCode() {
// }
//
// @Override
// public boolean equals(Object obj) {
// }
// }
//
// private final Map<TableKey, Object> table = new HashMap<>();
// private JavaScriptVariantHelper2 next;
//
// protected <T> void addEntry(String extension, ValidationFeature<T> feature, T value) {
// table.put(new TableKey(extension, feature), value);
// }
//
// private <T> T get(String extension, ValidationFeature<T> feature) {
// T result = feature.get(table, extension);
// if (result != null)
// return result;
// if (next != null)
// return next.get(extension, feature);
// return null;
// }
//
// public boolean isPlainJS(EObject object) {
// String extension = ""; // get actual extension
// return get(extension, PLAIN_JS);
// }
//
// // should happen in subclass constructor
// public static void foo() {
// JavaScriptVariantHelper2 helper = new JavaScriptVariantHelper2();
//
// helper.addEntry("n4js", VARIANT_MODE, "asdf");
// }
// }
