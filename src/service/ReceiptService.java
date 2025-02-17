package service;

import java.util.ArrayList;
import java.util.List;

import bean.receipt;
import dao.ReceiptDao;

public class ReceiptService {
	/**
	 * 发票管理service层
	 */
	public List<receipt> findAllReceiptsByUid(String uid) {
		// TODO Auto-generated method stub
		List<receipt> receipts = new ArrayList<receipt>();
		ReceiptDao receiptDao = new ReceiptDao();
		receipts = receiptDao.findAllReceiptsByUid(uid);
		return receipts;
	}

	public receipt findReceiptIsExit(String uid, String title) {
		// TODO Auto-generated method stub
		receipt Receipt = new receipt();
		ReceiptDao receiptDao = new ReceiptDao();
		Receipt = receiptDao.findReceiptIsExit(uid,title);
		return Receipt;
	}

	public void addReceiptInfoByObj(receipt Receipt) {
		// TODO Auto-generated method stub
		ReceiptDao receiptDao = new ReceiptDao();
		receiptDao.addReceiptInfoByObj(Receipt);
	}

	public receipt findReceiptIsExitByObj(receipt receipt) {
		// TODO Auto-generated method stub
		receipt Receipt = new receipt();
		ReceiptDao receiptDao = new ReceiptDao();
		Receipt = receiptDao.findReceiptIsExitByObj(receipt);
		return Receipt;
	}

	public void addReceiptInfoByObjPlus(receipt receipt) {
		// TODO Auto-generated method stub
		ReceiptDao receiptDao = new ReceiptDao();
		receiptDao.addReceiptInfoByObjPlus(receipt);
	}

	public List<receipt> findReceiptPInfoByUid(String uid) {
		// TODO Auto-generated method stub
		List<receipt> receipts = new ArrayList<receipt>();
		ReceiptDao receiptDao = new ReceiptDao();
		receipts = receiptDao.findReceiptPInfoByUid(uid);
		return receipts;
	}

	public List<receipt> findReceiptQInfoByUid(String uid) {
		// TODO Auto-generated method stub
		List<receipt> receipts = new ArrayList<receipt>();
		ReceiptDao receiptDao = new ReceiptDao();
		receipts = receiptDao.findReceiptQInfoByUid(uid);
		return receipts;
	}

}
