<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h1>회원 명부</h1>
<table class="table">
	<thead>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>이메일</th>
			<th>가입일</th>
			<th>코인</th>
			<c:if test="${login.id eq 'admin'}">
                <th>삭제</th>
            </c:if>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="member" items="${members }">
			<tr>
				<td>${member.id }</td>
				<td>${member.name}</td>
				<td>${member.email}</td>
				<td>${member.joinDate}</td>
				<td>${member.coin}</td>
				<c:if test="${login.id eq 'admin'}">
                    <td>
                        <a href="${contextPath}/member/delete/${id}">삭제</a>
                    </td>
                </c:if>
			</tr>
		</c:forEach>
	</tbody>
</table>