window.addEventListener('DOMContentLoaded', function (){
   let numberWrapElement = window.document.querySelectorAll(".number-wrap");

   /*페이지 이동할때마다 No.1로 시작해서 계산해서 상품 No. 적기*/
   let url = document.location.href.split("/");
   let param = url[url.length-1];
   let start = 1;

   if (param !== 1){
       start = ((param - 1) * 10)+1;
   }

   for(let i = 0; i<numberWrapElement.length; i++){
       numberWrapElement[i].innerText = start++;
   }
});