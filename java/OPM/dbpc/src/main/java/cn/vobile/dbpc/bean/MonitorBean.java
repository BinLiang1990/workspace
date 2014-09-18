/*
 * @(#)MonitorBean.java    1.0 2011-9-26
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.dbpc.bean;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * MonitorBean.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan Huang</A>
 * @version 3.0.0.0 2012-05-29
 * @since 3.0.0.0
 */
public class MonitorBean {

	private String service;
	private String component;
	private Date updateAt;
	private String ipaddress;
	private String version;

	public String getService() {
		return service;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public void setService(String service) {
		this.service = service;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * format the time
	 * 
	 * @return String formated utf time
	 * */
	public String formatTime() {

		Timestamp timestamp = new Timestamp(getUpdateAt().getTime());
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

		return dateFormat.format(timestamp);
	}

}
