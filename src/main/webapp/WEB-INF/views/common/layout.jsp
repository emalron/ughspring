<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>
		<tiles:insertAttribute name="title" />
	</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="${contextPath }/css/layout.css">
	<link rel="stylesheet" href="${contextPath }/ckeditor/ckeditor.css">
</head>
<body>
	<tiles:insertAttribute name="header" />
	<div class="container my-2">
		<tiles:insertAttribute name="body" />
	</div>
	<tiles:insertAttribute name="footer" />
	
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="${contextPath}/resources/js/header.js">
</body>
</html>