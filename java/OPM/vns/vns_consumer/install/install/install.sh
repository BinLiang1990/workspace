#!/bin/bash
########################################################################
# Author: Wang Lin
# File Name: install.sh
#########################################################################

INSTALL_DIR=/opt/vns_consumer
APP_NAME=vns_consumer
PACKAGE_NAME=vns_consumer.tar.gz

if [ ! -z $1 ]; then INSTALL_DIR=$1
fi

if [ ! -z $2 ]; then APP_NAME=$2
fi

if [ ! -d "$INSTALL_DIR" ]; then
	 echo "invalid file directory $INSTALL_DIR,please check installation directory!" 
	 exit 0
fi

if [ -e "$INSTALL_DIR/$APP_NAME" ]; then
	echo "$INSTALL_DIR/$APP_NAME has been installed, please check it ."
	exit 1
fi

if [ -d "$APP_NAME" ]; then
	rm "$APP_NAME" -rf
fi

pushd config
	chmod +x config.sh
    ./config.sh
popd

mkdir "$APP_NAME"
cd "$APP_NAME"
tar -xvf "../../${PACKAGE_NAME}"
mv ../config/applicationContext.xml ./config
mv ../config/heartbeat.py .
cd ..
mv "$APP_NAME" "$INSTALL_DIR/"
chmod +x $INSTALL_DIR/$APP_NAME/*.sh 
echo "Install vns_consumer 1.0.0.0 Packages done"