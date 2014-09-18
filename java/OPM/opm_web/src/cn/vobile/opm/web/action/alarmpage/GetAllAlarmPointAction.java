/*
 * @(#)GetAllAlarmPointAction.java    1.0 2012-7-26
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.action.alarmpage;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import cn.vobile.opm.web.dao.Livemonitor;
import cn.vobile.opm.web.dao.Opmuser;
import cn.vobile.opm.web.service.AlarmPointService;
import cn.vobile.opm.web.service.UserService;
import cn.vobile.opm.web.service.impl.AlarmPointServiceImpl;
import cn.vobile.opm.web.service.impl.UserServiceImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * GetAllAlarmPointAction.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-26
 * @since 1.0
 */
@SuppressWarnings("serial")
public class GetAllAlarmPointAction extends ActionSupport implements
		SessionAware, ServletRequestAware, ServletResponseAware {
	private String idc;

	ActionContext context = ActionContext.getContext();
	HttpServletRequest request;
	HttpServletResponse response;
	@SuppressWarnings("rawtypes")
	SessionMap session;

	private List<Livemonitor> active_upLivemonitors;
	private List<Livemonitor> active_downLivemonitors;
	private List<Livemonitor> warningLivemonitors;
	private boolean music_on;

	private List<Opmuser> opsOpmusers;

	// private List<Livemonitor> inactiveLivemonitors;
	// private List<Livemonitor> okLivemonitors;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		try {
			idc = new String(
					request.getParameter("idc").getBytes("iso-8859-1"), "UTF-8");
			session.put("idc", idc);
		} catch (Exception e) {
		}

		// avoid the session idc out of date
		try {
			idc = (String) session.get("idc");
			session.put("idc", idc);
		} catch (Exception e) {
		}

		AlarmPointService alarmPointService = new AlarmPointServiceImpl();
		List<Livemonitor> list = alarmPointService.getUpLivemonitors();
		this.setActive_upLivemonitors(alarmPointService
				.getActiveUpLivemonitors(list));
		// this.setActive_downLivemonitors(alarmPointService
		// .getActiveDownLivemonitors());
		this.setWarningLivemonitors(alarmPointService
				.getWarningLivemonitors(list));
		UserService userService = new UserServiceImpl();
		setOpsOpmusers(userService.getOpsOpmUsers());

		alarmPointService = null;
		userService = null;
		return super.execute();
	}

	// /**
	// * @return the okLivemonitors
	// */
	// public List<Livemonitor> getOkLivemonitors() {
	// return okLivemonitors;
	// }
	//
	// /**
	// * @param okLivemonitors
	// * the okLivemonitors to set
	// */
	// public void setOkLivemonitors(List<Livemonitor> okLivemonitors) {
	// this.okLivemonitors = okLivemonitors;
	// }

	/**
	 * @return the active_upLivemonitors
	 */
	public List<Livemonitor> getActive_upLivemonitors() {
		return active_upLivemonitors;
	}

	/**
	 * @param active_upLivemonitors
	 *            the active_upLivemonitors to set
	 */
	public void setActive_upLivemonitors(List<Livemonitor> active_upLivemonitors) {
		this.active_upLivemonitors = active_upLivemonitors;
	}

	/**
	 * @return the active_downLivemonitors
	 */
	public List<Livemonitor> getActive_downLivemonitors() {
		return active_downLivemonitors;
	}

	/**
	 * @param active_downLivemonitors
	 *            the active_downLivemonitors to set
	 */
	public void setActive_downLivemonitors(
			List<Livemonitor> active_downLivemonitors) {
		this.active_downLivemonitors = active_downLivemonitors;
	}

	/**
	 * @return the warningLivemonitors
	 */
	public List<Livemonitor> getWarningLivemonitors() {
		return warningLivemonitors;
	}

	/**
	 * @param warningLivemonitors
	 *            the warningLivemonitors to set
	 */
	public void setWarningLivemonitors(List<Livemonitor> warningLivemonitors) {
		this.warningLivemonitors = warningLivemonitors;
	}

	/**
	 * @return the music_on
	 */
	public boolean isMusic_on() {
		return music_on;
	}

	/**
	 * @param music_on
	 *            the music_on to set
	 */
	public void setMusic_on(boolean music_on) {
		this.music_on = music_on;
	}

	/**
	 * @return the opsOpmusers
	 */
	public List<Opmuser> getOpsOpmusers() {
		return opsOpmusers;
	}

	/**
	 * @param opsOpmusers
	 *            the opsOpmusers to set
	 */
	public void setOpsOpmusers(List<Opmuser> opsOpmusers) {
		this.opsOpmusers = opsOpmusers;
	}

	/**
	 * @return the idc
	 */
	public String getIdc() {
		return idc;
	}

	/**
	 * @param idc
	 *            the idc to set
	 */
	public void setIdc(String idc) {
		this.idc = idc;
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
	 * @return the inactiveLivemonitors
	 */
	// public List<Livemonitor> getInactiveLivemonitors() {
	// return inactiveLivemonitors;
	// }
	//
	// /**
	// * @param inactiveLivemonitors
	// * the inactiveLivemonitors to set
	// */
	// public void setInactiveLivemonitors(List<Livemonitor>
	// inactiveLivemonitors) {
	// this.inactiveLivemonitors = inactiveLivemonitors;
	// }

}
