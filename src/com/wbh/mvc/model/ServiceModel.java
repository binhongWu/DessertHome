package com.wbh.mvc.model;
/**
 * ҵ��ģ�ͽӿ�
 * @author admin
 *
 */
public interface ServiceModel {
	/**
	 * ҵ������
	 * @return ��Ӧ������
	 */
	public String execute();
	
	/**
	 * ���ģ����ͼ
	 */
	public void addModelForward(ModelForward modelForard);
	
	/**
	 * ��ȡģ����ͼ
	 * @return
	 */
	public ModelForward getModelForward(String name);
	
	/**
	 * ����ģ������
	 */
	public void reset();
}
