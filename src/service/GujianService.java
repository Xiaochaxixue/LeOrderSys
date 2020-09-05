package service;

import java.util.ArrayList;
import java.util.List;

import bean.gujian;
import dao.GujianDao;

public class GujianService {

	public List<gujian> findAllguJianInfo() {
		// TODO Auto-generated method stub
		List<gujian> guJians = new ArrayList<gujian>();
		GujianDao gujianDao = new GujianDao();
		guJians = gujianDao.findAllguJianInfo();
		return guJians;
	}

	

	public void updateGujianInfoByObj(gujian guJian) {
		// TODO Auto-generated method stub
		/**
		 * 对固件信息进行数据更新
		 */
		GujianDao gujianDao = new GujianDao();
		gujianDao.updateGujianInfoByObj(guJian);
	}

	public void addGujianInfoByObj(gujian guJian) {
		// TODO Auto-generated method stub
		/**
		 * 对固件信息进行数据添加
		 */
		GujianDao gujianDao = new GujianDao();
		gujianDao.addGujianInfoByObj(guJian);
	}



	
	public static gujian findGujianInfoByGunum(String gunum) {
		// TODO Auto-generated method stub
		gujian guJian = new gujian();
		guJian = GujianDao.findGujianInfoByGunum(gunum);
		return guJian;
	}



	public void updateGujianInfoByObjNoneFile(gujian guJian) {
		// TODO Auto-generated method stub
		GujianDao gujianDao = new GujianDao();
		gujianDao.updateGujianInfoByObjNoneFile(guJian);
	}



	public void updateGujianFileByGunum(String fileNewName, String gunum) {
		// TODO Auto-generated method stub
		GujianDao gujianDao = new GujianDao();
		gujianDao.updateGujianFileByGunum(fileNewName,gunum);
	}

}
