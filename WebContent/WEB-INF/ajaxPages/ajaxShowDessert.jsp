<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/tag.jsp" %>
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