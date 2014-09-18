/*
 * @(#)HistoryServiceImpl.java    1.0 2012-7-25
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import cn.vobile.opm.web.bean.AlarmPointStatusChangingHistory;
import cn.vobile.opm.web.common.OpmTimer;
import cn.vobile.opm.web.dao.AlarmHistory;
import cn.vobile.opm.web.dao.AlarmHistoryDAO;
import cn.vobile.opm.web.dao.DaoCreator;
import cn.vobile.opm.web.dao.Livemonitor;
import cn.vobile.opm.web.dao.LivemonitorDAO;
import cn.vobile.opm.web.service.HistoryService;
import cn.vobile.opm.web.service.enumdata.Status;

/**
 * HistoryServiceImpl.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-25
 * @since 1.0
 */
public class HistoryServiceImpl implements HistoryService {

	private AlarmHistoryDAO historyDAO;
	private LivemonitorDAO livemonitorDAO;
	private AlarmHistoryDAO alarmHistoryDAO;
	private OpmTimer opmTimer;

	/**
	 *
	 */
	public HistoryServiceImpl() {
		this.historyDAO = (AlarmHistoryDAO) DaoCreator
				.createDao("AlarmHistoryDAO");
		this.livemonitorDAO = (LivemonitorDAO) DaoCreator
				.createDao("LivemonitorDAO");
		this.alarmHistoryDAO = (AlarmHistoryDAO) DaoCreator
				.createDao("AlarmHistoryDAO");
		this.opmTimer = new OpmTimer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.vobile.opm.web.service.HistoryService#updateHistory(cn.vobile.opm.
	 * web.bean.History)
	 */
	@Override
	public boolean updateHistory(AlarmHistory history) {
		try {
			AlarmHistory historyIndb = historyDAO.findById(history.getId());

			historyIndb.setIsAccepted(history.getIsAccepted());
			historyIndb.setAcceptedTime(history.getAcceptedTime());
			historyIndb.setAcceptedPerson(history.getAcceptedPerson());

			historyIndb.setIsAlarmed(history.getIsAlarmed());
			historyIndb.setAlarmedTime(history.getAlarmedTime());
			historyIndb.setAlarmedPerson(history.getAlarmedPerson());
			historyIndb.setAlarmedFeedback(history.getAlarmedFeedback());
			historyIndb.setAlarmedNote(history.getAlarmedNote());

			historyIndb.setIsResponsed(history.getIsResponsed());
			historyIndb.setResponsedTime(history.getResponsedTime());
			historyIndb.setResponsedPerson(history.getResponsedPerson());
			historyIndb.setResponsedHandling(history.getResponsedHandling());
			historyIndb.setResponsedNote(history.getResponsedNote());

			historyDAO.update(historyIndb);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.vobile.opm.web.service.HistoryService#updateHistories(java.util.List)
	 */
	@Override
	public boolean updateHistories(List<AlarmHistory> histories) {
		boolean result = true;

		for (AlarmHistory history : histories) {
			result = result && updateHistory(history);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.vobile.opm.web.service.HistoryService#getHistories()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<AlarmHistory> getHistories() {
		return historyDAO.findAll();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AlarmHistory> getHistoriesByLid(int lid) {
		return new ArrayList<AlarmHistory>(
				alarmHistoryDAO.findByLivemonitorId(lid));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.vobile.opm.web.service.HistoryService#
	 * getAlarmPointStatusChangingHistoriesByLid(int)
	 */
	@Override
	@SuppressWarnings({})
	public List<AlarmPointStatusChangingHistory> getAlarmPointStatusChangingHistoriesByLid(
			int lid) throws ParseException {
		List<AlarmHistory> originalHistories = getHistoriesByLid(lid);

		List<AlarmHistory> changedHistories = new ArrayList<AlarmHistory>(
				originalHistories);
		return CreateNoOkAlarmPointStatusChangingHistories(originalHistories,
				changedHistories);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.vobile.opm.web.service.HistoryService#
	 * findAlarmPointStatusChangingHistories(java.sql.Timestamp,
	 * java.sql.Timestamp, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<AlarmPointStatusChangingHistory> findAlarmPointStatusChangingHistories(
			Timestamp startTime, Timestamp endTime, String serviceName,
			String type, String alarmLevel) throws ParseException {
		List<Livemonitor> livemonitors = livemonitorDAO.findAll();

		List<AlarmPointStatusChangingHistory> alarmPointStatusChangingHistories = new ArrayList<AlarmPointStatusChangingHistory>();

		for (Livemonitor livemonitor : livemonitors) {
			int livemonitorId = livemonitor.getId();
			List<AlarmHistory> originalHistories = historyDAO
					.findExceptedHistories(livemonitorId, startTime, endTime,
							serviceName, type, alarmLevel);
			List<AlarmHistory> changedHistories = new ArrayList<AlarmHistory>(
					originalHistories);
			alarmPointStatusChangingHistories
					.addAll(CreateAlarmPointStatusChangingHistories(
							originalHistories, changedHistories));
		}

		return alarmPointStatusChangingHistories;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.vobile.opm.web.service.HistoryService#
	 * findNoOkAlarmPointStatusChangingHistories(java.sql.Timestamp,
	 * java.sql.Timestamp, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<AlarmPointStatusChangingHistory> findNoOkAlarmPointStatusChangingHistories(
			Timestamp startTime, Timestamp endTime, String serviceName,
			String type, String alarmLevel) throws ParseException {
		List<Livemonitor> livemonitors = livemonitorDAO.findAll();

		List<AlarmPointStatusChangingHistory> alarmPointStatusChangingHistories = new ArrayList<AlarmPointStatusChangingHistory>();

		for (Livemonitor livemonitor : livemonitors) {
			int livemonitorId = livemonitor.getId();
			List<AlarmHistory> originalHistories = historyDAO
					.findExceptedHistories(livemonitorId, startTime, endTime,
							serviceName, type, alarmLevel);
			List<AlarmHistory> changedHistories = new ArrayList<AlarmHistory>(
					originalHistories);
			List<AlarmPointStatusChangingHistory> tmpPointStatusChangingHistories = CreateNoOkAlarmPointStatusChangingHistories(
					originalHistories, changedHistories);

			if (tmpPointStatusChangingHistories != null) {
				alarmPointStatusChangingHistories
						.addAll(tmpPointStatusChangingHistories);
			}

		}

		return alarmPointStatusChangingHistories;
	}

	public List<AlarmPointStatusChangingHistory> CreateAlarmPointStatusChangingHistories(
			List<AlarmHistory> originalHistories,
			List<AlarmHistory> changedHistories) throws ParseException {
		List<AlarmPointStatusChangingHistory> alarmPointStatusChangingHistories = new ArrayList<AlarmPointStatusChangingHistory>();

		if (changedHistories.size() > 1) {
			changedHistories.remove(0);
			for (int i = 0; i < changedHistories.size(); i++) {
				AlarmPointStatusChangingHistory alarmPointStatusChangingHistory = new AlarmPointStatusChangingHistory(
						originalHistories.get(i), changedHistories.get(i));
				alarmPointStatusChangingHistories
						.add(alarmPointStatusChangingHistory);
			}
		}

		if (originalHistories.size() != 0) {
			AlarmPointStatusChangingHistory lastAlarmPointStatusChangingHistory = new AlarmPointStatusChangingHistory(
					originalHistories.get(originalHistories.size() - 1));

			alarmPointStatusChangingHistories
					.add(lastAlarmPointStatusChangingHistory);
		}

		return alarmPointStatusChangingHistories;
	}

	public List<AlarmPointStatusChangingHistory> CreateNoOkAlarmPointStatusChangingHistories(
			List<AlarmHistory> originalHistories,
			List<AlarmHistory> changedHistories) throws ParseException {
		List<AlarmPointStatusChangingHistory> alarmPointStatusChangingHistories = new ArrayList<AlarmPointStatusChangingHistory>();

		if (changedHistories.size() > 1) {
			changedHistories.remove(0);
			for (int i = 0; i < changedHistories.size(); i++) {
				if (originalHistories.get(i).getStatus()
						.equals(Status.OK.getStatus())
						|| originalHistories.get(i).getStatus()
								.equals(Status.DELETED.getStatus())
						|| originalHistories.get(i).getStatus()
								.equals(Status.INACTIVE.getStatus())
						|| originalHistories.get(i).getStatus()
								.equals(Status.ACTIVE_DOWN.getStatus())) {
					continue;
				}
				AlarmPointStatusChangingHistory alarmPointStatusChangingHistory = new AlarmPointStatusChangingHistory(
						originalHistories.get(i), changedHistories.get(i));
				alarmPointStatusChangingHistories
						.add(alarmPointStatusChangingHistory);
			}
		}

		if (originalHistories.size() != 0) {
			AlarmHistory lastAlarmHistory = originalHistories
					.get(originalHistories.size() - 1);

			if (!lastAlarmHistory.getStatus().equals(Status.OK.getStatus())
					&& !lastAlarmHistory.getStatus().equals(
							Status.DELETED.getStatus())
					&& !lastAlarmHistory.getStatus().equals(
							Status.INACTIVE.getStatus())
					&& !lastAlarmHistory.getStatus().equals(
							Status.ACTIVE_DOWN.getStatus())) {
				AlarmPointStatusChangingHistory lastAlarmPointStatusChangingHistory = new AlarmPointStatusChangingHistory(
						lastAlarmHistory);

				alarmPointStatusChangingHistories
						.add(lastAlarmPointStatusChangingHistory);
			}

		}

		return alarmPointStatusChangingHistories;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.vobile.opm.web.service.HistoryService#updateAccepted(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void updateAccepted(String acplid, String acceptedPerson)
			throws ParseException {

		Livemonitor livemonitor = livemonitorDAO.findById(Integer
				.parseInt(acplid));

		AlarmHistory history = historyDAO.findById(livemonitor
				.getLatestHistoryId());

		history.setIsAccepted(true);
		history.setAcceptedPerson(acceptedPerson);
		Timestamp nowTimestamp = Timestamp.valueOf(opmTimer.getNowTimeString());
		history.setAcceptedTime(nowTimestamp);

		historyDAO.update(history);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.vobile.opm.web.service.HistoryService#updateAlarmed(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void updateAlarmed(String alalid, String alarmedPerson,
			String alarmedFeedback, String alarmedNote) throws ParseException {

		Livemonitor livemonitor = livemonitorDAO.findById(Integer
				.parseInt(alalid));

		AlarmHistory history = historyDAO.findById(livemonitor
				.getLatestHistoryId());

		history.setIsAlarmed(true);
		Timestamp nowTimestamp = Timestamp.valueOf(opmTimer.getNowTimeString());
		history.setAlarmedTime(nowTimestamp);
		history.setAlarmedPerson(alarmedPerson);
		history.setAlarmedFeedback(alarmedFeedback);
		history.setAlarmedNote(alarmedNote);

		historyDAO.update(history);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.vobile.opm.web.service.HistoryService#updateResponsed(int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void updateResponsed(int reshid, String responsedPerson,
			String responsedHandling, String responsedNote)
			throws ParseException {

		Livemonitor livemonitor = livemonitorDAO.findById(reshid);

		AlarmHistory history = historyDAO.findById(livemonitor
				.getLatestHistoryId());
		history.setIsResponsed(true);
		Timestamp nowTimestamp = Timestamp.valueOf(opmTimer.getNowTimeString());
		history.setResponsedTime(nowTimestamp);
		history.setResponsedPerson(responsedPerson);
		history.setResponsedHandling(responsedHandling);
		history.setResponsedNote(responsedNote);

		historyDAO.update(history);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.vobile.opm.web.service.HistoryService#getApointDateAlarmCountMap(java
	 * .util.List)
	 */
	@Override
	public SortedMap<String, Integer> getApointDateAlarmCountMap(
			List<AlarmPointStatusChangingHistory> alarmPointStatusChangingHistories) {
		SortedMap<String, Integer> ApointDateAlarmCountMap = new TreeMap<String, Integer>();

		for (AlarmPointStatusChangingHistory alarmPointStatusChangingHistory : alarmPointStatusChangingHistories) {
			String key = opmTimer
					.convertToCSTTimeStamp(
							alarmPointStatusChangingHistory
									.getOriginalAlarmHistory().getCreatedAt()
									.toString()).toString().split(" ")[0];

			if (alarmPointStatusChangingHistory.getOriginalAlarmHistory()
					.getStatus().equals(Status.ACTIVE_UP.getStatus())) {
				if (ApointDateAlarmCountMap.get(key) == null) {
					ApointDateAlarmCountMap.put(key, 1);
				} else {
					int value = ApointDateAlarmCountMap.get(key);
					ApointDateAlarmCountMap.put(key, value + 1);
				}
			}

		}

		return ApointDateAlarmCountMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.vobile.opm.web.service.HistoryService#getExceptedHistoryDateAlarmCountMap
	 * (java.lang.String, java.lang.String, java.util.List)
	 */
	@Override
	public SortedMap<String, Integer> getExceptedHistoryDateAlarmCountMap(
			String startTime,
			String endTime,
			List<AlarmPointStatusChangingHistory> alarmPointStatusChangingHistories) {
		SortedMap<String, Integer> ApointDateAlarmCountMap = new TreeMap<String, Integer>();

		ArrayList<String> dateList = createDateList(startTime, endTime);
		for (String date : dateList) {
			ApointDateAlarmCountMap.put(date.split(" ")[0], 0);
		}
		for (AlarmPointStatusChangingHistory alarmPointStatusChangingHistory : alarmPointStatusChangingHistories) {
			String key = opmTimer
					.convertToCSTTimeStamp(
							alarmPointStatusChangingHistory
									.getOriginalAlarmHistory().getCreatedAt()
									.toString()).toString().split(" ")[0];

			if (alarmPointStatusChangingHistory.getOriginalAlarmHistory()
					.getStatus().equals(Status.ACTIVE_UP.getStatus())) {
				if (ApointDateAlarmCountMap.get(key) == null) {
					ApointDateAlarmCountMap.put(key, 1);
				} else {
					int value = ApointDateAlarmCountMap.get(key);
					ApointDateAlarmCountMap.put(key, value + 1);
				}
			}

		}

		return ApointDateAlarmCountMap;
	}

	/**
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	private ArrayList<String> createDateList(String startTime, String endTime) {
		ArrayList<String> dateList = new ArrayList<String>();
		dateList.add(startTime);
		if (!startTime.equals(endTime)) {
			Timestamp dateTimestamp = new Timestamp(Timestamp
					.valueOf(startTime).getTime() + 24 * 60 * 60 * 1000);
			while (dateTimestamp.getTime() < Timestamp.valueOf(endTime)
					.getTime()) {
				dateList.add(dateTimestamp.toString());
				dateTimestamp = new Timestamp(dateTimestamp.getTime() + 24 * 60
						* 60 * 1000);
			}
			dateList.add(endTime);
		}

		return dateList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.vobile.opm.web.service.HistoryService#getServiceNames()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<String> getServiceNames() {
		List<String> serviceNames = livemonitorDAO.findGroupByServiceName();

		return serviceNames;
	}
}
