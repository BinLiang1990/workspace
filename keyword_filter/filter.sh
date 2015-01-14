#!/bin/bash
program="filter.jar"
if [ -z $1 ]; then
    echo "usage: $0 start | stop | restart"
    exit 1
fi
case $1 in
        start)
            PID=$(ps aux | grep $program | grep java | awk '{print $2}')
            if [ ! -z $PID ]; then
                echo " * keyword_filter has been running with pid $PID."
                exit 1
            fi
            echo "* Starting keyword_filter...ok"
            java -jar $program > /dev/null 2>&1 &
            ;;
        stop)
            PID=$(ps aux | grep $program | grep java | awk '{print $2}')
            if [ -z $PID ]; then
                echo " * keyword_filter has been stopped."
                exit 1
            fi
            echo "* Stopping keyword_filter...ok"
            kill $PID
            ;;
        restart)
            echo " * restarting keyword_filter."
            PID=$(ps aux | grep $program | grep java | awk '{print $2}')
            if [ ! -z $PID ]; then
                kill $PID
            fi
            java -jar $program > /dev/null 2>&1 &
            ;;
        *)
            echo "usage: $0 start | stop | restart"
            exit 1
            ;;
esac
exit 0