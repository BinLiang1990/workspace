/*
 * @(#)ChangeDelayTimeAction.java    1.0 2012-7-26
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.action.searchpage;

import org.apache.log4j.Logger;

import cn.vobile.opm.web.dao.AlarmSpot;
import cn.vobile.opm.web.dao.AlarmSpotDAO;
import cn.vobile.opm.web.dao.DaoCreator;
import cn.vobile.opm.web.dao.Livemonitor;
import cn.vobile.opm.web.dao.LivemonitorDAO;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author wang_lin
 * 
 */
@SuppressWarnings("serial")
public class AjaxChangeDelayTimeAction extends ActionSupport {
	private int id_1_new;
	private int delayTimeNew;
	private String opmuser_userid;
	private LivemonitorDAO livemonitorDAO = (LivemonitorDAO) DaoCreator
			.createDao("LivemonitorDAO");
	private AlarmSpotDAO alarmSpotDAO = (AlarmSpotDAO) DaoCreator
			.createDao("AlarmSpotDAO");
	private static final Logger log = Logger
			.getLogger(AjaxChangeDelayTimeAction.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		try {
			Livemonitor livemonitor = livemonitorDAO.findById(id_1_new);
			AlarmSpot alarmSpot = alarmSpotDAO.findById(livemonitor
					.getAlarmSpot().getId());
			alarmSpot.setIntervalTime(delayTimeNew);
			alarmSpotDAO.saveOrUpdate(alarmSpot);
			StringBuilder record = new StringBuilder();
			record.append(opmuser_userid);
			record.append(" modified delay time: ");
			record.append(id_1_new);
			record.append(", ");
			record.append(delayTimeNew);
			record.append("s");
			log.info(record.toString());
		} catch (Exception e) {
		}

		return "success";
	}

	/**
	 * @return the id_1_new
	 */
	public int getId_1_new() {
		return id_1_new;
	}

	/**
	 * @param id_1_new
	 *            the id_1_new to set
	 */
	public void setId_1_new(int id_1_new) {
		this.id_1_new = id_1_new;
	}

	/**
	 * @return the delayTimeNew
	 */
	public long getDelayTimeNew() {
		return delayTimeNew;
	}

	/**
	 * @param delayTimeNew
	 *            the delayTimeNew to set
	 */
	public void setDelayTimeNew(int delayTimeNew) {
		this.delayTimeNew = delayTimeNew;
	}

	/**
	 * @return the opmuser_userid
	 */
	public String getOpmuser_userid() {
		return opmuser_userid;
	}

	/**
	 * @param opmuser_userid
	 *            the opmuser_userid to set
	 */
	public void setOpmuser_userid(String opmuser_userid) {
		this.opmuser_userid = opmuser_userid;
	}

}
