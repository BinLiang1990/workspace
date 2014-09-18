/*
 * @(#)AlarmedAction.java    1.0 2012-7-26
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.action.alarmpage;

import cn.vobile.opm.web.service.HistoryService;
import cn.vobile.opm.web.service.impl.HistoryServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

/**
 * AlarmedAction.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-26
 * @since 1.0
 */
@SuppressWarnings("serial")
// TODO you can replace it of AlarmedListAction
public class AlarmedAction extends ActionSupport {
	private String alalid;

	private String alarmedPerson;
	private String alarmedFeedback;
	private String alarmedNote;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {

		HistoryService historyService = new HistoryServiceImpl();
		historyService.updateAlarmed(alalid, alarmedPerson, alarmedFeedback,
				alarmedNote);

		return super.execute();
	}

	/**
	 * @return the alarmedPerson
	 */
	public String getAlarmedPerson() {
		return alarmedPerson;
	}

	/**
	 * @param alarmedPerson
	 *            the alarmedPerson to set
	 */
	public void setAlarmedPerson(String alarmedPerson) {
		this.alarmedPerson = alarmedPerson;
	}

	/**
	 * @return the alarmedFeedback
	 */
	public String getAlarmedFeedback() {
		return alarmedFeedback;
	}

	/**
	 * @param alarmedFeedback
	 *            the alarmedFeedback to set
	 */
	public void setAlarmedFeedback(String alarmedFeedback) {
		this.alarmedFeedback = alarmedFeedback;
	}

	/**
	 * @return the alarmedNote
	 */
	public String getAlarmedNote() {
		return alarmedNote;
	}

	/**
	 * @param alarmedNote
	 *            the alarmedNote to set
	 */
	public void setAlarmedNote(String alarmedNote) {
		this.alarmedNote = alarmedNote;
	}

	/**
	 * @return the alalid
	 */
	public String getAlalid() {
		return alalid;
	}

	/**
	 * @param alalid
	 *            the alalid to set
	 */
	public void setAlalid(String alalid) {
		this.alalid = alalid;
	}

}
