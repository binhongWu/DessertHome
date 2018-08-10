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

	//��ַDao
	private AddressDao addressDao;
	
	@Override
	public String execute() {
		addressDao=new AddressDao();
		HttpServletRequest request=ModelSupport.getRequest();
		//ʡ��
		String provinceName=request.getParameter("province");
		//����
		String cityName=request.getParameter("city");
		//����
		String areaName=request.getParameter("area");
		//��ϸ��ַ
		String addressTxt=request.getParameter("addressTxt");
		
		//��ȡ��¼�û�
		HttpSession session= ModelSupport.getSession();
		User user=(User)session.getAttribute("loginUser");
		//������ַ
		String newAddress=provinceName+cityName+areaName+addressTxt;
		System.out.println(newAddress);
		addressDao.insertAddress(new Address(newAddress,user.getUserId()));
		return newAddress;
	}
	
	
}
