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
 * DAO���ݸ����࣬��װ��JDBC�ĺ������ݷ��ʲ���
 * @author admin
 *
 */
public class DaoHandler {

	/**
	 * ͨ�õ�������ɾ�ķ���
	 */
	public static void executeUpdate(String sql, Object[] params){
		//��ȡ����
		Connection con = DaoUtil.getConnection();
		PreparedStatement pstmt = null;
		if(con != null){
			try {
				//��ȡ������
				pstmt = con.prepareStatement(sql);
				//ע�����
				if(params != null){
					for(int i = 0; i < params.length; i++){
						//�жϲ��������ͣ��������;������õ�set����
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
				//ִ����ɾ��
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
	 * ��ѯ�������ݵ�ͨ�÷���
	 * @param sql
	 * @param params
	 * @param objClass
	 * @return
	 */
	public static <T> List<T> executeQueryMultiple(String sql, Object[] params, Class<T> objClass){
		List<T> list = new ArrayList<T>();
		//��ȡ����
		Connection con = DaoUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//�ж������Ƿ�ɹ�
		if(con != null){
			System.out.println("���ݿ����ӳɹ���ʼ��ѯ");
			try {
				pstmt = con.prepareStatement(sql);
				if(params != null){
					//ע�����
					for(int i = 0; i < params.length; i++){
						if(params[i] instanceof Date){
							pstmt.setTimestamp(i+1, new Timestamp(((Date)params[i]).getTime()));
						}
						else{
							pstmt.setObject(i+1, params[i]);
						}	
					}
				}
				//ִ�в�ѯ
				rs = pstmt.executeQuery();
				//��ȡԪ����
				ResultSetMetaData metaData = rs.getMetaData();
				//����Ԫ����
				ColumnInfo[] cols = JdbcUtil.parseResultSetMetaData(metaData);
				//������������
				Map<String, Field> fieldMap = JdbcUtil.parseClassForField(objClass);
				while(rs.next()){	
					//����ʵ��������
					T t = objClass.newInstance();
					//��ֵ
					Object value = null;
					//������������ȡ��ֵ
					//ѭ����ȡ�нṹ
					for(int i = 0; i < cols.length; i++){
						ColumnInfo col = cols[i];
						//��ȡ�е�����
						String colName = col.getColName();
//						System.out.println("������"+colName);
						//�������������ȡ��Ӧ�����Զ���
						 Field field = fieldMap.get(colName);
//						 System.out.println("���Ե����ƣ�"+field.getName());
						 //��ȡ���Ե�����
						 Class fieldType =field.getType();
						//�������Ե����ͷ����ж϶�ȡ�еķ�ʽ
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
						//����ֵд�����(���ö�Ӧ���Ե�setter����)
						//�������������������Ͳ��Ҷ�Ӧ�ķ���
						Method method = objClass.getMethod("set"+JdbcUtil.upperFirst(field.getName()), fieldType);
						//ִ��setter����
						//System.out.println(method.getName()+","+value);
						method.invoke(t, value);
					}
					//������д�뼯��
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
	 * ��ѯ�������л����ݵķ���
	 * @return
	 */
	public static Serializable executeQuerySeriz(String sql, Object[] params){
		Serializable value = null;
		//��ȡ����
		Connection con = DaoUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		if(con != null){
			try {
				pstmt = con.prepareStatement(sql);
				//�жϲ����Ƿ����
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
				//ִ�в�ѯ
				rs = pstmt.executeQuery();
				while(rs.next()){
					value = (Serializable)rs.getObject(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				//�ر�
				DaoUtil.close(con, pstmt, rs);
			}
		}
		return value;
	}
	
	/**
	 * 
	 * @param sql
	 * @param params
	 * @param page  ҳ��
	 * @param objClass
	 * @return
	 */
	public static <T> List<T> executeQueryByPage(String sql, Object[] params, int page, Class<T> objClass){
		List<T> list = new ArrayList<T>();
		//��ȡ����
		Connection con = DaoUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//�ж������Ƿ�ɹ�
		if(con != null){
			try {
				pstmt = con.prepareStatement(sql);
				//ռλ������
				int index = 1;
				if(params != null){
					//ע�����
					for(; index <= params.length; index++){
						if(params[index-1] instanceof Date){
							pstmt.setTimestamp(index, new Timestamp(((Date)params[index-1]).getTime()));
						}
						else{
							pstmt.setObject(index, params[index-1]);
						}	
					}
				}
				//��ȡÿҳ��size
				int size = CommonUtil.getSize();
				//ע���ҳ�Ĳ���ֵ
				pstmt.setInt(index, size*(page-1)+1);
				pstmt.setInt(index+1, size*page);
				
				//ִ�в�ѯ
				rs = pstmt.executeQuery();
				//��ȡԪ����
				ResultSetMetaData metaData = rs.getMetaData();
				//����Ԫ����
				ColumnInfo[] cols = JdbcUtil.parseResultSetMetaData(metaData);
				//������������
				Map<String, Field> fieldMap = JdbcUtil.parseClassForField(objClass);
				while(rs.next()){	
					//����ʵ��������
					T t = objClass.newInstance();
					//��ֵ
					Object value = null;
					//������������ȡ��ֵ
					//ѭ����ȡ�нṹ
					for(int i = 0; i < cols.length; i++){
						ColumnInfo col = cols[i];
						//��ȡ�е�����
						String colName = col.getColName();
						//�������������ȡ��Ӧ�����Զ���
						 Field field = fieldMap.get(colName);
						 //��ȡ���Ե�����
						 Class fieldType =field.getType();
						//�������Ե����ͷ����ж϶�ȡ�еķ�ʽ
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
						//����ֵд�����(���ö�Ӧ���Ե�setter����)
						//�������������������Ͳ��Ҷ�Ӧ�ķ���
						Method method = objClass.getMethod("set"+JdbcUtil.upperFirst(field.getName()), fieldType);
						//ִ��setter����
						//System.out.println(method.getName()+","+value);
						method.invoke(t, value);
					}
					//������д�뼯��
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
