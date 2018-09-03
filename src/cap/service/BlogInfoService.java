package cap.service;

import cap.bean.BlogInfo;

public interface BlogInfoService {

	public abstract BlogInfo getByuserId(int userId);

	public abstract int updateBlogInfo(BlogInfo bi);

	public abstract int insertBlogInfo(int userId, String blogName,
			String description, String annoucement);

}