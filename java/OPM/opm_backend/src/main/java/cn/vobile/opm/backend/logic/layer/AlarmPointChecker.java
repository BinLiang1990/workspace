/*
 * @(#)AlarmPointChecker.java    1.0 2012-7-23
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.backend.logic.layer;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.vobile.opm.backend.dao.AlarmHistory;
import cn.vobile.opm.backend.dao.Livemonitor;
import cn.vobile.opm.backend.dao.LivemonitorDAO;
import cn.vobile.opm.backend.logic.rule.ActiveRuleFactory;

/**
 * AlarmPointChecker.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-23
 * @since 1.0
 */
@Service
public class AlarmPointChecker {
	@Resource
	private LivemonitorDAO livemonitorDAO;

	@Transactional
	public List<AlarmHistory> checkAlarmPoint() throws ParseException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		List<AlarmHistory> histories = new ArrayList<AlarmHistory>();
		List<Livemonitor> livemonitors = livemonitorDAO.findAll();
		for (Livemonitor livemonitor : livemonitors) {

			AlarmHistory history = new AlarmHistory(livemonitor,
					Status.ACTIVE_UP.getStatus(), false, false, false,
					new Timestamp(System.currentTimeMillis()), new Timestamp(
							System.currentTimeMillis()));
			if (livemonitor.isDeleted()) {
				history.setStatus(Status.DELETED.getStatus());
			} else if (livemonitor.isInactive()) {
				history.setStatus(Status.INACTIVE.getStatus());
			} else if (livemonitor.isAlarmPointOK()) {
				history.setStatus(Status.OK.getStatus());
			} else if (livemonitor.isWarning()) {
				// / check the warning
				if (ActiveRuleFactory.createActiveRule("AlarmTimeRegionRule")
						.applyRule(livemonitor)) {
					history.setStatus(Status.WARNING_DOWN.getStatus());
				} else if (ActiveRuleFactory.createActiveRule(
						"NoAlarmEndTimeRule").applyRule(livemonitor)) {
					history.setStatus(Status.WARNING_DOWN.getStatus());
				} else {
					history.setStatus(Status.WARNING_UP.getStatus());
				}
			} else {
				if (ActiveRuleFactory.createActiveRule("AlarmTimeRegionRule")
						.applyRule(livemonitor)) {
					history.setStatus(Status.ACTIVE_DOWN.getStatus());
				} else if (ActiveRuleFactory.createActiveRule(
						"NoAlarmEndTimeRule").applyRule(livemonitor)) {
					history.setStatus(Status.ACTIVE_DOWN.getStatus());
				} else {
				}
			}
			histories.add(history);
		}
		return histories;
	}

}
