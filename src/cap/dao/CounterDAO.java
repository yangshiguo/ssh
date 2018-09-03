package cap.dao;

import java.util.List;

import cap.bean.Counter;

public interface CounterDAO {

	// property constants
	public static final String NUM = "num";

	public abstract void save(Counter transientInstance);

	public abstract void delete(Counter persistentInstance);

	public abstract Counter findById(java.lang.Integer id);

	public abstract List<Counter> findByExample(Counter instance);

	public abstract List<Counter> findByProperty(String propertyName, Object value);

	public abstract List<Counter> findByNum(Object num);

	public abstract List<Counter> findAll();

	public abstract Counter merge(Counter detachedInstance);

	public abstract void attachDirty(Counter instance);


}