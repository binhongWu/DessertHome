package com.wbh.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wbh.dao.CollectionDao;
import com.wbh.dao.DessertDao;
import com.wbh.mvc.model.DispatchModel;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.pojo.Collection;
import com.wbh.pojo.Dessert;
import com.wbh.pojo.User;

public class ShowDessertDetailsModel extends DispatchModel{
	private String dessertId;
	private DessertDao dessertDao;
	private CollectionDao collectionDao;
	public String getDessertId() {
		return dessertId;
	}
	public void setDessertId(String dessertId) {
		this.dessertId = dessertId;
	}
	/**
	 * 在构造中初始化Dao
	 */
	public ShowDessertDetailsModel(){
		dessertDao=new DessertDao();
		collectionDao=new CollectionDao();
	}
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		int flag=0;
		HttpServletRequest request=ModelSupport.getRequest();
		HttpSession session = ModelSupport.getSession();
		Dessert dessert = dessertDao.findDessertByDessertId(Integer.parseInt(dessertId));
		User user=(User)session.getAttribute("loginUser");
		if(user!=null){
			flag=collectionDao.checkIsCollect(user.getUserId(), Integer.parseInt(dessertId));
		}
		request.setAttribute("collectionFlag", flag);
		request.setAttribute("dessert", dessert);
		return "jump";
	}
	
}
