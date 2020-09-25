package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.receipt;
import utils.ConnectionFactory;

public class ReceiptDao {
	/**
	 * 发票管理的Dao层
	 */
	public List<receipt> findAllReceiptsByUid(String uid) {
		// TODO Auto-generated method stub
		List<receipt> receipts = new ArrayList<receipt>();
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet  rs = null;
		//② 准备SQL语句
		try {
			String sql = "select * from receipt where uid=?";
			//③ 获取集装箱或者说是车
			 preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			//索引从1开始
			 preparedStatement.setString(1, uid);
			//④执行SQL,获取执行后的结果,查询的结果封装在ResultSet
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体，封装该行数据
			rs = preparedStatement.executeQuery();
			while(rs.next()){
				receipt Receipt = new receipt();
				Receipt.setUid(rs.getString("uid"));
				//Receipt.setDdanNum(rs.getString("registecall"));
				Receipt.setTitle(rs.getString("title"));
				Receipt.setTax(rs.getString("tax"));
				Receipt.setBank(rs.getString("bank"));
				Receipt.setBanknumber(rs.getString("banknumber"));
				Receipt.setRegisteadd(rs.getString("registeadd"));
				Receipt.setRegistecall(rs.getString("registecall"));
				receipts.add(Receipt);
			}
			System.out.println("findAllReceiptsByUid()发票管理成功拿取所有的发票，并没有发生错误");
			return receipts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		System.out.println("findAllReceiptsByUid()成功进入数据库，但没有拿到相应的数据，返回null值");
		return null;
	}

	public receipt findReceiptIsExit(String uid, String title) {
		// TODO Auto-generated method stub
		receipt Receipt = new receipt();
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet  rs = null;
		try {
			//② 准备SQL语句
			String sql = "select * from receipt where uid=? and title = ?";
			//③ 获取集装箱或者说是车
			 preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			//索引从1开始
			 preparedStatement.setString(1, uid);
			 preparedStatement.setString(2, title);
			//④执行SQL,获取执行后的结果,查询的结果封装在ResultSet
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体，封装该行数据
			rs = preparedStatement.executeQuery();
			while(rs.next()){
				Receipt.setUid(rs.getString("uid"));
				Receipt.setTitle(rs.getString("title"));
				Receipt.setTax(rs.getString("tax"));
				return Receipt;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//System.out.println("findAllClientInfo()1未正常进入数据库进行查询");
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		System.out.println("findReceiptIsExit()未从数据库中拿到相应的数据， 返回null值");
		return null;
	}

	public void addReceiptInfoByObj(receipt Receipt) {
		// TODO Auto-generated method stub
		//① 获取连接
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement =null;
		try {
			//② 准备SQL语句
			String sql = "INSERT INTO receipt(uid,title,tax) VALUES(?,?,?) ";
			//③ 获取集装箱或者说是车
			 preparedStatement = connection.prepareStatement(sql);
			//索引从1开始
			 preparedStatement.setString(1,Receipt.getUid());
			 preparedStatement.setString(2,Receipt.getTitle());
			 preparedStatement.setString(3,Receipt.getTax());
			//④执行SQL
			preparedStatement.executeUpdate();
			System.out.println("==========addReceiptInfoByObj()===========");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
	}

	public receipt findReceiptIsExitByObj(receipt receipt) {
		// TODO Auto-generated method stub
		receipt Receipt = new receipt();
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet  rs = null;
		try {
			//② 准备SQL语句
			String sql = "select * from receipt where uid=? and title = ? and tax=? and banknumber=?";
			//③ 获取集装箱或者说是车
			 preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			//索引从1开始
			 preparedStatement.setString(1, receipt.getUid());
			 preparedStatement.setString(2, receipt.getTitle());
			 preparedStatement.setString(3, receipt.getTax());
			 preparedStatement.setString(4, receipt.getBanknumber());
			 
			//④执行SQL,获取执行后的结果,查询的结果封装在ResultSet
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体，封装该行数据
			rs = preparedStatement.executeQuery();
			while(rs.next()){
				//rs.getString("registecall")
				Receipt.setUid(rs.getString("uid"));
				Receipt.setTitle(rs.getString("title"));
				Receipt.setTax(rs.getString("tax"));
				Receipt.setBank(rs.getString("bank"));
				Receipt.setBanknumber(rs.getString("banknumber"));
				Receipt.setRegisteadd(rs.getString("registeadd"));
				Receipt.setRegistecall(rs.getString("registecall"));
				return Receipt;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//System.out.println("findAllClientInfo()1未正常进入数据库进行查询");
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		System.out.println("findReceiptIsExitByObj()未从数据库中拿到相应的数据， 返回null值");
		return null;
	}

	public void addReceiptInfoByObjPlus(receipt receipt) {
		// TODO Auto-generated method stub
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement =null;
		try {
			//② 准备SQL语句
			String sql = "INSERT INTO receipt(uid,title,tax,bank,banknumber,registeadd,registecall) VALUES(?,?,?,?,?,?,?) ";
			//③ 获取集装箱或者说是车
			 preparedStatement = connection.prepareStatement(sql);
			//索引从1开始
			 preparedStatement.setString(1,receipt.getUid());
			 preparedStatement.setString(2,receipt.getTitle());
			 preparedStatement.setString(3,receipt.getTax());
			 preparedStatement.setString(4, receipt.getBank());
			 preparedStatement.setString(5, receipt.getBanknumber());
			 preparedStatement.setString(6, receipt.getRegisteadd());
			 preparedStatement.setString(7, receipt.getRegistecall());
			//④执行SQL
			preparedStatement.executeUpdate();
			System.out.println("==========addReceiptInfoByObj()===========");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
	}
}
