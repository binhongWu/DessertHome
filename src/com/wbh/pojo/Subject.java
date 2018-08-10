package com.wbh.pojo;

import java.util.Date;

public class Subject {
	//ר����
	private int subjectId;
	//ר������
	private String subjectName;
	//ר��ͼƬ
	private String subjectImg;
	//ר�����
	private String introduce;
	//ר����ʼʱ��
	private Date beginTime;
	//ר������ʱ��
	private Date endTime;
	//��ۿ�
	private int discount;
	//ר����ղι���
	public Subject(){}
	//���ι���
	public Subject( String subjectName, String subjectImg,
			String introduce, Date beginTime, Date endTime, int discount) {
		super();
		this.subjectName = subjectName;
		this.subjectImg = subjectImg;
		this.introduce = introduce;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.discount = discount;
	}
	
	
	public Subject(String subjectName, String subjectImg, String introduce,
			int discount) {
		super();
		this.subjectName = subjectName;
		this.subjectImg = subjectImg;
		this.introduce = introduce;
		this.discount = discount;
	}
	//����ȫ��get��set����
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getSubjectImg() {
		return subjectImg;
	}
	public void setSubjectImg(String subjectImg) {
		this.subjectImg = subjectImg;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	
}
