package com.wbh.dao;

import java.util.List;

import com.wbh.dao.util.DaoHandler;
import com.wbh.pojo.Subject;

public class SubjectDao {
	/**
	 * ����ר��
	 * 
	 */
	public void addSubject(Subject sub){
		DaoHandler.executeUpdate("insert into subject_ values(seq_subjectId.Nextval,?,?,?,?,?,?)", new Object[]{sub.getSubjectName(),sub.getSubjectImg(),sub.getIntroduce(),sub.getBeginTime(),sub.getEndTime(),sub.getDiscount()});
	}
	/**
	 * ����ר��
	 * @return
	 */
	public List<Subject> findSubject(){
		String sql="select * from subject_";
		List<Subject> list=DaoHandler.executeQueryMultiple(sql, null, Subject.class);
		return list;
	}
	/**
	 * ����ר���Ų���ר��
	 * @param subjectId
	 * @return
	 */
	public List<Subject> findSubjectBySubjectId(int subjectId){
		String sql="select * from subject_ where subjectId=?";
		List<Subject> list=DaoHandler.executeQueryMultiple(sql, new Object[]{subjectId}, Subject.class);
		return list;
	}
//	public static void main(String[] args) {
//		SubjectDao s=new SubjectDao();
//		System.out.println(s.findSubjectBySubjectId(21).size());
//		
//	}
}