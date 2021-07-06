window.addEventListener('DOMContentLoaded', function (){
    const addressButton = window.document.getElementById('address-button');
    const registerForm = window.document.getElementById('register-form');
    const birthYearSelect = window.document.getElementById('birthYear-select');
    const birthMonthSelect = window.document.getElementById('birthMonth-select');
    const birthDaySelect = window.document.getElementById('birthDay-select');

    /*생년월일 선택 날짜별 태그 생성*/
    //년
    birthYearSelect.options[0] = new Option("년");
    for(let i = 1950; i<=2050; i++){
        birthYearSelect.options[(i+1) - 1950] = new Option(i, i);
    }

    //월
    birthMonthSelect.options[0] = new Option("월");
    birthMonthSelect.options[0].selected = "selected";
    for (let i = 1; i <= 12; i++){
        birthMonthSelect.options[i] = new Option(i, i);
    }

    //일
    birthDaySelect.options[0] = new Option("일");
    birthDaySelect.options[0].selected = "selected";
    birthDaySelect.disabled = true;

    birthMonthSelect.addEventListener('change', function (){
        if(birthYearSelect.value !== "" && birthMonthSelect.value !== ""){
            let lastDate = new Date(birthYearSelect.value, birthMonthSelect.value, 0);
            birthDaySelect.options[0] = new Option("일");
            birthDaySelect.options[0].selected = "selected";
            birthDaySelect.disabled = false;
            
            for(let i = 1; i <= lastDate.getDate(); i++){
                birthDaySelect.options[i] = new Option(i, i);
            }
        }
    });

    /*회원가입 입력값 확인*/
    registerForm.onsubmit = function (){
        const inputElements = document.querySelectorAll('input');
        for(let i = 0; i < inputElements.length; i++){
            let inputElement = inputElements[i];
            if(inputElement.dataset.regex !== undefined){
                let regex = new RegExp(inputElement.dataset.regex);
                let name = inputElement.getAttribute('placeholder');
                let value = inputElement.value;

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


    /*전화번호 4자리 이상 입력 금지*/
    [registerForm['contactSecond'], registerForm['contactThird']].forEach(element =>{
        element.oninput = function (){
            let maxLength =  element.getAttribute('maxlength')
            if (element.value.length > maxLength) {
                element.value = element.value.slice(0, maxLength);
            }
        };
    });


    /*주소 입력 api*/
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
