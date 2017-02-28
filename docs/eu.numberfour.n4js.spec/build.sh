#!/bin/bash
## Syntax for following set command:
# -e  == exit immediately
# -x  == enable debug. (+x for disable)
# -v  == Print shell input lines as they are read.
set -e +x -v

########## Directory Locations ###########
# output folder:
GEN_FOLDER=generated-docs/
ATTRS="-a stylesheet=foundation.min.css -a docinfodir=headers "
rm -rf ./$GEN_FOLDER/; mkdir -p ./$GEN_FOLDER/

# Copy resources to ./$GEN_FOLDER/
cp -r styles images scripts chapters ./$GEN_FOLDER/


############## Build HTML for gh-pages #############
if [ "${1}" == "--jenkins" ]; then
	
	# which document to convert 
	SPEC="N4JSSpec.adoc"
	
	# the output directory
	GEN_FOLDER="../generated-docs/spec"

	rm -rf $GEN_FOLDER; mkdir -p $GEN_FOLDER
	# Copy resources to ./$GEN_FOLDER/
	cp -r ./styles ./images ./scripts ./chapters $GEN_FOLDER/

	asciispec $ATTRS -D $GEN_FOLDER $SPEC 
	$ASPEC -b docbook -D $GEN_FOLDER $SPEC 
	$FOP $GEN_FOLDER/N4JSSpec.xml 
	rm $GEN_FOLDER/N4JSSpec.xml
	exit 0
fi

asciispec $ATTRS -D $GEN_FOLDER N4JSSpec.adoc 

# running "./build.sh -p" (preview) will skip PDF and launch index.html
if [ "${1}" == "--preview" ] || [ "${1}" == "-p" ]; then
open ./$GEN_FOLDER/N4JSSpec.html
exit 0
fi

####### Build PDF for gh-pages download #######
asciispec -b docbook N4JSSpec.adoc
fopub N4JSSpec.xml 
mv N4JSSpec.pdf ./$GEN_FOLDER/

# Clean unwanted adoc/graffle files and delete empty subdirectories
pushd ./$GEN_FOLDER/chapters
	rm -rf **/*.adoc && rm -rf **/**/*.graffle &&	find . -type d -empty -print
popd

echo DONE: AsciiSpec conversion finished.
