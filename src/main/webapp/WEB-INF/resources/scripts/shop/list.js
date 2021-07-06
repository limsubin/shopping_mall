window.addEventListener('DOMContentLoaded', function (){
    const subMenuElement = window.document.querySelectorAll(".submenu > span");

    subMenuElement.forEach(element =>{
       let url = new URL(location.href);
       let urlParams = url.searchParams;
       let urlSearchParams = urlParams.get('sub');
      if(element.getAttribute('class') === urlSearchParams){
          element.classList.add('active');
      }else{
          element.classList.remove('active');
      }
    });
});