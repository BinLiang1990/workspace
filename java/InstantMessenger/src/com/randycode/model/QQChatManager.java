package com.randycode.model;

import java.util.HashMap;

import com.randycode.client.view.QQChatWindow;

public class QQChatManager {

	public static HashMap<String, QQChatWindow> hashMap = new HashMap<String, QQChatWindow>();
	
	public static void addChatManager(String username, QQChatWindow qqChatWindow) {
		hashMap.put(username, qqChatWindow);
	}
	
	public static QQChatWindow getQQChatWindow(String username) {
		return hashMap.get(username);
	}
}
