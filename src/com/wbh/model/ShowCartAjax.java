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

public class ShowCartAjax extends DispatchModel{
	private String dessertId;
	private DessertDao dessertDao;
	

	public String getDessertId() {
		return dessertId;
	}



	public void setDessertId(String dessertId) {
		this.dessertId = dessertId;
	}
	
	public ShowCartAjax(){
		dessertDao=new DessertDao();
		
	}

	@Override
	public String execute() {
		// TODO Auto-generated method stub
		List<CartInfo> cartInfoList=new ArrayList<CartInfo>();
		HttpServletRequest request=ModelSupport.getRequest();
		HttpSession session= ModelSupport.getSession();
		int dessertId=Integer.parseInt(this.dessertId);
		//��¼��Ҫɾ�����ݵ�����
		int index=0;
		List<CartRecord> userCartRecordList;
		if(dessertId==0){
			//��չ��ﳵ����
			userCartRecordList=new ArrayList<CartRecord>();
			session.setAttribute("userCartRecordList", userCartRecordList);
		}else{
			//����Idɾ����Ӧ��Ʒ
			userCartRecordList=(List<CartRecord>)session.getAttribute("userCartRecordList");
			for(CartRecord cardRecord:userCartRecordList){
				if(cardRecord.getDessertId()==dessertId){
					break;
				}
				index++;
			}
			userCartRecordList.remove(index);
		}
		//��ʼ���ܽ��
		double goodsTotalPrice=0;
		//ѭ�����������б���д������
		for(CartRecord cartRecord:userCartRecordList){
			Dessert dessert=dessertDao.findDessertByDessertId(cartRecord.getDessertId());
			goodsTotalPrice+=dessert.getDessertPrice()*cartRecord.getDessertNumber();
			cartInfoList.add(new CartInfo(dessert, cartRecord.getDessertNumber(),dessert.getDessertPrice()*cartRecord.getDessertNumber()));
		}
		//����װ�õ�����д������
		request.setAttribute("listLength",cartInfoList.size());
		request.setAttribute("userCartInfoList", cartInfoList);
		request.setAttribute("goodsTotalPrice", goodsTotalPrice);
		return "showCartAjax";
	}
	
}
