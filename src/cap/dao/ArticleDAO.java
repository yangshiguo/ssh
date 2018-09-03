package cap.dao;

import java.util.List;

import cap.bean.Article;

public interface ArticleDAO {

	// property constants
	public static final String TITLE = "title";
	public static final String CONTENT = "content";
	public static final String SUMMARY = "summary";
	public static final String IS_TOP = "isTop";
	public static final String IS_DELETE = "isDelete";
	public static final String COUNT = "count";

	public abstract void save(Article transientInstance);

	public abstract void delete(Article persistentInstance);

	public abstract Article findById(java.lang.Integer id);

	public abstract List<Article> findByExample(Article instance);

	public abstract List<Article> findByProperty(String propertyName, Object value);

	public abstract List<Article> findByTitle(Object title);

	public abstract List<Article> findByContent(Object content);

	public abstract List<Article> findBySummary(Object summary);

	public abstract List<Article> findByIsTop(Object isTop);

	public abstract List<Article> findByIsDelete(Object isDelete);

	public abstract List<Article> findByCount(Object count);

	public abstract List<Article> findAll();

	public abstract Article merge(Article detachedInstance);

	public abstract void attachDirty(Article instance);

	
	
	public abstract List<Article> getArticleByPage(final int curPage, final int size);

	public abstract List<Article> getArticleByPageUserId(final int curPage,final int size,
			final int userId);
	
	public abstract List<Article> searchArticle(String str);
	
	public List<Article> getBycgIdorscgId(int cgId, int scgId, int artId);
	
	public List<Article> topTenArticle();

	public void update(Article art);
	
	public int getArtCountByUserId(int userId);
	
	

}