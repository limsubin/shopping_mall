<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>상품 등록</title>
    <%@ include file="/WEB-INF/views/parts/common.cdn.html" %>
    <link rel="stylesheet" href="/resources/stylesheets/shop/manager/create.css">
    <script src="/resources/scripts/shop/manager/create.js"></script>
    <script src="/resources/scripts/class.ajax.js"></script>
    <script src="/resources/scripts/libraries/ckeditor/ckeditor.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/parts/header.jsp" %>
<main class="create-wrap">
<%--    method = post--%>
    <form enctype="multipart/form-data" id="create-form">
        <table>
            <thead>
                <tr>
                    <th scope="cols" colspan="5">상품 등록</th>
                </tr>
            </thead>
            <tr>
                <th scope="row">카테고리 선택</th>
                <td colspan="4">
                    <label>
                        <span hidden>카테고리 선택</span>
                        <select name="category">
                            <option value="" disabled selected>카테고리 선택</option>
                            <c:forEach var="category" items="${categories}">
                                <option value="${category.code}">${category.name}</option>
                            </c:forEach>
                        </select>
                    </label>
                    <label>
                        <span hidden>서브 카테고리 선택</span>
                        <select name="subCategory">
                            <option value="" disabled selected>서브 카테고리 선택</option>
                        </select>
                    </label>
                </td>
            </tr>
            <tr>
                <th scope="row">상품명</th>
                <td colspan="4">
                    <label>
                        <span hidden>상품명</span>
                        <input type="text" maxlength="100" name="productName" placeholder="상품명">
                    </label>
                </td>
            </tr>
            <tr>
                <th scope="row">상품 가격</th>
                <td colspan="4">
                    <label>
                        <span hidden>상품 가격</span>
                        <input type="number" maxlength="50" name="productPrice" placeholder="상품 가격">
                    </label>
                </td>
            <tr>
                <th scope="row" rowspan="1">상품 이미지 추가</th>
                <td colspan="4">
                    <label for="create-image">
                        <span hidden>상품 이미지 추가</span>
                        <img id="preview-image" width="200" height="200">
                        <div>
                            <input type="file" name="thumbnail" id="create-image" accept="image/*" value="상품 이미지 추가" multiple>
                        </div>
                    </label>
                </td>
            </tr>
            </tr>
            <tr>
                <th id="rowspan-dynamic" scope="row" rowspan="4">상품선택옵션</th>
                <td colspan="4">
                    <div>
                       옵션 항목은 콤마(,)로 구분하여 여러개를 입력할 수 있습니다.
                        <br><br>옷을 예로 들어,
                        <br>['사이즈' 항목 : XXL, XL, L, M, S]
                        <br>['색상' 항목 : 빨강색, 파랑색, 노랑색]
                    </div>
                </td>
            </tr>
            <tr>
                <td>상품 사이즈</td>
                <td colspan="3">
                    <label>
                        <span hidden>상품 사이즈</span>
                        <input type="text" maxlength="100" id="product_sizes" name="sizes" placeholder="상품 사이즈">
                    </label>
                </td>
            </tr>
            <tr>
                <td>상품 색상</td>
                <td colspan="3">
                    <label>
                        <span hidden>상품 색상</span>
                        <input type="text" id="product_colors" maxlength="100" name="colors" placeholder="상품 색상">
                    </label>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <label>
                        <span hidden>상품 선택옵션 목록생성</span>
                        <input type="button" value="목록 생성" id="option-create">
                    </label>
                </td>
            </tr>
            <tr class="option-title">
                <td>
                    <input type="checkbox" name="allOption-delete" value="옵션 선택">
                </td>
                <td>옵션</td>
                <td>추가금액</td>
                <td>재고수량</td>
            </tr>
            <tr class="option-list" id="option-delete">
                <td colspan="6">
                    <label>
                        <input type="button" name="option-delete" value="선택 삭제">
                    </label>
                </td>
            </tr>
            <tr>
                <th scope="row">상품 내용</th>
                <td colspan="6">
                    <label>
                        <span hidden>상품 내용</span>
                        <textarea name="productContent" id="productContent" cols="30" rows="10"></textarea>
                    </label>
                </td>
            </tr>
        </table>
        <div>
            <input type="submit" value="상품 등록" class="object-button prop-dark">
        </div>
    </form>
</main>
<%@ include file="/WEB-INF/views/parts/footer.jsp" %>
<script>
    /*서브 카테고리 생성*/
    let categoryArray = new Array();
    <c:forEach var="subCategory" items="${subCategories}">
    categoryArray.push(
              {"index":"${subCategory.index}",
               "code": "${subCategory.code}",
               "name":"${subCategory.name}",
               "categoryCode":"${subCategory.categoryCode}"}
    );
    </c:forEach>

    const categoryElement = window.document.querySelector("select[name=category]");
    const subCategoryElement = window.document.querySelector("select[name=subCategory]");
    //subCategoryElement.disabled = true;
    subCategoryElement.style.display = 'none';

    categoryElement.addEventListener('change', function(){
        if(subCategoryElement.options.length > 1){
            let subCategoryCount = subCategoryElement.length;
            for(let i = 1; i<subCategoryCount; i++){
                subCategoryElement.options[1].remove();
            }
        }

        for(let i = 0; i<categoryArray.length; i++){
            const option = window.document.createElement("option");
            if(categoryElement.value === categoryArray[i]["categoryCode"]){
                subCategoryElement.style.display = 'inline';
                option.setAttribute("value", categoryArray[i]["code"])
                option.innerText = categoryArray[i]["name"];
                subCategoryElement.append(option);
            }
        }

        if(subCategoryElement.options.length === 1){
            subCategoryElement.style.display = 'none';
            return false;
        }

        subCategoryElement.disabled = false;
    });
</script>
</body>
</html>