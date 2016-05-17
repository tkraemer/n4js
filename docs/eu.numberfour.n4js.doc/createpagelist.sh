#!/bin/bash
find target/html/docs -name *.html | cut -sd / -f 2- >help-pages.txt