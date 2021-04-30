<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<div class="col">
    <div class="d-flex justify-content-center">
        <h1>고인돌 택시<h1>
    </div>
        <p>
            <span id="score">SCORE 💎</span> <span id="current-score">0</span>
        </p>
    </div>
    <div>
        <canvas id="jsdos"></canvas>
    </div>
</div>
<script src="${contextPath}/resources/ugh/js-dos.js"></script>
<script src="${contextPath}/resources/ugh/ugh.js"></script>

</script>