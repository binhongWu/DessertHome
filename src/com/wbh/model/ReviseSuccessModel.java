package com.wbh.model;

import com.wbh.dao.MessageDao;
import com.wbh.mvc.model.DispatchModel;

public class ReviseSuccessModel extends DispatchModel{
	private String messageid;
	
	public String getMessageid() {
		return messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}

	@Override
	public String execute() {
		// TODO Auto-generated method stub
		
		int messageid=Integer.parseInt(this.messageid);
		MessageDao messagedao=new MessageDao();
		if(messageid!=0){
			messagedao.reviseMessage(messageid);
		}
		return "success";
	}
	
}
