package com.wbh.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wbh.bean.CartInfo;
import com.wbh.dao.DessertDao;
import com.wbh.mvc.model.DispatchModel;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.pojo.CartRecord;
import com.wbh.pojo.Dessert;

public class ShowCartModel extends DispatchModel{
	private DessertDao dessertDao;
	public ShowCartModel(){
		dessertDao=new DessertDao();
	}
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		double goodsTotalPrice=0;
		HttpServletRequest request=ModelSupport.getRequest();
		HttpSession session= ModelSupport.getSession();
		//初始化一个CartInfo列表
		List<CartInfo> cartInfoList=new ArrayList<CartInfo>();
		List<CartRecord> cartRecordList=(List<CartRecord>)session.getAttribute("userCartRecordList");
		System.out.println("购物数据："+cartRecordList.size()+"条");
		//循环遍历，向列表中写入数据
		for(CartRecord cartRecord:cartRecordList){
			Dessert dessert=dessertDao.findDessertByDessertId(cartRecord.getDessertId());
			goodsTotalPrice+=dessert.getDessertPrice()*cartRecord.getDessertNumber();
			cartInfoList.add(new CartInfo(dessert, cartRecord.getDessertNumber(),dessert.getDessertPrice()*cartRecord.getDessertNumber()));
			System.out.println("正在封装数据");
		}
		System.out.println("封装数据");
		//将封装好的数据写入请求
		request.setAttribute("listLength",cartInfoList.size());
		request.setAttribute("userCartInfoList", cartInfoList);
		request.setAttribute("goodsTotalPrice", goodsTotalPrice);
		return "showCart";
	}
	
}
