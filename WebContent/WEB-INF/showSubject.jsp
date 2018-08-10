<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="include/tag.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showSubject.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

 </head>
<style>
	.wbh_subjectContainer{
		width:1200px;
		height: 600px;
		margin: auto;
		margin-top: 100px;
		
	}
	.wbh_subjectImg img{
		width:1200px;
		height: 600px;
	}
	.wbh_subjectContent{
		width:1000px;
		height: 150px;
		margin-top: 40px;
		position: relative;
		top: -200px;
		left: 0px;
	}
	.wbh_subjectContent p{
		letter-spacing: 5px;
		font-weight: bold;
		text-align: center;
		line-height: 80px;
		text-indent: 30px;
		color:#6C2619;
		font-size: 20px;
		
	}
	 
</style>
<body>
 <%@ include file="include/header.jsp" %>
<!--总容器-->
<div class="wbh_subjectContainer">
	<!--活动海报-->
	<c:forEach items="${subject1}" var="subject">
		<div class="wbh_subjectImg"><img src="indexImage/${subject.subjectImg}"></div>
		<!--活动内容-->
		<div class="wbh_subjectContent">
			<p>${subject.introduce}</p>
		</div>
	</c:forEach>
</div>
 <%@ include file="include/footer.jsp" %> 
</body>
</html>
