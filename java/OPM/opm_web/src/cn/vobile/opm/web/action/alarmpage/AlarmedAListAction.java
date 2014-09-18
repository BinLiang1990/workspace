/*
 * @(#)AlarmedAction.java    1.0 2012-7-26
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.action.alarmpage;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import cn.vobile.opm.web.common.OpmTimer;
import cn.vobile.opm.web.dao.DaoCreator;
import cn.vobile.opm.web.dao.Livemonitor;
import cn.vobile.opm.web.dao.LivemonitorDAO;
import cn.vobile.opm.web.service.HistoryService;
import cn.vobile.opm.web.service.impl.HistoryServiceImpl;
import cn.vobile.vns.sdk.exception.NotificationStyleException;
import cn.vobile.vns.sdk.model.Mail;
import cn.vobile.vns.sdk.model.Notification;
import cn.vobile.vns.sdk.service.NotificationSender;

import com.opensymphony.xwork2.ActionSupport;

/**
 * AlarmedAction.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-26
 * @since 1.0
 */
@SuppressWarnings("serial")
public class AlarmedAListAction extends ActionSupport {
	private String alalids;
	private String idc;
	private String alarmedPerson;
	private String alarmedFeedback;
	private String alarmedNote;
	private String vnsHost;
	private int vnsPort;
	private static final LivemonitorDAO livemonitorDAO = (LivemonitorDAO) DaoCreator
			.createDao("LivemonitorDAO");
	private static final OpmTimer opmTimer = new OpmTimer();
	private static final Logger log = LoggerFactory
			.getLogger(AlarmedAListAction.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		String[] alalidsStrings = alalids.split(",");
		HistoryService historyService = new HistoryServiceImpl();
		// StringBuilder content = new StringBuilder();
		for (String alalid : alalidsStrings) {
			if (alalid == null || alalid.equals("")) {

			} else {
				Livemonitor livemonitor = livemonitorDAO.findById(Integer
						.parseInt(alalid));
				livemonitor.setDuration((Timestamp.valueOf(
						opmTimer.getNowTimeString()).getTime() - livemonitor
						.getUpdatedAt().getTime()) / 1000 / 60); // min
				livemonitor.setConvertedCSTTime(opmTimer
						.convertToCSTString(livemonitor.getUpdatedAt()));
				// generateMailContent(content, livemonitor);
				historyService.updateAlarmed(alalid, alarmedPerson,
						alarmedFeedback, alarmedNote);
			}
		}
		// Opmuser opmUser = (Opmuser) opmuserDAO.findByUsername(alarmedPerson)
		// .get(0);
		// String alarmedPersonUserId = opmUser.getUserid();
		// sendMail(alarmedPersonUserId, content.toString());
		return super.execute();
	}

	public void sendMail(String recipient, String content) {
		Notification notification = new Mail();
		SimpleDateFormat dateformat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		TimeZone shanghaiTimeZone = TimeZone.getTimeZone("Asia/Shanghai");
		dateformat.setTimeZone(shanghaiTimeZone);
		String date = dateformat.format(new Date());
		notification.setSubject("Tips - DBPC One Page Monitor报警" + "_" + date);
		List<String> recipients = new ArrayList<String>();
		recipients.add(recipient + "@vobile.cn");
		recipients.add("team_ops@vobile.cn");
		recipients.add("team_gs@vobile.cn");
		notification.setRecipients(recipients);
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String opmUserId = null;
		if (principal instanceof UserDetails) {
			opmUserId = ((UserDetails) principal).getUsername();
		} else {
			opmUserId = "team_gs";
		}
		notification.setSender(opmUserId + "@vobile.cn");
		notification.setContent(content);
		NotificationSender notificationSender = new NotificationSender(vnsHost,
				vnsPort);
		try {
			notificationSender.send(notification);
		} catch (NotificationStyleException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	public void generateMailContent(StringBuilder content,
			Livemonitor livemonitor) {
		content.append("IDC: ");
		content.append(idc);
		content.append(", ");
		content.append("系统: ");
		content.append(livemonitor.getServiceName());
		content.append(", ");
		content.append("报警点: ");
		content.append(livemonitor.getComponentName());
		content.append(", ");
		content.append("Server/IP: ");
		content.append(livemonitor.getServerIpaddress());
		content.append(", ");
		content.append("种类: ");
		content.append(livemonitor.getConvertedType());
		content.append(", ");
		content.append("关键程度: ");
		content.append(livemonitor.getCriticality());
		content.append(", ");
		content.append("开始时间: ");
		content.append(livemonitor.getConvertedCSTTime());
		content.append(", ");
		content.append("持续时间: ");
		content.append(livemonitor.getDuration());
		content.append(", ");
		content.append("接警人: ");
		content.append(livemonitor.getAlarmPerson());
		content.append("\\n");
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
	 * @return the alalids
	 */
	public String getAlalids() {
		return alalids;
	}

	/**
	 * @param alalids
	 *            the alalids to set
	 */
	public void setAlalids(String alalids) {
		this.alalids = alalids;
	}

	public String getIdc() {
		return idc;
	}

	public void setIdc(String idc) {
		this.idc = idc;
	}

	public String getVnsHost() {
		return vnsHost;
	}

	public void setVnsHost(String vnsHost) {
		this.vnsHost = vnsHost;
	}

	public int getVnsPort() {
		return vnsPort;
	}

	public void setVnsPort(int vnsPort) {
		this.vnsPort = vnsPort;
	}

}
