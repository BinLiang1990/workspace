#!/bin/bash

#########################################################################
# Author: Wang Lin
# Created Time: Thu 27 Jun 2013
# File Name: dbpc.sh
# Description:
#########################################################################
if [ $1 == start ]
then
	java -jar opm_gui.jar
elif [ $1 == stop ]
then
	PID=$(ps aux | grep  opm_gui.jar |awk '{print $2}' | head -n1)
	kill $PID
else
	echo "Usage $0 [start|stop]"
    exit 0
fi
