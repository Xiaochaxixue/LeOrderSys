package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.tianprice;
import utils.ConnectionFactory;


public class TianPriceDao {
	public List<tianprice> findAllTianPriceInfo(){
		
		List<tianprice> tianPrices = new ArrayList<tianprice>();
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet  rs = null;
		try {
			//② 准备SQL语句
			String sql = "select * from tianprice";
			//③ 获取集装箱或者说是车
			 preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			//索引从1开始
			
			//④执行SQL,获取执行后的结果,查询的结果封装在ResultSet
			rs = preparedStatement.executeQuery();
			
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体，封装该行数据
			while (rs.next()) {
				tianprice tianPrice = new tianprice();
				//每一行的数据封装在一个实体bean中，根据字段名获取字段值，注意该字段是什么类型，就get什么类型
				tianPrice.setTiantype(rs.getInt("tiantype"));
				tianPrice.setPrice(rs.getFloat("price"));
				tianPrices.add(tianPrice);
			}
			return tianPrices;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//System.out.println("findAllClientInfo()1未正常进入数据库进行查询");
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		System.out.println("findAllTianPriceInfo()2正常进入数据库,但是表内无数据");
		return null;
	}

	public tianprice findTianPriceInfoByTiantype(int tianType) {
		// TODO Auto-generated method stub
		tianprice tianPrice = new tianprice();
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet  rs = null;
		try {
			//② 准备SQL语句
			String sql = "select * from tianprice where tiantype='"+tianType+"'";
			//③ 获取集装箱或者说是车
			 preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			//索引从1开始
			
			//④执行SQL,获取执行后的结果,查询的结果封装在ResultSet
			rs = preparedStatement.executeQuery();
			
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体，封装该行数据
			while(rs.next()){
				tianPrice.setTiantype(rs.getInt("tiantype"));
				tianPrice.setPrice(rs.getFloat("price"));
				System.out.println("==========findTianPriceInfoByTiantype()===========");
				System.out.println("结果集"+" tiantype:"+rs.getInt("tiantype")+" price:"+rs.getFloat("price"));
				System.out.println("对象存储里面"+" tiantype:"+rs.getInt("tiantype")+" price:"+rs.getFloat("price"));
				
				return tianPrice;
			}
			//每一行的数据封装在一个实体bean中，根据字段名获取字段值，注意该字段是什么类型，就get什么类型
			
		} catch (SQLException e) {
			System.out.println("findTianPriceInfoByTiantype()1未正常进入数据库进行查询");
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		System.out.println("findTianPriceInfoByTiantype()2正常进入数据库进行查询,但没查询到结果");
		return null;
	}

	public void addTianPriceInfoByObj(tianprice tianPrice) {
		// TODO Auto-generated method stub
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement =null;
		try {
			//② 准备SQL语句
			String sql = "INSERT INTO tianprice(tiantype,price) VALUES(?,?) ";
			//③ 获取集装箱或者说是车
			 preparedStatement = connection.prepareStatement(sql);
			//索引从1开始
			preparedStatement.setInt(1,tianPrice.getTiantype());
			preparedStatement.setFloat(2,tianPrice.getPrice());
			//④执行SQL
			preparedStatement.executeUpdate();
			System.out.println("==========数据库addTianPriceInfoByObj===========");
			System.out.println("Client.getCoraddress():"+tianPrice.getTiantype()+"cm");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
	}

	public void updateTianPriceInfoByObj(tianprice tianPrice) {
		// TODO Auto-generated method stub
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement =null;
		try {
			//② 准备SQL语句
			String sql = "UPDATE tianprice SET price = ? WHERE tiantype = ?";
			//③ 获取集装箱或者说是车
			preparedStatement = connection.prepareStatement(sql);
			//索引从1开始
			preparedStatement.setFloat(1, tianPrice.getPrice());
			preparedStatement.setInt(2, tianPrice.getTiantype());
			//④执行SQL
			preparedStatement.executeUpdate();
			System.out.println("==========数据库updateTianPriceInfoByObj()===========");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
	}
		
}
