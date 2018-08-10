package com.wbh.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wbh.dao.DessertDao;
import com.wbh.dao.SubjectDao;
import com.wbh.mvc.model.DispatchModel;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.pojo.Dessert;
import com.wbh.pojo.Subject;

public class FirstPageModel extends DispatchModel{
	//�Dao
	SubjectDao subjectDao;
	//��ƷDao
	DessertDao dessertDao;
	public FirstPageModel(){
		subjectDao=new SubjectDao();
		dessertDao=new DessertDao();
	}
	@Override
	public String execute() {
		HttpServletRequest request=ModelSupport.getRequest();
		//����ר��
		List<Subject> list=subjectDao.findSubject();
		//������Ʒ������Ʒǰ��
		List<Dessert> newDessertList=dessertDao.findNewDessert();
		//�������������ǰ��
		List<Dessert> hotDessertList=dessertDao.findHotDessert();
		if(list.size()!=0||newDessertList.size()!=0||hotDessertList.size()!=0){
			request.setAttribute("subjects", list);
			request.setAttribute("newDessert", newDessertList);
			request.setAttribute("hotDesserts", hotDessertList);
			return "find";
		}
		else{
			return "notFind";
		}
	}
//	public static void main(String[] args) {
//		System.out.println(new FirstPageModel().subjectDao.findSubject().size());
//	}
	
}
