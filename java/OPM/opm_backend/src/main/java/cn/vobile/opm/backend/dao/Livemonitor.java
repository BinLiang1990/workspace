package cn.vobile.opm.backend.dao;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Set;

import javax.annotation.Resource;

import cn.vobile.opm.backend.common.OpmTimer;

/**
 * Livemonitor entity. @author MyEclipse Persistence Tools
 */
public class Livemonitor {

	private int id;
	private AlarmSpot alarmSpot;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String ipAddress;
	private Integer alertRuleId;
	private Integer latestHistoryId;
	private Boolean isDeleted;
	private Set<AlarmHistory> alarmHistories;
	@Resource
	private OpmuserDAO opmuserDAO;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AlarmSpot getAlarmSpot() {
		return alarmSpot;
	}

	public void setAlarmSpot(AlarmSpot alarmSpot) {
		this.alarmSpot = alarmSpot;
	}

	public String getService() {
		return alarmSpot.getName().split("\\.")[0];
	}

	public String getComponent() {
		return alarmSpot.getName().substring(
				alarmSpot.getName().split("\\.")[0].length() + 1);
	}

	public long getDuration() {
		return (System.currentTimeMillis() - updatedAt.getTime()) / 1000 / 60
				- this.getAlarmSpot().getIntervalTime() / 60;
	}

	public String getType() {
		return alarmSpot.getType();
	}

	public String getCriticality() {
		return alarmSpot.getCriticality();
	}

	public String getConvertedCSTTime() {
		String convertedCSTTime = null;
		try {
			OpmTimer opmTimer = new OpmTimer();
			convertedCSTTime = opmTimer.convertToCSTString(updatedAt).split(
					"\\.")[0];
		} catch (Exception e) {
		}
		return convertedCSTTime;
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

	public Set<AlarmHistory> getAlarmHistories() {
		return alarmHistories;
	}

	public void setAlarmHistories(Set<AlarmHistory> alarmHistories) {
		this.alarmHistories = alarmHistories;
	}

	/**
	 * @return ture if is deleted
	 * */
	public boolean isDeleted() {
		return this.getIsDeleted();
	}

	/**
	 * check inactive
	 * 
	 * @return ture if is inactive
	 * */
	public boolean isInactive() {
		return this.getAlarmSpot().getLevel().equals("inactive");
	}

	/**
	 * check active
	 * 
	 * @return if is active
	 * */
	public boolean isActive() {
		return this.getAlarmSpot().getLevel().equals("active");
	}

	/**
	 * check warning
	 * 
	 * @return if is waring
	 * */
	public boolean isWarning() {
		return this.getAlarmSpot().getLevel().equals("warning");
	}

	/**
	 * @return
	 * @throws ParseException
	 * */
	public boolean isAlarmPointOK() throws ParseException {
		return OpmTimer.compareTheGiveTime(this.getAlarmSpot()
				.getIntervalTime(), this.getUpdatedAt());
	}

	public Integer getAlertRuleId() {
		return alertRuleId;
	}

	public void setAlertRuleId(Integer alertRuleId) {
		this.alertRuleId = alertRuleId;
	}

	public String getAlarmTimeRegion() {
		return this.getAlarmSpot().getAlarmTimeRegion();
	}

	public Integer getLatestHistoryId() {
		return latestHistoryId;
	}

	public void setLatestHistoryId(Integer latestHistoryId) {
		this.latestHistoryId = latestHistoryId;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
