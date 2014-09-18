/*
 * @(#)OpHistoryServiceImpl.java    1.0 2012-8-7
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import cn.vobile.opm.web.bean.UserOpHistory;
import cn.vobile.opm.web.common.OpmTimer;
import cn.vobile.opm.web.dao.DaoCreator;
import cn.vobile.opm.web.dao.Opmuser;
import cn.vobile.opm.web.dao.OpmuserDAO;
import cn.vobile.opm.web.dao.UserOpHistoryDAO;
import cn.vobile.opm.web.service.OpHistoryService;
import cn.vobile.opm.web.service.enumdata.Operation;

/**
 * OpHistoryServiceImpl.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-8-7
 * @since 1.0
 */
public class OpHistoryServiceImpl implements OpHistoryService {
	private UserOpHistoryDAO userOpHistoryDAO;
	private OpmuserDAO opmuserDAO;
	private OpmTimer opmTimer;

	/**
	 *
	 */
	public OpHistoryServiceImpl() {

		this.userOpHistoryDAO = (UserOpHistoryDAO) DaoCreator
				.createDao("UserOpHistoryDAO");
		this.opmuserDAO = (OpmuserDAO) DaoCreator.createDao("OpmuserDAO");
		this.opmTimer = new OpmTimer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.vobile.opm.web.service.OpHistoryService#insertChangeAlarmLevelHistory
	 * (java.lang.String, int, java.lang.String)
	 */
	@Override
	public void insertChangeAlarmLevelHistory(String opmuser_userid,
			int livemonitorid, String originalValue, String changeValue)
			throws ParseException {
		insertChangeHistory(opmuser_userid, livemonitorid, originalValue,
				changeValue, Operation.ALARM_CG);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.vobile.opm.web.service.OpHistoryService#insertChangeDelayTimeHistory
	 * (java.lang.String, int, java.lang.String, java.lang.String)
	 */
	@Override
	public void insertChangeDelayTimeHistory(String opmuser_userid,
			int livemonitorid, String originalValue, String changeValue)
			throws ParseException {
		insertChangeHistory(opmuser_userid, livemonitorid, originalValue,
				changeValue, Operation.DELAY_CG);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.vobile.opm.web.service.OpHistoryService#insertDeleteHistory(java.lang
	 * .String, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public void insertDeleteHistory(String opmuser_userid, int livemonitorid,
			String originalValue, String changeValue) throws ParseException {
		insertChangeHistory(opmuser_userid, livemonitorid, originalValue,
				changeValue, Operation.DELETE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.vobile.opm.web.service.OpHistoryService#insertChangeNoAlrmTimeHistory
	 * (java.lang.String, int, java.lang.String, java.lang.String)
	 */
	@Override
	public void insertChangeNoAlrmTimeHistory(String opmuser_userid,
			int livemonitorid, String originalValue, String changeValue)
			throws ParseException {

		insertChangeHistory(opmuser_userid, livemonitorid, originalValue,
				changeValue, Operation.NO_ALRM_ST);
	}

	/**
	 *
	 *
	 * */
	private void insertChangeHistory(String opmuser_userid, int livemonitorid,
			String originalValue, String changeValue, Operation operation)
			throws ParseException {
		int opmuserId = ((Opmuser) opmuserDAO.findByUserid(opmuser_userid).get(
				0)).getId();
		UserOpHistory userOpHistory = new UserOpHistory(opmuserId,
				livemonitorid, Timestamp.valueOf(opmTimer.getNowTimeString()),
				Timestamp.valueOf(opmTimer.getNowTimeString()));

		userOpHistory.setOperation(operation.getOperation());
		userOpHistory.setOriginalValue(originalValue);
		userOpHistory.setChangeValue(changeValue);

		userOpHistoryDAO.save(userOpHistory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.vobile.opm.web.service.OpHistoryService#findOpHistories(java.sql.Timestamp
	 * , java.sql.Timestamp, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public List<UserOpHistory> findOpHistories(Timestamp startTime,
			Timestamp endTime, String userName, String operation,
			String livemonitorId) {

		return userOpHistoryDAO.findExcptedOphistory(startTime, endTime,
				userName, operation, livemonitorId);
	}

}
