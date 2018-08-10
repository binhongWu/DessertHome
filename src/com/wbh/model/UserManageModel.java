package com.wbh.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wbh.dao.UserDao;
import com.wbh.dao.util.DaoUtil;
import com.wbh.mvc.model.DispatchModel;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.pojo.User;
import com.wbh.util.CommonUtil;

public class UserManageModel extends DispatchModel{
	private String username;
	private String page;
	
	
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String execute() {
		// TODO Auto-generated method stub
//		System.out.println("大家好好s");
		HttpServletRequest request=ModelSupport.getRequest();
		UserDao userdao=new UserDao();
		List<User> list=new ArrayList<User>();
		String userName=request.getParameter("username");
		int topicconut=userdao.findAllUserCount();
//		System.out.println(this.username);
		if(username!=null){
			topicconut=0;
		}
		//获取每页的记录条数
		int size=CommonUtil.getSize();
		
//		//计算总页数CommonUtil
		int totalpage;
		if(topicconut%size==0){
			totalpage=topicconut/size;
		}
		else{
			totalpage=topicconut/size+1;
		}
//		//当前页
		int nowpage=1;
		if(request.getParameter("page")!=null){
			nowpage=Integer.parseInt(request.getParameter("page"));
		}
//		//分页呈现的页数
		int size1=5;
//		//设置分页的范围
		int min,max;
		if(nowpage<3){
			min=1;
			max=size1;
		}
		else{
			min=nowpage-2;
			max=nowpage+2;
		}
		if(max>totalpage){
			max=totalpage;
		}
		if(username!=null){
			User user=userdao.findUserByUserName(userName);
//			System.out.println(user.getUserName());
			if(user!=null){
				list.add(user);
			}
			else {
				list=userdao.findAllUserBypage(nowpage);
			}
		}
		else{
			list=userdao.findAllUserBypage(nowpage);
		}
		request.setAttribute("min",min);
		request.setAttribute("size",size);
		request.setAttribute("max",max);
		request.setAttribute("totalpage",totalpage);
		request.setAttribute("nowpage",nowpage);
		request.setAttribute("userlist", list);
		return "success";
	}
	
}
