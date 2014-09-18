package com.randycode.service;

import static org.junit.Assert.*;

import java.lang.reflect.Proxy;

import org.junit.Test;

import com.randycode.dao.UserDAO;
import com.randycode.dao.impl.UserDAOImpl;
import com.randycode.model.User;
import com.randycode.spring.BeanFactory;
import com.randycode.spring.ClassPathXmlApplicationContext;
import com.randycode.spring.LogInterceptor;

public class UserServiceTest {

	@Test
	public void testAddUser() throws Exception {
		BeanFactory bf = new ClassPathXmlApplicationContext();
		UserService us = (UserService)bf.getBean("us");
		us.addUser(new User());
	}

	@Test
	public void testProxy() {
		UserDAO userDAO = new UserDAOImpl();
		LogInterceptor li = new LogInterceptor();
		li.setTarget(userDAO);
		UserDAO userDAOProxyDao = (UserDAO)Proxy.newProxyInstance(userDAO.getClass().getClassLoader(), userDAO.getClass().getInterfaces(), li);
		userDAOProxyDao.save(new User());
	}
}
