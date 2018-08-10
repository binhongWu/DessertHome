package com.wbh.dao;

import java.util.ArrayList;
import java.util.List;

import com.wbh.dao.util.DaoHandler;
import com.wbh.pojo.CartRecord;

public class CartRecordDao {
	/**
	 * 根据用户Id查询购物车记录表中的数据
	 */
	public List<CartRecord> findCartListByUserId(int userId) {
		return DaoHandler.executeQueryMultiple("select * from cartrecord_ where userId=?",new Object[]{userId}, CartRecord.class);
	}
	/**
	 * 将session中缓存的商品数据写入到用户的购物车列表中
	 */
	public void insertCartRecord(List<CartRecord> cartRecordList){
		for(CartRecord cartRecord:cartRecordList){
			DaoHandler.executeUpdate("insert into cartrecord_ values(?,?,?)", new Object[]{cartRecord.getDessertId(),cartRecord.getDessertNumber(),cartRecord.getUserId()});
		}
	}
	/**
	 * 根据用户Id删除购物车中该用户的数据
	 */
	public void deleteAllCartInfoByUserId(int userId){
		DaoHandler.executeUpdate("delete from cartRecord_ where userId=?", new Object[]{userId});
	}
}
