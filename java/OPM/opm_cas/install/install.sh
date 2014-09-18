#!/bin/bash

##########################################
#Program:
#	OPM 3.0 Install Script.
#History:
#	2012-08-14	HuangPanpan
##########################################

set -e

function exit_on_error()
{
    echo Install Failed!
    exit 1
}

trap exit_on_error ERR 

########## application configuration section ########
#web application package name
PACKAGE_NAME=opm_cas.war
#web application name
WEB_APP_NAME=opm_cas
#configfile
CONFIG_XML_FILE=deployerConfigContext.xml
#configuration files that changes according production env separated with space
########### end of configuration section ############

# tomcat home
WEB_APP_PATH=/var/lib/tomcat6/webapps
# for setting web application owner:group
TOMCAT_USER_GROUP=tomcat6:tomcat6



if [ ! -z $1 ]; then WEB_APP_NAME=$1
fi

if [ ! -z $2 ]; then PACKAGE_NAME=$2
fi

if [ ! -f "$PACKAGE_NAME" ]; then
	echo "$PACKAGE_NAME used for install does not exist, install failed!"
	exit 1
fi

if [ -d "$WEB_APP_PATH/$WEB_APP_NAME" ]; then
	echo "$WEB_APP_PATH/$WEB_APP_NAME has been installed, please check it ."
	exit 1
fi

if [ "$(pwd)" == "$WEB_APP_PATH" ]; then
	echo "installing steps can not be executed in current directory, please select another directory."
	exit 1
fi

if [ -d "$WEB_APP_NAME" ]; then
	rm "$WEB_APP_NAME" -rf
fi

# create the applicationContex.xml
pushd config
	./config.sh
popd

# extract from war package
mkdir "$WEB_APP_NAME"
(cd "$WEB_APP_NAME" ; jar -xf "../${PACKAGE_NAME}")

#config
mv config/$CONFIG_XML_FILE $WEB_APP_NAME/WEB-INF/

# backup web application files here
# get the config files from backup files
#(cd "config"; tar cf - $CONFIG_FILES )| (cd "$WEB_APP_NAME/WEB-INF/classes" ; tar xf -)

chown "$TOMCAT_USER_GROUP" "$WEB_APP_NAME" -R


# delete war

/etc/init.d/tomcat6 stop
if [ -e "$WEB_APP_PATH/$PACKAGE_NAME" ]; then
	rm "$WEB_APP_PATH/$PACKAGE_NAME" -rf
fi

mv "$WEB_APP_NAME" "$WEB_APP_PATH/"

# restart tomcat
# /etc/init.d/tomcat5.5 start

# Timing execution remove
# crontab crontab.script
