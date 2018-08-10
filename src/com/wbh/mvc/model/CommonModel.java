package com.wbh.mvc.model;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.wbh.mvc.util.MvcUtil;

/**
 * ͨ��ģ�Ͷ���Ҫ��ģ����д������ڽ����������ݵ����Ա���Ϊ��װ����
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
	 * �������
	 */
	public void reset() {
		//��������
		Field[] fields = modelClass.getDeclaredFields();
		if(fields != null){
			//��������
			try {
				for(Field f : fields){
					//��ȡ���Ե�����
					Class type = f.getType();
					//���Ҷ�Ӧ��set����
					Method method = modelClass.getMethod("set"+MvcUtil.upperFirst(f.getName()), type);
					//�������Ե���������ֵ
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
