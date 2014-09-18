/*
 * @(#)ChangePwdAction.java    1.0 2012-8-6
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.shell.action.userpage;

import cn.vobile.opm.web.shell.common.MD5Builder;
import cn.vobile.opm.web.shell.service.UserService;
import cn.vobile.opm.web.shell.service.impl.UserServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

/**
 * ChangePwdAction.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-8-6
 * @since 1.0
 */
@SuppressWarnings("serial")
public class ChangePwdAction extends ActionSupport {
	private String uid;
	private String pwd;
	private String pwdag;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		if (pwd.equals(pwdag)) {
			String md5pwd = MD5Builder.getMD5(pwd);

			UserService userService = new UserServiceImpl();
			userService.updatePwd(uid, md5pwd);
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
