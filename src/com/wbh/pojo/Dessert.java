package com.wbh.pojo;

import java.util.Date;

public class Dessert {
	//甜点Id
	private int dessertId;
	//甜点名
	private String dessertName;
	//甜点图片的路径
	private String dessertImg_S;
	//甜点价格
	private double dessertPrice;
	//甜点详情
	private String dessertInfo;
	//甜点发布时间
	private Date publishTime;
	//甜点规格
	private int weight;
	//甜点保鲜条件
	private String keepTip;
	//甜品保质期
	private String keepTime;
	//甜点的分类
	private int kindId;
	//甜点大图的路径
	private String dessertImg_B;
	//添加属性（购买数量）
	private int count;
	//添加序号
	private int rnum;
	//添加销售量
	private int sumNum;

	//甜点类空参构造
	public Dessert() {
		super();
	}
	
	

	//带参构造
	
	public Dessert(String dessertName, String dessertImgS, double dessertPrice,
			String dessertInfo, Date publishTime, int weight, String keepTip,
			String dessertImgB) {
		this.dessertName = dessertName;
		dessertImg_S = dessertImgS;
		this.dessertPrice = dessertPrice;
		this.dessertInfo = dessertInfo;
		this.publishTime = publishTime;
		this.weight = weight;
		this.keepTip = keepTip;
		dessertImg_B = dessertImgB;
	}



	
	

	public Dessert(String dessertName, String dessertImgS,
			double dessertPrice, String dessertInfo, Date publishTime,
			int weight, String keepTip, String keepTime, int kindId,
			String dessertImgB) {
		super();
		this.dessertName = dessertName;
		dessertImg_S = dessertImgS;
		this.dessertPrice = dessertPrice;
		this.dessertInfo = dessertInfo;
		this.publishTime = publishTime;
		this.weight = weight;
		this.keepTip = keepTip;
		this.keepTime = keepTime;
		this.kindId = kindId;
		dessertImg_B = dessertImgB;
	}

	public Dessert(String dessertname, String dessertImgS, double dessertPrice,
			String dessertInfo, int weight, String keepTip, String dessertImgB) {
		this.dessertName = dessertName;
		dessertImg_S = dessertImgS;
		this.dessertPrice = dessertPrice;
		this.dessertInfo = dessertInfo;
		this.weight = weight;
		this.keepTip = keepTip;
		dessertImg_B = dessertImgB;
	}



	//下面全是get和set方法
	
	
	
	public String getDessertImg_B() {
		return dessertImg_B;
	}
	


	public int getSumNum() {
		return sumNum;
	}



	public void setSumNum(int sumNum) {
		this.sumNum = sumNum;
	}



	public int getRnum() {
		return rnum;
	}



	public void setRnum(int rnum) {
		this.rnum = rnum;
	}



	public String getKeepTime() {
		return keepTime;
	}



	public void setKeepTime(String keepTime) {
		this.keepTime = keepTime;
	}



	public void setDessertImg_B(String dessertImgB) {
		dessertImg_B = dessertImgB;
	}
	
	public String getDessertImg_S() {
		return dessertImg_S;
	}

	public void setDessertImg_S(String dessertImgS) {
		dessertImg_S = dessertImgS;
	}
	public int getDessertId() {
		return dessertId;
	}
	public void setDessertId(int dessertId) {
		this.dessertId = dessertId;
	}
	public String getDessertName() {
		return dessertName;
	}
	public void setDessertName(String dessertName) {
		this.dessertName = dessertName;
	}
	
	public double getDessertPrice() {
		return dessertPrice;
	}
	public void setDessertPrice(double dessertPrice) {
		this.dessertPrice = dessertPrice;
	}
	
	public String getDessertInfo() {
		return dessertInfo;
	}

	public void setDessertInfo(String dessertInfo) {
		this.dessertInfo = dessertInfo;
	}

	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getKeepTip() {
		return keepTip;
	}
	public void setKeepTip(String keepTip) {
		this.keepTip = keepTip;
	}
	public int getKindId() {
		return kindId;
	}
	public void setKindId(int kindId) {
		this.kindId = kindId;
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
