package com.wbh.pojo;

import java.util.Date;

public class Subject {
	//专题编号
	private int subjectId;
	//专题名称
	private String subjectName;
	//专题图片
	private String subjectImg;
	//专题介绍
	private String introduce;
	//专题活动开始时间
	private Date beginTime;
	//专题活动结束时间
	private Date endTime;
	//活动折扣
	private int discount;
	//专题类空参构造
	public Subject(){}
	//带参构造
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
	//下面全是get和set方法
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
