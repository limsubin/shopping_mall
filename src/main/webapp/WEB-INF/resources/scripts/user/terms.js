window.addEventListener('DOMContentLoaded', function (){
   const termsButton = window.document.getElementById('terms-button');
   const agreeCheckBox = window.document.getElementById('agree-all');
   const agreeUserCheckBox = window.document.getElementById('agree-use');
   const agreePrivacyCheckBox = window.document.getElementById('agree-privacy');
   const agreeEmailCheckBox = window.document.getElementById('agree-email');

   agreeCheckBox.addEventListener('click', function (){
        agreeUserCheckBox.checked = true;
        agreePrivacyCheckBox.checked = true;
        agreeEmailCheckBox.checked = true;

        if(!agreeCheckBox.checked){
            agreeUserCheckBox.checked = false;
            agreePrivacyCheckBox.checked = false;
            agreeEmailCheckBox.checked = false;
        }
   });

    termsButton.addEventListener('click', function (){
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

        if(agreeUserCheckBox.checked && agreePrivacyCheckBox.checked){
            window.location.href = "/user/register?agree=Y";
            return false;
        }
    });
});