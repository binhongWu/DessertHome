package com.wbh.pojo;

public class Kind {
	//����Id
	private int kindId;
	//������
	private String kindName;
	

	
	//�ղι���
	public Kind() {
		super();
	}

	//���ι���
	public Kind(String kindName) {
		super();
		this.kindName = kindName;
	}

	//������get��set����
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
