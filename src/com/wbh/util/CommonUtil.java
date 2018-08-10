package com.wbh.util;

import java.util.ResourceBundle;

/**
 * ����������
 * @author admin
 *
 */
public class CommonUtil {
	/**
	 * ��ȡ�����ļ�
	 */
	public static ResourceBundle bundle;
	
	static{
		bundle = ResourceBundle.getBundle("config");
	}
	/**
	 * ��ȡ�����ļ���ÿҳ�Ĵ�С
	 * @return
	 */
	public static int getSize(){
		String s = bundle.getString("size");
		int size = 0;
		if(s != null){
			size = Integer.parseInt(s);
		}
		return size;
	}
}