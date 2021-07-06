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
    <link rel="stylesheet" href="/resources/stylesheets/shop/order.css">
    <script src="/resources/scripts/shop/order.js"></script>
    <script src="/resources/scripts/class.ajax.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/parts/header.jsp" %>
<main class="order-wrap">
    <div class="step">
        <div class="step-title">주문/결제</div>
        <div class="step-content">
            <ul>
                <li class="step1 on">
                    <i class="fal fa-shopping-cart"></i>
                    <div>장바구니</div>
                </li>
                <li class="step2 on">
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
    <div class="order">
        <div class="order-content">
            <form id="order-form">
                <div class="sub-title">주문 상품</div>
                <table>
                    <thead>
                        <tr>
                            <th colspan="2">주문상품 정보</th>
                            <th>수량</th>
                            <th>가격</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:if test="${orderPrepareVo.orderDto == []}">
                        <tr>
                            <td class="message" colspan="7">추가된 상품이 없습니다.</td>
                        </tr>
                    </c:if>
                    <c:forEach var="order" items="${orderPrepareVo.orderDto}" varStatus="status">
                        <tr>
                            <td>
                                <img src='data:image:jpg;base64,${order.imageBase64}' alt="옷 이미지" width="120" height="120">
                            </td>
                            <td class="info-wrap">
                                <div><b>${order.productName}</b></div>
                                <div class="option-wrap">${order.size}/${order.color}</div>
                            </td>
                            <td class="orderCount-wrap">
                                <input type="number" name="orderCount" value="${order.orderCount}">
                            </td>
                            <td class="subPrice-wrap price" data-price="${order.orderSubtotalPrice}">${order.orderSubtotalPrice}</td>
                            <td class="delete-wrap">
                                <input type="button" name="deleteIcon" id="deleteIcon_${status.index}"
                                       data-product-index = "${order.productIndex}"
                                       data-order-index="${order.orderIndex}">
                                <label for="deleteIcon_${status.index}">
                                    <i class="fal fa-times"></i>
                                </label>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <!--주소 입력, 결재수단 선택-->
                <div class="address-wrap">
                    <div class="sub-title">받는 사람 정보</div>
                    <div class="content">
                        <div>
                            <div class="address-title">우편번호</div>
                            <div class="address-content" id="addressPost">${orderPrepareVo.orderAddressDto.orderAddressPost}</div>
                            <input type="button" id="address-change" class="object-button prop-basic" value="주소변경">
                        </div>
                        <div>
                            <div class="address-title">주소</div>
                            <div class="address-content" id="address">${orderPrepareVo.orderAddressDto.orderAddress}</div>
                        </div>
                        <div>
                            <div class="address-title">상세주소</div>
                            <div class="address-content" id="addressDetail">${orderPrepareVo.orderAddressDto.orderAddressDetail}</div>
                        </div>
                    </div>
                </div>
                <!--//주소 입력, 결재수단 선택-->

                <br>
                <div class="payment-wrap">
                    <div class="sub-title">결재수단 선택</div>
                    <div class="payment-content simplePayment">
                        <div class="payment-title">간편결제</div>
                        <button type="button" data-payment="kakaoPay" name="payment" class="kakaoPay">
                            <i class="fas fa-comment"></i>
                            <span>kakao pay</span>
                        </button>
                        <button type="button" data-payment="payco" name="payment" class="payco">
                            <span>payco</span>
                        </button>

                        <button type="button" data-payment="smilePay" name="payment" class="smilePay">
                            <i class="fas fa-laugh"></i>
                            <span>SmilePay</span>
                        </button>
                    </div>

                    <div class="payment-content generalPayment">
                        <div class="payment-title">일반결제</div>
                        <button type="button" data-payment="phonePayment" name="payment" class="phonePayment">
                            <span>휴대폰 결제</span>
                        </button>
                        <button type="button" data-payment="creditCard" name="payment" class="creditCard">
                            <span>신용카드</span>
                        </button>
                        <button type="button" data-payment="account" name="payment" class="account">
                            <span>실시간 계좌 이체</span>
                        </button>
                    </div>
                </div>
                <%--<br>
                <div>
                        <input type="submit" id="order-button" value="결재하기">
                </div>--%>
            </form>
        </div>

        <!--계산서-->
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
            <input type="submit" id="order-button" value="주문하기" class="object-button prop-purple">
        </div>
        <!--//계산서-->
    </div>
</main>
<%@ include file="/WEB-INF/views/parts/footer.jsp" %>
</body>
</html>