/*
 * @(#)AcceptedAction.java    1.0 2012-7-26
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
 * AcceptedAction.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-26
 * @since 1.0
 */
// TODO you can replace it of AcceptedListAction
@SuppressWarnings("serial")
public class AcceptedAction extends ActionSupport {

	private String acplid;
	private String acceptedPerson;
	private String acceptedPersonId;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		try {
			HistoryService historyService = new HistoryServiceImpl();
			UserService userService = new UserServiceImpl();

			acceptedPerson = userService.getUserName(acceptedPersonId);
			historyService.updateAccepted(acplid, acceptedPerson);

			historyService = null;
			userService = null;
		} catch (Exception e) {
		} finally {
		}

		return super.execute();
	}

	/**
	 * @return the acceptedPerson
	 */
	public String getAcceptedPerson() {
		return acceptedPerson;
	}

	/**
	 * @param acceptedPerson
	 *            the acceptedPerson to set
	 */
	public void setAcceptedPerson(String acceptedPerson) {
		this.acceptedPerson = acceptedPerson;
	}

	/**
	 * @return the acplid
	 */
	public String getAcplid() {
		return acplid;
	}

	/**
	 * @param acplid
	 *            the acplid to set
	 */
	public void setAcplid(String acplid) {
		this.acplid = acplid;
	}

	/**
	 * @return the acceptedPersonId
	 */
	public String getAcceptedPersonId() {
		return acceptedPersonId;
	}

	/**
	 * @param acceptedPersonId
	 *            the acceptedPersonId to set
	 */
	public void setAcceptedPersonId(String acceptedPersonId) {
		this.acceptedPersonId = acceptedPersonId;
	}
}
