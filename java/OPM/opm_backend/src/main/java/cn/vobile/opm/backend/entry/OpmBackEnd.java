/*
 * @(#)OpmEnd.java    1.0 2012-7-24
 *
 * Copyright �� 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.backend.entry;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.vobile.opm.backend.common.InfoUtil;
import cn.vobile.opm.backend.dao.AlarmHistory;
import cn.vobile.opm.backend.dao.WorkRecord;
import cn.vobile.opm.backend.logic.layer.AlarmPointChecker;
import cn.vobile.opm.backend.logic.layer.HistoryInserter;

/**
 * OpmEnd.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-24
 * @since 1.0
 */
public class OpmBackEnd {
	private static final Logger logger = Logger.getLogger(OpmBackEnd.class);

	private AlarmPointChecker alarmPointChecker;
	private HistoryInserter historyInserter;
	private InfoUtil util;
	private ClassPathXmlApplicationContext context;

	/**
	 *
	 */
	public OpmBackEnd() {
		this.context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		this.historyInserter = (HistoryInserter) context
				.getBean("historyInserter");
		this.alarmPointChecker = (AlarmPointChecker) context
				.getBean("alarmPointChecker");
		this.util = (InfoUtil) context.getBean("infoUtil");
	}

	public void sendHeartBeatToDBPC() {
		try {
			Socket socket = new Socket(util.getDbpcHost(), util.getDbpcPort());
			JSONObject status = new JSONObject();
			status.put("service", util.getService());
			status.put("component", util.getComponent());
			StringBuilder sb = new StringBuilder("{\"status\":[");
			sb.append(status.toString());
			sb.append("]}");
			String json = sb.toString();
			PrintStream ps = new PrintStream(socket.getOutputStream());
			ps.print(json);
			ps.flush();
			ps.close();
		} catch (UnknownHostException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (JSONException e) {
			logger.error(e.getMessage());
		}
	}

	public void start() {
		sendHeartBeatToDBPC();
		WorkRecord workRecord = historyInserter.getWorkRecord();
		boolean firstWork = (workRecord == null);
		boolean stillWork = false;
		boolean theOtherOneIsDead = false;
		if (!firstWork) {
			stillWork = (workRecord.getBackendId().equals(util.getBackendId()));
			theOtherOneIsDead = (!workRecord.getBackendId().equals(
					util.getBackendId()) && System.currentTimeMillis()
					- workRecord.getStartTime().getTime() > util.getTimeout() * 60 * 1000);
		}
		if (!(firstWork || stillWork || theOtherOneIsDead)) {
			return;
		}

		logger.info("### OPM BackEnd starts ###");
		if (workRecord == null) {
			workRecord = new WorkRecord();
		}
		workRecord.setStartTime(new Timestamp(System.currentTimeMillis()));
		workRecord.setBackendId(util.getBackendId());
		try {
			List<AlarmHistory> histories = alarmPointChecker.checkAlarmPoint();
			historyInserter.insertHistories(histories);
		} catch (ParseException e) {
			logger.error(e.getMessage());
		} catch (InstantiationException e) {
			logger.error(e.getMessage());
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		}
		workRecord.setEndTime(new Timestamp(System.currentTimeMillis()));
		historyInserter.saveOrUpdateWorkRecord(workRecord);
		context.close();
		logger.info("### OPM BackEnd stops ###");
	}

	public static void main(String[] args) {
		OpmBackEnd opmEnd = new OpmBackEnd();
		opmEnd.start();
	}
}
