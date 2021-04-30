<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<div>
    <h1>고인돌 택시를 빛내주신 영웅들</h1>
  <table class="table">
    <thead>
        <tr>
            <th>ID</th>
            <th>💰 SCORE</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${list}">
            <tr>
                <td>${item.id}</td>
                <td>${item.score}</td>
            </tr>
        </c:forEach>
    </tbody>
  </table>
</div>