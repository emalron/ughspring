<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<div class="row my-1 justify-content-center">
    <form method="POST" action="${contextPath }/login.do">
        <h2>로그인</h2>
        <div class="row my-1 justify-content-center">
            <input class="col-sm-12" type="text" name="id" placeholder="아이디를 입력하세요" required />
        </div>
        <div class="row my-1 justify-content-center">
            <input class="col-sm-12" type="password" name="pwd" placeholder="비밀번호를 입력하세요" required />
        </div>
        <div class="row my-1 justify-content-center">
            <c:if test="${not empty message}">
                <span class="text text-danger">
                    ${message}
                </span>
            </c:if>
            <input type="submit" class="btn btn-primary mr-2" value="로그인" />
            <input type="reset" class="btn btn-secondary" value="다시 작성" />
        </div>
    </form>
</div>