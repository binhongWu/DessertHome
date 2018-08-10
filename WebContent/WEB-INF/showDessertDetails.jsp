<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="include/tag.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'showDessertDetails.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/showDessertDetails.css">
	<script type="text/javascript" src="js/jquery-1.8.0.js"></script>
  </head>
  
  <body><!--
  	 <%@ include file="include/header.jsp"%>
    --><div class="lcy_container">
		<!--甜点大图显示-->
		<div class="lcy_dessert_Img"><img src="DessertData/${dessert.dessertImg_B }" alt="${dessert.dessertName }" width="1200px" height="525px"></div>
		<!--甜点详细信息的容器-->
		<div class="lcy_dessert_Info_container clear">
			<!--甜点左侧详细信息-->
			<div class="lcy_left_Info_container">
				<div class="lcy_dessert_Title_Box">
					<span class="lcy_dessert_Name">${dessert.dessertName }</span><br><br><br>
					<div class="lcy_dessert_Detail"><span id="lcy_dessert_detail">${dessert.dessertInfo }</span></div>
				</div>
				<!--关于甜点保存方式和送达时间的提示信息-->
				<div class="lcy_dessert_Tips_Box">
					<div class="lcy_shopping_Tips clear">
						<div class="lcy_dispatching_img"></div>
						<div class="lcy_dispatching_text">&nbsp;&nbsp;&nbsp;&nbsp;配送时间：&nbsp;&nbsp;现在订购最早明天 10:00配送。每日最晚配送时间18:30</div>
					</div>
					<div class="lcy_shopping_Tips clear">
						<div class="lcy_keepfresh_img"></div>
						<div class="lcy_keepfresh_text">&nbsp;&nbsp;&nbsp;&nbsp;保鲜条件：&nbsp;&nbsp;${dessert.keepTip }</div>
					</div>
				</div>
			</div>
			<!--甜点右侧详细信息-->
			<div class="lcy_right_Info_container">
				<!--收藏按钮-->
				<!-- 用户未登录时的按钮显示 -->
				<div>
				<c:if test="${loginUser==null}">
				<div class="lcy_collection_btn lcy_flagIs_null"><img src="image/0_collect.png" width="50px" height="50px"></div>
				</c:if>
				<!-- 收藏登录层，平时隐藏 -->
				<div class="lcy_c_hidden_login_tips">
					<div class="lcy_c_hidden_login_text">收藏前请先登录！<a href="loginPrepare.do">去登陆>>></a></div>
				</div>
				<!-- 用户登录时显示的按钮 -->
				<c:if test="${loginUser!=null}">
					<div class="lcy_collection_btn lcy_flag"><img src="image/${collectionFlag }_collect.png" width="50px" height="50px"></div>
				</c:if>
				
				<!-- 收藏提示层，平时隐藏 -->
				<div>
				</div>
				</div>
				<!-----------------------分割线---------------------->
				<div class="lcy_goodstips_box clear">
					<!--关于甜点的详细信息-->
					<div class="lcy_goodstips_1 clear">
						<div class="lcy_goodstips_img lcy_goodstips_img1"></div>
						<div class="lcy_goodstips_text">18x18cm</div>
					</div>
					<div class="lcy_goodstips_2 clear">
						<div class="lcy_goodstips_img lcy_goodstips_img2"></div>
						<div class="lcy_goodstips_text">适合7-8人分享</div>
					</div>
					<div class="lcy_goodstips_3 clear">
						<div class="lcy_goodstips_img lcy_goodstips_img3"></div>
						<div class="lcy_goodstips_text">含10套餐具</div>
					</div>
					<div class="lcy_goodstips_4 clear">
						 <div class="lcy_goodstips_img lcy_goodstips_img4"></div>
						<div class="lcy_goodstips_text">需提前8小时预订</div>
					</div>
				</div>
				<div class="lcy_goods_info">商品规格:<span id="lcy_dessert_weight">${dessert.weight }</span>g</div>
				<div class="lcy_goods_info">保质期:<span id="lcy_dessert_keepTime">${dessert.keepTime }小时</span></div>
				<div class="lcy_goods_info">价格:￥<span id="lcy_dessert_price">${dessert.dessertPrice }0</span>/份</div>
				<!--立即购买和添加购物车的按钮层,先对用户是否登录进行判断，采用两套方式-->
				<c:if test="${sessionScope.loginUser!=null}">
				<div class="lcy_buy_btn_box clear">
					<div class="lcy_btn_style lcy_btn_buyNow">立即购买</div>
					<div>
						<div class="lcy_btn_style lcy_btn_addCart">
						加入购物车
						</div>
						<!--添加购物车成功的提示层，平时隐藏-->
						<div class="lcy_addSuccess_hidden">
							<div class="lcy_hidden_top clear">
								<div class="lcy_hidden_top_icon"></div>
								<div class="lcy_hidden_top_text"></div>
							</div>
							<div class="lcy_look_cart">查看购物车</div>
						</div>
					</div>
				</div>
				</c:if>
				<c:if test="${sessionScope.loginUser==null}">
					<div class="lcy_buy_btn_box clear">
					<div class="lcy_btn_style lcy_btn_buyNow1">立即购买</div>
					<div class="lcy_btn_style lcy_btn_addCart1">
					加入购物车
					</div>
					<!--提醒登录的提示层，平时隐藏-->
					<div class="lcy_addSuccess_hidden">
						<div class="lcy_loginTips">您尚未登录，是否现在登录？</div>
						<span class="lcy_loginNow">登录</span><span class="lcy_cancle">取消</span>
					</div>
				</div>
				</c:if>
			</div>
		</div>
	</div>
	 <!--<%@ include file="include/footer.jsp"%>
  --></body>
  <script>
		//未登录状态下按钮触发的提示信息
		$(".lcy_btn_buyNow1").click(function(){
			$(".lcy_addSuccess_hidden").fadeIn(1000);
			$(".lcy_addSuccess_hidden").css("left","-130px");
		})
		$(".lcy_btn_addCart1").click(function(){
			$(".lcy_addSuccess_hidden").fadeIn(1000);
			$(".lcy_addSuccess_hidden").css("left","130px");
		})
		$(".lcy_cancle").click(function(){
			$(".lcy_addSuccess_hidden").css("display","none");
		})
		$(".lcy_loginNow").click(function(){
			window.location.href="loginPrepare.do";
		})
		$(".lcy_flagIs_null").click(function(){
			$(".lcy_c_hidden_login_text").fadeIn(1000);
			setTimeout(function(){$(".lcy_c_hidden_login_text").fadeOut();},5000);
		})
		//登录状态下的按钮提示
		//点击添加购物车，隐藏层显示2s后自动关闭
		$(".lcy_btn_addCart").click(function(){
			$.ajax({
				type:"post",
				url:"addCartRecordAjax.do",
				data:{"dessertId":"${dessert.dessertId}"},
				success:function(data){
					$(".lcy_hidden_top_text").html(data);
				}
			})
			$(".lcy_addSuccess_hidden").css("display","block");
			$(".lcy_addSuccess_hidden").css("left","130px");
			setTimeout(function(){$(".lcy_addSuccess_hidden").hide();},5000);
		})
		$(".lcy_btn_buyNow").click(function(){
			$.ajax({
				type:"post",
				url:"addCartRecordAjax.do",
				data:{"dessertId":"${dessert.dessertId}"},
				success:function(data){
				}
			})
			window.location.href="showCart.do";
		})
		$(".lcy_look_cart").click(function(){
			window.location.href="showCart.do";
		})
		$(".lcy_flag").click(function(){
			$.ajax({
				type:"post",
				url:"addCollectionAjax.do",
				data:{"dessertId":"${dessert.dessertId}","collectionFlag":"0"},
				success:function(data){
					$(".lcy_flag").html("<img src='image/${collectionFlag }_collect.png' width='50px' height='50px'>");
					$(".lcy_c_hidden_login_text").html(data);
					$(".lcy_c_hidden_login_text").fadeIn(1000);
					setTimeout(function(){$(".lcy_c_hidden_login_text").fadeOut();},5000);
					
				}
			})
		})
	</script>
</html>
