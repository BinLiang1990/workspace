/*
 * @(#)AlarmPointService.java    1.0 2012-7-25
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.service;

import java.text.ParseException;
import java.util.List;

import cn.vobile.opm.web.dao.Livemonitor;

/**
 * AlarmPointService.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-25
 * @since 1.0
 */
public interface AlarmPointService {

	/**
	 * update the alarm point
	 * */
	public boolean updateAlarmPoint(Livemonitor livemonitor);

	/**
	 * get all live monitors
	 * 
	 * @return List<Livemonitor>
	 * */
	public List<Livemonitor> getLivemonitors();

	public List<Livemonitor> getUpLivemonitors();

	/**
	 * get ok live monitors
	 * 
	 * @return List<Livemonitor>
	 * @throws ParseException
	 * */
	public List<Livemonitor> getOkLivemonitors() throws ParseException;

	/**
	 * get inactive live monitors
	 * 
	 * @return List<Livemonitor>
	 * @throws ParseException
	 * */
	public List<Livemonitor> getInactiveLivemonitors() throws ParseException;

	/**
	 * get warning live monitors
	 * 
	 * @return List<Livemonitor>
	 * @throws ParseException
	 * */
	public List<Livemonitor> getWarningLivemonitors(List<Livemonitor> list)
			throws ParseException;

	/**
	 * get active up live monitors
	 * 
	 * @return List<Livemonitor>
	 * @throws ParseException
	 * */
	public List<Livemonitor> getActiveUpLivemonitors(List<Livemonitor> list)
			throws ParseException;

	/**
	 * get active up live monitors
	 * 
	 * @return List<Livemonitor>
	 * @throws ParseException
	 * */
	public List<Livemonitor> getActiveDownLivemonitors() throws ParseException;

	/**
	 * get deleted live monitors
	 * 
	 * @return List<Livemonitor>
	 * @throws ParseException
	 * */
	public List<Livemonitor> getDeletedLivemonitors() throws ParseException;
}
