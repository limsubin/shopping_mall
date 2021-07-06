<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>구매하기</title>
    <%@ include file="/WEB-INF/views/parts/common.cdn.html" %>
    <link rel="stylesheet" href="/resources/stylesheets/shop/order.success.css">
</head>
<body>
<%@ include file="/WEB-INF/views/parts/header.jsp" %>
<main class="order-success-wrap">
    <div class="step">
        <div class="step-title">주문완료</div>
        <div class="step-content">
            <ul>
                <li class="step1 on">
                    <i class="fal fa-shopping-cart"></i>
                    <div>장바구니</div>
                </li>
                <li class="step2">
                    <i class="fal fa-credit-card"></i>
                    <div>주문/결제</div>
                </li>
                <li class="step3 on">
                    <i class="fal fa-clipboard-list-check"></i>
                    <div>주문완료</div>
                </li>
            </ul>
        </div>
    </div>
    <div class="success-message">
        <div class="title">
            <i class="fal fa-check-circle"></i>
            <div>결제 완료되었습니다.</div>
        </div>

        <div class="content">
            <div>결제 내역은 <a href="/shop/paymentStatus">주문내역</a>에서 확인 하실 수 있습니다.</div>
        </div>
        <div class="home">
            <a href="/shop">홈으로</a>
        </div>
    </div>
</main>
<%@ include file="/WEB-INF/views/parts/footer.jsp" %>
</body>
</html>