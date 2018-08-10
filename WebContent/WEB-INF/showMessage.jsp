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
    
    <title>My JSP 'showMessage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <link rel="stylesheet" href="css/message.css">
  <body class="ypp_CommentBody">
<div class="ypp_comment">
	
	<div id="ypp_commentContainer">
		<a href="index.jsp" class="ypp_commentA">/&nbsp;返回首页&nbsp;/</a>
		<c:if test="${empty sessionScope.loginUser }">
			<a href="loginPrepare.do" class="ypp_commentlogin">&nbsp;登&nbsp;录&nbsp;</a>
		</c:if>
		<c:if test="${not empty sessionScope.loginUser }">
			<a href="userInfo.do?userId=${loginUser.userId}" class="ypp_commentUser">${loginUser.userName}&nbsp;</a>
		</c:if>
		<div class="ypp_commentTitle"><span class="ypp_commentSpan1">街角小屋留言区</span><br><span class="ypp_commentSpan2">或许你会在意，但如果言语不吐露而出的话，我们是不能也无法明白的</span><hr class="ypp_commentHr"></div>
		 
			<div class="ypp_commentPublish">
				<div class="ypp_commentPublishInfo">我要发表</div>
				<c:if test="${empty sessionScope.loginUser }">
					<div><textarea name="edit_messageContent" class="ypp_disableCommentPublishContent" cols="88" rows="10" placeholder="请输入你的建议"></textarea></div>
					<div class="ypp_commentTip">${tip}</div>
					<input type="button" value="提交" class="ypp_commentSubmit" disabled>
				</c:if>
				<c:if test="${not empty sessionScope.loginUser }">
					<div><textarea name="edit_messageContent" class="ypp_enableCommentPublishContent" cols="88" rows="10" placeholder="请输入你的建议"></textarea></div>
					<div class="ypp_commentTip">${tip}</div>
					<input type="button" value="提交" class="ypp_commentSubmit" onClick="validateContent()">
				</c:if>
				
			</div>
		<div class="ypp_ajaxMessage">
	        <c:forEach items="${messageList}" var="message">
	       	<div class="ypp_float">
	        	<span class="ypp_commentSpan3">${message.userName}</span>
	        </div>
	        <div class="ypp_float">
	        	<div class="ypp_content">
	        		${message.messageContent}
	        		<span class="ypp_commentSpan4"><fmt:formatDate value="${message.messageTime}" pattern="yyyy.MM.dd"/></span>
	        	</div>
	        </div>
	        <div class="ypp_clear"></div>
	        </c:forEach>
        </div>
	</div>
</div>
</body>
<script src="js/jquery-1.8.0.js"></script>
<script>
	//判断用户是否输入文字
	function validateContent(){
		if($(".ypp_enableCommentPublishContent").val() == ""){
			$(".ypp_commentTip").html("Tip：留言不能为空哦");
		}else{
			$.ajax({
				type:"post",
				url:"addMessageAjax.do",
				data:{"messageContent":$(".ypp_enableCommentPublishContent").val()},
				success:function(content){
					$(".ypp_commentTip").html(content);
				}
			});
			//alert($(".ypp_enableCommentPublishContent").val());
		}
	}
	$(".ypp_enableCommentPublishContent").focus(function(){
		$(".ypp_commentTip").html("");
	}).blur(function(){
		if($(this).val()==""){
			$(".ypp_commentTip").html("Tip：留言不能为空哦");
		}
	})
	$(".ypp_disableCommentPublishContent").focus(function(){
		$(".ypp_commentTip").html("Tip：登录后才能留言哦");
	})
	//页数
	var page=1;
	/*
	JS如何判断滚动条是否滚到底部
	判断滚动条到底部，需要用到DOM的三个属性值，即scrollTop、clientHeight、scrollHeight。
	 
	scrollTop为滚动条在Y轴上的滚动距离。
	 
	clientHeight为内容可视区域的高度。
	 
	scrollHeight为内容可视区域的高度加上溢出（滚动）的距离。
	 
	从这个三个属性的介绍就可以看出来，滚动条到底部的条件即为scrollTop + clientHeight == scrollHeight。
	 
	代码如下（兼容不同的浏览器）
	*/
	
	//滚动条在Y轴上的滚动距离

	function getScrollTop(){
	　　var scrollTop = 0, bodyScrollTop = 0, documentScrollTop = 0;
	　　if(document.body){
	　　　　bodyScrollTop = document.body.scrollTop;
	　　}
	　　if(document.documentElement){
	　　　　documentScrollTop = document.documentElement.scrollTop;
	　　}
	　　scrollTop = (bodyScrollTop - documentScrollTop > 0) ? bodyScrollTop : documentScrollTop;
	　　return scrollTop;
	}
	 
	//文档的总高度
	 
	function getScrollHeight(){
	　　var scrollHeight = 0, bodyScrollHeight = 0, documentScrollHeight = 0;
	　　if(document.body){
	　　　　bodyScrollHeight = document.body.scrollHeight;
	　　}
	　　if(document.documentElement){
	　　　　documentScrollHeight = document.documentElement.scrollHeight;
	　　}
	　　scrollHeight = (bodyScrollHeight - documentScrollHeight > 0) ? bodyScrollHeight : documentScrollHeight;
	　　return scrollHeight;
	}
	 
	//浏览器视口的高度
	 
	function getWindowHeight(){
	　　var windowHeight = 0;
	　　if(document.compatMode == "CSS1Compat"){
	　　　　windowHeight = document.documentElement.clientHeight;
	　　}else{
	　　　　windowHeight = document.body.clientHeight;
	　　}
	　　return windowHeight;
	}
	 
	window.onscroll = function(){
	　　if(getScrollTop() + getWindowHeight() == getScrollHeight()){
	　　　　//执行ajax
			page++;
			$.ajax({
				type:"post",
				url:"showMessage.do",
				data:"page="+page,
				success:function(content){
					$(".ypp_ajaxMessage").append(content);
				}
			});
	　　}
	};
	
</script>
</html>
