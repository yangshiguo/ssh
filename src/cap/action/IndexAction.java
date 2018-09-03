package cap.action;

import java.util.List;

import cap.bean.Article;
import cap.bean.SysCategory;
import cap.bean.User;
import cap.service.ArticleService;
import cap.service.CounterService;
import cap.service.SysCategoryService;
import cap.util.PageControl;

import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	private ArticleService artService;
	private SysCategoryService scService;
	private CounterService counterService;
	private List<SysCategory> scList ;
	private List<User> ulist;
	private List<Article> artList ;
	private List<Article> tenList;
	private PageControl pc;	
	private String curPageStr;
	private long count;
	
	public List<SysCategory> getScList() {
		return scList;
	}
	public void setScList(List<SysCategory> scList) {
		this.scList = scList;
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

	public String getCurPageStr() {
		return curPageStr;
	}

	public void setCurPageStr(String curPageStr) {
		this.curPageStr = curPageStr;
	}

	public PageControl getPc() {
		return pc;
	}

	public void setPc(PageControl pc) {
		this.pc = pc;
	}

	public List<User> getUlist() {
		return ulist;
	}

	public void setUlist(List<User> ulist) {
		this.ulist = ulist;
	}
		
	public CounterService getCounterService() {
		return counterService;
	}
	public void setCounterService(CounterService counterService) {
		this.counterService = counterService;
	}
	
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public String index(){
		scList=scService.getAllSysCategory();
		ulist=artService.getActiveUser(2);
		tenList=artService.topTenArticle();				
		pc=artService.getData(curPageStr);	
		count=counterService.getCounter().getNum();
		return SUCCESS;
	}
	
	
	

}
