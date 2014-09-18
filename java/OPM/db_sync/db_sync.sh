#!/bin/bash
if [ -z $1 ]; then
    echo "usage: $0 start | stop | restart"
    exit 1
fi
case $1 in
        start)
            PID=$(ps aux | grep db_sync.jar | grep java | awk '{print $2}')
            if [ ! -z $PID ]; then
                echo " * db_sync has been running with pid $PID."
                exit 1
            fi
            echo "* Starting db_sync...ok"
            java -jar db_sync.jar
            ;;
        stop)
            PID=$(ps aux | grep db_sync.jar | grep java | awk '{print $2}')
            if [ -z $PID ]; then
                echo " * db_sync has been stopped."
                exit 1
            fi
            echo "* Stopping db_sync...ok"
            kill $PID
            ;;
        restart)
            echo " * restarting db_sync."
            PID=$(ps aux | grep db_sync.jar | grep java | awk '{print $2}')
            if [ $PID > 0 ]; then
                kill $PID
            fi
            java -jar db_sync.jar
            ;;
        *)
            echo "usage: $0 start | stop | restart"
            exit 1
            ;;
esac
exit 0