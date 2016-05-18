#!/bin/bash
find target/html/docs -name *.html | cut -sd / -f 1- >help-pages.txt
