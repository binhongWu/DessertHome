package com.wbh.dao;

import java.util.List;

import com.wbh.dao.util.DaoHandler;
import com.wbh.pojo.Province;

/**
 * 省份Dao
 * @author admin
 *
 */
public class ProvinceDao {
	/**
	 * 查找所有省份
	 * @return
	 */
	public List<Province> findAllProvince(){
		String sql="select * from province_";
		List<Province> list=DaoHandler.executeQueryMultiple(sql, null, Province.class);
		return list;
	}
}
