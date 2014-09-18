package com.randycode.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.randycode.model.Message;
import com.randycode.model.ServerThreadManager;
/**
 * @author randy
 *服务器响应每个客户端连接，产生一个线程
 */
public class QQServerResponse extends Thread {

	private Socket socket;
	
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public QQServerResponse(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		while(true) {
			try {
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				Message message = (Message)ois.readObject();
				System.out.println(message.getTime() + " " + message.getSender() + "对" + message.getReceiver() + "说：" + message.getContent());
				QQServerResponse qqServerResponse = ServerThreadManager.getThread(message.getReceiver());
				ObjectOutputStream oos = new ObjectOutputStream(qqServerResponse.getSocket().getOutputStream());
				oos.writeObject(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
