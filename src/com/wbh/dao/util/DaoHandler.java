package com.wbh.dao.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wbh.util.CommonUtil;



/**
 * DAO数据辅助类，封装了JDBC的核心数据访问操作
 * @author admin
 *
 */
public class DaoHandler {

	/**
	 * 通用的数据增删改方法
	 */
	public static void executeUpdate(String sql, Object[] params){
		//获取连接
		Connection con = DaoUtil.getConnection();
		PreparedStatement pstmt = null;
		if(con != null){
			try {
				//获取处理器
				pstmt = con.prepareStatement(sql);
				//注入参数
				if(params != null){
					for(int i = 0; i < params.length; i++){
						//判断参数的类型，根据类型决定调用的set方法
//					if(params[i] instanceof String){
//						pstmt.setString(i+1, params[i]);
//					}
//					else if(params[i] instanceof Integer){
//						pstmt.setInt(i+1, params[i]);
//					}
//					else if(params[i] instanceof Double){
//						pstmt.setDouble(i+1, params[i]);
//					}
						if(params[i] instanceof Date){
							pstmt.setTimestamp(i+1, new Timestamp(((Date)params[i]).getTime()));
						}
						else{
							pstmt.setObject(i+1, params[i]);
						}
						
					}	
				}
				//执行增删改
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				DaoUtil.close(con, pstmt, null);
			}	
		}
	}
	
	/**
	 * 查询多行数据的通用方法
	 * @param sql
	 * @param params
	 * @param objClass
	 * @return
	 */
	public static <T> List<T> executeQueryMultiple(String sql, Object[] params, Class<T> objClass){
		List<T> list = new ArrayList<T>();
		//获取连接
		Connection con = DaoUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//判断连接是否成功
		if(con != null){
			System.out.println("数据库连接成功开始查询");
			try {
				pstmt = con.prepareStatement(sql);
				if(params != null){
					//注入参数
					for(int i = 0; i < params.length; i++){
						if(params[i] instanceof Date){
							pstmt.setTimestamp(i+1, new Timestamp(((Date)params[i]).getTime()));
						}
						else{
							pstmt.setObject(i+1, params[i]);
						}	
					}
				}
				//执行查询
				rs = pstmt.executeQuery();
				//获取元数据
				ResultSetMetaData metaData = rs.getMetaData();
				//解析元数据
				ColumnInfo[] cols = JdbcUtil.parseResultSetMetaData(metaData);
				//反射对象的属性
				Map<String, Field> fieldMap = JdbcUtil.parseClassForField(objClass);
				while(rs.next()){	
					//反射实例化对象
					T t = objClass.newInstance();
					//列值
					Object value = null;
					//根据列索引获取列值
					//循环读取列结构
					for(int i = 0; i < cols.length; i++){
						ColumnInfo col = cols[i];
						//获取列的名称
						String colName = col.getColName();
//						System.out.println("列名："+colName);
						//根据列名反射获取对应的属性对象
						 Field field = fieldMap.get(colName);
//						 System.out.println("属性的名称："+field.getName());
						 //获取属性的类型
						 Class fieldType =field.getType();
						//根据属性的类型反向判断读取列的方式
						if(fieldType == String.class){
							value = rs.getString(i+1);
						}
						else if(fieldType == Double.class || fieldType == double.class){
							value = rs.getDouble(i+1);
						}
						else if(fieldType == Float.class || fieldType == float.class){
							value = rs.getFloat(i+1);
						}
						else if(fieldType == Date.class){
							value = rs.getTimestamp(i+1);
						}
						else if(fieldType == Integer.class || fieldType == int.class){
							value = rs.getInt(i+1);
						}
	
						else if(fieldType == Clob.class){
							Clob clob = rs.getClob(i+1);
							if(clob != null){
								BufferedReader reader = new BufferedReader(clob.getCharacterStream());
								String str = "";
								String content = "";
								while((str = reader.readLine()) != null){
									content+=str;
								}
								reader.close();
								value = content;
							}
						}
						else{
							value = rs.getObject(i+1);
						}
						//将列值写入对象(调用对应属性的setter方法)
						//根据属性名和属性类型查找对应的方法
						Method method = objClass.getMethod("set"+JdbcUtil.upperFirst(field.getName()), fieldType);
						//执行setter方法
						//System.out.println(method.getName()+","+value);
						method.invoke(t, value);
					}
					//将对象写入集合
					list.add(t);
				}
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				DaoUtil.close(con, pstmt, rs);
			}
		}
		return list;
	}
	
	/**
	 * 查询单个序列化数据的方法
	 * @return
	 */
	public static Serializable executeQuerySeriz(String sql, Object[] params){
		Serializable value = null;
		//获取连接
		Connection con = DaoUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		if(con != null){
			try {
				pstmt = con.prepareStatement(sql);
				//判断参数是否存在
				if(params != null){
					for(int i = 0; i < params.length; i++){
						if(params[i] instanceof Date){
							pstmt.setTimestamp(i+1, new Timestamp(((Date)params[i]).getTime()));
						}
						else{
							pstmt.setObject(i+1, params[i]);
						}	
					}
				}
				//执行查询
				rs = pstmt.executeQuery();
				while(rs.next()){
					value = (Serializable)rs.getObject(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				//关闭
				DaoUtil.close(con, pstmt, rs);
			}
		}
		return value;
	}
	
	/**
	 * 
	 * @param sql
	 * @param params
	 * @param page  页数
	 * @param objClass
	 * @return
	 */
	public static <T> List<T> executeQueryByPage(String sql, Object[] params, int page, Class<T> objClass){
		List<T> list = new ArrayList<T>();
		//获取连接
		Connection con = DaoUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//判断连接是否成功
		if(con != null){
			try {
				pstmt = con.prepareStatement(sql);
				//占位符索引
				int index = 1;
				if(params != null){
					//注入参数
					for(; index <= params.length; index++){
						if(params[index-1] instanceof Date){
							pstmt.setTimestamp(index, new Timestamp(((Date)params[index-1]).getTime()));
						}
						else{
							pstmt.setObject(index, params[index-1]);
						}	
					}
				}
				//获取每页的size
				int size = CommonUtil.getSize();
				//注入分页的参数值
				pstmt.setInt(index, size*(page-1)+1);
				pstmt.setInt(index+1, size*page);
				
				//执行查询
				rs = pstmt.executeQuery();
				//获取元数据
				ResultSetMetaData metaData = rs.getMetaData();
				//解析元数据
				ColumnInfo[] cols = JdbcUtil.parseResultSetMetaData(metaData);
				//反射对象的属性
				Map<String, Field> fieldMap = JdbcUtil.parseClassForField(objClass);
				while(rs.next()){	
					//反射实例化对象
					T t = objClass.newInstance();
					//列值
					Object value = null;
					//根据列索引获取列值
					//循环读取列结构
					for(int i = 0; i < cols.length; i++){
						ColumnInfo col = cols[i];
						//获取列的名称
						String colName = col.getColName();
						//根据列名反射获取对应的属性对象
						 Field field = fieldMap.get(colName);
						 //获取属性的类型
						 Class fieldType =field.getType();
						//根据属性的类型反向判断读取列的方式
						if(fieldType == String.class){
							value = rs.getString(i+1);
						}
						else if(fieldType == Double.class || fieldType == double.class){
							value = rs.getDouble(i+1);
						}
						else if(fieldType == Float.class || fieldType == float.class){
							value = rs.getFloat(i+1);
						}
						else if(fieldType == Date.class){
							value = rs.getTimestamp(i+1);
						}
						else if(fieldType == Integer.class || fieldType == int.class){
							value = rs.getInt(i+1);
						}
	
						else if(fieldType == Clob.class){
							Clob clob = rs.getClob(i+1);
							if(clob != null){
								BufferedReader reader = new BufferedReader(clob.getCharacterStream());
								String str = "";
								String content = "";
								while((str = reader.readLine()) != null){
									content+=str;
								}
								reader.close();
								value = content;
							}
						}
						else{
							value = rs.getObject(i+1);
						}
						//将列值写入对象(调用对应属性的setter方法)
						//根据属性名和属性类型查找对应的方法
						Method method = objClass.getMethod("set"+JdbcUtil.upperFirst(field.getName()), fieldType);
						//执行setter方法
						//System.out.println(method.getName()+","+value);
						method.invoke(t, value);
					}
					//将对象写入集合
					list.add(t);
				}
			} catch (Exception e){
				e.printStackTrace();
			}
			finally{
				DaoUtil.close(con, pstmt, rs);
			}
		}
		return list;
	}
}
