<%@ page import="com.lsb.portfolio.shopping_mall.enums.shop.ReviewResult" %>
<%@ page import="com.lsb.portfolio.shopping_mall.dtos.user.UserDto" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>리스트</title>
    <%@ include file="/WEB-INF/views/parts/common.cdn.html" %>
    <link rel="stylesheet" href="/resources/stylesheets/shop/read.css">
    <script src="/resources/scripts/shop/read.js"></script>
    <script src="/resources/scripts/class.ajax.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/parts/header.jsp" %>
<main class="read-wrap">
<form id="read-form">
    <div class="read-top">
        <div class="image">
            <img src='data:image:jpg;base64,${readVo.productDto.imageBase64}' alt="옷 이미지">
        </div>
        <div class="prod-buy">
            <div class="nickname">${readVo.userDto.nickname}</div>
            <div class="title">
            <span class="name">
                ${readVo.productDto.productName}
            </span>

                <span id="like-toggle">
                <input type="checkbox" name="like" id="like" data-index="${readVo.productIndex}" ${isLike ? "checked" : ""}>
                <label for="like">
                    <i class="${isLike ? "fas" : "far"} fa-heart"></i>
                    <%--<i class="${isLike ? "fas" : "fal"} fa-heart-circle"></i>--%>
                </label>
            </span>
                <div>
                    <i class="far fa-eye"></i>
                    <span>${readVo.productDto.productView}</span>
                </div>
            </div>
            <div class="price">${readVo.productDto.productPrice}원</div>
            <div class="delivery">
                <i class="fas fa-truck"></i>
                <span>무료배송</span>
            </div>

            <div>
                <span>사이즈</span>
                <select name="sizes">
                    <option selected disabled>사이즈 선택</option>
                    <c:forEach var="size" items="${sizes}">
                        <option value="${size.index}">${size.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <span>색상</span>
                <select name="colors">
                    <option selected disabled>색상 선택</option>
                </select>
            </div>

            <div class="order-list"></div>
            <span class="cartTotalPrice-title">총 결제 금액 </span>
            <span data-cart-total-price="0" class="totalPrice">0</span>
            <div>
                <input type="submit" id="cart-button" class="object-button prop-dark" value="장바구니">
                <input type="submit" id="buy-button" class="object-button prop-purple" value="구매하기">
            </div>

            <div class="admin-read">
                <c:if test="${user.index.equals(readVo.productDto.memberIndex)}">
                    <a href="/shop/manager/edit/${readVo.productDto.index}">수정</a>
                    <span>|</span>
                    <a href="/shop/manager/delete/${readVo.productDto.productCategory}/${readVo.productDto.index}" id="delete">삭제</a>
                </c:if>
            </div>
        </div>
    </div>
</form>

<div class="read-content">
    <div class="content-tab">
        <span class="active" id="information">상품 정보</span>
        <span id="review">리뷰 (${reviews.size()})</span>
    </div>

    <div id="content-wrap">
        <div class="product-info">
            ${readVo.productDto.productContent}
        </div>
        <div class="product-review">
            <div class="rating-view">
                <div class="average-wrap">
                    <div class="rating-title">사용자 총 평점</div>
                    <div class="rating-content">
                        <div class="rating-average">
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                        </div>
                        <div>
                            <c:set var="sum" value="0" />
                            <c:set var="avg" value="0" />
                            <c:forEach var="reviewAverage" items="${reviews}">
                                <c:set var= "sum" value="${sum + reviewAverage.ratingOptions}"/>
                            </c:forEach>
                            <c:set var= "avg" value="${reviews.size() == 0 ? 0 : (sum / reviews.size())}"/>

                            <span class="average"><c:out value="${avg}"/></span>
                            <span class="averageTotal">/5</span>
                            <input type="hidden" name="ratingAverage" value="<c:out value="${avg}"/>">
                        </div>
                    </div>
                </div>
                <div class="reviewCount-wrap">
                    <div class="rating-title">전체 리뷰 수</div>
                    <div class="rating-content">
                        <i class="fas fa-comment-alt-dots"></i>
                        <div id="reviewTotalCount">${reviews.size()}</div>
                    </div>
                </div>
                <div class="ratingRatio-wrap">
                    <div class="rating-title">평점 비율</div>
                    <div class="rating-content">
                        <c:set var="reviewRatingCount1" value="0" />
                        <c:set var="reviewRatingCount2" value="0" />
                        <c:set var="reviewRatingCount3" value="0" />
                        <c:set var="reviewRatingCount4" value="0" />
                        <c:set var="reviewRatingCount5" value="0" />

                        <c:forEach var="reivew" items="${reviews}">
                            <c:if test="${reivew.ratingOptions == '1'}">
                                <c:set var= "reviewRatingCount1" value="${reviewRatingCount1+1}"/>
                            </c:if>
                            <c:if test="${reivew.ratingOptions == '2'}">
                                <c:set var= "reviewRatingCount2" value="${reviewRatingCount2+1}"/>
                            </c:if>
                            <c:if test="${reivew.ratingOptions == '3'}">
                                <c:set var= "reviewRatingCount3" value="${reviewRatingCount3+1}"/>
                            </c:if>
                            <c:if test="${reivew.ratingOptions == '4'}">
                                <c:set var= "reviewRatingCount4" value="${reviewRatingCount4+1}"/>
                            </c:if>
                            <c:if test="${reivew.ratingOptions == '5'}">
                                <c:set var= "reviewRatingCount5" value="${reviewRatingCount5+1}"/>
                            </c:if>
                        </c:forEach>
                        <div>
                            <div class="reviewRating-count">
                                <c:out value="${reviewRatingCount5}"/>
                            </div>
                            <div class="bar"></div>
                            <div>5점</div>
                        </div>

                        <div>
                            <div class="reviewRating-count">
                                <c:out value="${reviewRatingCount4}"/>
                            </div>
                            <div class="bar"></div>
                            <div>4점</div>
                        </div>

                        <div>
                            <div class="reviewRating-count">
                                <c:out value="${reviewRatingCount3}"/>
                            </div>
                            <div class="bar"></div>
                            <div>3점</div>
                        </div>

                        <div>
                            <div class="reviewRating-count">
                                <c:out value="${reviewRatingCount2}"/>
                            </div>
                            <div class="bar"></div>
                            <div>2점</div>
                        </div>

                        <div>
                            <div class="reviewRating-count">
                                <c:out value="${reviewRatingCount1}"/>
                            </div>
                            <div class="bar"></div>
                            <div>1점</div>
                        </div>
                    </div>
                </div>
            </div>
            <form id="review-form">
                <c:if test="${reviews.size() == 0}">
                    <div class="warning-message">
                        <i class="fal fa-exclamation-circle"></i>
                        작성된 리뷰가 없습니다.
                    </div>
                </c:if>
                <c:forEach var="review" items="${reviews}">
                    <ul>
                        <li class="userIcon">
                            <i class="fad fa-user-circle"></i>
                        </li>
                        <li class="star">
                            <c:forEach  var="i" begin="1" end="${review.ratingOptions}">
                                <i class="fas fa-star" style="color:#F0CA4F"></i>
                            </c:forEach>

                            <c:forEach  var="j" begin="1" end="${5-review.ratingOptions}">
                                <i class="fas fa-star" style="color:#DCDDDE"></i>
                            </c:forEach>
                            <span>${review.ratingOptions}</span>
                        </li>
                        <li class="nickname">${review.nickname}</li>
                        <li class="date">
                            <fmt:formatDate value="${review.reviewDate}" type="date"/>
                        </li>
                        <li class="content">${review.content}</li>
                    </ul>
                </c:forEach>

                <div class="review_rating">
                    <table>
                        <thead>
                            <tr>
                                <th colspan="2">리뷰작성</th>
                            </tr>
                        </thead>
                        <tbody>
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
                                        <textarea id="input-review" name="review" rows="7" cols="150"></textarea>
                                    </label>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <input type="submit" value="리뷰 작성" class="object-button prop-dark">
                </div>
            </form>
        </div>
    </div>
</div>
</main>
<%@ include file="/WEB-INF/views/parts/footer.jsp" %>
<script>
    /*좋아요 등록*/
    const likeToggleElement = window.document.getElementById("like-toggle");
    const likeInputElement = window.document.querySelector('input[name="like"]');

    //참고 : https://kwakkwakkwak.github.io/spring/2017/12/18/Sprng-%EC%A2%8B%EC%95%84%EC%9A%94%EA%B8%B0%EB%8A%A5/
    likeInputElement.addEventListener('click', function (){
        <c:if test="${readVo.userDto == null}">
            alert("로그인 후 좋아요 가 가능합니다.");
            likeInputElement.checked = false;
            location.href = '/user/login';
            return false;
        </c:if>

        <c:if test="${readVo.userDto != null}">
            let formData = new FormData();
            let iElement = likeInputElement.nextSibling.nextSibling.firstChild.nextSibling;
            if(likeInputElement.checked){
                formData.append('isLike', true);
                iElement.classList.replace("far", "fas");
            }else{
                formData.append('isLike', false);
                iElement.classList.replace("fas", "far");
            }
            formData.append("productIndex", likeInputElement.dataset.index);

            const callback = function (resp){
                const respJson = JSON.parse(resp);
                switch (respJson["result"]) {
                    case "success":
                        console.log("like success");
                        break;
                    case "failure":
                        alert("좋아요 추가에 실패하였습니다.");
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
        </c:if>
    });

    /*상품 사이즈 선택시, 색상 요소 생성*/
    let array = new Array();
    //모든 사이즈, 색상 정보 가져옴
    <c:forEach var="optionDetail" items="${optionDetails}">
    array.push(
        {"sizeIndex":"${optionDetail.sizeIndex}",
            "size":"${optionDetail.size}",
            "colorIndex": "${optionDetail.colorIndex}",
            "color":"${optionDetail.color}",
            "premium":"${optionDetail.premium}",
            "stock":"${optionDetail.stock}"}
    );
    </c:forEach>

    const sizeElement = window.document.querySelector("select[name=sizes]");
    const colorElement = window.document.querySelector("select[name=colors]");
    const deleteElement = window.document.getElementById("delete");
    let productPrice = ${readVo.productDto.productPrice};
    //사이즈 선택전에 색상 선택 못하게 막음
    colorElement.disabled = true;

    //'사이즈' 이벤트
    sizeElement.addEventListener('change', function(){
        //색상 안에 있는 <option> 태그 삭제
        if(colorElement.options.length > 1){
            let colorCount = colorElement.length;
            for(let i = 1; i<colorCount; i++){
                colorElement.options[1].remove();
            }
        }

        //색상에 <option> 태그 생성
        for(let i = 0; i<array.length; i++){
            if(sizeElement.value === array[i]["sizeIndex"]){            //선택된 사이즈랑 DB에 저장된 사이즈가 맞는지 비교
                const option = window.document.createElement("option"); //선택된 사이즈에 맞는 색상 <option>값 생성
                option.setAttribute("value", array[i]["colorIndex"])
                if(array[i]["premium"] === "0"){                        //사이즈, 색상에 맞는 추가 금액 표시
                    option.innerText = array[i]["color"];
                }else{
                    option.innerText = array[i]["color"] + "(\+" + array[i]["premium"] +")";
                    option.dataset.premium = array[i]["premium"];
                }
                colorElement.append(option);
            }
        }
        colorElement.disabled = false;
    });

    /*상품 삭제*/
    deleteElement.addEventListener('click', function (){
        if(confirm("해당 상품을 삭제하시겠습니까?")){
            <c:if test="${readVo.productSubDto == null}">
                location.href = '/shop/manager/delete/${readVo.productDto.productCategory}/${readVo.productDto.index}';
            </c:if>
            <c:if test="${readVo.productSubDto != null}">
                location.href = '/shop/manager/delete/${readVo.productDto.productCategory}/${readVo.productDto.index}?sub=${readVo.productSubDto.subCategory}';
            </c:if>
        }
    });
</script>
</body>
</html>