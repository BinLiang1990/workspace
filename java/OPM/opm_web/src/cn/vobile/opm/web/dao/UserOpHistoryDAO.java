package cn.vobile.opm.web.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.vobile.opm.web.bean.UserOpHistory;

/**
 * A data access object (DAO) providing persistence and search support for
 * UserOpHistory entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.vobile.opm.web.bean.UserOpHistory
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class UserOpHistoryDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(UserOpHistoryDAO.class);
	// property constants
	public static final String OPMUSER_ID = "opmuserId";
	public static final String OPERATION = "operation";
	public static final String LIVEMONITOR_ID = "livemonitorId";
	public static final String ORIGINAL_VALUE = "originalValue";
	public static final String CHANGE_VALUE = "changeValue";

	protected void initDao() {
		// do nothing
	}

	public void save(UserOpHistory transientInstance) {
		log.debug("saving UserOpHistory instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UserOpHistory persistentInstance) {
		log.debug("deleting UserOpHistory instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserOpHistory findById(java.lang.Integer id) {
		log.debug("getting UserOpHistory instance with id: " + id);
		try {
			UserOpHistory instance = (UserOpHistory) getHibernateTemplate()
					.get("cn.vobile.opm.web.bean.UserOpHistory", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(UserOpHistory instance) {
		log.debug("finding UserOpHistory instance by example");
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
		log.debug("finding UserOpHistory instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from UserOpHistory as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByOpmuserId(Object opmuserId) {
		return findByProperty(OPMUSER_ID, opmuserId);
	}

	public List findByOperation(Object operation) {
		return findByProperty(OPERATION, operation);
	}

	public List findByLivemonitorId(Object livemonitorId) {
		return findByProperty(LIVEMONITOR_ID, livemonitorId);
	}

	public List findByOriginalValue(Object originalValue) {
		return findByProperty(ORIGINAL_VALUE, originalValue);
	}

	public List findByChangeValue(Object changeValue) {
		return findByProperty(CHANGE_VALUE, changeValue);
	}

	public List findAll() {
		log.debug("finding all UserOpHistory instances");
		try {
			String queryString = "from UserOpHistory";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UserOpHistory merge(UserOpHistory detachedInstance) {
		log.debug("merging UserOpHistory instance");
		try {
			UserOpHistory result = (UserOpHistory) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserOpHistory instance) {
		log.debug("attaching dirty UserOpHistory instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserOpHistory instance) {
		log.debug("attaching clean UserOpHistory instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserOpHistoryDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (UserOpHistoryDAO) ctx.getBean("UserOpHistoryDAO");
	}

	/**
	 * @param startTime
	 * @param endTime
	 * @param userName
	 * @param operation2
	 * @param livemonitorId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserOpHistory> findExcptedOphistory(Timestamp startTime,
			Timestamp endTime, String userName, String operation,
			String livemonitorId) {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(UserOpHistory.class);
		detachedCriteria.createAlias("livemonitor", "l");
		detachedCriteria.createAlias("opmuser", "u");

		detachedCriteria.add(Restrictions.between("createdAt", startTime,
				endTime));

		if (!userName.equals("")) {
			detachedCriteria.add(Restrictions.eq("u.username", userName));
		}
		if (!operation.equals("")) {
			detachedCriteria.add(Restrictions.eq(OPERATION, operation));
		}
		if (!livemonitorId.equals("")) {
			detachedCriteria.add(Restrictions.eq("l.id",
					Integer.parseInt(livemonitorId)));
		}

		detachedCriteria.addOrder(Order.desc("createdAt"));

		return getHibernateTemplate().findByCriteria(detachedCriteria);
	}
}
