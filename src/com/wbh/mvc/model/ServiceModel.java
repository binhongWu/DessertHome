package com.wbh.mvc.model;
/**
 * 业务模型接口
 * @author admin
 *
 */
public interface ServiceModel {
	/**
	 * 业务处理方法
	 * @return 响应的数据
	 */
	public String execute();
	
	/**
	 * 添加模型视图
	 */
	public void addModelForward(ModelForward modelForard);
	
	/**
	 * 获取模型视图
	 * @return
	 */
	public ModelForward getModelForward(String name);
	
	/**
	 * 重置模型数据
	 */
	public void reset();
}
