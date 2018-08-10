c<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="include/tag.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'cart.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/cart.css">
	<script type="text/javascript" src="js/jquery-1.8.0.js"></script>

  </head>
  
  <body>
 	 <%@ include file="include/header.jsp"%>
    <!--购物车页面容器-->
    
	<div class="lcy_container">
	<c:if test="${listLength>0}">
		<div class="lcy_title_container">
			<div style="width: 200px;float: left;height: 35px;"></div>
			<div class="lcy_goodsName">商品</div>
			<div class="lcy_goodsPrice">单价</div>
			<div class="lcy_goodsNum">数量</div>
			<div class="lcy_TotalPriceOfOneGoods">金额</div>
		</div>
		<div style="margin-bottom:20px">
				<div class="lcy_goodsList_container">
				<c:forEach items="${userCartInfoList}" var="userCartInfo">
					<div class="lcy_goodsInfo_block clear">
						<div class="lcy_goodsInfo_container clear">
							<div class="lcy_goodsInfo_details clear">
							<!--遍历购物信息封装数据-->
								<div class="lcy_goodsInfoDeatils_Img" id=${userCartInfo.dessert.dessertId }>
								<img src="DessertData/${userCartInfo.dessert.dessertImg_S }" alt="${userCartInfo.dessert.dessertName }" width="80px" height="80px">
								</div>
								<div class="lcy_goodsInfoDeatils_Text">
									<div class="lcy_goodsInfoDeatils_Text_Title"><a href="showDessertDetails.do?dessertId=${userCartInfo.dessert.dessertId }">${userCartInfo.dessert.dessertName }</a></div>
									<div class="lcy_goodsInfoDeatils_Text_Weight">规格：${userCartInfo.dessert.weight }g</div>
									<div class="lcy_goodsInfoDeatils_Text_Tips"></div>赠送十套餐具
								</div>
							</div>
							<div class="lcy_goodsPriceInfo">￥${userCartInfo.dessert.dessertPrice }0</div>
							<div class="lcy_goodsNumInfo">
								<c:if test="${userCartInfo.dessertNum>1 }">
								<input type="button" class="lcy_reduce_btn" style="background-position:-252px -108px" /><input type="text" name="" id="" value="${userCartInfo.dessertNum }" class="lcy_showGoodsNum_text"><input type="button" class="lcy_plus_btn">
								</c:if>
								<c:if test="${userCartInfo.dessertNum==1 }">
								<input type="button" class="lcy_reduce_btn" /><input type="text" name="" value="${userCartInfo.dessertNum }" class="lcy_showGoodsNum_text"><input type="button" class="lcy_plus_btn">
								</c:if>
							</div>
							<div class="lcy_totalPriceOfOneGoodsInfo">￥${userCartInfo.totalPrice }0</div>
						</div>
						<div class="lcy_deleteGoods_btn_container">
							<div class="lcy_delectGoods_btn" id="${userCartInfo.dessert.dessertId }"></div>
						</div>
					</div>
					</c:forEach>
				</div>
				<div class="lcy_shoppingInfo_container">
					<div class="lcy_shoppingInfo_Line">
						<div class="lcy_deleteAll_container">
							<div class="lcy_deleteAll_btn_Img" id="0"></div>全部清空
						</div>
						<span class="sumMoney">商品金额:¥ ${goodsTotalPrice }0</span>
					</div>
					<div class="lcy_shoppingInfo_Line">配送费:¥ 0.00</div>
					<div class="lcy_shoppingInfo_Line">活动优惠:¥ 0.00</div>
					<div class="lcy_shoppingInfo_Line"><span class="lcy_PayMoney_Line">合计:¥ <span class="lcy_money">${goodsTotalPrice }0</span></span></div>
					<div class="lcy_shoppingInfo_Line"><span class="lcy_fullCut">订单已满￥100元,享免费配送服务</span></div>
					<div class="lcy_shoppingInfo_Line">
						<a href="comfirmedOrder.do"><div class="lcy_payMoney">去结算</div></a>
					</div>
				</div>
			  </div>
			</c:if>
			<c:if test="${listLength==0}">
				<div class="lcy_emptyCart">
					<div class="lcy_CartImgBox">
						<img src="image/cartempty.png">
					</div>
					<div class="lcy_textTips">您的购物车里还没有商品</div>
					<div class="lcy_goShopping"><a href="#">去购物>>></a></div>
				</div>
			</c:if>
		</div>
	 <%@ include file="include/footer.jsp"%>
  </body>
  <script>
  			var idArray=new Array();
  			var numArray=new Array();
  			for(var i=0;i<$(".lcy_showGoodsNum_text").length;i++){
  				numArray[i]=$($(".lcy_showGoodsNum_text")[i]).val();
  			}
  			var $moneyOfOneTypeGoods=$(".lcy_totalPriceOfOneGoodsInfo");
  			addEvent();
  			function sumMoney(){
  				var sum=0;
  				for(var i=0;i<$moneyOfOneTypeGoods.length;i++){
  					sum+=$($(".lcy_totalPriceOfOneGoodsInfo")[i]).html().substring(1)*1;
  				}
  			$(".sumMoney").html("商品金额:￥"+sum+".00");
  			$(".lcy_money").html("￥"+sum+".00")
  			}
  			
			
			//ajax页面
			function doAjax(dessertId){
				$.ajax({
					type:"post",
					url:"showCartAjax.do",
					data:"dessertId="+dessertId,
					success:function(data){
						$(".lcy_container").html(data);
						addEvent();
					}
				});
			}
			
			
			function addEvent(){
			/*减少单件商品的数量*/
			$(".lcy_reduce_btn").click(function(){
				var index=$(".lcy_reduce_btn").index(this);
				if($($(".lcy_showGoodsNum_text")[index]).val()>1){
					$($(".lcy_showGoodsNum_text")[index]).val($($(".lcy_showGoodsNum_text")[index]).val()-1);
					numArray[i]=$($(".lcy_showGoodsNum_text")[index]).val();
					var num=parseInt($($(".lcy_showGoodsNum_text")[index]).val());
					/*根据数量实时计算价格*/
					var price=$($(".lcy_goodsPriceInfo")[index]).html().substring(1);
					var totalPrice=price*num;
					$($(".lcy_totalPriceOfOneGoodsInfo")[index]).html("￥"+totalPrice+".00");
					sumMoney();
					saveCartData()
				}
				else{
					$($(".lcy_reduce_btn")[index]).css("background-position","-252px -96px");
					alert("商品数量不能小于一件！");
				}
			})
			/*增加单件商品的数量*/
			$(".lcy_plus_btn").click(function(){
				var index=$(".lcy_plus_btn").index(this);
				if($($(".lcy_showGoodsNum_text")[index]).val()<99){
					$($(".lcy_reduce_btn")[index]).css("background-position","-252px -108px");
					var num=parseInt($($(".lcy_showGoodsNum_text")[index]).val())+1;
					numArray[i]=$($(".lcy_showGoodsNum_text")[index]).val();
					$($(".lcy_showGoodsNum_text")[index]).val(num);
					/*根据数量实时计算价格*/
					var price=$($(".lcy_goodsPriceInfo")[index]).html().substring(1);
					var totalPrice=price*num;
					$($(".lcy_totalPriceOfOneGoodsInfo")[index]).html("￥"+totalPrice+".00");
					sumMoney();
					saveCartData()
				}
				else{
					alert("超过单次购买数量！");
				}
			})
			/*对用户手动输入的商品数量进行判断*/
				$(".lcy_showGoodsNum_text").blur(function(){
					var index=$(".lcy_showGoodsNum_text").index(this);
					var rule=/^(([1-9])||([1-9][0-9]))$/;
					if((!rule.test($($(".lcy_showGoodsNum_text")[index]).val()))||$($(".lcy_showGoodsNum_text")[index]).val()==""){
						alert("商品数量有误，请重新选择");
						$($(".lcy_showGoodsNum_text")[index]).val(1);
						$($(".lcy_reduce_btn")[index]).css("background-position","-252px -96px");
					}else{
						numArray[i]=$($(".lcy_showGoodsNum_text")[index]).val();
						if($($(".lcy_showGoodsNum_text")[index]).val()==1){
							$($(".lcy_reduce_btn")[index]).css("background-position","-252px -96px");
						}
						var num=parseInt($($(".lcy_showGoodsNum_text")[index]).val());
						/*根据数量实时计算价格*/
						var price=$($(".lcy_goodsPriceInfo")[index]).html().substring(1);
						var totalPrice=price*num;
						$($(".lcy_totalPriceOfOneGoodsInfo")[index]).html("￥"+totalPrice+".00");
						sumMoney();
						saveCartData();
					}
				})
				//点击商品的删除按钮触发事件
				$(".lcy_delectGoods_btn").click(function(){
					var index=$(".lcy_delectGoods_btn").index(this);
					var dessertId=$($(".lcy_delectGoods_btn")[index]).attr("id");
					doAjax(dessertId);
				})
				//点击全部清空触发事件
				$(".lcy_deleteAll_container").click(function(){
					var dessertId=$(".lcy_deleteAll_btn_Img").attr("id");
					doAjax(dessertId);
				})
		}
		//在页面跳转时，保存本页面的购物车数据
		function saveCartData(){
					//获取本页面所有的甜品Id
					var dessertIdList="";
					//获取购物车中所有甜品的Id
					for(var i=0;i<$(".lcy_goodsInfoDeatils_Img").length;i++){
						dessertIdList+=$($(".lcy_goodsInfoDeatils_Img")[i]).attr("id")+"A";
					}
					//获取购物车中所有甜品的数量
					var numList="";
					for(var i=0;i<$(".lcy_showGoodsNum_text").length;i++){
						numList+=$($(".lcy_showGoodsNum_text")[i]).val()+"A";
				
					}
					console.log("将要执行ajax");
					$.ajax({
					type:"post",
					url:"addCartRecordAjax.do",
					data:"dessertId=-1"+"&dessertIdList="+dessertIdList+"&numList="+numList,
					success:function(data){
					}
				});
				}
</script>
  
</html>
