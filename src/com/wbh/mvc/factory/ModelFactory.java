package com.wbh.mvc.factory;

import java.util.HashMap;
import java.util.Map;

import com.wbh.mvc.model.ServiceModel;

/**
 * 模型工厂,工厂模式设计
 * @author admin
 *
 */
public class ModelFactory {
	private Map<String, ServiceModel> modelMap;
	private static ModelFactory factory;
	
	private ModelFactory(){
		modelMap = new HashMap<String, ServiceModel>();
	}
	
	public static synchronized ModelFactory getFactoryInstance(){
		if(factory == null){
			factory = new ModelFactory();
		}
		return factory;
	}
	/**
	 * 生产获取模型对象
	 * @param flag
	 * @return
	 */
	public ServiceModel buildModel(String flag){
		return modelMap.get(flag);
	}
	/**
	 * 添加模型
	 * @param id
	 * @param className
	 */
	public void addModel(String id, ServiceModel model){	
		modelMap.put(id, model);		
	}
}
