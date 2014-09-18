package cn.vobile.vns.server.rabbitmq;

import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.lang.SerializationUtils;
import org.apache.log4j.Logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * The rabbit mq producer send notification that is from vns server to queue.
 * 
 * @author wang_lin
 * 
 */
public class NotificationProducer {

	private Channel channel;
	private Connection connection;
	private final String exchangeName;
	private final String hostName;
	private static Logger logger = Logger.getLogger(NotificationProducer.class);

	public NotificationProducer(String hostName, String exchangeName) {
		this.hostName = hostName;
		this.exchangeName = exchangeName;
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(hostName);
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
			channel.exchangeDeclare(exchangeName, "fanout");
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	public void sendMessage(Serializable object) throws IOException {
		channel.basicPublish(exchangeName, "", null,
				SerializationUtils.serialize(object));

	}

	public void close() {
		try {
			this.channel.close();
			this.connection.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

}
