package service;

import bean.user;
import dao.UserDao;

public class UserService {
	public user findByuserNameAndPass(String userName,String password){
		user User=new user();
		
		UserDao UserDao=new UserDao();
		
		User=UserDao.findByuserNameAndPass(userName, password);//获取数据库的user对象
		return User;
	}

	public static void addUserInfoByObj(user User) {
		// TODO Auto-generated method stub
		UserDao UserDao=new UserDao();
		UserDao.addUserInfoByObj(User);
	}

	public static void updateUserInfoByObj(user User) {
		// TODO Auto-generated method stub
		UserDao UserDao=new UserDao();
		UserDao.updateUserInfoByObj(User);
	}
}
