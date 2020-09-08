package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.dingdan;
import utils.ConnectionFactory;

public class DingDanDao {

	public void addDingDanInfoByObj(dingdan dingDan) {
		// TODO Auto-generated method stub
		//① 获取连接（数据库地址  用户名 密码）
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement =null;
		try {
			//② 准备SQL语句
			String sql = "INSERT INTO dingdan(ddanNum,uid,dealDate,totalprice,state) VALUES(?,?,?,?,?) ";
			//③ 获取集装箱或者说是车
			 preparedStatement = connection.prepareStatement(sql);
			//索引从1开始
			 preparedStatement.setString(1,dingDan.getDdanNum());
			 preparedStatement.setString(2,dingDan.getUid());
			 preparedStatement.setString(3,dingDan.getDealDate());
			 preparedStatement.setFloat(4,dingDan.getTotalprice());
			 preparedStatement.setInt(5,dingDan.getState());
			 preparedStatement.executeUpdate();
			 System.out.println("==========addDingDanInfoByObj()完成============");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
	}

	public List<dingdan> findAllDingDanInfo() {
		// TODO Auto-generated method stub
		List<dingdan> dingDans = new ArrayList<dingdan>();
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet  rs = null;
		
		try {
			//② 准备SQL语句
			String sql = "select * from dingdan";
			//③ 获取集装箱或者说是车
			 preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			//索引从1开始
			
			//④执行SQL,获取执行后的结果,查询的结果封装在ResultSet
			rs = preparedStatement.executeQuery();
			
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体，封装该行数据
			while (rs.next()) {
				dingdan dingDan = new dingdan();
				dingDan.setDdanNum(rs.getString("ddanNum"));
				dingDan.setUid(rs.getString("uid"));
				dingDan.setDealDate(rs.getString("dealDate"));
				dingDan.setTotalprice(rs.getFloat("totalprice"));
				dingDan.setPs(rs.getString("ps"));
				dingDan.setState(rs.getInt("state"));
				dingDan.setMakedealDate(rs.getString("makedealDate"));
				dingDan.setOpa(rs.getString("opa"));
				
				dingDans.add(dingDan);
			}
			System.out.println("dingDans正常进入数据库,已拿取数据库中的数据");
			return dingDans;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//System.out.println("findAllClientInfo()1未正常进入数据库进行查询");
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		return null;
	}

	public void setNextStepByObj(dingdan dingDan) {
		// TODO Auto-generated method stub
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement =null;
		
		try {
			//② 准备SQL语句
			String sql = "UPDATE dingdan SET ps = ?,state = ?,makedealDate = ?,opa = ? WHERE ddanNum = ? ";
			//③ 获取集装箱或者说是车
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,dingDan.getPs());
			preparedStatement.setInt(2, dingDan.getState());
			preparedStatement.setString(3, dingDan.getMakedealDate());
			preparedStatement.setString(4,dingDan.getOpa());
			preparedStatement.setString(5, dingDan.getDdanNum());
			
			preparedStatement.executeUpdate();//④执行SQL
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
		
	}

	public List<dingdan> findAllDingDanInfoByUid(String uid) {
		// TODO Auto-generated method stub
		List<dingdan> dingDans = new ArrayList<dingdan>();
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet  rs = null;
		
		try {
			//② 准备SQL语句
			String sql = "select * from dingdan where uid='"+uid+"'";
			//③ 获取集装箱或者说是车
			 preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			//索引从1开始
			
			//④执行SQL,获取执行后的结果,查询的结果封装在ResultSet
			rs = preparedStatement.executeQuery();
			
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体，封装该行数据
			while (rs.next()) {
				dingdan dingDan = new dingdan();
				dingDan.setDdanNum(rs.getString("ddanNum"));
				dingDan.setUid(rs.getString("uid"));
				dingDan.setDealDate(rs.getString("dealDate"));
				dingDan.setTotalprice(rs.getFloat("totalprice"));
				dingDan.setPs(rs.getString("ps"));
				dingDan.setState(rs.getInt("state"));
				dingDan.setMakedealDate(rs.getString("makedealDate"));
				dingDan.setOpa(rs.getString("opa"));
				
				dingDans.add(dingDan);
			}
			System.out.println("dingDans正常进入数据库,已拿取数据库中的数据");
			return dingDans;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//System.out.println("findAllClientInfo()1未正常进入数据库进行查询");
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		return null;
	}

	public void comfirmDingDanState(String ddanNum) {
		// TODO Auto-generated method stub
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement =null;
		
		try {
			//② 准备SQL语句
			String sql = "UPDATE dingdan SET sstate = ? WHERE ddanNum = ? ";
			//③ 获取集装箱或者说是车
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,2);
			preparedStatement.setString(2,ddanNum);
			
			preparedStatement.executeUpdate();//④执行SQL
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
	}
}
