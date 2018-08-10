package com.wbh.dao.util;

import java.lang.reflect.Field;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * JDBC�����࣬��װ�����ݲ㳣�÷���
 * @author admin
 *
 */
public class JdbcUtil {
	/**
	 * ����Ԫ���ݻ�ȡ��ṹ
	 * @param metaData
	 * @return
	 */
	public static ColumnInfo[] parseResultSetMetaData(ResultSetMetaData metaData){
		ColumnInfo[] array = null;
		if(metaData != null){
			try {
				//������������������
				array = new ColumnInfo[metaData.getColumnCount()];
				for(int i = 1; i <= metaData.getColumnCount(); i++){
					ColumnInfo col = new ColumnInfo();
					//��ÿ�е�����������д��
					col.setColName(metaData.getColumnName(i));
					col.setColType(metaData.getColumnType(i));
					//������Ϣд������
					array[i-1] = col;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return array;
	}
	
	/**
	 * �����������
	 * @param objClass
	 * @return �洢�˴�д���������������͵ļ���
	 */
	public static Map<String, Field> parseClassForField(Class objClass){
		Map<String, Field> map = new HashMap<String, Field>();
		//�����������
		Field[] fields = objClass.getDeclaredFields();
		//������������
		for(Field f : fields){
			String fieldName = f.getName();
			//��������ת���ɴ�д
			fieldName = fieldName.toUpperCase();
			//��ȡ���Ե�����
			//Class type = f.getType();
			//���������
			map.put(fieldName, f);
		}
		return map;
	}
	
	/**
	 * ����ĸ��дת��
	 * @param fieldName
	 * @return
	 */
	public static String upperFirst(String fieldName){
		//��ȡ��һ���ַ�
		String first = fieldName.substring(0,1);
		//��дת��
		first = first.toUpperCase();
		return first.concat(fieldName.substring(1));
	}
}
