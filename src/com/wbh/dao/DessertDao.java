package com.wbh.dao;

import java.util.Date;
import java.util.List;

import com.wbh.dao.util.DaoHandler;
import com.wbh.pojo.Dessert;

public class DessertDao {
	/**
	 * 
	 * 添加新的甜品
	 */
	public void addDessert(Dessert des){
		DaoHandler.executeUpdate("insert into dessert_ values(seq_dessertId.Nextval,?,?,?,?,?,?,?,?,?,?)", new Object[]{des.getDessertName(), des.getDessertImg_S(), des.getDessertPrice(), des.getDessertInfo(), des.getPublishTime(), des.getWeight(), des.getKeepTip(), des.getKeepTime(), des.getKindId(), des.getDessertImg_B()});
	}
	/**
	 * 查找所有的甜品
	 * @return
	 */
	public List<Dessert> findAllDessert(){
		return DaoHandler.executeQueryMultiple("select * from dessert_", null, Dessert.class);
	}
	
	/**
	 * 根据甜品分类查找该分类下的所有甜品（没有口味）
	 */
	public List<Dessert> findDessertByKindId(int kindId){
		return DaoHandler.executeQueryMultiple("select * from dessert_ where kindId=?", new Object[]{kindId}, Dessert.class);
	}
	
	/**
	 * 根据甜品口味查找所有给口味的甜品（没有分类）
	 * @param tasteId
	 * @return
	 */
	public List<Dessert> findDessertByTasteId(int tasteId){
		return DaoHandler.executeQueryMultiple("select * from dessert_ where dessertId in(select dessertId from dessertTaste_ where tasteId=?)", new Object[]{tasteId}, Dessert.class);
	}
	
	/**
	 * 根据甜品类别和甜品口味查找甜品
	 * @param kindId
	 * @param tasteId
	 * @return
	 */
	public List<Dessert> findDessertByKindIdTasteId(int kindId,int tasteId){
		return DaoHandler.executeQueryMultiple("select * from dessert_ where kindId=? and dessertId in(select dessertId from dessertTaste_ where tasteId=? group by dessertId)", new Object[]{kindId,tasteId}, Dessert.class);
	}
	
	/**
	 * 根据甜品Id查询甜品的所有信息
	 */
	public Dessert findDessertByDessertId(int dessertId){
		
		return DaoHandler.executeQueryMultiple("select * from dessert_ where dessertId=?", new Object[]{dessertId}, Dessert.class).get(0);
		
	}
	
	/**
	 * 查找新品  最新前三
	 */
	public List<Dessert> findNewDessert(){
		String sql="select d2.* from ( select rownum as rnum,d1.* from  (select * from dessert_ order by publishTime desc) d1)d2 where d2.rnum<=3";
		List<Dessert> list=DaoHandler.executeQueryMultiple(sql, null, Dessert.class);
		return list;
	}
	/**
	 * 查询甜品销售量最高的前四
	 */
	public List<Dessert> findHotDessert(){
		String sql="select * from dessert_ inner join((select d2.* from(select rownum as rnum,d1.* from(select * from (select dessertId,sum(dessertNumber) as sumNum from shoppingRecord_ where orderId in (select orderId from order_ where orderTime>add_months(?,-2))  group by dessertId) order by sumNum desc)d1)d2 where rnum<=4)d4)on dessert_.dessertId=d4.dessertId order by sumNum desc";
		List<Dessert> list=DaoHandler.executeQueryMultiple(sql, new Object[]{new Date()},Dessert.class);
		return list;
	}
}
