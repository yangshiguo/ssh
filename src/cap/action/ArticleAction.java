package cap.action;

import java.util.List;
import java.util.Map;

import cap.bean.Article;
import cap.bean.Category;
import cap.bean.SysCategory;
import cap.bean.User;
import cap.service.ArticleService;
import cap.service.CategoryService;
import cap.service.SysCategoryService;
import cap.service.UserService;
import cap.util.PageControl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ArticleAction extends ActionSupport {

	private CategoryService cgService;
	private SysCategoryService scService;
	private ArticleService artService;
	private UserService userService;
	private PageControl pc;
	private String curPage;
	private int userId;
	private int artId;
	private Article art;
	
	private List<SysCategory> scgList;
	private List<Category> cgList;
	
	
	public CategoryService getCgService() {
		return cgService;
	}
	public void setCgService(CategoryService cgService) {
		this.cgService = cgService;
	}
	public SysCategoryService getScService() {
		return scService;
	}
	public void setScService(SysCategoryService scService) {
		this.scService = scService;
	}
	public ArticleService getArtService() {
		return artService;
	}
	public void setArtService(ArticleService artService) {
		this.artService = artService;
	}
		
	public String getCurPage() {
		return curPage;
	}
	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}
	
	
	public PageControl getPc() {
		return pc;
	}
	public void setPc(PageControl pc) {
		this.pc = pc;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getArtId() {
		return artId;
	}
	public void setArtId(int artId) {
		this.artId = artId;
	}
	
	
	public List<SysCategory> getScgList() {
		return scgList;
	}
	public void setScgList(List<SysCategory> scgList) {
		this.scgList = scgList;
	}
		
	public Article getArt() {
		return art;
	}
	public void setArt(Article art) {
		this.art = art;
	}
	
	public List<Category> getCgList() {
		return cgList;
	}
	public void setCgList(List<Category> cgList) {
		this.cgList = cgList;
	}
	
	
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String manage(){		
		String curPageStr=curPage;
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		User u=(User) session.get("user");
		pc =artService.getByPageUserId(curPageStr, u.getId());
		return SUCCESS;
	}
	public String update(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		User u=(User) session.get("user");
	    art = artService.getById(artId);	
		cgList=cgService.getByUserId(u.getId());
		scgList = scService.getAllSysCategory();
		return SUCCESS;

	}
	public String add(){		
		cgList = cgService.getByUserId(userId);		
		scgList = scService.getAllSysCategory();
		return SUCCESS;

	}
	public String save(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		User u=(User) session.get("user");
		art.setUser(u);
		int res = artService.insertArtical(art);
		
		if (res == 0) {	//添加新文章成功
			session.put("succMsg", "更新文章成功！");
			
		} else {
			session.put("errorMsg", "更新文章失败");
		}
		System.out.println("ceshi");
		return SUCCESS;
	}
	
	
	public String saveupdate(){	
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		User u=(User) session.get("user");
		Article art1=artService.getById(artId);
		art1.setUser(u);
		art1.setTitle(art.getTitle());
		art1.setContent(art.getContent());
		art1.setSummary(art.getSummary());
		
		int res = artService.UpdateArtical(art1);
		
		if (res == 0) {	//更新成功
			session.put("succMsg", "修改文章成功！");
		} else {
			session.put("errorMsg", "修改文章失败");
		}
		art=art1;
		System.out.print("saveupdate");
		return SUCCESS;
	
	}
	public String delete(){
		artService.deleteArtical(artId);
		return SUCCESS;
	}
	

}
