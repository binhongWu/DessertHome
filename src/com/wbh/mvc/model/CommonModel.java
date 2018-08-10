package com.wbh.mvc.model;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.wbh.mvc.util.MvcUtil;

/**
 * 通用模型对象，要求模型中写入的用于接收请求数据的属性必须为包装类型
 * @author admin
 *
 */
public class CommonModel implements ServiceModel{
	private List<ModelForward> list;
	private Class modelClass;
	
	public CommonModel(){
		list = new ArrayList<ModelForward>();
		modelClass = this.getClass();
	}
	public void addModelForward(ModelForward modelForard) {
		list.add(modelForard);
	}

	public String execute() {
		// TODO Auto-generated method stub
		return null;
	}

	public ModelForward getModelForward(String name) {
		for(ModelForward modelForward : list){
			if(modelForward.getName().equals(name)){
				return modelForward;
			}
		}
		return null;
	}

	/**
	 * 重置清空
	 */
	public void reset() {
		//所有属性
		Field[] fields = modelClass.getDeclaredFields();
		if(fields != null){
			//遍历属性
			try {
				for(Field f : fields){
					//获取属性的类型
					Class type = f.getType();
					//查找对应的set方法
					Method method = modelClass.getMethod("set"+MvcUtil.upperFirst(f.getName()), type);
					//根据属性的类型重置值
					System.out.println(method.getName());
					method.invoke(this, new Object[]{null});
				}
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		
	}
	
}
