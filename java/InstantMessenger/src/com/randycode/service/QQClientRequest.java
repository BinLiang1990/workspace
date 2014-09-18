package com.randycode.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.randycode.client.view.QQChatWindow;
import com.randycode.model.Message;
import com.randycode.model.QQChatManager;
import com.randycode.model.User;

/*
 * QQ客户端成功登录后会开启一个线程,用来与服务器通信
 */
public class QQClientRequest extends Thread {
	
	private Socket socket;
	
	public QQClientRequest(Socket socket) {
		this.socket = socket;
	}
	public Socket getSocket() {
		return socket;
	}

	@Override
	public void run() {
		while(true) {
			try {
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				Message message = (Message)ois.readObject();
				QQChatWindow qqChatWindow = QQChatManager.getQQChatWindow(message.getReceiver());
				qqChatWindow.showMessage(message);
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
