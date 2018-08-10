<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'orderManage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/orderManage.css">
	<script type="text/javascript" src="js/jquery-1.8.0.js"></script>
  </head>
  <style>
  	.wwxdiv1{
		width: 600px;
		height: 80px;
		font-size: 25px;
		text-align: center;
		line-height: 80px;
		margin: auto;
		background-color: #EDB2B3;
		box-shadow: 3px 3px 5px grey;
	}
  </style>
  <body>
  <%@ include file="include/header.jsp"%>
<div class="wwx_div" align="center" id="contain">	
  <div class="wwx_div2">
	  <div class="wwx_div5 wwxdiv1">订单管理</div>
	  <form action="ordersearch.do" method="post" onSubmit="return validate1()">
	    <table border="0" id="tab" style="margin-left:0px;">
	    	<tr>
	    		<td colspan="3"><input type="button" id="btn1" value="返回首页" style="position: relative;left: 500px;top: 20px;"/></td>
	    	</tr>
	    	<tr>
	    		<td>根据用户:</td>
	    		<td>
	    			<input type="text" name="ordername" id="txt" />
	  				<input type="submit" value="搜索"/>
	  				<input type="button" id="btn" value="返回"/>
	    		</td>
	    		<td><div class="wwxtd8" id="ordererror" ></div></td>
	    	</tr>	
	    </table>
	  </form>
	    	<table id="wwxta1" border="1">
		    	<tr>
		    		<td >订单编号 </td>
		    		<td >订单状态</td>
		    		<td>用户名</td>
		    		<td>地址</td>
		    		<td style="width: 120px">下单时间</td>
		    		<td>总价</td>
		    	</tr>
		    	<c:forEach items="${orderlist}" var="order" varStatus="status">
		    		<tr>
		    			<td>${order.orderId}</td>
		    			<td>
							<c:if test="${0==order.orderFlag}">
		    					<span>待支付</span>
		    				</c:if>
		    				<c:if test="${-1==order.orderFlag}">
		    					<span>支付失败</span>
		    				</c:if>
		    				<c:if test="${1==order.orderFlag}">
		    					<span>支付成功</span>
		    				</c:if>
						</td>
						<td width="80px">${order.userName}</td>				
						<c:forEach items="${addreslist}" var="addre" varStatus="addrestatu">
							<c:if test="${status.index==addrestatu.index}">
								<td class="wwxtd1" style="line-height: 15px">${addre.addressDetail}</td>
							</c:if>
						</c:forEach>
						<td width="120px"><fmt:formatDate value="${order.orderTime}" pattern="yyyy-MM-dd"/></td>
						<td>${order.totalPrice}</td>
		    		</tr>
		    		
		    	</c:forEach>
		    	<tr id="wwxtr1">
		    		<td colspan="1"><div class="wwxtd6">总页数：${totalpage}</div></td>
		    		<td colspan="1" class="wwxtd2">
				<c:forEach begin="${min}" end="${max}" step="1" var="i">
					<c:if test="${i==nowpage}">
						<a href="ordersearch.do?page=${i}" class="wwxtd4">&nbsp;${i}&nbsp;</a>
					</c:if>
					<c:if test="${i!=nowpage}">
						<a href="ordersearch.do?page=${i}" class="wwxtd5">&nbsp;${i}&nbsp;</a>
					</c:if>
				</c:forEach>
				</td>
				<td></td>
				<td colspan="3"><div class="wwxtd3">
				<form action="ordersearch.do" method="post" onSubmit="return validate()">
					<span class="wwxtd6">跳转至：</span><input type="text" style="width: 40px;color: #746F6F" name="page" id="page" onblur="page1()"/>
							<input type="submit" value="确定"/><span id="pageerror" class="wwxtd7"></span>
				</form>
				</div>
				</td>
		 		</tr>
	  		</table>
	  		<div style="height: 25px;width: 100%"></div>
</div>
  			
 </div>
 <%@ include file="include/footer.jsp"%>
  </body>
  <script type="text/javascript">
  	//为返回按钮绑定事件
  	var btn=document.getElementById("btn");
  	btn.onclick=function(){
  	window.location.href="ordersearch.do";
  	}
  	//为返回首页绑定事件
  	var btn1=document.getElementById("btn1");
  	btn1.onclick=function(){
  	window.location.href="backgroundManager.do";
  	}
  	//提交表单
  	 function validate(){
		if(!page1()){
			return false;
		}
	}
	//跳转框数值校验
  	function page1(){	
		var page5=$("#page").val();			
		$("#pageerror").html("");
		if(page5<=0||page5>"${totalpage}"){
			$("#pageerror").html("输入的页数无效");
			return false;
		}
		return true;
	}
	//提交表单
	function validate1(){
	var ordererror = $("#ordererror").val();
		if(ordererror!=""){
			return false;
		}
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
						$("#ordererror").html("");
					}
					else{
						$("#ordererror").html(data);
						$("#txt").val("");
					}
				}
			})
  		}
  	})
  </script>
</html>
