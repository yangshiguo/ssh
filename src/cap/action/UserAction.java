package cap.action;

import java.util.List;
import java.util.Map;

import cap.bean.Article;
import cap.bean.BlogInfo;
import cap.bean.Category;
import cap.bean.Profile;
import cap.bean.SysCategory;
import cap.bean.User;
import cap.service.ArticleService;
import cap.service.BlogInfoService;
import cap.service.CategoryService;
import cap.service.ProfileService;
import cap.service.SysCategoryService;
import cap.service.UserService;
import cap.util.PageControl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{	
	private static final long serialVersionUID = 1L;	
	
	private UserService userService;
	private SysCategoryService scService;
	private ArticleService artService;
	private BlogInfoService biService;
	private CategoryService categoryService;
	private ProfileService profileService;
	
	private User u;
	private BlogInfo bi;
	private int userId;
	private String curPage;
	private PageControl pc;	
	private List<Category> cgList;
	private Profile pf;
	
	private List<User> ulist;
	private List<Article> artList ;
	private List<Article> tenList;
	private List<SysCategory> scList ;
	
	private String searchStr;
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
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
	public BlogInfoService getBiService() {
		return biService;
	}
	public void setBiService(BlogInfoService biService) {
		this.biService = biService;
	}
	public CategoryService getCategoryService() {
		return categoryService;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public ProfileService getProfileService() {
		return profileService;
	}
	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}
	public User getU() {
		return u;
		
	}
	public void setU(User u) {
		this.u = u;
	}
	
	public BlogInfo getBi() {
		return bi;
	}
	public void setBi(BlogInfo bi) {
		this.bi = bi;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	
	
	public List<Category> getCgList() {
		return cgList;
	}
	public void setCgList(List<Category> cgList) {
		this.cgList = cgList;
	}
	
	
	public Profile getPf() {
		return pf;
	}
	public void setPf(Profile pf) {
		this.pf = pf;
	}
	
	
	public List<User> getUlist() {
		return ulist;
	}
	public void setUlist(List<User> ulist) {
		this.ulist = ulist;
	}
	public List<Article> getArtList() {
		return artList;
	}
	public void setArtList(List<Article> artList) {
		this.artList = artList;
	}
	public List<Article> getTenList() {
		return tenList;
	}
	public void setTenList(List<Article> tenList) {
		this.tenList = tenList;
	}
		
	public List<SysCategory> getScList() {
		return scList;
	}
	public void setScList(List<SysCategory> scList) {
		this.scList = scList;
	}
	
	
	public String getSearchStr() {
		return searchStr;
	}
	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}
	public String login(){
		User u1=userService.login(u);
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		if (null != u1) {	//验证成功，还要看is_delete		
			if (u1.getIsDelete() == false) { 					
				session.put("user", u1);
				return SUCCESS;
			} else {
				session.put("userIsDeleMsg", "该用户已被禁用，无法登录");
				return ERROR;
			}			
		} else {
			session.put("msg", "验证失败，请重新输入用户名或密码！");
			return ERROR;
		}
	}
	public String register(){
		int userId = userService.getIdByuserName(u.getUsername());    //根据username查询用户id
		User u1 = userService.getByEmail(u.getEmail());				   //根据email查询用户
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		if ((userId > 0) || (null != u1)) {
			session.put("existMsg", "用户名或邮箱已被注册，请重新填写！");
			
		} else {
			int res = userService.register(u.getEmail(), u.getUsername(), u.getPassword());
			
			if (res == 0) {					//注册成功!
				session.put("succMsg", "注册成功");
			} else {
				session.put("errorMsg", "注册失败，请重新填写用户信息！");
			}
		}
		
		return SUCCESS;
	}
	public String logout(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		session.remove("user");
		return SUCCESS;
	}
	public String index(){
		String curPageStr=curPage;
		scList=scService.getAllSysCategory();//获取系统分类列表
		ulist=artService.getActiveUser(2); //获取 2 个活跃人数
		tenList=artService.topTenArticle();				
		pc=artService.getData(curPageStr);		
		return SUCCESS;
	}
	public String apply(){
		//初次注册开通博客
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		BlogInfo bi = biService.getByuserId(userId);		
		int res = -1;
		
		if (null != bi) {
			/*此处可能出错*/
			res = biService.updateBlogInfo(bi);
			bi = biService.getByuserId(userId);
		} else {
			res = biService.insertBlogInfo(userId, bi.getBlogName(), bi.getDescription(), bi.getAnnoucement());
		}
			
		if (res == 0) {
			
			userService.setIsAppliedById(userId);			//此处想blog_info插入新记录和修改user表的is_applied字段应该设计为事务！！！
			
			categoryService.insertCategory(userId, "无分类");
			
			User u = userService.getUserById(userId);		//查询最新的用户信息
			String succMsg = "申请博客成功！";
			
			session.put("user", u);
			session.put("succMsg", succMsg);
		} else {
			String errorMsg = "申请博客失败！";
			session.put("errorMsg", errorMsg);
		}
		
		return SUCCESS;
	}
	public String myblog(){
		bi = biService.getByuserId(userId);		
		cgList = categoryService.getByUserId(userId);    //获取用户的个人分类		
		u = userService.getUserById(userId);		
		if (null == bi) {
			//跳转到404页面
			return ERROR;
		} else {
						
			String curPageStr = curPage;
			pc=artService.getByPageUserId(curPageStr, userId);
			return SUCCESS;
		}
	}
	public String profile(){		
		pf = profileService.getByuserId(userId);		
		return SUCCESS;
	}
	public String updateprofile(){
		
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		if(pf.getStrGender().equals("male"))
			pf.setGender(true);
		else
			pf.setGender(false);
			
		Profile pf1 = profileService.getByuserId(userId);
		int res = -1;		
		if (null != pf1) {
			res = profileService.updateProfile(pf);
			
		} else {
			res = profileService.insertProfile(pf);
		}
			
		if (res == 0) {	
			String succMsg = "更新个人资料成功！";
			session.put("succMsg", succMsg);
		} else {
			String errorMsg = "更新个人资料失败！";
			session.put("errorMsg", errorMsg);
		}
	
		return SUCCESS;
	}
	public String updatepass(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		User u1=userService.getUserById(userId);
		pf=profileService.getByuserId(userId);//此处用于页面跳转的数据显示
		if(u1.getPassword().equals(u.getPassword())){
			if(u.getNewpassword1().equals(u.getNewpassword2()))
			{
				int res=userService.updatePwdById(userId, u.getNewpassword1());
				if (res > 0) {
					session.put("succUpdateMsg", "修改密码成功！");
				} else {
					session.put("errorUpdateMsg", "修改密码失败！");
				}
			}
		}else
		{
			session.put("validPwdMsg", "旧密码验证失败，请重新填写！");
		}
		return SUCCESS;
				
	}

	public String search(){
		artList=artService.search(searchStr);
		return SUCCESS;
	}
	public String bloginfo(){
		bi=biService.getByuserId(userId);
		return SUCCESS;
	}
	public String updatebloginfo(){
		User user=new User();
		user.setId(userId);
		bi.setUser(user);
		biService.updateBlogInfo(bi);
		return SUCCESS;
		
	}
}
