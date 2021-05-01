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
    <link rel="stylesheet" href="/resources/stylesheets/user/terms.css">
    <script src="/resources/scripts/user/terms.js"></script>
    <script>
<%--        ${strReferer == null ? "alert('정성적인 경로를 통해 다시 접근해주십시오.');window.location.href = '/';" : "return;"}--%>
    </script>
</head>
<body>
<%@ include file="/WEB-INF/views/parts/header.jsp" %>
<main class="terms-wrap">
    <h1>JOIN US</h1>
    <ul>
        <li>
            <div class="agreeAllCheck-wrap">
                <input type="checkbox" name="agree" id="agree-all">
                <label for="agree-all">
                    <i class="far fa-check-circle"></i>
                    <span>이용약관, 개인정보 수집 및 이용에 모두 동의합니다.</span>
                </label>
            </div>
        </li>
        <li class="agree">
            <div class="agree-title">
                <input type="checkbox" name="agree" id="agree-use">
                <label for="agree-use">
                    <i class="far fa-check-circle"></i>
                    <span>이용약관</span>
                </label>
            </div>
            <div class="agree-context">
            <pre>
                <%@ include file="/WEB-INF/views/termsAndConditions/rulesOfuse.jsp"%>
            </pre>
            </div>
        </li>

        <li class="agree">
            <div class="agree-title">
                <input type="checkbox" name="agree" id="agree-privacy">
                <label for="agree-privacy">
                    <i class="far fa-check-circle"></i>
                    <span>개인정보수집 및 이용</span>
                </label>
            </div>
            <div class="agree-context">
            <pre>
                <%@ include file="/WEB-INF/views/termsAndConditions/privacyRules.jsp"%>
            </pre>
            </div>
        </li>

        <li class="agree">
            <div class="agree-title">
                <input type="checkbox" name="agree" id="agree-email">
                <label for="agree-email">
                    <i class="far fa-check-circle"></i>
                    <span>이메일 수신동의 <b>[선택]</b></span>
                </label>
            </div>
            <div class="agree-context">
                <pre class="agree-Choice">쇼핑몰에서 제공하는 유익한 이벤트 소식을 이메일로 받으실 수 있습니다.</pre>
            </div>
        </li>
    </ul>

    <div>
        <input type="button" value="동의" id="terms-button" class="object-button prop-pink">
        <input type="button" value="비동의" id="cancel-button" class="object-button prop-basic">
    </div>
</main>
<%@ include file="/WEB-INF/views/parts/footer.jsp" %>
</body>
</html>