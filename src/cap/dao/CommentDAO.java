package cap.dao;

import java.util.List;

import cap.bean.Comment;

public interface CommentDAO {

	// property constants
	public static final String CONTENT = "content";
	public static final String IS_DELETE = "isDelete";

	public abstract void save(Comment transientInstance);

	public abstract void delete(Comment persistentInstance);

	public abstract Comment findById(java.lang.Integer id);

	public abstract List<Comment> findByExample(Comment instance);

	public abstract List<Comment> findByProperty(String propertyName, Object value);

	public abstract List<Comment> findByContent(Object content);

	public abstract List<Comment> findByIsDelete(Object isDelete);

	public abstract List<Comment> findAll();

	public abstract Comment merge(Comment detachedInstance);

	public abstract void attachDirty(Comment instance);


	
	public int getCountByUserId(int userId);
	
	public abstract List<Comment> getCommentByPage(final int curPage,final int size);
	
	public abstract List<Comment> getCommentByPageUserId(final int curPage, final int size, final int userId);

	public abstract List<Comment> getCommentByPageArtId(int curPage, int size, int artId);

	public abstract int getCountByArtId(int artId);

}