<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="include/tag.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showDessert.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
  </head>
  <link rel="stylesheet" href="css/showDessert.css">
  <body>
  <%@ include file="include/header.jsp" %>
   <!--分类列表总框开始-->
	<div class="ypp_TypeContainer">
		<div class="ypp_kindContainer">
			<!--分类开始-->
			<div class="ypp_clear" style="border-top: 1px solid #D4D4D4;">
				<div class="ypp_TypeDiv1">分类:</div>
				<!--动态加载分类名称层-->
				<div class="ypp_addType ypp_clear">
					<!--具体分类名称-->
					<c:if test="${kindId==0}">
						<a href="javascript:searchDessert(0,-1)" class="ypp_kindSelected ypp_kind">全部分类</a>
					</c:if>
					<c:if test="${kindId!=0}">
						<a href="javascript:searchDessert(0,-1)" class="ypp_unKindSelected ypp_kind ypp_kind">全部分类</a>
					</c:if>
					<!-- 遍历查询出所有分类 -->
					<c:forEach var="kind" items="${kindList}">
						<c:if test="${kindId==kind.kindId}">
							<a href="javascript:searchDessert(${kind.kindId},-1)" class="ypp_kindSelected ypp_kind">${kind.kindName}</a>
						</c:if>
						<c:if test="${kindId!=kind.kindId}">
							<a href="javascript:searchDessert(${kind.kindId},-1)" class="ypp_unKindSelected ypp_kind">${kind.kindName}</a>
						</c:if>
					</c:forEach>
				</div>
			<!--分类结束-->
			</div>
			</div>
			<!--分类列表总框结束-->
			
			</div>
		</div>
	<div class="ypp_ajax">
	<div class="ypp_kindContainer" style="border-bottom: 1px solid #D4D4D4;">
			
			<!--口味开始-->
			<div class="ypp_clear">
				<div class="ypp_TypeDiv1">口味:</div>
				<!--动态加载口味名称层-->
				<div class="ypp_addType">
					<c:if test="${tasteId==0}">
						<a href="javascript:searchDessert(-1,0)" class="ypp_tasteSelected ypp_taste">全部分类</a>
					</c:if>
					<c:if test="${tasteId!=0}">
						<a href="javascript:searchDessert(-1,0)" class="ypp_unTasteSelected ypp_taste">全部分类</a>
					</c:if>
					<c:forEach var="taste" items="${tasteList}">
						<c:if test="${tasteId==taste.tasteId}">
							<a href="javascript:searchDessert(-1,${taste.tasteId })" class="ypp_tasteSelected ypp_taste">${taste.tasteName}</a>
						</c:if>
						<c:if test="${tasteId!=taste.tasteId}">
							<a href="javascript:searchDessert(-1,${taste.tasteId })" class="ypp_unTasteSelected ypp_taste">${taste.tasteName}</a>
						</c:if>
					</c:forEach>
					<!--口味结束-->
				</div>
			<!--分类列表总框结束-->
			</div>
		</div>
		<!--动态加载甜品分类名称层，可能不会动态更新！！！-->
		<c:if test="${empty kindObj}">
			<div class="ypp_cake">全部</div>
		</c:if>
		<c:if test="${not empty kindObj}">
			<div class="ypp_cake">${kindObj.kindName}</div>
		</c:if>
		<!--循环-->
		<!--默认呈现   所有甜品层-->
		<div class="ypp_AllTypeContainer ypp_clear" id="ypp_ImgDiv">
			<!--甜品分层展示   循环  每四张换行-->
			<c:forEach items="${dessertList}" var="dessert">
			<a href="showDessertDetails.do?dessertId=${dessert.dessertId}">
			<div class="ypp_AllTypeContainer1">
				<!--动态加载具体甜品图片-->
				<div class="ypp_DessertImg"><img src="DessertData/${dessert.dessertImg_S}"></div>
				<div class="ypp_dessertInfo">
					<!--动态加载甜品名称-->
					<div class="ypp_price"><a href="showDessertDetails.do?dessertId=${dessert.dessertId}">${dessert.dessertName}</a></div>
					<!--动态加载名称、价格/重量-->
					<div class="ypp_priceWeight">￥<span>${dessert.dessertPrice}</span>&nbsp;&nbsp;/&nbsp;&nbsp;<span>${dessert.weight}</span>克</div>
					<!-- 判断用户是否登录，当有登陆的时候才显示加入购物车 -->
					
					<!--用户已经登录，显示加入购物车-->
					<c:if test="${empty sessionScope.loginUser }">
						<a class="ypp_disableAddShoppingCart"><div class="ypp_shoppingCar"></div>加入购物车</a>
					</c:if>
					<c:if test="${not empty sessionScope.loginUser }">
						<a href="javascript:addCart(${dessert.dessertId})" class="ypp_enableAddShoppingCart"><div class="ypp_shoppingCar"></div>加入购物车</a>
					</c:if>
				</div>
				<!--添加购物车成功的提示层，平时隐藏-->
				<div class="ypp_addSuccess_hidden">
					<div class="ypp_hidden_top ypp_clear">
						<div class="ypp_hidden_top_icon"></div>
						<div class="ypp_hidden_top_text">...</div>
					</div>
					<div class="ypp_look_cart">查看购物车</div>
				</div>
				<div id="ypp_loginNow">
					<div>您尚未登录，是否现在登录？</div>
					<div class="ypp_clear">
						<a class="ypp_confirm" href="loginPrepare.do">确定</a>
						<a class="ypp_cancel">取消</a>
					</div>
				</div>
			</div>
			</a>
			</c:forEach>
		</div>
	</div>
	</div>
	<%@ include file="include/footer.jsp" %>
</body>
<script type="text/javascript" src="js/jquery-1.8.0.js"></script>
<script>
	
	var kkid = 0;
	var ttid = 0;
	function addCart(dessertId){
		$.ajax({
			type:"post",
			url:"addCartRecordAjax.do",
			data:"dessertId="+dessertId,
			success:function(content){
				$(".ypp_hidden_top_text").html(content);
			}
		});
	}
	function addEvent(){
		//鼠标点击类别
		$(".ypp_kind").click(function(){
			//console.log($(this).attr("class"));
			//alert($(".ypp_kindSelected").attr("class"));
			$(".ypp_kindSelected").attr("class","ypp_unKindSelected");
			$(this).attr("class","ypp_kindSelected");
		})
		//鼠标点击口味
		$(".ypp_taste").click(function(){
			//console.log($(this).attr("class"));
			$(".ypp_tasteSelected").attr("class","ypp_unTasteSelected");
			$(this).attr("class","ypp_tasteSelected");
		})
		//用户未登录的情况下，给出提示，是否登录
		$(".ypp_disableAddShoppingCart").click(function(){
			//显示提示框
			$(this).parent("div").next().next().fadeIn(300);
			//鼠标未点击，离开容器，该提示框消失
			$(this).parent("div").parent("div").mouseleave(function(){
				$(this).children("#ypp_loginNow").fadeOut(1000);
			})
		})
		//鼠标点击取消（不进行登录），该提示框也消失
		$(".ypp_cancel").click(function(){
			$(this).parent().parent().fadeOut(500);
		})
		//用户登录的情况下，鼠标点击添加到购物车，显示相应的效果，该部分功能未完成
		$(".ypp_enableAddShoppingCart").click(function(){
			$(this).parent("div").next().fadeIn(300);
			$(this).parent("div").parent("div").mouseleave(function(){
				$(this).children(".ypp_addSuccess_hidden").fadeOut(1000);
			})
		})
		//用户成功添加购物车后点击查看购物车
		$(".ypp_look_cart").click(function(){
			window.location.href="showCart.do";
		})
	}
	addEvent();
	//
	
	/* ajax 实现展示所选择的类型的所有甜品 */
	function searchDessert(kid,tid){
		//选择种类
		if(tid==-1){
			kkid = kid;
			var str = $(".ypp_tasteSelected").attr("href");
			//获得口味
			//ttid = str.substring(str.indexOf(",")+1,str.indexOf(")"));
			//只要选择了种类，口味都默认先选中全部口味
			ttid = 0;
		}
		//选择口味
		else{
			ttid=tid;
			var str = $(".ypp_kindSelected").attr("href");
			//获得口味
			kkid = str.substring(str.indexOf("(")+1,str.indexOf(","));
		}
		$.ajax({
			type:"post",
			url:"showDessert.do",
			data:"kindId="+kkid+"&tasteId="+ttid,
			success:function(content){
				$(".ypp_ajax").html(content);
				addEvent();
			}
		});
	}
	
</script>	
</html>
