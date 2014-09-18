/*
 * @(#)KVCacheSystem.java    1.0 2012-05-23
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.dbpc.kvcacher;

import java.util.concurrent.ConcurrentHashMap;

import cn.vobile.dbpc.bean.MonitorBean;

/**
 * KVCacheSystem.java <br>
 * Key Value Cache System
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan Huang</A>
 * @version
 * @since 2012-05-23
 */
public interface CacheContainer {
	/**
	 * Set the key, valure pair to cache system
	 */
	public void setKey(String key, MonitorBean mb);

	/**
	 * Get the value by key
	 */
	public void getKey(String key);

	/**
	 * Remove the key
	 */
	public void delKey(String key);

	/**
	 * Return the items
	 */
	// It's Okay to convert the data to concurrent hash map
	// even the data is from redis server
	// also the value should convert to type MonitorBean
	public ConcurrentHashMap<String, MonitorBean> items();

	/**
	 * Expire the key in given time
	 */
	public void expire(String key, int seconds);
}
