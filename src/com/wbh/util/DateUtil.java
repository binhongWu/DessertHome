package com.wbh.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	 static SimpleDateFormat format=new SimpleDateFormat();
	 	/**
	 	 * ����������ת�����ַ������
	 	 * @param date
	 	 * @param pattern
	 	 * @return
	 	 */
		public static String DatePattern(Date date,String pattern){
			format.applyPattern(pattern);
			return format.format(date);
		}
		/**
		 * ���ַ�������ת�������ڸ�ʽ
		 * @param date
		 * @param pattern
		 * @return
		 */
		public static Date StringPattern(String date,String pattern){
			format.applyPattern(pattern);
			try {
				return format.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				return null;
			}
		}
}