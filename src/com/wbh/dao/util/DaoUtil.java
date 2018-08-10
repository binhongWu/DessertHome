package com.wbh.dao.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.MissingResourceException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * DAO工具类用于封装数据连接的获取及数据关闭
 * @author admin
 *
 */
public class DaoUtil {
	//public static ResourceBundle bundle;
	//c3p0数据池
	private static ComboPooledDataSource dataSource;
	//JNDI数据池
	private static DataSource dbcpDataSource;
	
	static{
		try {
			//C3P0创建数据池对象
			dataSource = new ComboPooledDataSource();
		} catch(MissingResourceException e){
			e.printStackTrace();
		} 

	}
	
	/**
	 * 获取连接
	 * @return
	 */
	public static Connection getConnection(){
		Connection con = null;
		
		//C3P0的连接获取
		try {
			con = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//使用JNDI通过DBCP数据池进行连接的获取
//		try {
//			con = dbcpDataSource.getConnection();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return con;
	}
	/**
	 * 关闭数据库连接
	 * @param con
	 * @param stmt
	 * @param rs
	 */
	public static void close(Connection con, Statement stmt, ResultSet rs){
		try {
			if(rs != null){
				rs.close();
			}
			if(stmt != null){
				stmt.close();
			}
			if(con != null){
				//通过数据池获取的connection由于是进行了改写，
				//因此此时的close是将connection重写放回dataSDource,而不是释放资源，断开数据库连接
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
