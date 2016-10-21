#!/bin/bash

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

# Rundoc function to convert source files to HTML and resolve relative path to resources
if [ "${1}" == "--rundoc" ]; then
fullpath="$2"
current="${fullpath:2}"
filename="${curent::-4}"
echo AsciiDoctor - Converting to HTML: "$current"
asciidoctor -D generated-docs/"$(dirname "$current")" "$current" \
-a docinfodir="../$(relpath "$2" ./_headers)"/$(dirname "$current")/ -a linkcss=true \
-a highlightjsdir="../$(relpath "$2" ./scripts/)" -a source-highlighter=highlightjs -a highlightjs-theme=n4jshighlighter \
-a sectlinks=true -a icons=font -a experimental=true -a index=true \
-a docinfo1=true -a doctype=book \
-a !stylesheet -a idseparator=-
exit 0
fi

# Building HTML into generated-docs/html folder
# all one-time commands (copying,cleaning resources) must be run/declared before the 
# find command below
ME="$0"
rm -r generated-docs; mkdir generated-docs && cp -r ./articles ./faq ./features ./images ./releases ./scripts ./styles ./userguides generated-docs/ \
&& cp index.html generated-docs/index.html && find . -name '*.adoc' ! -name 'epub.adoc' -exec "${ME}" --rundoc {}  \;

echo INFO: AsciiDoctor HTML conversion finished.

# Remove unnecessary source files
echo === Removing source files from distribution directory ===
rm -v generated-docs/**/*.graffle generated-docs/**/*.adoc
echo === Distribution directory resources cleaned. ===

# uncomment line below to open docs index on completion
open generated-docs/userguides/index.html