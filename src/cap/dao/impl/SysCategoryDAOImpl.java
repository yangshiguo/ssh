package cap.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cap.bean.SysCategory;
import cap.dao.SysCategoryDAO;
import cap.util.BaseDAO;



public class SysCategoryDAOImpl extends BaseDAO implements SysCategoryDAO {
	private static final Logger log = LoggerFactory.getLogger(SysCategoryDAOImpl.class);
	
	@Override
	public void save(SysCategory transientInstance) {
		log.debug("saving SysCategory instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(SysCategory persistentInstance) {
		log.debug("deleting SysCategory instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public SysCategory findById(java.lang.Integer id) {
		log.debug("getting SysCategory instance with id: " + id);
		try {
			SysCategory instance = (SysCategory) getSession().get(SysCategory.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<SysCategory> findByExample(SysCategory instance) {
		log.debug("finding SysCategory instance by example");
		try {
			List<SysCategory> results = getSession().createCriteria(SysCategory.class).add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@Override
	public List<SysCategory> findByProperty(String propertyName, Object value) {
		log.debug("finding SysCategory instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SysCategory as model where model."
					+ propertyName + "= ?";
			return getSession().createQuery(queryString).setParameter(0, value).list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@Override
	public List<SysCategory> findByCategoryName(Object categoryName) {
		return findByProperty(CATEGORY_NAME, categoryName);
	}

	@Override
	public List<SysCategory> findByArticals(Object articals) {
		return findByProperty(ARTICALS, articals);
	}

	@Override
	public List<SysCategory> findByIsDelete(Object isDelete) {
		return findByProperty(IS_DELETE, isDelete);
	}

	@Override
	public List<SysCategory> findAll() {
		log.debug("finding all SysCategory instances");
		try {
			String queryString = "from SysCategory";
			return getSession().createQuery(queryString).list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public SysCategory merge(SysCategory detachedInstance) {
		log.debug("merging SysCategory instance");
		try {
			SysCategory result = (SysCategory) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(SysCategory instance) {
		log.debug("attaching dirty SysCategory instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	
	public List<SysCategory> getSysCategoryByPage(final int curPage, final int size){
		final String hql="from SysCategory";
		return getSession().createQuery(hql).setFirstResult((curPage-1)*size).setMaxResults(size).list();
			
	}
	
}