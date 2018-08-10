 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="include/tag.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'comfirmedOrder.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  	<link rel="stylesheet" type="text/css" href="css/backgroundManager.css">
<style>
	*{
			padding:0;margin:0;border:none;text-decoration:none;text-align: center;
			
		}
		.clear:after{
			content:"";
			display: block;
			clear: both;
			
		}
		.lcy_container{
			width: 1200px;
			margin: auto;
			margin-bottom:50px;
		}
		.lcy_title_container{
			width: 1200px;
			height: 35px;
			border: #E1E1E1 solid 1px;
			color:#684029;
			line-height: 35px;
			background-color: #FAFAFA;
		}
		.lcy_goodsName{
			float: left;
			width: 350px;
		}
		.lcy_goodsPrice{
			float: left;
			width: 200px;
		}
		.lcy_goodsNum{
			float: left;
			width: 200px;
		}
		.lcy_TotalPriceOfOneGoods{
			float: left;
			width: 200px;
		}
	.wbh_goodsInfoDetial{
		width:1200px;
		margin-top: 30px;
	}
	.wbh_goodsInfoDetial div{
		float:left;
	}
	.wbh_title{
		width:1200px;
		margin-top: 100px;
		margin-bottom: 50px;
		height: 80px;
		text-align: center;
		line-height: 80px;
		font-size: 25px;
		color:#684029;
	}
	.wbh_foot{
	width:1200px;
	margin-top:20px;
	margin-bottom: 50px;
	}
	.wbh_foot div:first-child{
		margin-left: 30px;
		
	}
	.wbh_foot div{
		float:left;
		margin-left: 100px;
		margin-top: 20px;
		text-align:center;
		line-height: 30px;
	}
	.wbh_addressSelect{
		width:350px;
		height: 30px;
		background-color:#D9D9D9;
		text-align:center;
	}
	.wbh_comfirmed{
		width:100px;
		height: 30px;
	}
	
	.wbh_comfirmed input{
		width:100px;
		height: 30px;
		margin-bottom:50px;
		outline: none;
	}
	.wbh_comfirmed input:active{
		box-shadow: 2px 2px 5px grey inset;
	}
	
	.wbh_insertAddress{
		width:1200px;
		height:50px;
		display: none;
	}
	.wbh_insertAddress select{
		width:100px;
		height:25px;
		margin-left:50px;
		margin-top:10px;
	}
	.wbh_insertAddress input{
		width:300px;
		height:25px;
		margin-left:50px;
		margin-top:10px;
	}
	/*隐藏层  结算成功*/
	.wbh_hiddenContainer{
		width: 1200px;
		height: 100px;;
		position: absolute;
		top:800px;
		left:0px;
		background-color:azure;
		z-index: 1000;
		display: none;
		
	}
	.wbh_titleOK{
		width:1200px;;
		text-align: center;
		line-height: 300px;
		font-size: 30px;
		color:red;
	}
	.wbh_mune{
		width: 400px;
		height: 100px;
		margin: auto;
		
	}
	.wbh_mune div{
		width: 100px;
		height: 100px;
		margin-right: 50px;
		float:left;
	}
	  .lcy_sorry_hidden{
			width:100%;
			height:100%;
			position: absolute;
			top:0px;
			left:0px;
			background-color: rgba(0,0,0,0.7);
			z-index: 6;
			display: none;
		}
		.wbh_titleOK  img{
			width: 200px;
			height: 200px;
			margin:auto;
			margin-top:100px;
		}
</style>
  <body>
   <%@ include file="include/header.jsp" %>
  
<!--购物车页面容器-->
	<div class="lcy_container">
		<div class="wbh_title">确认订单</div>
		<div class="lcy_title_container">
			<div style="width: 100px;float: left;height: 35px;"></div>
			<div class="lcy_goodsName">商品名</div>
			<div class="lcy_goodsPrice">单价</div>
			<div class="lcy_goodsNum">数量</div>
			<div class="lcy_TotalPriceOfOneGoods">小计</div>
		</div>
		<!--添加商品详细内容-->
		<c:forEach items="${userCartInfoList}" var="userCartInfo">
			<div class="wbh_goodsInfoDetial clear">
					<div style="width: 100px;float: left;height: 35px;"></div>
					<!--名称-->
					<div class="lcy_goodsName">${userCartInfo.dessert.dessertName }</div>
					<!--单价-->
					<div class="lcy_goodsPrice">￥${userCartInfo.dessert.dessertPrice }0</div>
					<!--数量-->
					<div class="lcy_goodsNum">${userCartInfo.dessertNum }</div>
					<!--小计-->
					<div class="lcy_TotalPriceOfOneGoods">¥ ${userCartInfo.totalPrice }0</div>
					
			</div>
		</c:forEach>
		<div>
			<div class="wbh_foot clear">
				<div>收件人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${user.userName }</div>
				<!--选择地址-->
				<div class="wbh_chooseAddress">
					<select name=""  id="selAddress" class="wbh_addressSelect">
						<option name="chooseAddress" value="choose" style="text-align:center">--请选择地址--</option>
						<option name="newAddress" value="add">--新增地址--</option>
						<c:forEach items="${addressInfo }" var="loginUserAddress">
							<option value="${loginUserAddress.addressDetail }">${loginUserAddress.addressDetail }</option>
						</c:forEach>
					</select>
					<span id="addressOK" style="width:50px;height:30px;line-height:30px;color:red"></span>
				</div>
				<!-- 合计 -->
				<div>合计：¥<span id="span"> ${goodsTotalPrice }0</span></div>
				<!--确认结算-->
				<div class="wbh_comfirmed"><input type="button" value="确认结算" id="payBtn"></div>
				<a href="showCart.do" style="width:100px;height:30px;line-height:30px"><div>返回购物车</div></a>
			</div>
		</div>
			<!-- 添加地址  隐藏域 -->
			<div class="wbh_insertAddress" id="insertAddress">
				<select id="selProvince">
					<option>请选择省份</option>
					<c:forEach items="${proList}" var="pro">
						<option value="${pro.provinceId }" id="pro">${pro.provinceName }</option>
					</c:forEach>
				</select>
				<span id="addAddr1" style="width:50px;height:30px;line-height:30px;color:red"></span>
				<select id="selCity" class="wbh_ajaxCity">
					<option>请选择城市</option>
				</select>
				<span id="addAddr2" style="width:50px;height:30px;line-height:30px;color:red"></span>
				<select id="selArea" class="wbh_ajaxArea">
					<option>请选择区</option>
					<option></option>
				</select>
				<input type="text" placeholder="请填写详细地址" id="addressTxt">
				<span id="addAddr3" style="width:50px;height:30px;line-height:30px;color:red"></span>
				<input type="submit" value="确定" style="width:40px; height:20px" id="btn">
			</div>
			<div class="lcy_sorry_hidden">
			<!-- <div class="lcy_sorry_tips"> -->
			<div class="wbh_titleOK">
				<div><img src="indexImage/ZFB.jpg"></div>
				<div style="width:200px;height:50px;color:red;font-size: 20px;">${goodsTotalPrice }0</div>
				<div><a href="index.jsp" id="backIndex"style="color:red;font-size: 17px;">返回首页</a></div>
			</div>
	</div>
	<%-- <div class="wbh_insertAddress" id="payHidden" style="width:100%;height:100%;background-color: #AE5638">
		<div class="wbh_titleOK">支付成功！</div>
		<div class="wbh_mune clear">
		<div><a href="index.jsp" id="backIndex">返回首页</a></div>
		<div><a href="order.do?userId=${user.userId }" id="viewOrder">查看订单</a></div>
	</div> --%>
	 
	 <%@ include file="include/footer.jsp" %>
  </body>
  <script type="text/javascript" src="js/jquery-1.8.0.js"></script>
  <script>
 	 $($("#payBtn")).click(function(){
		$(".lcy_sorry_hidden").slideDown(200);
		setTimeout(function(){$(".lcy_sorry_hidden").slideUp(200000000000);},200000000);
	})
	/*获取新增地址填写区域*/
	//获取选择地址的请求，显示出新增地址层
	var selAddress=document.getElementById("selAddress");
	$("#selAddress").change(function(){
		if(selAddress.selectedIndex==1){
			$("#insertAddress").css("display","block");
		}else{
			$("#insertAddress").css("display","none");
		}
	})
	//获取城市对象
	var $selCity=$("#selCity");
	//获取地区对象
	var $selArea=$("#selArea");
	//选择城市
	$("#selProvince").change(function(){
		$("#addAddr1").html("");
		var $proId=$(this).val();
		$.ajax({
			type:"post",
			url:"comfirmedOrderAjax.do",
			data:"provinceId="+$proId,
			dataType:"json",
			success:function(data){
				$selCity.html("<option value='0'>请选择城市</option>");
				$selArea.html("<option value='0'>请选择地区</option>");
				for(var i=0;i<data.length;i++){
					console.log(data[i].cityId);
					$selCity.append("<option value="+data[i].cityId+">"+data[i].cityName+"</option>");
				}
			}
		})
	})
	//选择地区
	$("#selCity").change(function(){
		$("#addAddr2").html("");
		var $cityId=$(this).val();
		$.ajax({
			type:"post",
			url:"comfirmedOrderAreaAjax.do",
			data:"cityId="+$cityId,
			dataType:"json",
			success:function(data){
				$selArea.html("<option value='0'>请选择地区</option>");
				for(var i=0;i<data.length;i++){
					console.log(data[i].areaId);
					$selArea.append("<option value="+data[i].areaId+">"+data[i].areaName+"</option>");
				}
			}
		})
	})
	$("#selArea").change(function(){
		$("#addAddr3").html("");
	})
	
	//添加地址确认按钮事件
	$("#btn").click(function(){
		
		var $province=$("#selProvince option:selected").text();
		var $city=$("#selCity option:selected").text();
		var $area=$("#selArea option:selected").text();
		var $addressTxt=$("#addressTxt").val();
		if($province=="请选择省份"){
			$("#addAddr1").html("省份不能为空！");
		}
		else if($city=="请选择城市"){
			$("#addAddr2").html("城市不能为空！");
		}
		else if($area=="请选择地区"){
			$("#addAddr3").html("地区不能为空！");
		}
		else if($addressTxt==null){
			$("#addAddr3").html("请写入详细地址！");
		}else{
			$.ajax({
				type:"post",
				url:"insertAddress.do",
				data:"province="+$province+"&city="+$city+"&area="+$area+"&addressTxt="+$addressTxt,
				success:function(content){
					console.log(content);
					//加载新地址
					$("#selAddress").append("<option>"+content+"</option>");
					$("#insertAddress").css("display","none");
					
				}
			})
		}
		
	})
	$("#selAddress").change(function(){
		$("#addressOK").html("");
	})
	//确认结算按钮事件
	$("#payBtn").click(function(){
		//总价格
		var $totalPrice=$("#span").html();
		//订单状态  1支付成功
		var payFlag=1;
		//获取地址
		var $userAddress=$("#selAddress option:selected").text();
		var $userAddressValue=$("#selAddress option:selected").val();
		if($userAddressValue=="choose"||$userAddressValue=="add"){
			$("#addressOK").html("地址不能为空！");
		}else{
			//显示隐藏层
			$("#payHidden").css("display","block");
			$(".wbh_hiddenContainer").slideDown(200);
			//ajax 添加订单
			$.ajax({
				type:"post",
				url:"addOrder.do",
				data:"payFlag="+payFlag+"&userAddress="+$userAddress+"&totalPrice="+$totalPrice,
				success:function(contents){
					$("#payBtn").attr("disabled",true);
				}
			})
		}
	})
	
</script>
</html>
