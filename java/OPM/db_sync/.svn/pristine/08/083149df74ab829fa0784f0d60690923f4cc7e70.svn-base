INSTALL_DIR=/opt/db_sync
APP_NAME=db_sync
PACKAGE_NAME=db_sync.tar.gz
if [ ! -d "$INSTALL_DIR" ]; then
     echo "invalid file directory $INSTALL_DIR,please check installation directory!" 
     exit 0
fi
cd ..
mkdir backup
mv $INSTALL_DIR/$APP_NAME backup
tar xvf $PACKAGE_NAME
cp backup/$APP_NAME/config/config.properties $APP_NAME/config
mv $APP_NAME $INSTALL_DIR
echo "Upgrade completed!"