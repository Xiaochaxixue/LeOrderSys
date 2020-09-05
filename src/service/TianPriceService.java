package service;

import java.util.ArrayList;
import java.util.List;

import bean.tianprice;
import dao.TianPriceDao;

public class TianPriceService {

	public List<tianprice> findAllTianPriceInfo() {
		// TODO Auto-generated method stub
		List<tianprice> tianPrices = new ArrayList<tianprice>();
		TianPriceDao tianPriceDao = new TianPriceDao();
		tianPrices=tianPriceDao.findAllTianPriceInfo();
		return tianPrices;
	}

	public static tianprice findTianPriceInfoByTiantype(int tianType) {
		// TODO Auto-generated method stub
		tianprice tianPrice = new tianprice();
		TianPriceDao tianPriceDao = new TianPriceDao();
		tianPrice = tianPriceDao.findTianPriceInfoByTiantype(tianType);
		return tianPrice;
	}

	public void addTianPriceInfoByObj(tianprice tianPrice) {
		// TODO Auto-generated method stub
		TianPriceDao tianPriceDao = new TianPriceDao();
		tianPriceDao.addTianPriceInfoByObj(tianPrice);
	}

	public void updateTianPriceInfoByObj(tianprice tianPrice) {
		// TODO Auto-generated method stub
		TianPriceDao tianPriceDao = new TianPriceDao();
		tianPriceDao.updateTianPriceInfoByObj(tianPrice);
	}
}
