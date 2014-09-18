package cn.vobile.opm.web.shell.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.vobile.opm.web.shell.bean.Opmidc;

/**
 * A data access object (DAO) providing persistence and search support for
 * Opmidc entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.vobile.opm.web.shell.dao.Opmidc
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class OpmidcDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(OpmidcDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String LOCATION = "location";
	public static final String IMAGE = "image";
	public static final String DESCRIPTION = "description";

	protected void initDao() {
		// do nothing
	}

	public void save(Opmidc transientInstance) {
		log.debug("saving Opmidc instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * @param opmidc
	 */
	public void update(Opmidc transientInstance) {
		log.debug("updating Opmidc instance");
		try {
			getHibernateTemplate().update(transientInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public void delete(Opmidc persistentInstance) {
		log.debug("deleting Opmidc instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Opmidc findById(java.lang.Integer id) {
		log.debug("getting Opmidc instance with id: " + id);
		try {
			Opmidc instance = (Opmidc) getHibernateTemplate().get(
					"cn.vobile.opm.web.shell.bean.Opmidc", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Opmidc instance) {
		log.debug("finding Opmidc instance by example");
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
		log.debug("finding Opmidc instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Opmidc as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByLocation(Object location) {
		return findByProperty(LOCATION, location);
	}

	public List findByImage(Object image) {
		return findByProperty(IMAGE, image);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findAll() {
		log.debug("finding all Opmidc instances");
		try {
			String queryString = "from Opmidc";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Opmidc merge(Opmidc detachedInstance) {
		log.debug("merging Opmidc instance");
		try {
			Opmidc result = (Opmidc) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Opmidc instance) {
		log.debug("attaching dirty Opmidc instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Opmidc instance) {
		log.debug("attaching clean Opmidc instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OpmidcDAO getFromApplicationContext(ApplicationContext ctx) {
		return (OpmidcDAO) ctx.getBean("OpmidcDAO");
	}

}
