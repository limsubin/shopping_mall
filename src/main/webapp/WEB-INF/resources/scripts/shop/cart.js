window.addEventListener('DOMContentLoaded', function (){
   const cartFormElement = window.document.getElementById("cart-form");
   const orderButton = window.document.getElementById("order");
   const allCheckElement = window.document.querySelector("input[name='allCheck-order']");
   const choiceCheckElement = window.document.querySelectorAll("input[name='choiceCheck-order']");
   const totalPriceElement = window.document.querySelector(".totalPrice-wrap > .price");
   const productPriceElement = window.document.querySelector(".productPrice-wrap > .price");
   const deliveryChargeElement = window.document.querySelector(".deliveryCharge-wrap > .price");
   const cartDeleteButtonElement = window.document.getElementById("delete-button");
   const cartDeleteIconElement = window.document.querySelectorAll("input[name='deleteIcon']");
   const cartCountInput = window.document.querySelectorAll("input[name='cartCount']");

   let choiceCheckElementChecked = window.document.querySelectorAll("input[name='choiceCheck-order']:checked");

   /*장바구니 체크박스 전부 선택*/
    allCheckElement.checked = true;
    for(let i = 0; i<choiceCheckElement.length; i++){
        choiceCheckElement[i].checked= true;
    }

    /*총 상품 계산 가격*/
    function totalCalc(){
        let total = 0;
        let totalStr = '';
        let productTotalPrice = 0;
        let productTotalPriceStr = '';
        choiceCheckElementChecked = window.document.querySelectorAll("input[name='choiceCheck-order']:checked");
        choiceCheckElementChecked.forEach(element =>{
            let choiceProductPriceElement =  element.parentNode.parentNode.querySelector('.price');
            let choiceProductCartCountElement = element.parentNode.parentNode.querySelector("input[name='cartCount']");
            productTotalPrice = Number(productTotalPrice) + (Number(choiceProductPriceElement.dataset.price)*choiceProductCartCountElement.value);
        });
        /*총 상품 가격 + 배송비*/
        total = Number(productTotalPrice) + Number(deliveryChargeElement.innerText);
        totalStr = total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
        productTotalPriceStr = productTotalPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
        /*총 계산 가격*/
        totalPriceElement.innerText = totalStr;
        /*총 상품 가격*/
        productPriceElement.innerText = productTotalPriceStr;
    }
    totalCalc();

    /*장바구니 체크박스 전체 체크 해제*/
   allCheckElement.addEventListener('click', function (){
       for(let i = 0; i<choiceCheckElement.length; i++){
           if(allCheckElement.checked){
               choiceCheckElement[i].checked= true;
           }else{
               choiceCheckElement[i].checked= false;
           }
       }
       totalCalc();
   });

   /*장바구니 선택 체크*/
    choiceCheckElement.forEach(element =>{
        element.addEventListener('click', function (){
            totalCalc();
            allCheckElement.checked = false;
            //선택 체크할때 전부 선택했을 때 전체 선택 체크 박스도 체크
            choiceCheckElementChecked = window.document.querySelectorAll("input[name='choiceCheck-order']:checked");
            if(choiceCheckElement.length === choiceCheckElementChecked.length){
                allCheckElement.checked = true;
            }
        });
    });

    /*상품 개수 음수, 0 입력 방지*/
    cartCountInput.forEach(element =>{
        element.addEventListener("change", function (event){
            if (event.target.value < 1){
                event.target.value = 1;
            }else{
                event.target.value = Math.floor(this.value);
            }
            totalCalc();
        });
    });

   /*'장바구니의 상품 주문' 이벤트*/
    orderButton.addEventListener('click', function (){
       cartFormElement.onsubmit = function () {
            choiceCheckElementChecked = window.document.querySelectorAll("input[name='choiceCheck-order']:checked");
            if(choiceCheckElementChecked.length === 0){
                alert("상품을 하나 이상 선택해주십시오.");
                return  false;
            }

           const callback = function (resp){
               const respJson = JSON.parse(resp);
               switch (respJson["result"]){
                   case "success":
                       location.href = "/shop/order/"+respJson["orderIndex"];
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

           let cartIndexArray = [];
           let cartCountArray = [];
           for(let i = 0; i<choiceCheckElementChecked.length; i++){
               cartIndexArray.push(choiceCheckElementChecked[i].dataset.index);
               cartCountArray.push(choiceCheckElementChecked[i].parentNode.parentNode.querySelector("input[name='cartCount']").value);
           }

           //formData 값을 안 넣은 이유 : 이미 '/shop/read' 에서 cart DB로 전부넣어서 바로 '/shop/order'로 이동하면 됨
           const formData = new FormData();
           formData.append("cartIndex", JSON.stringify(cartIndexArray));
           formData.append("cartCount", JSON.stringify(cartCountArray));

           Ajax.request('POST', '/shop/order', callback, fullback, formData);
           return false;
       };
   });

    /*'장바구니 삭제' form 전송*/
    function cartDeleteAjax(cartIndexArray){
        const callback = function (resp){
            const respJson = JSON.parse(resp);
            switch (respJson["result"]){
                case "success":
                    alert("선택된 장바구니가 삭제되었습니다.");
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

        //formData 값을 안 넣은 이유 : 이미 '/shop/read' 에서 cart DB로 전부넣어서 바로 '/shop/order'로 이동하면 됨
        let formData = new FormData();
        formData.append("cartIndexArray", JSON.stringify(cartIndexArray));
        Ajax.request('POST', '/shop/cartDelete', callback, fullback, formData);
        return false;
    }

    /*'장바구니 선택 삭제' 버튼 이벤트*/
    cartDeleteButtonElement.addEventListener('click', function (){
        choiceCheckElementChecked = window.document.querySelectorAll("input[name='choiceCheck-order']:checked");
        if(choiceCheckElementChecked.length === 0){
            alert("삭제할 상품을 선택해주십시오.");
            return false;
        }
        if(confirm(`상픔 ${choiceCheckElementChecked.length}개를 선택하셨습니다. 삭제하시겠습니까?`)){
            let cartIndexArray = [];
            let cartIndex = 0;
            choiceCheckElementChecked.forEach(element =>{
                cartIndex = element.dataset.index;
                cartIndexArray.push(cartIndex);
            });

            cartDeleteAjax(cartIndexArray);
        }
    });

    /*'장바구니 아이콘 삭제 버튼' 이벤트*/
    cartDeleteIconElement.forEach(element => {
        element.addEventListener("click", function () {
            let cartIndexArray = [];
            let cartIndex = 0;
            if(confirm(`장바구니에서 해당 상품을 삭제하시겠습니까?`)){
                cartIndex = element.dataset.index;
                cartIndexArray.push(cartIndex);
                cartDeleteAjax(cartIndexArray);
            }
        });
    });
});


