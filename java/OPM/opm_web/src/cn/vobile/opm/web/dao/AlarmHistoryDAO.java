package cn.vobile.opm.web.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * AlarmHistory entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.vobile.opm.web.bean.AlarmHistory
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class AlarmHistoryDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(AlarmHistoryDAO.class);
	// property constants
	public static final String LIVEMONITOR_ID = "livemonitor.id";
	public static final String STATUS = "status";
	public static final String IS_ACCEPTED = "isAccepted";
	public static final String ACCEPTED_PERSON = "acceptedPerson";
	public static final String IS_ALARMED = "isAlarmed";
	public static final String ALARMED_PERSON = "alarmedPerson";
	public static final String ALARMED_FEEDBACK = "alarmedFeedback";
	public static final String ALARMED_NOTE = "alarmedNote";
	public static final String IS_RESPONSED = "isResponsed";
	public static final String RESPONSED_PERSON = "responsedPerson";
	public static final String RESPONSED_HANDLING = "responsedHandling";
	public static final String RESPONSED_NOTE = "responsedNote";

	@Override
	protected void initDao() {
		// do nothing
	}

	public void save(AlarmHistory transientInstance) {
		log.debug("saving AlarmHistory instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * @param historyIndb
	 */
	public void update(AlarmHistory transientInstance) {
		log.debug("updating AlarmHistory instance");
		try {
			getHibernateTemplate().update(transientInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public void delete(AlarmHistory persistentInstance) {
		log.debug("deleting AlarmHistory instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AlarmHistory findById(java.lang.Integer id) {
		log.debug("getting AlarmHistory instance with id: " + id);
		try {
			AlarmHistory instance = (AlarmHistory) getHibernateTemplate().get(
					"cn.vobile.opm.web.dao.AlarmHistory", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AlarmHistory instance) {
		log.debug("finding AlarmHistory instance by example");
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
		log.debug("finding AlarmHistory instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from AlarmHistory as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByLivemonitorId(Object livemonitorId) {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AlarmHistory.class);
		detachedCriteria.createAlias("livemonitor", "l");
		detachedCriteria.add(Restrictions.eq(LIVEMONITOR_ID, livemonitorId));
		return getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findByIsAccepted(Object isAccepted) {
		return findByProperty(IS_ACCEPTED, isAccepted);
	}

	public List findByAcceptedPerson(Object acceptedPerson) {
		return findByProperty(ACCEPTED_PERSON, acceptedPerson);
	}

	public List findByIsAlarmed(Object isAlarmed) {
		return findByProperty(IS_ALARMED, isAlarmed);
	}

	public List findByAlarmedPerson(Object alarmedPerson) {
		return findByProperty(ALARMED_PERSON, alarmedPerson);
	}

	public List findByAlarmedFeedback(Object alarmedFeedback) {
		return findByProperty(ALARMED_FEEDBACK, alarmedFeedback);
	}

	public List findByAlarmedNote(Object alarmedNote) {
		return findByProperty(ALARMED_NOTE, alarmedNote);
	}

	public List findByIsResponsed(Object isResponsed) {
		return findByProperty(IS_RESPONSED, isResponsed);
	}

	public List findByResponsedPerson(Object responsedPerson) {
		return findByProperty(RESPONSED_PERSON, responsedPerson);
	}

	public List findByResponsedHandling(Object responsedHandling) {
		return findByProperty(RESPONSED_HANDLING, responsedHandling);
	}

	public List findByResponsedNote(Object responsedNote) {
		return findByProperty(RESPONSED_NOTE, responsedNote);
	}

	public List findAll() {
		log.debug("finding all AlarmHistory instances");
		try {
			String queryString = "from AlarmHistory";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AlarmHistory merge(AlarmHistory detachedInstance) {
		log.debug("merging AlarmHistory instance");
		try {
			AlarmHistory result = getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AlarmHistory instance) {
		log.debug("attaching dirty AlarmHistory instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AlarmHistory instance) {
		log.debug("attaching clean AlarmHistory instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AlarmHistoryDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AlarmHistoryDAO) ctx.getBean("AlarmHistoryDAO");
	}

	/**
	 * @param livemonitorId
	 * @param startTime
	 * @param endTime
	 * @param serviceName
	 * @param type
	 * @param alarmLevel
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AlarmHistory> findExceptedHistories(int livemonitorId,
			Timestamp startTime, Timestamp endTime, String serviceName,
			String type, String alarmLevel) {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AlarmHistory.class);
		detachedCriteria.createAlias("livemonitor", "l");
		detachedCriteria.add(Restrictions.eq("l.id", livemonitorId));
		detachedCriteria.add(Restrictions.between("createdAt", startTime,
				endTime));
		detachedCriteria.addOrder(Order.asc("l.id"));
		detachedCriteria.addOrder(Order.asc("createdAt"));
		List<AlarmHistory> list = new ArrayList<AlarmHistory>();
		list = getHibernateTemplate().findByCriteria(detachedCriteria);
		Iterator<AlarmHistory> iterator = list.iterator();
		while (iterator.hasNext()) {
			AlarmHistory alarmHistory = iterator.next();
			if (!serviceName.equals("")
					&& !serviceName.equals(alarmHistory.getLivemonitor()
							.getAlarmSpot().getName().split("\\.")[0])) {
				iterator.remove();
				continue;
			}
			if (!type.equals("")
					&& !type.equals(alarmHistory.getLivemonitor()
							.getAlarmSpot().getType())) {
				iterator.remove();
				continue;
			}
			if (!alarmLevel.equals("")
					&& !alarmLevel.equals(alarmHistory.getLivemonitor()
							.getAlarmSpot().getLevel())) {
				iterator.remove();
				continue;
			}
		}
		return list;
	}

}
