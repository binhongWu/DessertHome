<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 页脚 -->
<style>
/*	页脚——开始*/
	.ypp_foot{
		width:100%;
		background-color: #333333;
		color:white;
	}
	.ypp_foot_container{
/*		border: 1px solid brown;*/
		color:white;
		text-align: center;
		padding:10px 0px 10px 0px;
		background-color: #333333;
		/* border: 1px solid red; */
		
	}
	.ypp_foot_p1{
		width:120px;
/*		border: 1px solid red;*/
		margin-top:10px;
	}
	.ypp_foot_p1 div{
		float:left;
	}
	.ypp_foot_logo{
		width:50px;
		height: 50px;
		background-size: cover;
		background-image: url(image/logo_white.png);
	}
	.ypp_foot_title{
		line-height: 50px;
/*		border: 1px solid red;*/
		font-family: Blackadder ITC;
/*		font-weight: bold;*/
		font-size: 23px;
		color:white;
	}
	.ypp_foot_p2{
		color:white;
/*		border: 1px solid red;*/
		padding: 10px;
	}
	.wbh_icon{
		width：100%;
		height:100px;
		/* border: 1px solid yellow; */
	}
	.wbh_iconContent{
		width:280px;
		height:100px;
		/* border: 1px solid yellow; */
		margin:auto;
	}
	.clear:after{
		content: "";
		display: block;
		clear: both;
	}
	.wbh_icon1{
		width:50px;
		height:50px;
		/* border: 1px solid yellow; */
		border-radius:50%;
		background-repeat:repeat;
		float:left;
		margin-top:30px;
		margin-left:30px;
	}
	.wbh_icon2{
		width:50px;
		height:50px;
		/* border: 1px solid yellow; */
		border-radius:50%;
		float:left;
		margin-top:30px;
		margin-left:30px;
	}
	.wbh_icon3{
		/* position:relative;
		top:;
		left:; */
		width:50px;
		height:50px;
	/* 	border: 1px solid yellow; */
		border-radius:50%;
		/* background-image: url(image/icon3.jpg); */
		float:left;
		margin-top:30px;
		margin-left:30px;
	}
	
/*	页脚——结束*/
</style>
<div class="ypp_foot">
	<div class="ypp_foot_container">
		<div class="ypp_foot_p1 clear">
			<div class="ypp_foot_logo"></div>
			<div class="ypp_foot_title">Dessert</div>
		</div>
		<!-- 关注 -->
		<div class="wbh_icon">
			<!-- 关注图标框框 -->
			<div class="wbh_iconContent clear">
				<div class="wbh_icon1">
					<img src="image/icon1.jpg" style="width:50px;height:50px;">
				</div>
				<div style="display:none;" class="wbh_icon1_1">
					<img src="image/WBGZ.jpg" style="width:100px;height:100px;">
				</div>
				<div class="wbh_icon2">
					<img src="image/icon2.jpg" style="width:50px;height:50px;">
				</div>
				<div class="wbh_icon3">
					<img src="image/icon3.jpg" style="width:50px;height:50px;">
				</div>
			</div>
		</div>
		<div class="ypp_foot_p2">Copyright© DessertHome街角小屋甜品站 2017-2019</div>
	</div>
</div>