package com.wbh.pojo;

public class ShoppingRecord {
	private int orderId;
	private int dessertId;
	private int dessertNumber;
	
	public ShoppingRecord(int orderId, int dessertId, int dessertNumber) {
		super();
		this.orderId = orderId;
		this.dessertId = dessertId;
		this.dessertNumber = dessertNumber;
	}
	
	public ShoppingRecord(){}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getDessertId() {
		return dessertId;
	}

	public void setDessertId(int dessertId) {
		this.dessertId = dessertId;
	}

	public int getDessertNumber() {
		return dessertNumber;
	}

	public void setDessertNumber(int dessertNumber) {
		this.dessertNumber = dessertNumber;
	}
	
	
}
