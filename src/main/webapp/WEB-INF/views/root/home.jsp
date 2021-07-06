<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>로그인</title>
    <%@ include file="/WEB-INF/views/parts/common.cdn.html" %>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
    <link rel="stylesheet" href="/resources/stylesheets/root/home.css">
    <script src="/resources/scripts/root/home.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/parts/header.jsp" %>
<main class="home-wrap">
    <div class="page-wrapper" style="position:relative;">
        <!--page slider -->
        <div class="post-slider">
            <i class="fas fa-chevron-left prev"></i>  <%--왼쪽 방향 버튼--%>
            <i class="fas fa-chevron-right next"></i>   <%--오른쪽 방향 버튼--%>
            <div class="post-wrapper">
                <div class="post">
                    <img src="/resources/images/imageSlider/imageSlider1.jpg" class="slider-image">
                </div>
                <div class="post">
                    <img src="/resources/images/imageSlider/imageSlider2.jpg" class="slider-image">
                </div>
                <div class="post">
                    <img src="/resources/images/imageSlider/imageSlider3.gif" class="slider-image">
                </div>
                <div class="post">
                    <img src="/resources/images/imageSlider/imageSlider4.jpg" class="slider-image">
                </div>
            </div>
        </div>
        <!--post slider-->
    </div>

    <div class="container">
        <div class="home">
            <div class="title">
                <span>전체 상품</span>
                <i class="fas fa-bags-shopping"></i>
            </div>

            <c:if test="${allProductVo.maxPage == 0}">
                <div class="list-message">등록된 상품이 없습니다.</div>
            </c:if>

            <c:forEach var="allProduct" items="${allProductVo.productDtos}" varStatus="status">
                <ul>
                    <a href="/shop/read/${allProduct.index}">
                        <li>
                            <img src='data:image:jpg;base64,${allProduct.imageBase64}' alt="옷 이미지" width="240" height="250">
                        </li>
                        <li class="nickname">${allProductVo.nicknameArray[status.index]}</li>
                        <li class="productName">${allProduct.productName}</li>
                        <li class="price">${allProduct.productPrice}</li>
                    </a>
                </ul>
            </c:forEach>
        </div>

        <div class="pagination">
            <c:if test="${allProductVo.page > 1}">
                <span>
                    <a href="/shop/1" target="_self">&lt;</a>
                </span>
            </c:if>
            <c:forEach var="i" begin="${allProductVo.leftPage}" end="${allProductVo.rightPage}" step="1">
                <c:if test="${i != 0}">
                    <c:if test="${i == allProductVo.page}">
                        <span class="${i == allProductVo.page ? "active" : ""}">
                            <a>${i}</a>
                        </span>
                    </c:if>
                    <c:if test="${i != allProductVo.page}">
                        <span>
                            <a href="/shop/${i}" target="_self">${i}</a>
                        </span>
                    </c:if>
                </c:if>
            </c:forEach>
            <c:if test="${allProductVo.page < allProductVo.maxPage}">
                <span>
                    <a href="/shop/${allProductVo.maxPage}" target="_self">&gt;</a>
                </span>
            </c:if>
        </div>
    </div>
</main>
<%@ include file="/WEB-INF/views/parts/footer.jsp" %>
</body>
</html>