window.addEventListener('DOMContentLoaded', () => {
    const loginForm = window.document.getElementById('login-form');
    loginForm.onsubmit = function () {
        let loginEmailRegex = loginForm['email'].dataset.regex;
        let loginPasswordRegex = loginForm['password'].dataset.regex;

        //TODO 밑에 if문에 alert() 제대로 동작하게 하기
        //TODO 두 개의 input가 빈 값일때도 막기
        if (!loginEmailRegex.test(loginForm['email'].value)) {
            alert('올바른 이메일을 입력해주세요.');
            loginForm['email'].focus();
            return false;
        }
        if (!loginPasswordRegex.test(loginForm['password'].value)) {
            alert('올바른 비밀번호를 입력해주세요.');
            loginForm['password'].focus();
            return false;
        }
    };
});



// const loginForm = window.document.getElementById('login-form');
// loginForm.onsubmit = () => {
//     const callback = (resp) => {
//       let respJson = JSON.parse(resp);
//       switch(respJson['result']){
//           case 'success':
//           console.log('성공: '+resp);
//           window.location.reload();
//           break;
//         case 'failure':
//           console.log('실패: '+resp);
//           break;
//       }
//     };
//
//     const fallback = (status) => {
//       console.log('연결 실패: '+status);
//     };
//     const formData = new FormData(loginForm);
//     Ajax.request('POST', '/user/login', callback, fallback, formData);
//     return false;
//   };