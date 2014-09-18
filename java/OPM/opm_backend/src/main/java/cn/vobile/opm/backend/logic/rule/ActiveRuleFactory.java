/*
 * @(#)ActiveRuleFactory.java    1.0 2012-7-24
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.backend.logic.rule;

/**
 * ActiveRuleFactory.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-24
 * @since 1.0
 */
public class ActiveRuleFactory {
	/**
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * 
	 * */
	public static IActiveRule createActiveRule(String name)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		return (IActiveRule) Class.forName(
				"cn.vobile.opm.backend.logic.rule." + name).newInstance();
	}
}
