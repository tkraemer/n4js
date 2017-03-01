#!/bin/bash

# Parameters:
# $1 = first path
# $2 = second path
#
# Output: the relative path between 1st and 2nd paths
relpath() {
    local pos="${1%%/}" ref="${2%%/}" down=''

    while :; do
        test "$pos" = '/' && break
        case "$ref" in $pos/*) break;; esac
        down="../$down"
        pos=${pos%/*}
    done

    echo "$down${ref##$pos/}"
}

GEN_FOLDER=generated-docs
echo copying resources to ./$GEN_FOLDER/

rm -r ./$GEN_FOLDER/; mkdir ./$GEN_FOLDER/ 
cp -r ./res/scripts ./res/styles ./src/articles ./src/faq ./src/features ./src/images ./src/releases  ./src/userguides ./$GEN_FOLDER/
cp ./src/index.html ./$GEN_FOLDER/index.html 

cd src
FILES=`find .  -name "*.adoc" ! -name 'config.adoc' -print`
cd ..

# Rundoc function to convert source files to HTML. Attributes are passed with the '-a' flag.
for f in $FILES; 
do 
	ADOC_FILE=src/$f
	REL_PATH="${f//\.\//}"
	REL_PATH="${REL_PATH//[^\/]/}"
	REL_PATH="${REL_PATH//[\/]/../}"
	OUT_FOLDER=./$GEN_FOLDER/$(dirname $f)
	HEADER_DIR=$(basename $(dirname $f))
	echo running AsciiDoctor on $ADOC_FILE to $OUT_FOLDER
	mkdir -p $OUT_FOLDER
	asciidoctor -D $OUT_FOLDER $ADOC_FILE \
-a stylesdir=${REL_PATH}../res/styles -a stylesheet=n4js-adoc.css \
-a highlightjsdir=${REL_PATH}../res/scripts -a highlightjs-theme=n4jshighlighter -a source-highlighter=highlightjs \
-a docinfodir=${REL_PATH}../res/headers/$HEADER_DIR -a docinfo1=true \
-a idseparator=- -a doctype=book -a experimental=true
done

cd ./$GEN_FOLDER/
find . -name "*.adoc" -delete && find . -name "*.graffle" -delete
cd ..

# Adding -l flag for launching pages after build
if [ "${1}" == "--launch" ] || [ "${1}" == "-l" ]; then
open ./$GEN_FOLDER/index.html
exit 0
fi
