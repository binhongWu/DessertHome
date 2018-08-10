package com.wbh.mvc.controller;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.wbh.model.LoginModel;
import com.wbh.model.RegistModel;
import com.wbh.mvc.factory.ModelFactory;
import com.wbh.mvc.model.DispatchModel;
import com.wbh.mvc.model.ModelForward;
import com.wbh.mvc.model.ModelSupport;
import com.wbh.mvc.model.ServiceModel;
import com.wbh.mvc.model.TextModel;
import com.wbh.mvc.util.MvcUtil;

/**
 * 核心控制器，用于接收并分发请求
 * @author admin
 *
 */
public class ActionServlet extends HttpServlet{
	private ServletContext application;
	//模型工厂
	private ModelFactory factory;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//解析请求
			String flag = MvcUtil.parseRequest(req);
			//封装请求
			ModelSupport.setRequest(req);
			//获取模型对象
			ServiceModel model = factory.buildModel(flag);
			//处理业务
			String result = model.execute();
			//判断模型类型
			if(model instanceof DispatchModel){
				//提取视图
				System.out.println(result);
				String url = model.getModelForward(result).getUrl();
				//根据视图进行响应
				req.getRequestDispatcher(url).forward(req, resp);
			}
			else if(model instanceof TextModel){
				resp.setCharacterEncoding("utf-8");
				resp.getWriter().write(result);
				resp.getWriter().flush();
			}
			
			
			//重置业务模型的数据
			model.reset();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * 解析配置文件
	 * @throws DocumentException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private void parseConfig() throws DocumentException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		//创建解析器
		SAXReader reader = new SAXReader();
		//解析web.xml中的配置文件
		String path = application.getInitParameter("configPath");
		String configPath = "";
		Document document = null;
		//如果web.xml有加载配置文件
		if(path != null)
			configPath = application.getRealPath("")+path;
		else{
			//默认情况加载src/config.xml
			configPath = this.getClass().getResource("/")+"config.xml";
			configPath = MvcUtil.substrPath(configPath);
		}
		//读取配置文件文档
		document = reader.read(new File(configPath));
		//获取根节点
		Element root = document.getRootElement();
		//获取所有的model节点
		List<Element> models = root.elements("model");
		//遍历model
		for(Element model : models){
			//获取model的id属性
			Attribute attr = model.attribute("id");
			String id = attr.getValue();
			//获取model-class节点
			Element classNode = model.element("model-class");
			//获取节点的值
			String className = classNode.getText();
			//反射获取模型的实例对象
			ServiceModel modelObj = (ServiceModel)Class.forName(className).newInstance();
			//解析model-forward
			Element forwardNode = model.element("model-forward");
			if(forwardNode != null){
				//获取所有的模型跳转视图
				List<Element> forwardList = forwardNode.elements();
				if(forwardList != null){
					//遍历
					for(Element childForwardNode : forwardList){
						//获取视图的键
						String name = childForwardNode.attribute("name").getValue();
						//获取视图的URL
						String url = childForwardNode.getText();
						//封装成modelForward对象
						ModelForward modelForward = new ModelForward(name, url);
						//添加模型视图
						modelObj.addModelForward(modelForward);
					}
				}
			}
			
			//将模型封装加入模型工厂
			factory.addModel(id, modelObj);
		}
	
		
	}
	
	@Override
	public void init() throws ServletException {
		//获取上下文
		application = getServletContext();
		//实例化模型工厂
		factory = ModelFactory.getFactoryInstance();
		
		try {
			//配置文件的解析
			parseConfig();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
