#!/bin/bash
## Syntax for following set command:
# -e  == exit immediately
# -x  == enable debug. (+x for disable)
# -v  == Print shell input lines as they are read.
set -e +x -v

########## Directory Locations ###########
# output folder:
GEN_FOLDER=generated-docs/

rm -rf ./$GEN_FOLDER/; mkdir -p ./$GEN_FOLDER/

# Copy resources to ./$GEN_FOLDER/
cp -r styles images scripts chapters ./$GEN_FOLDER/


############## Build HTML for gh-pages #############

# TODO remove hard-coded paths: $WORKSPACE should not be used instead rely on relative paths or parameters.
if [ "${1}" == "--jenkins" ]; then
	ASPEC="$WORKSPACE/asciispec/bin/asciispec"
	FOP="$WORKSPACE/fopub/fopub"
	GEN_FOLDER="$WORKSPACE/${LOCAL_CHECKOUT:-}docs/"
	DOCS_DIR="$WORKSPACE/${LOCAL_CHECKOUT:-}docs/eu.numberfour.n4js.spec"
	SPEC="$DOCS_DIR/N4JSSpec.adoc"

	rm -rf $GEN_FOLDER; mkdir -p $GEN_FOLDER
	# Copy resources to ./$GEN_FOLDER/
	cp -r $DOCS_DIR/styles $DOCS_DIR/images $DOCS_DIR/scripts $GEN_FOLDER/

	$ASPEC $ATTRS -D $GEN_FOLDER $SPEC 
	$ASPEC -b docbook -D $GEN_FOLDER $SPEC 
	$FOP $GEN_FOLDER/N4JSSpec.xml 
	rm $GEN_FOLDER/N4JSSpec.xml
	exit 0
fi

asciispec -a stylesheet=foundation.min.css -a docinfodir=headers -D $GEN_FOLDER/ N4JSSpec.adoc 

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
