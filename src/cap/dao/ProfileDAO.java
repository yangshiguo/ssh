package cap.dao;

import java.util.List;

import cap.bean.Profile;

public interface ProfileDAO {

	// property constants
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String GENDER = "gender";
	public static final String TELEPHONE = "telephone";

	public abstract void save(Profile transientInstance);

	public abstract void delete(Profile persistentInstance);

	public abstract Profile findById(java.lang.Integer id);

	public abstract List<Profile> findByExample(Profile instance);

	public abstract List<Profile> findByProperty(String propertyName, Object value);

	public abstract List<Profile> findByFirstName(Object firstName);

	public abstract List<Profile> findByLastName(Object lastName);

	public abstract List<Profile> findByGender(Object gender);

	public abstract List<Profile> findByTelephone(Object telephone);

	public abstract List<Profile> findAll();

	public abstract Profile merge(Profile detachedInstance);

	public abstract void attachDirty(Profile instance);

	
	
	public abstract Profile findByUserId(int userId);

}