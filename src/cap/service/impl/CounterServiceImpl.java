package cap.service.impl;

import cap.bean.Counter;
import cap.dao.CounterDAO;
import cap.service.CounterService;

public class CounterServiceImpl implements CounterService {
	private CounterDAO counterDAO;
	

	public void setCounterDAO(CounterDAO counterDAO) {
		this.counterDAO = counterDAO;
	}

	@Override
	public Counter getCounter() {
		// TODO Auto-generated method stub
		return counterDAO.findById(1);
	}

	@Override
	public int setNum(int num) {
		// TODO Auto-generated method stub
		Counter cnt=counterDAO.findById(1);
		long count=cnt.getNum();
		cnt.setNum(++count);
		counterDAO.merge(cnt);
		return 0;
	}

}
