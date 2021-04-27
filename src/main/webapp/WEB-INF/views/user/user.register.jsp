<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>로그인</title>
    <link rel="stylesheet" href="/resources/stylesheets/common.css">
    <link rel="stylesheet" href="/resources/stylesheets/user/login.css">
</head>
<body>
<form method="post">
    <div>
        <label hidden>이메일</label>
        <input type="email" name="email" maxlength="50" minlength="0" placeholder="이메일" autofocus>
    </div>
    <div>
        <label hidden>비밀번호</label>
        <input type="password" name="password" maxlength="128" minlength="0" placeholder="비밀번호">
    </div>
    <div>
        <label hidden>별명</label>
        <input type="text" name="nickname" maxlength="10" minlength="0" placeholder="별명">
    </div>
    <div>
        <label hidden>이름</label>
        <input type="text" name="name"  maxlength="10" minlength="0" placeholder="이름">
    </div>
    <div>
        <label hidden>연락처</label>
        <input type="text" name="contact" pattern="^(010)\-([0-9]{4})\-([0-9]{4})$" maxlength="15" minlength="0" placeholder="연락처">
    </div>
    <div>
        <label hidden>주소</label>
        <input type="text" name="address" maxlength="50" minlength="0" placeholder="주소">
    </div>
    <input type="submit" value="회원가입">
</form>
</body>
</html>