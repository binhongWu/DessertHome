package com.wbh.mvc.util;

import javax.servlet.http.HttpServletRequest;

/**
 * MVC框架工具类
 * @author admin
 *
 */
public class MvcUtil {
	/**
	 * 解析请求路径
	 * @param request
	 * @return  .do前的请求路径
	 */
	public static String parseRequest(HttpServletRequest request){
		String path = request.getRequestURI();
		return path.substring(path.lastIndexOf("/")+1,path.lastIndexOf(".do"));
	}
	/**
	 * 截取配置文件路径
	 * @param path
	 * @return
	 */
	public static String substrPath(String path){
		return path.substring(path.indexOf("/")+1);
	}
	
	/**
	 * 首字母大写转换
	 * @param fieldName
	 * @return
	 */
	public static String upperFirst(String fieldName){
		//获取第一个字符
		String first = fieldName.substring(0,1);
		//大写转换
		first = first.toUpperCase();
		return first.concat(fieldName.substring(1));
	}
}
