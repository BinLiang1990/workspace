#!/bin/bash

##########################################
#Program:
#	OPM 3.1 upgrade Script.
#History:
#	2013-06-27	WangLin
##########################################

#check the tomcat 6
/etc/init.d/tomcat6 status
tomcat6server=$?
#echo $tomcat6server
if test $tomcat6server -eq 0; then
    echo "tomcat 6 is already running, please stop the it first"
    exit 1
fi

#set -e

function exit_on_error()
{
    echo Update Failed!
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


#if [ ! -z $1 ]; then WEB_APP_NAME=$1
#fi

#if [ ! -z $2 ]; then PACKAGE_NAME=$2
#fi

if [ ! -f "$PACKAGE_NAME" ]; then
	echo "$PACKAGE_NAME used for upgrade does not exist, install failed!"
	exit 1
fi

if [ ! -d "$WEB_APP_PATH/$WEB_APP_NAME" ]; then
	echo "$WEB_APP_PATH/$WEB_APP_NAME has not been installed, please check it ."
	exit 1
fi

if [ "$(pwd)" == "$WEB_APP_PATH" ]; then
	echo "installing steps can not be executed in current directory, please select another directory."
	exit 1
fi

if [ -d "$BACK_UP_DIR" ]; then
	echo "the upgrade has already been finished please run rollback.sh first."
    exit 1
fi


# cp the exist system
mkdir $BACK_UP_DIR
mv $WEB_APP_PATH/$WEB_APP_NAME $BACK_UP_DIR
mkdir "$WEB_APP_NAME"
(cd "$WEB_APP_NAME" ; jar -xf "../${PACKAGE_NAME}")

#config
cp conf.properties $WEB_APP_NAME/WEB-INF/classes
mv "$WEB_APP_NAME" "$WEB_APP_PATH/"

###################################################################
# update the db config



##################################################################
echo "upgrade finished, please start the tomcat6"