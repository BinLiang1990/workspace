/*
 * @(#)DataSyncMonitor.java    1.0 2012-05-23
 *
 * Copyright �� 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.dbpc.datasyncsys;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.vobile.dbpc.dao.LiveMonitorDAO;
import cn.vobile.dbpc.kvcacher.CacheContainer;

/*
 * Sync data from cache to database.
 */
@Component
public class DataSyncMonitor extends Thread {
	private static Logger logger = Logger.getLogger(DataSyncMonitor.class);
	@Resource
	private CacheContainer nativeCacheContainer;
	@Resource
	private LiveMonitorDAO liveMonitorDAO;

	/**
	 * update the database every 1 minute
	 * */
	@Override
	public void run() {
		// Manually initialize the last update time as 1 minutes ago
		Timestamp lastUpdated = new Timestamp(new Date().getTime() - 60 * 1000L);
		Timestamp startTime, endTime;
		long elapse = 0;

		while (true) {
			// We can wrapper these code to function which can measure the
			// execute time of function
			// Record start time
			startTime = new Timestamp(new Date().getTime());
			// updateStatusDB will update all the record which is newer than
			// lastUpdated,
			// Do not worry about missing data during update because new updated
			// record are newer than now
			// They will be processed safely in next round when we pass current
			// time as lastUpdated
			updateStatusDB(lastUpdated, startTime);
			lastUpdated = startTime;
			// Record the end time
			endTime = new Timestamp(new Date().getTime());

			elapse = (endTime.getTime() - startTime.getTime()) / 1000;

			logger.error("Syncing data costs " + elapse + "seconds");

			if (elapse >= 60) {
				// If the process time is longer than 1 minutes, do not need to
				// wait anymore
				logger.warn("Syncing data costs more then 1 minutes !");
			} else {
				try {
					Thread.sleep((60 - elapse) * 1000L);
				} catch (InterruptedException e) {
					logger.error(e.getMessage());
				}
			}
		}
	}

	/**
	 * update data in database , this is called by Monitor and run in phrase
	 */
	private void updateStatusDB(Timestamp lastUpdated, Timestamp currentTime) {
		this.liveMonitorDAO.updateStatus(this.nativeCacheContainer.items(),
				lastUpdated, currentTime);
	}

	public CacheContainer getCacheContainer() {
		return this.nativeCacheContainer;
	}

	public void setCacheContainer(CacheContainer nativeCacheContainer) {
		this.nativeCacheContainer = nativeCacheContainer;
	}

}
