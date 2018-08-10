package com.wbh.dao.util;
/**
 * 封装了数据列的信息
 * @author admin
 *
 */
public class ColumnInfo {
	private String colName;
	private int colType;
	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}
	public int getColType() {
		return colType;
	}
	public void setColType(int colType) {
		this.colType = colType;
	}
}
