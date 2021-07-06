<!--참고 : https://kuzuro.blogspot.com/2018/10/7.html-->
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>관리자 페이지</title>
    <%@ include file="/WEB-INF/views/parts/common.cdn.html" %>
    <link rel="stylesheet" href="/resources/stylesheets/shop/manager/admin.css">
    <script src="/resources/scripts/shop/manager/admin.js"></script>
    <!--<script src="/resources/scripts/class.ajax.js"></script>-->
</head>
<body>
<%@ include file="/WEB-INF/views/parts/header.jsp" %>
<main class="admin-wrap">
    <div class="myPage-info">
        <div class="userIcon">
            <i class="fad fa-user-circle"></i>
        </div>
        <div class="user">
            <b>${adminListVo.userDto.nickname}님이 등록한 상품 리스트</b>
        </div>
        <div class="menu">
            <span class="create-button">
                <a href="/shop/manager/create">상품 등록</a>
            </span>
        </div>
    </div>

    <div class="admin-list">
        <table>
            <thead class="head">
            <tr class="title-wrap">
                <th>No.</th>
                <th colspan="2">주문정보</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="adminList" items="${adminListVo.productDtos}" varStatus="loop">
                    <tr>
                        <c:if test="${adminListVo.maxPage == 0}">
                            <td class="list-message">카테고리에 등록된 상품이 없습니다.</td>
                        </c:if>
                        <td class="number-wrap"></td>
                        <td>
                            <a href="/shop/read/${adminList.index}">
                                <img src='data:image:jpg;base64,${adminList.imageBase64}' alt="옷 이미지" width="130" height="130">
                            </a>
                        </td>
                        <td class="productInfo-wrap">
                            <div class="productName-wrap">${adminList.productName}</div>
                            <br>
                            <div class="price-wrap price">${adminList.productPrice}</div>
                            <div class="btn-group">
                                <span class="edit-button">
                                    <i class="fas fa-pen"></i>
                                    <a href="/shop/manager/edit/${adminList.index}">수정</a>
                                </span>
                                    <span class="delete-button">
                                    <i class="fas fa-trash"></i>
                                    <a href="/shop/manager/delete/${adminList.productCategory}/${adminList.index}">삭제</a>
                                </span>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="pagination">
            <c:if test="${adminListVo.page > 1}">
                <span>
                    <a href="/shop/manager/admin/1" target="_self">&lt;&lt;</a>
                </span>
                    </c:if>
                    <c:forEach var="i" begin="${adminListVo.leftPage}" end="${adminListVo.rightPage}" step="1">
                        <c:if test="${i != 0}">
                            <c:if test="${i == adminListVo.page}">
                                <span class="${i == adminListVo.page ? "active" : ""}">
                                    <a>${i}</a>
                                </span>
                            </c:if>
                            <c:if test="${i != adminListVo.page}">
                                <span>
                                    <a href="/shop/manager/admin/${i}" target="_self">${i}</a>
                                </span>
                            </c:if>
                        </c:if>
                    </c:forEach>
                    <c:if test="${adminListVo.page < adminListVo.maxPage}">
                <span>
                    <a href="/shop/manager/admin/${adminListVo.maxPage}" target="_self">&gt;&gt;</a>
                </span>
            </c:if>
        </div>
    </div>
</main>
<%@ include file="/WEB-INF/views/parts/footer.jsp" %>
</body>
</html>