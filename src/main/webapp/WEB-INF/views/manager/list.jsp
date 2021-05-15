<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>리스트</title>
    <%@ include file="/WEB-INF/views/parts/header-cdn.html" %>
    <link rel="stylesheet" href="/resources/stylesheets/board/list.css">
</head>
<body>
<%@ include file="/WEB-INF/views/parts/header.jsp" %>
<main class="list-wrap">
    <div class="list">
        <ul>
            <li>
                <img src="/resources/images/list-test-image.jpg" alt="옷 이미지">
            </li>
            <li>사용자 닉네임</li>
            <li>상품이름</li>
            <li>가격</li>
        </ul>
        <ul>
            <li>
                <img src="/resources/images/list-test-image.jpg" alt="옷 이미지">
            </li>
            <li>사용자 닉네임</li>
            <li>상품이름</li>
            <li>가격</li>
        </ul>
        <ul>
            <li>
                <img src="/resources/images/list-test-image.jpg" alt="옷 이미지">
            </li>
            <li>사용자 닉네임</li>
            <li>상품이름</li>
            <li>가격</li>
        </ul>
        <ul>
            <li>
                <img src="/resources/images/list-test-image.jpg" alt="옷 이미지">
            </li>
            <li>사용자 닉네임</li>
            <li>상품이름</li>
            <li>가격</li>
        </ul>
    </div>
</main>
<%@ include file="/WEB-INF/views/parts/footer.jsp" %>
</body>
</html>