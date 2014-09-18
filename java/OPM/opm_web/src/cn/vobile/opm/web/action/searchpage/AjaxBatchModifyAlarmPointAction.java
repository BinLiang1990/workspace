/*
 * @(#)UpdateAlarmPointAction.java    1.0 2012-7-30
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.action.searchpage;

import java.sql.Timestamp;
import java.util.HashSet;

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
 * 
 * @author wang_lin
 * 
 */
@SuppressWarnings("serial")
public class AjaxBatchModifyAlarmPointAction extends ActionSupport {

	private String id;
	private String opmuser_userid;
	private String description;
	private String type;
	private String criticality;
	private String firstAlarmPerson;
	private String secondAlarmPerson;
	private String alarmPerson;
	private int delayTime;
	private String alarmLevel;
	private String alarmTimeRegion;
	private String noAlarmEndTime;
	private String noAlarmStartTime;
	private LivemonitorDAO livemonitorDAO = (LivemonitorDAO) DaoCreator
			.createDao("LivemonitorDAO");
	private AlarmSpotDAO alarmSpotDAO = (AlarmSpotDAO) DaoCreator
			.createDao("AlarmSpotDAO");
	private static final Logger log = Logger
			.getLogger(AjaxBatchModifyAlarmPointAction.class);
	private static final OpmuserDAO opmuserDAO = (OpmuserDAO) DaoCreator
			.createDao("OpmuserDAO");

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		String idString[] = id.split(",");
		HashSet<Integer> set = new HashSet<Integer>();
		for (String string : idString) {
			Livemonitor livemonitor = livemonitorDAO.findById(Integer
					.parseInt(string));
			set.add(livemonitor.getAlarmSpot().getId());
		}
		for (int id : set) {
			AlarmSpot alarmSpot = alarmSpotDAO.findById(id);
			if (description.length() > 0) {
				alarmSpot.setDescription(description);
			}
			if (type.length() > 0) {
				alarmSpot.setType(type);
			}
			if (criticality.length() > 0) {
				alarmSpot.setCriticality(criticality);
			}
			if (firstAlarmPerson.length() > 0) {
				String first = null;
				String second = null;
				Opmuser firstOpmuser = (Opmuser) opmuserDAO.findByUsername(
						firstAlarmPerson.split("\\,")[0]).get(0);
				first = firstOpmuser.getId().toString();
				if (secondAlarmPerson.length() == 0) {
					second = first;
				} else {
					Opmuser secondOpmuser = (Opmuser) opmuserDAO
							.findByUsername(secondAlarmPerson.split("\\,")[0])
							.get(0);
					second = secondOpmuser.getId().toString();
				}
				alarmSpot.setOwner(first + "," + second);
			}
			if (0 != delayTime) {
				alarmSpot.setIntervalTime(delayTime);
			}
			if (alarmLevel.length() > 0) {
				alarmSpot.setLevel(alarmLevel);
			}
			if (alarmTimeRegion.length() > 0) {
				alarmSpot.setAlarmTimeRegion(alarmTimeRegion);
			}
			if (noAlarmStartTime.length() > 0 && noAlarmEndTime.length() > 0) {
				Timestamp start = Timestamp.valueOf(noAlarmStartTime);
				Timestamp stop = Timestamp.valueOf(noAlarmEndTime);
				if ((stop.getTime() - start.getTime()) / 1000 < 60) {
					start = stop = null;
				}
				alarmSpot.setHibernateStartTime(start);
				alarmSpot.setHibernateStopTime(stop);
			}
			// Timestamp start = Timestamp.valueOf(noAlarmStartTime);
			// Timestamp stop = Timestamp.valueOf(noAlarmEndTime);
			// if ((stop.getTime() - start.getTime()) / 1000 < 60) {
			// start = stop = null;
			// }
			// if (noAlarmStartTime.length() > 0) {
			// alarmSpot.setHibernateStartTime(start);
			// }
			// if (noAlarmEndTime.length() > 0) {
			// alarmSpot.setHibernateStopTime(stop);
			// }
			alarmSpotDAO.saveOrUpdate(alarmSpot);
		}

		StringBuilder record = new StringBuilder();
		record.append(opmuser_userid);
		record.append(" modified alarm points: ");
		record.append(id);
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
		record.append(noAlarmStartTime);
		record.append(", ");
		record.append(noAlarmEndTime);
		log.info(record.toString());
		return super.execute();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(int delayTime) {
		this.delayTime = delayTime;
	}

	public String getAlarmLevel() {
		return alarmLevel;
	}

	public String getOpmuser_userid() {
		return opmuser_userid;
	}

	public void setOpmuser_userid(String opmuser_userid) {
		this.opmuser_userid = opmuser_userid;
	}

	public void setAlarmLevel(String alarmLevel) {
		this.alarmLevel = alarmLevel;
	}

	public String getAlarmTimeRegion() {
		return alarmTimeRegion;
	}

	public void setAlarmTimeRegion(String alarmTimeRegion) {
		this.alarmTimeRegion = alarmTimeRegion;
	}

	public String getNoAlarmEndTime() {
		return noAlarmEndTime;
	}

	public void setNoAlarmEndTime(String noAlarmEndTime) {
		this.noAlarmEndTime = noAlarmEndTime;
	}

	public String getNoAlarmStartTime() {
		return noAlarmStartTime;
	}

	public void setNoAlarmStartTime(String noAlarmStartTime) {
		this.noAlarmStartTime = noAlarmStartTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCriticality() {
		return criticality;
	}

	public void setCriticality(String criticality) {
		this.criticality = criticality;
	}

	public String getAlarmPerson() {
		return alarmPerson;
	}

	public void setAlarmPerson(String alarmPerson) {
		this.alarmPerson = alarmPerson;
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

}
