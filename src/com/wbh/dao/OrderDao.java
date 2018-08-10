package com.wbh.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.List;

import com.wbh.dao.util.DaoHandler;
import com.wbh.pojo.Order;

public class OrderDao {
	/**
	 * �������ж���
	 */

	public List<Order> findAllOrderByPage(int page){

		return DaoHandler.executeQueryByPage("select orderId,orderFlag,userId,addressId,orderTime,totalPrice from(select  rownum as rod,n.* from ��select rownum as rid, order_.*from order_ order by orderTime desc��n) where rod between ? and ?", new Object[]{}, page, Order.class);
	}

	/**
	 * 
	 * ���Ҷ�����������
	 */
	public int findAllOrderCount(){
		BigDecimal decimal=(BigDecimal)DaoHandler.executeQuerySeriz("select count(*) from order_",new Object[]{});
		if(decimal==null){
			return 0;
		}
		return decimal.intValue();
	} 

	/**
	 * ͨ���û���Ų����û�����
	 * @param userId
	 * @return
	 */
	public List<Order> findOrderByUserId(int userId){
		return DaoHandler.executeQueryMultiple("select * from order_ where userId = ?", new Object[]{userId}, Order.class);
	}
	
	/**
	 * ͨ���û���Ų����û�����
	 * @param userId
	 * @return
	 */
	public List<Order> findAllOrderByUserId(int userId){
		return DaoHandler.executeQueryMultiple("select * from order_ where userId = ? and orderFlag > -2 order by orderId desc", new Object[]{userId}, Order.class);
	}
	
	/**
	 * �޸Ķ�����ʶ
	 * @param userId
	 */
	public void modifyOrderFlag(int orderId){
		DaoHandler.executeUpdate("update order_ set orderFlag = -100 where orderId = ?", new Object[]{orderId});
	}
	/**
	 * �����û������Ӷ���
	 */
	public void insertOrder(int orderFlag,int userId,int addressId,double totalPrice){
		String sql="insert into order_ values(seq_orderId.Nextval,?,?,?,?,?)";
		DaoHandler.executeUpdate(sql, new Object[]{orderFlag,userId,addressId,new Date(),totalPrice});
	}
	/**
	 * �����û���Ų�ѯ���µĶ�����¼
	 */
	public Order findNewOrder(int userId){
		String sql="select * from order_ where userId=? and orderId=(select max(orderId) from order_)";
		List<Order> list=DaoHandler.executeQueryMultiple(sql, new Object[]{userId}, Order.class);
		if(list.size()!=0){
			return list.get(0);
		}
		return null;
	}
}
