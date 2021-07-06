window.addEventListener('DOMContentLoaded', () => {
    const loginForm = window.document.getElementById('login-form');
    const loginButton = window.document.getElementById('login-button');

    loginForm.onsubmit = function () {
        let loginEmailRegex = new RegExp(loginForm['email'].dataset.regex);
        let loginPasswordRegex = new RegExp(loginForm['password'].dataset.regex);

        if (loginForm['email'].value === '') {
            alert('이메일을 입력해주세요.');
            loginForm['email'].focus();
            return false;
        }

        if (loginForm['password'].value === '') {
            alert('비밀번호를 입력해주세요.');
            loginForm['password'].focus();
            return false;
        }

        const callback = (resp) => {
          let respJson = JSON.parse(resp);
          switch(respJson['result']){
              case 'success':
                  location.href = "/shop";
              break;
            case 'failure':
              alert('아이디 혹은 비밀번호가 맞지 않습니다. 다시 한번 시도해주십시오');
              break;
          }
        };

        const fallback = (status) => {
            alert('예상치 못한 오류가 발생하였습니다. 관리자에게 문의해주십시오.');
        };

        let formData = new FormData(loginForm);
        Ajax.request('POST', '/user/login', callback, fallback, formData);
        return false;
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