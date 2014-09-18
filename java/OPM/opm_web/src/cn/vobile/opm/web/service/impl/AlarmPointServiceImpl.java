/*
 * @(#)AlarmPointServiceImpl.java    1.0 2012-7-25
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import cn.vobile.opm.web.common.OpmTimer;
import cn.vobile.opm.web.dao.AlarmHistoryDAO;
import cn.vobile.opm.web.dao.DaoCreator;
import cn.vobile.opm.web.dao.Livemonitor;
import cn.vobile.opm.web.dao.LivemonitorDAO;
import cn.vobile.opm.web.service.AlarmPointService;
import cn.vobile.opm.web.service.enumdata.Status;

/**
 * AlarmPointServiceImpl.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-25
 * @since 1.0
 */
public class AlarmPointServiceImpl implements AlarmPointService {

	private LivemonitorDAO livemonitorDAO;
	private AlarmHistoryDAO historyDAO;
	protected static final Logger log = Logger.getLogger(LivemonitorDAO.class);

	/**
	 *
	 */
	public AlarmPointServiceImpl() {
		this.livemonitorDAO = (LivemonitorDAO) DaoCreator
				.createDao("LivemonitorDAO");
		this.setHistoryDAO((AlarmHistoryDAO) DaoCreator
				.createDao("AlarmHistoryDAO"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.vobile.opm.web.service.AlarmPointService#updateDelayTime(int)
	 */
	@Override
	public boolean updateAlarmPoint(Livemonitor livemonitor) {
		// try {
		// Livemonitor livemonitorInDb = (Livemonitor) livemonitorDAO
		// .findById(livemonitor.getId());
		//
		// livemonitorInDb.setAlarmLevel(livemonitor.getAlarmLevel());
		// livemonitorInDb.setAlarmLevel(livemonitor.getAlarmLevel());
		// livemonitorInDb
		// .setAlarmTimeRegion(livemonitor.getAlarmTimeRegion());
		// livemonitorInDb.setCriticality(livemonitor.getCriticality());
		// livemonitorInDb.setDelayTime(livemonitor.getDelayTime());
		// livemonitorInDb.setDescription(livemonitor.getDescription());
		// livemonitorInDb.setNoAlarmEndTime(livemonitor.getNoAlarmEndTime());
		// livemonitorInDb.setType(livemonitor.getType());
		//
		// livemonitorDAO.update(livemonitorInDb);
		//
		// } catch (Exception e) {
		// return false;
		// }
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.vobile.opm.web.service.AlarmPointService#getLivemonitors()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Livemonitor> getLivemonitors() {
		return livemonitorDAO.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.vobile.opm.web.service.AlarmPointService#getOkLivemonitors()
	 */
	@Override
	public List<Livemonitor> getOkLivemonitors() throws ParseException {
		return this.getLivemonitors(Status.OK);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.vobile.opm.web.service.AlarmPointService#getInactiveLivemonitors()
	 */
	@Override
	public List<Livemonitor> getInactiveLivemonitors() throws ParseException {
		return this.getLivemonitors(Status.INACTIVE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.vobile.opm.web.service.AlarmPointService#getWarningLivemonitors()
	 */
	@Override
	public List<Livemonitor> getWarningLivemonitors(List<Livemonitor> list)
			throws ParseException {
		// return this.getLivemonitors(Status.WARNING_UP);
		List<Livemonitor> warningUp = new ArrayList<Livemonitor>();
		Iterator<Livemonitor> iterator = list.iterator();
		while (iterator.hasNext()) {
			Livemonitor livemonitor = iterator.next();
			if (!livemonitor.getLatestHistory().getStatus()
					.equals("WARNING_UP")) {
				continue;
			}
			warningUp.add(livemonitor);
			iterator.remove();
		}
		return warningUp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.vobile.opm.web.service.AlarmPointService#getActiveUpLivemonitors()
	 */
	@Override
	public List<Livemonitor> getActiveUpLivemonitors(List<Livemonitor> list)
			throws ParseException {
		List<Livemonitor> activeUp = new ArrayList<Livemonitor>();
		Iterator<Livemonitor> iterator = list.iterator();
		while (iterator.hasNext()) {
			Livemonitor livemonitor = iterator.next();
			if (!livemonitor.getLatestHistory().getStatus().equals("ACTIVE_UP")) {
				continue;
			}
			activeUp.add(livemonitor);
			iterator.remove();
		}
		return activeUp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.vobile.opm.web.service.AlarmPointService#getActiveDownLivemonitors()
	 */
	@Override
	public List<Livemonitor> getActiveDownLivemonitors() throws ParseException {
		return this.getLivemonitors(Status.ACTIVE_DOWN);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.vobile.opm.web.service.AlarmPointService#getDeletedLivemonitors()
	 */
	@Override
	public List<Livemonitor> getDeletedLivemonitors() throws ParseException {
		return this.getLivemonitors(Status.DELETED);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.vobile.opm.web.service.AlarmPointService#getLivemonitors(java.lang
	 * .String)
	 */
	private List<Livemonitor> getLivemonitors(Status status)
			throws ParseException {
		List<Livemonitor> allLivemonitors = livemonitorDAO.findAllInHistory();

		List<Livemonitor> okLivemonitors = new ArrayList<Livemonitor>();
		List<Livemonitor> active_upLivemonitors = new ArrayList<Livemonitor>();
		List<Livemonitor> active_downLivemonitors = new ArrayList<Livemonitor>();
		List<Livemonitor> warning_upLivemonitors = new ArrayList<Livemonitor>();
		List<Livemonitor> warning_downLivemonitors = new ArrayList<Livemonitor>();
		List<Livemonitor> inactiveLivemonitors = new ArrayList<Livemonitor>();
		List<Livemonitor> deletedLivemonitors = new ArrayList<Livemonitor>();

		for (Livemonitor livemonitor : allLivemonitors) {

			livemonitor.setConvertedIsAccpted(livemonitor.getHistory()
					.getIsAccepted());
			livemonitor.setConvertedIsAlarmed(livemonitor.getHistory()
					.getIsAlarmed());
			livemonitor.setConvertedIsResponsed(livemonitor.getHistory()
					.getIsResponsed());
			OpmTimer opmTimer = new OpmTimer();

			livemonitor.setDuration((System.currentTimeMillis() - livemonitor
					.getUpdatedAt().getTime()) / 1000 / 60); // min
			livemonitor.setConvertedCSTTime(opmTimer
					.convertToCSTString(livemonitor.getUpdatedAt()));

			String statusInDb = livemonitor.getHistory().getStatus();

			if (statusInDb.equals(Status.OK.getStatus())) {
				okLivemonitors.add(livemonitor);
			} else if (statusInDb.equals(Status.INACTIVE.getStatus())) {
				inactiveLivemonitors.add(livemonitor);
			} else if (statusInDb.equals(Status.WARNING_UP.getStatus())) {
				warning_upLivemonitors.add(livemonitor);
			} else if (statusInDb.equals(Status.WARNING_DOWN.getStatus())) {
				warning_downLivemonitors.add(livemonitor);
			} else if (statusInDb.equals(Status.ACTIVE_DOWN.getStatus())) {
				active_downLivemonitors.add(livemonitor);
			} else if (statusInDb.equals(Status.ACTIVE_UP.getStatus())) {
				active_upLivemonitors.add(livemonitor);
			} else if (statusInDb.equals(Status.DELETED.getStatus())) {
				deletedLivemonitors.add(livemonitor);
			} else {

			}

		}
		switch (status) {
		case OK:
			return okLivemonitors;
		case INACTIVE:
			return inactiveLivemonitors;
		case WARNING_UP:
			return warning_upLivemonitors;
		case WARNING_DOWN:
			return warning_downLivemonitors;
		case ACTIVE_DOWN:
			return active_downLivemonitors;
		case ACTIVE_UP:
			return active_upLivemonitors;
		case DELETED:
			return deletedLivemonitors;
		default:
			break;
		}
		return null;
	}

	/**
	 * @return the historyDAO
	 */
	public AlarmHistoryDAO getHistoryDAO() {
		return historyDAO;
	}

	/**
	 * @param historyDAO
	 *            the historyDAO to set
	 */
	public void setHistoryDAO(AlarmHistoryDAO historyDAO) {
		this.historyDAO = historyDAO;
	}

	@Override
	public List<Livemonitor> getUpLivemonitors() {
		List<Livemonitor> list = livemonitorDAO.findAllInHistory();
		Iterator<Livemonitor> iterator = list.iterator();
		while (iterator.hasNext()) {
			Livemonitor livemonitor = iterator.next();
			livemonitor.setLatestHistory(livemonitor.findLatestAlarmHistory());
			String status = livemonitor.getLatestHistory().getStatus();
			if (!(status.equals("ACTIVE_UP") || status.equals("WARNING_UP"))) {
				iterator.remove();
				continue;
			}
			livemonitor.setConvertedIsAccpted(livemonitor.getLatestHistory()
					.getIsAccepted());
			livemonitor.setConvertedIsAlarmed(livemonitor.getLatestHistory()
					.getIsAlarmed());
			livemonitor.setConvertedIsResponsed(livemonitor.getLatestHistory()
					.getIsResponsed());
			OpmTimer opmTimer = new OpmTimer();
			livemonitor.setDuration((System.currentTimeMillis() - livemonitor
					.getLatestHistory().getCreatedAt().getTime()) / 1000 / 60);
			livemonitor.setConvertedCSTTime(opmTimer
					.convertToCSTString(livemonitor.getLatestHistory()
							.getCreatedAt()));
		}
		return list;
	}
}
