package com.randycode.service;

import com.randycode.model.User;
import com.randycode.dao.UserDAO;
import com.randycode.dao.impl.UserDAOImpl;

public class UserService {

	private UserDAO userDAO;
	
	public void addUser(User u) {
		userDAO.save(u);
	}

	public UserDAO getuserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
