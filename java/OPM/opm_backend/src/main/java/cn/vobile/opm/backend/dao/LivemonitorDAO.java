package cn.vobile.opm.backend.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * Livemonitor entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.vobile.opm.backend.dao.Livemonitor
 * @author MyEclipse Persistence Tools
 */
@Repository
public class LivemonitorDAO {

	@Resource
	private HibernateTemplate hibernateTemplate;

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

	public void save(Livemonitor transientInstance) {
		log.debug("saving Livemonitor instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successf ul");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Livemonitor persistentInstance) {
		log.debug("deleting Livemonitor instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Livemonitor findById(java.lang.Integer id) {
		log.debug("getting Livemonitor instance with id: " + id);
		try {
			Livemonitor instance = (Livemonitor) hibernateTemplate.get(
					"cn.vobile.opm.backend.dao.Livemonitor", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Livemonitor> findByExample(Livemonitor instance) {
		log.debug("finding Livemonitor instance by example");
		try {
			List<Livemonitor> results = hibernateTemplate
					.findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<Livemonitor> findByProperty(String propertyName, Object value) {
		log.debug("finding Livemonitor instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Livemonitor as model where model."
					+ propertyName + "= ?";
			return (List<Livemonitor>) hibernateTemplate.find(queryString,
					value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Livemonitor> findByServiceName(Object serviceName) {
		return findByProperty(SERVICE_NAME, serviceName);
	}

	public List<Livemonitor> findByServerName(Object serverName) {
		return findByProperty(SERVER_NAME, serverName);
	}

	public List<Livemonitor> findByComponentName(Object componentName) {
		return findByProperty(COMPONENT_NAME, componentName);
	}

	public List<Livemonitor> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List<Livemonitor> findByIsNeedAlarm(Object isNeedAlarm) {
		return findByProperty(IS_NEED_ALARM, isNeedAlarm);
	}

	public List<Livemonitor> findByServerIpaddress(Object serverIpaddress) {
		return findByProperty(SERVER_IPADDRESS, serverIpaddress);
	}

	public List<Livemonitor> findByDelayTime(Object delayTime) {
		return findByProperty(DELAY_TIME, delayTime);
	}

	public List<Livemonitor> findByAlarmLevel(Object alarmLevel) {
		return findByProperty(ALARM_LEVEL, alarmLevel);
	}

	public List<Livemonitor> findByAlarmHistoryId(Object alarmHistoryId) {
		return findByProperty(ALARM_HISTORY_ID, alarmHistoryId);
	}

	public List<Livemonitor> findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List<Livemonitor> findByCriticality(Object criticality) {
		return findByProperty(CRITICALITY, criticality);
	}

	public List<Livemonitor> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<Livemonitor> findByAlarmPerson(Object alarmPerson) {
		return findByProperty(ALARM_PERSON, alarmPerson);
	}

	public List<Livemonitor> findByAlarmTimeRegion(Object alarmTimeRegion) {
		return findByProperty(ALARM_TIME_REGION, alarmTimeRegion);
	}

	public List<Livemonitor> findAll() {
		try {
			String queryString = "from Livemonitor";
			return (List<Livemonitor>) hibernateTemplate.find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Livemonitor merge(Livemonitor detachedInstance) {
		log.debug("merging Livemonitor instance");
		try {
			Livemonitor result = hibernateTemplate.merge(detachedInstance);
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
			hibernateTemplate.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Livemonitor instance) {
		log.debug("attaching clean Livemonitor instance");
		try {
			hibernateTemplate.lock(instance, LockMode.NONE);
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

	/**
	 * @param livemonitor
	 */
	public void update(Livemonitor transientInstance) {
		log.debug("updating Livemonitor instance");
		try {
			hibernateTemplate.update(transientInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	/**
	 * @param id
	 */
	public List<Livemonitor> findLivemonitorWithHistory(int id) {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(Livemonitor.class);
		detachedCriteria.createAlias("alarmHistory", "ah").add(
				Restrictions.idEq(id));
		return (List<Livemonitor>) hibernateTemplate
				.findByCriteria(detachedCriteria);
	}

}
