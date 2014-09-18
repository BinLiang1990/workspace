package cn.vobile.opm.web.dao;

import java.sql.Timestamp;
import java.util.Set;

import cn.vobile.opm.web.service.enumdata.AType;
import cn.vobile.opm.web.service.enumdata.IsAccpted;
import cn.vobile.opm.web.service.enumdata.IsAlarmed;
import cn.vobile.opm.web.service.enumdata.IsResponsed;
import cn.vobile.opm.web.service.enumdata.Status;
import cn.vobile.opm.web.service.enumdata.StatusPic;

/**
 * Livemonitor entity. @author MyEclipse Persistence Tools
 */

public class Livemonitor {

	private static OpmuserDAO opmuserDAO = (OpmuserDAO) DaoCreator
			.createDao("OpmuserDAO");
	private static AlarmHistoryDAO alarmHistoryDAO = (AlarmHistoryDAO) DaoCreator
			.createDao("AlarmHistoryDAO");
	private int id;
	private Integer latestHistoryId;
	private AlarmSpot alarmSpot;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String ipAddress;
	private Integer alertRuleId;
	private Set<AlarmHistory> alarmHistories;
	private long duration;
	private String convertedCSTTime;
	private String convertedType;
	private String convertedIsAccpted;
	private String convertedIsAlarmed;
	private String convertedIsResponsed;
	private Boolean isDeleted;
	private String statusPic;
	private AlarmHistory latestHistory;

	public AlarmHistory getLatestHistory() {
		return latestHistory;
	}

	public void setLatestHistory(AlarmHistory latestHistory) {
		this.latestHistory = latestHistory;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getLatestHistoryId() {
		return latestHistoryId;
	}

	public void setLatestHistoryId(Integer latestHistoryId) {
		this.latestHistoryId = latestHistoryId;
	}

	public AlarmSpot getAlarmSpot() {
		return alarmSpot;
	}

	public void setAlarmSpot(AlarmSpot alarmSpot) {
		this.alarmSpot = alarmSpot;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Integer getAlertRuleId() {
		return alertRuleId;
	}

	public void setAlertRuleId(Integer alertRuleId) {
		this.alertRuleId = alertRuleId;
	}

	public Set<AlarmHistory> getAlarmHistories() {
		return alarmHistories;
	}

	public void setAlarmHistories(Set<AlarmHistory> alarmHistories) {
		this.alarmHistories = alarmHistories;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getConvertedCSTTime() {
		return convertedCSTTime;
	}

	public void setConvertedCSTTime(String convertedCSTTime) {
		this.convertedCSTTime = convertedCSTTime;
	}

	public String getServiceName() {
		return this.alarmSpot.getName().split("\\.")[0];
	}

	public String getComponentName() {
		return this.alarmSpot.getName()
				.substring(getServiceName().length() + 1);
	}

	public String getServerIpaddress() {
		return this.getIpAddress();
	}

	public void setConvertedType(String convertedType) {
		this.convertedType = convertedType;
	}

	public String getConvertedType() {
		if (this.alarmSpot.getType().equals("module")) {
			this.convertedType = AType.MODULE.getType();
		} else if (this.alarmSpot.getType().equals("function")) {
			this.convertedType = AType.FUNCTION.getType();
		} else {
			this.convertedType = AType.DATA.getType();
		}
		return convertedType;
	}

	public String getCriticality() {
		return this.alarmSpot.getCriticality();
	}

	public String getAlarmPerson() {
		String[] owner = this.alarmSpot.getOwner().split(",");
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

	public AlarmHistory findLatestAlarmHistory() {
		AlarmHistory alarmHistory = alarmHistoryDAO.findById(latestHistoryId);
		return alarmHistory;
	}

	public String getStatus() {
		return findLatestAlarmHistory().getStatus();
	}

	public String getDescription() {
		return this.alarmSpot.getDescription();
	}

	public String getAlarmLevel() {
		return this.alarmSpot.getLevel();
	}

	public String getAlarmTimeRegion() {
		return this.alarmSpot.getAlarmTimeRegion();
	}

	public AlarmHistory getHistory() {
		return alarmHistoryDAO.findById(this.latestHistoryId);
	}

	public String getConvertedIsAccpted() {
		return convertedIsAccpted;
	}

	public void setConvertedIsAccpted(String convertedIsAccpted) {
		this.convertedIsAccpted = convertedIsAccpted;
	}

	public void setConvertedIsAccpted(Boolean isAccepted) {
		if (isAccepted) {
			this.setConvertedIsAccpted(IsAccpted.TRUE.getResult());
		} else {
			this.setConvertedIsAccpted(IsAccpted.FALSE.getResult());
		}
	}

	public String getConvertedIsAlarmed() {
		return convertedIsAlarmed;
	}

	public void setConvertedIsAlarmed(String convertedIsAlarmed) {
		this.convertedIsAlarmed = convertedIsAlarmed;
	}

	public String getConvertedIsResponsed() {
		return convertedIsResponsed;
	}

	public void setConvertedIsResponsed(String convertedIsResponsed) {
		this.convertedIsResponsed = convertedIsResponsed;
	}

	public void setConvertedIsAlarmed(Boolean isAlarmed) {
		if (isAlarmed) {
			this.setConvertedIsAlarmed(IsAlarmed.TRUE.getResult());
		} else {
			this.setConvertedIsAlarmed(IsAlarmed.FALSE.getResult());
		}
	}

	public void setConvertedIsResponsed(Boolean isResponsed) {
		if (isResponsed) {
			this.setConvertedIsResponsed(IsResponsed.TRUE.getResult());
		} else {
			this.setConvertedIsResponsed(IsResponsed.FALSE.getResult());
		}
	}

	public String getType() {
		return this.alarmSpot.getType();
	}

	public int getDelayTime() {
		return this.alarmSpot.getIntervalTime();
	}

	public String getConvertedNoAlarmStartTime() {
		Timestamp start = this.alarmSpot.getHibernateStartTime();
		Timestamp stop = this.alarmSpot.getHibernateStopTime();
		return start == stop || start.getTime() == stop.getTime() ? "" : start
				.toString().split("\\.")[0];
	}

	public String getConvertedNoAlarmEndTime() {
		Timestamp start = this.alarmSpot.getHibernateStartTime();
		Timestamp stop = this.alarmSpot.getHibernateStopTime();
		return start == stop || start.getTime() == stop.getTime() ? "" : stop
				.toString().split("\\.")[0];
	}

	public void setStatusPic(String statusPic) {
		this.statusPic = statusPic;
	}

	public String getStatusPic() {
		String status = this.getHistory().getStatus();

		if (status.equals(Status.OK.getStatus())) {
			this.setStatusPic(StatusPic.OK_PIC.getStatusPic());
		} else if (status.equals(Status.ACTIVE_DOWN.getStatus())) {
			this.setStatusPic(StatusPic.ACTIVE_DOWN_PIC.getStatusPic());
		} else if (status.equals(Status.ACTIVE_UP.getStatus())) {
			this.setStatusPic(StatusPic.ACTIVE_UP_PIC.getStatusPic());
		} else if (status.equals(Status.INACTIVE.getStatus())) {
			this.setStatusPic(StatusPic.INACTIVE_PIC.getStatusPic());
		} else if (status.equals(Status.WARNING_UP.getStatus())) {
			this.setStatusPic(StatusPic.WARNING_PIC.getStatusPic());
		} else {
		}

		return statusPic;
	}
}
