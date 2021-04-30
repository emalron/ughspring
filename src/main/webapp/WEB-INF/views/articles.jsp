<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />

<div class="d-flex justify-content-between align-items-center">
	<h1>게시글</h1>
	<a href="${contextPath }/board/write.do" class="btn btn-sm btn-primary">쓰기</a>
</div>
<table class="table">
	<thead>
		<tr>
			<th>#</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="article" items="${articles }">
			<tr>
				<td>${article.articleNo }</td>
				<td>
					<c:forEach var="i" begin="2" end="${article.lvl }">
						<span style="padding-left:25px"></span>
					</c:forEach>
					<a href="${contextPath }/board/${article.articleNo}">
						${article.title }
					</a>
				</td>
				<td>${article.id}</td>
				<td>${article.writeDate}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
        <c:if test="${page.begin > 10}">
            <li class="page-item">
                <a class="page-link" href="${contextPath}/board/listArticles.do?page=${page.begin-10}">prev</a>
            </li>
        </c:if>
        <c:forEach var="i" begin="${page.begin}" end="${page.end}">
            <c:choose>
                <c:when test="${i eq page.current}">
                    <li class="page-item active">
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                </c:otherwise>
            </c:choose>
                <a class="page-link" href="${contextPath}/board/listArticles.do?page=${i}" class="mr-2">${i}</a>
            </li>
        </c:forEach>
        <c:if test="${page.end % 10 eq 0}">
            <li class="page-item">
                <a class="page-link" href="${contextPath}/board/listArticles.do?page=${page.end+1}">next</a>
            </li>
        </c:if>
    </ul>
</nav>