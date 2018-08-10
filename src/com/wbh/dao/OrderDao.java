package com.wbh.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.List;

import com.wbh.dao.util.DaoHandler;
import com.wbh.pojo.Order;

public class OrderDao {
	/**
	 * 查找所有订单
	 */

	public List<Order> findAllOrderByPage(int page){

		return DaoHandler.executeQueryByPage("select orderId,orderFlag,userId,addressId,orderTime,totalPrice from(select  rownum as rod,n.* from （select rownum as rid, order_.*from order_ order by orderTime desc）n) where rod between ? and ?", new Object[]{}, page, Order.class);
	}

	/**
	 * 
	 * 查找订单的总数量
	 */
	public int findAllOrderCount(){
		BigDecimal decimal=(BigDecimal)DaoHandler.executeQuerySeriz("select count(*) from order_",new Object[]{});
		if(decimal==null){
			return 0;
		}
		return decimal.intValue();
	} 

	/**
	 * 通过用户编号查找用户订单
	 * @param userId
	 * @return
	 */
	public List<Order> findOrderByUserId(int userId){
		return DaoHandler.executeQueryMultiple("select * from order_ where userId = ?", new Object[]{userId}, Order.class);
	}
	
	/**
	 * 通过用户编号查找用户订单
	 * @param userId
	 * @return
	 */
	public List<Order> findAllOrderByUserId(int userId){
		return DaoHandler.executeQueryMultiple("select * from order_ where userId = ? and orderFlag > -2 order by orderId desc", new Object[]{userId}, Order.class);
	}
	
	/**
	 * 修改订单标识
	 * @param userId
	 */
	public void modifyOrderFlag(int orderId){
		DaoHandler.executeUpdate("update order_ set orderFlag = -100 where orderId = ?", new Object[]{orderId});
	}
	/**
	 * 根据用户编号添加订单
	 */
	public void insertOrder(int orderFlag,int userId,int addressId,double totalPrice){
		String sql="insert into order_ values(seq_orderId.Nextval,?,?,?,?,?)";
		DaoHandler.executeUpdate(sql, new Object[]{orderFlag,userId,addressId,new Date(),totalPrice});
	}
	/**
	 * 根据用户编号查询最新的订单记录
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
