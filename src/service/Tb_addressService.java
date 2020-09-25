package service;

import java.util.ArrayList;
import java.util.List;

import bean.tb_address;
import dao.Tb_addressDao;

public class Tb_addressService {

	public List<tb_address> findAllAddressInfoByUid(String uid) {
		// TODO Auto-generated method stub
		List<tb_address> tb_addresss = new ArrayList<tb_address>(); 
		Tb_addressDao tb_addressDao = new Tb_addressDao();
		tb_addresss = tb_addressDao.findAllAddressInfoByUid(uid);
		return tb_addresss;
	}

	public tb_address findTb_addressIsExitByObj(tb_address Tb_address) {
		// TODO Auto-generated method stub
		tb_address Tb_address2 = new tb_address();
		Tb_addressDao tb_addressDao = new Tb_addressDao();
		Tb_address2 = tb_addressDao.findTb_addressIsExitByObj(Tb_address);
		return Tb_address2;
	}

	public void addTb_addressServlet(tb_address tb_address) {
		// TODO Auto-generated method stub
		Tb_addressDao tb_addressDao = new Tb_addressDao();
		tb_addressDao.addTb_addressServlet(tb_address);
	}

	public void deleteTb_addressInfoByObj(tb_address tb_address) {
		// TODO Auto-generated method stub
		Tb_addressDao tb_addressDao = new Tb_addressDao();
		tb_addressDao.deleteTb_addressInfoByObj(tb_address);
	}

	public void updateAddressByNewAndOldObj(tb_address tb_addressFromSession, tb_address tb_address) {
		// TODO Auto-generated method stub
		Tb_addressDao tb_addressDao = new Tb_addressDao();
		tb_address Oldtb_address = tb_addressFromSession;
		tb_address Newtb_address = tb_address;
		tb_addressDao.updateAddressByNewAndOldObj(Oldtb_address,Newtb_address);
	}
}
