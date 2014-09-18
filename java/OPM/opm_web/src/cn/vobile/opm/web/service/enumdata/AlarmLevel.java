/*
 * @(#)AlarmLevel.java    1.0 2012-8-7
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.service.enumdata;

/**
 * AlarmLevel.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-8-7
 * @since 1.0
 */
public enum AlarmLevel {
	ACTIVE("active"), WARNING("warning"), INACTIVE("inactive"), DELETED(
			"deleted");

	/**
	 *
	 */
	private AlarmLevel(String alarmLevel) {
		setAlarmLevel(alarmLevel);
	}

	private String alarmLevel;

	/**
	 * @return the alarmLevel
	 */
	public String getAlarmLevel() {
		return alarmLevel;
	}

	/**
	 * @param alarmLevel
	 *            the alarmLevel to set
	 */
	public void setAlarmLevel(String alarmLevel) {
		this.alarmLevel = alarmLevel;
	}
}
