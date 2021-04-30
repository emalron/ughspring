<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">사이트</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav mr-auto">
      <li id="home" class="nav-item">
        <a class="nav-link" href="${contextPath }/">홈 <span class="sr-only">(current)</span></a>
      </li>
      <c:if test="${login.id eq 'admin'}">
          <li id="member" class="nav-item">
            <a class="nav-link" href="${contextPath }/member/listMembers.do">회원 명부</a>
          </li>
      </c:if>
      <li id="board" class="nav-item">
        <a class="nav-link" href="${contextPath }/board/listArticles.do">게시글</a>
      </li>
      <li id="ugh" class="nav-item">
        <a class="nav-link" href="${contextPath }/lobby">고인돌 택시</a>
      </li>
      <li id="ranking" class="nav-item">
        <a class="nav-link" href="${contextPath }/ranking">랭킹</a>
      </li>
      <li id="store" class="nav-item">
        <a class="nav-link" href="${contextPath }/store">상점</a>
      </li>

    </ul>
    <ul class="navbar-nav d-flex align-items-center">
          <li class="nav-item">
            <div class="progress mx-2" style="width:200px;">
                <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100"></div>
            </div>
          </li>
        <c:choose>
            <c:when test="${empty login}">
                <li class="nav-item">
                    <a href="${contextPath}/signup.do" class="btn btn-secondary mr-3">회원가입</a>
                </li>
                <li class="nav-item">
                    <a href="${contextPath}/login.do" class="btn btn-primary">로그인</a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="nav-item">
                    <span class="mr-2">
                        Welcome back, ${login.id} 💰 <span id="coin">${login.coin}</span>
                    </span>
                </li>
                <li class="nav-item">
                    <a href="${contextPath}/logout.do" class="btn btn-sm btn-secondary">로그아웃</a>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
  </div>
</nav>
<script>
    (() => {
        const t = window.location.href.split("/");
        let current_menu = t[4];
        if(t[4].length == 0) {
            current_menu = "home"
        }
        const elem_name = `li#\${current_menu}`;
        const elem = document.querySelector(elem_name);
        if(elem != null) {
            elem.classList.add("active");
        }
    })();
</script>
<script src="${contextPath}/resources/js/lobby.js"></script>
<script>coinCheck()</script>