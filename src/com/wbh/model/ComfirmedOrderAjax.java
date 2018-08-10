package com.wbh.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.wbh.dao.CityDao;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.mvc.model.TextModel;
import com.wbh.pojo.City;

public class ComfirmedOrderAjax extends TextModel{

	private CityDao cityDao;
	@Override
	public String execute() {
		cityDao=new CityDao();
		HttpServletRequest request=ModelSupport.getRequest();
		int proId=Integer.parseInt(request.getParameter("provinceId"));
		
		System.out.println("当前的省份编号是："+proId);
		JSONArray json=new JSONArray();
		//根据省份编号查找城市
		List<City> cityList=cityDao.findAllCityByProvinceId(proId);
		System.out.println("当前的城市长度是："+cityList.size());
		for(City city:cityList){
			//将每个User对象转换成JSONObject
			JSONObject obj = new JSONObject();
			obj.put("cityId", city.getCityId());
			obj.put("cityName", city.getCityName());
			System.out.println("当前城市编号是："+city.getCityId());
			System.out.println("当前城市名称是："+city.getCityName());
			json.add(obj);
		}
			return json.toString();
	}	
}
