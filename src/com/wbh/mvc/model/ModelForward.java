package com.wbh.mvc.model;
/**
 * 模型视图的跳转对象
 * @author admin
 *
 */
public class ModelForward {
	private String name;
	private String url;
	
	public ModelForward(String name, String url) {
		super();
		this.name = name;
		this.url = url;
	}
	public ModelForward(){
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
