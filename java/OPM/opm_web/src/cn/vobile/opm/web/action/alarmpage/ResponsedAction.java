/*
 * @(#)ResponsedAction.java    1.0 2012-7-26
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.action.alarmpage;

import java.sql.Timestamp;

import cn.vobile.opm.web.common.OpmTimer;
import cn.vobile.opm.web.dao.AlarmHistory;
import cn.vobile.opm.web.dao.AlarmHistoryDAO;
import cn.vobile.opm.web.dao.DaoCreator;
import cn.vobile.opm.web.service.UserService;
import cn.vobile.opm.web.service.impl.UserServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

/**
 * ResponsedAction.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-26
 * @since 1.0
 */
@SuppressWarnings("serial")
// TODO you can replace it of ResponsedListAction
public class ResponsedAction extends ActionSupport {
	private int reshid;

	private int lid;

	private String responsedPersonId;
	private String responsedPerson;
	private String responsedHandling;
	private String responsedNote;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		UserService userService = new UserServiceImpl();
		responsedPerson = userService.getUserName(responsedPersonId);
		OpmTimer opmTimer = new OpmTimer();
		AlarmHistoryDAO historyDAO = (AlarmHistoryDAO) DaoCreator
				.createDao("AlarmHistoryDAO");

		AlarmHistory history = historyDAO.findById(getReshid());
		history.setIsResponsed(true);
		Timestamp nowTimestamp = Timestamp.valueOf(opmTimer.getNowTimeString());
		history.setResponsedTime(nowTimestamp);
		history.setResponsedPerson(responsedPerson);
		history.setResponsedHandling(responsedHandling);
		history.setResponsedNote(responsedNote);

		historyDAO.update(history);

		return super.execute();
	}

	/**
	 * @return the responsedPerson
	 */
	public String getResponsedPerson() {
		return responsedPerson;
	}

	/**
	 * @param responsedPerson
	 *            the responsedPerson to set
	 */
	public void setResponsedPerson(String responsedPerson) {
		this.responsedPerson = responsedPerson;
	}

	/**
	 * @return the responsedHandling
	 */
	public String getResponsedHandling() {
		return responsedHandling;
	}

	/**
	 * @param responsedHandling
	 *            the responsedHandling to set
	 */
	public void setResponsedHandling(String responsedHandling) {
		this.responsedHandling = responsedHandling;
	}

	/**
	 * @return the responsedNote
	 */
	public String getResponsedNote() {
		return responsedNote;
	}

	/**
	 * @param responsedNote
	 *            the responsedNote to set
	 */
	public void setResponsedNote(String responsedNote) {
		this.responsedNote = responsedNote;
	}

	/**
	 * @return the reshid
	 */
	public int getReshid() {
		return reshid;
	}

	/**
	 * @param reshid
	 *            the reshid to set
	 */
	public void setReshid(int reshid) {
		this.reshid = reshid;
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
	 * @return the responsedPersonId
	 */
	public String getResponsedPersonId() {
		return responsedPersonId;
	}

	/**
	 * @param responsedPersonId
	 *            the responsedPersonId to set
	 */
	public void setResponsedPersonId(String responsedPersonId) {
		this.responsedPersonId = responsedPersonId;
	}

}
