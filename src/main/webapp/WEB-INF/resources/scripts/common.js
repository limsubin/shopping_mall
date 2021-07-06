window.addEventListener('DOMContentLoaded', function (){
    const priceElement = window.document.querySelectorAll(".price");
    const productNameElement = window.document.querySelectorAll(".productName");

    /*가격 3자리 단위마다 콤마 찍기*/
    priceElement.forEach(value => {
        let priceValue = value.innerText;
        value.innerText = priceValue.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    });

    /*특정 글자수가 넘었을때 마지막에 대체 문자 처리*/
    productNameElement.forEach(value => {
        let productName = value.innerText;
        let len = 15;
        let lastTxt = "...";

        if (productName.length > len) {
            value.innerText = productName.substr(0, len) + lastTxt;
        }
    });
});