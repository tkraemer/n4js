# Adding -a flag for converting with asciispec
if [ "${1}" == "--asciispec" ] || [ "${1}" == "-a" ]; then
	CONVERTER=asciispec
else
	CONVERTER=asciidoctor
fi

GEN_FOLDER=generated-docs
echo INFO: Copying resources to ./$GEN_FOLDER/
rm -r ./$GEN_FOLDER/; mkdir ./$GEN_FOLDER/ 

FILES=`find .  -name "*.adoc" ! -name "*config.adoc" -print`

# # Adding -f flag for flat output directory structure
# if [ "${1}" == "--flat" ] || [ "${1}" == "-f" ]; then
# 	OUT_FOLDER=./$GEN_FOLDER
# 	sh ./create-headers.sh -f
# else
# 	OUT_FOLDER="./$GEN_FOLDER/$(dirname $f)"
# 	sh ./create-headers.sh
# fi

for f in $FILES; 
do 
  ADOC_FILE=$f
  REL_PATH="${f//\.\//}"
  REL_PATH="${REL_PATH//[^\/]/}"
  REL_PATH="${REL_PATH//[\/]/../}"
  HEADER_DIR=$(basename $(dirname $f))
  OUT_FOLDER=./$GEN_FOLDER/$(dirname $f)
  mkdir -p $OUT_FOLDER
	echo INFO: $CONVERTER converting ${ADOC_FILE:2}
	$CONVERTER -D $OUT_FOLDER $ADOC_FILE \
	-a idseparator=- \
	-a stylesdir=${REL_PATH}resources/styles -a stylesheet=n4-adoc.css \
	-a highlightjsdir=${REL_PATH}resources/scripts -a highlightjs-theme=n4jshighlighter \
	-a docinfodir=${REL_PATH}generated-headers -a docinfo2=true -a doctype=book -a toc=right
done

echo INFO: Copying resources to ./$GEN_FOLDER/
cp -r resources chapters ./$GEN_FOLDER/
echo INFO: Cleaning target folder 
cd ./$GEN_FOLDER/
find . -name "*.adoc" -delete && find . -name "*.graffle" -delete
cd ../
