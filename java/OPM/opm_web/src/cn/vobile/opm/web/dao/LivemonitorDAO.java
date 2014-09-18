package cn.vobile.opm.web.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Livemonitor entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.vobile.opm.web.dao.Livemonitor
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class LivemonitorDAO extends HibernateDaoSupport {
	protected static final Logger log = Logger.getLogger(LivemonitorDAO.class);
	// property constants
	public static final String SERVICE_NAME = "serviceName";
	public static final String SERVER_NAME = "serverName";
	public static final String COMPONENT_NAME = "componentName";
	public static final String STATUS = "status";
	public static final String IS_NEED_ALARM = "isNeedAlarm";
	public static final String SERVER_IPADDRESS = "serverIpaddress";
	public static final String DELAY_TIME = "delayTime";
	public static final String ALARM_LEVEL = "alarmLevel";
	public static final String ALARM_HISTORY_ID = "alarmHistoryId";
	public static final String TYPE = "type";
	public static final String CRITICALITY = "criticality";
	public static final String DESCRIPTION = "description";
	public static final String ALARM_PERSON = "alarmPerson";
	public static final String ALARM_TIME_REGION = "alarmTimeRegion";

	@Override
	protected void initDao() {
		// do nothing
	}

	public void save(Livemonitor transientInstance) {
		log.debug("saving Livemonitor instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Livemonitor persistentInstance) {
		log.debug("deleting Livemonitor instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Livemonitor findById(java.lang.Integer id) {
		log.debug("getting Livemonitor instance with id: " + id);
		try {
			Livemonitor instance = (Livemonitor) getHibernateTemplate().get(
					"cn.vobile.opm.web.dao.Livemonitor", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Livemonitor instance) {
		log.debug("finding Livemonitor instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Livemonitor instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Livemonitor as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByServiceName(Object serviceName) {
		return findByProperty(SERVICE_NAME, serviceName);
	}

	public List findByServerName(Object serverName) {
		return findByProperty(SERVER_NAME, serverName);
	}

	public List findByComponentName(Object componentName) {
		return findByProperty(COMPONENT_NAME, componentName);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findByIsNeedAlarm(Object isNeedAlarm) {
		return findByProperty(IS_NEED_ALARM, isNeedAlarm);
	}

	public List findByServerIpaddress(Object serverIpaddress) {
		return findByProperty(SERVER_IPADDRESS, serverIpaddress);
	}

	public List findByDelayTime(Object delayTime) {
		return findByProperty(DELAY_TIME, delayTime);
	}

	public List findByAlarmLevel(Object alarmLevel) {
		return findByProperty(ALARM_LEVEL, alarmLevel);
	}

	public List findByAlarmHistoryId(Object alarmHistoryId) {
		return findByProperty(ALARM_HISTORY_ID, alarmHistoryId);
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findByCriticality(Object criticality) {
		return findByProperty(CRITICALITY, criticality);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByAlarmPerson(Object alarmPerson) {
		return findByProperty(ALARM_PERSON, alarmPerson);
	}

	public List findByAlarmTimeRegion(Object alarmTimeRegion) {
		return findByProperty(ALARM_TIME_REGION, alarmTimeRegion);
	}

	public List<Livemonitor> findAll() {
		log.debug("finding all Livemonitor instances");
		try {
			String queryString = "from Livemonitor";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Livemonitor merge(Livemonitor detachedInstance) {
		log.debug("merging Livemonitor instance");
		try {
			Livemonitor result = getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Livemonitor instance) {
		log.debug("attaching dirty Livemonitor instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Livemonitor instance) {
		log.debug("attaching clean Livemonitor instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LivemonitorDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (LivemonitorDAO) ctx.getBean("LivemonitorDAO");
	}

	public void update(Livemonitor transientInstance) {
		log.debug("updating Livemonitor instance");
		try {
			getHibernateTemplate().update(transientInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			re.printStackTrace();
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Livemonitor> findAllInHistory() {
		return getHibernateTemplate().find(
				"from Livemonitor where latestHistoryId is not null");
	}

	public List<String> findGroupByServiceName() {
		@SuppressWarnings("unchecked")
		List<String> list = getHibernateTemplate().find(
				"select distinct name from AlarmSpot");
		for (int i = 0; i < list.size(); ++i) {
			list.set(i, list.get(i).split("\\.")[0]);
		}
		List<String> distinctList = new ArrayList<String>();
		for (String string : list) {
			if (!distinctList.contains(string)) {
				distinctList.add(string);
			}
		}
		return distinctList;
	}
}
