#!/bin/bash

##########################################
#Program:
#   OPM 3.0 Install Script.
#History:
#   2012-08-14  HuangPanpan
##########################################

/etc/init.d/tomcat6 status
tomcat6server=$?
#echo $tomcat6server
if test $tomcat6server -eq 0; then
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
########### end of configuration section ############

# tomcat home
WEB_APP_PATH=/var/lib/tomcat6/webapps
# for setting web application owner:group




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

# extract from war package
mkdir "$WEB_APP_NAME"
(cd "$WEB_APP_NAME" ; jar -xf "../${PACKAGE_NAME}")

#config
cp conf.properties $WEB_APP_NAME/WEB-INF/classes

if [ -e "$WEB_APP_PATH/$PACKAGE_NAME" ]; then
    rm "$WEB_APP_PATH/$PACKAGE_NAME" -rf
fi

mv "$WEB_APP_NAME" "$WEB_APP_PATH/"

echo "install finished, please start tomcat6"

# restart tomcat
# /etc/init.d/tomcat5.5 start

# Timing execution remove
# crontab crontab.script
