/*
 * @(#)DBPCStart.java    1.0 2012-05-16
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.dbpc.entr;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.vobile.dbpc.datasyncsys.DataSyncMonitor;
import cn.vobile.dbpc.receiver.MessageReceiver;

/**
 * DBPCStart.java <BR>
 * the main entrance <BR>
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version
 * @since 2012-05-16
 */
public class DBPCStart {

	private static Logger logger = Logger.getLogger(DBPCStart.class);

	/**
	 * the entrance
	 * */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		logger.info("DBPC starts working!");
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MessageReceiver messageReceiver = applicationContext.getBean(
				"messageReceiver", MessageReceiver.class);
		DataSyncMonitor dataSyncMonitor = applicationContext.getBean(
				"dataSyncMonitor", DataSyncMonitor.class);
		messageReceiver.start();
		dataSyncMonitor.start();
	}
}
