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
		
		System.out.println("��ǰ��ʡ�ݱ���ǣ�"+proId);
		JSONArray json=new JSONArray();
		//����ʡ�ݱ�Ų��ҳ���
		List<City> cityList=cityDao.findAllCityByProvinceId(proId);
		System.out.println("��ǰ�ĳ��г����ǣ�"+cityList.size());
		for(City city:cityList){
			//��ÿ��User����ת����JSONObject
			JSONObject obj = new JSONObject();
			obj.put("cityId", city.getCityId());
			obj.put("cityName", city.getCityName());
			System.out.println("��ǰ���б���ǣ�"+city.getCityId());
			System.out.println("��ǰ���������ǣ�"+city.getCityName());
			json.add(obj);
		}
			return json.toString();
	}	
}
