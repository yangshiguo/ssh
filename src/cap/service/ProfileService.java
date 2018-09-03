package cap.service;

import cap.bean.Profile;

public interface ProfileService {

	public abstract Profile getByuserId(int userId);

	public abstract int updateProfile(Profile pf);

	public abstract int insertProfile(Profile pf);

}