package cap.action;

import java.util.Map;

import cap.bean.Category;
import cap.bean.User;
import cap.service.CategoryService;
import cap.util.PageControl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CategoryAction extends ActionSupport {
	private String curPage;
	private int userId;
	private PageControl pc ;
	private int cgId;
	private Category cg;
	
	private CategoryService cgService;

	public CategoryService getCgService() {
		return cgService;
	}

	public void setCgService(CategoryService cgService) {
		this.cgService = cgService;
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
	

	public PageControl getPc() {
		return pc;
	}

	public void setPc(PageControl pc) {
		this.pc = pc;
	}

	public int getCgId() {
		return cgId;
	}

	public void setCgId(int cgId) {
		this.cgId = cgId;
	}
	

	public Category getCg() {
		return cg;
	}

	public void setCg(Category cg) {
		this.cg = cg;
	}

	public String manage(){		
		String curPageStr = curPage;
		pc = cgService.getCategoryByUserId(curPageStr, userId);		
		return SUCCESS;
	}
	public String edit(){
		cg=cgService.getById(cgId);
		return SUCCESS;
	}
	public String update(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		User u=new User();
		u.setId(userId);
		cg.setUser(u);
		cg.setArticals(0);
		cg.setIsDelete(false);
		try {
			cgService.updateCategory(cg);
			session.put("succUpdateMsg", "分类更新成功");
		} catch (Exception e) {
			session.put("errorUpdateMsg", "分类更新失败");
		}		
		return SUCCESS;
	}
	
	

}
