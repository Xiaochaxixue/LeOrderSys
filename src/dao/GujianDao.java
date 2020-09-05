package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.gujian;
import utils.ConnectionFactory;

public class GujianDao {

	public List<gujian> findAllguJianInfo() {
		// TODO Auto-generated method stub
		List<gujian> guJians = new ArrayList<gujian>();
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet  rs = null;
		
		try {
			//② 准备SQL语句
			String sql = "select * from gujian";
			//③ 获取集装箱或者说是车
			 preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			//索引从1开始
			//④执行SQL,获取执行后的结果,查询的结果封装在ResultSet
			rs = preparedStatement.executeQuery();
			
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体，封装该行数据
			while (rs.next()) {
				gujian Gujian = new gujian();
				Gujian.setGunum(rs.getString("gunum"));
				Gujian.setUid(rs.getString("uid"));
				Gujian.setCnum(rs.getString("cnum"));
				Gujian.setGuversion(rs.getString("guversion"));
				Gujian.setGuname(rs.getString("guname"));
				Gujian.setGustate(rs.getInt("gustate"));
				Gujian.setGups(rs.getString("gups"));
				Gujian.setQfile(rs.getString("qfile"));
				Gujian.setRuDate(rs.getString("ruDate"));
				
				guJians.add(Gujian);
			}
			return guJians;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		System.out.println("findAllguJianInfo()未正常进入数据库进行查询");
		return null;
	}

	public void updateGujianInfoByObj(gujian guJian) {
		// TODO Auto-generated method stub
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement =null;
		
		try {
			//② 准备SQL语句
			String sql = "UPDATE gujian SET uid = ?,cnum = ?,guversion = ?,guname = ?,gustate = ?,gups = ?,qfile = ?,ruDate = ? WHERE gunum = ? ";
			//③ 获取集装箱或者说是车
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,guJian.getUid());
			preparedStatement.setString(2,guJian.getCnum());
			preparedStatement.setString(3,guJian.getGuversion());
			preparedStatement.setString(4,guJian.getGuname());
			preparedStatement.setInt(5,guJian.getGustate());
			preparedStatement.setString(6,guJian.getGups());
			preparedStatement.setString(7,guJian.getQfile());
			preparedStatement.setString(8,guJian.getRuDate());
			preparedStatement.setString(9,guJian.getGunum());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
	}

	public void addGujianInfoByObj(gujian guJian) {
		// TODO Auto-generated method stub
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement =null;
		
		try {
			//② 准备SQL语句
			String sql = "INSERT INTO gujian(gunum,uid,cnum,guversion,guname,gustate,gups,qfile,ruDate) VALUES(?,?,?,?,?,?,?,?,?)";
			//③ 获取集装箱或者说是车
			 preparedStatement = connection.prepareStatement(sql);
			 preparedStatement.setString(1, guJian.getGunum());
			 preparedStatement.setString(2, guJian.getUid());
			 preparedStatement.setString(3, guJian.getCnum());
			 preparedStatement.setString(4, guJian.getGuversion());
			 preparedStatement.setString(5, guJian.getGuname());
			 preparedStatement.setInt(6, guJian.getGustate());
			 preparedStatement.setString(7, guJian.getGups());
			 preparedStatement.setString(8, guJian.getQfile());
			 preparedStatement.setString(9, guJian.getRuDate());
			//④执行SQL
			preparedStatement.executeUpdate();
			System.out.println("==========数据库addGujianInfoByObj===========");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
	}

	
	public static gujian findGujianInfoByGunum(String gunum) {
		// TODO Auto-generated method stub
		gujian guJian = new gujian();
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet  rs = null;
		
		try {
			String sql = "select * from gujian where gunum='"+gunum+"'";
			//③ 获取集装箱或者说是车
			 preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			//索引从1开始
			//④执行SQL,获取执行后的结果,查询的结果封装在ResultSet
			rs = preparedStatement.executeQuery();
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体，封装该行数据
			while(rs.next()){
				guJian.setGunum(rs.getString("gunum"));
				guJian.setUid(rs.getString("uid"));
				guJian.setCnum(rs.getString("cnum"));
				guJian.setGuversion(rs.getString("guversion"));
				guJian.setGuname(rs.getString("guname"));
				guJian.setGustate(rs.getInt("gustate"));
				guJian.setGups(rs.getString("gups"));
				guJian.setQfile(rs.getString("qfile"));
				guJian.setRuDate(rs.getString("ruDate"));
				
				return guJian;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			System.out.println("findGujianInfoByGunum()2正常进入数据库进行查询");
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		
		return null;
	}

	public void updateGujianInfoByObjNoneFile(gujian guJian) {
		// TODO Auto-generated method stub
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement =null;
		
		try {
			//② 准备SQL语句
			String sql = "UPDATE gujian SET uid = ?,cnum = ?,guversion = ?,guname = ?,gustate = ?,gups = ? WHERE gunum = ? ";
			//③ 获取集装箱或者说是车
			preparedStatement = connection.prepareStatement(sql);
			
			
			preparedStatement.setString(1,guJian.getUid());
			System.out.println("guJian.getUid()"+guJian.getUid());
			
			preparedStatement.setString(2,guJian.getCnum());
			System.out.println("guJian.getCnum()"+guJian.getCnum());
			
			preparedStatement.setString(3,guJian.getGuversion());
			System.out.println("guJian.getGuversion()"+guJian.getGuversion());
			
			preparedStatement.setString(4,guJian.getGuname());
			System.out.println("guJian.getGuname()"+guJian.getGuname());
			
			preparedStatement.setInt(5,guJian.getGustate());
			System.out.println("guJian.getGustate()"+guJian.getGustate());
			
			preparedStatement.setString(6,guJian.getGups());
			System.out.println("guJian.getGups()"+guJian.getGups());
			
			preparedStatement.setString(7,guJian.getGunum());
			System.out.println("guJian.getGunum()"+guJian.getGunum());
			//④执行SQL
			preparedStatement.executeUpdate();
			System.out.println("==========数据库updateGujianInfoByObjNoneFile()===========");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
	}
	

	public void updateGujianFileByGunum(String fileNewName, String gunum) {
		// TODO Auto-generated method stub
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement =null;
		try {
			//② 准备SQL语句
			String sql = "UPDATE gujian SET qfile = ? WHERE gunum = ? ";
			//③ 获取集装箱或者说是车
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,fileNewName);
			preparedStatement.setString(2,gunum);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
		
	}
	
	
}
