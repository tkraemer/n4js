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

# Clean unwanted adoc/graffle files
pushd ./$GEN_FOLDER/chapters
	find . -name "*.adoc" -delete && find . -name "*.graffle" -delete
popd

echo DONE: AsciiSpec conversion finished.
