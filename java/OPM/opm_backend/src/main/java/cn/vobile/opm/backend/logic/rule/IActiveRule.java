/*
 * @(#)IActiveRule.java    1.0 2012-7-24
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.backend.logic.rule;

import java.text.ParseException;

import cn.vobile.opm.backend.dao.Livemonitor;

/**
 * IActiveRule.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-24
 * @since 1.0
 */
public interface IActiveRule {

	/**
	 * 
	 * @return true if pass the rule
	 * @throws ParseException
	 * */
	public boolean applyRule(Livemonitor livemonitor) throws ParseException;
}
