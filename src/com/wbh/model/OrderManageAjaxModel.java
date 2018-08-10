package com.wbh.model;

import com.wbh.dao.UserDao;
import com.wbh.mvc.model.TextModel;
import com.wbh.pojo.User;

public class OrderManageAjaxModel extends TextModel{
	private String ordername;
	
	public String getOrdername() {
		return ordername;
	}

	public void setOrdername(String ordername) {
		this.ordername = ordername;
	}

	@Override
	public String execute() {
		// TODO Auto-generated method stub
		UserDao userdao=new UserDao();
		User user = userdao.findUserByUserName(ordername);
		if(user != null){
			return "";
		}
		else{
			return "用户名不存在！";
		}
	}
	
}
