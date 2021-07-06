<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>좋아요</title>
    <%@ include file="/WEB-INF/views/parts/common.cdn.html" %>
    <link rel="stylesheet" href="/resources/stylesheets/shop/like.css">
    <script src="/resources/scripts/class.ajax.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/parts/header.jsp" %>
<main class="like-wrap">
<form id="like-form">
    <div class="myPage-info">
        <div class="userIcon">
            <i class="fad fa-user-circle"></i>
        </div>
        <div class="user">
            <b><%= userDto.getNickname() %></b>님 반갑습니다!
        </div>
        <div class="menu">
            <span>
                <a href="/shop/paymentStatus">주문내역</a>
            </span>
            <% if(userDto.isAdmin()){ %>
            <span>
                <a href="/shop/manager/admin">관리자 페이지</a>
            </span>
            <% } %>
        </div>
    </div>
    <div class="like">
        <div class="title">
            <span>좋아요</span>
            <i class="fas fa-heart-square"></i>
        </div>
        <c:if test="${likePrepareVo.productDtos == []}">
            <div class="warning-message">
                <i class="fal fa-exclamation-circle"></i>
                <span>등록된 좋아요가 없습니다.</span>
            </div>
        </c:if>

        <c:forEach var="likePrepareVo" items="${likePrepareVo.productDtos}">
            <ul>
                <a href="/shop/read/${likePrepareVo.index}">
                    <li class="image">
                        <img src='data:image:jpg;base64,${likePrepareVo.imageBase64}' alt="옷 이미지" width="240" height="250">
                        <div id="like-toggle">
                            <input type="checkbox" name="like" id="like-${likePrepareVo.index}" onclick="check(`${likePrepareVo.index}`)" data-index="${likePrepareVo.index}">
                            <label for="like-${likePrepareVo.index}">
                                <i class="fas fa-heart"></i>
                            </label>
                        </div>
                    </li>
                    <li class="productName">${likePrepareVo.productName}</li>
                    <li class="price">${likePrepareVo.productPrice}</li>

                </a>
            </ul>
        </c:forEach>
    </div>

    <div class="pagination">
        <c:if test="${likePrepareVo.page > 1}">
            <span>
                <a href="/shop/like/1" target="_self">&lt;&lt;</a>
            </span>
        </c:if>
        <c:forEach var="i" begin="${likePrepareVo.leftPage}" end="${likePrepareVo.rightPage}" step="1">
            <c:if test="${i == likePrepareVo.page}">
                <span class="${i == likePrepareVo.page ? "active" : ""}">
                    <a>${i}</a>
                </span>
            </c:if>
            <c:if test="${i != likePrepareVo.page}">
                <span>
                    <a href="/shop/like/${i}" target="_self">${i}</a>
                </span>
            </c:if>
        </c:forEach>
        <c:if test="${likePrepareVo.page < likePrepareVo.maxPage}">
            <span>
                <a href="/shop/like/${likePrepareVo.maxPage}" target="_self">&gt;&gt;</a>
            </span>
        </c:if>
    </div>
</form>
</main>
<%@ include file="/WEB-INF/views/parts/footer.jsp" %>
<script>
    const likeToggleElement = window.document.getElementById("like-toggle");
    const likeInputElement = window.document.querySelectorAll('input[name="like"]');
    const likeFormElement =  window.document.getElementById("like-form");
    let count = 0;
    //참고 : https://kwakkwakkwak.github.io/spring/2017/12/18/Sprng-%EC%A2%8B%EC%95%84%EC%9A%94%EA%B8%B0%EB%8A%A5/

    likeInputElement.forEach(element => {
        element.checked = true;
    });

    function check(choice){
        let choiceElement = window.document.getElementById("like-"+choice);
        let formData = new FormData();
        let iElement = choiceElement.nextElementSibling.firstElementChild;

        if(choiceElement.checked){
            formData.append('isLike', true);
            iElement.classList.replace("far", "fas");
        }else{
            formData.append('isLike', false);
            iElement.classList.replace("fas", "far");
        }
        formData.append("productIndex", choiceElement.dataset.index);

        const callback = function (resp){
            const respJson = JSON.parse(resp);
            switch (respJson["result"]) {
                case "success":
                    console.log("like success");
                    break;
                case "failure":
                    alert("상품 추가에 실패하였습니다.");
                    break;
                case "already_in_cart":
                    alert("이미 장바구니에 추가되어 있습니다.");
                    location.reload();
                    break;
                default:
                    alert("알 수 없는 오류가 발생하였습니다.");
                    break;
            }
        };

        const fallback = function (status){
            alert('예상치 못한 오류가 발생하였습니다. 관리자에게 문의해주십시오.');
        };

        Ajax.request('POST', '/shop/like', callback, fallback, formData);
        return false;
    }
</script>
</body>
</html>