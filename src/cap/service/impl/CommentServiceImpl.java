package cap.service.impl;

import java.util.List;

import cap.bean.Article;
import cap.bean.Comment;
import cap.bean.User;
import cap.dao.CommentDAO;
import cap.service.CommentService;
import cap.util.PageControl;

public class CommentServiceImpl implements CommentService {
	private CommentDAO commentDAO;
	

	public void setCommentDAO(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}

	@Override
	public int insertComment(int userId, int artId, Comment cmt) {
		// TODO Auto-generated method stub		
		User user=new User();
		Article art=new Article();
		user.setId(userId);
		art.setId(artId);
		cmt.setUser(user);
		cmt.setArticle(art);
		cmt.setIsDelete(false);
		commentDAO.save(cmt);
		return 0;
	}

	@Override
	public int deleteComment(int cmtId) {
		// TODO Auto-generated method stub
		Comment cmt=new Comment();
		cmt.setIsDelete(true);
		cmt.setId(cmtId);
		commentDAO.merge(cmt);
		return 0;
	}

	@Override
	public List<Comment> getAllByArtId(int artId) {
		// TODO Auto-generated method stub
		Comment cmt=new Comment();
		Article art=new Article();
		art.setId(artId);
		cmt.setArticle(art);
		return commentDAO.findByExample(cmt);
		
	}

	@Override
	public PageControl getCommentByUserId(String curPageStr, int userId) {
		// TODO Auto-generated method stub
		int total=commentDAO.getCountByUserId(userId);
		PageControl pc=new PageControl(curPageStr, total);
		List uList=commentDAO.getCommentByPageUserId(pc.getCurPage(), pc.getPageSize(), userId);
		pc.setList(uList);
		return pc;
	}
	@Override
	public  PageControl getCommentByPageArtId(String curPageStr, int artId){
		int total=commentDAO.getCountByArtId(artId);
		PageControl pc=new PageControl(curPageStr, total);
		List uList=commentDAO.getCommentByPageArtId(pc.getCurPage(), pc.getPageSize(), artId);
		pc.setList(uList);
		return pc;
	}

	@Override
	public List<Comment> getAll() {
		// TODO Auto-generated method stub
		return commentDAO.findAll();
	}

}
