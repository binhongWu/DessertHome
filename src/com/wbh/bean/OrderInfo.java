package com.wbh.bean;

import java.util.List;

import com.wbh.pojo.Dessert;
import com.wbh.pojo.Order;
import com.wbh.pojo.ShoppingRecord;

public class OrderInfo {
	private Order order;
	private List<Dessert> dessertList;
	
	public OrderInfo(){}
	
	public OrderInfo(Order order, List<Dessert> dessertList) {
		super();
		this.order = order;
		this.dessertList = dessertList;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<Dessert> getDessertList() {
		return dessertList;
	}

	public void setDessertList(List<Dessert> dessertList) {
		this.dessertList = dessertList;
	}
	
	
	
}
