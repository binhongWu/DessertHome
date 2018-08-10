package com.wbh.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import com.wbh.dao.DessertDao;
import com.wbh.dao.KindDao;
import com.wbh.mvc.model.DispatchModel;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.pojo.Dessert;
import com.wbh.pojo.Kind;
import com.wbh.util.DateUtil;

public class AddDessertModel extends DispatchModel{
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
		
		//������ӵ���Ʒ����
		Dessert des=new Dessert();
		//����ʱ��
		String time="";
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
//					System.out.println("��ʼ�ϴ���");
					//�ϴ����ļ���
//					System.out.println(item.getName());
					//��ȡ�ϴ��ļ���������
					InputStream input=item.getInputStream();
					
					//��ȡ�ϴ��ļ����ļ���
					String fileName=item.getName().substring(item.getName().lastIndexOf("\\")+1);
					if(i==10){
						des.setDessertImg_S(fileName);
//						System.out.println(fileName);
					}
					else if(i==11){
						des.setDessertImg_B(fileName);
//						System.out.println(fileName);
					}
					
					
					//��ȡ��Ŀ����·��
					String path=request.getSession().getServletContext().getRealPath("/");
//					System.out.println(path);
					//�����ϴ�·��
					path=path+"\\DessertData\\"+fileName;
					
					System.out.println("��ʾ·��"+path);
					
					//��ȡ�����
					FileOutputStream output=new FileOutputStream(new File(path));
					
					int data;
					
					while((data=input.read())!=-1)
					{
						output.write(data);
					}
					
					output.flush();
					output.close();
					input.close();
				}
				
				else{
					
					//��ȡ�ַ�����ʽ���ļ�����  �Ǳ�Ԫ��ͨ���÷�����ȡ��Ԫ�ص�ֵ
					//System.out.println(item.getString());
					//�ı�������������Ҫ�ع��ַ���
					String s = new String(item.getString("iso-8859-1").getBytes("iso-8859-1"),"utf-8");
					System.out.println(s);
					if(i==2){
						des.setDessertName(s);
						System.out.println(s);
					}
					else if(i==3){
						
						des.setDessertPrice(Integer.parseInt(s));
						System.out.println(des.getDessertPrice()+1);
					}
					else if(i==4){
						des.setWeight(Integer.parseInt(s));
						System.out.println(des.getWeight()+1);
					}
					else if(i==5){
						des.setKeepTip(s);
					}
					else if(i==6){
						des.setKeepTime(s);
					}
					else if(i==8||i==7){
						time=time+s+"-";
						System.out.println(time);
					}
					else if(i==9){
						time=time+s;
						Date endtime=StringPattern(time, "yyyy-mm-dd");
						des.setPublishTime(endtime);
					}
					else if(i==12){
						KindDao kind=new KindDao();
						List<Kind> list=kind.findKindlistByKindname(s);
						des.setKindId(list.get(0).getKindId());
						System.out.println(des.getKindId());
					}
					else if(i==13){
						des.setDessertInfo(s);
//						System.out.println("askkjsaldkl");
//						System.out.println(s);
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

			DessertDao desdao=new DessertDao();

			desdao.addDessert(new Dessert(des.getDessertName(), des.getDessertImg_S(), des.getDessertPrice(), des.getDessertInfo(), des.getPublishTime(), des.getWeight(), des.getKeepTip(),des.getDessertImg_B(), des.getKindId(), des.getKeepTime()));
			//request.getRequestDispatcher("/WEB-INF/success.jsp").forward(request, response);
		}
		else
		{
			//request.getRequestDispatcher("/WEB-INF/failed.jsp").forward(request, response);
		}
		return "success";
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
