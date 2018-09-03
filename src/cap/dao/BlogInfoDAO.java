package cap.dao;

import java.util.List;

import cap.bean.BlogInfo;

public interface BlogInfoDAO {

	// property constants
	public static final String BLOG_NAME = "blogName";
	public static final String DESCRIPTION = "description";
	public static final String ANNOUCEMENT = "annoucement";

	public abstract void save(BlogInfo transientInstance);

	public abstract void delete(BlogInfo persistentInstance);

	public abstract BlogInfo findById(java.lang.Integer id);

	public abstract List<BlogInfo> findByExample(BlogInfo instance);

	public abstract List<BlogInfo> findByProperty(String propertyName, Object value);

	public abstract List<BlogInfo> findByBlogName(Object blogName);

	public abstract List<BlogInfo> findByDescription(Object description);

	public abstract List<BlogInfo> findByAnnoucement(Object annoucement);

	public abstract List<BlogInfo> findAll();

	public abstract BlogInfo merge(BlogInfo detachedInstance);

	public abstract void attachDirty(BlogInfo instance);


}