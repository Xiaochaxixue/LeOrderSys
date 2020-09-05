package service;

import bean.pai;
import dao.PaiDao;

public class PaiService {

	public pai findPaiInfo() {
		// TODO Auto-generated method stub
		PaiDao paiDao = new PaiDao();
		pai Pai = new pai();
		Pai = paiDao.findPaiInfo();
		return Pai;
	}

	public pai updatePaiInfoByPprice(float ppriceS,float pprice2) {
		// TODO Auto-generated method stub
		PaiDao paiDao = new PaiDao();
		paiDao.updatePaiInfoByPprice(ppriceS, pprice2);
		return null;
	}
	
}
