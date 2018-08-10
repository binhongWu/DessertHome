package com.wbh.dao;

import java.util.List;

import com.wbh.dao.util.DaoHandler;
import com.wbh.pojo.Collection;


public class CollectionDao {
	/**
	 * ͨ���û���Ų����ղ���Ϣ
	 * @author admin
	 *
	 */
	public List<Collection> findCollectListByuserId(int userId){
		return DaoHandler.executeQueryMultiple("select * from collection_ where userId = ?", new Object[]{userId}, Collection.class);
	}
	
	/**
	 * ɾ���ղ���Ϣ
	 * @param userId
	 * @param dessertId
	 */
	public void deleteCollect(int userId,int dessertId){
		DaoHandler.executeUpdate("delete from collection_ where userId = ? and dessertId = ?", new Object[]{userId,dessertId});
	}
	/**
	 * �����û�Id����ƷId��ѯ����Ʒ�Ƿ񱻸��û��ղ�
	 */
	public int checkIsCollect(int userId,int dessertId){
		 if(DaoHandler.executeQueryMultiple("select * from collection_ where userId=? and dessertId=?", new Object[]{userId,dessertId}, Collection.class).size()==0){
			 return 0;
		 }else{
			 return 1;
		 }
	}
	/**
	 * �����û�����ղ�����Ϣ
	 */
	public void insertCollection(int userId,int dessertId){
		DaoHandler.executeUpdate("insert into collection_ values(?,?)", new Object[]{userId,dessertId});
	}
}

