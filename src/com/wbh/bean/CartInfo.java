package com.wbh.bean;

import com.wbh.pojo.Dessert;
/**
 * 这是一个封装类（封装了甜点对象和对应的数量）
 * @author admin
 *
 */
public class CartInfo {
	private Dessert dessert;
	private int dessertNum;
	private double totalPrice;
	
	
	public CartInfo(Dessert dessert, int dessertNum, double totalPrice) {
		super();
		this.dessert = dessert;
		this.dessertNum = dessertNum;
		this.totalPrice = totalPrice;
	}
	//下面是get和set方法
	
	public Dessert getDessert() {
		return dessert;
	}

	public void setDessert(Dessert dessert) {
		this.dessert = dessert;
	}
	public int getDessertNum() {
		return dessertNum;
	}
	public void setDessertNum(int dessertNum) {
		this.dessertNum = dessertNum;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
