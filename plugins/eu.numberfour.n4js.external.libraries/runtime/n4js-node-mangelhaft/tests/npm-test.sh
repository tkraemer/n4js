#!/bin/sh
#
# Copyright (c) 2016 NumberFour AG.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#
# Contributors:
#   NumberFour AG - Initial API and implementation
#
set -e
cd `dirname $0`

function echo_exec {
    echo "$@"
    $@
}

mkdir -p ./build

# TODO writes alien package.json next to n4tp file
# https://github.com/NumberFour/n4js/issues/165
cp ./targetplatform.n4tp build/

../src/js/n4js-mangelhaft-cli.js \
    --compile \
    --targetPlatformFile ./build/targetplatform.n4tp --targetPlatformInstallLocation ./build/npm \
    --xunitReportFile ./build/report.xml \
    --xunitReportName test-report \
    --xunitReportPackage TestPrj \
    --scan .
