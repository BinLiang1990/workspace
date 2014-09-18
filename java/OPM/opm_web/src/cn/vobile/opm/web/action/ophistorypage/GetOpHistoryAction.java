package cn.vobile.opm.web.action.ophistorypage;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.vobile.opm.web.bean.UserOpHistory;
import cn.vobile.opm.web.common.OpmTimer;
import cn.vobile.opm.web.dao.Opmuser;
import cn.vobile.opm.web.service.OpHistoryService;
import cn.vobile.opm.web.service.UserService;
import cn.vobile.opm.web.service.impl.OpHistoryServiceImpl;
import cn.vobile.opm.web.service.impl.UserServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

/*
 * @(#)GetHistoryAction.java    1.0 2012-7-26
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */

/**
 * GetHistoryAction.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-26
 * @since 1.0
 */
@SuppressWarnings("serial")
public class GetOpHistoryAction extends ActionSupport {

	private List<UserOpHistory> ophistories;

	private String startTime;
	private String endTime;
	private String userName;
	private String operation;
	private String livemonitorId;

	private List<Opmuser> opmusers;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {

		String convertedStartTime = startTime;
		String convertedEndTime = endTime;
		if (startTime != null) {
			OpmTimer opmTimer = new OpmTimer();
			if (!startTime.equals("")) {
				convertedStartTime += " 00:00:00";
			} else {
				startTime = opmTimer.getNowTimeString().split(" ")[0];
				convertedStartTime += opmTimer.getNowTimeString().split(" ")[0]
						+ " 00:00:00";
			}
			if (!endTime.equals("")) {
				convertedEndTime += " 23:59:59";
			} else {
				endTime = opmTimer.getNowTimeString().split(" ")[0];
				convertedEndTime += opmTimer.getNowTimeString().split(" ")[0]
						+ " 23:59:59";
			}
			OpHistoryService opHistoryService = new OpHistoryServiceImpl();
			setOphistories(opHistoryService.findOpHistories(
					opmTimer.convertToUTCTimeStamp(convertedStartTime),
					opmTimer.convertToUTCTimeStamp(convertedEndTime), userName,
					operation, livemonitorId));

			for (UserOpHistory opHistory : this.ophistories) {
				opHistory.setConvertCreatedAt(opmTimer
						.convertToCSTString(opHistory.getCreatedAt()));
			}
		}
		//
		UserService userService = new UserServiceImpl();
		opmusers = userService.getAllOpmusers();

		ServletActionContext.getRequest().getSession()
				.setAttribute("userOpHistories", ophistories);

		return super.execute();
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @param operation
	 *            the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * @return the livemonitorId
	 */
	public String getLivemonitorId() {
		return livemonitorId;
	}

	/**
	 * @param livemonitorId
	 *            the livemonitorId to set
	 */
	public void setLivemonitorId(String livemonitorId) {
		this.livemonitorId = livemonitorId;
	}

	/**
	 * @return the ophistories
	 */
	public List<UserOpHistory> getOphistories() {
		return ophistories;
	}

	/**
	 * @param ophistories
	 *            the ophistories to set
	 */
	public void setOphistories(List<UserOpHistory> ophistories) {
		this.ophistories = ophistories;
	}

	/**
	 * @return the opmusers
	 */
	public List<Opmuser> getOpmusers() {
		return opmusers;
	}

	/**
	 * @param opmusers
	 *            the opmusers to set
	 */
	public void setOpmusers(List<Opmuser> opmusers) {
		this.opmusers = opmusers;
	}

}
