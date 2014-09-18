/*
 * @(#)GetOpmUserAction.java    1.0 2012-7-26
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.shell.action.userpage;

import cn.vobile.opm.web.shell.bean.Opmuser;
import cn.vobile.opm.web.shell.service.UserService;
import cn.vobile.opm.web.shell.service.impl.UserServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

/**
 * GetOpmUserAction.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-26
 * @since 1.0
 */
@SuppressWarnings("serial")
public class GetOpmUserAction extends ActionSupport {
	private Opmuser opmuser;
	private String id;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		UserService userService = new UserServiceImpl();
		this.opmuser = userService.getOpmUser(Integer.parseInt(id));
		return super.execute();
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

}
