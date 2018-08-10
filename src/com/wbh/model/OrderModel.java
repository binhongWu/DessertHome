package com.wbh.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wbh.bean.OrderInfo;
import com.wbh.dao.DessertDao;
import com.wbh.dao.OrderDao;
import com.wbh.dao.ShoppingRecordDao;
import com.wbh.mvc.model.DispatchModel;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.pojo.Dessert;
import com.wbh.pojo.Order;
import com.wbh.pojo.ShoppingRecord;

public class OrderModel extends DispatchModel{
	private String userId;
	private OrderDao orderDao;
	private ShoppingRecordDao shoppingRecordDao;
	private DessertDao dessertDao;
	
	public OrderModel(){
		orderDao = new OrderDao();
		shoppingRecordDao = new ShoppingRecordDao();
		dessertDao = new DessertDao();
	}
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		HttpServletRequest request = ModelSupport.getRequest();
		List<OrderInfo> orderInfoList = new ArrayList();
		//根据用户标号获取订单列表
		List<Order> orderList = orderDao.findAllOrderByUserId(Integer.parseInt(userId));
		for(Order order : orderList){
			OrderInfo orderInfo = new OrderInfo();
			List<Dessert> dessertList = new ArrayList();
			//通过订单标号获取购物记录列表
			List<ShoppingRecord> shoppingList = shoppingRecordDao.findShoppingRecordListByOrderId(order.getOrderId());
			for(ShoppingRecord shoppingRecord : shoppingList){
				Dessert dessert = dessertDao.findDessertByDessertId(shoppingRecord.getDessertId());
				//封装商品数量
				dessert.setCount(shoppingRecord.getDessertNumber());
				//放入商品集合
				dessertList.add(dessert);
			}
			//封装订单信息
			orderInfo.setOrder(order);
			orderInfo.setDessertList(dessertList);
			orderInfoList.add(orderInfo);
		}
		request.setAttribute("orderInfoList", orderInfoList);
		request.setAttribute("orderCount", orderInfoList.size());
		return "success";
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
