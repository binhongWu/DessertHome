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
 * ��Ӷ��������ݿ�
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
		System.out.println("������Ӷ���ģ��");
		cartRecordDao= new CartRecordDao();
		orderDao=new OrderDao();
		addressDao=new AddressDao();
		shoppingRecordDao=new ShoppingRecordDao();
		HttpServletRequest request=ModelSupport.getRequest();
		int payFlag=Integer.parseInt(request.getParameter("payFlag"));
		String userAddress=request.getParameter("userAddress");
		double totalPrice=Double.parseDouble(request.getParameter("totalPrice"));
		//��ȡ��¼�û�
		HttpSession session= ModelSupport.getSession();
		User user=(User)session.getAttribute("loginUser");
		//���ݵ�ַ���ҵ�ַ���
		int addressId=addressDao.findaddressIdByAddress(userAddress);
		List<CartRecord> oldCratRecordlist=(List<CartRecord>)session.getAttribute("userCartRecordList");
		
		//����һ���µĹ��ﳵ���ڽ�ԭ���Ĺ��ﳵ���ݽ��и���
		List<CartRecord> userCartRecordList=new ArrayList<CartRecord>();
		//��Ӷ�����¼
		orderDao.insertOrder(payFlag, user.getUserId(), addressId, totalPrice);
//		userDao.addPoint((int)totalPrice, user.getUserId());
//		System.out.println("//////////////"+totalPrice+"/////////////"+user.getUserId());
		//��ѯ���µĶ�����¼
		Order order=orderDao.findNewOrder(user.getUserId());
		//��ӹ����¼
		for(CartRecord cartRecord:oldCratRecordlist){
			shoppingRecordDao.insertShoppingRecord(order.getOrderId(), cartRecord.getDessertId(), cartRecord.getDessertNumber());
		}
		session.setAttribute("userCartRecordList", userCartRecordList);
		System.out.println("�������2��       "+order.getOrderId());
		return "";
	}

}
