/*
 * @(#)ModifyUserAction.java    1.0 2012-7-26
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.action.userpage;

import cn.vobile.opm.web.dao.Opmuser;
import cn.vobile.opm.web.service.UserService;
import cn.vobile.opm.web.service.impl.UserServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

/**
 * ModifyUserAction.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-26
 * @since 1.0
 */
@SuppressWarnings("serial")
public class ModifyUserAction extends ActionSupport {

	private String id;
	private String uid;
	private String ugroup;
	private String uname;
	private String uphone;
	private String unote;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		UserService userService = new UserServiceImpl();
		Opmuser opmuser = userService.getOpmUser(Integer.parseInt(getId()));

		opmuser.setUsergroup(ugroup);
		opmuser.setUsername(uname);
		opmuser.setPhone(uphone);
		opmuser.setNote(unote);

		userService.updateOpmUser(opmuser);

		return super.execute();
	}

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @return the ugroup
	 */
	public String getUgroup() {
		return ugroup;
	}

	/**
	 * @param ugroup
	 *            the ugroup to set
	 */
	public void setUgroup(String ugroup) {
		this.ugroup = ugroup;
	}

	/**
	 * @return the uname
	 */
	public String getUname() {
		return uname;
	}

	/**
	 * @param uname
	 *            the uname to set
	 */
	public void setUname(String uname) {
		this.uname = uname;
	}

	/**
	 * @return the uphone
	 */
	public String getUphone() {
		return uphone;
	}

	/**
	 * @param uphone
	 *            the uphone to set
	 */
	public void setUphone(String uphone) {
		this.uphone = uphone;
	}

	/**
	 * @return the unote
	 */
	public String getUnote() {
		return unote;
	}

	/**
	 * @param unote
	 *            the unote to set
	 */
	public void setUnote(String unote) {
		this.unote = unote;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

}
