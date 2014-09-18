///*
// * @(#)ExtLivemonitorDAO.java    1.0 2012-12-29
// *
// * Copyright © 2012 Vobile, Inc. All rights reserved.
// */
//package cn.vobile.opm.web.extdao;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.collections.map.HashedMap;
//import org.hibernate.criterion.DetachedCriteria;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Projections;
//import org.hibernate.criterion.Restrictions;
//
//import cn.vobile.opm.web.bean.Livemonitor;
//import cn.vobile.opm.web.dao.LivemonitorDAO;
//
///**
// * ExtLivemonitorDAO.java
// * 
// * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
// * @version 1.0 2012-12-29
// * @since 1.0
// */
//@SuppressWarnings({ "rawtypes", "unchecked" })
//public class ExtLivemonitorDAO extends LivemonitorDAO {
//	/**
//	 *
//	 * */
//	public void update(Livemonitor transientInstance) {
//		log.debug("updating Livemonitor instance");
//		try {
//			getHibernateTemplate().update(transientInstance);
//			log.debug("update successful");
//		} catch (RuntimeException re) {
//			log.error("update failed", re);
//			System.out.println(transientInstance.getType());
//			re.printStackTrace();
//			System.out.println(re.getMessage());
//			throw re;
//		}
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see cn.vobile.opm.web.dao.LivemonitorDAO#findById(java.lang.Integer)
//	 */
//	@Override
//	public Livemonitor findById(Integer id) {
//		log.debug("getting Livemonitor instance with id: " + id);
//		try {
//			Livemonitor instance = (Livemonitor) getHibernateTemplate().get(
//					"cn.vobile.opm.web.bean.Livemonitor", id);
//			return instance;
//		} catch (RuntimeException re) {
//			log.error("get failed", re);
//			throw re;
//		}
//	}
//
//	/**
//	 * @param serviceName
//	 * @param serverIpaddress
//	 * @return
//	 */
//	public List<Livemonitor> findByServiceNameAndIPJoinHistory(
//			String serviceName, String serverIpaddress) {
//
//		DetachedCriteria detachedCriteria = DetachedCriteria
//				.forClass(Livemonitor.class);
//
//		detachedCriteria.add(Restrictions.eq(SERVICE_NAME, serviceName))
//				.add(Restrictions.eq(SERVER_IPADDRESS, serverIpaddress))
//				.createAlias("history", "h");
//
//		return getHibernateTemplate().findByCriteria(detachedCriteria);
//
//	}
//
//	/**
//	 * @param serviceName
//	 * @param serverIpaddress
//	 * @return
//	 */
//	public List<Livemonitor> findByServiceNameAndIPJoinHistoryNoDeleted(
//			String serviceName, String serverIpaddress) {
//
//		DetachedCriteria detachedCriteria = DetachedCriteria
//				.forClass(Livemonitor.class);
//
//		detachedCriteria.add(Restrictions.eq(SERVICE_NAME, serviceName))
//				.add(Restrictions.eq(SERVER_IPADDRESS, serverIpaddress))
//				.add(Restrictions.ne(ALARM_LEVEL, "deleted"))
//				.createAlias("history", "h");
//
//		return getHibernateTemplate().findByCriteria(detachedCriteria);
//
//	}
//
//	/**
//	 * @return
//	 */
//	public List<Livemonitor> findAllInHistory() {
//		DetachedCriteria detachedCriteria = DetachedCriteria
//				.forClass(Livemonitor.class);
//		detachedCriteria.createAlias("history", "h");
//		detachedCriteria.addOrder(Order.desc("lastUpdateTime"));
//
//		return getHibernateTemplate().findByCriteria(detachedCriteria);
//	}
//
//	/**
//	 * @return
//	 */
//	public List<Livemonitor> findAllInHistoryWithoutDeleted() {
//		DetachedCriteria detachedCriteria = DetachedCriteria
//				.forClass(Livemonitor.class);
//		detachedCriteria.add(Restrictions.ne(ALARM_LEVEL, "deleted"));
//		detachedCriteria.createAlias("history", "h");
//		detachedCriteria.addOrder(Order.desc("lastUpdateTime"));
//
//		return getHibernateTemplate().findByCriteria(detachedCriteria);
//	}
//
//	/**
//	 * @return
//	 */
//
//	public List<Livemonitor> findAllInHistoryByStatus(String status) {
//		DetachedCriteria detachedCriteria = DetachedCriteria
//				.forClass(Livemonitor.class);
//		detachedCriteria.createAlias("history", "h").add(
//				Restrictions.eq("h.status", status));
//		return getHibernateTemplate().findByCriteria(detachedCriteria);
//	}
//
//	/**
//	 *
//	 * */
//	public Map findServiceNameToIpMap() {
//		Map serviceNameToIpMap = new HashedMap();
//
//		DetachedCriteria detachedCriteria = DetachedCriteria
//				.forClass(Livemonitor.class);
//
//		detachedCriteria.setProjection(Projections.projectionList().add(
//				Projections.groupProperty(SERVICE_NAME)));
//
//		List serviceNames = getHibernateTemplate().findByCriteria(
//				detachedCriteria);
//
//		for (Object serviceName : serviceNames) {
//			List ipsList = new ArrayList<String>();
//
//			List resultList = findByProperty(SERVICE_NAME, serviceName);
//			for (Object object : resultList) {
//				String ip = ((Livemonitor) object).getServerName() + "/"
//						+ ((Livemonitor) object).getServerIpaddress();
//				ipsList.add(ip);
//			}
//			serviceNameToIpMap.put(serviceName, ipsList);
//		}
//
//		return serviceNameToIpMap;
//	}
//
//	/**
//	 *
//	 * */
//	public List findGroupByServiceNameAndIp() {
//
//		DetachedCriteria detachedCriteria = DetachedCriteria
//				.forClass(Livemonitor.class);
//
//		detachedCriteria.setProjection(Projections.projectionList()
//				.add(Projections.groupProperty(SERVICE_NAME))
//				.add(Projections.groupProperty(SERVER_IPADDRESS)));
//
//		// System.out.println(Projections.projectionList()
//		// .add(Projections.groupProperty(SERVICE_NAME))
//		// .add(Projections.groupProperty(SERVER_IPADDRESS)).toString());
//		return getHibernateTemplate().findByCriteria(detachedCriteria);
//		// return null;
//	}
//
//	/**
//	 * a test fucntion
//	 * */
//	public List findJoin() {
//		DetachedCriteria detachedCriteria = DetachedCriteria
//				.forClass(Livemonitor.class);
//
//		detachedCriteria.add(Restrictions.eq("id", 87));
//
//		return getHibernateTemplate().findByCriteria(detachedCriteria);
//	}
//
//	/**
//	 * @param string
//	 * @return
//	 */
//	public List<Livemonitor> findByAlarmLevelOrderByIP(String alarmLevel) {
//		DetachedCriteria detachedCriteria = DetachedCriteria
//				.forClass(Livemonitor.class);
//		detachedCriteria.add(Restrictions.eq(ALARM_LEVEL, alarmLevel))
//				.addOrder(Order.asc(SERVER_IPADDRESS));
//
//		return getHibernateTemplate().findByCriteria(detachedCriteria);
//	}
//
//	/**
//	 *
//	 * */
//	public List findGroupByServiceName() {
//		DetachedCriteria detachedCriteria = DetachedCriteria
//				.forClass(Livemonitor.class);
//		detachedCriteria.setProjection(Projections.projectionList().add(
//				Projections.groupProperty(SERVICE_NAME)));
//
//		return getHibernateTemplate().findByCriteria(detachedCriteria);
//	}
// }
