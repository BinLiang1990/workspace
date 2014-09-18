/*
 * @(#)UserService.java    1.0 2012-7-25
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.shell.service;

import java.util.List;

import cn.vobile.opm.web.shell.bean.Opmuser;

/**
 * UserService.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-25
 * @since 1.0
 */
public interface UserService {
	/**
	 * insert the user
	 * 
	 * @return true if success
	 * */
	public boolean insertOpmUser(Opmuser opmuser);

	/**
	 * update user info
	 * 
	 * @return true if success
	 * */
	public boolean updateOpmUser(Opmuser opmuser);

	/**
	 * get user list
	 * 
	 * @return List
	 * */
	public List<Opmuser> getAllOpmusers();

	/**
	 * @param uid
	 * @return
	 */
	public Opmuser getOpmUser(int id);

	/**
	 * @return
	 */
	public List<Opmuser> getOpsOpmUsers();

	/**
	 * @param uid
	 * @param md5pwd
	 */
	public void updatePwd(String uid, String md5pwd);

	/**
	 * @param uid
	 * @return
	 */
	public Opmuser getOpmUserByUid(String uid);

	/**
	 * @param acceptedPerson
	 * @return
	 */
	public String getUserName(String uid);

}
