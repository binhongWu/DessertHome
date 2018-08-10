package com.wbh.dao;

import java.util.List;

import com.wbh.dao.util.DaoHandler;
import com.wbh.dao.util.DaoUtil;
import com.wbh.pojo.Dessert;
import com.wbh.pojo.Kind;

public class KindDao {
	/**
	 * 查找所有的甜品类型
	 * 
	 */
	public List<Kind> findAllKind(){
		String sql="select * from kind_";
		List<Kind> list= DaoHandler.executeQueryMultiple(sql, null, Kind.class);
		//System.out.println(list.size());
		return list;
	}
	
	/**
	 * 根据分类Id查找一个Kind
	 * @param kindId
	 * @return
	 */
	public Kind findKindByKindId(int kindId){
		List<Kind> list = DaoHandler.executeQueryMultiple("select * from kind_ where kindId=?", new Object[]{kindId}, Kind.class);
		if(list.size() == 0){
			return null;
		}else{
			return list.get(0);
		}
	}
	/**
	 * 根据甜点名找到甜点集合
	 * 
	 */
	public List<Kind> findKindlistByKindname(String kindname){
		return DaoHandler.executeQueryMultiple("select *from kind_ where  kindName=?", new Object[]{kindname}, Kind.class);
	}
//	public static void main(String[] args) {
//		Kind kind = new KindDao().findKindByKindId(1);
//		System.out.println(kind.getKindName());
//	}
}
