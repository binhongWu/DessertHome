package com.wbh.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wbh.dao.MessageDao;
import com.wbh.dao.UserDao;
import com.wbh.mvc.model.DispatchModel;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.pojo.Message;
import com.wbh.pojo.User;

public class ShowMessageModel extends DispatchModel{
	private List<Message> messageList;
	private MessageDao messageDao;
	private UserDao userDao;
	public ShowMessageModel(){
		messageDao = new MessageDao();
		userDao = new UserDao();
	}
	@Override
	public String execute() {
		HttpServletRequest req = ModelSupport.getRequest();
		int page = 1;
		if(req.getParameter("page")!= null){
			try {
				page = Integer.parseInt(req.getParameter("page"));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//��ѯ������������
		messageList = messageDao.findMessageByPageMessageFlag(page);
		for(Message message:messageList){
			User user = userDao.findUserByUserId(message.getUserId());
			message.setUserName(user.getUserName());
		}
		req.setAttribute("messageList", messageList);
		System.out.println("message����:"+messageList.size());
		//��ʼ����ҳ��
		if(page == 1){
			return "showMessage";
		}
		//AJAX����ҳ��
		else{
			return "ajaxShowMessage";
		}
		
	}
}
