package cap.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cap.bean.Category;
import cap.dao.CategoryDAO;
import cap.util.BaseDAO;



public class CategoryDAOImpl extends BaseDAO  implements CategoryDAO {
	private static final Logger log = LoggerFactory.getLogger(CategoryDAOImpl.class);
	
	@Override
	public void save(Category transientInstance) {
		log.debug("saving Category instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Category persistentInstance) {
		log.debug("deleting Category instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public Category findById(java.lang.Integer id) {
		log.debug("getting Category instance with id: " + id);
		try {
			Category instance = (Category) getSession().get(Category.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List findByExample(Category instance) {
		log.debug("finding Category instance by example");
		try {
			List results = getSession().createCriteria(Category.class).add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@Override
	public List<Category> findByProperty(String propertyName, Object value) {
		log.debug("finding Category instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Category as model where model."
					+ propertyName + "= ?";
			return getSession().createQuery(queryString).setParameter(0, value).list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@Override
	public List<Category> findByCategoryName(Object categoryName) {
		return findByProperty(CATEGORY_NAME, categoryName);
	}

	@Override
	public List<Category> findByArticals(Object articals) {
		return findByProperty(ARTICALS, articals);
	}

	@Override
	public List<Category> findByIsDelete(Object isDelete) {
		return findByProperty(IS_DELETE, isDelete);
	}

	@Override
	public List<Category> findAll() {
		log.debug("finding all Category instances");
		try {
			String queryString = "from Category";
			return getSession().createQuery(queryString).list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public Category merge(Category detachedInstance) {
		log.debug("merging Category instance");
		try {
			Category result = (Category) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(Category instance) {
		log.debug("attaching dirty Category instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}



	@Override
	public Category getByName(String cgName, int userId) {
		String hql="select c from Category as c where c.categoryName=? and c.user.id=? and c.isDelete=0";
		Category c=(Category)getSession().createQuery(hql).setParameter(0, cgName).setParameter(1, userId).list().get(0);
		return c;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategoryByPage(final int curPage, final int size) {
		// TODO Auto-generated method stub
		final String hql="from Category";
		return getSession().createQuery(hql).setFirstResult((curPage-1)*size).setMaxResults(size).list();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategoryByPageUserId(final int curPage,final int size,
			final int userId) {
		final String hql="select c from Category as c where c.user.id=?";		
		return getSession().createQuery(hql).setParameter(0, userId).setFirstResult((curPage-1)*size).setMaxResults(size).list();
		
	}
	public List<Category> getCategoryByUserId(int userId) {
		final String hql="select c from Category as c where c.user.id=?";
		return getSession().createQuery(hql).setParameter(0, userId).list();
	}
}