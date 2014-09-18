package cn.vobile.opm.backend.dao;

import java.sql.Timestamp;

/**
 * Alarmhistory entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class AlarmHistory implements java.io.Serializable {

	// Fields

	private Integer id;
	private Livemonitor livemonitor;
	private String status;
	private Boolean isAccepted;
	private String acceptedPerson;
	private Timestamp acceptedTime;
	private Boolean isAlarmed;
	private Timestamp alarmedTime;
	private String alarmedPerson;
	private String alarmedFeedback;
	private String alarmedNote;
	private Boolean isResponsed;
	private Timestamp responsedTime;
	private String responsedPerson;
	private String responsedHandling;
	private String responsedNote;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	// Constructors

	/** default constructor */
	public AlarmHistory() {
	}

	/** minimal constructor */
	public AlarmHistory(Livemonitor livemonitor, String status,
			Boolean isAccepted, Boolean isAlarmed, Boolean isResponsed,
			Timestamp createdAt, Timestamp updatedAt) {
		this.livemonitor = livemonitor;
		this.status = status;
		this.isAccepted = isAccepted;
		this.isAlarmed = isAlarmed;
		this.isResponsed = isResponsed;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	/** full constructor */
	public AlarmHistory(Livemonitor livemonitor, String status,
			Boolean isAccepted, String acceptedPerson, Timestamp acceptedTime,
			Boolean isAlarmed, Timestamp alarmedTime, String alarmedPerson,
			String alarmedFeedback, String alarmedNote, Boolean isResponsed,
			Timestamp responsedTime, String responsedPerson,
			String responsedHandling, String responsedNote,
			Timestamp createdAt, Timestamp updatedAt) {
		this.livemonitor = livemonitor;
		this.status = status;
		this.isAccepted = isAccepted;
		this.acceptedPerson = acceptedPerson;
		this.acceptedTime = acceptedTime;
		this.isAlarmed = isAlarmed;
		this.alarmedTime = alarmedTime;
		this.alarmedPerson = alarmedPerson;
		this.alarmedFeedback = alarmedFeedback;
		this.alarmedNote = alarmedNote;
		this.isResponsed = isResponsed;
		this.responsedTime = responsedTime;
		this.responsedPerson = responsedPerson;
		this.responsedHandling = responsedHandling;
		this.responsedNote = responsedNote;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Livemonitor getLivemonitor() {
		return livemonitor;
	}

	public void setLivemonitor(Livemonitor livemonitor) {
		this.livemonitor = livemonitor;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getIsAccepted() {
		return this.isAccepted;
	}

	public void setIsAccepted(Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public String getAcceptedPerson() {
		return this.acceptedPerson;
	}

	public void setAcceptedPerson(String acceptedPerson) {
		this.acceptedPerson = acceptedPerson;
	}

	public Timestamp getAcceptedTime() {
		return this.acceptedTime;
	}

	public void setAcceptedTime(Timestamp acceptedTime) {
		this.acceptedTime = acceptedTime;
	}

	public Boolean getIsAlarmed() {
		return this.isAlarmed;
	}

	public void setIsAlarmed(Boolean isAlarmed) {
		this.isAlarmed = isAlarmed;
	}

	public Timestamp getAlarmedTime() {
		return this.alarmedTime;
	}

	public void setAlarmedTime(Timestamp alarmedTime) {
		this.alarmedTime = alarmedTime;
	}

	public String getAlarmedPerson() {
		return this.alarmedPerson;
	}

	public void setAlarmedPerson(String alarmedPerson) {
		this.alarmedPerson = alarmedPerson;
	}

	public String getAlarmedFeedback() {
		return this.alarmedFeedback;
	}

	public void setAlarmedFeedback(String alarmedFeedback) {
		this.alarmedFeedback = alarmedFeedback;
	}

	public String getAlarmedNote() {
		return this.alarmedNote;
	}

	public void setAlarmedNote(String alarmedNote) {
		this.alarmedNote = alarmedNote;
	}

	public Boolean getIsResponsed() {
		return this.isResponsed;
	}

	public void setIsResponsed(Boolean isResponsed) {
		this.isResponsed = isResponsed;
	}

	public Timestamp getResponsedTime() {
		return this.responsedTime;
	}

	public void setResponsedTime(Timestamp responsedTime) {
		this.responsedTime = responsedTime;
	}

	public String getResponsedPerson() {
		return this.responsedPerson;
	}

	public void setResponsedPerson(String responsedPerson) {
		this.responsedPerson = responsedPerson;
	}

	public String getResponsedHandling() {
		return this.responsedHandling;
	}

	public void setResponsedHandling(String responsedHandling) {
		this.responsedHandling = responsedHandling;
	}

	public String getResponsedNote() {
		return this.responsedNote;
	}

	public void setResponsedNote(String responsedNote) {
		this.responsedNote = responsedNote;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}
