package com.randycode.server.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.randycode.model.Message;
import com.randycode.model.ServerThreadManager;
import com.randycode.model.User;
import com.randycode.service.QQServerResponse;

//服务器窗口
public class QQServerWindow extends JFrame implements ActionListener {

	private JButton start, stop;
	
	public QQServerWindow() {
		start = new JButton("开始");
		stop = new JButton("停止");
		start.addActionListener(this);
		this.setLayout(new FlowLayout());
		this.add(start);
		this.add(stop);
		this.setBounds(100, 100, 300, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	//监听服务器启动按钮
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == start) {
			try {
				ServerSocket serverSocket = new ServerSocket(1234);
				System.out.println("QQ服务器正在监控1234端口");
				while(true) {
					Socket socket = serverSocket.accept();
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					User user = (User)ois.readObject();
					Message message = new Message();
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					if("1".equals(user.getPassword())) {
						System.out.println("服务器接受到用户id：" + user.getUsername() + " 密码：" + user.getPassword());
						message.setMessageType(1);
						oos.writeObject(message);
						//新开一个线程，与客户端通信
						QQServerResponse qqServerResponse = new QQServerResponse(socket);
						ServerThreadManager.addClientThread(user.getUsername(), qqServerResponse);
						qqServerResponse.start();
					} else {
						message.setMessageType(0);
						oos.writeObject(message);
					}
				}
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args) {
		new QQServerWindow();
	}

	
}

