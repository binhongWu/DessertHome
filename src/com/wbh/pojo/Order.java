package com.wbh.pojo;

import java.util.Date;
import java.util.List;

public class Order {
	//订单编号
	private int orderId;
	//订单状态
	private int orderFlag;
	//用户Id
	private int userId;
	//用户地址Id
	private int addressId;
	//订单产生时间
	private Date orderTime;
	//总金额
	private double totalPrice;
	private String userName;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 带参构造
	 */
	public Order(int orderFlag, int userId, int addressId,
			Date orderTime) {
		super();
		this.orderFlag = orderFlag;
		this.userId = userId;
		this.addressId = addressId;
		this.orderTime = orderTime;
	}
	/**
	 * 空参构造
	 */
	public Order(){}
	//下面是get和set方法
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getOrderFlag() {
		return orderFlag;
	}
	public void setOrderFlag(int orderFlag) {
		this.orderFlag = orderFlag;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	
}
