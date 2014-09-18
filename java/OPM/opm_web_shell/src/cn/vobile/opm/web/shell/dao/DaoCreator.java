/*
 * @(#)DaoCreator.java    1.0 2012-7-25
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.shell.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * DaoCreator.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-25
 * @since 1.0
 */
public class DaoCreator {
	static ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			"applicationContext.xml");

	/**
	 * common dap creator
	 * 
	 * @return Object : cast to daoName
	 * */
	public static Object createDao(String daoName) {
		return applicationContext.getBean(daoName);
	}
}
