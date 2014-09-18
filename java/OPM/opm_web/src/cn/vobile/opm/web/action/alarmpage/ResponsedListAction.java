/*
 * @(#)ResponsedAction.java    1.0 2012-7-26
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.action.alarmpage;

import cn.vobile.opm.web.service.HistoryService;
import cn.vobile.opm.web.service.UserService;
import cn.vobile.opm.web.service.impl.HistoryServiceImpl;
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
public class ResponsedListAction extends ActionSupport {
	private String reshids;

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
		responsedPerson = userService.getUserName(getResponsedPersonId());

		HistoryService historyService = new HistoryServiceImpl();
		String[] reshidStrings = getReshids().split(",");

		for (String reshid : reshidStrings) {
			if (reshid == null || reshid.equals("")) {

			} else {
				historyService.updateResponsed(Integer.parseInt(reshid),
						responsedPerson, responsedHandling, responsedNote);
			}
		}

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
	 * @return the reshids
	 */
	public String getReshids() {
		return reshids;
	}

	/**
	 * @param reshids
	 *            the reshids to set
	 */
	public void setReshids(String reshids) {
		this.reshids = reshids;
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
