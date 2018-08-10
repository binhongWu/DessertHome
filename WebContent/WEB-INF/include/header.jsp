<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 固定菜单 -->
<style>
	*{
		margin: auto;
		font-family: "微软雅黑";
		font-size: 13px;
		color:#684029;
	}	
/*	固定菜单——开始*/
	.ypp_head{
		width:100%;
		height: 80px;
/*		border: 1px solid green;*/
		background-color:white;
		box-shadow: 2px 2px 8px #ADADAD;
		color: #ADADAD;
		position: fixed;
		top:0px;
		z-index:3;
	}
/*	测试用，可删除*/
	.ypp_container{
		width 1200px;
		height: 1000px;
		position: relative;
		top:75px;
	}
	.clear:after{
		content: "";
		display: block;
		clear: both;
	}
	.ypp_head_container{
		width:1200px;
/*		border: 1px solid red;*/
	}
	.ypp_logo,.ypp_logo_title,.ypp_classify,.ypp_loginRegist{
		float:left;
	}
	.ypp_logo{
		width:60px;
		height: 60px;
		background-size: cover;
		margin:10px 0px 10px 0px;
		background-image: url(image/logo_brown.png);
	}
	.ypp_logo_title{
		line-height: 80px;
/*		border: 1px solid red;*/
		font-family: Blackadder ITC;
		font-weight: bold;
		font-size: 30px;
	}
	.ypp_classify{
		margin-left: 15px;
		width:720px;
/*		border:1px solid yellow;*/
	}
	.ypp_classify a{
		color:#684029;
		text-decoration: none;
		padding:5px;
		margin:25px 12px 25px 12px;
		display: inline-block;
/*		border: 1px solid green;*/
	}
	.ypp_classify a:hover{
		color:#BB9772;
	}
	.ypp_loginRegist{
		width:300px;
		height: 60px;
		padding:10px;
		text-align: center;
/*		border:1px solid blue;*/
		
	}
	.ypp_loginRegist a{
		color:#684029;
		text-decoration: none;
		line-height: 60px;
		margin: 20px;
/*		border: 1px solid orange;*/
	}
	.ypp_head_cart{
		width:35px;
		height: 35px;
		display: inline-block;
		vertical-align: middle;
		transform: scale(0.5);
	}
	.ypp_head_cartImg{
		background-image: url(image/icon.png);
		background-position: -440px -90px;
	}
		
/*	固定菜单——结束*/
</style>
<div class="ypp_head">
	<div class="ypp_head_container clear">
		<div class="ypp_logo"></div>
		<div class="ypp_logo_title">Dessert</div>
		<div class="ypp_classify">
			<a href="index.jsp">首页</a>
			<a href="showDessert.do?kindId=1&tasteId=0&count=0">蛋糕</a>
			<a href="showDessert.do?kindId=2&tasteId=0&count=0">冰淇淋</a>
			<a href="showDessert.do?kindId=3&tasteId=0&count=0">布丁</a>
			<a href="#newDessert">当季新品</a>
			<a href="#recommendDessert">深读美食</a>
			<a href="showMessage.do">联系我们</a>
			<c:if test="${sessionScope.loginUser.userFlag==-1 }">
				<a href="backgroundManager.do">后台管理</a>
			</c:if>
		</div>
		<c:if test="${empty sessionScope.loginUser }">
			<div class="ypp_loginRegist"><a href="loginPrepare.do">登录</a>/<a href="registPrepare.do">注册</a></div>
		</c:if>
		<c:if test="${not empty sessionScope.loginUser }">
			<div class="ypp_loginRegist"><a href="userInfo.do?userId=${loginUser.userId}">${loginUser.userName}</a>/<a href="offline.do">注销</a><a href="showCart.do"><div class="ypp_head_cart ypp_head_cartImg"></div></a></div>
		</c:if>
</div>
</div>