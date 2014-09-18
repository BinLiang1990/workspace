/*
 * @(#)AddUserAction.java    1.0 2012-7-26
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.shell.action.userpage;

import cn.vobile.opm.web.shell.bean.Opmuser;
import cn.vobile.opm.web.shell.common.MD5Builder;
import cn.vobile.opm.web.shell.service.UserService;
import cn.vobile.opm.web.shell.service.impl.UserServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

/**
 * AddUserAction.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-26
 * @since 1.0
 */
@SuppressWarnings("serial")
public class AddUserAction extends ActionSupport {

	private String uid;
	private String pwd;
	private String pwdag;
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
		if (uid == null || uid.equals("") || pwd == null || pwd.equals("")
				|| uname == null || uname.equals("")) {
		} else {
			if (pwd.equals(getPwdag())) {
				String md5pwd = MD5Builder.getMD5(pwd);

				Opmuser opmuser = new Opmuser(uid, md5pwd, 1, ugroup, uname,
						uphone, unote);
				UserService userService = new UserServiceImpl();
				userService.insertOpmUser(opmuser);
			}
		}

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
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * @param pwd
	 *            the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
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
	 * @return the pwdag
	 */
	public String getPwdag() {
		return pwdag;
	}

	/**
	 * @param pwdag
	 *            the pwdag to set
	 */
	public void setPwdag(String pwdag) {
		this.pwdag = pwdag;
	}

}
