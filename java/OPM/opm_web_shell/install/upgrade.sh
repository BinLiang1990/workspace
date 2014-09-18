PACKAGE_NAME=opm_web_shell.war
WEB_APP_NAME=opm_web_shell
WEB_APP_PATH=/var/lib/tomcat6/webapps
BACKUP_NAME=backup
mkdir $BACKUP_NAME
mv $WEB_APP_PATH/$WEB_APP_NAME ./$BACKUP_NAME
mkdir $WEB_APP_NAME
cd $WEB_APP_NAME
jar -xf ../$PACKAGE_NAME
cd ..
mv $WEB_APP_NAME $WEB_APP_PATH
cp $BACKUP_NAME/$WEB_APP_NAME/WEB-INF/classes/applicationContext.xml $WEB_APP_PATH/$WEB_APP_NAME/WEB-INF/classes
echo "upgrade completed,please restart tomcat!"