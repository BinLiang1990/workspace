/*
 * @(#)HistoryService.java    1.0 2012-7-25
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.SortedMap;

import cn.vobile.opm.web.dao.AlarmHistory;
import cn.vobile.opm.web.bean.AlarmPointStatusChangingHistory;

/**
 * HistoryService.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-25
 * @since 1.0
 */
public interface HistoryService {
	/**
	 * update a history record
	 * 
	 * @return true if success
	 * */
	public boolean updateHistory(AlarmHistory history);

	/**
	 * update a list of history
	 * 
	 * @return true if success
	 * */
	public boolean updateHistories(List<AlarmHistory> histories);

	/**
	 * get all histories
	 * */
	public List<AlarmHistory> getHistories();

	/**
	 *
	 * */
	List<AlarmHistory> getHistoriesByLid(int lid);

	/**
	 * @param acplid
	 * @param acceptedPerson
	 * @throws ParseException
	 */
	public void updateAccepted(String acplid, String acceptedPerson)
			throws ParseException;

	/**
	 * @param alarmedPerson
	 * @param alarmedFeedback
	 * @param alarmedNote
	 * @throws ParseException
	 */
	public void updateAlarmed(String alalid, String alarmedPerson,
			String alarmedFeedback, String alarmedNote) throws ParseException;

	/**
	 * @param reshid
	 * @param responsedPerson
	 * @param responsedHandling
	 * @param responsedNote
	 * @throws ParseException
	 */
	public void updateResponsed(int reshid, String responsedPerson,
			String responsedHandling, String responsedNote)
			throws ParseException;

	/**
	 * @param lid
	 * @return
	 * @throws ParseException
	 */
	public List<AlarmPointStatusChangingHistory> getAlarmPointStatusChangingHistoriesByLid(
			int lid) throws ParseException;

	/**
	 * @param convertToUTCTimeStamp
	 * @param convertToUTCTimeStamp2
	 * @param serviceName
	 * @param type
	 * @param alarmLevel
	 * @return
	 * @throws ParseException
	 */
	public List<AlarmPointStatusChangingHistory> findAlarmPointStatusChangingHistories(
			Timestamp startTime, Timestamp endTime, String serviceName,
			String type, String alarmLevel) throws ParseException;

	/**
	 * @param convertToUTCTimeStamp
	 * @param convertToUTCTimeStamp2
	 * @param serviceName
	 * @param type
	 * @param alarmLevel
	 * @return
	 */
	public List<AlarmPointStatusChangingHistory> findNoOkAlarmPointStatusChangingHistories(
			Timestamp startTime, Timestamp endTime, String serviceName,
			String type, String alarmLevel) throws ParseException;

	/**
	 * @param alarmPointStatusChangingHistories
	 * @return
	 */
	public SortedMap<String, Integer> getApointDateAlarmCountMap(
			List<AlarmPointStatusChangingHistory> alarmPointStatusChangingHistories);

	/**
	 * @param startTime
	 * @param endTime
	 * @param alarmPointStatusChangingHistories
	 * @return
	 */
	public SortedMap<String, Integer> getExceptedHistoryDateAlarmCountMap(
			String startTime,
			String endTime,
			List<AlarmPointStatusChangingHistory> alarmPointStatusChangingHistories);

	/**
	 * @return
	 */
	public List<String> getServiceNames();

}
