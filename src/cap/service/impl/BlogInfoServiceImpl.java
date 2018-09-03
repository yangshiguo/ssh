package cap.service.impl;

import cap.bean.BlogInfo;
import cap.bean.User;
import cap.dao.BlogInfoDAO;
import cap.service.BlogInfoService;

public class BlogInfoServiceImpl implements BlogInfoService {
	private BlogInfoDAO blogInfoDAO;
	

	public void setBlogInfoDAO(BlogInfoDAO blogInfoDAO) {
		this.blogInfoDAO = blogInfoDAO;
	}

	@Override
	public BlogInfo getByuserId(int userId) {
		// TODO Auto-generated method stub
		BlogInfo bi=new BlogInfo();
		User user=new User();
		user.setId(userId);
		bi.setUser(user);
		return (BlogInfo) blogInfoDAO.findByExample(bi).get(0);
	}

	@Override
	public int updateBlogInfo(BlogInfo bi) {
		blogInfoDAO.merge(bi);
		return 0;
	}

	@Override
	public int insertBlogInfo(int userId, String blogName, String description,
			String annoucement) {
		// TODO Auto-generated method stub
		BlogInfo bi=new BlogInfo();
		User user=new User();
		user.setId(userId);
		bi.setUser(user);
		bi.setBlogName(blogName);
		bi.setDescription(description);
		bi.setAnnoucement(annoucement);
		blogInfoDAO.save(bi);
		return 0;
	}

}
