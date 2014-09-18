package cn.vobile.opm.web.service.impl;

import cn.vobile.opm.web.dao.AlarmSpot;
import cn.vobile.opm.web.dao.AlarmSpotDAO;
import cn.vobile.opm.web.dao.DaoCreator;
import cn.vobile.opm.web.dao.Opmuser;
import cn.vobile.opm.web.dao.OpmuserDAO;

public class AlarmSpotServiceImpl {

	private AlarmSpotDAO alarmSpotDAO = (AlarmSpotDAO) DaoCreator
			.createDao("AlarmSpotDAO");
	private OpmuserDAO opmuserDAO = (OpmuserDAO) DaoCreator
			.createDao("OpmuserDAO");

	public boolean isLegal(String name) {
		String[] names = name.split("/");
		for (String spot : names) {
			spot = spot.trim();
			if (spot.split("\\.").length < 2) {
				return false;
			}
			if (alarmSpotDAO.findByName(spot).size() > 0) {
				return false;
			}
		}
		return true;
	}

	public void save(AlarmSpot alarmSpot) {
		alarmSpotDAO.saveOrUpdate(alarmSpot);
	}

	public String getAlarmPersonId(String alarmPerson) {
		String username = alarmPerson.split(",")[0];
		Opmuser opmuser = (Opmuser) opmuserDAO.findByUsername(username).get(0);
		return opmuser.getId().toString();
	}
}
