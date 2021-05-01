<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>로그인</title>
    <%@ include file="/WEB-INF/views/parts/header-cdn.html" %>
    <link rel="stylesheet" href="/resources/stylesheets/user/login.css">
<%--    <script src="/resources/scripts/user/login.js"></script>--%>
<%--    <script src="/resources/scripts/class.ajax.js"></script>--%>
</head>
<body>
<%@ include file="/WEB-INF/views/parts/header.jsp" %>
<main class="login-wrap">
    <form method="post" id="login-form">
        <h1>LOGIN</h1>
        <div>
            <label hidden>이메일</label>
            <input type="email" name="email" maxlength="50" minlength="0" placeholder="이메일" autofocus>
        </div>
        <div>
            <label hidden>비밀번호</label>
            <input type="password" name="password" maxlength="128" minlength="0" placeholder="비밀번호">
        </div>

        <input id="login-button" class="object-button prop-dark" type="submit" value="로그인">

        <div>
            <ul class="link-box">
                <li>일반회원</li>
                <li>아이디 찾기</li>
                <li>비밀번호 찾기</li>
            </ul>
        </div>
    </form>
</main>
<%@ include file="/WEB-INF/views/parts/footer.jsp" %>
</body>
</html>