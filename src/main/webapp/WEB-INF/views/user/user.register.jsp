<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>로그인</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
    <link rel="stylesheet" href="/resources/stylesheets/common.css">
    <link rel="stylesheet" href="/resources/stylesheets/user/register.css">
</head>
<body>
<%@ include file="/WEB-INF/views/parts/header.jsp" %>
<main class="register-wrap">
    <h1>JOIN US</h1>
    <form method="post" class="register-form">
        <table>
            <tr>
                <th>이메일</th>
                <td>
                    <input type="email" name="email" maxlength="50" minlength="0" placeholder="이메일" autofocus>
                </td>
            </tr>
            <tr>
                <th>비밀번호</th>
                <td>
                    <input type="password" name="password" maxlength="128" minlength="0" placeholder="6-20자 : 영문, 숫자, 특수문자 조합">

                </td>
            </tr>
            <tr>
                <th>비밀번호 확인</th>
                <td>
                    <input type="password" name="passwordCheck" maxlength="128" minlength="0" placeholder="6-20자 : 영문, 숫자, 특수문자 조합">
                </td>
            </tr>
<%--            <tr>--%>
<%--                <th>별명</th>--%>
<%--                <td>--%>
<%--                    <input type="text" name="nickname" maxlength="10" minlength="0" placeholder="별명">--%>
<%--                </td>--%>
<%--            </tr>--%>
            <tr>
                <th>이름</th>
                <td>
                    <input type="text" name="name"  maxlength="10" minlength="0" placeholder="이름">
                </td>
            </tr>
            <tr>
                <th>휴대전화</th>
                <td>
                    <select name="contactFirst" id="tel-select">
                        <option value="010" selected>010</option>
                        <option value="011">011</option>
                        <option value="016">016</option>
                        <option value="017">017</option>
                    </select>
                    &#45;
                    <input type="text" maxlength="4" name="contactSecond">
                    &#45;
                    <input type="text" maxlength="4" name="contactThird">
<%--                    <input type="text" name="contact" pattern="^(010)\-([0-9]{4})\-([0-9]{4})$" maxlength="15" minlength="0" placeholder="연락처">--%>
                </td>
            </tr>
            <tr>
                <th>생일</th>
                <td>
                    <select name="birthYear" id="birthYear-select">
                        <option value="" selected>년</option>
                        <c:forEach var="i" begin="1920" end="2021">
                            <option value="${i}">${i}</option>
                        </c:forEach>
                    </select>

                    <select name="birthMonth" id="birthMonth-select">
                        <option value="" selected>월</option>
                        <c:forEach var="i" begin="1" end="12">
                            <option value="${i}">${i}</option>
                        </c:forEach>
                    </select>

                    <%-- TODO 월에 따라서 일이 바꿔야함 --%>
                    <select name="birthDay" id="birthDay-select">
                        <option value="" selected>일</option>
                        <c:forEach var="i" begin="1" end="30">
                            <option value="${i}">${i}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <th>주소</th>
                <td>
                    <input type="text" name="addressPost" class="addressPost" placeholder="우편번호" readonly>
                    <%-- 이거 클릭하면 클릭 이벤트 발생해서 주소 api 나오게 하기 --%>
                    <input type="button" value="우편번호 찾기" class="address-button"><br>
                    <input type="text" id="address" placeholder="주소"><br>
                    <input type="text" id="addressDetails" placeholder="상세주소">
                </td>
            </tr>
        </table>
        <div>
            <input type="submit" value="가입" class="register-button">
            <input type="button" value="취소" class="cancel-button">
        </div>
    </form>

</main>
<%@ include file="/WEB-INF/views/parts/footer.jsp" %>
</body>
</html>