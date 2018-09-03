package cap.dao.impl;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cap.bean.Profile;
import cap.dao.ProfileDAO;
import cap.util.BaseDAO;



public class ProfileDAOImpl extends BaseDAO implements ProfileDAO {
	private static final Logger log = LoggerFactory.getLogger(ProfileDAOImpl.class);
    
	@Override
	public void save(Profile transientInstance) {
		log.debug("saving Profile instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Profile persistentInstance) {
		log.debug("deleting Profile instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public Profile findById(java.lang.Integer id) {
		log.debug("getting Profile instance with id: " + id);
		try {
			Profile instance = (Profile) getSession().get(Profile.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<Profile> findByExample(Profile instance) {
		log.debug("finding Profile instance by example");
		try {
			List<Profile> results = getSession().createCriteria(Profile.class).add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@Override
	public List<Profile> findByProperty(String propertyName, Object value) {
		log.debug("finding Profile instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Profile as model where model."
					+ propertyName + "= ?";
			return getSession().createQuery(queryString).setParameter(0, value).list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@Override
	public List<Profile> findByFirstName(Object firstName) {
		return findByProperty(FIRST_NAME, firstName);
	}

	@Override
	public List<Profile> findByLastName(Object lastName) {
		return findByProperty(LAST_NAME, lastName);
	}

	@Override
	public List<Profile> findByGender(Object gender) {
		return findByProperty(GENDER, gender);
	}

	@Override
	public List<Profile> findByTelephone(Object telephone) {
		return findByProperty(TELEPHONE, telephone);
	}

	@Override
	public List<Profile> findAll() {
		log.debug("finding all Profile instances");
		try {
			String queryString = "from Profile";
			return getSession().createQuery(queryString).list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public Profile merge(Profile detachedInstance) {
		log.debug("merging Profile instance");
		try {
			Profile result = (Profile) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(Profile instance) {
		log.debug("attaching dirty Profile instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public Profile findByUserId(int userId) {

		Profile p=null;
		String hql="select p from Profile as p where p.user.id=?";
		try {
		   List<Profile> pList=getSession().createQuery(hql).setParameter(0, userId).list();
		   if(pList.size()>0){
			   p=(Profile) pList.get(0);
		   }
		} catch (Exception e) {
			p=null;
		}
		return p;
		
	}
	
	
}