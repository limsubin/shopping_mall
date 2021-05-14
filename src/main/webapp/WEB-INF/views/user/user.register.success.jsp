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
    <%@ include file="/WEB-INF/views/parts/header-cdn.html" %>
    <link rel="stylesheet" href="/resources/stylesheets/user/register.css">
</head>
<body>
<%@ include file="/WEB-INF/views/parts/header.jsp" %>
<%--TODO CSS 입히기--%>
<main class="register-wrap">
    <%-- 참고 페이지 : http://blog.naver.com/PostView.nhn?blogId=blogyourlife&logNo=220904177455&parentCategoryNo=&categoryNo=&viewDate=&isShowPopularPosts=false&from=postView --%>
    <h1>회원가입이 완료되었습니다.</h1>
    <div>${register.name}님의 회원가입을 축하합니다.</div>
    <hr>
    <div>
        <input type="submit" value="가입" id="register-button" class="object-button prop-pink">
        <input type="button" value="취소" id="cancel-button" class="object-button prop-basic">
    </div>
</main>
<%@ include file="/WEB-INF/views/parts/footer.jsp" %>
</body>
</html>