/*
 * @(#)GetAPointHistory.java    1.0 2012-7-31
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.action.historypage;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import cn.vobile.opm.web.bean.AlarmPointStatusChangingHistory;
import cn.vobile.opm.web.dao.AlarmHistory;
import cn.vobile.opm.web.service.HistoryService;
import cn.vobile.opm.web.service.impl.HistoryServiceImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * GetAPointHistory.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-31
 * @since 1.0
 */
@SuppressWarnings("serial")
/**
 * show 10 historis
 * */
public class GetAPointHistory extends ActionSupport implements SessionAware,
		ServletRequestAware, ServletResponseAware {

	private int lid;

	private List<AlarmHistory> histories;

	private List<AlarmPointStatusChangingHistory> alarmPointStatusChangingHistories;

	private Map<String, Integer> dateAlarmCountMap;

	private List<String> serviceNames;
	//
	ActionContext context = ActionContext.getContext();
	HttpServletRequest request;
	HttpServletResponse response;
	@SuppressWarnings("rawtypes")
	SessionMap session;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		HistoryService historyService = new HistoryServiceImpl();

		setHistories(historyService.getHistoriesByLid(lid));

		this.alarmPointStatusChangingHistories = historyService
				.getAlarmPointStatusChangingHistoriesByLid(lid);

		setDateAlarmCountMap(historyService
				.getApointDateAlarmCountMap(this.alarmPointStatusChangingHistories));
		Collections.reverse(this.alarmPointStatusChangingHistories);
		setServiceNames(historyService.getServiceNames());

		session.put("alarmPointStatusChangingHistories",
				alarmPointStatusChangingHistories);

		// ServletActionContext
		// .getRequest()
		// .getSession()
		// .setAttribute("alarmPointStatusChangingHistories",
		// alarmPointStatusChangingHistories);

		return super.execute();
	}

	/**
	 * @return the lid
	 */
	public int getLid() {
		return lid;
	}

	/**
	 * @param lid
	 *            the lid to set
	 */
	public void setLid(int lid) {
		this.lid = lid;
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
	 * @return the dateAlarmCountMap
	 */
	public Map<String, Integer> getDateAlarmCountMap() {
		return dateAlarmCountMap;
	}

	/**
	 * @param dateAlarmCountMap
	 *            the dateAlarmCountMap to set
	 */
	public void setDateAlarmCountMap(Map<String, Integer> dateAlarmCountMap) {
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
