/*
 * @(#)MessageService.java    1.0 2012-05-23
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.dbpc.receiver;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Date;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import cn.vobile.dbpc.bean.MonitorBean;

/**
 * MessageService.java <BR>
 * A Service to get the heart beat from Client through socket <BR>
 * And update the key value cache system <BR>
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan Huang</A>
 * @version
 * @since 2012-05-23
 */
public class MessageService implements Runnable {

	private static Logger logger = Logger.getLogger(MessageService.class);
	private MessageReceiver receiver;
	private Socket socket;
	private String host;
	private int maxMsgLen;

	public MessageService(MessageReceiver receiver, Socket socket, int maxMsgLen) {
		this.receiver = receiver;
		this.socket = socket;
		this.host = this.socket.getInetAddress().getHostAddress();
		this.maxMsgLen = maxMsgLen;
	}

	/**
	 * Update message to cachecontainer
	 * */
	@Override
	public void run() {
		MonitorBean mb = null;
		String response = "success";
		try {
			logger.debug(socket.getInetAddress() + ":" + socket.getPort()
					+ " thread " + new Date());
			// parse the json to monitorbean
			mb = parseSocketInput(socket);
			// update into the kv cache system
			logger.debug(socket.getInetAddress() + ":" + socket.getPort()
					+ " start update map " + new Date());
			logger.debug(socket.getInetAddress() + ":" + socket.getPort()
					+ " end update map " + new Date());
		} catch (Exception e) {
			logger.error(this.host + this.socket.getPort() + e.getMessage());
			response = "failed";
		} finally {
			if (this.socket != null) {
				logger.debug(socket.getInetAddress() + ":" + socket.getPort()
						+ " start close socket " + new Date());
				reply(socket, response);
				try {
					this.socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				logger.debug(socket.getInetAddress() + ":" + socket.getPort()
						+ " end close socket " + new Date());
			}
		}
		try {
			updateApplicationStatus(mb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Parse message
	 * 
	 * @return MonitorBean
	 * @throws IOException
	 * */
	private MonitorBean parseSocketInput(Socket socket) throws IOException {
		int BYTE_BUFFER = 200;
		BufferedInputStream bufferedInputStream = new BufferedInputStream(
				this.socket.getInputStream(), BYTE_BUFFER);

		String line = "";
		StringBuffer stringBuffer = new StringBuffer();
		byte[] byteBuf = new byte[BYTE_BUFFER];
		if (bufferedInputStream != null) {
			int bytesRead;
			bytesRead = bufferedInputStream.read(byteBuf, 0, BYTE_BUFFER);
			try {
				line = new String(byteBuf, 0, bytesRead, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stringBuffer.append(line);
		}
		String string = new String(stringBuffer.toString().getBytes("utf8"),
				"utf8");
		if (string.length() > this.maxMsgLen) {
			logger.error("The messgae is too long.");
		}
		JSONArray jsonArray = new JSONObject(string).getJSONArray("status");
		JSONObject json = jsonArray.getJSONObject(0);
		MonitorBean mb = new MonitorBean();
		mb.setComponent(json.getString("component").trim());
		mb.setService(json.getString("service").trim());
		mb.setIpaddress(socket.getInetAddress().getHostAddress());
		mb.setUpdateAt(new Date());
		mb.setVersion("1");
		if (jsonArray.length() > 1) {
			String address = jsonArray.getJSONObject(1).getString("address");
			if (address != null && address.length() > 0) {
				mb.setIpaddress(address);
				mb.setVersion("2");
			}
		}
		if (null == mb.getComponent() || null == mb.getService()) {
			logger.error("Invalid message.");
		}
		return mb;

		// BufferedReader input = null;
		// StringBuilder buffer = new StringBuilder();
		// MonitorBean mb = new MonitorBean();
		// try {
		// logger.debug(socket.getInetAddress() + ":" + socket.getPort()
		// + " start read " + new Date());
		// input = new BufferedReader(new InputStreamReader(
		// socket.getInputStream()));
		// String line = input.readLine();
		// while (line != null) {
		// buffer.append(line);
		// buffer.append("\n");
		// line = input.readLine();
		// }
		// logger.debug(socket.getInetAddress() + ":" + socket.getPort()
		// + " end read " + new Date());
		// input.close();
		// String string = new String(buffer.toString().getBytes("utf8"),
		// "utf8");
		// if (string.length() > this.maxMsgLen) {
		// logger.error("The messgae is too long.");
		// }
		// JSONObject json = new JSONObject(string).getJSONArray("status")
		// .getJSONObject(0);
		// mb.setComponent(json.getString("component"));
		// mb.setService(json.getString("service"));
		// mb.setIpaddress(socket.getInetAddress().getHostAddress());
		// mb.setUpdateAt(new Date());
		// if (null == mb.getComponent() || null == mb.getService()) {
		// logger.error("Invalid message.");
		// }
		// } catch (IOException e) {
		// logger.error(e.getMessage());
		// } catch (JSONException e) {
		// logger.error(e.getMessage());
		// }
		// return mb;
	}

	/**
	 * this function updates the application's state in the key value cache
	 * system
	 * 
	 * */
	private void updateApplicationStatus(MonitorBean mb) throws Exception {
		String key = mb.getIpaddress() + mb.getService() + mb.getComponent();
		this.receiver.getCacheContainer().setKey(key, mb);
	}

	public void reply(Socket sock, String message) {
		PrintStream output;
		try {
			output = new PrintStream(sock.getOutputStream());
			output.print(message);
			output.flush();
			output.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
}
