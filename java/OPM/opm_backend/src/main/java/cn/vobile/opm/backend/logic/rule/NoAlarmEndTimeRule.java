/*
 * @(#)NoAlarmEndTimeRule.java    1.0 2012-7-24
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.backend.logic.rule;

import java.text.ParseException;

import cn.vobile.opm.backend.common.OpmTimer;
import cn.vobile.opm.backend.dao.Livemonitor;

/**
 * NoAlarmEndTimeRule.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-24
 * @since 1.0
 */
public class NoAlarmEndTimeRule implements IActiveRule {

	/**
	 * no alarm between the no alarm start and end time
	 * 
	 * @throws ParseException
	 * @see cn.vobile.opm.backend.logic.rule.IActiveRule#applyRule(cn.vobile.opm.backend.bean.Livemonitor)
	 */
	public boolean applyRule(Livemonitor livemonitor) throws ParseException {
		if (livemonitor.getAlarmSpot().getHibernateStopTime() == null) {
			return false;
		} else {
			return OpmTimer.afterTheGiventime(livemonitor.getAlarmSpot()
					.getHibernateStartTime())
					&& !OpmTimer.afterTheGiventime(livemonitor.getAlarmSpot()
							.getHibernateStopTime());
		}
	}

}
