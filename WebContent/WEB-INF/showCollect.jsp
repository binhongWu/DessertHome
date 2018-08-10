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
    
    <title>My JSP 'showCollect.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/collect.css">
	<script src="js/jquery-1.8.0.js"></script>

  </head>
  
  <body>
 	<%@include file="include/header.jsp" %>
    <div class="wf_container">
    	<c:if test="${dessertCount==0}">
    		<div style="width:800px;height:600px;margin:auto;text-align:center;line-height:600px">
    			<img style="position:relative;top:270px" src="image/collect.png">
    			<div style="font-size:18px">暂无收藏信息！<a href="index.jsp">去收藏>></a></div>
    		</div>
    	</c:if>
    	<c:if test="${dessertCount!=0}">
    		<div class="wf_topTitle clear">
	    		<div class="wf_titleLeft"></div>
	    		<div class="wf_titleDessert">商品</div>
	    		<div class="wf_titleRight">单价</div>
	    		<div class="wf_titleRight">保质期</div>
	    		<div class="wf_titleRight">上架时间</div>
	    	</div>
	    	<div class="wf_collectList clear">
	    		<c:forEach var="dessert" items="${dessertList }">
	    			<a href="showDessertDetails.do?dessertId=${dessert.dessertId }">
			    		<div class="wf_collectInfo clear">
			    			<div class="wf_dessertImg"><img class="wf_cakeImg" src="DessertData/${dessert.dessertImg_S }"></div>
			    			<div class="wf_dessertInfo">
			    				<div class="wf_dessertName">${dessert.dessertName }</div>
			    				<div class="wf_dessertWeight">规格：${dessert.weight }g</div>
			    				<div class="wf_img"></div><span class="wf_imgText">赠送十套餐具</span>
			    			</div>
			    			<div class="wf_style">￥${dessert.dessertPrice }</div>
			    			<div class="wf_style">${dessert.keepTime }小时</div>
			    			<div class="wf_style"><fmt:formatDate value="${dessert.publishTime }" pattern="yyyy-MM-dd"/></div>
			    		</div>
			    	</a>
		    		<div class="wf_deleteDiv">
		    			<div id="${dessert.dessertId }" class="wf_deleteImg"></div>
		    		</div>
    			</c:forEach>
    		</div>
    	</c:if>
    	
    </div>
    <%@include file="include/footer.jsp" %>
  </body>
  <script>
  	$(".wf_deleteImg").mouseenter(function(){
  		$(this).css("background-position","-441px -168px");
  	}).mouseleave(function(){
  		$(this).css("background-position","-441px -140px");
  	})
  	$(".wf_deleteImg").click(function(){
  		$.ajax({
  			type:"post",
  			url:"deleteCollect.do",
  			data:{"userId":"${loginUser.userId}","dessertId":$(this).attr("id")},
  			success:function(data){
  				$(".wf_container").html(data);
  			}
  		})
  	})
 </script>
</html>
