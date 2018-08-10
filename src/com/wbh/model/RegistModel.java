package com.wbh.model;

import java.util.Date;

import javax.servlet.http.HttpSession;

import com.wbh.dao.UserDao;
import com.wbh.mvc.model.DispatchModel;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.pojo.User;

public class RegistModel extends DispatchModel{
	private UserDao userDao;
	private String userName;
	private String pwd;
	private String sex;
	public RegistModel(){
		userDao = new UserDao();
	}

	@Override
	public String execute() {
		// TODO Auto-generated method stub
		User user=new User(userName,pwd,Integer.parseInt(sex),new Date());
		userDao.addUser(user);
		HttpSession session=ModelSupport.getSession();
		session.setAttribute("loginUser", null);
		return "success";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
	
	
}
