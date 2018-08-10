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
 * ���Ŀ����������ڽ��ղ��ַ�����
 * @author admin
 *
 */
public class ActionServlet extends HttpServlet{
	private ServletContext application;
	//ģ�͹���
	private ModelFactory factory;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//��������
			String flag = MvcUtil.parseRequest(req);
			//��װ����
			ModelSupport.setRequest(req);
			//��ȡģ�Ͷ���
			ServiceModel model = factory.buildModel(flag);
			//����ҵ��
			String result = model.execute();
			//�ж�ģ������
			if(model instanceof DispatchModel){
				//��ȡ��ͼ
				System.out.println(result);
				String url = model.getModelForward(result).getUrl();
				//������ͼ������Ӧ
				req.getRequestDispatcher(url).forward(req, resp);
			}
			else if(model instanceof TextModel){
				resp.setCharacterEncoding("utf-8");
				resp.getWriter().write(result);
				resp.getWriter().flush();
			}
			
			
			//����ҵ��ģ�͵�����
			model.reset();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * ���������ļ�
	 * @throws DocumentException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private void parseConfig() throws DocumentException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		//����������
		SAXReader reader = new SAXReader();
		//����web.xml�е������ļ�
		String path = application.getInitParameter("configPath");
		String configPath = "";
		Document document = null;
		//���web.xml�м��������ļ�
		if(path != null)
			configPath = application.getRealPath("")+path;
		else{
			//Ĭ���������src/config.xml
			configPath = this.getClass().getResource("/")+"config.xml";
			configPath = MvcUtil.substrPath(configPath);
		}
		//��ȡ�����ļ��ĵ�
		document = reader.read(new File(configPath));
		//��ȡ���ڵ�
		Element root = document.getRootElement();
		//��ȡ���е�model�ڵ�
		List<Element> models = root.elements("model");
		//����model
		for(Element model : models){
			//��ȡmodel��id����
			Attribute attr = model.attribute("id");
			String id = attr.getValue();
			//��ȡmodel-class�ڵ�
			Element classNode = model.element("model-class");
			//��ȡ�ڵ��ֵ
			String className = classNode.getText();
			//�����ȡģ�͵�ʵ������
			ServiceModel modelObj = (ServiceModel)Class.forName(className).newInstance();
			//����model-forward
			Element forwardNode = model.element("model-forward");
			if(forwardNode != null){
				//��ȡ���е�ģ����ת��ͼ
				List<Element> forwardList = forwardNode.elements();
				if(forwardList != null){
					//����
					for(Element childForwardNode : forwardList){
						//��ȡ��ͼ�ļ�
						String name = childForwardNode.attribute("name").getValue();
						//��ȡ��ͼ��URL
						String url = childForwardNode.getText();
						//��װ��modelForward����
						ModelForward modelForward = new ModelForward(name, url);
						//���ģ����ͼ
						modelObj.addModelForward(modelForward);
					}
				}
			}
			
			//��ģ�ͷ�װ����ģ�͹���
			factory.addModel(id, modelObj);
		}
	
		
	}
	
	@Override
	public void init() throws ServletException {
		//��ȡ������
		application = getServletContext();
		//ʵ����ģ�͹���
		factory = ModelFactory.getFactoryInstance();
		
		try {
			//�����ļ��Ľ���
			parseConfig();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
