package com.wbh.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wbh.dao.AddressDao;
import com.wbh.dao.OrderDao;
import com.wbh.dao.UserDao;
import com.wbh.mvc.model.DispatchModel;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.pojo.Address;
import com.wbh.pojo.Order;
import com.wbh.pojo.User;
import com.wbh.util.CommonUtil;

public class OrderManageModel extends DispatchModel{
	private String page;
	private String ordername;
	

	public String getOrdername() {
		return ordername;
	}


	public void setOrdername(String ordername) {
		this.ordername = ordername;
	}


	public String getPage() {
		return page;
	}


	public void setPage(String page) {
		this.page = page;
	}


	@Override
	public String execute() {
//		System.out.println("saksadɽ���ƽ𿴵���");
		HttpServletRequest request=ModelSupport.getRequest();
		OrderDao orderdao=new OrderDao();
		AddressDao addressdao=new AddressDao();
		UserDao userdao=new UserDao();
		String userName=request.getParameter("ordername");
		int topicconut=orderdao.findAllOrderCount();
		//��ȡÿҳ�ļ�¼����
		int size=CommonUtil.getSize();
//		if()
//		//������ҳ��CommonUtil
		int totalpage;
		if(topicconut%size==0){
			totalpage=topicconut/size;
		}
		else{
			totalpage=topicconut/size+1;
		}
//		//��ǰҳ
		int nowpage=1;
		if(request.getParameter("page")!=null){
			nowpage=Integer.parseInt(request.getParameter("page"));
		}
//		//��ҳ���ֵ�ҳ��
		int size1=5;
//		//���÷�ҳ�ķ�Χ
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
		List<Order> list=new ArrayList();
		
		if(ordername!=null){
			User user=userdao.findUserByUserName(ordername);
			if(user!=null){
				list=orderdao.findOrderByUserId(user.getUserId());
			}
			else{
				list=orderdao.findAllOrderByPage(nowpage);
			}	
		}
		else{
			list=orderdao.findAllOrderByPage(nowpage);
		}	
		
		//������ַ���Ϻ��û������������ո��ݵ�ַid���û�id�ҵ��Ķ���
		List<Address> addresslist=new ArrayList();
		List<User> userlist=new ArrayList();
		 
		for(Order  t:list){
			List<Address> addlist=addressdao.findAddressByAddressId(t.getAddressId());
			User user=userdao.findUserByUserId(t.getUserId());
			addresslist.add(addlist.get(0));
			t.setUserName(user.getUserName());		
		}
		System.out.println(list.size());
		request.setAttribute("min",min);
		request.setAttribute("size",size);
		request.setAttribute("max",max);
		request.setAttribute("nowpage",nowpage);
		request.setAttribute("totalpage",totalpage);
		request.setAttribute("addreslist", addresslist);
//		request.setAttribute("uselist", userlist);
		request.setAttribute("orderlist", list);
		return "success";
	}
	
}
