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
    
    <title>My JSP 'messageManage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/messageManage.css">
	<script type="text/javascript" src="js/jquery-1.8.0.js"></script>
  </head>
  <style>
  	  
  </style>
  <body>
   <%@ include file="include/header.jsp"%>
   <div class="container">
 <div class="wwx_div" align="center">
  <div class="wwx_div2">
  <div class="wwx_div5">留言管理</div>
  	<input type="button" id="btn1" value="返回首页" style="position: relative;left: 250px"/>
  	<div class="clear">
    	<div class="wwxdiv222">编号</div> 
    	<div class="wwxdiv222" style="width: 60px;">用户名  </div>
    	<div class="wwxdiv222" style="width: 80px;">留言时间 </div>
    	<div class="wwxdiv222" style="width: 80px;">状态</div>
    </div>	
    	<c:forEach items="${messagelist}" var="message" varStatus="status">
						<div class="clear" >
							<div class="wwxdiv222">${status.index}</div>
							<div class="wwxdiv222">${message.userName}</div>
							<div class="wwxdiv222" style="width: 120px;">
								<fmt:formatDate value="${message.messageTime}" pattern="yyyy-MM-dd"/>
							</div>
							<div class="wwxdiv222">
								<c:if test="${1==message.messageFlag}">
    								<span>未读</span>
    							</c:if>
    							<c:if test="${0==message.messageFlag}">
    								<span>已阅</span>
    							</c:if>
							</div>
						</div>
						<div class="clear">
							<div class="wwxdivmessagecontent">留言内容：</div>
							<div class="wwxdivmessagecontent1">${message.messageContent}</div>						
							<div class="wwxdivmessagecontent2">
							<c:if test="${1==message.messageFlag}">
    								<a href="reviseMessage.do?messageid=${message.messageId}">未处理</a>
    							</c:if>	
							</div>
							<div class="wwxdivmessagecontent2">
							<c:if test="${0==message.messageFlag}">
    								<a >已处理</a>
    							</c:if>	
							</div>
						</div>	
    	</c:forEach>
    <!-- 分页 -->
 	<div >
 		<table>
 			<tr id="wwxtr1">
    		<td ><div class="wwxtd6">总页数：${totalpage}</div></td>
    		<td  class="wwxtd2">
		<c:forEach begin="${min}" end="${max}" step="1" var="i">
			<c:if test="${i==nowpage}">
				<a href="messageManage.do?page=${i}" class="wwxtd4">&nbsp;${i}&nbsp;</a>
			</c:if>
			<c:if test="${i!=nowpage}">
				<a href="messageManage.do?page=${i}" class="wwxtd5">&nbsp;${i}&nbsp;</a>
			</c:if>
		</c:forEach>
		</td>
		<td><div class="wwxtd3" style="">
		<form action="messageManage.do" method="post" onSubmit="return validate()">
			<span class="wwxtd9">跳转至：</span><input type="text" style="width: 40px;color: #746F6F" name="page" id="page" onblur="page1()" />
					<input type="submit" value="确定"/><span id="pageerror" class="wwxtd7"></span>
		</form>
		</div>
		</td>
 		</tr>
 		</table>	
 	</div>
 	<div style="height: 30px;"></div>
  		</div>
 </div>
 </div>
 <%@ include file="include/footer.jsp"%>
  </body>
  <script type="text/javascript">
  //提交表单方法
  function validate(){
		if(!page1()){
			return false;
		}
	}
	//找到返回首页按钮对象并为其绑定onclick方法
	var btn1=document.getElementById("btn1");
  	btn1.onclick=function(){
  	window.location.href="backgroundManager.do";
  	}
  	//页面跳转框的校验
  	function page1(){	
		var page5=$("#page").val();			
		$("#pageerror").html("");
		if(page5<=0||page5>"${totalpage}"){
			$("#pageerror").html("输入的页数无效");
			return false;
		}
		return true;
	}
  </script>
</html>
