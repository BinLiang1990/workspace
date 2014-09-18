package cn.vobile.opm.backend.common;

public class InfoUtil {

	private String idc;
	private String service;
	private String component;
	private String dbpcHost;
	private int dbpcPort;
	private String backendId;
	private int timeout;
	private String vnsHost;
	private int vnsPort;
	private String mailAddress;

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String getIdc() {
		return idc;
	}

	public void setIdc(String idc) {
		this.idc = idc;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getDbpcHost() {
		return dbpcHost;
	}

	public void setDbpcHost(String dbpcHost) {
		this.dbpcHost = dbpcHost;
	}

	public int getDbpcPort() {
		return dbpcPort;
	}

	public void setDbpcPort(int dbpcPort) {
		this.dbpcPort = dbpcPort;
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

	public String getBackendId() {
		return backendId;
	}

	public void setBackendId(String backendId) {
		this.backendId = backendId;
	}

}
