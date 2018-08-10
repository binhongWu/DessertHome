package com.wbh.model;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wbh.dao.MessageDao;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.mvc.model.TextModel;
import com.wbh.pojo.Message;
import com.wbh.pojo.User;

public class AddMessageAjax extends TextModel{
	private String messageContent;
	private MessageDao messageDao;
	public AddMessageAjax(){
		messageDao = new MessageDao();
	}
	
	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	@Override
	public String execute() {
		HttpSession session = ModelSupport.getSession();
		HttpServletRequest req = ModelSupport.getRequest();
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(session.getAttribute("loginUser") == null){
			return "error";
		}else{
			User user = (User)session.getAttribute("loginUser");
			if(user.getUserFlag()==-100){
				return "对不起，该账户的留言功能已被禁用！";
			}else{
				//获得用户填写的留言内容
				if(messageContent==null){
					System.out.println("为空");
					return "为空";
				}else{
//					String messageContent = req.getParameter("messageContent");
					System.out.println(this.messageContent);
					Message message = new Message(user.getUserId(), messageContent, new Date(), 1);
					messageDao.addMessage(message);
					System.out.println("插入数据库");
					return "提交成功！";
				}
				
			}
		}
		
	}
}
