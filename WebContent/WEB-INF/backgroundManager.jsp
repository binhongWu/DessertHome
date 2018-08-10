<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'backgroundManager.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/backgroundManager.css">
	<script src="js/jquery-1.8.0.js"></script>
  </head>
  
  <body class="lcy_bodyStyle">
	<div class="lcy_container">
	<div class="lcy_borderBox">
		<li class="lcy_li_style"> <a href="addDessertFirst.do">新品上架</a></li>
		<li class="lcy_li_style"> <a href="addSubjectFirst.do">专题发布</a></li>
		<li class="lcy_li_style"> <a href="ordersearch.do">订单管理</a></li>
		<li class="lcy_li_style"> <a href="search.do">用户管理</a></li>
		<li class="lcy_li_style"> <a href="messageManage.do">留言管理</a></li>
		<li class="lcy_li_style"> <a href="index.jsp">退出</a></li>
	</div>
	</div>
</body>
</html>
