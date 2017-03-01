#!/bin/bash

# -e  == exit immediately
# -x  == enable debug. (+x for disable)
# -v  == Print shell input lines as they are read.
set -e +x -v

# Which document to convert 
SPEC="N4JSSpec.adoc"

# Document attributes
ATTRS="-a stylesheet=foundation.min.css -a docinfodir=headers "

############## Build HTML #############

# if Jenkins is building, use the following params
if [ "${1}" == "--jenkins" ]; then

	# Specify & Clean output directory
	GEN_FOLDER="../generated-docs/spec"
	rm -rf $GEN_FOLDER; mkdir -p $GEN_FOLDER

	# Copy resources to ./$GEN_FOLDER/
	cp -r ./styles ./images ./scripts ./chapters $GEN_FOLDER/

	####### Build HTML Spec #######
	asciispec $ATTRS -D $GEN_FOLDER $SPEC
	
	####### Build PDF via docbook toolchain #######
	asciispec -b docbook -D $GEN_FOLDER $SPEC && fopub $GEN_FOLDER/N4JSSpec.xml 

	##### EXIT ##### 
	exit 0

# otherwise, use these params
else
	# Specify & Clean output directory
	GEN_FOLDER=generated-docs
	rm -rf ./$GEN_FOLDER/; mkdir -p ./$GEN_FOLDER/

	# Copy resources to ./$GEN_FOLDER/
	cp -r styles images scripts chapters ./$GEN_FOLDER/

	####### Build HTML Spec #######
	asciispec $ATTRS -D $GEN_FOLDER N4JSSpec.adoc 

	# running "./build.sh -p" (preview) will skip PDF build and launch N4JSSpec.html
	if [ "${1}" == "--preview" ] || [ "${1}" == "-p" ]; then
		open ./$GEN_FOLDER/N4JSSpec.html
		exit 0
	fi
fi

####### Build PDF via docbook toolchain #######
asciispec -b docbook N4JSSpec.adoc && fopub N4JSSpec.xml 
mv N4JSSpec.pdf ./$GEN_FOLDER/

# Clean unwanted adoc/graffle files and delete empty subdirectories
pushd ./$GEN_FOLDER/chapters
	rm -rf **/*.adoc && rm -rf **/**/*.graffle &&	find . -type d -empty -print
popd
