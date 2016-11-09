FILES=`find .  -name "*.adoc" -print`

mkdir -p generated-headers
echo "INFO: Removing existing page lists"
rm -v generated-headers/*.txt
rm -v generated-headers/*.html
echo "INFO: Existing page lists cleaned"


#  # Adding -f flag for flat output directory structure
#  if [ "${1}" == "--flat" ] || [ "${1}" == "-f" ]; then
#    echo "INFO: Creating headers with flat menu structure"
#  else
#    echo "INFO: Creating headers with relative menu structure"
#  fi


for f in $FILES; 
do 
  filename="${f%.*}"
  # remove extension
  FILE_NAME=$(basename $filename)
  # Capitalise the first letter of the page title for displayed navmenu text
  # MENU_TITLE= echo $FILE_NAME | awk '{print toupper(substr($0,1,1)) tolower(substr($0,2)) }'
  cp resources/headers/docinfo.html generated-headers/$FILE_NAME-docinfo.html
  # Create a simple file/directory list, then enclose each entry with HTML tags
  echo ${filename:2}.html#$FILE_NAME "\n"  >> generated-headers/menuitems.txt
  echo "<li><a href=\""${filename:2}.html#$FILE_NAME"\">"$FILE_NAME"</a></li> \n"  >> generated-headers/formatted-menuitems.txt
done

for f in $FILES; 
do 
  filename="${f%.*}"
  FILE_NAME=$(basename $filename)
  echo "INFO: Creating HTML list item for "$FILE_NAME.html
  # Insert the formatted page list at the commented pointer in the header
  sed -i.bak '/<!-- insert menu items here-->/r generated-headers/formatted-menuitems.txt' generated-headers/$FILE_NAME-docinfo.html

done

cp resources/headers/docinfo-footer.html generated-headers/docinfo-footer.html

find .  -name "*.bak" -delete