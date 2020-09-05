package service;

import java.util.ArrayList;
import java.util.List;

import bean.dingdan;
import dao.DingDanDao;

public class DingDanService {

	public void addDingDanInfoByObj(dingdan dingDan) {
		// TODO Auto-generated method stub
		DingDanDao dingDanDao = new DingDanDao();
		dingDanDao.addDingDanInfoByObj(dingDan);
	}

	public List<dingdan> findAllDingDanInfo() {
		// TODO Auto-generated method stub
		List<dingdan> dingDans = new ArrayList<dingdan>();
		DingDanDao dingDanDao = new DingDanDao();
		dingDans = dingDanDao.findAllDingDanInfo();
		return dingDans;
	}

	public void setNextStepByObj(dingdan dingDan) {
		// TODO Auto-generated method stub
		DingDanDao dingDanDao = new DingDanDao();
		dingDanDao.setNextStepByObj(dingDan);
	}

}
