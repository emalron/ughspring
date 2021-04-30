<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div class="d-flex justify-content-between align-items-center">
    <h1>고인돌 택시 상점</h1>
    <h4>💎 <span id="score">${score.score}</span></h4>
</div>
<div>
  <table class="table">
    <thead>
        <tr>
            <th>품목</th>
            <th>가격</th>
            <th>수량</th>
            <th></th>
        <tr>
    </thead>
    <tbody>
        <tr>
            <td>
                코인 💰
            </td>
            <td>
                💎 1000
            </td>
            <td>
                <input type="number" id="coin-qty" min="1" max="10" value="1"/>
            </td>
            <td>
                <button class="btn btn-primary mx-2" onclick="buy('coin')">구매</button>
            </td>
        </tr>
    </tbody>
  </table>
</div>
<script>
    function buy(item) {
        const qty_name = `\${item}-qty`;
        const qty_elem = document.querySelector(`input#\${qty_name}`);
        const qty_value = qty_elem.value;
        const url = `\${window.location.origin}/pro30/store/buy`;
        fetch(url, {
            method: 'post',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({item:item, qty:qty_value})
        }).then(res => {
            return res.json();
        }).then(result => {
            const coin_display = document.querySelector("span#coin");
            const score_display = document.querySelector("span#score");
            coin_display.innerText = result.coin;
            score_display.innerText = result.score;
        }).catch(error => {
            console.error(error)
        })
        console.log(`\${item}: \${qty_value}`);
    }
</script>