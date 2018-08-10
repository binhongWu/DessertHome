package com.wbh.dao;

import java.util.List;

import com.wbh.dao.util.DaoHandler;
import com.wbh.pojo.City;

/**
 * 城市 Dao
 * @author admin
 *
 */
public class CityDao {
	/**
	 * 根据省份编号查找所有城市 
	 * @param provinceId
	 * @return
	 */
	public List<City> findAllCityByProvinceId(int provinceId){
		String sql="select * from city_ where provinceId=?";
		List<City> list=DaoHandler.executeQueryMultiple(sql, new Object[]{provinceId}, City.class);
		return list;
	}
}
