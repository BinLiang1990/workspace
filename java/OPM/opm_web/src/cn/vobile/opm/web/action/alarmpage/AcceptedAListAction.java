/*
 * @(#)AcceptedAListAction.java    1.0 2012-8-6
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
 * AcceptedAListAction.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-8-6
 * @since 1.0
 */
@SuppressWarnings("serial")
public class AcceptedAListAction extends ActionSupport {

	private String acplids;
	private String acceptedPerson;
	private String acceptedPersonId;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {

		String[] acplidStrings = acplids.split(",");
		HistoryService historyService = new HistoryServiceImpl();
		UserService userService = new UserServiceImpl();
		acceptedPerson = userService.getUserName(acceptedPersonId);
		for (String acplid : acplidStrings) {
			if (acplid == null || acplid.equals("")) {
			} else {
				historyService.updateAccepted(acplid, acceptedPerson);
			}
		}

		return super.execute();
	}

	/**
	 * @return the acplids
	 */
	public String getAcplids() {
		return acplids;
	}

	/**
	 * @param acplids
	 *            the acplids to set
	 */
	public void setAcplids(String acplids) {
		this.acplids = acplids;
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
