<%@ page import="com.lsb.portfolio.shopping_mall.services.Regex" %>
<%@ page import="com.lsb.portfolio.shopping_mall.enums.user.UserRegisterResult" %>
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
    <%@ include file="/WEB-INF/views/parts/common.cdn.html" %>
    <link rel="stylesheet" href="/resources/stylesheets/user/register.css">
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="/resources/scripts/user/register.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/parts/header.jsp" %>
<main class="register-wrap">
    <h1>JOIN US</h1>
    <form method="post" id="register-form">
        <table>
            <tr>
                <th>이메일</th>
                <td>
                    <input type="email" name="email" maxlength="50" minlength="0" placeholder="이메일" value="${register.email}" data-regex="<%= Regex.User.EMAIL %>" autofocus>
                </td>
            </tr>
            <tr>
                <th>비밀번호</th>
                <td>
                    <input type="password" name="password" maxlength="128" minlength="0" placeholder="비밀번호" data-regex="<%= Regex.User.PASSWORD %>">
                    <div>*6-20자 : 영문, 숫자, 특수문자 조합</div>
                </td>
            </tr>
            <tr>
                <th>비밀번호 확인</th>
                <td>
                    <input type="password" name="passwordCheck" maxlength="128" minlength="0" placeholder="비밀번호 확인" data-regex="<%= Regex.User.PASSWORD %>">
                    <div>*6-20자 : 영문, 숫자, 특수문자 조합</div>
                </td>
            </tr>
            <tr>
                <th>별명</th>
                <td>
                    <input type="text" name="nickname" maxlength="10" minlength="0" data-regex="<%= Regex.User.NICKNAME %>" value="${register.nickname}" placeholder="별명">
                </td>
            </tr>
            <tr>
                <th>이름</th>
                <td>
                    <input type="text" name="name"  maxlength="10" minlength="0" placeholder="이름" data-regex="<%= Regex.User.NAME %>" value="${register.name}">
                </td>
            </tr>
            <tr>
                <th>휴대전화</th>
                <td>
                    <select name="contactFirst" id="tel-select">
                        <option value="010" ${register.contactFirst.equals("010") ? "selected" : ""}>010</option>
                        <option value="011" ${register.contactFirst.equals("011") ? "selected" : ""}>011</option>
                        <option value="016" ${register.contactFirst.equals("016") ? "selected" : ""}>016</option>
                        <option value="017" ${register.contactFirst.equals("017") ? "selected" : ""}>017</option>
                    </select>
                    &#45;
                    <input type="number" maxlength="4" name="contactSecond" data-regex="<%= Regex.User.CONTACT_SECOND %>" value="${register.contactSecond}">
                    &#45;
                    <input type="number" maxlength="4" name="contactThird" data-regex="<%= Regex.User.CONTACT_THIRD %>" value="${register.contactThird}">
                </td>
            </tr>
            <tr>
                <th>생일</th>
                <td>
                    <select name="birthYear" id="birthYear-select" value=""></select>
                    <select name="birthMonth" id="birthMonth-select"></select>
                    <select name="birthDay" id="birthDay-select"></select>
                </td>
            </tr>
            <tr>
                <th>주소</th>
                <td>
                    <input type="text" name="addressPost" id="addressPost" placeholder="우편번호" data-regex="<%= Regex.User.ADDRESS_POST %>" value="${register.addressPost}" readonly>
                    <input type="button" value="우편번호 찾기" id="address-button" class="object-button prop-purple"><br>
                    <input type="text" id="address" name="address" placeholder="주소" data-regex="<%= Regex.User.ADDRESS %>" value="${register.address}"><br>
                    <input type="text" id="addressDetail" name="addressDetail" placeholder="상세주소" data-regex="<%= Regex.User.ADDRESS_DETAILS %>" value="${register.address_detail}">
                </td>
            </tr>
        </table>
        <div>
            <input type="submit" value="가입" id="register-button" class="object-button prop-purple">
            <input type="button" value="취소" id="cancel-button" class="object-button prop-basic">
        </div>
    </form>
</main>
<%@ include file="/WEB-INF/views/parts/footer.jsp" %>
<script>
    ${register.result == UserRegisterResult.DUPLICATE_NICKNAME ? "alert('닉네임이 중복됩니다.다시 한번 확인해주십시오.');" : ""}
    ${register.result == UserRegisterResult.DUPLICATE_EMAIL ? "alert('이메일이 중복됩니다. 다시 한번 확인해주십시오.');" : ""}
    ${register.result == UserRegisterResult.DUPLICATE_CONTACT ? "alert('연락처가 중복됩니다. 다시 한번 확인해주십시오.');" : ""}
</script>
</body>
</html>