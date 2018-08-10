package com.wbh.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wbh.dao.DessertDao;
import com.wbh.dao.KindDao;
import com.wbh.dao.TasteDao;
import com.wbh.mvc.model.DispatchModel;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.pojo.Dessert;
import com.wbh.pojo.Kind;
import com.wbh.pojo.Taste;

public class ShowDessertModel extends DispatchModel {
	KindDao kindDao;
	TasteDao tasteDao;
	DessertDao dessertDao;
	public ShowDessertModel(){
		kindDao=new KindDao();
		tasteDao = new TasteDao();
		dessertDao = new DessertDao();
	}
	@Override
	public String execute() {
		//������ҳ���ʼ��������AJAX����
		int count=-100;
		//�������з���
		List<Kind> kindList=kindDao.findAllKind();
//		System.out.println(kindList.size());
		HttpServletRequest request=ModelSupport.getRequest();
		int kindId = 0;
		int tasteId = 0;
		
		try {
			//��÷���Id
			if(request.getParameter("kindId") != null){
				kindId = Integer.parseInt(request.getParameter("kindId"));
			}
			//��ÿ�ζId
			if(request.getParameter("tasteId") != null){
				tasteId = Integer.parseInt(request.getParameter("tasteId"));
			}
			if(request.getParameter("count") != null){
				System.out.println("count is not null");
				count = Integer.parseInt(request.getParameter("count"));
				
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		//������Ʒ������Ҹ÷����µ����п�ζ
		List<Taste> tasteList = null;
		//kind����
		Kind kindObj = null;
		if(kindId == 0){
			//�������з�������еĿ�ζ
			tasteList = tasteDao.findAllTaste();
		}else{
			//���ݷ�����ҿ�ζ
			tasteList = tasteDao.findTasteByKindId(kindId);
			//����Kind����
			kindObj = kindDao.findKindByKindId(kindId);
		}
		System.out.println("count:"+count+"?????");
		System.out.println("kindId:"+kindId);
		if(kindObj != null){
			System.out.println(kindObj.getKindName()+"////////////////");
		}else{
			System.out.println("is null/////////////////");
		}
		//������Ʒ����Ϳ�ζ������Ʒ
		List<Dessert> dessertList = null;
		//��������������㣨��Ϊ4�������
		if(kindId == 0 && tasteId == 0){
			dessertList = dessertDao.findAllDessert();
		}else if(kindId == 0 && tasteId != 0){
			dessertList = dessertDao.findDessertByTasteId(tasteId);
		}else if(kindId != 0 && tasteId == 0){
			dessertList = dessertDao.findDessertByKindId(kindId);
		}else{
			dessertList = dessertDao.findDessertByKindIdTasteId(kindId, tasteId);
		}
		System.out.println("kindId="+kindId+";tasteId="+tasteId);
		System.out.println("kindList:"+kindList.size());
		System.out.println("tasteList:"+tasteList.size());
		System.out.println("dessertList:"+dessertList.size());
		//�������
		request.setAttribute("kindId", kindId);
		request.setAttribute("kindObj", kindObj);
		request.setAttribute("tasteId", tasteId);
		request.setAttribute("kindList", kindList);
		request.setAttribute("tasteList", tasteList);
		request.setAttribute("dessertList", dessertList);
		//ҳ����ת
		if(count==0){
			return "showDessert";
		}else{
			return "ajaxShowDessert";
		}
		
		
	}
	
}