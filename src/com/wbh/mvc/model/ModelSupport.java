package com.wbh.mvc.model;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 模型的辅助类
 * @author admin
 *
 */
public class ModelSupport {
	private static HttpServletRequest request;
	private static HttpServletResponse response;
	//用于根据当前运行的线程封装数据的对象
	//private static ThreadLocal<HttpServletRequest> threadLocal = new ThreadLocal<>();
	/**
	 * 获取请求
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		//return threadLocal.get();
		return request;
	}
	
	public static void setRequest(HttpServletRequest request) {
		//清空之前的请求对象
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
	 * 获取会话
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
