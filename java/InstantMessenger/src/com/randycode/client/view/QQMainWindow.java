package com.randycode.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.randycode.model.QQChatManager;

//主窗口
public class QQMainWindow extends JFrame implements MouseListener {

	private JButton myFriends;
	private JScrollPane jScrollPane;
	private JPanel friendsList;
	private String username;
	public QQMainWindow(String username) {
		this.username = username;
		myFriends = new JButton("我的好友");
		friendsList = new JPanel(new GridLayout(100, 1, 4, 4));
		for(int i = 0; i < 100; ++i) {
			JLabel jLabel = new JLabel(i + 1 + "", new ImageIcon("images/mm.jpg"), JLabel.LEFT);
			jLabel.setEnabled(false);
			if(jLabel.getText().equals(username)) {
				jLabel.setEnabled(true);
			}
			jLabel.addMouseListener(this);
			friendsList.add(jLabel);
		}
		jScrollPane = new JScrollPane(friendsList);
		this.setLayout(new BorderLayout());
		this.add(myFriends, BorderLayout.NORTH);
		this.add(jScrollPane, BorderLayout.CENTER);
		this.setBounds(100, 100, 140, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(username);
		this.setBounds(100, 100, 140, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	//双击好友，打开聊天窗口
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		if(e.getClickCount() == 2) {
			String friend = ((JLabel)e.getSource()).getText();
			QQChatWindow qqChatWindow = new QQChatWindow(username, friend);
			QQChatManager.addChatManager(username, qqChatWindow);
		}
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		JLabel jLabel = (JLabel)e.getSource();
		jLabel.setForeground(Color.red);
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		JLabel jLabel = (JLabel)e.getSource();
		jLabel.setForeground(Color.black);
	}
}

