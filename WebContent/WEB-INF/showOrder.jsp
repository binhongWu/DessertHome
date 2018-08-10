<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="include/tag.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showOrder.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/order.css">
	<script src="js/jquery-1.8.0.js"></script>

  </head>
  
  <body>
  <%@include file="include/header.jsp" %>
    <div class="wf_container">
	    <c:if test="${orderCount==0}">
	    	<div style="width:800px;height:600px;margin:auto;text-align:center;line-height:600px">
	    		<img style="position:relative;top:270px" src="image/order.png">
    			<div style="font-size:18px">暂无订单信息！<a href="index.jsp">去购物>></a></div>
	    	</div>
	    </c:if>
	    <c:if test="${orderCount!=0}">
	    	<c:forEach var="orderInfo" items="${orderInfoList }">
		    	<div class="wf_orderBox">
		    		<div class="wf_orderTop clear">
		    			<div class="wf_orderIdBox">订单编号：${orderInfo.order.orderId }</div>
		    			<c:if test="${orderInfo.order.orderFlag==1}">
		    				<div class="wf_orderFlagBox">交易成功</div>
		    			</c:if>
		    			<c:if test="${orderInfo.order.orderFlag==-1}">
		    				<div class="wf_orderFlagBox">交易失败</div>
		    			</c:if>
		    			<c:if test="${orderInfo.order.orderFlag==0}">
		    				<div class="wf_orderFlagBox">待支付<a class="wf_toPay" href="#" title="去支付">go>></a></div>
		    			</c:if>
		    		</div>
		    		<c:forEach var="dessert" items="${orderInfo.dessertList}">
			    		<div class="wf_dessertBox clear">
			    			<div class="wf_dessertImg"><img class="wf_cakeImg" src="DessertData/${dessert.dessertImg_S }"></div>
			    			<div class="wf_dessertInfo">
			    				<div class="wf_dessertName">${dessert.dessertName }</div>
			    				<div class="wf_dessertWeight">规格：${dessert.weight }g</div>
			    				<div class="wf_img"></div><span class="wf_imgText">赠送十套餐具</span>
			    			</div>
			    			<div class="wf_orderRight">
			    				<div class="wf_priceBox">￥${dessert.dessertPrice }</div>
			    				<div class="wf_countBox">x${dessert.count }</div>
			    			</div>
			    		</div>
		    		</c:forEach>
		    		<div class="wf_totalPrice">合计：￥${orderInfo.order.totalPrice }</div>
		    		<div class="wf_orderTime">
		    			下单时间：<fmt:formatDate value="${orderInfo.order.orderTime }" pattern="yyyy-MM-dd HH:mm:ss" />
		    			<div id="${orderInfo.order.orderId }" class="wf_deleteOrder">删除订单</div>
		    		</div>
		    	</div>
	    	</c:forEach>
	    </c:if>
    </div>
   <%@include file="include/footer.jsp" %> 
  </body>
  <script>
  	$(".wf_deleteOrder").mouseenter(function(){
  		$(this).css("color","#684029");
  		$(this).css("border","1px solid #684029");
  	}).mouseleave(function(){
  		$(this).css("color","grey");
  		$(this).css("border","1px solid grey");
  	})
  	$(".wf_deleteOrder").click(function(){
  		$.ajax({
  			type:"post",
  			url:"deleteOrder.do",
  			data:{"userId":"${loginUser.userId}","orderId":$(this).attr("id")},
  			success:function(data){
  				$(".wf_container").html(data);
  			}
  		})
  	})
  	
  </script>
</html>
