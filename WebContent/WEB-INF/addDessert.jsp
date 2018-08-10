<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <link rel="stylesheet" type="text/css" href="css/addDessert.css">
  <script type="text/javascript" src="js/jquery-1.8.0.js"></script>
  <style>
	#tab td{
		text-align: left;
	}
	#tab td:first-child{
		text-align: right;
	}
	#tab td:nth-child(2){
		width:45px;
	}
</style>
  <body>
  <%@ include file="include/header.jsp"%>
<div class="container">
	<div class="wwxdiv1">甜品发布</div>
	<form method="post" action="addDessert.do" enctype="multipart/form-data" id="wwxdessertform1" onSubmit="return validate()">
		<table border="0" id="tab">
			<tr>  
				<td>甜品名称:</td>
				<td></td>
				<td><input type="text" name="dessertName" id="dessertName" onBlur="dessertName1()"> <span id="dessertNameerror" class="wwxspan1"></span></td>
			</tr>
			<tr>
				<td>甜品价格:</td>
				<td></td>
				<td><input type="text" name="dessertPrice" id="dessertPrice" onBlur="dessertPrice1()"> <span id="dessertPriceerror" class="wwxspan1"></span></td>
			</tr>
			<tr>
				<td>净含量:</td>
				<td></td>
				<td><input type="text" name="weight" id="weight" onBlur="weight1()"> <span id="weighterror" class="wwxspan1"></span></td>
			</tr>
			<tr>
				<td>保质小提示:</td>
				<td></td>
				<td><input type="text" name="keepTip" id="keepTip" onBlur="keepTip1()"> <span id="keepTiperror" class="wwxspan1"></span></td>
			</tr>
			<tr>
				<td>保鲜时间:</td>
				<td></td>
				<td><input type="text" name="keepTime" id="keepTime" onBlur="keepTime1()"> <span id="keepTimeerror" class="wwxspan1"></span></td>
			</tr>
			<tr>
				<td>甜品发布时间:</td>
				<td></td>
				<td>
					<select id="year" name="year">
						<option value="">--年份--</option>
					</select>
					<select  id="month" name="month">
						<option value="">--月份--</option>
					</select>
					<select id="day" name="day">
						<option value="">--天--</option>
					</select>
					<span id="yearerror" class="wwxspan1"></span>
 				</td>
			</tr>
			<tr>
				<td>甜品图片（小）:</td>
				<td></td>
				<td><input type="file" name="file1"  id="dessertImg_S"> <span id="dessertImg_Serror" class="wwxspan1"></span></td>
			</tr>
			<tr>
				<td>甜品图片（大）:</td>
				<td></td>
				<td><input type="file" name="file2" id="dessertImg_B"> <span id="dessertImg_Berror" class="wwxspan1"></span></td>
			</tr>
			<tr>
				<td>甜品类型:</td>
				<td></td>
				<td>
					
					 <select id="kind" name="kind" onblur="kind1()">
						<option value="">--甜品类型--</option>
						<c:forEach items="${kindlist}" var="kind">
							<option value="${kind.kindName}">${kind.kindName}</option>
						</c:forEach>
					</select>
					<span id="kinderror" class="wwxspan1"></span>
				</td>
			</tr>
			<tr>
				<td>甜品故事:</td>
				<td></td>
				<td><textarea class="wwxdesserContent" name="dessertinfo" id="dessertinfo" cols="50" rows="12" placeholder="甜品故事" onBlur="dessertinfo1()"></textarea><span id="dessertinfoerror" class="wwxspan1"></span></td>
			</tr>
			<tr>
				<td colspan="1"><input type="submit" value="发布" class="wwxbtn"></td>
				<td></td>
				<td colspan="1"><input type="button" id="btn1" value="返回首页"  /></td>
			</tr>
		</table>
	</form>
</div>
<%@ include file="include/footer.jsp"%>
</body>
<script>
	//获取下拉列表框
	var year = document.getElementById("year");
	var month = document.getElementById("month");
	var day = document.getElementById("day");
	var kind = document.getElementById("kind");
	var taste = document.getElementById("taste");
	
	
	//初始化年月下拉列表的选项
	function initData(){
		for(var i = 2017; i <= 2022; i++){
			year.add(new Option(i,i));
		}
		for(var i = 1; i <= 12; i++){
			month.add(new Option(i,i));
		}
	}
	window.onload = initData;
	
	//级联显示日期
	function showDay(){
		//判断是否是有效的年月值
		if(year.selectedIndex > 0 && month.selectedIndex > 0){
			//清空日期
			day.length = 1;
			//获取选择的年份和月份
			var y = parseInt(year.value);
			var m = parseInt(month.value);
			var d;
			//判断月份
			switch(m){
				case 2:
					//判断年
					if(y%4 == 0 && y%100 != 0 || y%400 == 0)
						d = 29;
					else
						d = 28;
					break;
				case 4:
				case 6:
				case 9:
				case 11:
					d = 30;
					break;
				default:
					d = 31;	
			}
			//添加天数
			for(var i = 1; i <= d; i++){
				day.add(new Option(i,i));
			}
		}
	}
	//绑定年月下拉列表的选项事件
	year.onchange = showDay;
	month.onchange = showDay;
	//为返回首页按钮绑定点击事件
	var btn1=document.getElementById("btn1");
  	btn1.onclick=function(){
  	window.location.href="backgroundManager.do";
  	}
  	//提交表单
	function validate(){
		if(!dessertName1()||!dessertPrice1()||!publishDate1()||!weight1()||!keepTip1()||!ysar1()||!keepTime1()||!dessertImg_S1()||!dessertImg_B1()||!kind1()||!dessertinfo1()){
			return false;
		}
	}
	//甜品发布时间校验
	function ysar1(){
		var year1=$("#year").val();
		var month1=$("#month").val();
		var day1=$("#day").val();
		$("#yearerror").html("");
		if(year1==""||month1==""||day1==""){
		$("#yearerror").html("请输入正确的日期");
		return false;
		}
		return true;
	}
	//甜品名称校验
	function dessertName1(){
		var dessertName2=$("#dessertName").val();	
		$("#dessertNameerror").html("");
		if(dessertName2==""){
			$("#dessertNameerror").html("甜品名称不能为空");
			return false;
		}
		return true;
	}
	//甜品类型校验
	function kind1(){
		var kind2=$("#kind").val();	
		$("#kinderror").html("");
		if(kind2==""){
			$("#kinderror").html("甜品类型不能为空");
			return false;
		}
		return true;
	}
	//甜品图片校验
	function dessertImg_S1(){
		var dessertImg_Srule=/^(jpg|jpeg|png|gif|JPG|JPEG|PNG|GIF)$/;
		var dessertImg_S2=$("#dessertImg_S").val();	
		$("#dessertImg_Serror").html("");
		if(dessertImg_S2==""){
			$("#dessertImg_Serror").html("甜品图片不能为空");
			return false;
		}
		else if(!dessertImg_Srule.test(dessertImg_S2.substring(dessertImg_S2.indexOf(".")+1))){
			$("#dessertImg_Serror").html("请选择图片上传");
			return false;
		}
		return true;
	}
	//甜品大图片校验
	function dessertImg_B1(){
		var dessertImg_Brule=/^(jpg|jpeg|png|gif|JPG|JPEG|PNG|GIF)$/;
		var dessertImg_B2=$("#dessertImg_B").val();	
		$("#dessertImg_Berror").html("");
		if(dessertImg_B2==""){
			$("#dessertImg_Berror").html("甜品大图片不能为空");
			
			return false;
		}
		else if(!dessertImg_Brule.test(dessertImg_B2.substring(dessertImg_B2.indexOf(".")+1))){
			$("#dessertImg_Berror").html("请选择图片上传");
			return false;
		}
		return true;
	}
	//保鲜时间校验
	function keepTime1(){
		var keepTimerule = /^[1-9]\d*$/;
		var keepTime2=$("#keepTime").val();	
		$("#keepTimeerror").html("");
		if(keepTime2==""){
			$("#keepTimeerror").html("保鲜时间不能为空");
			return false;
		}
		else if(!keepTimerule.test(keepTime2)){
			$("#keepTimeerror").html("请输入有效的整数");
			return false;
		}
		return true;
	}
	//净含量校验
	function weight1(){
		var weightrule = /^[1-9]\d*$/;
		var weight2=$("#weight").val();	
		$("#weighterror").html("");
		if(weight2==""){
			$("#weighterror").html("净含量不能为空");
			return false;
		}
		else if(!weightrule.test(weight2)){
			$("#weighterror").html("请输入有效的整数");
			return false;
		}
		return true;
	}
	//保质小提示校验
	function keepTip1(){
		var keepTip2=$("#keepTip").val();	
		$("#keepTiperror").html("");
		if(keepTip2==""){
			$("#keepTiperror").html("保质小提示不能为空");
			return false;
		}
		return true;
	}
	//甜品价格校验
	function dessertPrice1(){
		var dessertPricerule = /^(0|([1-9]\d*))(\.\d?[1-9])?$/;
		var dessertPrice2=$("#dessertPrice").val();	
		$("#dessertPriceerror").html("");
		if(dessertPrice2==""){
			$("#dessertPriceerror").html("甜品价格不能为空");
			return false;
		}
		else if(!dessertPricerule.test(dessertPrice2)){
			$("#dessertPriceerror").html("请输入有效的数值");
			return false;
		}
		return true;
	}
	//甜品发布时间校验
	function publishDate1(){
		
		var publishDate2=$("#publishDate").val();	
		$("#publishDateerror").html("");
		if(publishDate2==""){
			$("#publishDateerror").html("甜品发布时间不能为空");
			return false;
		}
		return true;
	}
	//甜品故事校验
	function dessertinfo1(){
		var dessertinfo2=$("#dessertinfo").val();	
		$("#dessertinfoerror").html("");
		if(dessertinfo2==""){
			$("#dessertinfoerror").html("甜品故事不能为空");
			return false;
		}
		return true;
	}
</script>
</html>
