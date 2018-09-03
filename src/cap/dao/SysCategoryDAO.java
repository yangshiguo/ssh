package cap.dao;

import java.util.List;

import cap.bean.SysCategory;

public interface SysCategoryDAO {

	// property constants
	public static final String CATEGORY_NAME = "categoryName";
	public static final String ARTICALS = "articals";
	public static final String IS_DELETE = "isDelete";

	public abstract void save(SysCategory transientInstance);

	public abstract void delete(SysCategory persistentInstance);

	public abstract SysCategory findById(java.lang.Integer id);

	public abstract List<SysCategory> findByExample(SysCategory instance);

	public abstract List<SysCategory> findByProperty(String propertyName, Object value);

	public abstract List<SysCategory> findByCategoryName(Object categoryName);

	public abstract List<SysCategory> findByArticals(Object articals);

	public abstract List<SysCategory> findByIsDelete(Object isDelete);

	public abstract List<SysCategory> findAll();

	public abstract SysCategory merge(SysCategory detachedInstance);

	public abstract void attachDirty(SysCategory instance);

	
	
	public List<SysCategory> getSysCategoryByPage(final int curPage, final int size);

}