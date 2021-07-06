window.addEventListener('DOMContentLoaded', function (){
    const readFormElement = window.document.getElementById("read-form");
    const cartButtonElement = window.document.getElementById("cart-button");
    const buyButtonElement = window.document.getElementById("buy-button");
    const informationButtonElement = window.document.getElementById("information");
    const reviewButtonElement = window.document.getElementById("review");
    const contentWrapElement = window.document.getElementById("content-wrap");
    const reviewFormElement = window.document.getElementById("review-form");
    const inputReviewElement = window.document.getElementById("input-review");
    const ratingElement = window.document.querySelector('.rating');
    const ratingAverageElement = window.document.querySelectorAll('.rating-average > i');

    const productReview = window.document.querySelector(".product-review");
    const productInformation = window.document.querySelector(".product-info");
    const cartCount = window.document.querySelector(".cart-count");
    const cartDelete = window.document.querySelectorAll(".cart-delete");
    let totalPrice = window.document.querySelector(".totalPrice");
    const reviewRatingCount = window.document.querySelectorAll(".reviewRating-count");
    let ratingOptions = 0;

    productInformation.classList.add('visible');

    /*'별 점수' 평균*/
    const LENGTH = 2;
    let inputAverageElement = window.document.querySelector("input[name='ratingAverage']");
    let ratingAverageValue = inputAverageElement.value;
    let ratingAverageQuotient = Math.floor(ratingAverageValue); //평균 몫
    let ratingAverageDecimal = ratingAverageValue % 1;          //평균 소수점
    ratingAverageDecimal  = Number(ratingAverageDecimal.toFixed(LENGTH));

    /*평균 몫 만큼 별 색깔 변경*/
    for(let i = 0; i < ratingAverageQuotient; i++){
        ratingAverageElement[i].style.background = '#F0CA4F';
    }

    /*평균 소수점이 있으면 그거만큼 별 색깔 변경*/
    if(ratingAverageDecimal !== 0){
        //스타일 참고 : http://rwdb.kr/34656788/
        ratingAverageElement[ratingAverageQuotient].style.background = `linear-gradient(90deg, #F0CA4F ${ratingAverageDecimal*100}%, #DCDDDE 50%)`;
    }

    /*이미 색칠한 별 나머지 부분 색깔 변경*/
    for(let i = Math.round(ratingAverageValue); i<ratingAverageElement.length; i++){
        ratingAverageElement[i].style.background = '#DCDDDE';
    }

    /*배경을 어디에 넣을지 속성 추가*/
    for(let j = 0; j<ratingAverageElement.length; j++){
        ratingAverageElement[j].style.webkitBackgroundClip = 'text';
    }

    /*평점 비율 바 색깔 채우기*/
    let reviewTotalCount = window.document.getElementById("reviewTotalCount").innerText;
    reviewRatingCount.forEach(element =>{
        let value = element.innerText;
        let barElement = element.nextElementSibling;
        let percentage = (value/reviewTotalCount) * 100;

        barElement.style.background = `linear-gradient(0deg, #F0CA4F ${percentage}%, #DCDDDE ${percentage}%)`;
    });

    /*'상품 정보' 이벤트*/
    informationButtonElement.addEventListener('click', function (){
        productReview.classList.remove('visible');
        productInformation.classList.add('visible');

        reviewButtonElement.classList.remove('active');
        informationButtonElement.classList.add('active');
    });

    /*'리뷰' 이벤트*/
    reviewButtonElement.addEventListener('click', function (){
        productInformation.classList.remove('visible');
        productReview.classList.add('visible');

        reviewButtonElement.classList.add('active');
        informationButtonElement.classList.remove('active');

    });

    /*'장바구니' 생성*/
    let duplicateArray = [];
    colorElement.addEventListener('change', function(){
        /*이미 선택된 사이즈, 색상 값*/
        let duplicateObject = {};
        duplicateObject["sizeIndex"] = sizeElement.value;
        duplicateObject["colorIndex"] = colorElement.value;
        duplicateArray.push(duplicateObject);

        /*장바구니 사이즈, 색상 중복 확인*/
        for(let i = 0; i<duplicateArray.length; i++){
            let sizeCurrElem = duplicateArray[i]["sizeIndex"];
            let colorCurrElem = duplicateArray[i]["colorIndex"];
            //클릭했던걸 담아두는 array, 새로 클릭한걸 담아두는 array
            for(let j = i+1; j<duplicateArray.length; j++){
                if(sizeCurrElem === duplicateArray[j]["sizeIndex"] && colorCurrElem === duplicateArray[j]["colorIndex"]){
                    alert("이미 선택된 항목입니다.");
                    duplicateArray.pop();
                    sizeElement.options[0].selected = "selected"
                    colorElement.options[0].selected = "selected"
                    colorElement.disabled = true;
                    return false;
                }
            }
        }

        /*장바구니 요소 생성*/
        const divElement = window.document.createElement("div");
        const spanOptionElement = window.document.createElement("span");
        const spanPriceElement = window.document.createElement("span");
        const iElement = window.document.createElement("i");
        const cartCountInput = window.document.createElement("input");
        const cartSubtotalPrice = window.document.createElement("input");
        const orderList = window.document.querySelector(".order-list");


        /*장바구니 생성 (사이즈, 색상)*/
        spanOptionElement.innerText = sizeElement.options[sizeElement.selectedIndex].text + ">" + colorElement.options[colorElement.selectedIndex].text;
        spanOptionElement.dataset.sizeIndex = sizeElement.options[sizeElement.selectedIndex].value;
        spanOptionElement.dataset.colorIndex = colorElement.options[colorElement.selectedIndex].value;
        /*장바구니 생성 (상품 개수)*/
        cartCountInput.setAttribute("type", "number");
        cartCountInput.setAttribute("name", "cartCount");
        cartCountInput.setAttribute("value", "1");
        /*장바구니 생성 (상품 가격)*/
        cartSubtotalPrice.setAttribute("type", "hidden");
        cartSubtotalPrice.setAttribute("name", "cartSubtotalPrice");

        spanPriceElement.classList.add("cartPrice");

        /*상품 가격, 추가 가격 계산*/
        let price = 0;
        let priceStr = '';
        let priceTotal = 0;
        let priceTotalStr = '';
        if(colorElement.options[colorElement.selectedIndex].dataset.premium === undefined){
            price = productPrice;

        }else{
            price = Number(productPrice) + Number(colorElement.options[colorElement.selectedIndex].dataset.premium);
        }

        /*장바구니 가격*/
        priceStr = price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
        spanPriceElement.innerText = priceStr;

        /*장바구니 총 가격*/
        //totalPrice = window.document.querySelector(".totalPrice");
        //alert(totalPrice.dataset.cartTotalPrice);
        priceTotal = Number(totalPrice.dataset.cartTotalPrice) + Number(price);
        priceTotalStr = priceTotal.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
        totalPrice.innerText = priceTotalStr;
        totalPrice.dataset.cartTotalPrice = priceTotal;

        /*장바구니 삭제 아이콘 생성*/
        cartSubtotalPrice.setAttribute("value", price);
        iElement.classList.add("far");
        iElement.classList.add("fa-times");
        iElement.classList.add("cart-delete");

        /*장바구니 삭제 이벤트*/
        //참고 : https://velog.io/@eunsonny/TIL.-JS-event.target%EA%B3%BC-event.currentTarget
        iElement.onclick = function (event){
            event.target.parentNode.remove();
            let sizeIndexDelete = event.target.parentNode.firstChild.dataset.sizeIndex;
            let colorIndexDelete = event.target.parentNode.firstChild.dataset.colorIndex;
            //참고 : https://stackoverflow.com/questions/25723674/javascript-possible-iteration-over-unexpected
            Object.keys(duplicateArray).forEach(key =>{
                if(sizeIndexDelete === duplicateArray[key]["sizeIndex"] && colorIndexDelete === duplicateArray[key]["colorIndex"]){
                    duplicateArray.splice(key);
                }
            });
        };

        /*장바구니 갯수에 따라 가격 계산*/
        cartCountInput.onchange = function (event){
            /*상품 개수 음수, 0 입력 방지*/
            if (event.target.value < 1){
                event.target.value = 1;
            }else{
                event.target.value = Math.floor(this.value);
            }

            //let targetPrice = event.target.nextSibling.innerText;
            let cartCount = event.target.value;     //해당 상품 개수
            let price = event.target.nextSibling.nextSibling.value; //해당 상품 단가
            let subPrice = price * cartCount;
            let subPriceStr = subPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
            
            spanPriceElement.innerText = subPriceStr; // 장바구니 가격

            /*총 가격 계산*/
            totalPrice.dataset.cartTotalPrice = 0;
            let total = 0;
            if(readFormElement["cartCount"].value !== ""){
                total = Number(readFormElement["cartSubtotalPrice"].value) * Number(readFormElement["cartCount"].value);
                priceTotal = Number(totalPrice.dataset.cartTotalPrice) + Number(total);
                priceTotalStr = priceTotal.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                totalPrice.innerText = priceTotalStr;
                totalPrice.dataset.cartTotalPrice = priceTotal;
            }else{
                for (let i = 0; i<readFormElement["cartCount"].length; i++){
                    total = Number(readFormElement["cartSubtotalPrice"][i].value) * Number(readFormElement["cartCount"][i].value);
                    priceTotal = Number(totalPrice.dataset.cartTotalPrice) + Number(total);
                    priceTotalStr = priceTotal.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                    totalPrice.innerText = priceTotalStr;
                    totalPrice.dataset.cartTotalPrice = priceTotal;
                }
            }
        };

        /*요소 추가*/
        divElement.append(spanOptionElement, cartCountInput, spanPriceElement, cartSubtotalPrice, iElement);
        orderList.append(divElement);
        /*초기화*/
        sizeElement.options[0].selected = "selected"
        colorElement.options[0].selected = "selected"
        colorElement.disabled = true;
    });



    /*장바구니 결제*/
    cartButtonElement.addEventListener('click', function (){
        readFormElement.onsubmit = function () {
            if (!Object.keys(duplicateArray).length) {
                alert("상품 사이즈, 색상을 선택해주십시오.");
                readFormElement["sizes"].focus();
                return false;
            }

            const callback = (resp) => {
                const respJson = JSON.parse(resp);

                switch (respJson["result"]) {
                    case "success":
                        if(confirm("장바구니에 추가되었습니다. 장바구니로 이동하시겠습니까?")){
                            location.href = "/shop/cart";
                        }else{
                            location.reload();
                        }
                        break;
                    case "failure":
                        console.log("like failure");
                        break;
                    case "no_login":
                        location.href = "/user/login";
                        break;
                    default:
                        alert("알 수 없는 오류가 발생하였습니다.");
                        break;
                }
            };

            const fallback = (status) => {
                alert('예상치 못한 오류가 발생하였습니다. 관리자에게 문의해주십시오.');
            };

            /*장바구니 값 (POST)*/
            let productSizeIndexArray = [];
            let productColorIndexArray = [];
            let cartCountArray = [];
            let cartSubtotalPriceArray = [];
            Object.keys(duplicateArray).forEach(key => {
                if (duplicateArray[key]["sizeIndex"] === undefined
                    && duplicateArray[key]["colorIndex"] === undefined
                    && readFormElement["cartCount"] === undefined
                    && readFormElement["cartSubtotalPrice"] === undefined) {
                    alert("장바구니의 값이 올바르지 않습니다. 관리자에게 문의해 주십시오.");
                    return false;
                }

                productSizeIndexArray.push(duplicateArray[key]["sizeIndex"]);
                productColorIndexArray.push(duplicateArray[key]["colorIndex"]);
                if (readFormElement["cartCount"].value !== "" && readFormElement["cartSubtotalPrice"].value !== "") {
                    cartCountArray.push(readFormElement["cartCount"].value);
                    cartSubtotalPriceArray.push(readFormElement["cartSubtotalPrice"].value);
                } else {
                    //장바구니 값이 두개 이상 들어갔을 때
                    if (readFormElement["cartCount"][key].value === "" && readFormElement["cartSubtotalPrice"][key].value === "") {
                        alert("상품의 개수, 가격의 값이 올바르지 않습니다. 관리자에게 문의해 주십시오.");
                        return false;
                    }
                    cartCountArray.push(readFormElement["cartCount"][key].value);
                    cartSubtotalPriceArray.push(readFormElement["cartSubtotalPrice"][key].value);
                }
            });

            const formData = new FormData();
            formData.append("productSizeIndex", JSON.stringify(productSizeIndexArray));
            formData.append("productColorIndex", JSON.stringify(productColorIndexArray));
            formData.append("cartCount", JSON.stringify(cartCountArray));
            formData.append("cartSubtotalPrice", JSON.stringify(cartSubtotalPriceArray));
            //console.log(formData.getAll('productSizeIndex'));

            let url = window.location.href;
            let params = url.split("/");
            let productIndex = params[params.length - 1];

            Ajax.request('POST', `/shop/cart/${productIndex}`, callback, fallback, formData);
            return false;
        };
    });


    /*'바로 결제' 이벤트*/
    buyButtonElement.addEventListener('click', function (){
        readFormElement.onsubmit = function () {
            if (!Object.keys(duplicateArray).length) {
                alert("상품 사이즈, 색상을 선택해주십시오.");
                readFormElement["sizes"].focus();
                return false;
            }

            const callback = (resp) => {
                const respJson = JSON.parse(resp);

                switch (respJson["result"]) {
                    case "success":
                        location.href = "/shop/order/"+respJson["orderIndex"];
                        break;
                    case "failure":
                        alert("상품 추가에 실패하였습니다.");
                        break;
                    case "already_in_cart":
                        alert("이미 장바구니에 추가되어 있습니다.");
                        location.reload();
                        break;
                    case "no_login":
                        location.href = "/user/login";
                        break;
                    default:
                        alert("알 수 없는 오류가 발생하였습니다.");
                        break;
                }
            };

            const fallback = (status) => {
                alert('예상치 못한 오류가 발생하였습니다. 관리자에게 문의해주십시오.');
            };

            /*장바구니 값 (POST)*/
            let productSizeIndexArray = [];
            let productColorIndexArray = [];
            let cartCountArray = [];
            let cartSubtotalPriceArray = [];
            Object.keys(duplicateArray).forEach(key => {
                if (duplicateArray[key]["sizeIndex"] === undefined
                    && duplicateArray[key]["colorIndex"] === undefined
                    && readFormElement["cartCount"] === undefined
                    && readFormElement["cartSubtotalPrice"] === undefined) {
                    alert("장바구니의 값이 올바르지 않습니다. 관리자에게 문의해 주십시오.");
                    return false;
                }

                productSizeIndexArray.push(duplicateArray[key]["sizeIndex"]);
                productColorIndexArray.push(duplicateArray[key]["colorIndex"]);
                if (readFormElement["cartCount"].value !== "" && readFormElement["cartSubtotalPrice"].value !== "") {
                    cartCountArray.push(readFormElement["cartCount"].value);
                    cartSubtotalPriceArray.push(readFormElement["cartSubtotalPrice"].value);
                } else {
                    //장바구니 값이 두개 이상 들어갔을 때
                    if (readFormElement["cartCount"][key].value === "" && readFormElement["cartSubtotalPrice"][key].value === "") {
                        alert("상품의 개수, 가격의 값이 올바르지 않습니다. 관리자에게 문의해 주십시오.");
                        return false;
                    }
                    cartCountArray.push(readFormElement["cartCount"][key].value);
                    cartSubtotalPriceArray.push(readFormElement["cartSubtotalPrice"][key].value);
                }
            });

            const formData = new FormData();
            formData.append("productSizeIndex", JSON.stringify(productSizeIndexArray));
            formData.append("productColorIndex", JSON.stringify(productColorIndexArray));
            formData.append("cartCount", JSON.stringify(cartCountArray));
            formData.append("cartSubtotalPrice", JSON.stringify(cartSubtotalPriceArray));

            let url = window.location.href;
            let params = url.split("/");
            let productIndex = params[params.length - 1];

            Ajax.request('POST', `/shop/buy/${productIndex}`, callback, fallback, formData);
            return false;
        };
    });


    /*'리뷰 작성' 이벤트*/
    reviewFormElement.onsubmit = function (){
        if(ratingOptions === 0){
            alert("별점을 선택해주십시오.");
            return false;
        }

        if(inputReviewElement.length < 5){
            alert("리뷰를 5자 이상 입력해주십시오.");
            return false;
        }

        if(reviewFormElement["review"].value.length <= 5){
            alert("리뷰 내용을 5자 이상 입력해주십시오.");
            reviewFormElement["review"].focus();
            return false;
        }

        const callback = function (resp){
            const respJson = JSON.parse(resp);
            switch (respJson["result"]){
                case "success":
                    alert("리뷰 작성이 완료되었습니다.");
                    location.reload();
                    break;
                case "review_completed":
                    alert("이미 리뷰를 작성하였습니다.");
                    location.reload();
                    break;
                case "no_order_history":
                    alert("해당 상품에 리뷰를 작성할 권한이 없습니다.");
                    location.reload();
                    break;
                case "no_login":
                    location.href = "/user/login";
                    break;
                case "failure":
                    alert("서버에 예상치 못한 오류가 발생하였습니다.");
                    break;
                default:
                    alert("알 수 없는 오류가 발생하였습니다.");
                    break;
            }
        };
        const fallback = function (status){
            alert('예상치 못한 오류가 발생하였습니다. 관리자에게 문의해주십시오.');
        };

        let formData = new FormData();
        formData.append("content", reviewFormElement["review"].value);
        formData.append("ratingOptions", ratingOptions);

        let url = location.href.split("/");
        let param = url[url.length-1];

        Ajax.request('POST', `/shop/review/${param}`, callback, fallback, formData);
        return false;
    };

    /*'별 점수' 클릭 이벤트*/
    //별 점수 참고 : https://blogpack.tistory.com/695#google_vignette
    ratingElement.addEventListener('click', function (event){
        let elem = event.target;
        if(elem.classList.contains('rate_radio')){
            let rate = parseInt(elem.value);
            let items = window.document.querySelectorAll('.rate_radio');
            ratingOptions = rate;
            items.forEach(function (item, idx){
               if(idx < rate){
                   item.checked = true;
               }else{
                   item.checked = false;
               }
            });
        }
    });
});