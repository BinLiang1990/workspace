package cn.vobile.opm.web.dao;

/**
 * SearchConditions entity
 * 
 * @author wang_lin
 * 
 */
@SuppressWarnings("serial")
public class SearchConditions implements java.io.Serializable {

	private int id;
	private String opmUserId, serverName, componentName, tag, serviceName,
			alarmLevel, alarmTimeRegion, status;
	private long delayTimeLow, delayTimeHigh;

	public String getOpmUserId() {
		return opmUserId;
	}

	public void setOpmUserId(String opmuserId) {
		this.opmUserId = opmuserId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getAlarmLevel() {
		return alarmLevel;
	}

	public void setAlarmLevel(String alarmLevel) {
		this.alarmLevel = alarmLevel;
	}

	public String getAlarmTimeRegion() {
		return alarmTimeRegion;
	}

	public void setAlarmTimeRegion(String alarmTimeRegion) {
		this.alarmTimeRegion = alarmTimeRegion;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getDelayTimeLow() {
		return delayTimeLow;
	}

	public void setDelayTimeLow(long delayTimeLow) {
		this.delayTimeLow = delayTimeLow;
	}

	public long getDelayTimeHigh() {
		return delayTimeHigh;
	}

	public void setDelayTimeHigh(long delayTimeHigh) {
		this.delayTimeHigh = delayTimeHigh;
	}

}
