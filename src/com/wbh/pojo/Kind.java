package com.wbh.pojo;

public class Kind {
	//类型Id
	private int kindId;
	//类型名
	private String kindName;
	

	
	//空参构造
	public Kind() {
		super();
	}

	//带参构造
	public Kind(String kindName) {
		super();
		this.kindName = kindName;
	}

	//下面是get和set方法
	public int getKindId() {
		return kindId;
	}
	public void setKindId(int kindId) {
		this.kindId = kindId;
	}
	public String getKindName() {
		return kindName;
	}
	public void setKindName(String kindName) {
		this.kindName = kindName;
	}
	
	
	
}
