<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<div class="article title my-3">
	<h1>${article.title }</h1>
	<h4>by <span class="text-primary">${article.id }</span></h4>
</div>
<hr>
<div class="article content ck-content">
	<pre class="text">${article.content }</pre>
</div>
<div class="article">
    <a href="${contextPath}/board/reply/${article.articleNo}" class="mr-3">답글작성</a>
	<c:if test="${login.id eq article.id}">
	    <a href="${contextPath}/board/edit/${article.articleNo}" class="mr-3">수정</a>
    </c:if>
	<a href="${contextPath }/board/delete/${article.articleNo}" class="mr-3">삭제</a>
	<a class="mx-2" href="${contextPath }/board/listArticles.do">목록으로</a>
</div>