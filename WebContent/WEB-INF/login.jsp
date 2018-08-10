<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="include/tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/login.css">
	<script src="js/jquery-1.8.0.js"></script>

  </head>
  
  <body>
  <%@include file="include/header.jsp" %>
    <div class="wf_container">
		<div class="wf_loginBox">
			<form action="login.do" method="post" onSubmit="return validate()">
				<div class="wf_loginTitle">账号密码登录</div>
				<div class="wf_textDiv">
					<input class="wf_textBox" type="text" name="userName" onBlur="userNameCheck()" placeholder="请输入您的用户名">
				</div>
				<div class="wf_textDiv">
					<input class="wf_textBox" type="password" name="pwd" onBlur="passwordCheck()" placeholder="请输入密码">
				</div>
				<div class="wf_errorDiv">${error }</div>
				<div class="wf_textDiv"><input class="wf_textBtn" type="submit" value="登录"></div>
				<div class="wf_textDiv clear">
					<div class="wf_checkBox"><input type="checkbox" name="noLogin"></div>
					<div class="wf_checkText">一周内免登陆</div>
					<div class="wf_toRegist"><a href="registPrepare.do">去注册</a></div>
				</div>
			</form>
		</div>
	</div>
	<%@include file="include/footer.jsp" %>
  </body>
  <script>
  	$(".wf_textBox:eq(0)").blur(function(){
  		if($(".wf_textBox:eq(0)").val()!=""){
  			//ajax判断用户名是否存在
			var userName = $(".wf_textBox:eq(0)").val();
			$.ajax({
				type:"post",
				url:"loginAjax.do",
				data:{"userName":userName},
				success:function(data){
					if(data==""){
						$(".wf_errorDiv").html("");
					}
					else{
						$(".wf_errorDiv").html(data);
						$(".wf_textBox:eq(0)").val("");
					}
				}
			})
  		}
  	})
  	//用户名框
	function userNameCheck(){
		if($(".wf_textBox:eq(0)").val()==""){
			$(".wf_errorDiv").html("请输入用户名！");
			return false;
		}
		return true;
	}
	//密码框
	function passwordCheck(){
		if($(".wf_textBox:eq(1)").val()==""){
			$(".wf_errorDiv").html("请输入密码！");
			return false;
		}
		return true;
	}
	//登录按钮
	function validate(){
		if(!userNameCheck()||!passwordCheck()){
			return false;
		}
	}
  </script>
</html>
