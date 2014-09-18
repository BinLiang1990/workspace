/*
 * @(#)JavaConcurrentHashmap.java    1.0 2012-05-21
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.dbpc.kvcacher;

import java.util.concurrent.ConcurrentHashMap;

import cn.vobile.dbpc.bean.MonitorBean;

public class NativeCacheContainer implements CacheContainer {

	private ConcurrentHashMap<String, MonitorBean> cache;

	public NativeCacheContainer() {
		this.cache = new ConcurrentHashMap<String, MonitorBean>();
	}

	public void setKey(String key, MonitorBean mb) {
		this.cache.put(key, mb);
	}

	public void getKey(String key) {
		// TODO Auto-generated method stub

	}

	public ConcurrentHashMap<String, MonitorBean> items() {
		return this.cache;
	}

	public void delKey(String key) {
		// TODO Auto-generated method stub

	}

	public void expire(String key, int seconds) {
		// TODO Auto-generated method stub

	}

}
