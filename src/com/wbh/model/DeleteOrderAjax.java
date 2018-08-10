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

public class DeleteOrderAjax extends DispatchModel{
	private String userId;
	private String orderId;
	
	private OrderDao orderDao;
	private ShoppingRecordDao shoppingRecordDao;
	private DessertDao dessertDao;
	
	public DeleteOrderAjax(){
		orderDao = new OrderDao();
		shoppingRecordDao = new ShoppingRecordDao();
		dessertDao = new DessertDao();
	}
	
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		orderDao.modifyOrderFlag(Integer.parseInt(orderId));
		HttpServletRequest request = ModelSupport.getRequest();
		List<OrderInfo> orderInfoList = new ArrayList();
		//�����û���Ż�ȡ�����б�
		List<Order> orderList = orderDao.findAllOrderByUserId(Integer.parseInt(userId));
		for(Order order : orderList){
			OrderInfo orderInfo = new OrderInfo();
			List<Dessert> dessertList = new ArrayList();
			//ͨ��������Ż�ȡ�����¼�б�
			List<ShoppingRecord> shoppingList = shoppingRecordDao.findShoppingRecordListByOrderId(order.getOrderId());
			for(ShoppingRecord shoppingRecord : shoppingList){
				Dessert dessert = dessertDao.findDessertByDessertId(shoppingRecord.getDessertId());
				//��װ��Ʒ����
				dessert.setCount(shoppingRecord.getDessertNumber());
				//������Ʒ����
				dessertList.add(dessert);
			}
			//��װ������Ϣ
			orderInfo.setOrder(order);
			orderInfo.setDessertList(dessertList);
			orderInfoList.add(orderInfo);
		}
		request.setAttribute("orderInfoList", orderInfoList);
		if(orderList.size()!=0){
			return "success";
		}
		else{
			return "noOrder";
		}
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}
