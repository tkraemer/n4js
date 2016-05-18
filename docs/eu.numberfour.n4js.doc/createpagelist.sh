#!/bin/bash
find ./target/html -name *.html | cut -sd / -f 1- >help-pages.txt