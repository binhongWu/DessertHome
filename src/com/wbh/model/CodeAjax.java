package com.wbh.model;

import javax.servlet.http.HttpServletRequest;

import com.wbh.mvc.model.ModelSupport;
import com.wbh.mvc.model.TextModel;

public class CodeAjax extends TextModel{
	private String inputCode;
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		HttpServletRequest request = ModelSupport.getRequest();
		String code=request.getSession().getAttribute("code").toString();
		if(inputCode.equalsIgnoreCase(code)){
			return "";
		}
		else{
			return "ÑéÖ¤Âë´íÎó";
		}
	}
	public String getInputCode() {
		return inputCode;
	}
	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}
	
}
