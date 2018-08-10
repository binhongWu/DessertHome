package com.wbh.dao;

import java.util.List;

import com.wbh.dao.util.DaoHandler;
import com.wbh.pojo.Address;

public class AddressDao {
	/**
	 * 根据地址id找到地址对象
	 */
	public List<Address> findAddressByAddressId(int addressId){
		return DaoHandler.executeQueryMultiple("select * from address_ where addressId=?", new Object[]{addressId}, Address.class);
	}
	/**
	 * 通过用户编号查找用户地址（吴彬红）
	 */
	public List<Address> findAddressByUserId(int userId){
		String sql="select * from address_ where userId=?";
		List<Address> list=DaoHandler.executeQueryMultiple(sql, new Object[]{userId}, Address.class);
		return list;
	}
	
	/**
	 * 根据用户编号新增地址（吴彬红）
	 */
	public void insertAddress(Address address){
		String sql="insert into address_ values(seq_addressId.Nextval,?,?)";
		DaoHandler.executeUpdate(sql, new Object[]{address.getAddressDetail(),address.getUserId()});
	}
	/**
	 * 根据地址查找地址编号
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
