package cap.action;

import java.util.Map;

import cap.bean.Article;
import cap.bean.Category;
import cap.bean.Comment;
import cap.bean.SysCategory;
import cap.bean.User;
import cap.service.ArticleService;
import cap.service.CategoryService;
import cap.service.CommentService;
import cap.service.SysCategoryService;
import cap.service.UserService;
import cap.util.PageControl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CommentAction extends ActionSupport {
	private CommentService cmtService;
	private UserService userService;
	private ArticleService artService;
	private SysCategoryService scService;
	private CategoryService cgService;
	private PageControl pc;
	private String curPage;
	private int artId;
	private Article art;
	private Comment cmt;
	private SysCategory scCategory;
	private Category category;

	public CommentService getCmtService() {
		return cmtService;
	}

	public void setCmtService(CommentService cmtService) {
		this.cmtService = cmtService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ArticleService getArtService() {
		return artService;
	}

	public void setArtService(ArticleService artService) {
		this.artService = artService;
	}

	public SysCategoryService getScService() {
		return scService;
	}

	public void setScService(SysCategoryService scService) {
		this.scService = scService;
	}

	public CategoryService getCgService() {
		return cgService;
	}

	public void setCgService(CategoryService cgService) {
		this.cgService = cgService;
	}

	public PageControl getPc() {
		return pc;
	}

	public void setPc(PageControl pc) {
		this.pc = pc;
	}

	public String getCurPage() {
		return curPage;
	}

	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}

	public int getArtId() {
		return artId;
	}

	public void setArtId(int artId) {
		this.artId = artId;
	}

	public Article getArt() {
		return art;
	}

	public void setArt(Article art) {
		this.art = art;
	}

	public Comment getCmt() {
		return cmt;
	}

	public void setCmt(Comment cmt) {
		this.cmt = cmt;
	}

	public SysCategory getScCategory() {
		return scCategory;
	}

	public void setScCategory(SysCategory scCategory) {
		this.scCategory = scCategory;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String manage() {
		String curPageStr = curPage;
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		User u = (User) session.get("user");
		pc = cmtService.getCommentByUserId(curPageStr, u.getId());
		return SUCCESS;
	}

	public String post() {
		String curPageStr = curPage;
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		User u = (User) session.get("user");
		art = artService.getById(artId);
		artService.updateCount(artId);// 更新文章点击数
		scCategory = scService.getById(art.getSysCategory().getId());
		category = cgService.getById(art.getCategory().getId());
		pc = cmtService.getCommentByPageArtId(curPageStr, artId);
		return SUCCESS;
	}

	public String commit() {
		String curPageStr = curPage;
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		User u = (User) session.get("user");
		if (u != null) {
			int res = cmtService.insertComment(u.getId(), artId, cmt);
			String errorMsg = null;
			String succMsg = null;
			if (res == 0) {
				succMsg = "评论文章成功！";
			} else {
				errorMsg = "评论文章失败！";
			}
			art = artService.getById(artId);
			pc = cmtService.getCommentByPageArtId(curPageStr, artId);
			session.put("succMsg", succMsg);
			session.put("errorMsg", errorMsg);
			return SUCCESS;
		} else {
			// String errorMsg = "评论文章失败！";
			// session.put("errorMsg", errorMsg);
			return ERROR;
		}

	}

}
