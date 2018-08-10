package com.wbh.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wbh.bean.CartInfo;
import com.wbh.dao.DessertDao;
import com.wbh.mvc.model.DispatchModel;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.pojo.CartRecord;
import com.wbh.pojo.Dessert;

public class ShowCartModel extends DispatchModel{
	private DessertDao dessertDao;
	public ShowCartModel(){
		dessertDao=new DessertDao();
	}
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		double goodsTotalPrice=0;
		HttpServletRequest request=ModelSupport.getRequest();
		HttpSession session= ModelSupport.getSession();
		//��ʼ��һ��CartInfo�б�
		List<CartInfo> cartInfoList=new ArrayList<CartInfo>();
		List<CartRecord> cartRecordList=(List<CartRecord>)session.getAttribute("userCartRecordList");
		System.out.println("�������ݣ�"+cartRecordList.size()+"��");
		//ѭ�����������б���д������
		for(CartRecord cartRecord:cartRecordList){
			Dessert dessert=dessertDao.findDessertByDessertId(cartRecord.getDessertId());
			goodsTotalPrice+=dessert.getDessertPrice()*cartRecord.getDessertNumber();
			cartInfoList.add(new CartInfo(dessert, cartRecord.getDessertNumber(),dessert.getDessertPrice()*cartRecord.getDessertNumber()));
			System.out.println("���ڷ�װ����");
		}
		System.out.println("��װ����");
		//����װ�õ�����д������
		request.setAttribute("listLength",cartInfoList.size());
		request.setAttribute("userCartInfoList", cartInfoList);
		request.setAttribute("goodsTotalPrice", goodsTotalPrice);
		return "showCart";
	}
	
}
