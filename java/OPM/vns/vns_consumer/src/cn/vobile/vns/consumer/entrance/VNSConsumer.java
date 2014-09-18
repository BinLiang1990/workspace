package cn.vobile.vns.consumer.entrance;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.vobile.vns.consumer.rabbitmq.NotificationConsumer;

public class VNSConsumer {

	public static void main(String[] args) {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		NotificationConsumer nc = beanFactory.getBean("notificationConsumer",
				NotificationConsumer.class);
		Thread consumerThread = new Thread(nc);
		consumerThread.start();
	}

}
