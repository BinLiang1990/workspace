package cn.vobile.opm.web.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class SearchConditionsDAO extends HibernateDaoSupport {

	protected static final Logger log = LoggerFactory
			.getLogger(SearchConditionsDAO.class);
	public static final String OPMUSER_ID = "opmUserId";
	public static final String SERVICE_NAME = "serviceName";
	public static final String SERVER_NAME = "serverName";
	public static final String COMPONENT_NAME = "componentName";
	public static final String STATUS = "status";
	public static final String DELAY_TIME = "delayTime";
	public static final String ALARM_LEVEL = "alarmLevel";
	public static final String ALARM_TIME_REGION = "alarmTimeRegion";
	public static final String TAG = "tag";

	public void save(SearchConditions transientInstance) {
		log.debug("saving SearchConditions instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@SuppressWarnings("rawtypes")
	public List getTagList(String opmUserId) {
		List<SearchConditions> list = findByOpmUserId(opmUserId);
		List<String> tagList = new ArrayList<String>();
		for (int i = 0; i < list.size(); ++i) {
			tagList.add(list.get(i).getTag());
		}
		return tagList;
	}

	public void delete(SearchConditions persistentInstance) {
		log.debug("deleting SearchConditions instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@SuppressWarnings("rawtypes")
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SearchConditions instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SearchConditions as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("rawtypes")
	public List findByTag(Object tag) {
		return findByProperty(TAG, tag);
	}

	@SuppressWarnings("rawtypes")
	public List findByOpmUserId(Object opmUserId) {
		return findByProperty(OPMUSER_ID, opmUserId);
	}
}
