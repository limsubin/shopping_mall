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
    <link rel="stylesheet" href="/resources/stylesheets/shop/search.css">
</head>
<body>
<%@ include file="/WEB-INF/views/parts/header.jsp" %>
<main class="search-wrap">
    <div class="title">
        <i class="far fa-search"></i>
        <span id="keyword-wrap">검색어 : </span>
    </div>

    <div class="search-list">
        <c:if test="${searchVo.maxPage == 0}">
            <div class="warning-message">
                <i class="fal fa-exclamation-circle"></i>
                <span>검색한 상품이 없습니다.</span>
            </div>
        </c:if>
        <c:forEach var="search" items="${searchVo.productDtos}">
            <ul>
                <a href="/shop/read/${search.index}">
                    <li>
                        <img src='data:image:jpg;base64,${search.imageBase64}' alt="옷 이미지" width="240" height="250">
                    </li>
                    <li class="productName">${search.productName}</li>
                    <li class="price">${search.productPrice}</li>
                </a>
            </ul>
        </c:forEach>
    </div>

    <div class="pagination">
        <c:if test="${searchVo.page > 1}">
            <span>
                <a href="/shop/search/1" target="_self">&lt;&lt;</a>
            </span>
        </c:if>
        <c:forEach var="i" begin="${searchVo.leftPage}" end="${searchVo.rightPage}" step="1">
            <c:if test="${i == searchVo.page}">
                <span class="${i == searchVo.page ? "active" : ""}">
                    <a>${i}</a>
                </span>
            </c:if>
            <c:if test="${i != searchVo.page}">
                <span>
                    <a href="/shop/search/${i}" target="_self">${i}</a>
                </span>
            </c:if>
        </c:forEach>
        <c:if test="${searchVo.page < searchVo.maxPage}">
            <span>
                <a href="/shop/search/${searchVo.maxPage}" target="_self">&gt;&gt;</a>
            </span>
        </c:if>
    </div>
</main>
<%@ include file="/WEB-INF/views/parts/footer.jsp" %>
<script>
    const keywordWrapElement = window.document.getElementById("keyword-wrap");
    const URLSearch = new URLSearchParams(location.search);
    let keyword = URLSearch.get("keyword");
    keywordWrapElement.innerText = keyword;
</script>
</body>
</html>