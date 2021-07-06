window.addEventListener("DOMContentLoaded", function (){
    const allMenuButton = window.document.getElementById("btn-allMenu");
    const allMenuCoverButton = window.document.getElementById("btn-allMenu-cover");
    const allMenuCoverElement = window.document.querySelector(".allMenu-cover");

    /*전체 메뉴 보기*/
    allMenuButton.addEventListener('click', function (){
        allMenuCoverElement.classList.add("visible");
    });

    allMenuCoverButton.addEventListener('click', function (){
        allMenuCoverElement.classList.remove("visible");
    })
});