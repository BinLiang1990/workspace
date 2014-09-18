package cn.vobile.vns.consumer.rabbitmq;

import java.io.IOException;

import org.apache.commons.lang.SerializationUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.vobile.vns.consumer.service.NotificationService;
import cn.vobile.vns.model.Notification;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

public class NotificationConsumer implements Consumer, Runnable {

	private Channel channel;
	private Connection connection;
	private final String exchangeName;
	private final String hostName;
	private String queueName;
	private String type;
	private static Logger logger = Logger.getLogger(NotificationConsumer.class);
	private static final ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			"applicationContext.xml");

	public NotificationConsumer(String hostName, String exchangeName,
			String type) {
		this.hostName = hostName;
		this.exchangeName = exchangeName;
		this.type = type;
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(hostName);
		try {
			this.connection = factory.newConnection();
			this.channel = connection.createChannel();
			this.channel.exchangeDeclare(exchangeName, "fanout");
			this.queueName = channel.queueDeclare().getQueue();
			this.channel.queueBind(queueName, exchangeName, "");
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void handleDelivery(String arg0, Envelope arg1,
			BasicProperties arg2, byte[] arg3) throws IOException {
		Notification noti = (Notification) SerializationUtils.deserialize(arg3);
		if (type.equals(noti.getType())) {
			NotificationService ns = (NotificationService) applicationContext
					.getBean(noti.getType() + "ServiceImpl");
			ns.sendNotification(noti);
		}
	}

	@Override
	public void run() {
		try {
			this.channel.basicConsume(queueName, true, this);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	public void close() {
		try {
			this.channel.close();
			this.connection.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void handleCancel(String arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleCancelOk(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleConsumeOk(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleRecoverOk(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleShutdownSignal(String arg0, ShutdownSignalException arg1) {
		// TODO Auto-generated method stub

	}

}
