package com.wbh.dao;

import java.util.List;

import com.wbh.dao.util.DaoHandler;
import com.wbh.pojo.Collection;


public class CollectionDao {
	/**
	 * 通过用户编号查找收藏信息
	 * @author admin
	 *
	 */
	public List<Collection> findCollectListByuserId(int userId){
		return DaoHandler.executeQueryMultiple("select * from collection_ where userId = ?", new Object[]{userId}, Collection.class);
	}
	
	/**
	 * 删除收藏信息
	 * @param userId
	 * @param dessertId
	 */
	public void deleteCollect(int userId,int dessertId){
		DaoHandler.executeUpdate("delete from collection_ where userId = ? and dessertId = ?", new Object[]{userId,dessertId});
	}
	/**
	 * 根据用户Id和甜品Id查询该甜品是否被该用户收藏
	 */
	public int checkIsCollect(int userId,int dessertId){
		 if(DaoHandler.executeQueryMultiple("select * from collection_ where userId=? and dessertId=?", new Object[]{userId,dessertId}, Collection.class).size()==0){
			 return 0;
		 }else{
			 return 1;
		 }
	}
	/**
	 * 根据用户添加收藏先信息
	 */
	public void insertCollection(int userId,int dessertId){
		DaoHandler.executeUpdate("insert into collection_ values(?,?)", new Object[]{userId,dessertId});
	}
}

