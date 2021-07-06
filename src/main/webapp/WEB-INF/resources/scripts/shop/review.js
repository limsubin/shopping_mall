window.addEventListener('DOMContentLoaded', function (){
    const reviewFormElement = window.document.getElementById("review-form");
    const inputReviewElement = window.document.getElementById("input-review");
    const ratingElement = window.document.querySelector('.rating');
    let ratingOptions = 0;

    /*'리뷰 작성' 이벤트*/
    reviewFormElement.onsubmit = function (){
        if(ratingOptions === 0){
            alert("별점을 선택해주십시오.");
            return false;
        }

        if(inputReviewElement.length < 5){
            alert("리뷰를 5자 이상 입력해주십시오.");
            return false;
        }

        if(reviewFormElement["review"].value.length <= 5){
            alert("리뷰 내용을 5자 이상 입력해주십시오.");
            reviewFormElement["review"].focus();
            return false;
        }

        const callback = function (resp){
            const respJson = JSON.parse(resp);
            switch (respJson["result"]){
                case "success":
                    alert("리뷰 작성이 완료되었습니다.");
                    history.back();
                    break;
                case "review_completed":
                    alert("이미 리뷰를 작성하였습니다.");
                    history.back();
                    break;
                case "no_order_history":
                    alert("해당 상품에 리뷰를 작성할 권한이 없습니다.");
                    history.back();
                    break;
                case "no_login":
                    location.href = "/user/login";
                    break;
                case "failure":
                    alert("서버에 예상치 못한 오류가 발생하였습니다.");
                    break;
                default:
                    alert("알 수 없는 오류가 발생하였습니다.");
                    break;
            }
        };
        const fallback = function (status){
            alert('예상치 못한 오류가 발생하였습니다. 관리자에게 문의해주십시오.');
        };

        let url = location.href.split("/");
        let orderIndex = url[url.length-1];
        let productIndex = url[url.length-2];

        let formData = new FormData();
        formData.append("content", reviewFormElement["review"].value);
        formData.append("ratingOptions", ratingOptions);
        formData.append("orderIndex", orderIndex);
        formData.append("productIndex", productIndex);

        Ajax.request('POST', `/shop/paymentReview`, callback, fallback, formData);
        return false;
    };

    /*'별 점수' 이벤트*/
    //별 점수 참고 : https://blogpack.tistory.com/695#google_vignette
    ratingElement.addEventListener('click', function (event){
        let elem = event.target;
        if(elem.classList.contains('rate_radio')){
            let rate = parseInt(elem.value);
            let items = window.document.querySelectorAll('.rate_radio');
            ratingOptions = rate;
            items.forEach(function (item, idx){
                if(idx < rate){
                    item.checked = true;
                }else{
                    item.checked = false;
                }
            });
        }
    });
});