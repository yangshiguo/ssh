package cap.dao.impl;
import java.util.List;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cap.bean.User;
import cap.dao.UserDAO;
import cap.util.BaseDAO;

public class UserDAOImpl extends BaseDAO implements UserDAO {
	private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);

	@Override
	public void save(User transientInstance) {
		log.debug("saving User instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public User findById(java.lang.Integer id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) getSession().get(User.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<User> findByExample(User instance) {
		log.debug("finding User instance by example");
		try {
			List<User> results = getSession().createCriteria(User.class)
					.add(Example.create(instance)).list();
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
		log.debug("finding User instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from User as model where model."
					+ propertyName + "= ?";
			return getSession().createQuery(queryString).setParameter(0, value)
					.list();
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
	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	@Override
	public List findByIsApplied(Object isApplied) {
		return findByProperty(IS_APPLIED, isApplied);
	}

	@Override
	public List findByIsDelete(Object isDelete) {
		return findByProperty(IS_DELETE, isDelete);
	}

	@Override
	public List findByIsProfile(Object isProfile) {
		return findByProperty(IS_PROFILE, isProfile);
	}

	@Override
	public List<User> findAll() {
		log.debug("finding all User instances");
		try {
			String queryString = "from User";
			return getSession().createQuery(queryString).list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = (User) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		log.debug("ÓÃ»§µÇÂ¼");
		User u = null;
		try {
			String hql = "select u from User as u where u.username=? and u.password=?";
			List<User> uList = getSession().createQuery(hql)
					.setParameter(0, user.getUsername())
					.setParameter(1, user.getPassword()).list();
			u = (User) uList.get(0);
		} catch (Exception e) {
			u = null;
			log.error("µÇÂ¼Ê§°Ü");
		}
		return u;
	}

	@Override
	public List<User> getUserByPage(final int curPage, final int size) {

		final String hql = "from User";
		return getSession().createQuery(hql)
				.setFirstResult((curPage - 1) * size).setMaxResults(size)
				.list();

	}

	@Override
	public List<User> getActiveUser(int num) {

		String hql = "SELECT u FROM Article AS a , User AS u where a.user.id = u.id GROUP BY a.user.id";
		List<User> uList = getSession().createQuery(hql).setFirstResult(0).setMaxResults(num).list();
		return uList;
	}
}