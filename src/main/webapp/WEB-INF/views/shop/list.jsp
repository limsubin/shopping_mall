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
    <link rel="stylesheet" href="/resources/stylesheets/shop/list.css">
    <script src="/resources/scripts/shop/list.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/parts/header.jsp" %>
<main class="list-wrap">
    <div class="title">
        <c:forEach var="category" items="${listVo.categoryDtos}">
            <c:if test="${not loop_flag }">
                <c:if test="${listVo.categoryCode.equals(category.code)}">
                    <a href="/shop/list/${listVo.categoryCode}">${category.name}</a>
                    <c:set var="loop_flag" value="true" />
                </c:if>
            </c:if>
        </c:forEach>
    </div>

    <div class="submenu">
        <c:forEach var="subCategory" items="${listVo.subCategoryDtos}">
            <c:if test="${listVo.categoryCode.equals(subCategory.categoryCode)}" >
                <span class="${subCategory.code}">
                    <a href="/shop/list/${listVo.categoryCode}?sub=${subCategory.code}">${subCategory.name}</a>
                </span>
            </c:if>
        </c:forEach>
    </div>

    <div class="list-content">
        <c:if test="${listVo.maxPage == 0}">
            <div class="warning-message">
                <i class="fal fa-exclamation-circle"></i>
                <span>카테고리에 등록된 상품이 없습니다.</span>
            </div>
        </c:if>

        <c:forEach var="list" items="${listVo.lists}" varStatus="status">
            <ul>
                <a href="/shop/read/${list.index}">
                    <li>
                       <img src='data:image:jpg;base64,${list.imageBase64}' alt="옷 이미지" width="240" height="250">
                    </li>
                    <li class="nickname">${listVo.nicknameArray[status.index]}</li>
                    <li class="productName">${list.productName}</li>
                    <li class="price">${list.productPrice}</li>
                </a>
            </ul>
        </c:forEach>
    </div>

    <div class="pagination">
        <c:if test="${listVo.page > 1}">
            <span>
                <a href="/shop/list/${listVo.categoryCode}/1" target="_self">&lt;&lt;</a>
            </span>
        </c:if>
        <c:forEach var="i" begin="${listVo.leftPage}" end="${listVo.rightPage}" step="1">
            <c:if test="${i != 0}">
                <c:if test="${i == listVo.page}">
                    <span class="${i == listVo.page ? "active" : ""}">
                        <a>${i}</a>
                    </span>
                </c:if>
                <c:if test="${i != listVo.page}">
                    <span>
                        <a href="/shop/list/${listVo.categoryCode}/${i}" target="_self">${i}</a>
                    </span>
                </c:if>
            </c:if>
        </c:forEach>
        <c:if test="${listVo.page < listVo.maxPage}">
            <span>
                <a href="/shop/list/${listVo.categoryCode}/${listVo.maxPage}" target="_self">&gt;&gt;</a>
            </span>
        </c:if>
    </div>
</main>
<%@ include file="/WEB-INF/views/parts/footer.jsp" %>
</body>
</html>