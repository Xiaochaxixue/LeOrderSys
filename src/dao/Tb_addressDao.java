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

	public List<tb_address> findAllAddressInfoByUid(String uid) {
		// TODO Auto-generated method stub
		/**
		 * 获取所有的地址信息
		 */
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
		return null;
	}

}
