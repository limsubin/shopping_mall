// DOMContentLoaded : DOM이 준비된 것을 확인한 후 원하는 DOM 노드를 찾아 핸들러를 등록해 인터페이스를 초기화할 때
//https://postcode.map.daum.net/guide#sample
window.addEventListener('DOMContentLoaded', function (){
    const addressButton = window.document.getElementById('address-button');
    const registerForm = window.document.getElementById('register-form');
    addressButton.addEventListener('click', function (){
        new daum.Postcode({
            oncomplete: (data) => {
                registerForm['addressPost'].value = data['zonecode'];
                registerForm['address'].value = data['address'];
                registerForm['addressDetails'].focus();
                console.log(data);
            },
            width: '100%',
            height: '100%'
        }).open();
    });


});