<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<form method="POST" action="${contextPath }/member/signup.do">
	<div class="container col-sm-4">
	<h2>회원가입</h2>
		<div class="row my-1 justify-content-center">
			<input class="col-sm-12" type="text" name="id" placeholder="아이디를 입력하세요" required />
		</div>
		<div class="row my-1 justify-content-center">
			<input class="col-sm-12" type="password" name="pwd" placeholder="비밀번호를 입력하세요" required />
		</div>
		<div class="row my-1 justify-content-center">
            <input class="col-sm-12" type="text" name="name" placeholder="이름" required />
        </div>
        <div class="row my-1 justify-content-center">
            <input class="col-sm-12" type="email" name="email" placeholder="xxx@xxx.com" pattern="[a-z0-9._%+-]+@[a-z0-9]+\.[a-z0-9]+" required />
        </div>
        <c:if test="${not empty message}">
            <div class="row my-1 justify-content-center">
                <div class="text text-danger">
                    ${message}
                </div>
            </div>
        </c:if>
		<div class="row my-1 justify-content-center">
			<input type="submit" class="btn btn-primary mr-2" value="회원가입" />
			<input type="reset" class="btn btn-secondary" value="다시 작성" />
		</div>
	</div>
</form>