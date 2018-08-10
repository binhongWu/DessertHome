package com.wbh.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.wbh.dao.AreaDao;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.mvc.model.TextModel;
import com.wbh.pojo.Area;

public class ComfirmedOrderAreaAjax extends TextModel{
	private AreaDao areaDao;
	@Override
	public String execute() {
		areaDao=new AreaDao();
		HttpServletRequest request=ModelSupport.getRequest();
		int cityId=Integer.parseInt(request.getParameter("cityId"));
		//根据城市编号查找地区
		List<Area> areaList=areaDao.findAllAreaByCityId(cityId);
		System.out.println("当前地区长度："+areaList.size());
		JSONArray json=new JSONArray();
		for(Area area:areaList){
			JSONObject obj=new JSONObject();
			obj.put("areaId", area.getAreaId());
			obj.put("areaName", area.getAreaName());
			json.add(obj);
		}
		return json.toString();
	}
	
}
