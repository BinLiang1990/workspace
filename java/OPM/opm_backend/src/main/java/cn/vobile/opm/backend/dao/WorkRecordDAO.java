package cn.vobile.opm.backend.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) provides persistence and search support for
 * WorkRecord entities.
 * 
 * @author wang_lin
 * 
 */
@Repository
public class WorkRecordDAO {

	@Resource
	private HibernateTemplate hibernateTemplate;
	protected static final Logger log = Logger.getLogger(WorkRecordDAO.class);

	public void saveOrUpdate(WorkRecord transientInstance) {
		try {
			hibernateTemplate.saveOrUpdate(transientInstance);
		} catch (RuntimeException re) {
			log.error("save or update failed", re);
			throw re;
		}
	}

	public WorkRecord findById(int id) {
		try {
			WorkRecord instance = (WorkRecord) hibernateTemplate.get(
					"cn.vobile.opm.backend.dao.WorkRecord", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public WorkRecord getFirstWorkRecord() {
		try {
			String queryString = "from WorkRecord";
			@SuppressWarnings("unchecked")
			List<WorkRecord> resultList = (List<WorkRecord>) hibernateTemplate
					.find(queryString);
			return resultList.size() > 0 ? resultList.get(0) : null;
		} catch (RuntimeException re) {
			log.error("find first workRecord failed", re);
			throw re;
		}
	}
}
