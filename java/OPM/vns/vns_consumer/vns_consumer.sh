#!/bin/bash
if [ -z $1 ]; then
	echo "usage: $0 start | stop | restart"
fi
case $1 in
	    start)
			PID=$(ps aux | grep vns_consumer.jar | grep java | awk '{print $2}')
			if [ ! -z $PID ]; then
				echo " * vns_consumer has been running with pid $PID."
				exit 1
			fi
			echo "* Starting vns_consumer...ok"
			java -jar vns_consumer.jar
			;;
		stop)
			PID=$(ps aux | grep vns_consumer.jar | grep java | awk '{print $2}')
			if [ -z $PID ]; then
				echo " * vns_consumer has been stopped."
				exit 1
			fi
			echo "* Stopping vns_consumer...ok"
			kill $PID
			;;
		restart)
			echo " * restarting vns_consumer."
			PID=$(ps aux | grep vns_consumer.jar | grep java | awk '{print $2}')
			if [ $PID > 0 ]; then
				kill $PID
			fi
			java -jar vns_consumer.jar
			;;
		*)
			echo "usage: $0 start | stop | restart"
			exit 1
			;;
esac
exit 0