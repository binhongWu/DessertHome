package com.wbh.pojo;
/**
 * ���ﳵ�ݴ�������
 * @author admin
 *
 */
public class CartRecord {
	//��Ʒ���
	private int dessertId;
	//��Ʒ����
	private int dessertNumber;
	//�û����
	private int userId;
	/**
	 * ���ι���
	 * @param desertId
	 * @param dessertNum
	 * @param userId
	 */
	public CartRecord(int desertId, int dessertNumber, int userId) {
		super();
		this.dessertId = desertId;
		this.dessertNumber = dessertNumber;
		this.userId = userId;
	}
	/**
	 * �ղι���
	 */
	public CartRecord(){}
	//������get��set����
	
	
	public int getDessertId() {
		return dessertId;
	}
	public int getDessertNumber() {
		return dessertNumber;
	}
	public void setDessertNumber(int dessertNumber) {
		this.dessertNumber = dessertNumber;
	}
	public void setDessertId(int dessertId) {
		this.dessertId = dessertId;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
