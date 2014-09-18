package cn.vobile.opm.backend.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * Alarmhistory entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.vobile.opm.backend.dao.Alarmhistory
 * @author MyEclipse Persistence Tools
 */

@Repository
public class AlarmHistoryDAO {

	@Resource
	private HibernateTemplate hibernateTemplate;
	private static Logger log = Logger.getLogger(AlarmHistoryDAO.class);
	// property constants
	public static final String LIVEMONITOR_ID = "livemonitorId";
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

	public void save(AlarmHistory transientInstance) {
		log.debug("saving Alarmhistory instance");
		try {
			hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AlarmHistory persistentInstance) {
		log.debug("deleting Alarmhistory instance");
		try {
			hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AlarmHistory findById(java.lang.Integer id) {
		log.debug("getting Alarmhistory instance with id: " + id);
		try {
			AlarmHistory instance = (AlarmHistory) hibernateTemplate.get(
					"cn.vobile.opm.backend.dao.AlarmHistory", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<AlarmHistory> findByExample(AlarmHistory instance) {
		log.debug("finding Alarmhistory instance by example");
		try {
			List<AlarmHistory> results = hibernateTemplate
					.findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<AlarmHistory> findByProperty(String propertyName, Object value) {
		log.debug("finding Alarmhistory instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Alarmhistory as model where model."
					+ propertyName + "= ?";
			return (List<AlarmHistory>) hibernateTemplate.find(queryString,
					value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<AlarmHistory> findByLivemonitorId(Object livemonitorId) {
		return findByProperty(LIVEMONITOR_ID, livemonitorId);
	}

	public List<AlarmHistory> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List<AlarmHistory> findByIsAccepted(Object isAccepted) {
		return findByProperty(IS_ACCEPTED, isAccepted);
	}

	public List<AlarmHistory> findByAcceptedPerson(Object acceptedPerson) {
		return findByProperty(ACCEPTED_PERSON, acceptedPerson);
	}

	public List<AlarmHistory> findByIsAlarmed(Object isAlarmed) {
		return findByProperty(IS_ALARMED, isAlarmed);
	}

	public List<AlarmHistory> findByAlarmedPerson(Object alarmedPerson) {
		return findByProperty(ALARMED_PERSON, alarmedPerson);
	}

	public List<AlarmHistory> findByAlarmedFeedback(Object alarmedFeedback) {
		return findByProperty(ALARMED_FEEDBACK, alarmedFeedback);
	}

	public List<AlarmHistory> findByAlarmedNote(Object alarmedNote) {
		return findByProperty(ALARMED_NOTE, alarmedNote);
	}

	public List<AlarmHistory> findByIsResponsed(Object isResponsed) {
		return findByProperty(IS_RESPONSED, isResponsed);
	}

	public List<AlarmHistory> findByResponsedPerson(Object responsedPerson) {
		return findByProperty(RESPONSED_PERSON, responsedPerson);
	}

	public List<AlarmHistory> findByResponsedHandling(Object responsedHandling) {
		return findByProperty(RESPONSED_HANDLING, responsedHandling);
	}

	public List<AlarmHistory> findByResponsedNote(Object responsedNote) {
		return findByProperty(RESPONSED_NOTE, responsedNote);
	}

	public List<AlarmHistory> findAll() {
		log.debug("finding all Alarmhistory instances");
		try {
			String queryString = "from Alarmhistory";
			return (List<AlarmHistory>) hibernateTemplate.find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AlarmHistory merge(AlarmHistory detachedInstance) {
		log.debug("merging Alarmhistory instance");
		try {
			AlarmHistory result = hibernateTemplate.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AlarmHistory instance) {
		log.debug("attaching dirty Alarmhistory instance");
		try {
			hibernateTemplate.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AlarmHistory instance) {
		log.debug("attaching clean Alarmhistory instance");
		try {
			hibernateTemplate.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

}
