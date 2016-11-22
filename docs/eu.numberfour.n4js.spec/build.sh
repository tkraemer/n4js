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
cp -r scripts styles images ./$SRC_FOLDER/chapters ./$GEN_FOLDER/

echo INFO: AsciiSpec Generating HTML

####################### Build HTML for gh-pages #######################
# All values are built-in AsciiDoctor CLI attributes passed with -a flag, 
# see http://asciidoctor.org/docs/user-manual/#builtin-attributes
asciispec -D $GEN_FOLDER/ N4JSSpec.adoc  \
-a docinfodir=headers -a docinfo1=true \
-a stylesdir=styles -a stylesheet=n4-adoc.css \
-a source-highlighter=highlight.js -a highlightjsdir=scripts -a highlightjs-theme=n4jshighlighter \
-a toc=right -a sectnums=true -a sectanchors=true -a sectlinks=true \
-a data-uri=true -a doctype=book -a experimental=true -a idseparator=-

echo INFO: AsciiSpec HTML conversion Done

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
