package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import bean.user;
import utils.ConnectionFactory;

public class UserDao {
	public user findByuserNameAndPass(String userName,String password){
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet  rs = null;
		try {
			//② 准备SQL语句
			String sql = "select * from user where uid =? and paw=?";
			//③ 获取集装箱或者说是车
			 preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			//索引从1开始
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			
			//④执行SQL,获取执行后的结果,查询的结果封装在ResultSet
			rs = preparedStatement.executeQuery();
			
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体，封装该行数据
			while (rs.next()) {
				user User = new user();
				//每一行的数据封装在一个实体bean中，根据字段名获取字段值，注意该字段是什么类型，就get什么类型
				User.setUid(rs.getString("uid"));
				User.setPaw(rs.getString("paw"));
				User.setType(rs.getInt("type"));
				System.out.println("uid:"+rs.getString("uid")+" paw:"+rs.getString("paw")+" type:"+rs.getInt("type"));
				//System.out.println("uid:"+User.getUid()+" paw:"+User.getPaw()+" type:"+User.getUid());
				return User;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		return null;
	}

	public void addUserInfoByObj(user User) {
		// TODO Auto-generated method stub
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement =null;
		try {
			//② 准备SQL语句
			String sql = "INSERT INTO user(uid,paw,type) VALUES(?,?,?) ";
			//③ 获取集装箱或者说是车
			 preparedStatement = connection.prepareStatement(sql);
			//索引从1开始
			preparedStatement.setString(1,User.getUid());
			preparedStatement.setString(2,User.getPaw());
			preparedStatement.setInt(3,User.getType());
			//④执行SQL
			preparedStatement.executeUpdate();
			System.out.println("==========数据库addUserInfoByObj()===========");
			System.out.println("User.getType():"+User.getType());
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
	}

	public void updateUserInfoByObj(user User) {
		// TODO Auto-generated method stub
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement =null;
		try {
			//② 准备SQL语句
			String sql = "UPDATE user SET paw = ? WHERE uid = ?";
			//③ 获取集装箱或者说是车
			 preparedStatement = connection.prepareStatement(sql);
			//索引从1开始
			
			preparedStatement.setString(1,User.getPaw());
			preparedStatement.setString(2,User.getUid());
			
			//④执行SQL
			preparedStatement.executeUpdate();
			System.out.println("==========数据库updateUserInfoByObj()===========");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
	}
}
