package com.wbh.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wbh.dao.UserDao;
import com.wbh.mvc.model.DispatchModel;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.pojo.User;

public class UserInfoModel extends DispatchModel{
	private UserDao userDao;
	private String userId;
	
	public UserInfoModel(){
		userDao = new UserDao();
	}

	@Override
	public String execute() {
		// TODO Auto-generated method stub
		HttpServletRequest request = ModelSupport.getRequest();
		User user = userDao.findUserByUserId(Integer.parseInt(userId));
		if(user!=null){
			request.setAttribute("userId", user.getUserId());
			request.setAttribute("userName", user.getUserName());
			request.setAttribute("pwd", user.getUserPwd());
			if(user.getUserSex()==1){
				request.setAttribute("sex", "男");
			}
			else{
				request.setAttribute("sex", "女");
			}
			request.setAttribute("userPoint", user.getUserPoint());
			if(user.getUserFlag()==-1){
				request.setAttribute("userFlag", "管理员");
			}
			else if(user.getUserFlag()==0){
				request.setAttribute("userFlag", "普通用户");
			}
			else{
				request.setAttribute("userFlag", "会员"+user.getUserFlag()+"级");
			}
			request.setAttribute("regTime", user.getRegTime());
		}
//		HttpSession session=ModelSupport.getSession();
//		session.setAttribute("loginUser", null);
		return "success";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
}
