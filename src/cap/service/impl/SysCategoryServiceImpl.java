package cap.service.impl;

import java.util.List;

import cap.bean.SysCategory;
import cap.dao.SysCategoryDAO;
import cap.service.SysCategoryService;
import cap.util.PageControl;

public class SysCategoryServiceImpl implements SysCategoryService {
	private SysCategoryDAO scDAO;
	
	

	public void setScDAO(SysCategoryDAO scDAO) {
		this.scDAO = scDAO;
	}

	@Override
	public List<SysCategory> getAllSysCategory() {
		// TODO Auto-generated method stub
		return scDAO.findAll();
	}

	@Override
	public SysCategory getById(int id) {
		// TODO Auto-generated method stub
		return scDAO.findById(id);
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return scDAO.findAll().size();
	}

	@Override
	public PageControl getSysCategoryByPage(String curPageStr) {
		int total=scDAO.findAll().size();
		PageControl pc=new PageControl(curPageStr, total);
		List uList=scDAO.getSysCategoryByPage(pc.getCurPage(), pc.getPageSize());
		pc.setList(uList);
		return pc;
	}

	@Override
	public SysCategory getByName(String scgName) {
		// TODO Auto-generated method stub
		SysCategory sc=new SysCategory();
		sc.setCategoryName(scgName);
		return (SysCategory) scDAO.findByExample(sc).get(0);
	}

	@Override
	public int updateSysCategory(SysCategory sc) {
		// TODO Auto-generated method stub
		scDAO.merge(sc);
		return 0;
	}

	@Override
	public int insertSysCategory(SysCategory sc) {
		scDAO.save(sc);
		return 0;
	}

	@Override
	public int deleteSysCategory(int scgId) {
		// TODO Auto-generated method stub
		SysCategory sc=scDAO.findById(scgId);
		sc.setIsDelete(true);
		scDAO.merge(sc);
		return 0;
	}

}
