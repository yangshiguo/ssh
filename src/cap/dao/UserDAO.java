package cap.dao;

import java.util.List;

import cap.bean.User;

public interface UserDAO {

	// property constants
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String EMAIL = "email";
	public static final String IS_APPLIED = "isApplied";
	public static final String IS_DELETE = "isDelete";
	public static final String IS_PROFILE = "isProfile";

	public abstract void save(User transientInstance);

	public abstract void delete(User persistentInstance);

	public abstract User findById(java.lang.Integer id);

	public abstract List<User> findByExample(User instance);

	public abstract List<User> findByProperty(String propertyName, Object value);

	public abstract List<User> findByUsername(Object username);

	public abstract List<User> findByPassword(Object password);

	public abstract List<User> findByEmail(Object email);

	public abstract List<User> findByIsApplied(Object isApplied);

	public abstract List<User> findByIsDelete(Object isDelete);

	public abstract List<User> findByIsProfile(Object isProfile);

	public abstract List<User> findAll();

	public abstract User merge(User detachedInstance);

	public abstract void attachDirty(User instance);

	public abstract User login(User user);
	
	public List<User> getUserByPage(final int curPage, final int size) ;
	
	public List<User> getActiveUser(int num);

}