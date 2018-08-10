package com.wbh.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.bcel.internal.generic.NEWARRAY;
import com.wbh.dao.AddressDao;
import com.wbh.dao.CartRecordDao;
import com.wbh.dao.OrderDao;
import com.wbh.dao.ShoppingRecordDao;
import com.wbh.dao.UserDao;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.mvc.model.TextModel;
import com.wbh.pojo.CartRecord;
import com.wbh.pojo.Order;
import com.wbh.pojo.User;

/**
 * 添加订单进数据库
 * @author admin
 *
 */
public class AddOrderModel extends TextModel{
	private OrderDao orderDao;
	private AddressDao addressDao;	
	private CartRecordDao cartRecordDao;
	private ShoppingRecordDao shoppingRecordDao;
//	private UserDao userDao;
	
	@Override
	public String execute() {
		System.out.println("进入添加订单模型");
		cartRecordDao= new CartRecordDao();
		orderDao=new OrderDao();
		addressDao=new AddressDao();
		shoppingRecordDao=new ShoppingRecordDao();
		HttpServletRequest request=ModelSupport.getRequest();
		int payFlag=Integer.parseInt(request.getParameter("payFlag"));
		String userAddress=request.getParameter("userAddress");
		double totalPrice=Double.parseDouble(request.getParameter("totalPrice"));
		//获取登录用户
		HttpSession session= ModelSupport.getSession();
		User user=(User)session.getAttribute("loginUser");
		//根据地址查找地址编号
		int addressId=addressDao.findaddressIdByAddress(userAddress);
		List<CartRecord> oldCratRecordlist=(List<CartRecord>)session.getAttribute("userCartRecordList");
		
		//创建一个新的购物车用于将原本的购物车数据进行覆盖
		List<CartRecord> userCartRecordList=new ArrayList<CartRecord>();
		//添加订单记录
		orderDao.insertOrder(payFlag, user.getUserId(), addressId, totalPrice);
//		userDao.addPoint((int)totalPrice, user.getUserId());
//		System.out.println("//////////////"+totalPrice+"/////////////"+user.getUserId());
		//查询最新的订单记录
		Order order=orderDao.findNewOrder(user.getUserId());
		//添加购物记录
		for(CartRecord cartRecord:oldCratRecordlist){
			shoppingRecordDao.insertShoppingRecord(order.getOrderId(), cartRecord.getDessertId(), cartRecord.getDessertNumber());
		}
		session.setAttribute("userCartRecordList", userCartRecordList);
		System.out.println("当单编号2是       "+order.getOrderId());
		return "";
	}

}
