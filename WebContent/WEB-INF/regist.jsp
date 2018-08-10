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
    
    <title>My JSP 'regist.jsp' starting page</title>
    
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
		<div style="height: 450px" class="wf_loginBox">
			<form action="regist.do" method="post" onSubmit="return validate()">
				<div class="wf_loginTitle">用户注册</div>
				<div class="wf_textDiv">
					<input class="wf_textBox" type="text" name="userName" onBlur="userNameCheck()" placeholder="请输入用户名">
				</div>
				<div class="wf_textDiv">
					<input class="wf_textBox" type="password" name="pwd" onBlur="passwordCheck()" placeholder="密码由数字字母组成，不得低于4位！">
				</div>
				<div class="wf_textDiv">
					<input class="wf_textBox" type="password" name="cPwd" onBlur="cPasswordCheck()" placeholder="确认密码">
				</div>
				<div style="color:#442818;font-size:15px" class="wf_textDiv">
					<input class="sexChoose" type="radio" name="sex" value="1">男
					<input class="sexChoose" type="radio" name="sex" value="0">女
				</div>
				<div class="wf_textDiv clear">
					<input class="wf_codeBox" type="text" name="inputCode" onBlur="codeCheck()" placeholder="请输入验证码">
					<div class="wf_codeImg"><img id="img" src="code.jsp"></div>
					<div class="wf_changeCode"></div>
				</div>
				<div class="wf_errorDiv"></div>
				<div class="wf_textDiv"><input class="wf_textBtn" type="submit" value="注册" onSubmit=""></div>
			</form>
		</div>
	</div>
	<%@include file="include/footer.jsp" %>
  </body>
  <script>
  	//密码正则
  	/* var rule = /^\d{4,}$/; */
  	var rule = /^(.*[A-Za-z].*\d.*)|(.*\d.*[A-Za-z].*)$/;
  	$(".wf_textBox:eq(0)").blur(function(){
  		if($(".wf_textBox:eq(0)").val()!=""){
  			//ajax判断用户名是否存在
			var userName = $(".wf_textBox:eq(0)").val();
			$.ajax({
				type:"post",
				url:"registAjax.do",
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

  	$(".wf_codeBox").blur(function(){
	  	if($(".wf_codeBox").val()!=null){
	  		//ajax判断验证码输入
			var code = $(".wf_codeBox").val();
			$.ajax({
				type:"post",
				url:"codeAjax.do",
				data:{"inputCode":code},
				success:function(data){
					if(data==""){
						$(".wf_errorDiv").html("");
					}
					else{
						$(".wf_errorDiv").html(data);
						$(".wf_codeBox").val("");
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
		else{
			if(!rule.test($(".wf_textBox:eq(1)").val())){
				$(".wf_errorDiv").html("密码由数字字母组成，不得低于4位！");
				$(".wf_textBox:eq(1)").val("");
				return false;
			}
		}
		return true;
	}
	//确认密码框
	function cPasswordCheck(){
		if($(".wf_textBox:eq(2)").val()==""){
			$(".wf_errorDiv").html("密码由数字字母组成，不得低于4位！");
			return false;
		}
		else{
			if($(".wf_textBox:eq(1)").val()==""){
				$(".wf_errorDiv").html("请先输入密码！");
				$(".wf_textBox:eq(2)").val("");
				return false;
			}
			else{
				if($(".wf_textBox:eq(1)").val()!=$(".wf_textBox:eq(2)").val()){
					$(".wf_errorDiv").html("两次密码不一致！");
					$(".wf_textBox:eq(2)").val("");
					return false;
				}
			}
		}
		return true;
	}
	//性别选项
	function sexCheck(){
		if(!$(".sexChoose:eq(0)").attr("checked")&&!$(".sexChoose:eq(1)").attr("checked")){
			$(".wf_errorDiv").html("请选择性别！");
			return false;
		}
		return true;
	}
	//验证码框
	function codeCheck(){
		if($(".wf_codeBox").val()==""){
			$(".wf_errorDiv").html("验证码有误！");
			return false;
		}
		return true;
	}
	//更换验证码
	$(".wf_changeCode").click(function(){
		var ran=Math.floor(Math.random()*10000);
		document.getElementById("img").src="code.jsp?id="+ran;
	})
	//注册按钮
	function validate(){
		if(!codeCheck()||!userNameCheck()||!passwordCheck()||!cPasswordCheck()||!sexCheck()){
			return false;
		}
		return true;
	}
  </script>
</html>
