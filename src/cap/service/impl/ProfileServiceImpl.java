package cap.service.impl;

import cap.bean.Profile;
import cap.bean.User;
import cap.dao.ProfileDAO;
import cap.service.ProfileService;

public class ProfileServiceImpl implements ProfileService {
	private ProfileDAO profileDAO;
	

	public void setProfileDAO(ProfileDAO profileDAO) {
		this.profileDAO = profileDAO;
	}

	@Override
	public Profile getByuserId(int userId) {
		// TODO Auto-generated method stub		
		Profile p=(Profile) profileDAO.findByUserId(userId);
		return p;
	}

	@Override
	public int updateProfile(Profile pf) {		
		profileDAO.merge(pf);
		return 0;
	}

	@Override
	public int insertProfile(Profile profile) {
		// TODO Auto-generated method stub
		profileDAO.save(profile);
		return 0;
	}

}
