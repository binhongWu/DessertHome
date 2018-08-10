package com.wbh.pojo;

public class Collection {
	private int userId;
	private int dessertId;
	
	public Collection(int userId, int dessertId) {
		super();
		this.userId = userId;
		this.dessertId = dessertId;
	}

	public Collection(){}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getDessertId() {
		return dessertId;
	}

	public void setDessertId(int dessertId) {
		this.dessertId = dessertId;
	}
	
	
}
