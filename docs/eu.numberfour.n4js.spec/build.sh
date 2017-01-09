#!/bin/bash
## Syntax for following set command:
# -e  == exit immediately
# -x  == enable debug. (+x for disable)
# -v  == Print shell input lines as they are read.
set -e +x -v

########## Directory Locations ###########
# output folder:
GEN_FOLDER=generated-docs/html

rm -rf ./$GEN_FOLDER/; mkdir -p ./$GEN_FOLDER/

echo INFO: Copying resources to ./$GEN_FOLDER/
cp -r styles images scripts ./$GEN_FOLDER/

echo INFO: AsciiSpec Generating HTML

####################### Build HTML for gh-pages #######################
asciispec -a data-uri=true -a stylesheet=foundation.css -D $GEN_FOLDER/ N4JSSpec.adoc 

echo INFO: AsciiSpec HTML conversion Done

# running "./build.sh -p" (preview) will skip PDF and launch index.html
if [ "${1}" == "--preview" ] || [ "${1}" == "-p" ]; then
open ./$GEN_FOLDER/N4JSSpec.html
exit 0
fi

####### Build PDF for gh-pages download #######
echo INFO: AsciiSpec Generating PDF
asciispec -fop N4JSSpec.adoc
mv N4JSSpec.pdf ./$GEN_FOLDER/

# running "./build.sh -l" will launch N4JSSpec.html after build
if [ "${1}" == "--launch" ] || [ "${1}" == "-l" ]; then
open ./$GEN_FOLDER/N4JSSpec.html
exit 0
fi

echo DONE: AsciiSpec conversion finished.
