package com.wbh.dao;

import java.util.List;

import com.wbh.dao.util.DaoHandler;
import com.wbh.pojo.City;

/**
 * ���� Dao
 * @author admin
 *
 */
public class CityDao {
	/**
	 * ����ʡ�ݱ�Ų������г��� 
	 * @param provinceId
	 * @return
	 */
	public List<City> findAllCityByProvinceId(int provinceId){
		String sql="select * from city_ where provinceId=?";
		List<City> list=DaoHandler.executeQueryMultiple(sql, new Object[]{provinceId}, City.class);
		return list;
	}
}
