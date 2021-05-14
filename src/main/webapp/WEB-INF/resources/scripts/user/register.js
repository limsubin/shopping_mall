// DOMContentLoaded : DOM이 준비된 것을 확인한 후 원하는 DOM 노드를 찾아 핸들러를 등록해 인터페이스를 초기화할 때
//https://postcode.map.daum.net/guide#sample
window.addEventListener('DOMContentLoaded', function (){
    const addressButton = window.document.getElementById('address-button');
    const registerForm = window.document.getElementById('register-form');
    const birthYearSelect = window.document.getElementById('birthYear-select');
    const birthMonthSelect = window.document.getElementById('birthMonth-select');
    const birthDaySelect = window.document.getElementById('birthDay-select');

    // https://kouzie.github.io/html/WEB-JavaScript-4%EC%9D%BC%EC%B0%A8/#%EB%8B%AC%EB%A0%A5-%EB%A7%8C%EB%93%A4%EA%B8%B0
    birthYearSelect.options[0] = new Option("년");
    //birthYearSelect.options[0].selected = "selected";
    for(let i = 1950; i<=2050; i++){
        // new Option(text, value, defaultSelected, selected)
        birthYearSelect.options[(i+1) - 1950] = new Option(i, i);
    }

    birthMonthSelect.options[0] = new Option("월");
    birthMonthSelect.options[0].selected = "selected";
    for (let i = 1; i <= 12; i++){
        birthMonthSelect.options[i] = new Option(i, i);
    }

    birthDaySelect.options[0] = new Option("일");
    birthDaySelect.options[0].selected = "selected";
    birthDaySelect.disabled = true;

    function seleted(birthYear, birthMonth, birthDay){
        console.log(birthYear);
    }

    birthMonthSelect.addEventListener('change', function (){
        if(birthYearSelect.value !== "" && birthMonthSelect.value !== ""){
            let lastDate = new Date(birthYearSelect.value, birthMonthSelect.value, 0);
            console.log("year: "+birthYearSelect.value);
            console.log("month: "+birthMonthSelect.value);
            birthDaySelect.options[0] = new Option("일");
            birthDaySelect.options[0].selected = "selected";
            birthDaySelect.disabled = false;
            for(let i = 1; i <= lastDate.getDate(); i++){
                birthDaySelect.options[i] = new Option(i, i);
            }
            console.log("lastDay: "+lastDate.getDate());
        }
    });

    registerForm.onsubmit = function (){
        const inputElements = document.querySelectorAll('input');
        const selectElement = document.queryCommandEnabled('option[:selected]');
        for(let i = 0; i < inputElements.length; i++){
            let inputElement = inputElements[i];
            if(inputElement.dataset.regex !== undefined){
                //참고 문서 : https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/RegExp
                let regex = new RegExp(inputElement.dataset.regex);
                let name = inputElement.getAttribute('placeholder');
                let value = inputElement.value;
                //참고 문서 : https://heropy.blog/2018/10/28/regexp/
                if(!regex.test(value)){
                    alert(`올바른 ${name} 값을 입력해주세요.`);
                    inputElement.focus();
                    return false;
                }
            }
        }

        if(registerForm['password'].value !== registerForm['passwordCheck'].value){
            alert("비밀번호가 일치하지 않습니다. 다시 한번 확인해주십시오.");
            registerForm['passwordCheck'].focus();
            return false;
        }
    };

    [registerForm['contactSecond'], registerForm['contactThird']].forEach(element =>{
        element.oninput = function (){
            let maxLength =  element.getAttribute('maxlength')
            if (element.value.length > maxLength) {
                element.value = element.value.slice(0, maxLength);
            }
        };
    });


    addressButton.addEventListener('click', function (){
        new daum.Postcode({
            oncomplete: (data) => {
                registerForm['addressPost'].value = data['zonecode'];
                registerForm['address'].value = data['address'];
                registerForm['addressDetail'].focus();
                console.log(data);
            },
            width: '100%',
            height: '100%'
        }).open();
    });


});
