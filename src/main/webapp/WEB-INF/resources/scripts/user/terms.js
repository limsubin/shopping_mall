window.addEventListener('DOMContentLoaded', function (){
   const termsButton = window.document.getElementById('terms-button');
   const agreeAllCheckBox = window.document.getElementById('agree-all');
   const agreeUserCheckBox = window.document.getElementById('agree-use');
   const agreePrivacyCheckBox = window.document.getElementById('agree-privacy');
   const agreeEmailCheckBox = window.document.getElementById('agree-email');

   agreeAllCheckBox.addEventListener('click', function (){
        agreeUserCheckBox.checked = true;
        agreePrivacyCheckBox.checked = true;
        agreeEmailCheckBox.checked = true;

        if(!agreeAllCheckBox.checked){
            agreeUserCheckBox.checked = false;
            agreePrivacyCheckBox.checked = false;
            agreeEmailCheckBox.checked = false;
        }
   });

   [agreeUserCheckBox, agreePrivacyCheckBox, agreeEmailCheckBox].forEach(elementName =>{
      elementName.addEventListener('click', function (){
          if(agreeAllCheckBox.checked){
              agreeAllCheckBox.checked = false;
          }
      });
   });

    termsButton.addEventListener('click', function (){
        const formData = new FormData();
        if(!agreeUserCheckBox.checked){
            alert('이용약관에 동의해주세요.');
            agreeUserCheckBox.focus();
            return false;
        }

        if(!agreePrivacyCheckBox.checked){
            alert('개인정보수집 및 이용에 동의해주세요.');
            agreeUserCheckBox.focus();
            return false;
        }
        //TODO 이메일 및 이용약관 동의한것도 DB에 넣고 싶은데 이걸 체크하고 다른 페이지로 가니까 GET으로 넘길 수 있는 방법 밑에꺼 말고 있는지 찾기
        if(agreeUserCheckBox.checked && agreePrivacyCheckBox.checked){
            window.location.href = "/user/register?agree=Y";
            return false;
        }

        if(agreeUserCheckBox.checked && agreePrivacyCheckBox.checked && agreeEmailCheckBox.checked){
            window.location.href = "/user/register?agree=Y&email=Y";
            return false;
        }
    });
});