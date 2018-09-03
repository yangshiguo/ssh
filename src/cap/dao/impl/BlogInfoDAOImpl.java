package cap.dao.impl;

import java.util.List;

import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cap.bean.BlogInfo;
import cap.dao.BlogInfoDAO;
import cap.util.BaseDAO;


public class BlogInfoDAOImpl extends BaseDAO implements BlogInfoDAO {
	private static final Logger log = LoggerFactory.getLogger(BlogInfoDAOImpl.class);
    
	@Override
	public void save(BlogInfo transientInstance) {
		log.debug("saving BlogInfo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(BlogInfo persistentInstance) {
		log.debug("deleting BlogInfo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public BlogInfo findById(java.lang.Integer id) {
		log.debug("getting BlogInfo instance with id: " + id);
		try {
			BlogInfo instance = (BlogInfo) getSession().get(BlogInfo.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<BlogInfo> findByExample(BlogInfo instance) {
		log.debug("finding BlogInfo instance by example");
		try {
			List<BlogInfo> results = getSession().createCriteria(BlogInfo.class).add(Example.create(instance)).list();
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
		log.debug("finding BlogInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from BlogInfo as model where model."
					+ propertyName + "= ?";
			return getSession().createQuery(queryString).setParameter(0, value).list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@Override
	public List<BlogInfo> findByBlogName(Object blogName) {
		return findByProperty(BLOG_NAME, blogName);
	}

	@Override
	public List<BlogInfo> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	@Override
	public List<BlogInfo> findByAnnoucement(Object annoucement) {
		return findByProperty(ANNOUCEMENT, annoucement);
	}

	@Override
	public List findAll() {
		log.debug("finding all BlogInfo instances");
		try {
			String queryString = "from BlogInfo";
			return getSession().createQuery(queryString).list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public BlogInfo merge(BlogInfo detachedInstance) {
		log.debug("merging BlogInfo instance");
		try {
			BlogInfo result = (BlogInfo) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(BlogInfo instance) {
		log.debug("attaching dirty BlogInfo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}


}