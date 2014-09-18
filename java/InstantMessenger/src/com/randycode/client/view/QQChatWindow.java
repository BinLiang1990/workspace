package com.randycode.client.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.omg.CORBA.FREE_MEM;

import com.randycode.model.Message;
import com.randycode.model.QQClientTheadManager;
import com.randycode.service.QQClientRequest;

/*
 * QQ聊天窗口
 */
public class QQChatWindow extends JFrame implements ActionListener {

	private JTextArea jTextArea;
	private JTextField jTextField;
	private JButton send;
	private JPanel jPanel;
	private JScrollPane jScrollPane;
	private Message message;
	private String username;
	public static Format format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public QQChatWindow(String username, String friend) {
		this.username = username;
		message = new Message();
		message.setSender(username);
		message.setReceiver(friend);
		jTextArea = new JTextArea();
		jScrollPane = new JScrollPane(jTextArea);
		jTextField = new JTextField(15);
		send = new JButton("发送");
		send.addActionListener(this);
		jPanel = new JPanel(new FlowLayout());
		jPanel.add(jTextField);
		jPanel.add(send);
		this.setLayout(new BorderLayout());
		this.add(jScrollPane, BorderLayout.CENTER);
		this.add(jPanel, BorderLayout.SOUTH);
		this.setTitle(username + "正在与" + friend + "聊天");
		this.setIconImage(new ImageIcon("images/qq.gif").getImage());
		this.setBounds(300, 100, 300, 200);
		this.setVisible(true);
		
	}
	
	//发送聊天内容
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == send) {
			message.setContent(jTextField.getText());
			jTextField.setText("");
			Date date = new Date();
			message.setTime(format.format(date));
			jTextArea.append(message.getTime() + " " + message.getSender() + "说：" + message.getContent() + "\n");
			try {
				ObjectOutputStream oos = new ObjectOutputStream(QQClientTheadManager.getQQClientThread(username).getSocket().getOutputStream());
				oos.writeObject(message);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void showMessage(Message message) {
		jTextArea.append(message.getTime() + " " + message.getSender() + "说：" + message.getContent() + "\n");
	}
	
}
