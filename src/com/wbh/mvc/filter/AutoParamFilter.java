package com.wbh.mvc.filter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.wbh.mvc.factory.ModelFactory;
import com.wbh.mvc.model.ServiceModel;
import com.wbh.mvc.util.MvcUtil;

/**
 * 自动注参至模型的过滤器
 * @author admin
 *
 */

public class AutoParamFilter implements Filter{
	private ModelFactory factory;
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		//解析请求
		String flag = MvcUtil.parseRequest((HttpServletRequest)req);
		//获取模型对象
		ServiceModel model = factory.buildModel(flag);
//		if(model == null){
//			System.out.println("model is null");
//		}else{
//			System.out.println(model.getClass());
//		}

		System.out.println(flag);
		//获取模型对象的运行时类
		Class<ServiceModel> modelClass = (Class<ServiceModel>)model.getClass();
		//拦截请求，获取请求中的参数
		Enumeration<String> paramNames = req.getParameterNames();
		//循环迭代
		while(paramNames.hasMoreElements()){
			String name = paramNames.nextElement();
			//获取请求中参数的值
			String value = req.getParameter(name);
			try {
				//反射模型的属性
				Method method = modelClass.getMethod("set"+MvcUtil.upperFirst(name), value.getClass());
				if(method != null){
					//调用方法
					try {
						method.invoke(model, value);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		factory = ModelFactory.getFactoryInstance();
	}
}
