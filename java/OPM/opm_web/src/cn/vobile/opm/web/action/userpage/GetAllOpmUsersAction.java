/*
 * @(#)GetAllOpmUsersAction.java    1.0 2012-7-26
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.action.userpage;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;

import cn.vobile.opm.web.dao.Opmuser;
import cn.vobile.opm.web.service.UserService;
import cn.vobile.opm.web.service.impl.UserServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

/**
 * GetAllOpmUsersAction.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-26
 * @since 1.0
 */
@SuppressWarnings("serial")
public class GetAllOpmUsersAction extends ActionSupport {

	private List<Opmuser> opmusers;
	private Opmuser loginUser;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {

		UserService userService = new UserServiceImpl();
		this.opmusers = userService.getAllOpmusers();

		String uid = SecurityContextHolder.getContext().getAuthentication()
				.getName();
		this.loginUser = userService.getOpmUserByUid(uid);
		return super.execute();
	}

	/**
	 * @return the opmusers
	 */
	public List<Opmuser> getOpmusers() {
		return opmusers;
	}

	/**
	 * @param opmusers
	 *            the opmusers to set
	 */
	public void setOpmusers(List<Opmuser> opmusers) {
		this.opmusers = opmusers;
	}

	/**
	 * @return the loginUser
	 */
	public Opmuser getLoginUser() {
		return loginUser;
	}

	/**
	 * @param loginUser
	 *            the loginUser to set
	 */
	public void setLoginUser(Opmuser loginUser) {
		this.loginUser = loginUser;
	}
}
