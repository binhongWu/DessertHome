<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="d" uri="http://java.sun.com/jstl/fmt_rt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<script type="text/javascript" src="js/jquery-1.8.0.js"></script>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userManage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/userManage.css">
	<script type="text/javascript" src="js/jquery-1.8.0.js"></script>
  </head>
  <style>
  	.wwxdiverror{
  		height:20px;
  		width:160px;
  		color:red;
  		font-size: 13px;
  	}
  </style>
  <body>
  <%@ include file="include/header.jsp"%>
  <div class="container">
  <div class="wwx_div">	
 	 <div class="wwx_div2" align="center">
 	 <div class="wwx_div5">用户管理</div>
  	<form action="search.do" method="post" onSubmit="return validate1()">
    <table align="center">
    	<tr>
    		<td colspan="3"><input type="button" id="btn1" value="返回首页" style="position: relative;left: 380px"/></td>
    	</tr>
    	<tr>
    		<td> 搜索用户:</td>
    		<td>
    			<input type="text" name="username" id="txt" />
  				<input type="submit" value="搜索"/>
  				<input type="button" id="btn" value="返回"/>	 						
    		</td>
    		<td><div class="" id="usererror" class="wwxdiverror"></div></td>
    	</tr>	
    	</table>
  </form>
    	<table id="wwxtr1">
    	<tr >
    		<td >编号</td>
    		<td >用户名</td>
    		<td>性别</td>
    		<td>积分</td>
    		<td>用户类型</td>
    		<td>注册时间</td>
    		<td>操作</td>
    	</tr>
    	<c:forEach items="${userlist}" var="user" varStatus="status">
    	<tr>
    		<td>  			
    				<span>${(nowpage-1)*size+(status.index)+1}</span>	
    		</td>
    		<td>
    			<a>${user.userName}</a>
    		</td>
    		<td>
    			<c:if test="${1==user.userSex}">
    				<span>男</span>
    			</c:if>
    			<c:if test="${0==user.userSex}">
    				<span>女</span>
    			</c:if>
    		</td>
    		<td >
    			<span>${user.userPoint}</span>
    		</td>
    		<td>
    			<c:if test="${-1==user.userFlag}">
    				<span>管理员</span>
    			</c:if>
    			<c:if test="${0==user.userFlag}">
    				<span>普通用户</span>
    			</c:if>
    			<c:if test="${-100==user.userFlag}">
    				<span>禁用用户（被封号）</span>
    			</c:if>
    		</td>
    		<td>
    			<d:formatDate value="${user.regTime}" pattern="yyyy-MM-dd"/>
    		</td>
    		<td>
    			<input type="hidden" value="${user.userId}" id="clearUserId">
    			<input type="button" value="清空积分" onClick="return clearPoint()" disabled="disabled">
    		</td>		
    	</tr>	
    	</c:forEach>
 		<tr id="wwxtr1">
    		<td colspan="2"><div class="wwxtd6">总页数：${totalpage}</div></td>
    		<td colspan="2" class="wwxtd1">
		<c:forEach begin="${min}" end="${max}" step="1" var="i">
			<c:if test="${i==nowpage}">
				<a href="search.do?page=${i}" class="wwxtd2">&nbsp;${i}&nbsp;</a>
			</c:if>
			<c:if test="${i!=nowpage}">
				<a href="search.do?page=${i}" class="wwxtd3">&nbsp;${i}&nbsp;</a>
			</c:if>
		</c:forEach>
		</td>
		<td colspan="2"><div class="wwxtd4">
		<form action="search.do" method="post" onSubmit="return validate()">
			<span class="wwxtd9">跳转至：</span><input type="text" style="width: 40px;color: #746F6F" name="page" id="page" onblur="page1()" />
					<input type="submit" value="确定"/><span id="pageerror" class="wwxtd7"></span>
		</form>
		</div>
		</td>
 		</tr>
  	</table>
  	<div style="height: 30px;"></div>
  	</div>
 </div>
 </div>
 <%@ include file="include/footer.jsp"%>
  </body>
  <script>
  //为返回按钮绑定事件
   var btn=document.getElementById("btn");
  	btn.onclick=function(){
  	window.location.href="userManageAction.jsp";
  	}
  	//为返回首页 按钮绑定事件
  	var btn1=document.getElementById("btn1");
  	btn1.onclick=function(){
  	window.location.href="backgroundManager.do";
  	}
  	//提交搜索表单
  	 function validate(){
		if(!page1()){
			return false;
		}
	}
	//提交确定表单
	function validate1(){
	var usererror = $("#usererror").val();
		if(usererror!=""){
			return false;
		}
	}
	//跳转框校验
  	function page1(){	
		var page5=$("#page").val();			
		$("#pageerror").html("");
		if(page5<=0||page5>"${totalpage}"){
			$("#pageerror").html("输入的页数无效");
			return false;
		}
		return true;
	}
	
		//搜索栏判断
		$("#txt").blur(function(){
  		if($("#txt").val()!=""){
  			//ajax判断用户名是否存在
			var userName = $("#txt").val();
			
			$.ajax({
				type:"post",
				url:"userManageAjax.do",
				data:{"userName":userName},
				success:function(data){
					if(data==""){
						$("#usererror").html("");
					}
					else{
						$("#usererror").html(data);
						$("#txt").val("");
					}
				}
			})
  		}
  	})
  	function clearPoint(){
		var userId=$("#clearUserId").val();
		/*$.ajax({
			type:"post",
			url:"clearPoint.do",
			data:{"userId":userId},
			success:function(data){
				if(data==""){
					$("#usererror").html("");
				}
				else{
					$("#usererror").html(data);
					$("#txt").val("");
				}
			}
		})*/	
	}
  </script>
</html>
