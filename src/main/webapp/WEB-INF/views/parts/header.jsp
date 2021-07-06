<%@ page language="java" pageEncoding="UTF-8" contentType="text/html" trimDirectiveWhitespaces="true" %>
<%@ page import="com.lsb.portfolio.shopping_mall.enums.user.UserLoginResult" %>
<%@ page import="com.lsb.portfolio.shopping_mall.dtos.user.UserDto" %>
<%@ page import="com.lsb.portfolio.shopping_mall.dtos.cart.CartTotalCountDto" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    Object valueObject = session.getAttribute(UserDto.CLASS_NAME);
    UserDto userDto = valueObject == null ? null : (UserDto) valueObject;

    Object cartTotalCountObject = session.getAttribute(UserDto.CART_TOTAL_COUNT_NAME);
    CartTotalCountDto cartTotalCountDto = valueObject == null ? null : (CartTotalCountDto) cartTotalCountObject;
%>

<header>
    <%-- 로그인, 회원가입 --%>
    <div class="login-wrap">
        <% if(userDto != null){ %>
            <div class="login">
                <div class="login-menu">
                    <div class="title">
                        <span><%= userDto.getNickname() %>님 환영합니다.</span>
                        <i class="far fa-caret-circle-down"></i>
                    </div>
                    <div class="content">
                        <a href="/user/logout">로그아웃</a>
                        <a href="/shop/paymentStatus">주문 내역</a>

                        <% if(userDto.isAdmin()){ %>
                            <a href="/shop/manager/admin/1">관리자 페이지</a>
                        <% } %>
                    </div>
                </div>
            </div>
        <% }else{ %>
            <div class="logout">
                <span>
                    <a href="/user/terms">회원가입</a>
                </span>
                <span>
                    <a class="login-btn"  href="/user/login">로그인</a>
                </span>
            </div>
        <% } %>
    </div>

    <div class="top-wrap">
        <%-- 로고 --%>
        <div>
            <a href="/shop">
                <img src="/resources/images/logo.png" alt="로고" width="250" height="80">
            </a>
        </div>

        <%-- 검색 --%>
        <div class="search-box">
            <form action="/shop/search" method="get">
                <label>
                    <div>
                        <input type="search" id="search" maxlength="10" name="keyword">
                        <span>
                            <i class="far fa-search"></i>
                        </span>
                    </div>
                </label>
                <input type="submit" value="검색" class="search">
            </form>
        </div>

        <%-- 좋아요, 장바구니 --%>
        <div class="my-menu">
            <div>
                <a href="/shop/like">
                    <i class="fal fa-heart i-wrap"></i>
                    <div class="good">좋아요</div>
                </a>
            </div>

            <div>
                <a href="/shop/cart">
                    <i class="fal fa-shopping-cart i-wrap"></i>
                    <div class="cart-count">
                        <% if(userDto != null){ %>
                            <span><%= cartTotalCountDto.getCartTotalCount() %></span>
                        <% }else{ %>
                            <span>0</span>
                        <% } %>
                    </div>
                    <div class="cart">장바구니</div>
                </a>
            </div>
        </div>
    </div>

    <%-- 메인 메뉴 --%>
    <div class="allMenu-warp">
        <ul>
            <li>
                <button id="btn-allMenu" type="button" name="btn-allMenu">
                    <i class="fas fa-bars"></i>
                </button>
            </li>
            <li>
                <div>
                    <a href="/shop/list/best">베스트</a>
                </div>
            </li>
            <li>
                <div>
                    <a href="/shop/list/new">신상</a>
                </div>

            </li>
            <li>
                <div class="text">
                    <a href="/shop/list/outer">아우터</a>
                </div>
                <ul class="submenu">
                    <li>
                        <a href="/shop/list/outer?sub=vest">가디건/조끼</a>
                    </li>
                    <li>
                        <a href="/shop/list/outer?sub=jumper">야상/점퍼</a>
                    </li>
                    <li>
                        <a href="/shop/list/outer?sub=jacket">자켓/코트</a>
                    </li>
                    <li>
                        <a href="/shop/list/outer?sub=padding">패딩</a>
                    </li>
                    <li>
                        <a href="/shop/list/outer?sub=fleece">플리스</a>
                    </li>
                </ul>
            </li>
            <li>
                <div>
                    <a href="/shop/list/consultation">상의</a>
                </div>
                <ul class="submenu">
                    <li>
                        <a href="/shop/list/consultation?sub=longTShirt">긴팔티셔츠</a>
                    </li>
                    <li>
                        <a href="/shop/list/consultation?sub=manToMan">맨투맨</a>
                    </li>
                    <li>
                        <a href="/shop/list/consultation?sub=hood">후드</a>
                    </li>
                    <li>
                        <a href="/shop/list/consultation?sub=shortSleeve">반팔/민소매티셔츠</a>
                    </li>
                    <li>
                        <a href="/shop/list/consultation?sub=knit">니트</a>
                    </li>
                </ul>
            </li>
            <li>
                <div>
                    <a href="/shop/list/training">트레이닝</a>
                </div>

            </li>
            <li>
                <div>
                    <a href="/shop/list/basic">베이직</a>
                </div>

            </li>
            <li>
                <div>
                    <a href="/shop/list/ouePiece">원피스</a>
                </div>
            </li>
            <li>
                <div>
                    <a href="/shop/list/skirt">스커트</a>
                </div>
            </li>
            <li>
                <div>
                    <a href="/shop/list/pants">팬츠</a>
                </div>
                <ul class="submenu">
                    <li>
                        <a href="/shop/list/pants?sub=jeans">청바지</a>
                    </li>
                    <li>
                        <a href="/shop/list/pants?sub=longPants">롱팬츠</a>
                    </li>
                    <li>
                        <a href="/shop/list/pants?sub=leggings">레깅스</a>
                    </li>
                    <li>
                        <a href="/shop/list/pants?sub=shortPants">숏팬츠</a>
                    </li>
                </ul>
            </li>
            <li>
                <div>
                    <a href="/shop/list/bag">가방</a>
                </div>
                <ul class="submenu">
                    <li>
                        <a href="/shop/list/bag?sub=backpack">백팩/스쿨백</a>
                    </li>
                    <li>
                        <a href="/shop/list/bag?sub=cross">크로스/토트백</a>
                    </li>
                </ul>
            </li>
            <li>
                <div>
                    <a href="/shop/list/shoes">신발</a>
                </div>
                <ul class="submenu">
                    <li>
                        <a href="/shop/list/shoes?sub=sneakers">운동화/단화</a>
                    </li>
                    <li>
                        <a href="/shop/list/shoes?sub=shoes">구두/워커</a>
                    </li>
                    <li>
                        <a href="/shop/list/shoes?sub=sandals">샌들/슬리피/장화</a>
                    </li>
                </ul>
            </li>
            <li>
                <div>
                    <a href="/shop/list/accessories">악세서리</a>
                </div>
                <ul class="submenu">
                    <li>
                        <a href="/shop/list/accessories?sub=jewelry">주얼리</a>
                    </li>
                    <li>
                        <a href="/shop/list/accessories?sub=hat">모자/벨트</a>
                    </li>
                    <li>
                        <a href="/shop/list/accessories?sub=socks">양말/스타킹</a>
                    </li>
                </ul>
            </li>
        </ul>

        <div class="allMenu-cover">
            <div class="box-category">
                <div class="box-list">
                    <div>
                        <button id="btn-allMenu-cover" type="button" name="btn-allMenu-cover">
                            <i class="far fa-times"></i>
                        </button>
                    </div>
                </div>
                <div class="box-list">
                    <div class="best-text">
                        <a href="/shop/list/best">베스트</a>
                    </div>
                </div>
                <div class="box-list">
                    <div class="new-text">
                        <a href="/shop/list/new">신상</a>
                    </div>
                </div>
                <div class="box-list">
                    <div>
                        <a href="/shop/list/outer">아우터</a>
                    </div>
                    <ul>
                        <li>
                            <a href="/shop/list/outer?sub=vest">가디건/조끼</a>
                        </li>
                        <li>
                            <a href="/shop/list/outer?sub=jumper">야상/점퍼</a>
                        </li>
                        <li>
                            <a href="/shop/list/outer?sub=jacket">자켓/코트</a>
                        </li>
                        <li>
                            <a href="/shop/list/outer?sub=padding">패딩</a>
                        </li>
                        <li>
                            <a href="/shop/list/outer?sub=fleece">플리스</a>
                        </li>
                    </ul>
                </div>
                <div class="box-list">
                    <div>
                        <a href="/shop/list/consultation">상의</a>
                    </div>
                    <ul class="submenu">
                        <li>
                            <a href="/shop/list/consultation?sub=longTShirt">긴팔티셔츠</a>
                        </li>
                        <li>
                            <a href="/shop/list/consultation?sub=manToMan">맨투맨</a>
                        </li>
                        <li>
                            <a href="/shop/list/consultation?sub=hood">후드</a>
                        </li>
                        <li>
                            <a href="/shop/list/consultation?sub=shortSleeve">반팔/민소매티셔츠</a>
                        </li>
                        <li>
                            <a href="/shop/list/consultation?sub=knit">니트</a>
                        </li>
                    </ul>
                </div>
                <div class="box-list">
                    <div>
                        <a href="/shop/list/training">트레이닝</a>
                    </div>
                </div>
                <div class="box-list">
                    <div>
                        <a href="/shop/list/basic">베이직</a>
                    </div>
                </div>
                <div class="box-list">
                    <div>
                        <a href="/shop/list/ouePiece">원피스</a>
                    </div>
                </div>
                <div class="box-list">
                    <div>
                        <a href="/shop/list/skirt">스커트</a>
                    </div>
                </div>
                <div class="box-list">
                    <div>
                        <a href="/shop/list/pants">팬츠</a>
                    </div>
                    <ul class="submenu">
                        <li>
                            <a href="/shop/list/pants?sub=jeans">청바지</a>
                        </li>
                        <li>
                            <a href="/shop/list/pants?sub=longPants">롱팬츠</a>
                        </li>
                        <li>
                            <a href="/shop/list/pants?sub=leggings">레깅스</a>
                        </li>
                        <li>
                            <a href="/shop/list/pants?sub=shortPants">숏팬츠</a>
                        </li>
                    </ul>
                </div>
                <div class="box-list">
                    <div>
                        <a href="/shop/list/bag">가방</a>
                    </div>
                    <ul class="submenu">
                        <li>
                            <a href="/shop/list/bag?sub=backpack">백팩/스쿨백</a>
                        </li>
                        <li>
                            <a href="/shop/list/bag?sub=cross">크로스/토트백</a>
                        </li>
                    </ul>
                </div>
                <div class="box-list">
                    <div>
                        <a href="/shop/list/shoes">신발</a>
                    </div>
                    <ul class="submenu">
                        <li>
                            <a href="/shop/list/shoes?sub=sneakers">운동화/단화</a>
                        </li>
                        <li>
                            <a href="/shop/list/shoes?sub=shoes">구두/워커</a>
                        </li>
                        <li>
                            <a href="/shop/list/shoes?sub=sandals">샌들/슬리피/장화</a>
                        </li>
                    </ul>
                </div>
                <div class="box-list">
                    <div>
                        <a href="/shop/list/accessories">악세서리</a>
                    </div>
                    <ul class="submenu">
                        <li>
                            <a href="/shop/list/accessories?sub=jewelry">주얼리</a>
                        </li>
                        <li>
                            <a href="/shop/list/accessories?sub=hat">모자/벨트</a>
                        </li>
                        <li>
                            <a href="/shop/list/accessories?sub=socks">양말/스타킹</a>
                        </li>
                    </ul>
                    </li>
                </div>
            </div>
        </div>
    </div>
</header>