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
				return "�Բ��𣬸��˻������Թ����ѱ����ã�";
			}else{
				//����û���д����������
				if(messageContent==null){
					System.out.println("Ϊ��");
					return "Ϊ��";
				}else{
//					String messageContent = req.getParameter("messageContent");
					System.out.println(this.messageContent);
					Message message = new Message(user.getUserId(), messageContent, new Date(), 1);
					messageDao.addMessage(message);
					System.out.println("�������ݿ�");
					return "�ύ�ɹ���";
				}
				
			}
		}
		
	}
}
