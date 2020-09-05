package service;

import java.util.ArrayList;
import java.util.List;

import bean.client;
import dao.ClientDao;

public class ClientService {

	public List<client> findAllClientInfo() {
		// TODO Auto-generated method stub
		List<client> clients = new ArrayList<client>();
		ClientDao clientDao = new ClientDao();
		//进入数据库进行查询数据
		clients = clientDao.findAllClientInfo();
		
		return clients;
	}

	public static client findClientInfoByUid(String uid) {
		// TODO Auto-generated method stub
		client Client = new client();//创建client对象，传递到dao层
		ClientDao clientDao = new ClientDao();
		Client = clientDao.findClientInfoByUid(uid);//调用dao层的方法得到
		return Client;
	}

	public static void addClientInfoByObj(client Client) {
		// TODO Auto-generated method stub
		ClientDao clientDao = new ClientDao();
		clientDao.addClientInfoByObj(Client);
	}

	public static void updateClientInfoByObj(client Client) {
		// TODO Auto-generated method stub
		ClientDao clientDao = new ClientDao();
		clientDao.updateClientInfoByObj(Client);
	}
	

}
