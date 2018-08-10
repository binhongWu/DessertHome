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
    
    <title>My JSP 'firstPage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <link rel="stylesheet" href="css/firstPage.css">
  <link href="css/main.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="js/jquery-1.8.0.js"></script>
  <body>
 <%@ include file="include/header.jsp" %> 
 <!--总容器-->
 <a name="top"></a>
<div class="wbh_indexContainer">
	<!--轮播图层-->
	<div class="wbh_floatContainer">
		<!--图片层-->
		<div class="wbh_floatImg"><c:forEach items="${subjects}" var="subject"><a href="showSubject.do?subjectId=${subject.subjectId}"><img src="indexImage/${subject.subjectImg}"></a></c:forEach></div>
		<!--标识层-->
		<div class="wbh_flagDiv">
			<div></div>
			<div></div>
			<div></div>
		</div>
	</div>
	<!-- 视频层 -->
	<div class="mainSpoke">
		<video src="DessertData/video.mp4" controls autoplay loop width="1200px" height="500px"></video>
	</div>
	<!--跳转层-->
	<div class="wbh_jumpDiv wbh_indexClear">
		<!-- 新品 -->
		<a href="#newDessert"><div><img src="indexImage/index_01.jpg"></div></a>
		<!-- 热销 -->
		<a href="#hotDessert"><div><img src="indexImage/index_02.jpg"></div></a>
		<!-- 推荐 -->
		<a href="#recommendDessert"><div><img src="indexImage/index_03.jpg"></div></a>
		<div><img src="indexImage/index_04.jpg"></div>
	</div>
	<a name="newDessert">
	<!--新品推荐层-->
		<div class="wbh_newDessertContainer">
			<!--新品推荐文字-->
			<div class="wbh_newDessertWords"><span>DessertHome</span>&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;<span>新品</span></div>
			<!--新品推荐  最新发布前三-->
			<div class="wbh_newDessert">
				<a href="showDessertDetails.do?dessertId=1"><div class="wbh_img"><img src="indexImage/72f08d7c749edea2edbac61732ab35d4.jpg"></div></a>
				<!--新品发布图片-->
				<div class="wbh_newImg wbh_indexClear">
					<!--动态获取  按照时间排序  取前三-->
					<c:forEach items="${newDessert}" var="newDessert">
						<div class="wbh_newImg1">
							<a href="showDessertDetails.do?dessertId=${newDessert.dessertId }"><div><img src="DessertData/${newDessert.dessertImg_B}"></div>
							<!--甜品名称-->
							<div class="wbh_newImg2">${newDessert.dessertName}</div></a>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</a>
	<a name="hotDessert">
		<!--热销推荐层  销量前四-->
		<div class="wbh_hotDessert">
			<div class="wbh_newDessertWords"><span>DessertHome</span>&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;<span>热销</span></div>
			<!--图片层-->
			<div class="wbh_hotDessertImg">
				<!--第一层图-->
				<c:forEach items="${hotDesserts}" var="hotDessert">
					<div class="wbh_hotDessertImg1 wbh_indexClear">
						<div style="height:204px; overflow:hidden;">
							<a href="showDessertDetails.do?dessertId=${hotDessert.dessertId }"><img src="DessertData/${hotDessert.dessertImg_B}">
								<div class="wbh_hotDessertName">${hotDessert.dessertName }</div>
								<div class="wbh_hotDessertNume1" >月销&nbsp;${hotDessert.sumNum}</div>
							</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</a>
	<a name="recommendDessert">
		<!-- 甜品故事 -->
		<div class="wbh_dessertStoryContent">
			<div class="wbh_newDessertWords"><span>DessertHome</span>&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;<span>推荐</span></div>
			<!-- 故事层 -->
			<div class="wbh_dessertStory clear">
				<a href="showDessertDetails.do?dessertId=8"><div class="wbh_dessertStory1"><img src="DessertData/cake8.jpg"></div></a>
				<a href="showDessertDetails.do?dessertId=37"><div class="wbh_dessertStory1"><img src="DessertData/part6.jpg"></div></a>
				<a href="showDessertDetails.do?dessertId=28"><div class="wbh_dessertStory1"><img src="DessertData/cake28.jpg"></div></a>
				<a href="showDessertDetails.do?dessertId=18"><div class="wbh_dessertStory1"><img src="DessertData/cake18.jpg"></div></a>
			</div>
		</div>
	</a>
	<!-- 置顶锚点 -->
	<div class="wbh_backTop"><a href="#top"><span>▲</span><br><p>TOP</p></a></div>
</div>
 <%@ include file="include/footer.jsp" %> 
  </body>
<script>
	var count = 0;
	//定时器
	var timer;
	//循环播放图片
	function loopImage(){
		//图片动画
		$(".wbh_floatContainer>.wbh_floatImg>a>img:first").animate({
			"marginLeft":-1200*count+"px"
		},3000);
		//累计滚动的次数
		count++;
		if(count==3){
			count=0;
		}
		//开启定时
		timer = window.setTimeout("loopImage()",8000);
	}
	loopImage();
	$(".wbh_floatContainer").mouseenter(function(){
		window.clearTimeout(timer);
	}).mouseleave(function(){
		loopImage();
	})
</script>
</html>
