package cap.service.impl;

import java.util.List;

import cap.bean.Category;
import cap.bean.User;
import cap.dao.CategoryDAO;
import cap.service.CategoryService;
import cap.util.PageControl;

public class CategoryServiceImpl implements CategoryService {
	private CategoryDAO categoryDAO;
	

	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	@Override
	public List<Category> getByUserId(int userId) {
		// TODO Auto-generated method stub
		
		return categoryDAO.getCategoryByUserId(userId);
	}

	@Override
	public Category getById(int id) {
		// TODO Auto-generated method stub
		return categoryDAO.findById(id);
	}

	@Override
	public Category getByName(String cgName, int userId) {
		// TODO Auto-generated method stub
		return categoryDAO.getByName(cgName, userId);
	}

	@Override
	public int insertCategory(int userId, String cgName) {
		// TODO Auto-generated method stub
		Category category=new Category();
		User user=new User();
		user.setId(userId);
		category.setUser(user);
		category.setCategoryName(cgName);
		category.setArticals(1);
		category.setIsDelete(false);
		categoryDAO.save(category);
		return 0;
	}

	@Override
	public int updateCategory(Category category) {
		// TODO Auto-generated method stub
		categoryDAO.merge(category);
		return 0;
	}

	@Override
	public int deleteCategory(int cgId) {
		// TODO Auto-generated method stub
		Category category=new Category();
		category.setId(cgId);
		category.setIsDelete(true);
		categoryDAO.merge(category);
		return 0;
	}

	@Override
	public PageControl getCategoryByUserId(String curPageStr, int userId) {
		// TODO Auto-generated method stub
		int total=categoryDAO.findAll().size();
		PageControl pc=new PageControl(curPageStr, total);
		List uList=categoryDAO.getCategoryByPageUserId(pc.getCurPage(), pc.getPageSize(), userId);
		pc.setList(uList);
		return pc;
	}

	@Override
	public PageControl getCategoryByPage(String curPageStr, int size) {
		// TODO Auto-generated method stub
		int total=categoryDAO.findAll().size();
		PageControl pc=new PageControl(curPageStr, total);
		List uList=categoryDAO.getCategoryByPage(pc.getCurPage(), pc.getPageSize());
		pc.setList(uList);
		return pc;
	}

}
