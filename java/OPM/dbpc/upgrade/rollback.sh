#!/bin/bash
INSTALL_DIR=/opt/dbpc
APP_NAME=dbpc
BACKUP_NAME=backup
mv $INSTALL_DIR/$APP_NAME .
mv $BACKUP_NAME/$APP_NAME $INSTALL_DIR/
rm -r $BACKUP_NAME
echo "rollback done"
