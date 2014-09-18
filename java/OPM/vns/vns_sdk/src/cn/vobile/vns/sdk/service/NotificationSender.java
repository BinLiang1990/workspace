package cn.vobile.vns.sdk.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.vobile.vns.sdk.exception.NotificationStyleException;
import cn.vobile.vns.sdk.model.Notification;

/**
 * @brief NotificationSender can send json to server by http protocol
 * 
 * @author wang_lin
 * 
 */
public class NotificationSender {

	private String host;
	private int port;

	public NotificationSender(String host, int port) {
		this.host = host;
		this.port = port;
	}

	/**
	 * @brief Send json to server by http protocol
	 * @param notification
	 * @throws NotificationStyleException
	 * @throws IOException
	 */
	public void send(Notification notification)
			throws NotificationStyleException, IOException {
		if (!notification.checkStyle()) {
			throw new NotificationStyleException(
					"The style of sender or recipients is invalid.");
		}
		try {
			URL url = new URL("http://" + host + ":" + Integer.toString(port)
					+ "/vns_server/notification/add");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.connect();
			StringBuilder json = new StringBuilder();
			json.append("{\"type\":\"");
			json.append(notification.getType());
			json.append("\",\"sender\":\"");
			json.append(notification.getSender());
			json.append("\",\"recipients\":[");
			StringBuilder addressBuilder = new StringBuilder();
			for (String string : notification.getRecipients()) {
				addressBuilder.append("\"");
				addressBuilder.append(string);
				addressBuilder.append("\",");
			}
			json.append(addressBuilder.substring(0, addressBuilder.length() - 1));
			json.append("],\"subject\":\"");
			json.append(notification.getSubject());
			json.append("\",\"content\":\"");
			json.append(notification.getContent());
			json.append("\"}");
			OutputStream os = conn.getOutputStream();
			os.write(json.toString().getBytes());
			os.flush();
			os.close();
			conn.getInputStream();
			conn.disconnect();
		} catch (FileNotFoundException e) {
			// Please ignore FileNotFoundException
		}

	}
}
