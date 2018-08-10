package com.wbh.dao.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.MissingResourceException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * DAO���������ڷ�װ�������ӵĻ�ȡ�����ݹر�
 * @author admin
 *
 */
public class DaoUtil {
	//public static ResourceBundle bundle;
	//c3p0���ݳ�
	private static ComboPooledDataSource dataSource;
	//JNDI���ݳ�
	private static DataSource dbcpDataSource;
	
	static{
		try {
			//C3P0�������ݳض���
			dataSource = new ComboPooledDataSource();
		} catch(MissingResourceException e){
			e.printStackTrace();
		} 

	}
	
	/**
	 * ��ȡ����
	 * @return
	 */
	public static Connection getConnection(){
		Connection con = null;
		
		//C3P0�����ӻ�ȡ
		try {
			con = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ʹ��JNDIͨ��DBCP���ݳؽ������ӵĻ�ȡ
//		try {
//			con = dbcpDataSource.getConnection();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return con;
	}
	/**
	 * �ر����ݿ�����
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
				//ͨ�����ݳػ�ȡ��connection�����ǽ����˸�д��
				//��˴�ʱ��close�ǽ�connection��д�Ż�dataSDource,�������ͷ���Դ���Ͽ����ݿ�����
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
