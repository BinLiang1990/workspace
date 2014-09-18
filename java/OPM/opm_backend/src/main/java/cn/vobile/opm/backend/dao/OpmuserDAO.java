package cn.vobile.opm.backend.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * Opmuser entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.vobile.opm.web.bean.Opmuser
 * @author MyEclipse Persistence Tools
 */
@Repository
public class OpmuserDAO {

	@Resource
	private HibernateTemplate hibernateTemplate;
	private static final Logger log = Logger.getLogger(OpmuserDAO.class);
	// property constants
	public static final String USERID = "userid";
	public static final String PASSWD = "passwd";
	public static final String IS_ENABLED = "isEnabled";
	public static final String USERGROUP = "usergroup";
	public static final String USERNAME = "username";
	public static final String PHONE = "phone";
	public static final String NOTE = "note";

	public void save(Opmuser transientInstance) {
		log.debug("saving Opmuser instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * @param opmuserInDb
	 */
	public void update(Opmuser transientInstance) {
		log.debug("updating Opmuser instance");
		try {
			hibernateTemplate.update(transientInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public void delete(Opmuser persistentInstance) {
		log.debug("deleting Opmuser instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Opmuser findById(java.lang.Integer id) {
		log.debug("getting Opmuser instance with id: " + id);
		try {
			Opmuser instance = (Opmuser) hibernateTemplate.get(
					"cn.vobile.opm.backend.dao.Opmuser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Opmuser> findByExample(Opmuser instance) {
		log.debug("finding Opmuser instance by example");
		try {
			List<Opmuser> results = hibernateTemplate.findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<Opmuser> findByProperty(String propertyName, Object value) {
		log.debug("finding Opmuser instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Opmuser as model where model."
					+ propertyName + "= ?";
			return (List<Opmuser>) hibernateTemplate.find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Opmuser> findByUserid(Object userid) {
		return findByProperty(USERID, userid);
	}

	public List<Opmuser> findByPasswd(Object passwd) {
		return findByProperty(PASSWD, passwd);
	}

	public List<Opmuser> findByIsEnabled(Object isEnabled) {
		return findByProperty(IS_ENABLED, isEnabled);
	}

	public List<Opmuser> findByUsergroup(Object usergroup) {
		return findByProperty(USERGROUP, usergroup);
	}

	public List<Opmuser> findByUsername(Object username) {
		return findByProperty(USERNAME, username);
	}

	public List<Opmuser> findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List<Opmuser> findByNote(Object note) {
		return findByProperty(NOTE, note);
	}

	public List<Opmuser> findAll() {
		log.debug("finding all Opmuser instances");
		try {
			String queryString = "from Opmuser";
			return (List<Opmuser>) hibernateTemplate.find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Opmuser merge(Opmuser detachedInstance) {
		log.debug("merging Opmuser instance");
		try {
			Opmuser result = hibernateTemplate.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Opmuser instance) {
		log.debug("attaching dirty Opmuser instance");
		try {
			hibernateTemplate.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Opmuser instance) {
		log.debug("attaching clean Opmuser instance");
		try {
			hibernateTemplate.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

}
