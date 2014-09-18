package com.randycode.dao.impl;

import com.randycode.model.User;
import com.randycode.dao.UserDAO;

public class UserDAOImpl implements UserDAO {

	@Override
	public void save(User u) {
		System.out.println("save success");
	}

}
