package com.wbh.model;

import com.wbh.dao.UserDao;
import com.wbh.mvc.model.TextModel;
import com.wbh.pojo.User;

public class UserManageAjaxModel extends TextModel{
	private String userName;
	
	
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	@Override
	public String execute() {
		// TODO Auto-generated method stub

		UserDao userdao=new UserDao();
		User user = userdao.findUserByUserName(userName);
		if(user != null){
			return "";
		}
		else{
			return "�û��������ڣ�";
		}
	}
	
}
