<%@ page language="java" import="java.util.*,com.sun.image.codec.jpeg.*" pageEncoding="UTF-8"%>
<jsp:directive.page import="java.awt.Color"/>
<jsp:directive.page import="java.awt.image.BufferedImage"/>
<jsp:directive.page import="java.awt.Graphics2D"/>
<jsp:directive.page import="java.awt.Font"/>
<jsp:directive.page import="java.io.OutputStream"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'code.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <%
    	//验证码的宽度
    	int width=100;
    	//验证码的高度
    	int height=30;
    	
    	//验证码字符串
    	String s="";
    	
    	//验证码出现范围的数组
    	char[] code={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};
    	
    	//获取4位随机数
    	Random random=new Random();
    	for(int i=1;i<=4;i++)
    	{
    		s+=code[random.nextInt(code.length)];
    	}
    	
    	
    	//System.out.println(s);
    	
    	//保存验证码
    	session.setAttribute("code",s);
    	
    	//创建缓存图像
    	BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
    	
    	//创建图像
    	Graphics2D graph=image.createGraphics();
    	
    	//设置背景色
    	graph.setColor(Color.white);
    	//设置绘图区域
    	graph.fillRect(0,0,width,height);
    	
    	//创建字体
    	Font font=new Font("宋体",Font.BOLD+Font.ITALIC,height-5);
    	
    	//设置图像的字体
    	graph.setFont(font);
    	
    	//设置边框颜色
    	graph.setColor(Color.black);
    	
    	//绘制边框  前两个参数为起始坐标 后两个参数为边框的宽和高
    	graph.drawRect(0,0,width-1,height-1);
    	
    	//设置文字颜色  setColor根据图形现有内容决定为哪个部分着色 先背景，再边框，后文字
    	graph.setColor(getColor());
    	
    	//输出验证码
    	char c;
    	for(int i=0;i<s.length();i++)
    	{
    		c=s.charAt(i);
    		
    		graph.drawString(c+"",25*i+5,20);
    	}
    	
    	//输出干扰线
    	graph.setColor(Color.BLACK);
    	for(int i=1;i<=30;i++)
    	{
    		//线条起点的位置
    		int startX=random.nextInt(width);
    		int startY=random.nextInt(height);
    		
    		int endX=random.nextInt(5);
    		int endY=random.nextInt(5); 
    		//随机线条颜色
    		graph.setColor(getColor());
    		//绘制线条
    		graph.drawLine(startX,startY,startX+endX,startY+endY);
    	}
    	
    	//创建输出流
    	OutputStream output=response.getOutputStream();
    	
    	JPEGImageEncoder imageEncoder=JPEGCodec.createJPEGEncoder(output);
    	
    	imageEncoder.encode(image);
    	
    	//关闭输出流
    	output.close();
    	//清空页面输出缓存
    	out.clear();
    	//重新创建输出
    	out=pageContext.pushBody();
     %>
     <%!
    	//获取验证码字符的颜色
     	private Color getColor()
     	{
     		Random random=new Random();
     		
     		//定义色差值
     		int red;
     		int green;
     		int blue;
     		
     		//通过随机数获取色差值
     		red=random.nextInt(255);
     		green=random.nextInt(255);
     		blue=random.nextInt(255);
     		
     		//返回颜色
     		return new Color(red,green,blue).brighter();
     	}
      %>
  </body>
</html>
