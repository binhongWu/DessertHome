<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="../include/tag.jsp" %>
<select id="selCity" class="wbh_ajaxCity">
	<option>请选择城市</option>
	<c:forEach items="${cityList}" var="city">
		<option>${city.cityName }</option>
	</c:forEach>
</select>
