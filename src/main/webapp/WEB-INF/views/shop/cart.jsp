<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>장바구니</title>
    <%@ include file="/WEB-INF/views/parts/common.cdn.html" %>
    <link rel="stylesheet" href="/resources/stylesheets/shop/cart.css">
    <script src="/resources/scripts/shop/cart.js"></script>
    <script src="/resources/scripts/class.ajax.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/parts/header.jsp" %>
<main class="cart-wrap">
    <div class="step">
        <div class="step-title">장바구니</div>
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
                <li class="step3">
                    <i class="fal fa-clipboard-list-check"></i>
                    <div>주문완료</div>
                </li>
            </ul>
        </div>
    </div>
    <div class="cart">
        <form id="cart-form">
            <table>
                <thead>
                    <tr>
                        <th class="check-wrap">
                            <input type="checkbox" name="allCheck-order" id="allCheck">
                            <label for="allCheck">
                                <i class="fas fa-check-circle"></i>
                            </label>
                        </th>
                        <th colspan="2">주문상품 정보</th>
                        <th>수량</th>
                        <th>가격</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                <c:if test="${cartPrepareVo.cartDto == []}">
                    <tr>
                        <td class="message" colspan="7">추가된 상품이 없습니다.</td>
                    </tr>
                </c:if>
                <c:forEach var="cart" items="${cartPrepareVo.cartDto}" varStatus="status">
                    <tr>
                        <td class="check-wrap">
                            <input type="checkbox" name="choiceCheck-order" id="choiceCheck_${status.index}" data-index="${cart.cartIndex}">
                            <label for="choiceCheck_${status.index}">
                                <i class="fas fa-check-circle"></i>
                            </label>
                        </td>
                        <td>
                            <img src='data:image:jpg;base64,${cart.imageBase64}' alt="옷 이미지" width="120" height="120">
                        </td>
                        <td class="info-wrap">
                            <div><b>${cart.productName}</b></div>
                            <div class="option-wrap">${cart.size}/${cart.color}</div>
                        </td>
                        <td class="cartCount-wrap">
                            <input type="number" name="cartCount" value="${cart.cartCount}">
                        </td>
                        <td class="subPrice-wrap price" data-price="${cart.cartSubtotalPrice}">${cart.cartSubtotalPrice}</td>
                        <td class="delete-wrap">
                            <input type="button" name="deleteIcon" id="deleteIcon_${status.index}" data-index="${cart.cartIndex}">
                            <label for="deleteIcon_${status.index}">
                                <i class="fal fa-times"></i>
                            </label>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="6"class="choice-delete-wrap">
                        <input type="button" name="delete" id="delete-button" class="object-button prop-dark" value="선택삭제">
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="total-order">
                <div class="order-wrap">
                    <div class="title">
                        <b>결제 금액</b>
                    </div>

                    <div class="totalPrice-wrap">
                        <span class="price">0</span>
                    </div>

                    <div class="content">
                        <div class="productPrice-wrap">
                            <span>총 상품금액</span>
                            <span class="price">0</span>
                        </div>
                        <div class="deliveryCharge-wrap">
                            <span>배송비</span>
                            <span class="price">0</span>
                        </div>
                    </div>
                </div>
                <input type="submit" id="order" value="주문하기" class="object-button prop-purple">
            </div>
        </form>
    </div>
</main>
<%@ include file="/WEB-INF/views/parts/footer.jsp" %>
</body>
</html>