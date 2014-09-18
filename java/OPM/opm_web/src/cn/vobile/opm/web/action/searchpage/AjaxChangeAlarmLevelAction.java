/*
 * @(#)ChangeAlarmLevelAction.java    1.0 2012-7-26
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
// TODO you can replace it of ChangeAlarmLevelListAction
public class AjaxChangeAlarmLevelAction extends ActionSupport {
	private int id_1_al;
	private String alarmLevel_al;
	private String alarmTimeRegion_al;
	private String opmuser_userid;
	private static final Logger log = Logger
			.getLogger(AjaxChangeAlarmLevelAction.class);
	private LivemonitorDAO livemonitorDAO = (LivemonitorDAO) DaoCreator
			.createDao("LivemonitorDAO");
	private AlarmSpotDAO alarmSpotDAO = (AlarmSpotDAO) DaoCreator
			.createDao("AlarmSpotDAO");

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		Livemonitor livemonitor = livemonitorDAO.findById(id_1_al);
		AlarmSpot alarmSpot = alarmSpotDAO.findById(livemonitor.getAlarmSpot()
				.getId());
		alarmSpot.setLevel(alarmLevel_al);
		alarmSpot.setAlarmTimeRegion(alarmTimeRegion_al);
		alarmSpotDAO.saveOrUpdate(alarmSpot);
		StringBuilder record = new StringBuilder();
		record.append(opmuser_userid);
		record.append(" modified alarm level: ");
		record.append(id_1_al);
		record.append(", ");
		record.append(alarmLevel_al);
		record.append("/");
		record.append(alarmTimeRegion_al);
		log.info(record);
		return super.execute();
	}

	/**
	 * @return the id_1_al
	 */
	public int getId_1_al() {
		return id_1_al;
	}

	/**
	 * @param id_1_al
	 *            the id_1_al to set
	 */
	public void setId_1_al(int id_1_al) {
		this.id_1_al = id_1_al;
	}

	/**
	 * @return the alarmLevel_al
	 */
	public String getAlarmLevel_al() {
		return alarmLevel_al;
	}

	/**
	 * @param alarmLevel_al
	 *            the alarmLevel_al to set
	 */
	public void setAlarmLevel_al(String alarmLevel_al) {
		this.alarmLevel_al = alarmLevel_al;
	}

	/**
	 * @return the alarmTimeRegion_al
	 */
	public String getAlarmTimeRegion_al() {
		return alarmTimeRegion_al;
	}

	/**
	 * @param alarmTimeRegion_al
	 *            the alarmTimeRegion_al to set
	 */
	public void setAlarmTimeRegion_al(String alarmTimeRegion_al) {
		this.alarmTimeRegion_al = alarmTimeRegion_al;
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
