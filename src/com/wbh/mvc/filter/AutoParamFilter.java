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
 * �Զ�ע����ģ�͵Ĺ�����
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
		//��������
		String flag = MvcUtil.parseRequest((HttpServletRequest)req);
		//��ȡģ�Ͷ���
		ServiceModel model = factory.buildModel(flag);
//		if(model == null){
//			System.out.println("model is null");
//		}else{
//			System.out.println(model.getClass());
//		}

		System.out.println(flag);
		//��ȡģ�Ͷ��������ʱ��
		Class<ServiceModel> modelClass = (Class<ServiceModel>)model.getClass();
		//�������󣬻�ȡ�����еĲ���
		Enumeration<String> paramNames = req.getParameterNames();
		//ѭ������
		while(paramNames.hasMoreElements()){
			String name = paramNames.nextElement();
			//��ȡ�����в�����ֵ
			String value = req.getParameter(name);
			try {
				//����ģ�͵�����
				Method method = modelClass.getMethod("set"+MvcUtil.upperFirst(name), value.getClass());
				if(method != null){
					//���÷���
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
