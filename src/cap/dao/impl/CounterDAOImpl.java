package cap.dao.impl;

import java.util.List;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cap.bean.Counter;
import cap.dao.CounterDAO;
import cap.util.BaseDAO;



public class CounterDAOImpl extends BaseDAO implements CounterDAO {
	private static final Logger log = LoggerFactory.getLogger(CounterDAOImpl.class);
    
	@Override
	public void save(Counter transientInstance) {
		log.debug("saving Counter instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Counter persistentInstance) {
		log.debug("deleting Counter instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public Counter findById(java.lang.Integer id) {
		log.debug("getting Counter instance with id: " + id);
		try {
			Counter instance = (Counter) getSession().get(Counter.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<Counter> findByExample(Counter instance) {
		log.debug("finding Counter instance by example");
		try {
			List<Counter> results = getSession().createCriteria(Counter.class).add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@Override
	public List<Counter> findByProperty(String propertyName, Object value) {
		log.debug("finding Counter instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Counter as model where model."
					+ propertyName + "= ?";
			return getSession().createQuery(queryString).setParameter(0, value).list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@Override
	public List findByNum(Object num) {
		return findByProperty(NUM, num);
	}

	@Override
	public List<Counter> findAll() {
		log.debug("finding all Counter instances");
		try {
			String queryString = "from Counter";
			return getSession().createQuery(queryString).list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public Counter merge(Counter detachedInstance) {
		log.debug("merging Counter instance");
		try {
			Counter result = (Counter) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(Counter instance) {
		log.debug("attaching dirty Counter instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}


}