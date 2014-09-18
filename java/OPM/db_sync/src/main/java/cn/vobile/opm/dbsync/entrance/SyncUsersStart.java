package cn.vobile.opm.dbsync.entrance;

import java.util.Date;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.vobile.opm.dbsync.dao.LivemonitorDAO;

public class SyncUsersStart {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		LivemonitorDAO livemonitorDAO = applicationContext.getBean(
				"livemonitorDAO", LivemonitorDAO.class);
		List<Integer> ids = livemonitorDAO.selectAllId();
		System.out.println(new Date());
		for (int i : ids) {
			livemonitorDAO.computeFlash(livemonitorDAO.getAlarmHistories(i));
		}
		System.out.println(new Date());
		applicationContext.close();
	}
}
