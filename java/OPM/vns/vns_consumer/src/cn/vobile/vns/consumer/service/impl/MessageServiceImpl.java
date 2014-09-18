package cn.vobile.vns.consumer.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import cn.vobile.vns.consumer.service.NotificationService;
import cn.vobile.vns.model.Notification;

/*
 * The service can send sms.
 */
public class MessageServiceImpl implements NotificationService {

	private String username;
	private String password;
	private HttpClient client;
	private PostMethod post;
	private static Logger logger = Logger.getLogger(MessageServiceImpl.class);

	public MessageServiceImpl(String username, String password) {
		this.username = username;
		this.password = password;
		this.client = new HttpClient();
		this.post = new PostMethod("http://utf8.sms.webchinese.cn");
		this.post.addRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=utf8");
	}

	@Override
	public void sendNotification(Notification notification) {
		StringBuilder addressBuilder = new StringBuilder();
		List<String> address = notification.getRecipients();
		for (int i = 0; i < address.size(); ++i) {
			addressBuilder.append(address.get(i));
			if (i != address.size() - 1) {
				addressBuilder.append(",");
			}
		}
		NameValuePair[] data = { new NameValuePair("Uid", username),
				new NameValuePair("Key", password),
				new NameValuePair("smsMob", addressBuilder.toString()),
				new NameValuePair("smsText", notification.getContent()) };
		post.setRequestBody(data);
		try {
			client.executeMethod(post);
		} catch (HttpException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		post.getResponseHeaders();
		int statusCode = post.getStatusCode();
		logger.info("statusCode: " + statusCode);
	}

}
