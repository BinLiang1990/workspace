/*
 * @(#)OpHistoryService.java    1.0 2012-8-7
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import cn.vobile.opm.web.bean.UserOpHistory;

/**
 * OpHistoryService.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-8-7
 * @since 1.0
 */
public interface OpHistoryService {

	/**
	 * @param opmuser_id
	 * @param id_1_al
	 * @param string
	 * @throws ParseException
	 */
	public void insertChangeAlarmLevelHistory(String opmuser_userid,
			int livemonitorid, String originalValue, String changeValue)
			throws ParseException;

	/**
	 * @param opmuser_userid
	 * @param id
	 * @param originalValue
	 * @param changeValue
	 */
	public void insertChangeDelayTimeHistory(String opmuser_userid,
			int livemonitorid, String originalValue, String changeValue)
			throws ParseException;

	/**
	 * @param opmuser_userid
	 * @param id
	 * @param originalValue
	 * @param changeValue
	 */
	public void insertDeleteHistory(String opmuser_userid, int livemonitorid,
			String originalValue, String changeValue) throws ParseException;

	/**
	 * @param opmuser_userid
	 * @param id
	 * @param originalValue
	 * @param changeValue
	 */
	public void insertChangeNoAlrmTimeHistory(String opmuser_userid,
			int livemonitorid, String originalValue, String changeValue)
			throws ParseException;

	/**
	 * @param convertToUTCTimeStamp
	 * @param convertToUTCTimeStamp2
	 * @param userName
	 * @param operation
	 * @param livemonitorId
	 * @return
	 */
	public List<UserOpHistory> findOpHistories(Timestamp startTime,
			Timestamp endTime, String userName, String operation,
			String livemonitorId);
}
