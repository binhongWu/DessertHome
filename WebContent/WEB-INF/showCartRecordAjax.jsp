<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

  <%@include file="include/tag.jsp" %>
  长度是：${listLength }<br>
  <c:if test="${listLength==0}">
				<div class="lcy_emptyCart">
					<div class="lcy_CartImgBox">
						<img src="image/cartempty.png">
					</div>
					<div class="lcy_textTips">您的购物车里还没有商品</div>
					<div class="lcy_goShopping"><a href="#">去购物>>></a></div>
				</div>
			</c:if>
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
								<div class="lcy_goodsInfoDeatils_Img">
								<img src="DessertData/${userCartInfo.dessert.dessertImg_S }" alt="${userCartInfo.dessert.dessertName }" width="80px" height="80px">
								</div>
								<div class="lcy_goodsInfoDeatils_Text">
									<div class="lcy_goodsInfoDeatils_Text_Title"><a href="demo6.html">${userCartInfo.dessert.dessertName }</a></div>
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
								<input type="button" class="lcy_reduce_btn" /><input type="text" name="" id="" value="${userCartInfo.dessertNum }" class="lcy_showGoodsNum_text"><input type="button" class="lcy_plus_btn">
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
						<div class="lcy_payMoney">去结算</div>
					</div>
				</div>
			  </div>
			</c:if>
			