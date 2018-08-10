package com.wbh.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wbh.dao.SubjectDao;
import com.wbh.mvc.model.DispatchModel;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.pojo.Subject;

public class ShowSubjectModel extends DispatchModel {
	//活动Dao
	SubjectDao subjectDao;
	public ShowSubjectModel(){
		subjectDao=new SubjectDao();
	}
	@Override
	public String execute() {
		HttpServletRequest request=ModelSupport.getRequest();
		//查找专题活动
		List<Subject> list=subjectDao.findSubject();
		//根据专题编号查找专题活动
		List<Subject> list1=subjectDao.findSubjectBySubjectId

(Integer.parseInt(request.getParameter("subjectId")));
		System.out.println("专题活动:"+list1.size());
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
