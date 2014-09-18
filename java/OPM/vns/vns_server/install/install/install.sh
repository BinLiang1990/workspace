#!/bin/bash

##########################################
#Program:
#	VNS 1.0.0.0 Install Script.
#	Author: Wang Lin
##########################################

/etc/init.d/tomcat6 status
tomcat6server=$?
#echo $tomcat6server
if test $tomcat6server -eq 0; then
    echo "tomcat 6 is already running, please stop the it first"
    exit 1
fi

PACKAGE_NAME=vns_server.war
WEB_APP_NAME=vns_server
WEB_APP_PATH=/var/lib/tomcat6/webapps
TOMCAT_USER_GROUP=tomcat6:tomcat6

if [ -d "$WEB_APP_PATH/$WEB_APP_NAME" ]; then
	echo "$WEB_APP_PATH/$WEB_APP_NAME has been installed, please check it ."
	exit 1
fi

pushd config
	chmod +x config.sh
	./config.sh
popd

mkdir "$WEB_APP_NAME"
(cd "$WEB_APP_NAME" ; jar -xf "../../${PACKAGE_NAME}")
mv config/applicationContext.xml $WEB_APP_NAME/WEB-INF/classes
chown "$TOMCAT_USER_GROUP" "$WEB_APP_NAME" -R

mv "$WEB_APP_NAME" "$WEB_APP_PATH/"

echo "install finished, please start tomcat6"