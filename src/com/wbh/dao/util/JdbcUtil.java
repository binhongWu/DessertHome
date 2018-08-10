package com.wbh.dao.util;

import java.lang.reflect.Field;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * JDBC工具类，封装了数据层常用方法
 * @author admin
 *
 */
public class JdbcUtil {
	/**
	 * 解析元数据获取表结构
	 * @param metaData
	 * @return
	 */
	public static ColumnInfo[] parseResultSetMetaData(ResultSetMetaData metaData){
		ColumnInfo[] array = null;
		if(metaData != null){
			try {
				//根据列数量创建数组
				array = new ColumnInfo[metaData.getColumnCount()];
				for(int i = 1; i <= metaData.getColumnCount(); i++){
					ColumnInfo col = new ColumnInfo();
					//将每列的列名、类型写入
					col.setColName(metaData.getColumnName(i));
					col.setColType(metaData.getColumnType(i));
					//将列信息写入数组
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
	 * 反射类的属性
	 * @param objClass
	 * @return 存储了大写属性名于属性类型的集合
	 */
	public static Map<String, Field> parseClassForField(Class objClass){
		Map<String, Field> map = new HashMap<String, Field>();
		//反射类的属性
		Field[] fields = objClass.getDeclaredFields();
		//遍历所有属性
		for(Field f : fields){
			String fieldName = f.getName();
			//将属性名转换成大写
			fieldName = fieldName.toUpperCase();
			//获取属性的类型
			//Class type = f.getType();
			//添加至集合
			map.put(fieldName, f);
		}
		return map;
	}
	
	/**
	 * 首字母大写转换
	 * @param fieldName
	 * @return
	 */
	public static String upperFirst(String fieldName){
		//获取第一个字符
		String first = fieldName.substring(0,1);
		//大写转换
		first = first.toUpperCase();
		return first.concat(fieldName.substring(1));
	}
}
