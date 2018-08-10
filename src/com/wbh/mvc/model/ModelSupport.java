package com.wbh.mvc.model;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ģ�͵ĸ�����
 * @author admin
 *
 */
public class ModelSupport {
	private static HttpServletRequest request;
	private static HttpServletResponse response;
	//���ڸ��ݵ�ǰ���е��̷߳�װ���ݵĶ���
	//private static ThreadLocal<HttpServletRequest> threadLocal = new ThreadLocal<>();
	/**
	 * ��ȡ����
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		//return threadLocal.get();
		return request;
	}
	
	public static void setRequest(HttpServletRequest request) {
		//���֮ǰ���������
		ModelSupport.request = null;
		ModelSupport.request = request;
	}
	
	
	public static HttpServletResponse getResponse() {
		return response;
	}

	public static void setResponse(HttpServletResponse response) {
		ModelSupport.response = response;
	}

	/**
	 * ��ȡ�Ự
	 * @return
	 */
	public static HttpSession getSession() {
		//HttpServletRequest request = getRequest();
		if(request != null){
//			ServletContext application=request.getSession().getServletContext();
//			application.setAttribute("sesionId", request.getSession().getId());
			return request.getSession();
		}
			
		//return null;
		throw new RuntimeException("session is closed!!");
	}
}
