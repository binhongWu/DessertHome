package com.wbh.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wbh.bean.CartInfo;
import com.wbh.dao.AddressDao;
import com.wbh.dao.AreaDao;
import com.wbh.dao.CityDao;
import com.wbh.dao.DessertDao;
import com.wbh.dao.ProvinceDao;
import com.wbh.dao.UserDao;
import com.wbh.mvc.model.DispatchModel;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.pojo.Address;
import com.wbh.pojo.Area;
import com.wbh.pojo.CartRecord;
import com.wbh.pojo.City;
import com.wbh.pojo.Dessert;
import com.wbh.pojo.Province;
import com.wbh.pojo.User;

public class ComfirmedOrderModel extends DispatchModel{
	private DessertDao dessertDao;
	private AddressDao addressDao;
	private ProvinceDao provinceDao;
	private UserDao userDao;

	@Override
	public String execute() {
		dessertDao=new DessertDao();
		addressDao=new AddressDao();
		provinceDao=new ProvinceDao();
		userDao=new UserDao();
		double goodsTotalPrice=0;
		HttpServletRequest request=ModelSupport.getRequest();
		HttpSession session= ModelSupport.getSession();
		//初始化一个CartInfo列表
		List<CartInfo> cartInfoList=new ArrayList<CartInfo>();
		List<CartRecord> cartRecordList=(List<CartRecord>)session.getAttribute("userCartRecordList");
		System.out.println("购物数据："+cartRecordList.size()+"条");
		//当前登录用户对象
		User user=(User)session.getAttribute("loginUser");
		//根据用户编号查找地址
		List<Address> addressList=addressDao.findAddressByUserId(user.getUserId());
		//查找所有省份
		List<Province> proList=provinceDao.findAllProvince();
		
		//循环遍历，向列表中写入数据
		for(CartRecord cartRecord:cartRecordList){
			Dessert dessert=dessertDao.findDessertByDessertId(cartRecord.getDessertId());
			goodsTotalPrice+=dessert.getDessertPrice()*cartRecord.getDessertNumber();
			cartInfoList.add(new CartInfo(dessert, cartRecord.getDessertNumber(),dessert.getDessertPrice()*cartRecord.getDessertNumber()));	
			userDao.addPoint((int)goodsTotalPrice, user.getUserId());
			System.out.println("//////////////"+goodsTotalPrice+"/////////////"+user.getUserId());
		}
		//将封装好的数据写入请求
		request.setAttribute("user", user);
		request.setAttribute("addressInfo", addressList);
		request.setAttribute("userCartInfoList", cartInfoList);
		request.setAttribute("goodsTotalPrice", goodsTotalPrice);
		request.setAttribute("proList", proList);
		return "yes";
	}
	
}
