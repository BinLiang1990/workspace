#!/bin/bash

##########################################
#Program:
#	OPM 3.1 upgrade Script.
#History:
#	2013-06-27	WangLin
##########################################

/etc/init.d/tomcat6 status
if test $? -eq 0
then
    echo "tomcat 6 is already running, please stop the it first"
    exit 1
fi

set -e

function exit_on_error()
{
    echo Install Failed!
    exit 1
}

trap exit_on_error ERR 

########## application configuration section ########
#web application package name
PACKAGE_NAME=opm_web.war
#web application name
WEB_APP_NAME=opm_web
#configuration files that changes according production env separated with space
#back up dir
BACK_UP_DIR=backup_tmp
########### end of configuration section ############

# tomcat home
WEB_APP_PATH=/var/lib/tomcat6/webapps
# for setting web application owner:group
TOMCAT_USER_GROUP=tomcat6:tomcat6


if [ ! -d "$BACK_UP_DIR" ]; then
	echo "the roll back dir has not exist, please check it."
    exit 1
fi

if [ ! -d "$WEB_APP_PATH/$WEB_APP_NAME" ]; then
	echo "$WEB_APP_PATH/$WEB_APP_NAME has not been installed, please check it ."
	exit 1
fi

if [ "$(pwd)" == "$WEB_APP_PATH" ]; then
	echo "roolback steps can not be executed in current directory, please select another directory."
	exit 1
fi

# delete 
mv $WEB_APP_PATH/$WEB_APP_NAME .

# cp the exist system here
mv $BACK_UP_DIR/$WEB_APP_NAME $WEB_APP_PATH

# delete war

#/etc/init.d/tomcat6 stop
#if [ -e "$WEB_APP_PATH/$PACKAGE_NAME" ]; then
#	rm "$WEB_APP_PATH/$PACKAGE_NAME" -rf
#fi

rm -rf $BACK_UP_DIR

echo "rollback finished, please start the tomcat 6"

