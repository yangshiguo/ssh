package cap.dao;

import java.util.List;

import cap.bean.Admin;

public interface AdminDAO {

	// property constants
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";

	public abstract void save(Admin transientInstance);

	public abstract void delete(Admin persistentInstance);

	public abstract Admin findById(java.lang.Integer id);

	public abstract List findByExample(Admin instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByUsername(Object username);

	public abstract List findByPassword(Object password);

	public abstract List findAll();

	public abstract Admin merge(Admin detachedInstance);

	public abstract void attachDirty(Admin instance);


	public abstract long getArticleCount();

	public abstract long getCmtCount();

	public abstract long getUserCount();

}