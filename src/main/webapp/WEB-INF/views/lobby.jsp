<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<div class="d-flex justify-content-center">
    <h1>고인돌 택시</h1>
</div>
<div class="d-flex my-4 justify-content-center">
    <img src="${contextPath}/resources/images/lobby.png">
</div>

<div class="d-flex my-2 justify-content-center">
    가지고 있는 코인 💰 <c:if test="${empty login.coin}">0</c:if>${login.coin}
</div>
<div class="d-flex my-2 justify-content-center">
    <a id="play" class="btn btn-primary" href="${contextPath}/ugh">플레이하기 💰 1</a>
</div>
