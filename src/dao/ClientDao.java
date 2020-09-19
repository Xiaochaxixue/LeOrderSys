package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.client;
import utils.ConnectionFactory;


public class ClientDao {

	public List<client> findAllClientInfo() {
		// TODO Auto-generated method stub
		List<client> clients = new ArrayList<client>();
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet  rs = null;
		try {
			//② 准备SQL语句
			String sql = "select * from client";
			//③ 获取集装箱或者说是车
			 preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			//索引从1开始
			
			//④执行SQL,获取执行后的结果,查询的结果封装在ResultSet
			rs = preparedStatement.executeQuery();
			
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体，封装该行数据
			while (rs.next()) {
				client Client = new client();
				//每一行的数据封装在一个实体bean中，根据字段名获取字段值，注意该字段是什么类型，就get什么类型
				Client.setUid(rs.getString("uid"));
				Client.setPaw(rs.getString("paw"));
				Client.setCorname(rs.getString("corname"));
				Client.setCoraddress(rs.getString("coraddress"));
				Client.setContact(rs.getString("contact"));
				Client.setPhone(rs.getString("phone"));
				Client.setAddress0(rs.getString("address0"));
				Client.setAddress1(rs.getString("address1"));
				Client.setAddress2(rs.getString("address2"));
				/*System.out.println("==========findAllClientInfo()===========");
				System.out.println("uid:"+rs.getString("uid")+" paw:"+rs.getString("paw"));
				System.out.println("uid:"+Client.getUid()+" paw:"+Client.getPaw());*/
				clients.add(Client);
			}
			System.out.println("clients正常进入数据库,已拿取数据库中的数据");
			return clients;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		System.out.println("findAllClientInfo()2正常进入数据库,但是表内无数据");
		return null;
	}

	public client findClientInfoByUid(String Uid) {
		// TODO Auto-generated method stub
		client Client = new client();
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet  rs = null;
		try {
			//② 准备SQL语句
			String sql = "select * from client where uid='"+Uid+"'";
			//③ 获取集装箱或者说是车
			 preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			//索引从1开始
			
			//④执行SQL,获取执行后的结果,查询的结果封装在ResultSet
			rs = preparedStatement.executeQuery();
			
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体，封装该行数据
			while(rs.next()){
				Client.setUid(rs.getString("uid"));
				Client.setPaw(rs.getString("paw"));
				Client.setCorname(rs.getString("corname"));
				Client.setCoraddress(rs.getString("coraddress"));
				Client.setContact(rs.getString("contact"));
				Client.setPhone(rs.getString("phone"));
				Client.setAddress0(rs.getString("address0"));
				Client.setAddress1(rs.getString("address1"));
				Client.setAddress2(rs.getString("address2"));
				System.out.println("==========数据库findClientInfoByUid()===========");
				System.out.println("结果集"+" uid:"+rs.getString("uid")+" paw:"+rs.getString("paw"));
				System.out.println("对象存储里面"+" uid:"+Client.getUid()+" paw:"+Client.getPaw());
				//clients.add(Client);
				return Client;
			}
			//每一行的数据封装在一个实体bean中，根据字段名获取字段值，注意该字段是什么类型，就get什么类型
		} catch (SQLException e) {
			System.out.println("findClientInfoByUid()1未正常进入数据库进行查询");
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		System.out.println("findClientInfoByUid()2未正常进入数据库进行查询");
		return null;
	}

	public void addClientInfoByObj(client Client) {
		// TODO Auto-generated method stub
		//① 获取连接（数据库地址  用户名 密码）
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement =null;
		try {
			//② 准备SQL语句
			String sql = "INSERT INTO client(uid,paw,corname,coraddress,contact,phone,address0,address1,address2) VALUES(?,?,?,?,?,?,?,?,?) ";
			//③ 获取集装箱或者说是车
			 preparedStatement = connection.prepareStatement(sql);
			//索引从1开始
			preparedStatement.setString(1,Client.getUid());
			preparedStatement.setString(2,Client.getPaw());
			preparedStatement.setString(3,Client.getCorname());
			preparedStatement.setString(4,Client.getCoraddress());
			preparedStatement.setString(5,Client.getContact() );
			preparedStatement.setString(6,Client.getPhone());
			preparedStatement.setString(7,Client.getAddress0());
			preparedStatement.setString(8,Client.getAddress1());
			preparedStatement.setString(9,Client.getAddress2());
			//④执行SQL
			preparedStatement.executeUpdate();
			System.out.println("==========数据库addClientInfoByObj()===========");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
	}

	public void updateClientInfoByObj(client Client) {//更新数据库
		// TODO Auto-generated method stub
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement =null;
		try {
			//② 准备SQL语句
			String sql = "UPDATE client SET paw = ?,corname = ?,coraddress = ?,contact = ?,phone = ?,address0 = ?,address1 = ?,address2 = ? WHERE uid = ?";
			//③ 获取集装箱或者说是车
			 preparedStatement = connection.prepareStatement(sql);
			//索引从1开始
			preparedStatement.setString(1,Client.getPaw());
			preparedStatement.setString(2,Client.getCorname());
			preparedStatement.setString(3,Client.getCoraddress());
			preparedStatement.setString(4,Client.getContact() );
			preparedStatement.setString(5,Client.getPhone());
			preparedStatement.setString(6,Client.getAddress0());
			preparedStatement.setString(7,Client.getAddress1());
			preparedStatement.setString(8,Client.getAddress2());
			preparedStatement.setString(9,Client.getUid());
			//④执行SQL
			preparedStatement.executeUpdate();
			System.out.println("==========数据库updateClientInfoByObj()===========");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
	}

	public client findClientPictureIsExitByUid(String uid) {
		// TODO Auto-generated method stub
		//① 获取连接（数据库地址  用户名 密码）
		client Client = new client();
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet  rs = null;
		try {
			//② 准备SQL语句
			String sql = "select * from client where uid=?";
			//③ 获取集装箱或者说是车
			 preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			//索引从1开始
			 preparedStatement.setString(1, uid);
			//④执行SQL,获取执行后的结果,查询的结果封装在ResultSet
			rs = preparedStatement.executeQuery();
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体，封装该行数据
			while (rs.next()) {
				Client.setUid(rs.getString("uid"));
				Client.setPaw(rs.getString("paw"));
				Client.setCorname(rs.getString("corname"));
				Client.setCoraddress(rs.getString("coraddress"));
				Client.setContact(rs.getString("contact"));
				Client.setPhone(rs.getString("phone"));
				Client.setAddress0(rs.getString("address0"));
				Client.setPicture(rs.getString("picture"));
				return Client;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("findClientPictureIsExitByUid()未正常进入数据库，发生异常。");
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		System.out.println("findClientPictureIsExitByUid()正常进入数据库，但未拿到相应的数据，返回值为null");
		return null;
	}
	
}
