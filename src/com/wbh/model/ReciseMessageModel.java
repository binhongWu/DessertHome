package com.wbh.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wbh.dao.MessageDao;
import com.wbh.dao.UserDao;
import com.wbh.mvc.model.DispatchModel;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.pojo.Message;
import com.wbh.pojo.User;

public class ReciseMessageModel extends DispatchModel{
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
		HttpServletRequest request=ModelSupport.getRequest();
		int messageid=Integer.parseInt(this.messageid);
//		System.out.println(messageid+"�տ��ʵ����");
//		System.out.println(messageid+"sadasdasdasdasdasd");
		MessageDao messagedao=new MessageDao();
		UserDao userdao=new UserDao();
		
		List<Message> list=messagedao.findMessageByMessageid(messageid);
		User user=userdao.findUserByUserId(list.get(0).getUserId());
//		System.out.println(user.getUserName());
		request.setAttribute("messageid", messageid);
		request.setAttribute("user", user);
		request.setAttribute("messageinfo", list);
		return "success";
	}
	
}
