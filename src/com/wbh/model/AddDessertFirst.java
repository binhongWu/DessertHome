package com.wbh.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wbh.dao.KindDao;
import com.wbh.mvc.model.DispatchModel;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.pojo.Kind;

public class AddDessertFirst extends DispatchModel{

	@Override
	public String execute() {
		// TODO Auto-generated method stub
		HttpServletRequest request=ModelSupport.getRequest();
		KindDao kinddao=new KindDao();
		List<Kind> list=kinddao.findAllKind();
		request.setAttribute("kindlist", list);
		return "success";
	}
	
}
