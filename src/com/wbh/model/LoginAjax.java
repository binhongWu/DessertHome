package com.wbh.model;


import com.wbh.dao.UserDao;
import com.wbh.mvc.model.TextModel;
import com.wbh.pojo.User;

public class LoginAjax extends TextModel{
	private UserDao userDao;
	private String userName;
	
	public LoginAjax(){
		userDao = new UserDao();
	}
	
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		
		User user = userDao.findUserByUserName(userName);
		if(user != null){
			return "";
		}
		else{
			return "用户名不存在！";
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
