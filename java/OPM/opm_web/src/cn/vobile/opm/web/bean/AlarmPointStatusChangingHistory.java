/*
 * @(#)AlarmPointStatusChangingHistory.java    1.0 2012-8-9
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.bean;

import java.sql.Timestamp;
import java.text.ParseException;

import cn.vobile.opm.web.common.OpmTimer;
import cn.vobile.opm.web.dao.AlarmHistory;

/**
 * AlarmPointStatusChangingHistory.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-8-9
 * @since 1.0
 */
public class AlarmPointStatusChangingHistory {
	private AlarmHistory originalAlarmHistory;
	private AlarmHistory changedAlarmHistory;

	private String startTime;
	private String stopTime;
	private Long duration;
	private OpmTimer opmTimer;
	private long responseTime;

	/**
	 *
	 */
	public AlarmPointStatusChangingHistory(AlarmHistory originalAlarmHistory,
			AlarmHistory changedAlarmHistory) {
		this.setOriginalAlarmHistory(originalAlarmHistory);
		this.setChangedAlarmHistory(changedAlarmHistory);
		if (originalAlarmHistory.getStatus().equals("ACTIVE_UP")
				&& originalAlarmHistory.getAlarmedTime() != null) {
			this.responseTime = (originalAlarmHistory.getAcceptedTime()
					.getTime() - originalAlarmHistory.getCreatedAt().getTime()) / 1000 / 60 - 2;
			if (this.responseTime < 0) {
				this.responseTime = 0;
			}
		}
		this.opmTimer = new OpmTimer();

		this.setStartTime(opmTimer.convertToCSTString(
				originalAlarmHistory.getCreatedAt()).split("\\.")[0]);
		this.setStopTime(opmTimer.convertToCSTString(
				changedAlarmHistory.getCreatedAt()).split("\\.")[0]);

		this.originalAlarmHistory
				.setConvertedIsResponsed(this.originalAlarmHistory
						.getIsResponsed());

		// this.convertTime(originalAlarmHistory);
		// this.convertTime(changedAlarmHistory);

		Long minutes = (changedAlarmHistory.getCreatedAt().getTime() - originalAlarmHistory
				.getCreatedAt().getTime()) / 1000 / 60;
		this.setDuration(minutes);
	}

	/**
	 * @throws ParseException
	 * 
	 */
	public AlarmPointStatusChangingHistory(AlarmHistory originalAlarmHistory)
			throws ParseException {
		this.setOriginalAlarmHistory(originalAlarmHistory);
		this.setChangedAlarmHistory(null);
		if (originalAlarmHistory.getStatus().equals("ACTIVE_UP")
				&& originalAlarmHistory.getAlarmedTime() != null) {
			this.responseTime = (originalAlarmHistory.getAcceptedTime()
					.getTime() - originalAlarmHistory.getCreatedAt().getTime()) / 1000 / 60 - 2;
			if (this.responseTime < 0) {
				this.responseTime = 0;
			}
		}
		this.opmTimer = new OpmTimer();
		this.setStartTime(opmTimer.convertToCSTString(
				originalAlarmHistory.getCreatedAt()).split("\\.")[0]);
		this.setStopTime("至今");

		this.originalAlarmHistory
				.setConvertedIsResponsed(this.originalAlarmHistory
						.getIsResponsed());

		// this.convertTime(originalAlarmHistory);

		Long minutes = (Timestamp.valueOf(opmTimer.getNowTimeString())
				.getTime() - originalAlarmHistory.getCreatedAt().getTime()) / 1000 / 60;

		this.setDuration(minutes);
	}

	/**
	 * @param originalAlarmHistory2
	 */
	public void convertTime(AlarmHistory alarmHistory) {
		OpmTimer opmTimer = new OpmTimer();
		alarmHistory.setConvertedAcceptedTime(opmTimer
				.convertToCSTString(alarmHistory.getAcceptedTime()));
		alarmHistory.setConvertedAlarmedTime(opmTimer
				.convertToCSTString(alarmHistory.getAlarmedTime()));
		alarmHistory.setConvertedResponsedTime(opmTimer
				.convertToCSTString(alarmHistory.getResponsedTime()));
	}

	/**
	 * @return the originalAlarmHistory
	 */
	public AlarmHistory getOriginalAlarmHistory() {
		return originalAlarmHistory;
	}

	/**
	 * @param originalAlarmHistory
	 *            the originalAlarmHistory to set
	 */
	public void setOriginalAlarmHistory(AlarmHistory originalAlarmHistory) {
		this.originalAlarmHistory = originalAlarmHistory;
	}

	/**
	 * @return the changedAlarmHistory
	 */
	public AlarmHistory getChangedAlarmHistory() {
		return changedAlarmHistory;
	}

	public long getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}

	/**
	 * @param changedAlarmHistory
	 *            the changedAlarmHistory to set
	 */
	public void setChangedAlarmHistory(AlarmHistory changedAlarmHistory) {
		this.changedAlarmHistory = changedAlarmHistory;
	}

	/**
	 * @return the duration
	 */
	public Long getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(Long duration) {
		this.duration = duration;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the stopTime
	 */
	public String getStopTime() {
		return stopTime;
	}

	/**
	 * @param stopTime
	 *            the stopTime to set
	 */
	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

}
