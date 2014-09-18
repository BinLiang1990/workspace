package cn.vobile.opm.web.dao;

import java.sql.Timestamp;
import java.util.Set;

public class AlarmSpot {

	private int id;
	private String name;
	private String owner;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String status;
	private int intervalTime;
	private String level;
	private String type;
	private String criticality;
	private String description;
	private String alarmTimeRegion;
	private Timestamp hibernateStartTime;
	private Timestamp hibernateStopTime;
	private Set<Livemonitor> livemonitors;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getIntervalTime() {
		return intervalTime;
	}

	public void setIntervalTime(int intervalTime) {
		this.intervalTime = intervalTime;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCriticality() {
		return criticality;
	}

	public void setCriticality(String criticality) {
		this.criticality = criticality;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAlarmTimeRegion() {
		return alarmTimeRegion;
	}

	public void setAlarmTimeRegion(String alarmTimeRegion) {
		this.alarmTimeRegion = alarmTimeRegion;
	}

	public Timestamp getHibernateStartTime() {
		return hibernateStartTime;
	}

	public void setHibernateStartTime(Timestamp hibernateStartTime) {
		this.hibernateStartTime = hibernateStartTime;
	}

	public Timestamp getHibernateStopTime() {
		return hibernateStopTime;
	}

	public void setHibernateStopTime(Timestamp hibernateStopTime) {
		this.hibernateStopTime = hibernateStopTime;
	}

	public Set<Livemonitor> getLivemonitors() {
		return livemonitors;
	}

	public void setLivemonitors(Set<Livemonitor> livemonitors) {
		this.livemonitors = livemonitors;
	}
}
