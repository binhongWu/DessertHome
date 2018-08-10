package com.wbh.pojo;

import java.util.Date;

public class User {
	private int userId;
	private String userName;
	private String userPwd;
	private int userSex;
	private int userPoint;
	private int userFlag;
	private Date regTime;
	private int rid;
	
	
	


	public User(String userName, String userPwd, int userSex, Date regTime) {
		super();
		this.userName = userName;
		this.userPwd = userPwd;
		this.userSex = userSex;
		this.regTime = regTime;
	}

	public User(){}
	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public int getUserSex() {
		return userSex;
	}

	public void setUserSex(int userSex) {
		this.userSex = userSex;
	}

	public int getUserPoint() {
		return userPoint;
	}

	public void setUserPoint(int userPoint) {
		this.userPoint = userPoint;
	}

	public int getUserFlag() {
		return userFlag;
	}

	public void setUserFlag(int userFlag) {
		this.userFlag = userFlag;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	
	
}
