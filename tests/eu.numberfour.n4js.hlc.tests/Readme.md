# eu.numberfour.n4js.hlc.tests
Integration tests for the ready to use "n4jsc.jar" of module *eu.numberfour.n4js.hlc* 

## Structure
This is a maven-structured module and not an osgi-bundle. As a consequence there are several differences in the organization:

* Dependencies are stated *only* in the pom.xml  (NO Manifest)
* tests and source are packaged in the same module
* the folder structure is maven-like: 
  * Java-sources: src/main/java  (Should be empty here, except a place-holder)
  * Additional resources to be packaged into the jar: src/main/resources  
  * Test-sources: src/test/java
  * Test-resources: src/test/resources
  * Exemplary N4 Project: fixture

## Integration test
* This test-module runs the  "n4jsc.jar" in a external VM, testing the outside behavior
* N4JS-projects are found in folder "fixture" 

   