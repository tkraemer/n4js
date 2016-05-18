#!/bin/bash
echo `pwd`
if [ -d "./target" ]; then
  echo "target exists"
fi
echo $1
find target -name *.html | cut -sd / -f 1- >help-pages.txt
