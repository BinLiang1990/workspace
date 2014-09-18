#!/bin/bash

source install.properties

cat > applicationContext.xml <<EOF
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
	http://www.springframework.org/schema/context
	http://www.springframework.org/schema/context/spring-context-3.2.xsd">

	<context:component-scan base-package="cn.vobile.vns.consumer" />

	<bean id="sender" class="org.springframework.mail.javamail.JavaMailSenderImpl"
		p:host="mail.vobile.cn" p:username="$mail_username" p:password="$mail_password">
		<property name="javaMailProperties">
			<props>
				<prop key="mail.smtp.auth">true</prop>
			</props>
		</property>
	</bean>

	<bean id="notificationConsumer" 
		class="cn.vobile.vns.consumer.rabbitmq.NotificationConsumer">
		<constructor-arg index="0" value="$rabbitmq_host" />
		<constructor-arg index="1" value="vns_wl_exchange" />
		<constructor-arg index="2" value="$notification_type" />
	</bean>

	<bean id="messageServiceImpl" 
		class="cn.vobile.vns.consumer.service.impl.MessageServiceImpl">
		<constructor-arg index="0" value="$message_username" />
		<constructor-arg index="1" value="$message_password" />
	</bean>
	
	<bean id="taskExecutor"
		class="org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor"
		p:corePoolSize="10" p:maxPoolSize="30" />


</beans>
EOF
cat > heartbeat.py <<EOF
#!/usr/bin/env python
# coding=utf-8
import socket
import commands
import threading
import time
def send_heartbeat():
    while True:
        pid1 = commands.getoutput('ps aux | grep vns_consumer.jar | grep java').split(" ")[1]
        pid2 = commands.getoutput('ps aux | grep vns_consumer.jar | grep java').split(" ")[1]
        if pid1 == pid2:
            sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            sock.connect(('$dbpc_host', $dbpc_port))
            sock.send('{"status": [{"service": "$service", "component": "$component"}]}')
        time.sleep(60)
t = threading.Thread(target = send_heartbeat)
t.start();
EOF