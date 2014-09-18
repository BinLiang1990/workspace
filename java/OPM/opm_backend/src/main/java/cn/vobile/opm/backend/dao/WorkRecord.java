package cn.vobile.opm.backend.dao;

import java.sql.Timestamp;

/**
 * WorkRecord entity can store backend work record.
 * 
 * @author wang_lin
 * 
 */
public class WorkRecord {

	private int id;
	private String backendId;
	private Timestamp startTime;
	private Timestamp endTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBackendId() {
		return backendId;
	}

	public void setBackendId(String backendId) {
		this.backendId = backendId;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

}
