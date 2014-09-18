package cn.vobile.opm.backend.dao;

/**
 * Opmuser entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Opmuser implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userid;
	private String passwd;
	private Integer isEnabled;
	private String usergroup;
	private String username;
	private String phone;
	private String note;

	// Constructors

	/** default constructor */
	public Opmuser() {
	}

	/** full constructor */
	public Opmuser(String userid, String passwd, Integer isEnabled,
			String usergroup, String username, String phone, String note) {
		this.userid = userid;
		this.passwd = passwd;
		this.isEnabled = isEnabled;
		this.usergroup = usergroup;
		this.username = username;
		this.phone = phone;
		this.note = note;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Integer getIsEnabled() {
		return this.isEnabled;
	}

	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getUsergroup() {
		return this.usergroup;
	}

	public void setUsergroup(String usergroup) {
		this.usergroup = usergroup;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
