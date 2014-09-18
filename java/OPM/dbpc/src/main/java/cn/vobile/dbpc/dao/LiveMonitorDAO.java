/*
 * @(#)DAODB3.java    1.0 2012-05-16
 *
 * Copyright �� 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.dbpc.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.vobile.dbpc.bean.MonitorBean;

/*
 * Sync alarm message to db
 */
@Repository
public class LiveMonitorDAO {

	private static Logger logger = Logger.getLogger(LiveMonitorDAO.class);
	@Resource
	private JdbcTemplate jdbcTemplate;
	private static final String FINDALARMSPOTID = "select id from "
			+ "alarm_spot where name = ?";
	private static final String COUNTALARMSPOTID = "select count(1) from "
			+ "alarm_spot where name = ?";
	private static final String UPDATELIVEMONITOR = "update livemonitor "
			+ "set updated_at = ?, is_deleted = 0 where alarm_spot_id = ? and ip_address = ?";
	private static final String INSERTLIVEMONITOR = "insert livemonitor"
			+ "(alarm_spot_id, created_at, updated_at, ip_address) values(?, ?, ?, ?)";
	private static final String INSERTALARMSPOT = "insert alarm_spot(name,owner) values(?, ?)";

	public boolean saveOrUpdate(MonitorBean mb) throws DataAccessException {
		String service = mb.getService();
		String component = mb.getComponent();
		String ip = mb.getIpaddress();
		String version = mb.getVersion();
		String alarmSpotName = service + "." + component;
		if (jdbcTemplate.queryForObject(COUNTALARMSPOTID,
				new Object[] { alarmSpotName }, Integer.class) == 0) {
			if ("1".equals(version)) {
				return false;
			}
			jdbcTemplate.update(INSERTALARMSPOT, new Object[] { alarmSpotName,
					"" });
		}
		Integer alarmSpotId = jdbcTemplate.queryForObject(FINDALARMSPOTID,
				new Object[] { alarmSpotName }, Integer.class);
		if (jdbcTemplate.update(UPDATELIVEMONITOR, new Object[] {
				new Timestamp(System.currentTimeMillis()), alarmSpotId, ip }) == 0) {
			jdbcTemplate.update(INSERTLIVEMONITOR, new Object[] { alarmSpotId,
					new Timestamp(System.currentTimeMillis()),
					new Timestamp(System.currentTimeMillis()), ip });
		}
		return true;
	}

	/**
	 * update the table livemonitor <BR>
	 * livemonitor (server_name,server_ipaddress, service_name,
	 * component_name,last_update_time) <BR>
	 * 
	 * @throws SQLException
	 * */
	public void updateStatus(ConcurrentHashMap<String, MonitorBean> items,
			Timestamp lastUpdated, Timestamp current) {
		Iterator<Entry<String, MonitorBean>> iterator = items.entrySet()
				.iterator();
		long elapse = 0;
		int itemsSyncOK = 0;
		int itemsExpired = 0;
		int itemsSyncFailed = 0;
		int itemsUnknownName = 0;
		int itemsSum = items.size();
		while (iterator.hasNext()) {
			Entry<String, MonitorBean> entry = iterator.next();
			String key = entry.getKey();
			MonitorBean mb = entry.getValue();
			Timestamp timestamp = new Timestamp(mb.getUpdateAt().getTime());
			elapse = timestamp.getTime() - lastUpdated.getTime();
			if (elapse > 0) {
				String serviceName = mb.getService();
				String componentName = mb.getComponent();
				String ip = mb.getIpaddress();
				logger.debug("Update to database:" + " " + mb.getService()
						+ " " + mb.getComponent() + " " + mb.getIpaddress());
				// Sync the data every 1 minutes
				try {
					if (saveOrUpdate(mb)) {
						++itemsSyncOK;
					} else {
						++itemsUnknownName;
						items.remove(key);
						logger.error(ip + " " + serviceName + " "
								+ componentName);
					}
				} catch (DataAccessException e) {
					++itemsSyncFailed;
					logger.error(e.getMessage() + " " + ip + " " + serviceName
							+ " " + componentName);
				}
			} else if (elapse / 1000 < -3600) {
				/*
				 * Remove it in cache when it is not active more than 1 hours
				 * since last update
				 */
				items.remove(key);
				++itemsExpired;
			}
		}
		logger.error("Syncing result:itemsSum, " + itemsSum + "/itemsSyncOK, "
				+ itemsSyncOK + "/itemsExpired, " + itemsExpired
				+ "/itemsSyncFailed, " + itemsSyncFailed
				+ "/itemsUnknownName, " + itemsUnknownName);
	}
}
