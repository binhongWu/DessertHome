package com.wbh.pojo;
/**
 * 购物车暂存数据类
 * @author admin
 *
 */
public class CartRecord {
	//甜品编号
	private int dessertId;
	//甜品数量
	private int dessertNumber;
	//用户编号
	private int userId;
	/**
	 * 带参构造
	 * @param desertId
	 * @param dessertNum
	 * @param userId
	 */
	public CartRecord(int desertId, int dessertNumber, int userId) {
		super();
		this.dessertId = desertId;
		this.dessertNumber = dessertNumber;
		this.userId = userId;
	}
	/**
	 * 空参构造
	 */
	public CartRecord(){}
	//下面是get和set方法
	
	
	public int getDessertId() {
		return dessertId;
	}
	public int getDessertNumber() {
		return dessertNumber;
	}
	public void setDessertNumber(int dessertNumber) {
		this.dessertNumber = dessertNumber;
	}
	public void setDessertId(int dessertId) {
		this.dessertId = dessertId;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
