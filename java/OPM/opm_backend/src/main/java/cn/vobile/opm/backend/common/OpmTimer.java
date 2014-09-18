/*
 * @(#)OpmTimer.java    1.0 2012-7-24
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.backend.common;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * OpmTimer.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-24
 * @change CST is a duplicated word, it can be Central Stander Time, change it
 *         to GMT+8
 * @since 1.0
 */
public class OpmTimer {
	/**
	 * 
	 * 
	 * @throws ParseException
	 * 
	 * */
	public static String getNowTimeString() throws ParseException {
		SimpleDateFormat dateFormatUtc = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		dateFormatUtc.setTimeZone(TimeZone.getTimeZone("UTC"));
		// Time in Utc
		return dateFormatUtc.format(new Date());
	}

	/**
	 * 
	 * 
	 * @throws ParseException
	 * 
	 * */
	public static String getNowTimeCSTString() throws ParseException {
		SimpleDateFormat dateFormatUtc = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		dateFormatUtc.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		// Time in CST
		return dateFormatUtc.format(new Date());
	}

	public String convertToCSTString(Timestamp timestamp) {
		if (timestamp != null) {
			Timestamp cstTimestamp = new Timestamp(timestamp.getTime());
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			String date = dateFormat.format(new Date(cstTimestamp.getTime()));
			return date;
		}
		return "";
	}

	/**
	 * if now is before the timestamp given, then return ture
	 * 
	 * @param Timestamp
	 *            timestamp
	 * 
	 * @return true if now is afters the timestamp
	 * @throws ParseException
	 * 
	 * */
	public static boolean afterTheGiventime(Timestamp timestamp)
			throws ParseException {
		Timestamp nowTimestamp = new Timestamp(System.currentTimeMillis());
		if (nowTimestamp.compareTo(timestamp) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @param delayTime
	 * @param noAlarmEndTime
	 * @return true if the lastupdatetime is in the delay time
	 * @throws ParseException
	 */
	public static boolean compareTheGiveTime(int delayTime,
			Timestamp lastUpdateTime) throws ParseException {
		Long now = System.currentTimeMillis();
		if (now - lastUpdateTime.getTime() <= delayTime * 1000) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * test main
	 * */
	public static void main(String[] args) throws ParseException {
		Timestamp timestamp = Timestamp.valueOf("2012-07-24 05:20:37"); // utctime
		System.out.println(OpmTimer.compareTheGiveTime(600, timestamp));
	}
}
