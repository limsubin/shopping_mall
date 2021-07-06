<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>결제 현황</title>
    <%@ include file="/WEB-INF/views/parts/common.cdn.html" %>
    <link rel="stylesheet" href="/resources/stylesheets/shop/paymentStatus.css">
    <script src="/resources/scripts/shop/paymentStatus.js"></script>
    <%--<script src="/resources/scripts/class.ajax.js"></script>--%>
</head>
<body>
<%@ include file="/WEB-INF/views/parts/header.jsp" %>
<main class="payment-wrap">
    <div class="payment">
        <div class="title">${paymentStatusVo.userDto.nickname}님의 주문 내역</div>
        <c:if test="${paymentStatusVo.maxPage == 0}">
            <div class="warning-message">
                <i class="fal fa-exclamation-circle"></i>
                <span>주문 내역이 없습니다.</span>
            </div>
        </c:if>

        <c:if test="${paymentStatusVo.maxPage != 0}">
            <c:set var="orderIndexDuplicateCount" value="0" />
            <c:set var="orderIndexValue" value="" />
            <c:forEach var="i" begin="0" end="${paymentStatusVo.paymentStatusDtos.size()-1}" varStatus="vs">
                <c:if test="${i == orderIndexDuplicateCount}">
                    <div class="date-wrap">
                        <div class="date" data-date="${paymentStatusVo.paymentStatusDtos[i].orderDate}">
                            <fmt:formatDate value="${paymentStatusVo.paymentStatusDtos[i].orderDate}" type="date"/>
                        </div>
                    </div>

                    <table>
                        <thead class="head">
                            <tr class="title-wrap">
                                <th>
                                    주문 번호 : ${paymentStatusVo.paymentStatusDtos[i].orderIndex}
                                </th>
                                <th>주문정보</th>
                                <th>가격</th>
                                <th>개수</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <c:set var="orderIndexValue" value="${paymentStatusVo.paymentStatusDtos[vs.index].orderIndex}" />

                        <c:forEach var="paymentStatus" items="${paymentStatusVo.paymentStatusDtos}" varStatus="status">
                            <c:if test="${orderIndexValue == paymentStatus.orderIndex}">
                                <c:set var="orderIndexDuplicateCount" value="${orderIndexDuplicateCount+1}" />
                                <tr>
                                    <td class="image-wrap">
                                        <a href="/shop/read/${paymentStatus.productIndex}">
                                            <img src='data:image:jpg;base64,${paymentStatus.imageBase64}' alt="옷 이미지" width="130" height="130">
                                        </a>
                                    </td>
                                    <td class="options-wrap">
                                        <div class="productName-wrap">
                                                ${paymentStatus.productName}
                                        </div>
                                        <div>
                                            <span class="size-title">사이즈</span>
                                            <b>${paymentStatus.size}</b>
                                        </div>
                                        <div>
                                            <span class="color-title">색상</span>
                                            <b>${paymentStatus.color}</b>
                                        </div>
                                    </td>
                                    <td class="price-wrap price">${paymentStatus.orderSubtotalPrice}</td>
                                    <td>${paymentStatus.orderCount}</td>
                                    <td>
                                        <c:if test="${paymentStatus.paymentState}">
                                            <div class="payment-status">결제 완료</div>
                                        </c:if>
                                        <c:if test="${!paymentStatus.paymentState}">
                                            <div class="paying-status">결제 중</div>
                                        </c:if>
                                    </td>
                                    <td class="review-wrap">
                                        <c:if test="${paymentStatusVo.reviewStatusArray[status.index] == 0}">
                                            <div class="review-button-wrap">
                                                <i class="fas fa-marker"></i>
                                                <a href="/shop/paymentReview/${paymentStatus.productIndex}/${paymentStatus.orderIndex}">리뷰 작성</a>
                                            </div>
                                        </c:if>
                                        <c:if test="${paymentStatusVo.reviewStatusArray[status.index] > 0}">
                                            <div class="review-message">리뷰 작성 완료</div>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </table>
                </c:if>
            </c:forEach>
        </c:if>

        <div class="pagination">
            <c:if test="${paymentStatusVo.page > 1}">
            <span>
                <a href="/shop/paymentStatus/1" target="_self">&lt;&lt;</a>
            </span>
            </c:if>
            <c:forEach var="i" begin="${paymentStatusVo.leftPage}" end="${paymentStatusVo.rightPage}" step="1">
                <c:if test="${i != 0}">
                    <c:if test="${i == paymentStatusVo.page}">
                        <span class="${i == paymentStatusVo.page ? "active" : ""}">
                            <a>${i}</a>
                        </span>
                    </c:if>
                    <c:if test="${i != paymentStatusVo.page}">
                        <span>
                            <a href="/shop/paymentStatus/${i}" target="_self">${i}</a>
                        </span>
                    </c:if>
                </c:if>
            </c:forEach>
            <c:if test="${paymentStatusVo.page < paymentStatusVo.maxPage}">
            <span>
                <a href="/shop/paymentStatus/${paymentStatusVo.maxPage}" target="_self">&gt;&gt;</a>
            </span>
            </c:if>
        </div>
    </div>
</main>
<%@ include file="/WEB-INF/views/parts/footer.jsp" %>
</body>
</html>