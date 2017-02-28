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

pushd src
FILES=`find .  -name "*.adoc" ! -name 'config.adoc' -print`
popd

# Rundoc function to convert source files to HTML. Attributes are passed with the '-a' flag.
for f in $FILES; 
do 
	
	ADOC_FILE=src/$f
	REL_PATH="${f//\.\//}"
	REL_PATH="${REL_PATH//[^\/]/}"
	REL_PATH="${REL_PATH//[\/]/../}"
	HEADER_DIR=$(basename $(dirname $f))
	OUT_FOLDER=./$GEN_FOLDER/$(dirname $f)
	ASPEC=asciidoctor
	ATTRS="-a doctype=book -a experimental=true -a stylesheet=n4js-adoc.css -a docinfo1=true \ 
	-a highlightjs-theme=n4jshighlighter -a source-highlighter=highlightjs "	


	# If Jenkins is building, then use AsciiSpec
	if [ "${1}" == "--jenkins" ]; then
		ASPEC=asciispec
	fi
	echo running $ASPEC on $ADOC_FILE to $OUT_FOLDER

	mkdir -p $OUT_FOLDER
	$ASPEC -D $OUT_FOLDER $ADOC_FILE -a stylesdir=${REL_PATH}../res/styles  -a highlightjsdir=${REL_PATH}../res/scripts \
	-a docinfodir=${REL_PATH}../res/headers/$HEADER_DIR  
done


# Delete unwanted source files.
pushd ./$GEN_FOLDER/
find . -name "*.adoc" -delete && find . -name "*.graffle" -delete
popd

# If Jenkins is building, move generated docs
if [ "${1}" == "--jenkins" ]; then
	cp -r generated-docs ../
	exit 0
fi

# Adding -p flag for launching pages after build
if [ "${1}" == "--preview" ] || [ "${1}" == "-p" ]; then
open ./$GEN_FOLDER/index.html
exit 0
fi
