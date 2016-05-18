#!/bin/bash
find target -name *.html | cut -sd / -f 1- >help-pages.txt
