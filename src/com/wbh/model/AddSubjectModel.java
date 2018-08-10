package com.wbh.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import com.wbh.dao.SubjectDao;
import com.wbh.mvc.model.DispatchModel;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.util.DateUtil;

public class AddSubjectModel extends DispatchModel{
	public String execute() {
		HttpServletRequest request=ModelSupport.getRequest();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//�Ƿ��ϴ��ɹ��ı�ʶ
		boolean isSuccess=true;
		//������ӵ�ר�����
		com.wbh.pojo.Subject sub = new com.wbh.pojo.Subject();
		//�������յ�ר�⿪ʼʱ��
		String c="";
		//��������ר�����ʱ��
		String d="";
		//�����ļ��ϴ�����
		DiskFileUpload disk=new DiskFileUpload();
		
		//�趨�ϴ����ݵ��ַ���
		disk.setHeaderEncoding("utf-8");
		
		//����������ʱ�ļ���
		File tempDirectory=new File("d:/temp");
		
		if(!tempDirectory.exists())
		{
			tempDirectory.mkdir();
		}
	
		//�趨������ʱ�ļ�
		disk.setRepositoryPath("d:/temp");
		
		//�趨�����ļ���С  ��λ�ֽ�
		disk.setSizeThreshold(1024*10);
		
		//�����ϴ��ļ���С
		disk.setSizeMax(1024*1024*100);
		
		try
		{
			
			//��ȡ��������е���������ȡ�ύ���󼯺�
			List<FileItem> fileList=disk.parseRequest(request);
			int i=1;
			//�����ύ�Ķ���
			for(FileItem item : fileList)
			{
				//��ȡ�ϴ��ļ���·��(������file)
//				System.out.println("ÿһ��");
				i++;
				
				//��ȡ��Ԫ�ص�name����
				//System.out.println(item.getFieldName());
				//��ȡ�ļ���С
				//System.out.println(item.getSize());
				
				
				//�ж��ļ��Ƿ����ϴ�����
				//����ֵtrue��ʾ��Ԫ������ͨ�ı�Ԫ�أ��磺text,password,checkbox��
				//����ֵfalse��ʾ��Ԫ����file�ļ���Ԫ��
				if(!item.isFormField())
				{
					System.out.println("��ʼ�ϴ���");
					//�ϴ����ļ���
					System.out.println(item.getName());
					//��ȡ�ϴ��ļ���������
					InputStream input=item.getInputStream();
					//��ȡ�ϴ��ļ����ļ���
					String fileName=item.getName().substring(item.getName().lastIndexOf("\\")+1);
					sub.setSubjectImg(fileName);
					
					
					//��ȡ��Ŀ����·��
					String path=request.getSession().getServletContext().getRealPath("/");
					
					path = path.substring(0,path.length()-1);
//					System.out.println(path);
//					System.out.println(path);
					//�����ϴ�·��
					path=path+"\\SubjectData\\"+fileName;
					
//					System.out.println(path);
					
					//��ȡ�����
					FileOutputStream output=new FileOutputStream(new File(path));
					
					int data;
					
					while((data=input.read())!=-1)
					{
						output.write(data);
					}
					System.out.println("�ļ��ϴ��ɹ���");
					output.flush();
					output.close();
					input.close();
//					System.out.println("�ϴ��ɹ�");
				}
				else{
					
					//��ȡ�ַ�����ʽ���ļ�����  �Ǳ�Ԫ��ͨ���÷�����ȡ��Ԫ�ص�ֵ
					//System.out.println(item.getString());
					//�ı�������������Ҫ�ع��ַ���
					String s = new String(item.getString("iso-8859-1").getBytes("iso-8859-1"),"utf-8");
					System.out.println("ר����Ϣ���£�");
					System.out.println(i+"��:"+s);
					if(i==2){
						sub.setSubjectName(s);
						
					}
					else if(i==3||i==4){
						c=c+s+"-";
					}
					else if(i==5){
						c=c+s;
						Date begintime=StringPattern(c, "yyyy-mm-dd");
						sub.setBeginTime(begintime);
					}
					else if(i==6||i==7){
						d=d+s+"-";
					}
					else if(i==8){
						d=d+s;
						Date endtime=StringPattern(d, "yyyy-mm-dd");
						sub.setEndTime(endtime);
					}
					else if(i==9){
						
						sub.setDiscount(Integer.parseInt(s));
//						System.out.prntln(s);
					}
					else if(i==11){
						sub.setIntroduce(s);
					}
//					System.out.println(s);
//					System.out.println(new String(item.getString("iso-8859-1").getBytes("iso-8859-1"),"utf-8"));
				}
				
			}
			
			
		} catch (FileUploadException e)
		{
			isSuccess=false;
			
			request.setAttribute("error", e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (Exception e)
		{
			isSuccess=false;
			
			request.setAttribute("error", e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(isSuccess)
		{		
			
//			String subname=request.getParameter("SubjectName");
//			String S_discount=request.getParameter("S_discount");	
//			String SubjectIntroduce=request.getParameter("SubjectIntroduce");
//			
//			
			SubjectDao subdao=new SubjectDao();
			subdao.addSubject(new com.wbh.pojo.Subject(sub.getSubjectName(),sub.getSubjectImg(),sub.getIntroduce(),sub.getBeginTime(),sub.getEndTime(),sub.getDiscount()));
			
			//request.getRequestDispatcher("/WEB-INF/success.jsp").forward(request, response);
		}
		else
		{
			//request.getRequestDispatcher("/WEB-INF/failed.jsp").forward(request, response);
		}
		return "success";
	}

	private ServletRequest getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}
	public Date StringPattern(String date,String pattern){
		SimpleDateFormat format=new SimpleDateFormat();
		format.applyPattern(pattern);
		try {
			return format.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
}
