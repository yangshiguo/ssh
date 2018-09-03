package cap.dao;

import java.util.List;

import cap.bean.Category;

public interface CategoryDAO {

	// property constants
	public static final String CATEGORY_NAME = "categoryName";
	public static final String ARTICALS = "articals";
	public static final String IS_DELETE = "isDelete";

	public abstract void save(Category transientInstance);

	public abstract void delete(Category persistentInstance);

	public abstract Category findById(java.lang.Integer id);

	public abstract List<Category> findByExample(Category instance);

	public abstract List<Category> findByProperty(String propertyName, Object value);

	public abstract List<Category> findByCategoryName(Object categoryName);

	public abstract List<Category> findByArticals(Object articals);

	public abstract List<Category> findByIsDelete(Object isDelete);

	public abstract List<Category> findAll();

	public abstract Category merge(Category detachedInstance);

	public abstract void attachDirty(Category instance);

	
	public Category getByName(String cgName, int userId);
	
	public abstract List<Category> getCategoryByPage(final int curPage,final int size);

	public abstract List<Category> getCategoryByPageUserId(final int curPage, final int size,
			final int userId);
	
	public List<Category> getCategoryByUserId(int userId);

}