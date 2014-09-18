/*
 * @(#)UpdateAlarmPointAction.java    1.0 2012-7-30
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.action.searchpage;

import java.sql.Timestamp;

import org.apache.log4j.Logger;

import cn.vobile.opm.web.dao.AlarmSpot;
import cn.vobile.opm.web.dao.AlarmSpotDAO;
import cn.vobile.opm.web.dao.DaoCreator;
import cn.vobile.opm.web.dao.Livemonitor;
import cn.vobile.opm.web.dao.LivemonitorDAO;
import cn.vobile.opm.web.dao.Opmuser;
import cn.vobile.opm.web.dao.OpmuserDAO;

import com.opensymphony.xwork2.ActionSupport;

/**
 * UpdateAlarmPointAction.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-30
 * @since 1.0
 */
@SuppressWarnings("serial")
public class AjaxUpdateAlarmPointAction extends ActionSupport {

	private String id_1;

	private int delayTime;
	private String alarmLevel;
	private String type;
	private String criticality;
	private String description;
	private String alarmTimeRegion;
	private Timestamp noAlarmEndTime;
	private String noAlarmStartTimeComponent;
	private String noAlarmEndTimeComponent;
	private String firstAlarmPerson;
	private String secondAlarmPerson;
	private String alarmPerson;
	private String opmuser_userid;
	private LivemonitorDAO livemonitorDAO = (LivemonitorDAO) DaoCreator
			.createDao("LivemonitorDAO");
	private OpmuserDAO opmuserDAO = (OpmuserDAO) DaoCreator
			.createDao("OpmuserDAO");
	private AlarmSpotDAO alarmSpotDAO = (AlarmSpotDAO) DaoCreator
			.createDao("AlarmSpotDAO");
	private static final Logger log = Logger
			.getLogger(AjaxUpdateAlarmPointAction.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {

		Livemonitor livemonitor = livemonitorDAO.findById(Integer
				.parseInt(id_1));
		AlarmSpot alarmSpot = alarmSpotDAO.findById(livemonitor.getAlarmSpot()
				.getId());
		alarmSpot.setIntervalTime(delayTime);
		alarmSpot.setLevel(alarmLevel);
		alarmSpot.setAlarmTimeRegion(alarmTimeRegion);
		alarmSpot.setType(type);
		alarmSpot.setCriticality(criticality);
		alarmSpot.setDescription(description);
		if (firstAlarmPerson.length() > 0) {
			Opmuser firstOpmuser = (Opmuser) opmuserDAO.findByUsername(
					firstAlarmPerson.split(",")[0]).get(0);
			Opmuser secondOpmuser = secondAlarmPerson.length() > 0 ? (Opmuser) opmuserDAO
					.findByUsername(secondAlarmPerson.split(",")[0]).get(0)
					: null;
			int firstAlarmPersonId = firstOpmuser.getId();
			int secondAlarmPersonId = secondOpmuser == null ? firstAlarmPersonId
					: secondOpmuser.getId();
			alarmSpot.setOwner(firstAlarmPersonId + "," + secondAlarmPersonId);
		}
		Timestamp hibernateStartTime = null;
		Timestamp hibernateStopTime = null;
		if (!"".equals(noAlarmStartTimeComponent)) {
			hibernateStartTime = Timestamp.valueOf(noAlarmStartTimeComponent);
		}
		if (!"".equals(noAlarmEndTimeComponent)) {
			hibernateStopTime = Timestamp.valueOf(noAlarmEndTimeComponent);
		}
		alarmSpot.setHibernateStartTime(hibernateStartTime);
		alarmSpot.setHibernateStopTime(hibernateStopTime);
		alarmSpot.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		alarmSpotDAO.saveOrUpdate(alarmSpot);
		StringBuilder record = new StringBuilder();
		record.append(opmuser_userid);
		record.append(" modified alarm point: ");
		record.append(id_1);
		record.append(", ");
		record.append(type);
		record.append(", ");
		record.append(criticality);
		record.append(", ");
		record.append(alarmPerson);
		record.append(", ");
		record.append(alarmLevel);
		record.append(", ");
		record.append(alarmTimeRegion);
		record.append(", ");
		record.append(delayTime);
		record.append(", ");
		record.append(noAlarmStartTimeComponent);
		record.append(", ");
		record.append(noAlarmEndTimeComponent);
		log.info(record.toString());
		return super.execute();
	}

	/**
	 * @return the id_1
	 */
	public String getId_1() {
		return id_1;
	}

	/**
	 * @param id_1
	 *            the id_1 to set
	 */
	public void setId_1(String id_1) {
		this.id_1 = id_1;
	}

	/**
	 * @return the delayTime
	 */
	public long getDelayTime() {
		return delayTime;
	}

	/**
	 * @param delayTime
	 *            the delayTime to set
	 */
	public void setDelayTime(Integer delayTime) {
		this.delayTime = delayTime;
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
	 * @return the criticality
	 */
	public String getCriticality() {
		return criticality;
	}

	/**
	 * @param criticality
	 *            the criticality to set
	 */
	public void setCriticality(String criticality) {
		this.criticality = criticality;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the alarmTimeRegion
	 */
	public String getAlarmTimeRegion() {
		return alarmTimeRegion;
	}

	/**
	 * @param alarmTimeRegion
	 *            the alarmTimeRegion to set
	 */
	public void setAlarmTimeRegion(String alarmTimeRegion) {
		this.alarmTimeRegion = alarmTimeRegion;
	}

	/**
	 * @return the noAlarmEndTime
	 */
	public Timestamp getNoAlarmEndTime() {
		return noAlarmEndTime;
	}

	/**
	 * @param noAlarmEndTime
	 *            the noAlarmEndTime to set
	 */
	public void setNoAlarmEndTime(Timestamp noAlarmEndTime) {
		this.noAlarmEndTime = noAlarmEndTime;
	}

	public String getFirstAlarmPerson() {
		return firstAlarmPerson;
	}

	public void setFirstAlarmPerson(String firstAlarmPerson) {
		this.firstAlarmPerson = firstAlarmPerson;
	}

	public String getSecondAlarmPerson() {
		return secondAlarmPerson;
	}

	public void setSecondAlarmPerson(String secondAlarmPerson) {
		this.secondAlarmPerson = secondAlarmPerson;
	}

	public String getNoAlarmStartTimeComponent() {
		return noAlarmStartTimeComponent;
	}

	public void setNoAlarmStartTimeComponent(String noAlarmStartTimeComponent) {
		this.noAlarmStartTimeComponent = noAlarmStartTimeComponent;
	}

	public String getNoAlarmEndTimeComponent() {
		return noAlarmEndTimeComponent;
	}

	public void setNoAlarmEndTimeComponent(String noAlarmEndTimeComponent) {
		this.noAlarmEndTimeComponent = noAlarmEndTimeComponent;
	}

	public void setDelayTime(int delayTime) {
		this.delayTime = delayTime;
	}

	public String getOpmuser_userid() {
		return opmuser_userid;
	}

	public void setOpmuser_userid(String opmuser_userid) {
		this.opmuser_userid = opmuser_userid;
	}

	public String getAlarmPerson() {
		return alarmPerson;
	}

	public void setAlarmPerson(String alarmPerson) {
		this.alarmPerson = alarmPerson;
	}

}
