package cn.vobile.opm.web.bean;

import java.sql.Timestamp;

import cn.vobile.opm.web.dao.Livemonitor;
import cn.vobile.opm.web.dao.Opmuser;
import cn.vobile.opm.web.service.enumdata.Operation;

/**
 * UserOpHistory entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class UserOpHistory implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer opmuserId;
	private String operation;
	private Integer livemonitorId;
	private String originalValue;
	private String changeValue;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	private Opmuser opmuser;
	private Livemonitor livemonitor;

	private String convertCreatedAt;
	private String convertedOperation;

	// Constructors

	/** default constructor */
	public UserOpHistory() {
	}

	/** minimal constructor */
	public UserOpHistory(Integer opmuserId, Integer livemonitorId,
			Timestamp createdAt, Timestamp updatedAt) {
		this.opmuserId = opmuserId;
		this.livemonitorId = livemonitorId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	/** full constructor */
	public UserOpHistory(Integer opmuserId, String operation,
			Integer livemonitorId, String originalValue, String changeValue,
			Timestamp createdAt, Timestamp updatedAt) {
		this.opmuserId = opmuserId;
		this.operation = operation;
		this.livemonitorId = livemonitorId;
		this.originalValue = originalValue;
		this.changeValue = changeValue;
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

	public Integer getOpmuserId() {
		return this.opmuserId;
	}

	public void setOpmuserId(Integer opmuserId) {
		this.opmuserId = opmuserId;
	}

	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Integer getLivemonitorId() {
		return this.livemonitorId;
	}

	public void setLivemonitorId(Integer livemonitorId) {
		this.livemonitorId = livemonitorId;
	}

	public String getOriginalValue() {
		return this.originalValue;
	}

	public void setOriginalValue(String originalValue) {
		this.originalValue = originalValue;
	}

	public String getChangeValue() {
		return this.changeValue;
	}

	public void setChangeValue(String changeValue) {
		this.changeValue = changeValue;
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

	/**
	 * @return the opmuser
	 */
	public Opmuser getOpmuser() {
		return opmuser;
	}

	/**
	 * @param opmuser
	 *            the opmuser to set
	 */
	public void setOpmuser(Opmuser opmuser) {
		this.opmuser = opmuser;
	}

	/**
	 * @return the livemonitor
	 */
	public Livemonitor getLivemonitor() {
		return livemonitor;
	}

	/**
	 * @param livemonitor
	 *            the livemonitor to set
	 */
	public void setLivemonitor(Livemonitor livemonitor) {
		this.livemonitor = livemonitor;
	}

	/**
	 * @return the convertCreatedAt
	 */
	public String getConvertCreatedAt() {
		return convertCreatedAt;
	}

	/**
	 * @param convertCreatedAt
	 *            the convertCreatedAt to set
	 */
	public void setConvertCreatedAt(String convertCreatedAt) {
		this.convertCreatedAt = convertCreatedAt;
	}

	/**
	 * @return the convertedOperation
	 */
	public String getConvertedOperation() {
		if (this.operation.equals(Operation.ALARM_CG.getOperation())) {
			this.convertedOperation = Operation.ALARM_CG_CH.getOperation();
		} else if (this.operation.equals(Operation.CRITI_CG.getOperation())) {
			this.convertedOperation = Operation.CRITI_CG_CH.getOperation();
		} else if (this.operation.equals(Operation.DELAY_CG.getOperation())) {
			this.convertedOperation = Operation.DELAY_CG_CH.getOperation();
		} else if (this.operation.equals(Operation.DELETE.getOperation())) {
			convertedOperation = Operation.DELETE_CH.getOperation();
		} else if (this.operation.equals(Operation.NO_ALRM_ST.getOperation())) {
			convertedOperation = Operation.NO_ALRM_ST_CH.getOperation();
		} else if (this.operation.equals(Operation.OTHER.getOperation())) {
			convertedOperation = Operation.OTHER_CH.getOperation();
		} else if (this.operation.equals(Operation.TYPE_CG.getOperation())) {
			convertedOperation = Operation.TYPE_CG_CH.getOperation();
		}
		return convertedOperation;
	}

	/**
	 * @param convertedOperation
	 *            the convertedOperation to set
	 */
	public void setConvertedOperation(String convertedOperation) {
		this.convertedOperation = convertedOperation;
	}

}
