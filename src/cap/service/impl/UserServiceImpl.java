package cap.service.impl;

import java.util.List;

import cap.bean.User;
import cap.dao.UserDAO;
import cap.service.UserService;
import cap.util.PageControl;

public class UserServiceImpl implements UserService {
	private UserDAO userDAO;
	

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return userDAO.login(user);
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return userDAO.findById(id);
	}

	@Override
	public int getIdByuserName(String username) {
		// TODO Auto-generated method stub
		User user=null;
		try {
			user=(User) userDAO.findByUsername(username).get(0);
			return user.getId();
		} catch (Exception e) {
			// TODO: handle exception
			user=null;		    
		}
		return 0;
		
	}

	@Override
	public User getByEmail(String email) {
		// TODO Auto-generated method stub
		User u=new User();
		u.setEmail(email);
		User user=null;
		try {
			List uList=(List) userDAO.findByExample(u);
			if(uList!=null||uList.size()>0){
				user=(User) uList.get(0);
			}else
			{
				user=null;
			}
		} catch (Exception e) {
			user=null;
		}
		
		return user;
	}

	@Override
	public int register(String email, String username, String password) {
		// TODO Auto-generated method stub
		User u=new User();
		u.setEmail(email);
		u.setUsername(username);
		u.setPassword(password);
		u.setIsApplied(false);
		u.setIsDelete(false);
		u.setIsProfile(false);
		userDAO.save(u);
		return 0;
	}

	@Override
	public int setIsAppliedById(int id) {
		// TODO Auto-generated method stub
		User u=userDAO.findById(id);
		if(u!=null){
			u.setIsApplied(true);
		}	
		userDAO.merge(u);
		return 0;
	}

	@Override
	public User getByIdPwd(int userId, String password) {
		// TODO Auto-generated method stub
		User user=new User();
		user.setId(userId);
		user.setPassword(password);
		return (User) userDAO.findByExample(user).get(0);
	}

	@Override
	public int updatePwdById(int userId, String password) {
		// TODO Auto-generated method stub
		User u=userDAO.findById(userId);
		if(u!=null){
			u.setPassword(password);
		}	
		User u1=userDAO.merge(u);
		if(u1!=null)
			return 1;
		else
			return 0;
	}

	@Override
	public int setIsProfile(int userId) {
		// TODO Auto-User user=userDAO.findById(uId);
		User user=userDAO.findById(userId);
		user.setIsProfile(true);
		User u1=userDAO.merge(user);
		if(u1!=null)
			return 1;
		else
			return 0;
	}

	@Override
	public PageControl getUserOfPage(String curPageStr) {
		int total=userDAO.findAll().size();
		PageControl pc=new PageControl(curPageStr, total);
		List uList=userDAO.getUserByPage(pc.getCurPage(), pc.getPageSize());
		pc.setList(uList);
		// TODO Auto-generated method stub
		return pc;
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
	public int activeUser(int uId) {
		User user=userDAO.findById(uId);
		user.setIsDelete(false);
		User u1=userDAO.merge(user);
		if(u1!=null)
			return 1;
		else
			return 0;
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userDAO.findAll();
	}

	@Override
	public List<User> getActiveUser(int num) {
		// TODO Auto-generated method stub
		return userDAO.getActiveUser(num);
	}

}
