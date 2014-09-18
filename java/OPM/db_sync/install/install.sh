INSTALL_DIR=/opt/db_sync
APP_NAME=db_sync
PACKAGE_NAME=db_sync.tar.gz
if [ ! -d "$INSTALL_DIR" ]; then
     echo "invalid file directory $INSTALL_DIR,please check installation directory!" 
     exit 0
fi
if [ -e "$INSTALL_DIR/$APP_NAME" ]; then
    echo "$INSTALL_DIR/$APP_NAME has been installed, please check it ."
    exit 1
fi
cd ..
tar xvf $PACKAGE_NAME
cp install/config.properties $APP_NAME/config
mv $APP_NAME $INSTALL_DIR
echo "Install completed!"