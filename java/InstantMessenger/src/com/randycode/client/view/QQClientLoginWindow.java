package com.randycode.client.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.randycode.model.Message;
import com.randycode.model.QQClientTheadManager;
import com.randycode.model.User;
import com.randycode.service.QQClientRequest;

//登录窗口
public class QQClientLoginWindow extends JFrame implements ActionListener {

	private JPanel buttons, mainUI;
	private JButton login, cancel, register, clearNumber, forgetNumber;
	private JLabel welcome, qqNumber, qqPassword;
	private JTextField numberField;
	private JPasswordField passwordField;
	private JCheckBox invisible, rememberPassword;
	
	public QQClientLoginWindow() {
		welcome = new JLabel(new ImageIcon("images/header.gif"));
		this.setBounds(100, 100, 350, 240);
		this.setLayout(new BorderLayout());
		this.add(welcome, BorderLayout.NORTH);
		buttons = new JPanel(new FlowLayout());
		login = new JButton(new ImageIcon("images/login.gif"));
		login.addActionListener(this);
		cancel = new JButton(new ImageIcon("images/cancel.gif"));
		register = new JButton(new ImageIcon("images/register.gif"));
		buttons.add(login);
		buttons.add(cancel);
		buttons.add(register);
		mainUI = new JPanel(new GridLayout(3, 3));
		qqNumber = new JLabel("QQ号码");
		qqPassword = new JLabel("QQ密码");
		numberField = new JTextField();
		passwordField = new JPasswordField();
		clearNumber = new JButton("清除号码");
		forgetNumber = new JButton("忘记密码");
		invisible = new JCheckBox("隐身登录");
		rememberPassword = new JCheckBox("记住密码");
		mainUI.add(qqNumber);
		mainUI.add(numberField);
		mainUI.add(clearNumber);
		mainUI.add(qqPassword);
		mainUI.add(passwordField);
		mainUI.add(forgetNumber);
		mainUI.add(invisible);
		mainUI.add(rememberPassword);
		this.add(mainUI, BorderLayout.CENTER);
		this.add(buttons, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("QQ客户端");
		this.setVisible(true);
	}
	
	//监听登录按钮
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == login) {
			User user = new User();
			user.setUsername(numberField.getText());
			user.setPassword(new String(passwordField.getPassword()));
			if(sendLoginInfo(user)) {
				//登录验证通过后打开主窗口
				new QQMainWindow(user.getUsername());
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "用户名或者密码错误");
			}
		}
	}
	
	//客户端发送帐号信息到服务器请求登录
	public boolean sendLoginInfo(Object object) {
		boolean loginSuccess = false;
		try {
			Socket socket = new Socket("127.0.0.1", 1234);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(object);
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			int messageType = ((Message)ois.readObject()).getMessageType();
			if(messageType == 1) {
				loginSuccess = true;
				QQClientRequest qqClientRequest = new QQClientRequest(socket);
				qqClientRequest.start();
				QQClientTheadManager.addClientThread(numberField.getText(), qqClientRequest);
			} else {
				socket.close();
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loginSuccess;
	}
	
	public static void main(String[] args) {
		QQClientLoginWindow QQClientLoginWindow = new QQClientLoginWindow();
		
	}

	
}
