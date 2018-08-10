package com.wbh.model;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wbh.dao.CartRecordDao;
import com.wbh.dao.UserDao;
import com.wbh.mvc.model.DispatchModel;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.pojo.CartRecord;
import com.wbh.pojo.User;

public class LoginModel extends DispatchModel{
	private String userName;
	private String pwd;
	private String noLogin;
	private UserDao userDao;
	private CartRecordDao cartRecordDao;
	
	public LoginModel(){
		userDao = new UserDao();
		cartRecordDao=new CartRecordDao();
	}

	@Override
	public String execute() {
		System.out.println("??"+noLogin);
		// TODO Auto-generated method stub
		HttpServletRequest request = ModelSupport.getRequest();
		HttpServletResponse response = ModelSupport.getResponse();
		//�����û�
		User user = userDao.findUserByUserName(userName);
		if(user != null){
			if(user.getUserPwd().equals(pwd)){
				ModelSupport.getSession().setAttribute("loginUser", user);
				if(noLogin == "on"){
					Cookie cookieName = new Cookie("userName",userName);
					Cookie cookiePwd = new Cookie("pwd",pwd);
					cookieName.setMaxAge(60*60*24*7);
					cookiePwd.setMaxAge(60*60*24*7);
					response.addCookie(cookieName);
					response.addCookie(cookiePwd);
				}
				
				System.out.println("��ʼ�����û��Ĺ��ﳵ");
				List<CartRecord> cartRecordList=cartRecordDao.findCartListByUserId(user.getUserId());
				System.out.println("�û����ﳵ�Ĵ�С��"+cartRecordList.size());
				ModelSupport.getSession().setAttribute("userCartRecordList", cartRecordList);
				return "success";
			}
			else{
				request.setAttribute("error", "�������");
				return "error";
			}
		}
		else{
			request.setAttribute("error", "�û���������!");
			return "error";
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNoLogin() {
		return noLogin;
	}

	public void setNoLogin(String noLogin) {
		this.noLogin = noLogin;
	}
	
	
}
