package com.randycode.model;

import java.util.HashMap;

import com.randycode.service.QQClientRequest;

//管理客户端线程，每个用户名对应唯一的线程
public class QQClientTheadManager {

	public static HashMap<String, QQClientRequest> hashMap = new HashMap<String, QQClientRequest>();
	
	public static void addClientThread(String username, QQClientRequest qqClientRequest) {
		hashMap.put(username, qqClientRequest);
	}
	
	public static QQClientRequest getQQClientThread(String username) {
		return hashMap.get(username);
	}
}
