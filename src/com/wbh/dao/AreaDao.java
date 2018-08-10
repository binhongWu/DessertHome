package com.wbh.dao;

import java.util.List;

import com.wbh.dao.util.DaoHandler;
import com.wbh.pojo.Area;

/**
 * Çø Dao
 * @author admin
 *
 */
public class AreaDao {
	
	public List<Area> findAllAreaByCityId(int cityId){
		String sql="select * from area_ where cityId=?";
		List<Area> list=DaoHandler.executeQueryMultiple(sql, new Object[]{cityId}, Area.class);
		return list;
	}
}
