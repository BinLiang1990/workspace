//package cn.vobile.opm.web.bean;
//
//import java.sql.Timestamp;
//
//import cn.vobile.opm.web.common.OpmTimer;
//import cn.vobile.opm.web.service.enumdata.IsResponsed;
//
///**
// * AlarmHistory entity. @author MyEclipse Persistence Tools
// */
//
//@SuppressWarnings("serial")
//public class AlarmHistory implements java.io.Serializable {
//
//	// Fields
//
//	private Integer id;
//	private Integer livemonitorId;
//	private String status;
//	private Boolean isAccepted;
//	private String acceptedPerson;
//	private Timestamp acceptedTime;
//	private Boolean isAlarmed;
//	private Timestamp alarmedTime;
//	private String alarmedPerson;
//	private String alarmedFeedback;
//	private String alarmedNote;
//	private Boolean isResponsed;
//	private Timestamp responsedTime;
//	private String responsedPerson;
//	private String responsedHandling;
//	private String responsedNote;
//	private Timestamp createdAt;
//	private Timestamp updatedAt;
//
//	private Livemonitor livemonitor;
//	private String convertedAcceptedTime;
//	private String convertedAlarmedTime;
//	private String convertedResponsedTime;
//
//	private String convertedIsResponsed;
//
//	// Constructors
//
//	/** default constructor */
//	public AlarmHistory() {
//	}
//
//	/** minimal constructor */
//	public AlarmHistory(Integer livemonitorId, String status,
//			Boolean isAccepted, Boolean isAlarmed, Boolean isResponsed,
//			Timestamp createdAt, Timestamp updatedAt) {
//		this.livemonitorId = livemonitorId;
//		this.status = status;
//		this.isAccepted = isAccepted;
//		this.isAlarmed = isAlarmed;
//		this.isResponsed = isResponsed;
//		this.createdAt = createdAt;
//		this.updatedAt = updatedAt;
//	}
//
//	/** full constructor */
//	public AlarmHistory(Integer livemonitorId, String status,
//			Boolean isAccepted, String acceptedPerson, Timestamp acceptedTime,
//			Boolean isAlarmed, Timestamp alarmedTime, String alarmedPerson,
//			String alarmedFeedback, String alarmedNote, Boolean isResponsed,
//			Timestamp responsedTime, String responsedPerson,
//			String responsedHandling, String responsedNote,
//			Timestamp createdAt, Timestamp updatedAt) {
//		this.livemonitorId = livemonitorId;
//		this.status = status;
//		this.isAccepted = isAccepted;
//		this.acceptedPerson = acceptedPerson;
//		this.acceptedTime = acceptedTime;
//		this.isAlarmed = isAlarmed;
//		this.alarmedTime = alarmedTime;
//		this.alarmedPerson = alarmedPerson;
//		this.alarmedFeedback = alarmedFeedback;
//		this.alarmedNote = alarmedNote;
//		this.isResponsed = isResponsed;
//		this.responsedTime = responsedTime;
//		this.responsedPerson = responsedPerson;
//		this.responsedHandling = responsedHandling;
//		this.responsedNote = responsedNote;
//		this.createdAt = createdAt;
//		this.updatedAt = updatedAt;
//	}
//
//	// Property accessors
//
//	public Integer getId() {
//		return this.id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public Integer getLivemonitorId() {
//		return this.livemonitorId;
//	}
//
//	public void setLivemonitorId(Integer livemonitorId) {
//		this.livemonitorId = livemonitorId;
//	}
//
//	public String getStatus() {
//		return this.status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public Boolean getIsAccepted() {
//		return this.isAccepted;
//	}
//
//	public void setIsAccepted(Boolean isAccepted) {
//		this.isAccepted = isAccepted;
//	}
//
//	public String getAcceptedPerson() {
//		return this.acceptedPerson;
//	}
//
//	public void setAcceptedPerson(String acceptedPerson) {
//		this.acceptedPerson = acceptedPerson;
//	}
//
//	public Timestamp getAcceptedTime() {
//		return this.acceptedTime;
//	}
//
//	public void setAcceptedTime(Timestamp acceptedTime) {
//		this.acceptedTime = acceptedTime;
//	}
//
//	public Boolean getIsAlarmed() {
//		return this.isAlarmed;
//	}
//
//	public void setIsAlarmed(Boolean isAlarmed) {
//		this.isAlarmed = isAlarmed;
//	}
//
//	public Timestamp getAlarmedTime() {
//		return this.alarmedTime;
//	}
//
//	public void setAlarmedTime(Timestamp alarmedTime) {
//		this.alarmedTime = alarmedTime;
//	}
//
//	public String getAlarmedPerson() {
//		return this.alarmedPerson;
//	}
//
//	public void setAlarmedPerson(String alarmedPerson) {
//		this.alarmedPerson = alarmedPerson;
//	}
//
//	public String getAlarmedFeedback() {
//		return this.alarmedFeedback;
//	}
//
//	public void setAlarmedFeedback(String alarmedFeedback) {
//		this.alarmedFeedback = alarmedFeedback;
//	}
//
//	public String getAlarmedNote() {
//		return this.alarmedNote;
//	}
//
//	public void setAlarmedNote(String alarmedNote) {
//		this.alarmedNote = alarmedNote;
//	}
//
//	public Boolean getIsResponsed() {
//		return this.isResponsed;
//	}
//
//	public void setIsResponsed(Boolean isResponsed) {
//		this.isResponsed = isResponsed;
//	}
//
//	public Timestamp getResponsedTime() {
//		return this.responsedTime;
//	}
//
//	public void setResponsedTime(Timestamp responsedTime) {
//		this.responsedTime = responsedTime;
//	}
//
//	public String getResponsedPerson() {
//		return this.responsedPerson;
//	}
//
//	public void setResponsedPerson(String responsedPerson) {
//		this.responsedPerson = responsedPerson;
//	}
//
//	public String getResponsedHandling() {
//		return this.responsedHandling;
//	}
//
//	public void setResponsedHandling(String responsedHandling) {
//		this.responsedHandling = responsedHandling;
//	}
//
//	public String getResponsedNote() {
//		return this.responsedNote;
//	}
//
//	public void setResponsedNote(String responsedNote) {
//		this.responsedNote = responsedNote;
//	}
//
//	public Timestamp getCreatedAt() {
//		return this.createdAt;
//	}
//
//	public void setCreatedAt(Timestamp createdAt) {
//		this.createdAt = createdAt;
//	}
//
//	public Timestamp getUpdatedAt() {
//		return this.updatedAt;
//	}
//
//	public void setUpdatedAt(Timestamp updatedAt) {
//		this.updatedAt = updatedAt;
//	}
//
//	/**
//	 * @return the livemonitor
//	 */
//	public Livemonitor getLivemonitor() {
//		return livemonitor;
//	}
//
//	/**
//	 * @param livemonitor
//	 *            the livemonitor to set
//	 */
//	public void setLivemonitor(Livemonitor livemonitor) {
//		this.livemonitor = livemonitor;
//	}
//
//	/**
//	 * @return the convertedAcceptedTime
//	 */
//	public String getConvertedAcceptedTime() {
//		try {
//			OpmTimer opmTimer = new OpmTimer();
//			this.convertedAcceptedTime = opmTimer.convertToCSTString(
//					acceptedTime).split("\\.")[0];
//		} catch (Exception e) {
//		}
//
//		return convertedAcceptedTime;
//	}
//
//	/**
//	 * @param convertedAcceptedTime
//	 *            the convertedAcceptedTime to set
//	 */
//	public void setConvertedAcceptedTime(String convertedAcceptedTime) {
//		this.convertedAcceptedTime = convertedAcceptedTime;
//	}
//
//	/**
//	 * @return the convertedAlarmedTime
//	 */
//	public String getConvertedAlarmedTime() {
//		try {
//			OpmTimer opmTimer = new OpmTimer();
//			this.convertedAlarmedTime = opmTimer
//					.convertToCSTString(alarmedTime).split("\\.")[0];
//		} catch (Exception e) {
//		}
//		return convertedAlarmedTime;
//	}
//
//	/**
//	 * @param convertedAlarmedTime
//	 *            the convertedAlarmedTime to set
//	 */
//	public void setConvertedAlarmedTime(String convertedAlarmedTime) {
//		this.convertedAlarmedTime = convertedAlarmedTime;
//	}
//
//	/**
//	 * @return the convertedResponsedTime
//	 */
//	public String getConvertedResponsedTime() {
//		try {
//			OpmTimer opmTimer = new OpmTimer();
//			this.convertedResponsedTime = opmTimer.convertToCSTString(
//					responsedTime).split("\\.")[0];
//		} catch (Exception e) {
//		}
//		return convertedResponsedTime;
//	}
//
//	/**
//	 * @param convertedResponsedTime
//	 *            the convertedResponsedTime to set
//	 */
//	public void setConvertedResponsedTime(String convertedResponsedTime) {
//		this.convertedResponsedTime = convertedResponsedTime;
//	}
//
//	/**
//	 * @param isResponsed2
//	 */
//	public void setConvertedIsResponsed(Boolean isResponsed2) {
//		if (isResponsed2) {
//			this.setConvertedIsResponsed(IsResponsed.TRUE.getResult());
//		} else {
//			this.setConvertedIsResponsed(IsResponsed.FALSE.getResult());
//		}
//	}
//
//	/**
//	 * @return the convertedIsResponsed
//	 */
//	public String getConvertedIsResponsed() {
//		return convertedIsResponsed;
//	}
//
//	/**
//	 * @param convertedIsResponsed
//	 *            the convertedIsResponsed to set
//	 */
//	public void setConvertedIsResponsed(String convertedIsResponsed) {
//		this.convertedIsResponsed = convertedIsResponsed;
//	}
//
// }
