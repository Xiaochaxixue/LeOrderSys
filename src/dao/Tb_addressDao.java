package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.tb_address;
import utils.ConnectionFactory;

public class Tb_addressDao {
	/**
	 * 获取所有的地址信息
	 */
	public List<tb_address> findAllAddressInfoByUid(String uid) {
		// TODO Auto-generated method stub
		
		List<tb_address> tb_addresss = new ArrayList<tb_address>();
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet  rs = null;
		try {
			//② 准备SQL语句
			String sql = "select * from tb_address where uid=?";
			//③ 获取集装箱或者说是车
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			//索引从1开始
			preparedStatement.setString(1, uid);
			//④执行SQL,获取执行后的结果,查询的结果封装在ResultSet
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体，封装该行数据
			rs = preparedStatement.executeQuery();
			while(rs.next()){
				tb_address Tb_address = new tb_address();
				//rs.getString("address");
				Tb_address.setUid(rs.getString("uid"));
				Tb_address.setAcceptor(rs.getString("acceptor"));
				Tb_address.setTel(rs.getString("tel"));
				Tb_address.setAddress(rs.getString("address"));
				tb_addresss.add(Tb_address);
			}
			System.out.println("findAllAddressInfoByUid()地址管理成功拿取了所有的地址信息，并没有发生错误");
			return tb_addresss; 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		System.out.println("findAllAddressInfoByUid()成功进入数据库，但没有拿到相应的数据，返回null值");
		return null;
	}

	public tb_address findTb_addressIsExitByObj(tb_address tb_address) {
		// TODO Auto-generated method stub
		tb_address Tb_address = new tb_address();
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet  rs = null;
		try {
			//② 准备SQL语句
			String sql = "select * from tb_address where uid=? and acceptor = ? and tel=? and address=?";
			//③ 获取集装箱或者说是车
			 preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			//索引从1开始
			 preparedStatement.setString(1, tb_address.getUid());
			 preparedStatement.setString(2, tb_address.getAcceptor());
			 preparedStatement.setString(3, tb_address.getTel());
			 preparedStatement.setString(4, tb_address.getAddress());
			//④执行SQL,获取执行后的结果,查询的结果封装在ResultSet
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体，封装该行数据
			rs = preparedStatement.executeQuery();
			while(rs.next()){
				//rs.getString("address");
				Tb_address.setUid(rs.getString("uid"));
				Tb_address.setAcceptor(rs.getString("acceptor"));
				Tb_address.setTel(rs.getString("tel"));
				Tb_address.setAddress(rs.getString("address"));
				return Tb_address;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//System.out.println("findAllClientInfo()1未正常进入数据库进行查询");
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		System.out.println("findTb_addressIsExitByObj()未从数据库中拿到相应的数据， 返回null值");
		return null;
	}

	public void addTb_addressServlet(tb_address tb_address) {
		// TODO Auto-generated method stub
		//① 获取连接
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement =null;
		try {
			//② 准备SQL语句
			String sql = "INSERT INTO tb_address(uid,acceptor,tel,address) VALUES(?,?,?,?) ";
			//③ 获取集装箱或者说是车
			 preparedStatement = connection.prepareStatement(sql);
			//索引从1开始
			 preparedStatement.setString(1, tb_address.getUid());
			 preparedStatement.setString(2, tb_address.getAcceptor());
			 preparedStatement.setString(3, tb_address.getTel());
			 preparedStatement.setString(4, tb_address.getAddress());
			//④执行SQL
			preparedStatement.executeUpdate();
			System.out.println("==========addTb_addressServlet()===========");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
	}

	public void deleteTb_addressInfoByObj(tb_address tb_address) {
		// TODO Auto-generated method stub
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement =null;
		
		try {
			//② 准备SQL语句
			String sql = "DELETE FROM tb_address WHERE uid=? and acceptor=? and tel=? and address=?";
			//③ 获取集装箱或者说是车
			 preparedStatement = connection.prepareStatement(sql);
			//索引从1开始
			preparedStatement.setString(1,tb_address.getUid());
			preparedStatement.setString(2,tb_address.getAcceptor());
			preparedStatement.setString(3,tb_address.getTel());
			preparedStatement.setString(4,tb_address.getAddress());
			//执行该sql语句
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
	}

	public void updateAddressByNewAndOldObj(tb_address oldtb_address, tb_address newtb_address) {
		// TODO Auto-generated method stub
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement =null;
		try {
			//② 准备SQL语句
			String sql = "UPDATE tb_address SET acceptor = ?,tel = ?,address = ? WHERE uid=? and acceptor=? and tel=? and address=?";
			//③ 获取集装箱或者说是车
			 preparedStatement = connection.prepareStatement(sql);
			//索引从1开始
			preparedStatement.setString(1,newtb_address.getAcceptor());
			preparedStatement.setString(2,newtb_address.getTel());
			preparedStatement.setString(3,newtb_address.getAddress());
			
			preparedStatement.setString(4,oldtb_address.getUid());
			preparedStatement.setString(5,oldtb_address.getAcceptor());
			preparedStatement.setString(6,oldtb_address.getTel());
			preparedStatement.setString(7,oldtb_address.getAddress());
			//④执行SQL
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
	}

}
