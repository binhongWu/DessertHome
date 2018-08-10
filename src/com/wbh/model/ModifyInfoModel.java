package com.wbh.model;

import com.wbh.dao.UserDao;
import com.wbh.mvc.model.DispatchModel;

public class ModifyInfoModel extends DispatchModel{
	private UserDao userDao;
	private String userName;
	private String newPwd;
	
	public ModifyInfoModel(){
		userDao = new UserDao();
	}

	@Override
	public String execute() {
		// TODO Auto-generated method stub
		userDao.modifyPwd(newPwd, userName);
		return "success";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	
}
