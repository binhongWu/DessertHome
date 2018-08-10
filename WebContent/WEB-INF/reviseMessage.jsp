<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'reviseMessage.jsp' starting page</title>
    
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
  	.wwxtd{
  		text-align: center;
  		font-size: 25px;
  		width: 500px;
  		height: 60px;
  		line-height: 60px;
  		
  	}
  	.wwxrevisediv{
  		border: 1px solid  gray;
  		width: 300px;
  		height: 120px;
  	}
  	#tab input{
  		position: relative;
  		left: 150px;
  		top:30px;
  	}
  	.wwxdiv55{
  		width:500px;
  		height: 400px;
  		position: absolute;
  		left: 200px;
  		top:100px;
  		border: 1px solid red;
  		z-index: 10;
  		background-color:#EEEEEE;
  		display: non1e;
  	}
  </style>
  <body>
  <div>
		  <div class="wwxdiv55">
		  	<form action="revisesuccess.do?messageid=${messageid}" method="post">
		  		<table id="tab">
		  			<tr>
		  				<td class="wwxtd" colspan="2">留言处理</td>
		  			</tr>
		  			
		  				<c:forEach items="${messageinfo}" var="bbsmessage">
		  					<tr>
		  					<td><div style="position: relative;left: 20px;">用户名:</div></td>
		  					<td><span>${user.userName}</span></td>
		  					</tr>
		  					<tr>
		  						<td><div style="position: relative;left: 20px;">留言时间:</div></td>
		  						<td><fmt:formatDate value="${bbsmessage.messageTime}" pattern="yyyy-MM-dd"/></td>
		  					</tr>
		  					<tr>
		  						<td><div style="position: relative;top:-50px;left: 20px">留言内容:</div></td>
		  						<td><div class="wwxrevisediv">${bbsmessage.messageContent}</div></td>
		  					</tr>
		  					<tr>
		  						<td><input type="submit" value="已阅"></td>
		  						<td><input type="button" value="返回" id="btn"/></td>
		  					</tr>
		  				</c:forEach>		
		  		</table>
		  	</form>
		  </div>
		  </div>	
  </body>
  <script type="text/javascript">
  //为返回首页绑定事件
  	var btn=document.getElementById("btn");
  	btn.onclick=function(){
  	window.location.href="messageManage.do";
  	}
  </script>
</html>
