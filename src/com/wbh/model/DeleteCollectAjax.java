package com.wbh.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wbh.dao.CollectionDao;
import com.wbh.dao.DessertDao;
import com.wbh.mvc.model.DispatchModel;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.pojo.Collection;
import com.wbh.pojo.Dessert;

public class DeleteCollectAjax extends DispatchModel{
	private String userId;
	private String dessertId;
	private CollectionDao collectionDao;
	private DessertDao dessertDao;
	
	@Override
	public String execute() {
		collectionDao = new CollectionDao();
		dessertDao = new DessertDao();
		// TODO Auto-generated method stub
		System.out.println(userId+" "+dessertId);
		HttpServletRequest request = ModelSupport.getRequest();
		//删除对应的收藏信息
		collectionDao.deleteCollect(Integer.parseInt(userId),Integer.parseInt(dessertId));
		//返回其余的收藏列表
		List<Dessert> dessertList = new ArrayList();
		List<Collection> collectionList = collectionDao.findCollectListByuserId(Integer.parseInt(userId));
		if(collectionList.size()!=0){
			for(Collection collection : collectionList){
				Dessert dessert = dessertDao.findDessertByDessertId(collection.getDessertId());
				dessertList.add(dessert);
			}
		}
		request.setAttribute("dessertList", dessertList);
		if(dessertList.size()!=0){
			return "success";
		}
		else{
			return "noCollect";
		}
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDessertId() {
		return dessertId;
	}
	public void setDessertId(String dessertId) {
		this.dessertId = dessertId;
	}
	
}
