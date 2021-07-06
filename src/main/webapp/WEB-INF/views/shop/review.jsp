<%@ page import="com.lsb.portfolio.shopping_mall.enums.shop.ReviewResult" %>
<%@ page import="com.lsb.portfolio.shopping_mall.dtos.user.UserDto" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>리스트</title>
    <%@ include file="/WEB-INF/views/parts/common.cdn.html" %>
    <link rel="stylesheet" href="/resources/stylesheets/shop/review.css">
    <script src="/resources/scripts/shop/review.js"></script>
    <script src="/resources/scripts/class.ajax.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/parts/header.jsp" %>
<main class="review-wrap">
    <div class="title">리뷰 작성</div>
    <div class="product-wrap">
        <table>
            <thead class="head">
            <tr class="title-wrap">
                <th>주문 번호 : ${paymentReviewPrepareVo.orderDto.orderIndex}</th>
                <th>주문정보</th>
                <th>가격</th>
                <th>개수</th>
            </tr>
            </thead>
                <tr>
                    <td class="image-wrap">
                        <a href="/shop/read/${paymentReviewPrepareVo.orderDto.productIndex}">
                            <img src='data:image:jpg;base64,${paymentReviewPrepareVo.orderDto.imageBase64}' alt="옷 이미지" width="130" height="130">
                        </a>
                    </td>
                    <td class="options-wrap">
                        <div class="productName-wrap">
                            ${paymentReviewPrepareVo.orderDto.productName}
                        </div>
                        <div>
                            <span class="size-title">사이즈</span>
                            <b>${paymentReviewPrepareVo.orderDto.size}</b>
                        </div>
                        <div>
                            <span class="color-title">색상</span>
                            <b>${paymentReviewPrepareVo.orderDto.color}</b>
                        </div>
                    </td>
                    <td class="price-wrap price">${paymentReviewPrepareVo.orderDto.orderSubtotalPrice}</td>
                    <td>${paymentReviewPrepareVo.orderDto.orderCount}</td>
                </tr>
        </table>
    </div>

    <form id="review-form">
        <div class="review_rating">
            <table>
                <tr>
                    <th>별점</th>
                    <td>
                        <span class="rating">
                            <!-- 해당 별점을 클릭하면 해당 별과 그 왼쪽의 모든 별의 체크박스에 checked 적용 -->
                            <!--별 참고 사이트 : https://blogpack.tistory.com/695#google_vignette-->
                            <input type="checkbox" name="ratingOptions" id="rating1" value="1" class="rate_radio" title="1점">
                            <label for="rating1">
                                <i class="fas fa-star"></i>
                            </label>
                            <input type="checkbox" name="ratingOptions" id="rating2" value="2" class="rate_radio" title="2점">
                            <label for="rating2">
                                <i class="fas fa-star"></i>
                            </label>
                            <input type="checkbox" name="ratingOptions" id="rating3" value="3" class="rate_radio" title="3점" >
                            <label for="rating3">
                                <i class="fas fa-star"></i>
                            </label>
                            <input type="checkbox" name="ratingOptions" id="rating4" value="4" class="rate_radio" title="4점">
                            <label for="rating4">
                                <i class="fas fa-star"></i>
                            </label>
                            <input type="checkbox" name="ratingOptions" id="rating5" value="5" class="rate_radio" title="5점">
                            <label for="rating5">
                                <i class="fas fa-star"></i>
                            </label>
                        </span>
                    </td>
                </tr>
                <tr>
                    <th>구매후기</th>
                    <td>
                        <label>
                            <textarea id="input-review" name="review" rows="7" cols="116"></textarea>
                        </label>
                    </td>
                </tr>
            </table>
            <input class="review-button-wrap" type="submit" value="리뷰 작성">
        </div>
    </form>
</main>
<%@ include file="/WEB-INF/views/parts/footer.jsp" %>
</body>
</html>