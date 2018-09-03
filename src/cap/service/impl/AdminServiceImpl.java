package cap.service.impl;

import java.util.List;

import cap.bean.Admin;
import cap.bean.User;
import cap.dao.AdminDAO;
import cap.dao.UserDAO;
import cap.service.AdminService;
import cap.util.PageControl;

public class AdminServiceImpl implements AdminService {
	private AdminDAO adminDAO;
	private UserDAO userDAO;
	

	public void setAdminDAO(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}
	

	public UserDAO getUserDAO() {
		return userDAO;
	}


	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}


	@Override
	public Admin login(Admin admin) {
		// TODO Auto-generated method stub
		Admin admin1=(Admin) adminDAO.findByExample(admin).get(0);
		return admin1;
				
		
	}

	@Override
	public int activeUser(int uId) {
		// TODO Auto-generated method stub
		User user=userDAO.findById(uId);
		user.setIsDelete(false);
		User u1=userDAO.merge(user);
		if(u1!=null)
			return 1;
		else
			return 0;
	}

	@Override
	public PageControl getUserOfPage(String curPageStr) {
		// TODO Auto-generated method stub
		int total=userDAO.findAll().size();
		PageControl pc=new PageControl(curPageStr, total);
		List uList=userDAO.getUserByPage(pc.getCurPage(), pc.getPageSize());
		pc.setList(uList);
		// TODO Auto-generated method stub
		return pc;
	}

	@Override
	public PageControl getSysCategoryByPage(String curPageStr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteUser(int uId) {
		User user=userDAO.findById(uId);
		user.setIsDelete(true);
		User u1=userDAO.merge(user);
		if(u1!=null)
			return 1;
		else
			return 0;
	}

	@Override
	public int deleteArtical(int artId) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public long getArticleCount() {
		// TODO Auto-generated method stub
		return adminDAO.getArticleCount();
	}


	@Override
	public long getCmtCount() {
		// TODO Auto-generated method stub
		return adminDAO.getCmtCount();
	}


	@Override
	public long getUserCount() {
		// TODO Auto-generated method stub
		return adminDAO.getUserCount();
	}

}
