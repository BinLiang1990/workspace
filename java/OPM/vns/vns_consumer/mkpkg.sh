#!/bin/bash
#

if [ $# -lt 1 ] ; then
	echo "$0 pkgname"
	exit 1
fi

pkgname=$1

if [ $? -ne 0 ] ; then
	exit 1
fi

echo "Compiling..."
. PROG_VERSION.def
echo $APP_VERSION
echo $pkgname
ant clean -DpackageName=$pkgname -Dversion=$APP_VERSION
ant -DpackageName=$pkgname -Dversion=$APP_VERSION
if [ $? -ne 0 ] ; then
	exit 1
fi

cp dist/$pkgname.tar.gz .
rm -rf dist
exit $?
