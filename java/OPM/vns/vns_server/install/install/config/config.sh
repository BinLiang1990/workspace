#!/bin/bash

source install.properties

cat > applicationContext.xml <<EOF
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:p="http://www.springframework.org/schema/p"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
	http://www.springframework.org/schema/context
	http://www.springframework.org/schema/context/spring-context-3.2.xsd">

	<context:component-scan base-package="cn.vobile.vns.server" />

	<bean id="notificationProducer" class="cn.vobile.vns.server.rabbitmq.NotificationProducer">
		<constructor-arg index="0" value="$rabbitmq_host"/>
		<constructor-arg index="1" value="vns_wl_exchange"/>
	</bean>


</beans>
EOF
