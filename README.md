# N4JS

The [N4JS language and its IDE](https://numberfour.github.io/n4js) enables high-quality JavaScript development for large Node.js projects. 
N4JS enriches ECMAScript with a static type system and provides extensive support for static validation hosted within a feature-rich IDE.

N4JS is based on ECMAScript Version 5 and ECMAScript 2015 is supported to a great extent. It adds a sound static type system inspired by Java 8, extended by concepts such as structural typing or union types. The language provides built-in support for state-of-the-art programming paradigms such as dependency injection and robust test support. The Eclipse based IDE for typed JavaScript is custom-built for exactly these concepts. Code is validated as you type in addition to tools such as content-assist and quick-fixes to ensure your code is written safely and intuitively.

## Releases

- N4JS IDE Snapshot Releases: [Linux64](http://goo.gl/qGKcm9) [Win64](http://goo.gl/2QDLfc) [MacOS](http://goo.gl/DszAu3)
- Eclipse Update Site N4JS IDE: http://updates.n4js.org/updatesite/
- N4JS command line compiler (jar): [n4jsc.jar] (http://goo.gl/09dfYB)

## Contribute

Eclipse developers who want to develop N4JS itself should use the Oomph Eclipse installer. This installs the correct Eclipse version, creates a new workspace and clones all projects into it.

### Eclipse Installer

The recommended way to install the Eclipse IDE and set up the workspace is to use the Eclipse Installer.
This installer is to be downloaded from https://wiki.eclipse.org/Eclipse_Installer

Run the installer and apply the following steps:

1. change to "Advance Mode" via the menu (upper-right corner) (no need to move the installer)
2. select a product, e.g. "Eclipse Platform"
4. on the next page, drag'n'drop the following URI on the "github" label:
 
    https://raw.githubusercontent.com/NumberFour/n4js/master/releng/eu.numberfour.n4js.targetplatform/N4JS.setup
    
   If this does not work, create a new github project via the "+" button and put the URL there.
5. double-click the entry github/&lt;users>/N4JS so that it is shown in the catalog view below
6. on the next page, configure paths accordingly. You only have to configure the installation and workspace folder.
7. start installation
 
The installer will then guide you through the rest of the installation. All plug-ins are downloaded and configured automatically, so is the workspace including downloading the git repository and setting up the workspace.

### Manual IDE Configuration

For a manual install, clone the code and import all top-level projects from the docs, features, plugins, releng, testhelpers, and tests folders. Activate the targetplatform contained in the ```releng/eu.numberfour.ide.targetplatform``` project.

The N4JS IDE is developed with Eclipse Mars 4.5 or better since the system is based on Eclipse anyway. 
It is almost impossible to use another IDE to develop Eclipse plugins. The list of required plugins includes:

- Xtext/Xtend 2.9.1
- Xcore 1.3.1 
- Xsemantics 1.9.0
- XpectN4 0.1

It is important to use the latest version of Xtext and the corresponding service release of Xcore. You will find the latest version numbers and plugins used in the target platform definition at
https://github.com/NumberFour/n4js/blob/master/releng/eu.numberfour.n4js.targetplatform/eu.numberfour.n4js.targetplatform.target

## Documentation

- [User Guides and Tutorials](http://numberfour.github.io/n4js/documentation/): IDE Setup, writing type safe Node.js modules and exporting as npm with the N4JS IDE
- [N4JS Language Specification (PDF)](https://goo.gl/2Lv2Te)
- [Complete Feature Table](https://numberfour.github.io/n4js/features/)
- [Release Notes](https://numberfour.github.io/n4js/releases/)

## Participate

- [FAQ](https://numberfour.github.io/n4js/faq/)
- [mailing list / web forum](http://groups.google.com/group/n4js)
- [issues](https://github.com/numberfour/n4js/issues/) 

## Build the N4JS IDE from command line

Ensure you have 
- Java 8
- Maven 3 and
- Node.js 6
installed on your system.

Clone the repository 
```
git clone https://github.com/NumberFour/n4js.git
```

Change to the n4js folder:
```
cd n4js
```

Run the Maven build:
```
mvn clean verify
```

You may have to increase the memory for maven via ```export MAVEN_OPTS="-Xmx2048m"``` (Unix) or ```set MAVEN_OPTS="-Xmx2048m"``` (Windows).

## License

Copyright (c) 2016 NumberFour AG.

All rights reserved. This program and the accompanying materials
are made available under the terms of the Eclipse Public License v1.0
which accompanies this distribution, and is available at
http://www.eclipse.org/legal/epl-v10.html
