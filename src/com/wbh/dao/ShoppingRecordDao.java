package com.wbh.dao;

import java.util.List;

import com.wbh.dao.util.DaoHandler;
import com.wbh.pojo.CartRecord;
import com.wbh.pojo.ShoppingRecord;


public class ShoppingRecordDao {
	/**
	 * 通过订单编号找出购物记录
	 * @author admin
	 *
	 */
	public List<ShoppingRecord> findShoppingRecordListByOrderId(int orderId){
		return DaoHandler.executeQueryMultiple("select * from shoppingRecord_ where orderId = ?", new Object[]{orderId}, ShoppingRecord.class);
	}
	public void insertShoppingRecord(int orderId,int dessertId,int dessertNum){
		String sql="insert into shoppingRecord_ values(?,?,?)";
			DaoHandler.executeUpdate(sql, new Object[]{orderId,dessertId,dessertNum});
			
			}
	
}
