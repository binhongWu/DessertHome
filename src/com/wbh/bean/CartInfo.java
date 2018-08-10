package com.wbh.bean;

import com.wbh.pojo.Dessert;
/**
 * ����һ����װ�ࣨ��װ��������Ͷ�Ӧ��������
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
	//������get��set����
	
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
