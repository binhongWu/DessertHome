package com.wbh.mvc.util;

import javax.servlet.http.HttpServletRequest;

/**
 * MVC��ܹ�����
 * @author admin
 *
 */
public class MvcUtil {
	/**
	 * ��������·��
	 * @param request
	 * @return  .doǰ������·��
	 */
	public static String parseRequest(HttpServletRequest request){
		String path = request.getRequestURI();
		return path.substring(path.lastIndexOf("/")+1,path.lastIndexOf(".do"));
	}
	/**
	 * ��ȡ�����ļ�·��
	 * @param path
	 * @return
	 */
	public static String substrPath(String path){
		return path.substring(path.indexOf("/")+1);
	}
	
	/**
	 * ����ĸ��дת��
	 * @param fieldName
	 * @return
	 */
	public static String upperFirst(String fieldName){
		//��ȡ��һ���ַ�
		String first = fieldName.substring(0,1);
		//��дת��
		first = first.toUpperCase();
		return first.concat(fieldName.substring(1));
	}
}
