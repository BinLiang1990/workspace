/*
 * @(#)ChangeNoAlarmEndTimeAction.java    1.0 2012-8-2
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

import com.opensymphony.xwork2.ActionSupport;

/**
 * ChangeNoAlarmEndTimeAction.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-8-2
 * @since 1.0
 */
@SuppressWarnings("serial")
// TODO you can replace it of ChangeNoAlarmEndTimeListAction
public class AjaxChangeNoAlarmEndTimeAction extends ActionSupport {

	private int nae_id;

	private String noAlarmEndTime;
	private String noAlarmStartTime;
	private String opmuser_userid;
	private LivemonitorDAO livemonitorDAO = (LivemonitorDAO) DaoCreator
			.createDao("LivemonitorDAO");
	private AlarmSpotDAO alarmSpotDAO = (AlarmSpotDAO) DaoCreator
			.createDao("AlarmSpotDAO");

	private static final Logger log = Logger
			.getLogger(AjaxChangeNoAlarmEndTimeAction.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		Livemonitor livemonitor = livemonitorDAO.findById(nae_id);
		AlarmSpot alarmSpot = alarmSpotDAO.findById(livemonitor.getAlarmSpot()
				.getId());
		Timestamp start = noAlarmStartTime.length() == 0 ? null : Timestamp
				.valueOf(noAlarmStartTime);
		Timestamp stop = noAlarmEndTime.length() == 0 ? null : Timestamp
				.valueOf(noAlarmEndTime);
		alarmSpot.setHibernateStartTime(start);
		alarmSpot.setHibernateStopTime(stop);
		alarmSpotDAO.saveOrUpdate(alarmSpot);
		StringBuilder record = new StringBuilder();
		record.append(opmuser_userid);
		record.append(" changed no alarm time: ");
		record.append(nae_id);
		record.append(", ");
		record.append(noAlarmStartTime);
		record.append(", ");
		record.append(noAlarmEndTime);
		log.info(record.toString());
		return super.execute();
	}

	/**
	 * @return the nae_id
	 */
	public int getNae_id() {
		return nae_id;
	}

	/**
	 * @param nae_id
	 *            the nae_id to set
	 */
	public void setNae_id(int nae_id) {
		this.nae_id = nae_id;
	}

	/**
	 * @return the noAlarmEndTime
	 */
	public String getNoAlarmEndTime() {
		return noAlarmEndTime;
	}

	/**
	 * @param noAlarmEndTime
	 *            the noAlarmEndTime to set
	 */
	public void setNoAlarmEndTime(String noAlarmEndTime) {
		this.noAlarmEndTime = noAlarmEndTime;
	}

	/**
	 * @return the noAlarmStartTime
	 */
	public String getNoAlarmStartTime() {
		return noAlarmStartTime;
	}

	/**
	 * @param noAlarmStartTime
	 *            the noAlarmStartTime to set
	 */
	public void setNoAlarmStartTime(String noAlarmStartTime) {
		this.noAlarmStartTime = noAlarmStartTime;
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
