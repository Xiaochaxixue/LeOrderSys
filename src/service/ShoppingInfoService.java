package service;

import java.util.ArrayList;
import java.util.List;

import bean.shoppinginfo;
import dao.ShoppingInfoDao;

public class ShoppingInfoService {

	public List<shoppinginfo> findAllShoppingInfo() {
		// TODO Auto-generated method stub
		List<shoppinginfo> shoppingInfos = new ArrayList<shoppinginfo>();
		shoppingInfos = null;
		ShoppingInfoDao shoppingInfoDao = new ShoppingInfoDao();
		shoppingInfos = shoppingInfoDao.findAllShoppingInfo();
		return shoppingInfos;
	}

	public shoppinginfo findShoppingInfoByCnum(String cnum) {
		// TODO Auto-generated method stub
		shoppinginfo shoppingInfo = new shoppinginfo(); 
		ShoppingInfoDao shoppingInfoDao = new ShoppingInfoDao();
		shoppingInfo = shoppingInfoDao.findShoppingInfoByCnum(cnum);
		return shoppingInfo;
	}

	public void addShoppingInfoByObj(shoppinginfo shoppingInfo) {
		// TODO Auto-generated method stub
		ShoppingInfoDao shoppingInfoDao = new ShoppingInfoDao();
		shoppingInfoDao.addShoppingInfoByObj(shoppingInfo);
	}

	public void updateShoppingInfoByObj(shoppinginfo shoppingInfo) {
		// TODO Auto-generated method stub
		ShoppingInfoDao shoppingInfoDao = new ShoppingInfoDao();
		shoppingInfoDao.updateShoppingInfoByObj(shoppingInfo);
	}

	public void updateShoppingFileByCnum(String fileNewName, String cnum) {
		// TODO Auto-generated method stub
		ShoppingInfoDao shoppingInfoDao = new ShoppingInfoDao();
		shoppingInfoDao.updateShoppingFileByCnum(fileNewName,cnum);
	}
	
	
}
