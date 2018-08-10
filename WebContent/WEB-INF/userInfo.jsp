<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="include/tag.jsp"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userInfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/userInfo.css">
	<script src="js/jquery-1.8.0.js"></script>

  </head>
  
  <body>
  <%@include file="include/header.jsp" %>
   	<div class="wf_container">
   		<div class="wf_infoBox">
   			<div class="wf_infoTitle clear">
   				<div class="wf_leftTitle">个人信息</div>
   				<div class="wf_rightTitle">修改密码</div>
   			</div>
   			<div class="wf_infoTab">
   				<table class="wf_tab">
   					<tr>
   						<td>用户名称： </td>
   						<td>${userName }</td>
   					</tr>
   					<tr>
   						<td>用户性别：</td>
   						<td>${sex }</td>
   					</tr>
   					<tr>
   						<td>用户积分：</td>
   						<td>${userPoint }</td>
   					</tr>
   					<tr>
   						<td>用户等级：</td>
   						<td>${userFlag }</td>
   					</tr>
   					<tr>
   						<td>注册时间：</td>
   						<td><fmt:formatDate value="${regTime }" pattern="yyyy-MM-dd" /></td>
   					</tr>
   				</table>
   			</div>
   			<div class="wf_modifyInfo">
   				<form action="modifyInfo.do" method="post" onSubmit="return validate()">
   					<input type="hidden" value="${userName }" name="userName">
   					<table class="wf_tab">
   						<tr>
   							<td>原密码：</td>
   							<td><input class="wf_modifyPwd" type="password" onblur="oldPwdCheck()" name="oldPwd"></td>
   						</tr>
   						<tr>
   							<td>新密码：</td>
   							<td><input class="wf_modifyPwd" type="password" onblur="newPwdCheck()" name="newPwd"></td>
   						</tr>
   						<tr>
   							<td>确认密码：</td>
   							<td><input class="wf_modifyPwd" type="password" onblur="cNewPwdCheck()" name="cNewPwd"></td>
   						</tr>
   					</table>
   					<div class="clear"><input class="wf_submitBtn" type="submit" value="提交"></div>
   					<div class="wf_errorDiv"></div>
   				</form>
   			</div>
   			<div class="wf_borderBox">
   				<a href="order.do?userId=${userId }" title="订单列表">
	   				<div class="wf_borderDiv">
	   					<img style="width:30px;height30px;" src="image/order.png">
	   				</div>
   				</a>
   				<a href="showCart.do?userId=${userId }" title="购物车">
   					<div class="wf_borderDiv">
   						<img style="width:30px;height30px;" src="image/shoppingCart.png">
   					</div>
   				</a>
   				<a href="showCollect.do?userId=${userId }" title="收藏列表">
   					<div class="wf_borderDiv">
   						<img style="width:30px;height30px;" src="image/collect.png">
   					</div>
   				</a>
   			</div>
   		</div>
   	</div>
   	<%@include file="include/footer.jsp" %>
  </body>
  <script>
  	var rule = /^(.*[A-Za-z].*\d.*)|(.*\d.*[A-Za-z].*)$/;
  	$(".wf_leftTitle").click(function(){
		$(this).css("color","#482618");
		$(".wf_rightTitle").css("color","lightgrey");
		$(".wf_infoTab").css("display","block");
		$(".wf_modifyInfo").css("display","none");
  	})
  	$(".wf_rightTitle").click(function(){
		$(this).css("color","#482618");
		$(".wf_leftTitle").css("color","lightgrey");
		if($(".wf_modifyInfo").css("display")=="none"){
			$(".wf_modifyInfo").css("display","block");
			$(".wf_infoTab").css("display","none");
			$(".wf_errorDiv").html("");
			$(".wf_modifyPwd").val("");
		}
  	})
  	//原密码
  	function oldPwdCheck(){
  		if($(".wf_modifyPwd:eq(0)").val()==""){
  			$(".wf_errorDiv").html("请填写原密码！");
  			return false;
  		}
  		else{
  			if($(".wf_modifyPwd:eq(0)").val()!="${pwd }"){
  				$(".wf_errorDiv").html("原密码错误！");
  				$(".wf_modifyPwd:eq(0)").val("");
  				return false;
  			}
  			else{
  				$(".wf_errorDiv").html("");
  				return true;
  			}
  		}
  		return true;
  	}
  	//新密码
  	function newPwdCheck(){
  		if($(".wf_modifyPwd:eq(1)").val()==""){
  			$(".wf_errorDiv").html("密码由数字字母组成，且不低于4位！");
  			return false;
  		}
  		else{
  			if($(".wf_modifyPwd:eq(0)").val()==""){
  				$(".wf_errorDiv").html("请先填写原密码！");
  				$(".wf_modifyPwd:eq(1)").val("");
  				return false;
  			}
  			else if(!rule.test($(".wf_modifyPwd:eq(1)").val())){
  				$(".wf_errorDiv").html("密码由数字字母组成，且不低于4位！");
  				$(".wf_modifyPwd:eq(1)").val("");
  				return false;
  			}
  			else{
  				if($(".wf_modifyPwd:eq(0)").val()==$(".wf_modifyPwd:eq(1)").val()){
  					$(".wf_errorDiv").html("修改的密码与原密码相同！");
  					$(".wf_modifyPwd:eq(1)").val("");
  					return false;
  				}
  				else{
  					$(".wf_errorDiv").html("");
  					return true;
  				}
  			}
  		}
  		return true;
  	}
  	
  	function validate(){
  		if(!oldPwdCheck()||!newPwdCheck()){
  			return false;
  		}
  	}
  </script>
</html>
