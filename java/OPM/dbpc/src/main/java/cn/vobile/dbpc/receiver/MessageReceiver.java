/*
 * @(#)DBPCReciever.java    1.0 2012-05-23
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.dbpc.receiver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import cn.vobile.dbpc.kvcacher.CacheContainer;

/**
 * DBPCReciever.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version
 * @since 2012-05-23
 */
public class MessageReceiver extends Thread {

	private static Logger logger = Logger.getLogger(MessageReceiver.class);
	private int port;
	private String host;
	private int maxMsgLen;
	private int maxThread;
	private int backlog;
	private CacheContainer cacheContainer;

	/**
	 * thread
	 * */
	@Override
	public void run() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port, backlog,
					InetAddress.getByName(host));
		} catch (UnknownHostException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		ExecutorService executorService = Executors
				.newFixedThreadPool(maxThread);
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				logger.debug(socket.getInetAddress() + ":" + socket.getPort()
						+ " accept " + new Date());
				socket.setSoTimeout(1000 * 60);
				executorService.execute(new MessageService(this, socket,
						maxMsgLen));
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getMaxMsgLen() {
		return maxMsgLen;
	}

	public void setMaxMsgLen(int maxMsgLen) {
		this.maxMsgLen = maxMsgLen;
	}

	public int getMaxThread() {
		return maxThread;
	}

	public void setMaxThread(int maxThread) {
		this.maxThread = maxThread;
	}

	public int getBacklog() {
		return backlog;
	}

	public void setBacklog(int backlog) {
		this.backlog = backlog;
	}

	public CacheContainer getCacheContainer() {
		return cacheContainer;
	}

	public void setCacheContainer(CacheContainer cacheContainer) {
		this.cacheContainer = cacheContainer;
	}
}
