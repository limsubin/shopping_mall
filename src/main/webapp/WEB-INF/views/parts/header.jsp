<%@ page language="java" pageEncoding="UTF-8" contentType="text/html" trimDirectiveWhitespaces="true" %>

<header>
    <div class="login-wrap">
        <ul>
            <li>
                <a href="/user/register">회원가입</a>
            </li>
            <li>
                <a class="login-btn"  href="/user/login">로그인</a>
            </li>
        </ul>
    </div>

    <div class="top-wrap">
        <div>
            <a href="/">
                <img src="/resources/images/logo.png" alt="로고" width="130" height="40">
            </a>

            <div class="search-box">
                <div>
                    <input type="search" id="search" />
                    <span>
                        <i class="far fa-search"></i>
                    </span>
                </div>
            </div>

            <div class="my-menu">
                <div>
                    <i class="fal fa-heart i-wrap"></i>
                    <div class="good">좋아요</div>
                </div>
                <div>
                    <i class="fal fa-shopping-cart i-wrap"></i>
                    <div class="cart-count">
                        <span>0</span>
                    </div>
                    <div class="cart">장바구니</div>
                </div>
            </div>

            <div class="rank-box">
                <div class="rank-title">
                    테스트
                    <i class="fas fa-sort-down"></i>
                </div>
            </div>
        </div>
    </div>

    <div class="allMenu-warp">
        <ul>
            <li>
                <i class="fas fa-bars"></i>
            </li>
            <li>
                <div>
                    <a href="">베스트</a>
                </div>
            </li>
            <li>
                <div>
                    <a href="">신상</a>
                </div>

            </li>
            <li>
                <div class="text">
                    <a href="">아우터</a>
                </div>
                <ul class="submenu">
                    <li>
                        <a href="">가디건/조끼</a>
                    </li>
                    <%--TODO li 서브 메뉴에 a태그 넣기--%>
                    <li>야상/점퍼</li>
                    <li>자켓/코트</li>
                    <li>패딩</li>
                    <li>플리스</li>
                </ul>
            </li>
            <li>
                <div>
                    <a href="">상의</a>
                </div>
                <ul class="submenu">
                    <li>긴팔티셔츠</li>
                    <li>맨투맨</li>
                    <li>후드</li>
                    <li>반팔/미소매티셔츠</li>
                    <li>니트</li>
                </ul>
            </li>
            <li>
                <div>
                    <a href="">베스트</a>
                </div>
            </li>
            <li>
                <div>
                    <a href="">셔츠/블라우스</a>
                </div>

            </li>
            <li>
                <div>
                    <a href="">트레이닝</a>
                </div>

            </li>
            <li>
                <div>
                    <a href="">베이직</a>
                </div>

            </li>
            <li>
                <div>
                    <a href="">원피스</a>
                </div>
            </li>
            <li>
                <div>
                    <a href="">스커트</a>
                </div>

            </li>
            <li>
                <div>
                    <a href="">팬츠</a>
                </div>
                <ul class="submenu">
                    <li>청바지</li>
                    <li>롱팬츠</li>
                    <li>면바지</li>
                    <li>슬랙스</li>
                    <li>레깅스</li>
                    <li>숏팬츠</li>
                </ul>
            </li>
            <li>
                <div>
                    <a href="">가방</a>
                </div>
                <ul class="submenu">
                    <li>백팩/스쿨백</li>
                    <li>크로스/토트백</li>
                </ul>
            </li>
            <li>
                <div>
                    <a href="">신발</a>
                </div>
                <ul class="submenu">
                    <li>운동화/단화</li>
                    <li>구두/워커</li>
                    <li>샌들/슬리피/장화</li>
                </ul>
            </li>
            <li>
                <div>
                    <a href="">악세서리</a>
                </div>
                <ul class="submenu">
                    <li>주얼리</li>
                    <li>모자/벨트</li>
                    <li>양말/스타킹</li>
                </ul>
            </li>
        </ul>
    </div>
</header>