///*
// * @(#)MonitorServiceImpl.java    1.0 2012-7-30
// *
// * Copyright © 2012 Vobile, Inc. All rights reserved.
// */
//package cn.vobile.opm.web.service.impl;
//
//import java.util.List;
//import java.util.Map;
//import java.util.TreeMap;
//
//import cn.vobile.opm.web.dao.AlarmSpotDAO;
//import cn.vobile.opm.web.dao.DaoCreator;
//import cn.vobile.opm.web.dao.Livemonitor;
//import cn.vobile.opm.web.dao.LivemonitorDAO;
//
//
///**
// * MonitorServiceImpl.java
// * 
// * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
// * @version 1.0 2012-7-30
// * @since 1.0
// */
//public class MonitorServiceImpl implements
//		cn.vobile.opm.web.service.MonitorService {
//
//	private LivemonitorDAO livemonitorDAO = (LivemonitorDAO) DaoCreator
//			.createDao("LivemonitorDAO");
//	private AlarmSpotDAO alarmSpotDAO = (AlarmSpotDAO) DaoCreator
//			.createDao("AlarmSpotDAO");
//	@Override
//	public TreeMap<String, Map<String, List<Livemonitor>>> getMonitors() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public TreeMap<String, Map<String, List<Livemonitor>>> getMonitorsNew() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean updateLiveMonitor(Livemonitor livemonitor) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean updateLiveMonitorDeleyTime(String opmuser_userid,
//			Livemonitor livemonitor) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean updateLiveMonitorAlarmLevel(String opmuser_userid,
//			Livemonitor livemonitor) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean updateLiveMonitorNoAlarmTime(String opmuser_userid,
//			Livemonitor livemonitor) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean updateLiveMonitorDescription(String opmuser_userid,
//			Livemonitor livemonitor) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean updateLiveMonitorType(String opmuser_userid,
//			Livemonitor livemonitor) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean updateLiveMonitorCriticality(String opmuser_userid,
//			Livemonitor livemonitor) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean updateLiveMonitorAlarmPerson(String opmuser_userid,
//			Livemonitor livemonitor) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public List<Livemonitor> getDeletedLivemonitors() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean deleteLivemonitor(String opmuser_userid, int dellids) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
// }
