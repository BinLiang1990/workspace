package com.randycode.model;

import java.util.HashMap;

import com.randycode.service.QQServerResponse;

/*
 * 管理服务器端线程,与客户端帐号相对应
 */
public class ServerThreadManager {

	public static HashMap<String, QQServerResponse> hashMap = new HashMap<String, QQServerResponse>();
	
	public static void addClientThread(String key, QQServerResponse value) {
		hashMap.put(key, value);
	}
	
	public static QQServerResponse getThread(String username) {
		return hashMap.get(username);
	}
}
