/*
 * @(#)AlarmTimeRegionRule.java    1.0 2012-7-24
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.backend.logic.rule;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.TimeZone;

import cn.vobile.opm.backend.common.OpmTimer;
import cn.vobile.opm.backend.dao.Livemonitor;

/**
 * AlarmTimeRegionRule.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-24
 * @since 1.0
 */
public class AlarmTimeRegionRule implements IActiveRule {

	/**
	 * @throws ParseException
	 * @see cn.vobile.opm.backend.logic.rule.IActiveRule#applyRule()
	 */
	@SuppressWarnings("deprecation")
	// make it CST timzone
	public boolean applyRule(Livemonitor livemonitor) throws ParseException {

		Timestamp nowTimeCSTstamp = Timestamp.valueOf(OpmTimer
				.getNowTimeCSTString());
		if (livemonitor.getAlarmTimeRegion().equals("7*24")) {
			return false;
		} else if (livemonitor.getAlarmTimeRegion().equals("7*12")) { // 09:00 -
																		// 21:00
			if (nowTimeCSTstamp.getHours() >= 9
					&& nowTimeCSTstamp.getHours() < 21) {
				return false;
			}
		} else { // 5*9

			Calendar calendar = Calendar.getInstance(TimeZone
					.getTimeZone("GMT+8"));
			if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
					&& calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				if (nowTimeCSTstamp.getHours() >= 8
						&& nowTimeCSTstamp.getHours() < 19) {
					return false;
				}
			}
		}

		return true;
	}
}
