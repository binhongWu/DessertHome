package com.wbh.model;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wbh.bean.CartInfo;
import com.wbh.dao.AddressDao;
import com.wbh.dao.DessertDao;
import com.wbh.dao.ProvinceDao;
import com.wbh.mvc.model.DispatchModel;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.mvc.model.TextModel;
import com.wbh.pojo.Address;
import com.wbh.pojo.CartRecord;
import com.wbh.pojo.Dessert;
import com.wbh.pojo.Province;
import com.wbh.pojo.User;

public class InsertAddressModel extends TextModel{

	//地址Dao
	private AddressDao addressDao;
	
	@Override
	public String execute() {
		addressDao=new AddressDao();
		HttpServletRequest request=ModelSupport.getRequest();
		//省份
		String provinceName=request.getParameter("province");
		//城市
		String cityName=request.getParameter("city");
		//地区
		String areaName=request.getParameter("area");
		//详细地址
		String addressTxt=request.getParameter("addressTxt");
		
		//获取登录用户
		HttpSession session= ModelSupport.getSession();
		User user=(User)session.getAttribute("loginUser");
		//新增地址
		String newAddress=provinceName+cityName+areaName+addressTxt;
		System.out.println(newAddress);
		addressDao.insertAddress(new Address(newAddress,user.getUserId()));
		return newAddress;
	}
	
	
}
