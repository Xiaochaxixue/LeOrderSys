package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.dingshopping;
import utils.ConnectionFactory;

public class DingShoppingDao {

	public List<dingshopping> findAllSelectedDingShoppingByUid(String uid) {
		// TODO Auto-generated method stub
		/**
		 * 进行多表查询，即商品信息表和固件信息表
		 * 通过uid进行表数据的查询，并将找到的
		 * 数据封装到dingShoppings（List集合）中
		 */
		List<dingshopping> dingShoppings = new ArrayList<dingshopping>();
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet  rs = null;
		try {
			//② 准备SQL语句
			//sql语句为多表查询sql语句
			String sql = "SELECT gunum,s.cnum,s.cname,s.ruDate,s.danwei,s.price,s.picture,s.sstate,s.ctype,pt FROM gujian g,shoppinginfo s WHERE g.cnum=s.cnum AND g.uid= ? ";
			
			//③ 获取集装箱或者说是车
			 preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			//索引从1开始
			preparedStatement.setString(1, uid);
			//④执行SQL,获取执行后的结果,查询的结果封装在ResultSet
			rs = preparedStatement.executeQuery();
			
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体，封装该行数据
			while (rs.next()) {
				dingshopping dingShopping = new dingshopping();
				/**
				 * rs结果集的相关操作
				 * 将rs结果集中的数据存入到dingShopping对象中
				 * 将封装好的数据对象放入List集合中
				 */
				/**
				 * 将ctype，pt添加到list集合中
				 * 新添需求2020/09/07 15：47
				 */
				dingShopping.setCtype(rs.getString("ctype"));
				dingShopping.setPt(rs.getString("pt"));
				
				dingShopping.setGunum(rs.getString("gunum"));
				
				dingShopping.setCnum(rs.getString("cnum"));
				
				dingShopping.setCname(rs.getString("cname"));
				
				dingShopping.setRuDate(rs.getString("ruDate"));
				
				dingShopping.setDanwei(rs.getString("danwei"));
				
				dingShopping.setPrice(rs.getFloat("price"));
				
				dingShopping.setPicture(rs.getString("picture"));
				
				dingShopping.setSstate(rs.getInt("sstate"));
				
				dingShoppings.add(dingShopping);
			}
			System.out.println("已经拿取了所有的不可选的订单商品数据，dingShoppings正常进入数据库");
			return dingShoppings;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//System.out.println("findAllClientInfo()1未正常进入数据库进行查询");
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		System.out.println("findAllSelectedDingShoppingByUid()正常进入数据库,但是未从表中拿到数据return null!");
		return null;
	}

	public List<dingshopping> findAllNoSelectDingShopping() {
		// TODO Auto-generated method stub
		/**
		 * 进行多表查询，即商品信息表和固件信息表
		 * 通过uid进行表数据的查询，并将找到的
		 * 数据封装到dingShoppings（List集合）中
		 */
		List<dingshopping> dingShoppings = new ArrayList<dingshopping>();
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet  rs = null;
		try {
			//② 准备SQL语句
			//sql语句为多表查询sql语句
			//String sql = "SELECT cnum,ruDate,danwei,price,picture,sstate,ctype,pt FROM shoppinginfo WHERE sselect=1 ";
			/*"SELECT gunum,s.cnum,s.cname,s.ruDate,s.danwei,s.price,s.picture,s.sstate,s.ctype,pt FROM gujian g,shoppinginfo s WHERE g.cnum=s.cnum AND g.uid= ? "*/
			String sql = "SELECT gunum,s.cnum,s.cname,s.ruDate,s.danwei,s.price,s.picture,s.sstate,s.ctype,pt FROM gujian g,shoppinginfo s WHERE g.cnum=s.cnum AND sselect=1 ";
			//③ 获取集装箱或者说是车
			 preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			//索引从1开始
			/*preparedStatement.setString(1, );*/
			//④执行SQL,获取执行后的结果,查询的结果封装在ResultSet
			rs = preparedStatement.executeQuery();
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体，封装该行数据
			while (rs.next()) {
				dingshopping dingShopping = new dingshopping();
				/**
				 * rs结果集的相关操作
				 * 将rs结果集中的数据存入到dingShopping对象中
				 * 将封装好的数据对象放入List集合中
				 */
				/*dingShopping.setGunum(rs.getString("gunum"));
				System.out.println("dingShopping.setGunum:"+rs.getString("gunum"));*/
				dingShopping.setGunum(rs.getString("gunum"));
				dingShopping.setCname(rs.getString("cname"));
				System.out.println("gunum#cname:"+rs.getString("gunum")+"#"+rs.getString("cname"));
				
				dingShopping.setCtype(rs.getString("ctype"));
				dingShopping.setPt(rs.getString("pt"));
				System.out.println("ctype#pt:"+rs.getString("ctype")+"#"+rs.getString("pt"));
				
				dingShopping.setCnum(rs.getString("cnum"));
				System.out.println("dingShopping.setCnum:"+rs.getString("cnum"));
				
				dingShopping.setRuDate(rs.getString("ruDate"));
				System.out.println("dingShopping.setRuDate:"+rs.getString("ruDate"));
				
				dingShopping.setDanwei(rs.getString("danwei"));
				System.out.println("dingShopping.setDanwei:"+rs.getString("danwei"));
				
				dingShopping.setPrice(rs.getFloat("price"));
				System.out.println("dingShopping.setPrice:"+rs.getFloat("price"));
				
				dingShopping.setPicture(rs.getString("picture"));
				System.out.println("dingShopping.setPicture:"+rs.getString("picture"));
				
				dingShopping.setSstate(rs.getInt("sstate"));
				System.out.println("dingShopping.setSstate:"+rs.getInt("sstate"));
				
				System.out.println("============================");
				dingShoppings.add(dingShopping);
			}
			System.out.println("已经拿取了所有的可选的订单商品数据，dingShoppings正常进入数据库");
			return dingShoppings;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//System.out.println("findAllClientInfo()1未正常进入数据库进行查询");
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		System.out.println("findAllNoSelectDingShopping()正常进入数据库,但是未从表中拿到数据return null!");
		return null;
	}

	public void addDingShoppingByObj(dingshopping dingShopping) {
		// TODO Auto-generated method stub
		//① 获取连接
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement =null;
		
		try {
			//② 准备SQL语句
			String sql = "INSERT INTO dingshopping(picture,cnum,gunum,ruDate,danwei,price,sstate,number,total,ctype,pt,uid) VALUES(?,?,?,?,?,?,?,?,?,?,?,?) ";
			//③ 获取集装箱或者说是车
			 preparedStatement = connection.prepareStatement(sql);
			 
			//索引从1开始
			 preparedStatement.setString(1,dingShopping.getPicture());
			 preparedStatement.setString(2,dingShopping.getCnum());
			 preparedStatement.setString(3,dingShopping.getGunum());
			 preparedStatement.setString(4,dingShopping.getRuDate());
			 preparedStatement.setString(5,dingShopping.getDanwei());
			 preparedStatement.setFloat(6,dingShopping.getPrice());
			 preparedStatement.setInt(7,dingShopping.getSstate());
			 preparedStatement.setInt(8,dingShopping.getNumber());
			 preparedStatement.setFloat(9,dingShopping.getTotal());
			 preparedStatement.setString(10,dingShopping.getCtype());
			 preparedStatement.setString(11,dingShopping.getPt());
			 preparedStatement.setString(12,dingShopping.getUid());
			//④执行SQL
			preparedStatement.executeUpdate();
			System.out.println("==========数据库addDingShoppingByObj()===========");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
	}

	public List<dingshopping> findAllDingShoppingInfo() {
		// TODO Auto-generated method stub
		/**
		 * 查找所有的订单商品信息
		 * 将所有的订单商品信息返回
		 * 返回给service层
		 */
		List<dingshopping> dingShoppings = new ArrayList<dingshopping>();
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet  rs = null;
		
		try {
			//② 准备SQL语句
			//sql语句为多表查询sql语句
			String sql = "SELECT * FROM dingshopping WHERE ddanNum IS NULL";
			//③ 获取集装箱或者说是车
			 preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			//④执行SQL,获取执行后的结果,查询的结果封装在ResultSet
			rs = preparedStatement.executeQuery();
			//如有数据，就进入循环体，封装该行数据
			while (rs.next()) {
				dingshopping dingShopping = new dingshopping();
				/**
				 * rs结果集的相关操作
				 * 将rs结果集中的数据存入到dingShopping对象中
				 * 将封装好的数据对象放入List集合中
				 */
				//dingShopping.setGunum(rs.getString("gunum"));
				dingShopping.setCname(rs.getString("cname"));
				
				dingShopping.setCtype(rs.getString("ctype"));
				dingShopping.setPt(rs.getString("pt"));
				
				dingShopping.setDdanNum(rs.getString("ddanNum"));
				dingShopping.setUid(rs.getString("uid"));
				
				dingShopping.setGunum(rs.getString("gunum"));
				/*dingShopping.setGunum(rs.getString("gunum"));
				System.out.println("dingShopping.setGunum:"+rs.getString("gunum"));*/
				
				dingShopping.setCnum(rs.getString("cnum"));
				/*System.out.println("dingShopping.setCnum:"+rs.getString("cnum"));*/
				
				dingShopping.setSstate(rs.getInt("sstate"));
				/*System.out.println("dingShopping.setSstate:"+rs.getInt("sstate"));*/
				dingShopping.setSselect(rs.getInt("sselect"));
				
				dingShopping.setDanwei(rs.getString("danwei"));
				/*System.out.println("dingShopping.setDanwei:"+rs.getString("danwei"));*/
				
				int number = rs.getInt("number");
				dingShopping.setNumber(number);
				
				float price = rs.getFloat("price");
				dingShopping.setPrice(price);
				/*System.out.println("dingShopping.setPrice:"+rs.getFloat("price"));*/
				
				dingShopping.setPicture(rs.getString("picture"));
				/*System.out.println("dingShopping.setPicture:"+rs.getString("picture"));*/
				
				dingShopping.setRuDate(rs.getString("ruDate"));
				/*System.out.println("dingShopping.setRuDate:"+rs.getString("ruDate"));*/
				
				dingShopping.setTotal(price*number);
				
				/*System.out.println("============================");*/
				dingShoppings.add(dingShopping);
			}
			System.out.println("已经拿取了所有的购物车数据，dingShoppings正常进入数据库");
			return dingShoppings;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//System.out.println("findAllClientInfo()1未正常进入数据库进行查询");
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		System.out.println("findAllDingShoppingInfo()正常进入数据库,但是未从表中拿到数据return null!");
		return null;
	}

	public void setDdanNumByCnum(String ddanNum,String cnum) {
		// TODO Auto-generated method stub
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement =null;
		
		try {
			//② 准备SQL语句
			String sql = "UPDATE dingshopping SET ddanNum = ? WHERE ddanNum IS NULL AND cnum = ? ";
			//③ 获取集装箱或者说是车
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ddanNum);
			preparedStatement.setString(2, cnum);
			
			//④执行SQL
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
	}

	public float getTotalpriceByCnum(String cnum) {
		// TODO Auto-generated method stub
		float Total = 0;
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet  rs = null;
		
		try {
			String sql = "SELECT total FROM dingshopping WHERE ddanNum IS NULL AND cnum= ? ";
			
			//③ 获取集装箱或者说是车
			 preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			//索引从1开始
			preparedStatement.setString(1,cnum);
			//④执行SQL,获取执行后的结果,查询的结果封装在ResultSet
			rs = preparedStatement.executeQuery();
			
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体，封装该行数据
			while (rs.next()) {
				Total = rs.getFloat("total");
				System.out.println("rs.getFloat(total):"+rs.getFloat("total"));
				return Total;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//System.out.println("getTotalpriceByCnum()1未正常进入数据库进行查询");
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		return 0;
	}

	public dingshopping findRepeatDingShoppingInfo(String cnum, String uid) {
		// TODO Auto-generated method stub
		dingshopping dingShopping = new dingshopping();
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet  rs = null;
		
		try {
			//② 准备SQL语句
			//sql语句为多表查询sql语句
			String sql = "SELECT * FROM dingshopping WHERE ddanNum IS NULL AND cnum=? AND uid = ?";
			
			//③ 获取集装箱或者说是车
			 preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			//索引从1开始
			preparedStatement.setString(1, cnum);
			preparedStatement.setString(2, uid);
			//④执行SQL,获取执行后的结果,查询的结果封装在ResultSet
			rs = preparedStatement.executeQuery();
			
			//因为查询出来的结果包括表头信息，所以要指针下移一行，看是否有查询出来的数据
			//如有数据，就进入循环体，封装该行数据
			while (rs.next()) {
				/**
				 * rs结果集的相关操作
				 * 将rs结果集中的数据存入到dingShopping对象中
				 * 将封装好的数据对象放入List集合中
				 */
				/*dingShopping.setGunum(rs.getString("gunum"));
				System.out.println("dingShopping.setGunum:"+rs.getString("gunum"));*/
				dingShopping.setCtype(rs.getString("ctype"));
				dingShopping.setPt(rs.getString("pt"));
				System.out.println("ctype#pt:"+rs.getString("ctype")+"#"+rs.getString("pt"));
				
				dingShopping.setCnum(rs.getString("cnum"));
				System.out.println("dingShopping.setCnum:"+rs.getString("cnum"));
				
				dingShopping.setRuDate(rs.getString("ruDate"));
				System.out.println("dingShopping.setRuDate:"+rs.getString("ruDate"));
				
				dingShopping.setDanwei(rs.getString("danwei"));
				System.out.println("dingShopping.setDanwei:"+rs.getString("danwei"));
				
				dingShopping.setPrice(rs.getFloat("price"));
				System.out.println("dingShopping.setPrice:"+rs.getFloat("price"));
				
				dingShopping.setPicture(rs.getString("picture"));
				System.out.println("dingShopping.setPicture:"+rs.getString("picture"));
				
				dingShopping.setSstate(rs.getInt("sstate"));
				System.out.println("dingShopping.setSstate:"+rs.getInt("sstate"));
				
				dingShopping.setNumber(rs.getInt("number"));
				
				dingShopping.setTotal(rs.getFloat("total"));
				
				System.out.println("============================");
				//dingShoppings.add(dingShopping);
				return dingShopping;
			}
			/*System.out.println("已经拿取了所有的可选的订单商品数据，dingShoppings正常进入数据库");
			return dingShopping;*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//System.out.println("findAllClientInfo()1未正常进入数据库进行查询");
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		System.out.println("findRepeatDingShoppingInfo()正常进入数据库,但是未从表中拿到数据return null!");
		return null;
	}

	public void resetDingShoppingInfo(String uid, String cnum, int number, Float total) {
		// TODO Auto-generated method stub
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement =null;
		
		try {
			//② 准备SQL语句
			String sql = "UPDATE dingshopping SET number = ? ,total=? WHERE ddanNum IS NULL AND uid=? AND cnum = ? ";
			//③ 获取集装箱或者说是车
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			preparedStatement.setFloat(2, total);
			preparedStatement.setString(3, uid);
			preparedStatement.setString(4, cnum);
			
			//④执行SQL
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
	}

	public List<dingshopping> findAllDingShoppingInfoByDdanNum(String ddanNum) {
		// TODO Auto-generated method stub
		List<dingshopping> dingShoppings = new ArrayList<dingshopping>();
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet  rs = null;
		
		try {
			//② 准备SQL语句
			//sql语句为多表查询sql语句
			String sql = "SELECT * FROM dingshopping WHERE ddanNum=?";
			
			//③ 获取集装箱或者说是车
			 preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			 
			 preparedStatement.setString(1, ddanNum);
			//④执行SQL,获取执行后的结果,查询的结果封装在ResultSet
			rs = preparedStatement.executeQuery();
			//如有数据，就进入循环体，封装该行数据
			while (rs.next()) {
				dingshopping dingShopping = new dingshopping();
				/**
				 * rs结果集的相关操作
				 * 将rs结果集中的数据存入到dingShopping对象中
				 * 将封装好的数据对象放入List集合中
				 */
				//dingShopping.setGunum(rs.getString("gunum"));
				dingShopping.setCname(rs.getString("cname"));
				
				dingShopping.setCtype(rs.getString("ctype"));
				
				dingShopping.setPt(rs.getString("pt"));
				
				dingShopping.setDdanNum(rs.getString("ddanNum"));
				
				dingShopping.setUid(rs.getString("uid"));
				
				dingShopping.setGunum(rs.getString("gunum"));
				
				dingShopping.setCnum(rs.getString("cnum"));
				/*System.out.println("dingShopping.setCnum:"+rs.getString("cnum"));*/
				
				dingShopping.setSstate(rs.getInt("sstate"));
				/*System.out.println("dingShopping.setSstate:"+rs.getInt("sstate"));*/
				dingShopping.setSselect(rs.getInt("sselect"));
				
				dingShopping.setDanwei(rs.getString("danwei"));
				/*System.out.println("dingShopping.setDanwei:"+rs.getString("danwei"));*/
				
				int number = rs.getInt("number");
				dingShopping.setNumber(number);
				
				float price = rs.getFloat("price");
				dingShopping.setPrice(price);
				/*System.out.println("dingShopping.setPrice:"+rs.getFloat("price"));*/
				
				dingShopping.setPicture(rs.getString("picture"));
				/*System.out.println("dingShopping.setPicture:"+rs.getString("picture"));*/
				
				dingShopping.setRuDate(rs.getString("ruDate"));
				/*System.out.println("dingShopping.setRuDate:"+rs.getString("ruDate"));*/
				
				dingShopping.setTotal(rs.getFloat("total"));
				
				/*System.out.println("============================");*/
				dingShoppings.add(dingShopping);
				System.out.println("dingShopping.toString():"+dingShopping.toString());
			}
			System.out.println("已经拿取了所有的商品信息数据，findAllDingShoppingInfoByDdanNum正常进入数据库");
			return dingShoppings;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//System.out.println("findAllClientInfo()1未正常进入数据库进行查询");
			ConnectionFactory.close(connection, preparedStatement, rs);
		}
		System.out.println("findAllDingShoppingInfoByDdanNum()正常进入数据库,但是未从表中拿到数据return null!");
		return null;
	}

	public void modifyPtByObj(dingshopping dingShopping) {
		// TODO Auto-generated method stub
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement =null;
		
		try {
			//② 准备SQL语句
			String sql = "UPDATE dingshopping SET pt = ? WHERE ddanNum=? AND cnum = ? ";
			//③ 获取集装箱或者说是车
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, dingShopping.getPt());
			preparedStatement.setString(2, dingShopping.getDdanNum());
			preparedStatement.setString(3, dingShopping.getCnum());
			
			//④执行SQL
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection, preparedStatement, null);
		}
	}
}
