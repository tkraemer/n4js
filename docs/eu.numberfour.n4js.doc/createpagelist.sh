#!/bin/bash
echo `pwd`
if [ -d "./target" ]; then
  echo "1: target exists"
fi

if [ -d "$1/target" ]; then
  echo "2: target exists"
fi

echo $1
echo `$1`
find $1/target -name *.html | cut -sd / -f 1- >help-pages.txt
find `$1`/target -name *.html | cut -sd / -f 1- >help-pages2.txt
