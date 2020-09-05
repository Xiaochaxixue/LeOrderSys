package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.shoppinginfo;
import utils.ConnectionFactory;

public class ShoppingInfoDao {

	public List<shoppinginfo> findAllShoppingInfo() {
		// TODO Auto-generated method stub
		List<shoppinginfo> shoppingInfos = new ArrayList<shoppinginfo>();
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet  rs = null;
		
		try {
			//② 准备SQL语句
			String sql = "select * from shoppinginfo";
			//③ 获取集装箱或者说是车
			 preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			
			//④执行SQL,获取执行后的结果,查询的结果封装在ResultSet
			rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				shoppinginfo shoppingInfo = new shoppinginfo();
				
				shoppingInfo.setCnum(rs.getString("cnum"));
				shoppingInfo.setCtype(rs.getString("ctype"));
				shoppingInfo.setCname(rs.getString("cname"));
				shoppingInfo.setGuige(rs.getString("guige"));
				shoppingInfo.setDanwei(rs.getString("danwei"));
				shoppingInfo.setPrice(rs.getFloat("price"));
				shoppingInfo.setTiantype(rs.getInt("tiantype"));
				shoppingInfo.setPt(rs.getString("pt"));
				shoppingInfo.setPnum(rs.getInt("pnum"));
				shoppingInfo.setSstate(rs.getInt("sstate"));
				shoppingInfo.setSselect(rs.getInt("sselect"));
				shoppingInfo.setPicture(rs.getString("picture"));
				shoppingInfo.setRuDate(rs.getString("ruDate"));
				
				shoppingInfos.add(shoppingInfo);
			}
			return shoppingInfos;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("findAllShoppingInfo()1未正常进入数据库,发生异常");
			e.printStackTrace();
		}finally {
			//System.out.println("findAllClientInfo()1未正常进入数据库进行查询");
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		System.out.println("shoppinginfos正常进入数据库,已拿取数据库中的数据");
		return null;
	}

	public shoppinginfo findShoppingInfoByCnum(String cnum) {
		// TODO Auto-generated method stub
		shoppinginfo shoppingInfo = new shoppinginfo();
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet  rs = null;
		try {
			String sql = "select * from shoppinginfo where cnum='"+cnum+"'";
			//③ 获取集装箱或者说是车
			 preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			//索引从1开始
			
			//④执行SQL,获取执行后的结果,查询的结果封装在ResultSet
			rs = preparedStatement.executeQuery();
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体，封装该行数据
			while(rs.next()){
				
				shoppingInfo.setCnum(rs.getString("cnum"));
				shoppingInfo.setCtype(rs.getString("ctype"));
				shoppingInfo.setCname(rs.getString("cname"));
				shoppingInfo.setGuige(rs.getString("guige"));
				shoppingInfo.setDanwei(rs.getString("danwei"));
				shoppingInfo.setPrice(rs.getFloat("price"));
				shoppingInfo.setTiantype(rs.getInt("tiantype"));
				shoppingInfo.setPt(rs.getString("pt"));
				shoppingInfo.setPnum(rs.getInt("pnum"));
				shoppingInfo.setSstate(rs.getInt("sstate"));
				shoppingInfo.setSselect(rs.getInt("sselect"));
				shoppingInfo.setPicture(rs.getString("picture"));
				shoppingInfo.setRuDate(rs.getString("ruDate"));
				
				return shoppingInfo;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("findShoppingInfoByCnum()1未正常进入数据库进行查询");
			e.printStackTrace();
		}finally {
			System.out.println("findShoppingInfoByCnum()2正常进入数据库进行查询");
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		return null;
	}

	public void addShoppingInfoByObj(shoppinginfo shoppingInfo) {
		// TODO Auto-generated method stub
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement =null;
		try {
			//② 准备SQL语句
			String sql = "INSERT INTO shoppinginfo(cnum,ctype,cname,guige,danwei,price,tiantype,pt,pnum,sstate,sselect,picture,ruDate) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			//③ 获取集装箱或者说是车
			 preparedStatement = connection.prepareStatement(sql);
			 preparedStatement.setString(1, shoppingInfo.getCnum());
			 preparedStatement.setString(2, shoppingInfo.getCtype());
			 preparedStatement.setString(3, shoppingInfo.getCname());
			 preparedStatement.setString(4, shoppingInfo.getGuige());
			 preparedStatement.setString(5, shoppingInfo.getDanwei());
			 preparedStatement.setFloat(6, shoppingInfo.getPrice());
			 preparedStatement.setInt(7, shoppingInfo.getTiantype());
			 preparedStatement.setString(8, shoppingInfo.getPt());
			 preparedStatement.setInt(9, shoppingInfo.getPnum());
			 preparedStatement.setInt(10, shoppingInfo.getSstate());
			 preparedStatement.setInt(11, shoppingInfo.getSselect());
			 preparedStatement.setString(12, shoppingInfo.getPicture());
			 preparedStatement.setString(13, shoppingInfo.getRuDate());
			//④执行SQL
			preparedStatement.executeUpdate();
			System.out.println("==========数据库addShoppingInfoByObj===========");
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
	}

	public void updateShoppingInfoByObj(shoppinginfo shoppingInfo) {
		// TODO Auto-generated method stub
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement =null;
		try {
			//② 准备SQL语句
			String sql = "UPDATE shoppinginfo SET ctype = ?,cname = ?,guige = ?,danwei = ?,price = ?,tiantype = ?,pt = ?,pnum = ?,sstate = ?,sselect = ? WHERE cnum = ? ";
			//③ 获取集装箱或者说是车
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,shoppingInfo.getCtype());
			System.out.println(shoppingInfo.getCtype());
			preparedStatement.setString(2,shoppingInfo.getCname());
			System.out.println(shoppingInfo.getCname());
			preparedStatement.setString(3,shoppingInfo.getGuige());
			System.out.println(shoppingInfo.getGuige());
			preparedStatement.setString(4,shoppingInfo.getDanwei());
			System.out.println(shoppingInfo.getDanwei());
			preparedStatement.setFloat(5,shoppingInfo.getPrice());
			System.out.println(shoppingInfo.getPrice());
			preparedStatement.setInt(6,shoppingInfo.getTiantype());
			System.out.println(shoppingInfo.getTiantype());
			preparedStatement.setString(7,shoppingInfo.getPt());
			System.out.println(shoppingInfo.getPt());
			preparedStatement.setInt(8,shoppingInfo.getPnum());
			System.out.println(shoppingInfo.getPnum());
			preparedStatement.setInt(9,shoppingInfo.getSstate());
			System.out.println(shoppingInfo.getSstate());
			preparedStatement.setInt(10,shoppingInfo.getSselect());
			System.out.println(shoppingInfo.getSselect());
			/*preparedStatement.setString(11,shoppingInfo.getPicture());
			preparedStatement.setString(12,shoppingInfo.getRuDate());*/
			preparedStatement.setString(11,shoppingInfo.getCnum());
			System.out.println(shoppingInfo.getCnum());
			
			preparedStatement.executeUpdate();
			System.out.println("==========数据库updateShoppingInfoByObj()===========");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
	}

	public void updateShoppingFileByCnum(String fileNewName, String cnum) {
		// TODO Auto-generated method stub
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement =null;
		try {
			//② 准备SQL语句
			String sql = "UPDATE shoppinginfo SET picture = ? WHERE cnum = ? ";
			//③ 获取集装箱或者说是车
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,fileNewName);
			preparedStatement.setString(2,cnum);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
	}
	
}	
