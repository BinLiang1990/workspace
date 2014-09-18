#!/bin/bash
########################################################################
# Author: Wang Lin
# Created Time: Thu 27 June 2012
# File Name: install.sh
# Description: 
#########################################################################
INSTALL_DIR=/opt/opm_gui
APP_NAME=opm_gui
PACKAGE_NAME=opm_gui.tar.gz

mkdir "$APP_NAME"
(cd "$APP_NAME" ; tar -xvf "../../${PACKAGE_NAME}")
cp config/opm_gui.properties $APP_NAME/config/
mv "$APP_NAME" "$INSTALL_DIR/"
now=$(date)
echo "Install opm 3.1.0.0 gui Packages done at $now"