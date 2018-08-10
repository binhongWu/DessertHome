package com.wbh.dao;

import java.util.List;

import com.wbh.dao.util.DaoHandler;
import com.wbh.pojo.Address;

public class AddressDao {
	/**
	 * ���ݵ�ַid�ҵ���ַ����
	 */
	public List<Address> findAddressByAddressId(int addressId){
		return DaoHandler.executeQueryMultiple("select * from address_ where addressId=?", new Object[]{addressId}, Address.class);
	}
	/**
	 * ͨ���û���Ų����û���ַ�����죩
	 */
	public List<Address> findAddressByUserId(int userId){
		String sql="select * from address_ where userId=?";
		List<Address> list=DaoHandler.executeQueryMultiple(sql, new Object[]{userId}, Address.class);
		return list;
	}
	
	/**
	 * �����û����������ַ�����죩
	 */
	public void insertAddress(Address address){
		String sql="insert into address_ values(seq_addressId.Nextval,?,?)";
		DaoHandler.executeUpdate(sql, new Object[]{address.getAddressDetail(),address.getUserId()});
	}
	/**
	 * ���ݵ�ַ���ҵ�ַ���
	 * @param addressDetail
	 * @return
	 */
	public int findaddressIdByAddress(String addressDetail){
		String sql="select addressId from address_ where addressDetail=?";
		List<Address> list=DaoHandler.executeQueryMultiple(sql, new Object[]{addressDetail}, Address.class);
		for(Address address:list){
			return address.getAddressId();
		}
		return 0;
	}
	
}
