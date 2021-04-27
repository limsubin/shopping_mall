window.addEventListener('DOMContentLoaded', () => {
  const loginForm = window.document.getElementById('login-form');
  loginForm.onsubmit = () => {
      const callback = (resp) => {
        let respJson = JSON.parse(resp);
        switch(respJson['result']){
            case 'success':
            console.log('성공: '+resp);
            window.location.reload();
            break;
          case 'failure':
            console.log('실패: '+resp);
            break;
        }
      };

      const fallback = (status) => {
        console.log('연결 실패: '+status);
      };
      const formData = new FormData(loginForm);
      Ajax.request('POST', '/user/login', callback, fallback, formData);
      return false;
    };

});