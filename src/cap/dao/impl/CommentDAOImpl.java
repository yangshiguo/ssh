package cap.dao.impl;

import java.util.List;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cap.bean.Comment;
import cap.dao.CommentDAO;
import cap.util.BaseDAO;

public class CommentDAOImpl extends BaseDAO implements CommentDAO {
	private static final Logger log = LoggerFactory.getLogger(CommentDAOImpl.class);
   
	@Override
	public void save(Comment transientInstance) {
		log.debug("saving Comment instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Comment persistentInstance) {
		log.debug("deleting Comment instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public Comment findById(java.lang.Integer id) {
		log.debug("getting Comment instance with id: " + id);
		try {
			Comment instance = (Comment) getSession().get(Comment.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List findByExample(Comment instance) {
		log.debug("finding Comment instance by example");
		try {
			List results = getSession().createCriteria(Comment.class).add(Example.create(instance)).list();
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
		log.debug("finding Comment instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Comment as model where model."
					+ propertyName + "= ?";
			return getSession().createQuery(queryString).setParameter(0, value).list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@Override
	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	@Override
	public List findByIsDelete(Object isDelete) {
		return findByProperty(IS_DELETE, isDelete);
	}

	@Override
	public List findAll() {
		log.debug("finding all Comment instances");
		try {
			String queryString = "from Comment";
			return getSession().createQuery(queryString).list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public Comment merge(Comment detachedInstance) {
		log.debug("merging Comment instance");
		try {
			Comment result = (Comment) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(Comment instance) {
		log.debug("attaching dirty Comment instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentByPage(final int curPage, final int size) {
		final String hql="from Comment";
		return getSession().createQuery(hql).setFirstResult((curPage-1)*size).setMaxResults(size).list();

	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentByPageUserId(final int curPage, final int size,
			final int userId) {
		final String hql="select c from Comment as c where c.user.id=?";
		return getSession().createQuery(hql).setParameter(0, userId).setFirstResult((curPage-1)*size).setMaxResults(size).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentByPageArtId(final int curPage, final int size,
			final int artId) {
		final String hql="select c from Comment as c where c.article.id=?";
		return getSession().createQuery(hql).setParameter(0, artId).setFirstResult((curPage-1)*size).setMaxResults(size).list();
	}

	@Override
	public int getCountByUserId(int userId) {
		
		String hql="select c from Comment as c where c.user.id=?";
		return getSession().createQuery(hql).setParameter(0, userId).list().size();
	}
	@Override
	public int getCountByArtId(int artId) {
		
		String hql="select c from Comment as c where c.article.id=?";
		return getSession().createQuery(hql).setParameter(0, artId).list().size();
	}
}