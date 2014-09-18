/*
 * @(#)HistoryInserter.java    1.0 2012-7-24
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.backend.logic.layer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.vobile.opm.backend.common.InfoUtil;
import cn.vobile.opm.backend.dao.AlarmHistory;
import cn.vobile.opm.backend.dao.AlarmHistoryDAO;
import cn.vobile.opm.backend.dao.Livemonitor;
import cn.vobile.opm.backend.dao.LivemonitorDAO;
import cn.vobile.opm.backend.dao.Opmuser;
import cn.vobile.opm.backend.dao.OpmuserDAO;
import cn.vobile.opm.backend.dao.WorkRecord;
import cn.vobile.opm.backend.dao.WorkRecordDAO;
import cn.vobile.vns.sdk.exception.NotificationStyleException;
import cn.vobile.vns.sdk.model.Mail;
import cn.vobile.vns.sdk.model.Notification;
import cn.vobile.vns.sdk.service.NotificationSender;

/**
 * HistoryInserter.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-24
 * @since 1.0
 */
@Service
public class HistoryInserter {

	private static Logger logger = Logger.getLogger(HistoryInserter.class);
	@Resource
	private AlarmHistoryDAO alarmHistoryDAO;
	@Resource
	private LivemonitorDAO livemonitorDAO;
	@Resource
	private WorkRecordDAO workRecordDAO;
	@Resource
	private InfoUtil infoUtil;
	@Resource
	private OpmuserDAO opmuserDAO;

	/**
	 * insert the history nodes whitch status is changed
	 * */
	@Transactional
	public void insertHistories(List<AlarmHistory> histories) {
		for (AlarmHistory history : histories) {
			if (isPointStatusChanged(history)) {
				alarmHistoryDAO.save(history);
				Livemonitor livemonitor = history.getLivemonitor();
				livemonitor.setLatestHistoryId(history.getId());
				try {
					livemonitorDAO.update(livemonitor);
				} catch (RuntimeException re) {
					/**
					 * if update failed cause of the dirty enum data in db then
					 * catch the exp and run other
					 * */
					logger.error("update failed", re);
				}
				if (history.getStatus().equals("ACTIVE_UP")
						|| history.getStatus().equals("WANRING_UP")) {
					sendMail(livemonitor);
				}
			}
		}
	}

	public String getOwner(Livemonitor livemonitor) {
		String[] owner = livemonitor.getAlarmSpot().getOwner().split(",");
		StringBuilder alarmPerson = new StringBuilder("");
		for (String string : owner) {
			if (!string.equals("")) {
				Opmuser opmuser = opmuserDAO.findById(Integer.parseInt(string));
				if (opmuser == null) {
					break;
				}
				alarmPerson.append(opmuser.getUsername());
				alarmPerson.append(",");
				alarmPerson.append(opmuser.getPhone());
				alarmPerson.append("/");
			}
		}
		return alarmPerson.toString();
	}

	@Transactional
	public void saveOrUpdateWorkRecord(WorkRecord workRecord) {
		workRecordDAO.saveOrUpdate(workRecord);
	}

	@Transactional
	public WorkRecord getWorkRecord() {
		return workRecordDAO.getFirstWorkRecord();
	}

	/**
	 * 
	 * @return true if the history status changes
	 * */
	private boolean isPointStatusChanged(AlarmHistory history) {
		Integer historyId = history.getLivemonitor().getLatestHistoryId();
		if (historyId == null) {
			return true;
		}
		AlarmHistory historyIndb = alarmHistoryDAO.findById(historyId);
		if (!historyIndb.getStatus().equals(history.getStatus())) {
			return true;
		}
		return false;
	}

	public void sendMail(Livemonitor livemonitor) {
		Notification notification = new Mail();
		SimpleDateFormat dateformat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		TimeZone shanghaiTimeZone = TimeZone.getTimeZone("Asia/Shanghai");
		dateformat.setTimeZone(shanghaiTimeZone);
		String date = dateformat.format(new Date());
		notification.setSubject(infoUtil.getIdc() + "-OPM-"
				+ livemonitor.getAlarmSpot().getName() + "-"
				+ livemonitor.getAlarmSpot().getCriticality() + "级报警-" + date);
		notification.setContent(generateNotificationContent(livemonitor));
		notification.setSender("doNotReply@vobile.cn");
		Set<String> recipients = new HashSet<String>();
		for (String recipient : infoUtil.getMailAddress().split(",")) {
			recipients.add(recipient.trim());
		}
		String[] owner = livemonitor.getAlarmSpot().getOwner().split(",");
		for (String string : owner) {
			if (!string.equals("")) {
				Opmuser opmuser = opmuserDAO.findById(Integer.parseInt(string
						.trim()));
				if (opmuser != null) {
					recipients.add(opmuser.getUserid() + "@vobile.cn");
				}
			}
		}
		notification.setRecipients(new ArrayList<String>(recipients));
		NotificationSender sender = new NotificationSender(
				infoUtil.getVnsHost(), infoUtil.getVnsPort());
		try {
			sender.send(notification);
		} catch (NotificationStyleException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	public String generateNotificationContent(Livemonitor livemonitor) {
		StringBuilder content = new StringBuilder();
		content.append("IDC: ");
		content.append(infoUtil.getIdc());
		content.append(", ");
		content.append("系统: ");
		content.append(livemonitor.getService());
		content.append(", ");
		content.append("报警点: ");
		content.append(livemonitor.getComponent());
		content.append(", ");
		content.append("Server/IP: ");
		content.append(livemonitor.getIpAddress());
		content.append(", ");
		content.append("种类: ");
		content.append(livemonitor.getType());
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
		content.append(getOwner(livemonitor));
		return content.toString();
	}
}
