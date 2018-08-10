package com.wbh.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wbh.dao.SubjectDao;
import com.wbh.mvc.model.DispatchModel;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.pojo.Subject;

public class ShowSubjectModel extends DispatchModel {
	//�Dao
	SubjectDao subjectDao;
	public ShowSubjectModel(){
		subjectDao=new SubjectDao();
	}
	@Override
	public String execute() {
		HttpServletRequest request=ModelSupport.getRequest();
		//����ר��
		List<Subject> list=subjectDao.findSubject();
		//����ר���Ų���ר��
		List<Subject> list1=subjectDao.findSubjectBySubjectId

(Integer.parseInt(request.getParameter("subjectId")));
		System.out.println("ר��:"+list1.size());
		if(list.size()!=0||list1.size()!=0){
			request.setAttribute("subjects", list);
			request.setAttribute("subject1", list1);
			return "find";
		}
		else{
			return "notFind";
		}
	}
	
}
