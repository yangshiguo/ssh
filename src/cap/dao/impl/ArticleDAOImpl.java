package cap.dao.impl;
import java.util.List;

import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cap.bean.Article;
import cap.dao.ArticleDAO;
import cap.util.BaseDAO;



public class ArticleDAOImpl extends BaseDAO implements ArticleDAO {
	private static final Logger log = LoggerFactory.getLogger(ArticleDAOImpl.class);
    
	@Override
	public void save(Article transientInstance) {
		log.debug("saving Article instance");
		try {
			 getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Article persistentInstance) {
		log.debug("deleting Article instance");
		try {
			 getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public Article findById(java.lang.Integer id) {
		log.debug("getting Article instance with id: " + id);
		try {
			Article instance = (Article)  getSession().get(Article.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<Article> findByExample(Article instance) {
		log.debug("finding Article instance by example");
		try {
			List<Article> results = getSession().createCriteria(Article.class).add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@Override
	public List<Article> findByProperty(String propertyName, Object value) {
		log.debug("finding Article instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Article as model where model."
					+ propertyName + "= ?";
			return getSession().createQuery(queryString).setParameter(0, value).list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@Override
	public List<Article> findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	@Override
	public List<Article> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	@Override
	public List<Article> findBySummary(Object summary) {
		return findByProperty(SUMMARY, summary);
	}

	@Override
	public List<Article> findByIsTop(Object isTop) {
		return findByProperty(IS_TOP, isTop);
	}

	@Override
	public List<Article> findByIsDelete(Object isDelete) {
		return findByProperty(IS_DELETE, isDelete);
	}

	@Override
	public List<Article> findByCount(Object count) {
		return findByProperty(COUNT, count);
	}

	@Override
	public List<Article> findAll() {
		log.debug("finding all Article instances");
		try {
			String queryString = "from Article";
			return getSession().createQuery(queryString).list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	@Override
	public void update(Article art) {
		log.debug("¸üÐÂ");
		try {
			getSession().update(art);			
			log.debug("update successful");			
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Override
	public Article merge(Article detachedInstance) {
		log.debug("merging Article instance");
		try {
			Article result = (Article) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(Article instance) {
		log.debug("attaching dirty Article instance");
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
	public List<Article> getArticleByPage(final int curPage, final int size) {
		final String hql="from Article";
		return getSession().createQuery(hql).setFirstResult((curPage-1)*size).setMaxResults(size).list();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getArticleByPageUserId(final int curPage, final int size,
			final int userId) {
		final String hql="select a from Article as a where a.user.id=? and a.isDelete=false";
		return getSession().createQuery(hql).setParameter(0, userId).setFirstResult((curPage-1)*size).setMaxResults(size).list();
	}
	

	@Override
	public List<Article> searchArticle(String str) {
		str="%"+"%";
		String hql="select a from Article as a where a.title like ? or a.content like ? or a.summary like ? order by a.publishTime";
		return getSession().createQuery(hql).setParameter(0, str).setParameter(1, str).setParameter(2, str).list();
		
	}

	@Override
	public List<Article> getBycgIdorscgId(int cgId, int scgId, int artId) {
		// TODO Auto-generated method stub
		String hql="select a from Article as a where a.isDelete=0 and a.id<>? and a.category.id=? and a.sysCategory.id=? order by publish_time DESC";
		List<Article> artList=getSession().createQuery(hql).setParameter(0, artId).setParameter(1, cgId).setParameter(2, scgId).list().subList(0, 4);
		return artList;
	}

	@Override
	public List<Article> topTenArticle() {
		// TODO Auto-generated method stub
		String hql="select a from Article as a ORDER BY a.count DESC";
		List<Article> tenList=getSession().createQuery(hql).setFirstResult(0).setMaxResults(10).list();
		return tenList;
	}

	@Override
	public int getArtCountByUserId(int userId) {
		String hql="select count(*) from Article as a where a.user.id=:id and a.isDelete=false";
		Long count=(Long)getSession().createQuery(hql).setParameter("id", userId).uniqueResult();
		return count.intValue();
	}
	


}