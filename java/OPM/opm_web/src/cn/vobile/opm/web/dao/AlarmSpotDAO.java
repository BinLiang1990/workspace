package cn.vobile.opm.web.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AlarmSpotDAO extends HibernateDaoSupport {

	private static final Logger log = LoggerFactory
			.getLogger(AlarmSpotDAO.class);
	private static final String FINDBYPROPERTY = "from AlarmSpot as model where model.";

	public void saveOrUpdate(AlarmSpot alarmSpot) {
		try {
			getHibernateTemplate().saveOrUpdate(alarmSpot);
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
			log.error(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<AlarmSpot> findByProperty(String property, Object value) {
		String queryString = FINDBYPROPERTY + property + "= ?";
		return getHibernateTemplate().find(queryString, value);
	}

	public List<AlarmSpot> findByName(Object value) {
		return findByProperty("name", value);
	}

	public AlarmSpot findById(int id) {
		AlarmSpot alarmSpot = null;
		try {
			alarmSpot = (AlarmSpot) getHibernateTemplate().get(
					"cn.vobile.opm.web.dao.AlarmSpot", id);
		} catch (DataAccessException e) {
			log.error(e.getMessage());
		}
		return alarmSpot;
	}
}
