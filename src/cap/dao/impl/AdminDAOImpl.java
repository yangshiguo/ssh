package cap.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cap.bean.Admin;
import cap.dao.AdminDAO;
import cap.util.BaseDAO;



public class AdminDAOImpl extends BaseDAO  implements AdminDAO {
	private static final Logger log = LoggerFactory.getLogger(AdminDAOImpl.class);
    
	@Override
	public void save(Admin transientInstance) {
		log.debug("saving Admin instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Admin persistentInstance) {
		log.debug("deleting Admin instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public Admin findById(java.lang.Integer id) {
		log.debug("getting Admin instance with id: " + id);
		try {
			Admin instance = (Admin) getSession().get("cap.dao.Admin", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List findByExample(Admin instance) {
		log.debug("finding Admin instance by example");
		try {
			List results = getSession().createCriteria(Admin.class).add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Admin instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Admin as model where model."
					+ propertyName + "= ?";
			return getSession().createQuery(queryString).setParameter(0, value).list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@Override
	public List findByUsername(Object username) {
		return findByProperty(USERNAME, username);
	}

	@Override
	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	@Override
	public List findAll() {
		log.debug("finding all Admin instances");
		try {
			String queryString = "from Admin";
			return getSession().createQuery(queryString).list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public Admin merge(Admin detachedInstance) {
		log.debug("merging Admin instance");
		try {
			Admin result = (Admin) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(Admin instance) {
		log.debug("attaching dirty Admin instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}


	@Override
	public long getArticleCount(){
		String hql="select count(*) from Article as a";
		Long count =(Long) getSession().createQuery(hql).uniqueResult();
		return count.intValue();
	}
	@Override
	public long getCmtCount(){
		String hql="select count(*) from Comment as c";
		Long count =(Long) getSession().createQuery(hql).uniqueResult();
		return count.intValue();
	}
	@Override
	public long getUserCount(){
		String hql="select count(*) from User as u";
		Long count =(Long) getSession().createQuery(hql).uniqueResult();
		return count.intValue();
	}
}