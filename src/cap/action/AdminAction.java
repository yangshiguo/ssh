package cap.action;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import cap.bean.Admin;
import cap.bean.Profile;
import cap.bean.SysCategory;
import cap.service.AdminService;
import cap.service.ArticleService;
import cap.service.CounterService;
import cap.service.SysCategoryService;
import cap.service.UserService;
import cap.util.PageControl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AdminAction extends ActionSupport {
	private AdminService adminService;
	private UserService userService;	
	private ArticleService artService;
	private SysCategoryService scService;
	private CounterService cntService;	
	private long counter;
	private long userCount;
	private long articleCount;
	private long cmtCount;
	
	private Admin admin;
	private PageControl pc;
	private String curPage;
	private int userId;
	private Profile pf;
	private SysCategory sc;
	private int scgId;
	
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setArtService(ArticleService artService) {
		this.artService = artService;
	}
	public void setScService(SysCategoryService scService) {
		this.scService = scService;
	}
	public void setCntService(CounterService cntService) {
		this.cntService = cntService;
	}
	
	
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	
	public long getCounter() {
		return counter;
	}
	public void setCounter(long counter) {
		this.counter = counter;
	}
	public long getUserCount() {
		return userCount;
	}
	public void setUserCount(long userCount) {
		this.userCount = userCount;
	}
	public long getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(long articleCount) {
		this.articleCount = articleCount;
	}
	public long getCmtCount() {
		return cmtCount;
	}
	public void setCmtCount(long cmtCount) {
		this.cmtCount = cmtCount;
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
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public Profile getPf() {
		return pf;
	}
	public void setPf(Profile pf) {
		this.pf = pf;
	}
	
	
	public SysCategory getSc() {
		return sc;
	}
	public void setSc(SysCategory sc) {
		this.sc = sc;
	}
	
	public int getScgId() {
		return scgId;
	}
	public void setScgId(int scgId) {
		this.scgId = scgId;
	}
	public String index(){
		counter=cntService.getCounter().getNum();
		userCount=adminService.getUserCount();
		articleCount=adminService.getArticleCount();
		cmtCount=adminService.getCmtCount();
		return SUCCESS;
	}
	public String login(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		Admin ad=adminService.login(admin);
		if (ad== null) {
			session.put("msg", "用户名或密码不正确！");
			return ERROR;
		} else {
			session.put("admin", ad);
			return SUCCESS;
		}
		
	}
	public String logout(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		session.remove("admin");
		return SUCCESS;
	}
	public String useradmin(){
		String curPageStr=curPage;
		pc=userService.getUserOfPage(curPageStr);
		return SUCCESS;
	}
	
	public String sysArticalAdmin(){
		String curPageStr=curPage;
		pc=artService.getData(curPageStr);
		return SUCCESS;
	}
	public String sysCategoryAdmin(){
		String curPageStr=curPage;
		pc=scService.getSysCategoryByPage(curPageStr);
		return SUCCESS;
	}
	public String userprofile(){
		
		@SuppressWarnings("unchecked")
		Set<Profile> pfs=userService.getUserById(userId).getProfiles();
		Iterator<Profile> it = pfs.iterator();
		if(it.hasNext())//取第一个值
		{
			pf=it.next();
		}
		return SUCCESS;
	}
	public String deleteuser(){
		userService.deleteUser(userId);
		return SUCCESS;
		
	}
	public String activeuser(){
		userService.activeUser(userId);
		return SUCCESS;
	}
	public String editSysCategory(){
		sc=scService.getById(scgId);
		return SUCCESS;
	}
	public String addSysCategory(){
		sc.setArticals(0);
		sc.setIsDelete(false);
		scService.insertSysCategory(sc);
		return SUCCESS;
	}
	public String deleteSysCategory(){
		scService.deleteSysCategory(scgId);
		return SUCCESS;
	}
	public String updateSysCategory(){
		sc.setIsDelete(false);
		scService.updateSysCategory(sc);
		return SUCCESS;
	}
	

}
