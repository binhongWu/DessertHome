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

public class ShowCollectModel extends DispatchModel{
	private String userId;
	private CollectionDao collectionDao;
	private DessertDao dessertDao;
	
	public ShowCollectModel(){
		collectionDao = new CollectionDao();
		dessertDao = new DessertDao();
	}
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		HttpServletRequest request = ModelSupport.getRequest();
		List<Dessert> dessertList = new ArrayList();
		List<Collection> collectionList = collectionDao.findCollectListByuserId(Integer.parseInt(userId));
		if(collectionList.size()!=0){
			for(Collection collection : collectionList){
				Dessert dessert = dessertDao.findDessertByDessertId(collection.getDessertId());
				dessertList.add(dessert);
			}
		}
		request.setAttribute("dessertList", dessertList);
		request.setAttribute("dessertCount", dessertList.size());
		return "success";
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
