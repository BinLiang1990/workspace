/*
 * @(#)OpmTimer.java    1.0 2012-7-24
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.shell.common;

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
 * @since 1.0
 */
public class OpmTimer {
	/**
	 * 
	 * 
	 * @throws ParseException
	 * 
	 * */
	public String getNowTimeString() throws ParseException {
		SimpleDateFormat dateFormatUtc = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		dateFormatUtc.setTimeZone(TimeZone.getTimeZone("UTC"));
		// Time in Utc
		return dateFormatUtc.format(new Date());
	}

	public Timestamp convertToUTCTimeStamp(String CSTTimeString) {
		Timestamp cstTimestamp = Timestamp.valueOf(CSTTimeString);
		Timestamp utcTimestamp = new Timestamp(
				cstTimestamp.getTime() - 8 * 3600 * 1000);

		return utcTimestamp;
	}

	/**
	 * @param lastUpdateTime
	 * @return
	 */
	public String convertToCSTString(Timestamp utcTimestamp) {
		if (utcTimestamp != null) {
			Timestamp cstTimestamp = new Timestamp(
					utcTimestamp.getTime() + 8 * 3600 * 1000);

			return cstTimestamp.toString();
		}
		return "";
	}

	public Timestamp convertToCSTTimeStamp(String UTCTimeString) {
		Timestamp utcTimestamp = Timestamp.valueOf(UTCTimeString);
		Timestamp cstTimestamp = new Timestamp(
				utcTimestamp.getTime() + 8 * 3600 * 1000);

		return cstTimestamp;
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
	public boolean afterTheGiventime(Timestamp timestamp) throws ParseException {
		Timestamp nowTimestamp = Timestamp.valueOf(getNowTimeString());
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
	public boolean compareTheGiveTime(Integer delayTime,
			Timestamp lastUpdateTime) throws ParseException {
		Timestamp nowTimestamp = Timestamp.valueOf(getNowTimeString());
		if (nowTimestamp.getTime() - lastUpdateTime.getTime() <= delayTime * 1000) {
			return true;
		} else {
			return false;
		}
	}

}
