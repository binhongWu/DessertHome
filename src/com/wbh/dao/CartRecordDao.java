package com.wbh.dao;

import java.util.ArrayList;
import java.util.List;

import com.wbh.dao.util.DaoHandler;
import com.wbh.pojo.CartRecord;

public class CartRecordDao {
	/**
	 * �����û�Id��ѯ���ﳵ��¼���е�����
	 */
	public List<CartRecord> findCartListByUserId(int userId) {
		return DaoHandler.executeQueryMultiple("select * from cartrecord_ where userId=?",new Object[]{userId}, CartRecord.class);
	}
	/**
	 * ��session�л������Ʒ����д�뵽�û��Ĺ��ﳵ�б���
	 */
	public void insertCartRecord(List<CartRecord> cartRecordList){
		for(CartRecord cartRecord:cartRecordList){
			DaoHandler.executeUpdate("insert into cartrecord_ values(?,?,?)", new Object[]{cartRecord.getDessertId(),cartRecord.getDessertNumber(),cartRecord.getUserId()});
		}
	}
	/**
	 * �����û�Idɾ�����ﳵ�и��û�������
	 */
	public void deleteAllCartInfoByUserId(int userId){
		DaoHandler.executeUpdate("delete from cartRecord_ where userId=?", new Object[]{userId});
	}
}