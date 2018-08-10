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
		//是否上传成功的标识
		boolean isSuccess=true;
		//创建添加的专题对象
		com.wbh.pojo.Subject sub = new com.wbh.pojo.Subject();
		//创建接收的专题开始时间
		String c="";
		//创建接收专题结束时间
		String d="";
		//创建文件上传对象
		DiskFileUpload disk=new DiskFileUpload();
		
		//设定上传内容的字符集
		disk.setHeaderEncoding("utf-8");
		
		//创建缓冲临时文件夹
		File tempDirectory=new File("d:/temp");
		
		if(!tempDirectory.exists())
		{
			tempDirectory.mkdir();
		}
	
		//设定缓冲临时文件
		disk.setRepositoryPath("d:/temp");
		
		//设定缓冲文件大小  单位字节
		disk.setSizeThreshold(1024*10);
		
		//设置上传文件大小
		disk.setSizeMax(1024*1024*100);
		
		try
		{
			
			//读取请求对象中的输入流获取提交对象集合
			List<FileItem> fileList=disk.parseRequest(request);
			int i=1;
			//遍历提交的对象
			for(FileItem item : fileList)
			{
				//获取上传文件的路径(仅限于file)
//				System.out.println("每一次");
				i++;
				
				//获取表单元素的name属性
				//System.out.println(item.getFieldName());
				//获取文件大小
				//System.out.println(item.getSize());
				
				
				//判断文件是否是上传对象
				//返回值true表示该元素是普通的表单元素，如：text,password,checkbox等
				//返回值false表示该元素是file文件域元素
				if(!item.isFormField())
				{
					System.out.println("开始上传：");
					//上传的文件名
					System.out.println(item.getName());
					//获取上传文件的输入流
					InputStream input=item.getInputStream();
					//获取上传文件的文件名
					String fileName=item.getName().substring(item.getName().lastIndexOf("\\")+1);
					sub.setSubjectImg(fileName);
					
					
					//获取项目绝对路径
					String path=request.getSession().getServletContext().getRealPath("/");
					
					path = path.substring(0,path.length()-1);
//					System.out.println(path);
//					System.out.println(path);
					//设置上传路径
					path=path+"\\SubjectData\\"+fileName;
					
//					System.out.println(path);
					
					//获取输出流
					FileOutputStream output=new FileOutputStream(new File(path));
					
					int data;
					
					while((data=input.read())!=-1)
					{
						output.write(data);
					}
					System.out.println("文件上传成功！");
					output.flush();
					output.close();
					input.close();
//					System.out.println("上传成功");
				}
				else{
					
					//获取字符串形式的文件内容  非表单元素通过该方法获取表单元素的值
					//System.out.println(item.getString());
					//文本框中文乱码需要重构字符串
					String s = new String(item.getString("iso-8859-1").getBytes("iso-8859-1"),"utf-8");
					System.out.println("专题信息如下：");
					System.out.println(i+"次:"+s);
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
