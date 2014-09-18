package cn.vobile.opm.web.action.historypage;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import cn.vobile.opm.web.bean.AlarmPointStatusChangingHistory;
import cn.vobile.opm.web.common.OpmTimer;
import cn.vobile.opm.web.dao.AlarmHistory;
import cn.vobile.opm.web.service.HistoryService;
import cn.vobile.opm.web.service.impl.HistoryServiceImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/*
 * @(#)GetHistoryAction.java    1.0 2012-7-26
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */

/**
 * GetHistoryAction.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-26
 * @since 1.0
 */
@SuppressWarnings("serial")
public class GetHistoryAction extends ActionSupport implements SessionAware,
		ServletRequestAware, ServletResponseAware {

	private List<AlarmHistory> histories;

	private List<AlarmPointStatusChangingHistory> alarmPointStatusChangingHistories;

	private List<String> serviceNames;

	private String startTime;
	private String endTime;
	private String serviceName;
	private String type;
	private String alarmLevel;

	ActionContext context = ActionContext.getContext();
	HttpServletRequest request;
	HttpServletResponse response;
	@SuppressWarnings("rawtypes")
	SessionMap session;

	@SuppressWarnings("rawtypes")
	private List show_ok;

	private SortedMap<String, Integer> dateAlarmCountMap;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		HistoryService historyService = new HistoryServiceImpl();
		setServiceNames(historyService.getServiceNames());

		String convertedStartTime = startTime;
		String convertedEndTime = endTime;
		OpmTimer opmTimer = new OpmTimer();
		if (startTime != null) {
			if (!startTime.equals("") && !startTime.contains("/")) {
				convertedStartTime += " 00:00:00";
			} else {

				startTime = opmTimer.getNowTimeString().split(" ")[0];
				convertedStartTime = opmTimer.getNowTimeString().split(" ")[0]
						+ " 00:00:00";
			}
			if (!endTime.equals("") && !endTime.contains("/")) {
				convertedEndTime += " 23:59:59";
			} else {
				endTime = opmTimer.getNowTimeString().split(" ")[0];
				convertedEndTime = opmTimer.getNowTimeString().split(" ")[0]
						+ " 23:59:59";
			}

			if (show_ok != null) {
				this.alarmPointStatusChangingHistories = historyService
						.findNoOkAlarmPointStatusChangingHistories(
								opmTimer.convertToUTCTimeStamp(convertedStartTime),
								opmTimer.convertToUTCTimeStamp(convertedEndTime),
								serviceName, type, alarmLevel);
			} else {
				this.alarmPointStatusChangingHistories = historyService
						.findAlarmPointStatusChangingHistories(
								opmTimer.convertToUTCTimeStamp(convertedStartTime),
								opmTimer.convertToUTCTimeStamp(convertedEndTime),
								serviceName, type, alarmLevel);
				show_ok = null;
			}

			this.setDateAlarmCountMap(historyService
					.getExceptedHistoryDateAlarmCountMap(
							convertedStartTime.split(" ")[0] + " 00:00:00",
							convertedEndTime.split(" ")[0] + " 00:00:00",
							alarmPointStatusChangingHistories));

		}
		//
		session.put("alarmPointStatusChangingHistories",
				alarmPointStatusChangingHistories);

		// .getRequest()
		// .getSession()
		// .setAttribute("alarmPointStatusChangingHistories",
		// alarmPointStatusChangingHistories);

		return super.execute();
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
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * @param serviceName
	 *            the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

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

	/**
	 * @return the histories
	 */
	public List<AlarmHistory> getHistories() {
		return histories;
	}

	/**
	 * @param histories
	 *            the histories to set
	 */
	public void setHistories(List<AlarmHistory> histories) {
		this.histories = histories;
	}

	/**
	 * @return the alarmPointStatusChangingHistories
	 */
	public List<AlarmPointStatusChangingHistory> getAlarmPointStatusChangingHistories() {
		return alarmPointStatusChangingHistories;
	}

	/**
	 * @param alarmPointStatusChangingHistories
	 *            the alarmPointStatusChangingHistories to set
	 */
	public void setAlarmPointStatusChangingHistories(
			List<AlarmPointStatusChangingHistory> alarmPointStatusChangingHistories) {
		this.alarmPointStatusChangingHistories = alarmPointStatusChangingHistories;
	}

	/**
	 * @return the show_ok
	 */
	@SuppressWarnings("rawtypes")
	public List getShow_ok() {
		return show_ok;
	}

	/**
	 * @param show_ok
	 *            the show_ok to set
	 */
	@SuppressWarnings("rawtypes")
	public void setShow_ok(List show_ok) {
		this.show_ok = show_ok;
	}

	/**
	 * @return the dateAlarmCountMap
	 */
	public SortedMap<String, Integer> getDateAlarmCountMap() {
		return dateAlarmCountMap;
	}

	/**
	 * @param dateAlarmCountMap
	 *            the dateAlarmCountMap to set
	 */
	public void setDateAlarmCountMap(
			SortedMap<String, Integer> dateAlarmCountMap) {
		this.dateAlarmCountMap = dateAlarmCountMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.ServletResponseAware#setServletResponse
	 * (javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public void setSession(Map map) {
		this.session = (SessionMap) map;
	}

	/**
	 * @return the serviceNames
	 */
	public List<String> getServiceNames() {
		return serviceNames;
	}

	/**
	 * @param serviceNames
	 *            the serviceNames to set
	 */
	public void setServiceNames(List<String> serviceNames) {
		this.serviceNames = serviceNames;
	}

}
