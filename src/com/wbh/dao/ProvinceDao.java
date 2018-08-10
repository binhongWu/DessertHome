package com.wbh.dao;

import java.util.List;

import com.wbh.dao.util.DaoHandler;
import com.wbh.pojo.Province;

/**
 * ʡ��Dao
 * @author admin
 *
 */
public class ProvinceDao {
	/**
	 * ��������ʡ��
	 * @return
	 */
	public List<Province> findAllProvince(){
		String sql="select * from province_";
		List<Province> list=DaoHandler.executeQueryMultiple(sql, null, Province.class);
		return list;
	}
}
