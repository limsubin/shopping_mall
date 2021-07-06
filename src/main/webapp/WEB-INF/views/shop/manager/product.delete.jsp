<%@ page import="com.lsb.portfolio.shopping_mall.enums.shop.manager.ManagerProductDeleteResult" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>상품 삭제</title>
    <script>
        <c:if test="${deleteVo.result == ManagerProductDeleteResult.SUCCESS}">
            alert('해당 상품이 삭제되었습니다.');
            <c:if test="${deleteVo.subCategory == null}">
                window.location.href='/shop/list/${deleteVo.category}';
            </c:if>
            <c:if test="${deleteVo.subCategory != null}">
                window.location.href='/shop/list/${deleteVo.category}?sub=${deleteVo.subCategory}';
            </c:if>
        </c:if>
        <c:if test="${deleteVo.result == ManagerProductDeleteResult.FAILURE}">
        alert('해당 상품을 삭제하는데 예상치 못한 에러가 발생하였습니다. 다시 한번 시도해주십시오.');
        window.history.back();
        </c:if>
    </script>
</head>
</html>