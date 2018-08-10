package com.wbh.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wbh.dao.CollectionDao;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.mvc.model.TextModel;
import com.wbh.pojo.User;

public class AddCollectionAjax extends TextModel{
	private String collectionFlag;
	private String dessertId;
	private CollectionDao collectionDao;
	
	
	public String getCollectionFlag() {
		return collectionFlag;
	}

	public void setCollectionFlag(String collectionFlag) {
		this.collectionFlag = collectionFlag;
	}

	public String getDessertId() {
		return dessertId;
	}

	public void setDessertId(String dessertId) {
		this.dessertId = dessertId;
	}
	public AddCollectionAjax(){
		collectionDao=new CollectionDao();
	}
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		HttpSession session = ModelSupport.getSession();
		HttpServletRequest request=ModelSupport.getRequest();
		User user=(User)session.getAttribute("loginUser");
		int dessertId=Integer.parseInt(this.dessertId);
		int collectionFlag=Integer.parseInt(this.collectionFlag);
		if(collectionFlag==0){
			collectionDao.insertCollection(user.getUserId(),dessertId);
			request.setAttribute("collectionFlag", "1");
			return "收藏成功！";
		}
		else{
			collectionDao.deleteCollect(user.getUserId(), dessertId);
			request.setAttribute("collectionFlag", "0");
			return "取消收藏！";
		}
		
	}
	
}
