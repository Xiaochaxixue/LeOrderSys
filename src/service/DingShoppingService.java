package service;

import java.util.ArrayList;
import java.util.List;

import bean.dingshopping;
import dao.DingShoppingDao;

public class DingShoppingService {

	public List<dingshopping> findAllSelectedDingShoppingByUid(String uid) {
		// TODO Auto-generated method stub
		List<dingshopping> dingShoppings = new ArrayList<dingshopping>();
		DingShoppingDao dingShoppingDao = new DingShoppingDao();
		dingShoppings = dingShoppingDao.findAllSelectedDingShoppingByUid(uid);
		return dingShoppings;
	}

	public List<dingshopping> findAllNoSelectDingShopping() {
		// TODO Auto-generated method stub
		List<dingshopping> dingShoppings = new ArrayList<dingshopping>();
		DingShoppingDao dingShoppingDao = new DingShoppingDao();
		dingShoppings = dingShoppingDao.findAllNoSelectDingShopping();
		return dingShoppings;
	}

	public void addDingShoppingByObj(dingshopping dingShopping) {
		// TODO Auto-generated method stub
		/*dingshopping dingShopping = new dingshopping();*/
		DingShoppingDao dingShoppingDao = new DingShoppingDao();
		dingShoppingDao.addDingShoppingByObj(dingShopping);
	}

	public List<dingshopping> findAllDingShoppingInfo() {
		// TODO Auto-generated method stub
		List<dingshopping> dingShoppings = new ArrayList<dingshopping>();
		DingShoppingDao dingShoppingDao = new DingShoppingDao();
		dingShoppings = dingShoppingDao.findAllDingShoppingInfo();
		return dingShoppings;
	}

	public void setDdanNumByCnum(String ddanNum,String cnum) {
		// TODO Auto-generated method stub
		DingShoppingDao dingShoppingDao = new DingShoppingDao();
		dingShoppingDao.setDdanNumByCnum(ddanNum, cnum);
	}

	public float getTotalpriceByCnum(String cnum) {
		// TODO Auto-generated method stub
		float Total;
		DingShoppingDao dingShoppingDao = new DingShoppingDao();
		Total = dingShoppingDao.getTotalpriceByCnum(cnum);
		return Total;
	}

	public dingshopping findRepeatDingShoppingInfo(String cnum, String uid) {
		// TODO Auto-generated method stub
		dingshopping dingShopping = new dingshopping();
		DingShoppingDao dingShoppingDao = new DingShoppingDao();
		dingShopping = dingShoppingDao.findRepeatDingShoppingInfo(cnum,uid);
		return dingShopping;
	}

	public void resetDingShoppingInfo(String uid, String cnum, int number, Float total) {
		// TODO Auto-generated method stub
		DingShoppingDao dingShoppingDao = new DingShoppingDao();
		dingShoppingDao.resetDingShoppingInfo(uid, cnum,number,total);
	}

	

}
