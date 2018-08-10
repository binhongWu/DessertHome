package com.wbh.dao;

import java.util.Date;
import java.util.List;

import com.wbh.dao.util.DaoHandler;
import com.wbh.pojo.Dessert;

public class DessertDao {
	/**
	 * 
	 * ����µ���Ʒ
	 */
	public void addDessert(Dessert des){
		DaoHandler.executeUpdate("insert into dessert_ values(seq_dessertId.Nextval,?,?,?,?,?,?,?,?,?,?)", new Object[]{des.getDessertName(), des.getDessertImg_S(), des.getDessertPrice(), des.getDessertInfo(), des.getPublishTime(), des.getWeight(), des.getKeepTip(), des.getKeepTime(), des.getKindId(), des.getDessertImg_B()});
	}
	/**
	 * �������е���Ʒ
	 * @return
	 */
	public List<Dessert> findAllDessert(){
		return DaoHandler.executeQueryMultiple("select * from dessert_", null, Dessert.class);
	}
	
	/**
	 * ������Ʒ������Ҹ÷����µ�������Ʒ��û�п�ζ��
	 */
	public List<Dessert> findDessertByKindId(int kindId){
		return DaoHandler.executeQueryMultiple("select * from dessert_ where kindId=?", new Object[]{kindId}, Dessert.class);
	}
	
	/**
	 * ������Ʒ��ζ�������и���ζ����Ʒ��û�з��ࣩ
	 * @param tasteId
	 * @return
	 */
	public List<Dessert> findDessertByTasteId(int tasteId){
		return DaoHandler.executeQueryMultiple("select * from dessert_ where dessertId in(select dessertId from dessertTaste_ where tasteId=?)", new Object[]{tasteId}, Dessert.class);
	}
	
	/**
	 * ������Ʒ������Ʒ��ζ������Ʒ
	 * @param kindId
	 * @param tasteId
	 * @return
	 */
	public List<Dessert> findDessertByKindIdTasteId(int kindId,int tasteId){
		return DaoHandler.executeQueryMultiple("select * from dessert_ where kindId=? and dessertId in(select dessertId from dessertTaste_ where tasteId=? group by dessertId)", new Object[]{kindId,tasteId}, Dessert.class);
	}
	
	/**
	 * ������ƷId��ѯ��Ʒ��������Ϣ
	 */
	public Dessert findDessertByDessertId(int dessertId){
		
		return DaoHandler.executeQueryMultiple("select * from dessert_ where dessertId=?", new Object[]{dessertId}, Dessert.class).get(0);
		
	}
	
	/**
	 * ������Ʒ  ����ǰ��
	 */
	public List<Dessert> findNewDessert(){
		String sql="select d2.* from ( select rownum as rnum,d1.* from  (select * from dessert_ order by publishTime desc) d1)d2 where d2.rnum<=3";
		List<Dessert> list=DaoHandler.executeQueryMultiple(sql, null, Dessert.class);
		return list;
	}
	/**
	 * ��ѯ��Ʒ��������ߵ�ǰ��
	 */
	public List<Dessert> findHotDessert(){
		String sql="select * from dessert_ inner join((select d2.* from(select rownum as rnum,d1.* from(select * from (select dessertId,sum(dessertNumber) as sumNum from shoppingRecord_ where orderId in (select orderId from order_ where orderTime>add_months(?,-2))  group by dessertId) order by sumNum desc)d1)d2 where rnum<=4)d4)on dessert_.dessertId=d4.dessertId order by sumNum desc";
		List<Dessert> list=DaoHandler.executeQueryMultiple(sql, new Object[]{new Date()},Dessert.class);
		return list;
	}
}
