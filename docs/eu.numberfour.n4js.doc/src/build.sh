#!/bin/bash

# Copy Resources
mkdir generated-docs
cp index.html generated-docs/index.html

mkdir -p generated-docs/styles
cp -r styles/* generated-docs/styles

mkdir -p generated-docs/scripts
cp -r scripts/* generated-docs/scripts

mkdir -p generated-docs/images
cp -r images/* generated-docs/images

# Prints out the relative path between to absolute paths.
#
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
    down="$down"
    pos=${pos%/*}
done

echo "$down${ref##$pos/}"
}

# Rundoc function to convert source files to HTML with embedded CSS and images as URIs
if [ "${1}" == "--rundoc" ]; then
fullpath="$2"
current="${fullpath:2}"
filename="${curent::-4}"
echo AsciiDoctor - Converting to HTML: "$current"
mkdir -p generated-docs/"$(dirname "$current")"
asciidoctor -D generated-docs/"$(dirname "$current")" "$current" \
-a stylesdir="$(relpath "$2" ./styles)" -a stylesheet=n4js-adoc.css \
-a docinfodir="$(relpath "$2" ./_headers)" -a docinfo1=true \
-a highlightjsdir=/scripts -a highlightjs-theme=n4jshighlighter -a data-uri=true \
-a sectnums=true -a sectanchors=true -a sectlinks=true -a icons=font -a linkcss=true -a copycss=true \
-a idseparator=-
exit 0
fi

# Building HTML into generated-docs/html folder retaining directory structure
ME="$0"
find . -name '*.adoc' ! -name 'config.adoc' -exec "${ME}" --rundoc {}  \;