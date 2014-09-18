/*
 * @(#)UserServiceImpl.java    1.0 2012-7-25
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.shell.service.impl;

import java.util.List;

import cn.vobile.opm.web.shell.bean.Opmuser;
import cn.vobile.opm.web.shell.dao.DaoCreator;
import cn.vobile.opm.web.shell.dao.OpmuserDAO;
import cn.vobile.opm.web.shell.service.UserService;

/**
 * UserServiceImpl.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-25
 * @since 1.0
 */
public class UserServiceImpl implements UserService {

	private OpmuserDAO opmuserDAO;

	/**
	 *
	 */
	public UserServiceImpl() {
		opmuserDAO = (OpmuserDAO) DaoCreator.createDao("OpmuserDAO");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.vobile.opm.web.service.UserService#insertOpmUser(cn.vobile.opm.web
	 * .bean.Opmuser)
	 */
	public boolean insertOpmUser(Opmuser opmuser) {
		try {
			opmuserDAO.save(opmuser);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.vobile.opm.web.service.UserService#updayeOpmUser(cn.vobile.opm.web
	 * .bean.Opmuser)
	 */
	public boolean updateOpmUser(Opmuser opmuser) {
		try {
			Opmuser opmuserInDb = opmuserDAO.findById(opmuser.getId());

			opmuserInDb.setPasswd(opmuser.getPasswd());
			opmuserInDb.setUsergroup(opmuser.getUsergroup());
			opmuserInDb.setUsername(opmuser.getUsername());
			opmuserInDb.setPhone(opmuser.getPhone());
			opmuserInDb.setNote(opmuser.getNote());

			opmuserDAO.update(opmuserInDb);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.vobile.opm.web.service.UserService#getOpmusers()
	 */
	@SuppressWarnings("unchecked")
	public List<Opmuser> getAllOpmusers() {
		return opmuserDAO.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.vobile.opm.web.service.UserService#getOpmUser(java.lang.String)
	 */
	public Opmuser getOpmUser(int id) {
		return opmuserDAO.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.vobile.opm.web.service.UserService#getOpsOpmUsers()
	 */
	@SuppressWarnings("unchecked")
	public List<Opmuser> getOpsOpmUsers() {
		return opmuserDAO.findByUsergroup("ROLE_OPS");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.vobile.opm.web.service.UserService#updatePwd(java.lang.String,
	 * java.lang.String)
	 */
	public void updatePwd(String uid, String md5pwd) {
		Opmuser opmuser = (Opmuser) opmuserDAO.findByUserid(uid).get(0);
		opmuser.setPasswd(md5pwd);
		System.out.println("@@@" + opmuser.getPasswd() + opmuser.getUserid());
		opmuserDAO.update(opmuser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.vobile.opm.web.service.UserService#getOpmUserByUid(java.lang.String)
	 */
	public Opmuser getOpmUserByUid(String uid) {
		return (Opmuser) opmuserDAO.findByUserid(uid).get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.vobile.opm.web.service.UserService#getUserName(java.lang.String)
	 */
	public String getUserName(String uid) {
		return ((Opmuser) opmuserDAO.findByUserid(uid).get(0)).getUsername();
	}
}
