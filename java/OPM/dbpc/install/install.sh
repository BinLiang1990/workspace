#!/bin/bash
INSTALL_DIR=/opt/dbpc
APP_NAME=dbpc
PACKAGE_NAME=dbpc.tar.gz
if [ ! -z $1 ]; then INSTALL_DIR=$1
fi
if [ ! -d "$INSTALL_DIR" ]; then
     echo "invalid file directory $INSTALL_DIR,please check installation directory!" 
     exit 1
fi
if [ -d "$APP_NAME" ]; then
    rm "$APP_NAME" -rf
fi
mkdir "$APP_NAME"
tar xvf "../../$PACKAGE_NAME" "$APP_NAME"
mv conf.properties "$APP_NAME"/conf
mv "$APP_NAME" "$INSTALL_DIR"