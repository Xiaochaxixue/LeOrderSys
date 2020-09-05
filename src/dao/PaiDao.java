package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.pai;
import utils.ConnectionFactory;

public class PaiDao {

	public pai findPaiInfo() {
		// TODO Auto-generated method stub
		pai Pai = new pai();
		
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet  rs = null;
		try{
			//② 准备SQL语句
			String sql = "select * from pai";
			//③ 获取集装箱或者说是车
			 preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			//索引从1开始
			
			//④执行SQL,获取执行后的结果,查询的结果封装在ResultSet
			rs = preparedStatement.executeQuery();
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体，封装该行数据
			while (rs.next()) {
				Pai.setPprice(rs.getFloat("pprice"));
				System.out.println(rs.getFloat("pprice"));
				return Pai;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//System.out.println("findAllClientInfo()1正常进入数据库进行查询");
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		System.out.println("findPaiInfo()2正常进入数据库,但是表内无数据");
		
		return null;
	}

	public void updatePaiInfoByPprice(float ppriceS,float pprice2) {
		// TODO Auto-generated method stub
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement =null;
		try{
			//② 准备SQL语句
			String sql = "UPDATE pai SET pprice = ? ";
			//③ 获取集装箱或者说是车
			 preparedStatement = connection.prepareStatement(sql);
			//索引从1开始
			 preparedStatement.setFloat(1, pprice2);
			 //preparedStatement.setFloat(2, ppriceS);
			 
			//④执行SQL
			preparedStatement.executeUpdate();
			System.out.println("==========数据库updatePaiInfoByObj()===========");
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
	}

}
