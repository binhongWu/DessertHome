<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/tag.jsp" %>
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