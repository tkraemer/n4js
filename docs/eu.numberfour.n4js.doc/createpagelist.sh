#!/bin/bash
find $1/target/html/docs -name *.html | cut -sd / -f 1- >help-pages.txt
