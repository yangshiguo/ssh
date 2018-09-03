package cap.service.impl;

import java.util.List;

import cap.bean.Article;
import cap.bean.Category;
import cap.bean.SysCategory;
import cap.bean.User;
import cap.dao.ArticleDAO;
import cap.dao.UserDAO;
import cap.service.ArticleService;
import cap.util.PageControl;

public class ArticleServiceImpl implements ArticleService{
	private ArticleDAO articleDAO;
	private UserDAO userDAO;
	

	public void setArticleDAO(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}
	

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}


	@Override
	public int insertArtical(Article art) {
		// TODO Auto-generated method stub
		//User user=new User();
		//user.setId(userId);
		//SysCategory scgCategory=new SysCategory();
		//scgCategory.setId(scgId);
		//Category category=new Category();
		//category.setId(cgId);
		//Article art=new Article();
		//art.setTitle(title);
		//art.setUser(user);
		//art.setSysCategory(scgCategory);
		//art.setCategory(category);
	    art.setIsTop(false);
	    art.setIsDelete(false);
	    art.setCount(0);
		articleDAO.save(art);
		return 0;
	}

	@Override
	public PageControl getData(String curPageStr) {
		// TODO Auto-generated method stub
		int total=articleDAO.findAll().size();
		PageControl pc=new PageControl(curPageStr, total);
		List<Article> artList=articleDAO.getArticleByPage(pc.getCurPage(), pc.getPageSize());
		pc.setList(artList);
		return pc;
	}

	@Override
	public PageControl getByPageUserId(String curPageStr, int userId) {
		// TODO Auto-generated method stub
		Article art=new Article();
		User user=new User();
		user.setId(userId);
		art.setUser(user);
		int total=articleDAO.getArtCountByUserId(userId);
		PageControl pc=new PageControl(curPageStr, total);
		List<Article> artList=articleDAO.getArticleByPageUserId(pc.getCurPage(), pc.getPageSize(),userId);
		pc.setList(artList);
		return pc;
	}

	@Override
	public List<Article> search(String q) {
		// TODO Auto-generated method stub
		return articleDAO.searchArticle(q);
	}

	@Override
	public int deleteArtical(int artId) {
		// TODO Auto-generated method stub
		Article art=articleDAO.findById(artId);
		art.setIsDelete(true);
		articleDAO.merge(art);
		return 0;
	}

	@Override
	public int UpdateArtical(Article art) {
		// TODO Auto-generated method stub
		//art.setIsTop(false);
	   // art.setIsDelete(false);
	   // art.setCount(0);
		articleDAO.update(art);
		return 0;
	}

	@Override
	public List<Article> getBycgIdorscgId(int cgId, int scgId, int artId) {
		// TODO Auto-generated method stub
		return articleDAO.getBycgIdorscgId(cgId, scgId, artId);
	}

	@Override
	public List<Article> topTenArticle() {
		// TODO Auto-generated method stub
		return articleDAO.topTenArticle();
	}

	@Override
	public List<User> getActiveUser(int num) {		
		return userDAO.getActiveUser(num);
	}

	@Override
	public Article getById(int id) {
		// TODO Auto-generated method stub
		return articleDAO.findById(id);
	}

	@Override
	public int updateCount(int artId) {
		// TODO Auto-generated method stub
		Article art=articleDAO.findById(artId);
		int count=art.getCount();
		art.setCount(++count);
		articleDAO.merge(art);
		return 0;
	}

	@Override
	public List<Article> getAllArtical() {
		// TODO Auto-generated method stub
		return articleDAO.findAll();
	}

}
