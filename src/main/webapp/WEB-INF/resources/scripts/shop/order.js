window.addEventListener('DOMContentLoaded', function (){
    const orderFormElement = window.document.getElementById('order-form');
    const addressChangeButton = window.document.getElementById('address-change');
    const addressPostElement = window.document.getElementById('addressPost');
    const addressElement = window.document.getElementById('address');
    const addressDetailElement = window.document.getElementById('addressDetail');
    const orderButton = window.document.getElementById('order-button');
    const addressWrapElement = window.document.querySelector('.address-wrap > .content');
    const addressContentElement = window.document.querySelectorAll('.address-wrap > .content > div');
    const paymentWrapElement = window.document.querySelector('.payment-wrap');
    const orderDeleteIconElement = window.document.querySelectorAll("input[name='deleteIcon']");
    const orderCountInput = window.document.querySelectorAll("input[name='orderCount']");
    const deliveryChargeElement = window.document.querySelector(".deliveryCharge-wrap > .price");
    const totalPriceElement = window.document.querySelector(".totalPrice-wrap > .price");
    const productPriceElement = window.document.querySelector(".productPrice-wrap > .price");
    let payment = '';

    function totalCalc(){
        /*총 상품 계산 가격*/
        console.log(1);
        let calcTotal = 0;
        let calcTotalStr = '';
        let productTotalPrice = 0;
        let productTotalPriceStr = '';
        let orderProductPriceElement =  window.document.querySelectorAll('.subPrice-wrap');
        orderProductPriceElement.forEach(element =>{
            let productOrderCountElement = element.parentNode.querySelector("input[name='orderCount']");
            productTotalPrice = Number(productTotalPrice) + (Number(element.dataset.price)*productOrderCountElement.value);
        });
        /*총 상품 가격 + 배송비*/
        calcTotal = Number(productTotalPrice) + Number(deliveryChargeElement.innerText);
        calcTotalStr = calcTotal.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
        productTotalPriceStr = productTotalPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
        /*총 계산 가격*/
        totalPriceElement.innerText = calcTotalStr;
        /*총 상품 가격*/
        productPriceElement.innerText = productTotalPriceStr;
    }
    totalCalc();

    /*상품 개수 음수, 0 입력 방지*/
    orderCountInput.forEach(element =>{
        element.addEventListener("change", function (event){
            if (event.target.value < 1){
                event.target.value = 1;
            }else{
                event.target.value = Math.floor(this.value);
            }
            totalCalc();
        });
    });

    addressChangeButton.addEventListener('click', function (){
        const addressDiv = window.document.createElement('div');
        const addressTitle = window.document.createElement('div');
        const address = window.document.createElement('input');

        const addressPostDiv = window.document.createElement('div');
        const addressPostTitle = window.document.createElement('div');
        const addressPost = window.document.createElement('input');

        const addressDetailDiv = window.document.createElement('div');
        const addressDetailTitle = window.document.createElement('div');
        const addressDetail = window.document.createElement('input');

        const addressChange = window.document.createElement('input');

        /*addressPostElement.remove();
        addressElement.remove();
        addressDetailElement.remove();*/

        addressContentElement.forEach(element =>{
            element.remove();
        });

        //우편번호
        addressPostTitle.classList.add('address-title');
        addressPostTitle.innerText = "우편번호";
        // <input type="button" id="address-change" class="object-button prop-basic" value="주소변경">
        addressChange.setAttribute("type", "button");
        addressChange.setAttribute("id", "addressChange-AddButton");
        addressChange.setAttribute("value", "주소변경");
        addressChange.classList.add("object-button");
        addressChange.classList.add("prop-basic");
        addressChange.onclick = function (){
            new daum.Postcode({
                oncomplete: (data) => {
                    orderFormElement['addressPost'].value = data['zonecode'];
                    orderFormElement['address'].value = data['address'];
                    orderFormElement['addressDetail'].focus();
                    console.log(data);
                },
                width: '100%',
                height: '100%'
            }).open();
        };

        addressPost.setAttribute("type", "text");
        addressPost.setAttribute("name", "addressPost");
        addressPost.setAttribute("id", "addressPost");
        addressPost.classList.add("address-content");
        addressPostDiv.append(addressPostTitle, addressPost, addressChange);

        //주소
        addressTitle.classList.add('address-title');
        addressTitle.innerText = "주소";
        address.setAttribute("type", "text");
        address.setAttribute("name", "address");
        address.setAttribute("id", "address");
        address.classList.add("address-content");
        addressDiv.append(addressTitle,address);

        //상세주소
        addressDetailTitle.classList.add('address-title');
        addressDetailTitle.innerText = "상세주소";
        addressDetail.setAttribute("type", "text");
        addressDetail.setAttribute("name", "addressDetail");
        addressDetail.setAttribute("id", "addressDetail");
        addressDetail.classList.add("address-content");
        addressDetailDiv.append(addressDetailTitle,addressDetail);

        addressWrapElement.append(addressPostDiv, addressDiv, addressDetailDiv);

        //주소 찾기 api
        new daum.Postcode({
            oncomplete: (data) => {
                orderFormElement['addressPost'].value = data['zonecode'];
                orderFormElement['address'].value = data['address'];
                orderFormElement['addressDetail'].focus();
                console.log(data);
            },
            width: '100%',
            height: '100%'
        }).open();
    });

    orderFormElement['payment'].forEach(element => {
        element.addEventListener('click', function (event){
            orderFormElement['payment'].forEach(element => {
                element.classList.remove('active');
            });
            element.classList.add('active');
            payment = element.dataset.payment;
            console.log(payment);
        });
    })

    //orderFormElement['payment'][0].dataset.payment

    /*'주문하기' 이벤트*/
    orderButton.addEventListener('click', function (){
       if(payment === ''){
            alert('결재 방식을 하나 선택해주십시오.');
            return false;
       }

       const callback = function (resp){
           const respJson = JSON.parse(resp);

           switch (respJson["result"]){
               case 'success':
                   alert('결제가 완료되었습니다.');
                   location.href = `/shop/order/${respJson["orderIndex"]}?type=success`;
                   break;
               case 'failure':
                   alert('결제에 실패하였습니다.');
                   break;
               case "no_login":
                   location.href = "/user/login";
                   break;
               default:
                   alert("알 수 없는 오류가 발생하였습니다.");
                   break;
           }
       };

       const fallback = function (status){
           alert('예상치 못한 오류가 발생하였습니다. 관리자에게 문의해주십시오.');
       };

       const formData = new FormData();
       if(orderFormElement["addressPost"] === undefined
           && orderFormElement["address"] === undefined
           && orderFormElement["addressDetail"] === undefined){
           formData.append('addressPost', addressPostElement.innerText);
           formData.append('address', addressElement.innerText);
           formData.append('addressDetail', addressDetailElement.innerText);
       }else{
           formData.append('addressPost', orderFormElement["addressPost"].value);
           formData.append('address', orderFormElement["address"].value);
           formData.append('addressDetail', orderFormElement["addressDetail"].value);
       }

       let orderCountArray = [];
       let productIndexArray = [];
       for(let i = 0; i<orderCountInput.length; i++){
           let productIndexElement = orderCountInput[i].parentElement.parentElement.querySelector("input[name='deleteIcon']");
           orderCountArray.push(orderCountInput[i].value);
           productIndexArray.push(productIndexElement.dataset.productIndex);
       }

       formData.append("productIndex", JSON.stringify(productIndexArray));
       formData.append("orderCount", JSON.stringify(orderCountArray));
       formData.append('paymentMethod', payment);

       let url = document.location.href.split("/");
       let param = url[url.length-1];
       Ajax.request('POST', `/shop/payment/${param}`, callback, fallback, formData);
       return false;
    });

    /*'주문 아이콘 삭제 버튼' 이벤트*/
    orderDeleteIconElement.forEach(element => {
        element.addEventListener("click", function () {
            let orderIndex = 0;
            let productIndex = 0;
            if(confirm(`해당 상품을 삭제하시겠습니까?`)){
                orderIndex = element.dataset.orderIndex;
                productIndex = element.dataset.productIndex;

                const callback = function (resp){
                    const respJson = JSON.parse(resp);
                    switch (respJson["result"]){
                        case "success":
                            alert("선택된 주문상품이 삭제되었습니다.");
                            location.reload();
                            break;
                        case "failure":
                            alert("서버에 예상치 못한 오류가 발생하였습니다.");
                            break;
                        case "no_login":
                            location.href = "/user/login";
                            break;
                        default:
                            alert("알 수 없는 오류가 발생하였습니다.");
                            break;
                    }
                };

                const fullback = function (status){
                    alert('예상치 못한 오류가 발생하였습니다. 관리자에게 문의해주십시오.');
                };

                let formData = new FormData();
                formData.append("orderIndex", orderIndex);
                formData.append("productIndex", productIndex);

                Ajax.request('POST', '/shop/orderProductDelete', callback, fullback, formData);
                return false;
            }
        });
    });
});