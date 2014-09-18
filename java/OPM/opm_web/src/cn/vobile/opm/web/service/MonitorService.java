///*
// * @(#)MonitorService.java    1.0 2012-7-30
// *
// * Copyright © 2012 Vobile, Inc. All rights reserved.
// */
//package cn.vobile.opm.web.service;
//
//import java.util.List;
//import java.util.Map;
//import java.util.TreeMap;
//
//import cn.vobile.opm.web.dao.Livemonitor;
//
///**
// * MonitorService.java
// * 
// * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
// * @version 1.0 2012-7-30
// * @since 1.0
// */
//public interface MonitorService {
//	/**
//	 * to get the monitor map as:<br>
//	 * service_name<br>
//	 * | |-server/ip<br>
//	 * | | `-livemonitors<br>
//	 * | `-server/ip<br>
//	 * |-<br>
//	 * 
//	 * @return Map<String, Map<String, Map<String, List<Livemonitor>>>>
//	 * */
//	TreeMap<String, Map<String, List<Livemonitor>>> getMonitors();
//
//	/**
//	 * to get the monitor map as:<br>
//	 * service_name<br>
//	 * | |-server/ip<br>
//	 * | | `-livemonitors<br>
//	 * | `-server/ip<br>
//	 * |-<br>
//	 * 
//	 * @return Map<String, Map<String, Map<String, List<Livemonitor>>>>
//	 * */
//	TreeMap<String, Map<String, List<Livemonitor>>> getMonitorsNew();
//
//	/**
//	 * update a live monitor
//	 * 
//	 * @return true if success
//	 * */
//	boolean updateLiveMonitor(Livemonitor livemonitor);
//
//	/**
//	 * update the delay time
//	 * 
//	 * @param string
//	 * 
//	 * @return true if success
//	 * 
//	 * */
//	boolean updateLiveMonitorDeleyTime(String opmuser_userid,
//			Livemonitor livemonitor);
//
//	/**
//	 * @param opmuser_userid
//	 * @param livemonitor
//	 * 
//	 * @return true if success
//	 */
//	boolean updateLiveMonitorAlarmLevel(String opmuser_userid,
//			Livemonitor livemonitor);
//
//	/**
//	 * @param opmuser_userid
//	 * @param livemonitor
//	 */
//	boolean updateLiveMonitorNoAlarmTime(String opmuser_userid,
//			Livemonitor livemonitor);
//
//	boolean updateLiveMonitorDescription(String opmuser_userid,
//			Livemonitor livemonitor);
//
//	boolean updateLiveMonitorType(String opmuser_userid, Livemonitor livemonitor);
//
//	boolean updateLiveMonitorCriticality(String opmuser_userid,
//			Livemonitor livemonitor);
//
//	boolean updateLiveMonitorAlarmPerson(String opmuser_userid,
//			Livemonitor livemonitor);
//
//	/**
//	 * @return
//	 */
//	List<Livemonitor> getDeletedLivemonitors();
//
//	/**
//	 * @param opmuser_userid
//	 * @param dellids
//	 */
//	boolean deleteLivemonitor(String opmuser_userid, int dellids);
//
// }
