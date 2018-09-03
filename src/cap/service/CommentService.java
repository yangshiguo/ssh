package cap.service;

import java.util.List;

import cap.bean.Comment;
import cap.util.PageControl;

public interface CommentService {

	public abstract int insertComment(int userId, int artId, Comment cmt);

	public abstract int deleteComment(int cmtId);

	public abstract List<Comment> getAllByArtId(int artId);

	public abstract PageControl getCommentByUserId(String curPageStr, int userId);
	public abstract List<Comment> getAll();

	public abstract PageControl getCommentByPageArtId(String curPageStr, int artId);

}