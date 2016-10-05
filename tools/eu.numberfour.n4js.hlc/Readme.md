# eu.numberfour.n4js.hlc
This is a maven-structured module and not an osgi-bundle. As a consequence there are several differences in the organization:

* Dependencies are stated *only* in the pom.xml  (NO Manifest)
* tests and source are packaged in the same module
* the folder structure is maven-like: 
  * Java-sources: src/main/java
  * Additional resources to be packaged into the jar: src/main/resources
    * Currently a file from *Ecore* - properties for user-messages.  
  * Test-sources: src/test/java
  * Test-resources: src/test/resources

## Why tests not in a separate bundle?
One of the goals in this module is to package the command-line jar. 
In order to ensure product quality a fail-fast approach is taken, that means if the command-line interface code in "N4Jsc.java" is broken,
this jar should not be build.

This can only be achieved when the tests are carried out before the packaging kicks in. 

## What about the test-module eu.numberfour.n4js.hlc.tests
This module houses integration tests for the ready-to use "n4jsc.jar" produced here. 
(There was still the aim to separate tests in form of an independent module :-). )  

   