class Edit {
    static init() {
        Edit.sizes = [];
        Edit.colors = [];
        Edit.optionDetail = [];
        Edit.trOption = window.document.querySelectorAll(".tr-option");
        Edit.rowspan_dynamic_number = 0
    }
    
    /*상품 옵션 테이블 rowspan 값 변경*/
    static rowspanDynamic(){
        const ROWSPAN_DEFAULT = 6;
        Edit.trOption = window.document.querySelectorAll(".tr-option");
        const rowspan_dynamic_number = ROWSPAN_DEFAULT + Edit.trOption.length;

        Edit.rowspanElement = window.document.getElementById("rowspan-dynamic");
        Edit.rowspanElement.setAttribute("rowspan", rowspan_dynamic_number);

        return rowspan_dynamic_number;
    }
}

window.addEventListener('DOMContentLoaded', function (){
    Edit.init();
    Edit.rowspanDynamic();

    const editForm = window.document.getElementById("edit-form");
    const uploadWrap = window.document.getElementById("upload-wrap");
    const editInputImage = window.document.getElementById("input-image");
    const optionCreateButton = window.document.getElementById("option-create");
    const editImageElement = window.document.getElementById("edit-image");
    const previewImageElement = window.document.getElementById("preview-image");
    const optionTitle = window.document.querySelector(".option-title");
    const optionList = window.document.querySelector(".option-list");

    /*input[type=file]값 셋팅*/
    /*if(editImageElement.value !== ""){
        editForm['thumbnail'].files[0]
    }*/

    /*이미지 미리보기*/
    editForm['thumbnail'].addEventListener("change", function(e) {
        let input = e.target; //이벤트버블링의 가장 마지막에 위치한 최하위의 요소를 반환
        if(input.files && input.files[0]) { // 인풋 태그에 파일이 있는 경우
            // 이미지 파일인지 검사 (생략)
            const fileArr = Array.from(editInputImage.files);

            // FileReader 인스턴스 생성
            const reader = new FileReader();
            //const newImg = window.document.createElement("img");
            // 이미지가 로드가 된 경우
            reader.onload = function (e) {
                previewImageElement.src = e.target.result;
            };
            // reader가 이미지 읽도록 하기
            reader.readAsDataURL(input.files[0])
        }
    });

    /*사이즈, 색상 목록 생성*/
    optionCreateButton.addEventListener('click', function (){
        if(editForm["sizes"].value === ""){
            alert('상품 사이즈를 입력해주십시오.');
            editForm["sizes"].focus();
            return false;
        }

        if(editForm["colors"].value === ""){
            alert('상품 색상을 입력해주십시오.');
            editForm["colors"].focus();
            return false;
        }

        // const isValidSpecial = new RegExp("^([ㄱ-ㅎ|가-힣|a-z|A-Z|0-9]{1,},)*([ㄱ-ㅎ|가-힣|a-z|A-Z|0-9]{1,})$");
        // let sizeValue = editForm['sizes'].value.replace(/ /gi, "");
        // let colorValue = editForm['colors'].value.replace(/ /gi, "");
        // if(!isValidSpecial.test(sizeValue)){
        //     alert('상품 사이즈의 입력값이 잘못되었습니다. 다시 한번 확인해주십시오.');
        //     editForm["sizes"].focus();
        //     return false;
        // }
        //
        // if(!isValidSpecial.test(colorValue)){
        //     alert('상품 색상의 입력값이 잘못되었습니다. 다시 한번 확인해주십시오.');
        //     editForm["colors"].focus();
        //     return false;
        // }

        /*size, color 중복검사*/
        Edit.sizesArray = [];
        Edit.sizes = editForm["sizes"].value.split(',');
        Edit.colors = editForm["colors"].value.split(',');

        /*사이즈 중복 검사*/
        let size = window.document.querySelectorAll("#duplicateSize");
        for(let i = 0; i<size.length; i++){
            for (let j = 0; j<Edit.sizes.length; j++) {
                if (size[i].innerText.indexOf(Edit.sizes[i]) === 0) {
                    alert(`상품 사이즈값 "${Edit.sizes[i]}"이 중복됩니다.`);
                    return false;
                }
            }
        }

        /*색상 중복 검사*/
        let color = window.document.querySelectorAll("#duplicateColor");
        for(let i = 0; i<color.length; i++){
            for (let j = 0; j<Edit.sizes.length; j++) {
                if (color[i].innerText.indexOf(Edit.colors[i]) === 0) {
                    alert(`상품 색상값이 "${Edit.colors[i]}"이 중복됩니다.`);
                    return false;
                }
            }
        }

        // for(let i = 0; i< Create.sizes.length; i++){
        //     if(Create.sizes[i] === "" && Create.sizes[i].contains(Create.sizes[i+1])){
        //         alert("상품 사이즈값이 중복됩니다.");
        //         return false;
        //     }
        // }

        for(let i = 0; i < Edit.sizes.length; i++){
            for (let j = 0; j < Edit.colors.length; j++){
                const trContentElement = window.document.createElement("tr");

                const tdCheckOptionElement = window.document.createElement("td");
                const tdColorSizeElement = window.document.createElement("td");
                const tdPremiumsElement = window.document.createElement("td");
                const tdStocksElement = window.document.createElement("td");

                const checkOptionDelete = window.document.createElement("input");
                const inputPremiumsElement = window.document.createElement("input");
                const inputStocksElement = window.document.createElement("input");

                trContentElement.append(tdCheckOptionElement, tdColorSizeElement, tdPremiumsElement, tdStocksElement);
                tdCheckOptionElement.append(checkOptionDelete);
                checkOptionDelete.setAttribute("type", "checkbox");
                checkOptionDelete.setAttribute("name", "checkOption-delete");

                tdColorSizeElement.innerText = Edit.colors[j] +" > "+Edit.sizes[i]

                tdPremiumsElement.append(inputPremiumsElement);
                inputPremiumsElement.setAttribute("type", "number");
                inputPremiumsElement.setAttribute("name", "premiums");
                inputPremiumsElement.setAttribute("value", "0");
                inputPremiumsElement.setAttribute("placeholder", "추가 금액");

                tdStocksElement.append(inputStocksElement);
                inputStocksElement.setAttribute("type", "number");
                inputStocksElement.setAttribute("name", "stocks");
                inputStocksElement.setAttribute("value", "0");
                inputStocksElement.setAttribute("placeholder", "0");
                trContentElement.classList.add("tr-option");
                trContentElement.dataset.type = "1";

                optionList.before(trContentElement);
            }
        }
        Edit.rowspanDynamic();
        console.log("rowspanDynamic: "+Edit.rowspanDynamic());

        editForm["sizes"].value = "";
        editForm["colors"].value = "";
    });

    editForm['option-delete'].addEventListener("click", function (){
        const checked = document.querySelectorAll("input[type='checkbox']:checked");
        if(confirm(`${checked.length}개의 옵션이 선택되었습니다. 삭제하시겠습니까?`)){
            for (let i = 0; i < checked.length; i++){
                    // 부모 요소 찾기 참고 : https://itun.tistory.com/501
                    checked[i].parentNode.parentNode.setAttribute('style','display:none');
                    checked[i].parentNode.parentNode.dataset.type = "3";
            }
            let trOptionLength = Edit.rowspanDynamic() - checked.length;
            Edit.rowspanElement.setAttribute("rowspan", trOptionLength);
        }
    });

    /* ckeditor5 online builder : https://ckeditor.com/ckeditor-5/online-builder/ */
    ClassicEditor.create(editForm['productContent'], {
        simpleUpload:{
            uploadUrl:'/shop/manager/add-image'  //이미지가 올라갈 주소
        },
        toolbar: {
            items: [
                'heading',
                '|',
                'bold',
                'italic',
                'link',
                'bulletedList',
                'numberedList',
                '|',
                'outdent',
                'indent',
                '|',
                'imageUpload',
                'blockQuote',
                'insertTable',
                'mediaEmbed',
                'undo',
                'redo'
            ]
        },
        language: 'ko',
        image: {
            toolbar: [
                'imageTextAlternative',
                'imageStyle:full',
                'imageStyle:side'
            ]
        },
        table: {
            contentToolbar: [
                'tableColumn',
                'tableRow',
                'mergeTableCells'
            ]
        },
        licenseKey: '',
    }).then(editor => {
        window.editor = editor;
    }).catch(error => {
        console.error(error);
    });

    editForm.onsubmit = function (){
        if(editForm['category'].value === ""){
            alert('카테고리를 선택해주세요.');
            editForm['category'].focus();
            return false;
        }

        if(editForm['productName'].value === ""){
            alert('상품명을 입력해주세요.');
            editForm['productName'].focus();
            return false;
        }

        if(editForm['productPrice'].value === ""){
            alert('상품 가격을 입력해주세요.');
            editForm['productPrice'].focus();
            return false;
        }

        //let productContent = document.querySelector('.ck-content > p');
        if(editForm['productContent'].value === ""){
            alert('상품 내용을 입력해주세요.');
            editForm['productContent'].focus();
            return false;
        }

        Edit.optionDetail = [];
        for (let i = 0; i < Edit.trOption.length; i++){
            let optionsUpdate = {};
            optionsUpdate["size"] = Edit.trOption[i].children[1].innerText.split(">")[1].trim();
            optionsUpdate["color"] = Edit.trOption[i].children[1].innerText.split(">")[0].trim();

            if(editForm["premiums"].length === undefined){
                optionsUpdate["premium"] = editForm["premiums"].value;
            }else{
                optionsUpdate["premium"] = editForm["premiums"][i].value;
            }

            if(editForm["stocks"].length === undefined){
                optionsUpdate["stock"] = editForm["stocks"].value;
            }else{
                optionsUpdate["stock"] = editForm["stocks"][i].value;
            }

            if(optionsUpdate["stock"] === "0"){
                alert("재고 수량을 1개 이상 입력해주십시오.");
                return false;
            }

            optionsUpdate["type"] = Edit.trOption[i].dataset.type;
            Edit.optionDetail.push(optionsUpdate);
        }

        //alert("file if: " + editForm['thumbnail'].files[0] === undefined);
        /*파일이 변경되지 않았을 때 원래 파일을 데이터 값으로 넣음*/
/*        if(editForm['thumbnail'].files[0] === undefined){
            editForm['thumbnail'].files[0] = "undefined";
            alert('edit: '+editForm['thumbnail'].files[0]);
        }*/

        // let optionCount = 0;
        // if(Edit.sizes.length !== 0 && Edit.colors.length !== 0){
        //     for(let i = 0; i < Edit.sizes.length; i++) {
        //         for (let j = 0; j < Edit.colors.length; j++) {
        //             let optionsInsert = {};
        //             if(Edit.sizes[i] === undefined && Edit.colors[i] === undefined){
        //                 alert("상품 선택 옵션의 사이즈, 색상의 값이 올바르지 못합니다. 다시 한번 확인해주세요.");
        //                 return false;
        //             }
        //             optionsInsert["size"] = Edit.sizes[i];
        //             optionsInsert["color"] = Edit.colors[j];
        //             optionsInsert["premium"] = editForm["premiums"][optionCount].value;
        //             optionsInsert["stock"] = editForm["stocks"][optionCount].value;
        //             optionsInsert["type"] = Edit.trInsert[optionCount].dataset.type;
        //             Edit.optionDetail.push(optionsInsert);
        //             optionCount++;
        //         }
        //     }
        // }

        //console.log("optionCount: "+optionCount);
        console.log(Edit.optionDetail);

        const callback = (resp) =>{
            const respJson = JSON.parse(resp);
            console.log("respJson: "+respJson["result"]);
            switch (respJson["result"]){
                case "success":
                    alert("상품이 수정되었습니다.");
                    location.href = '/shop/read/'+respJson['productIndex'];
                    break;
                case "failure":
                    alert("상품 추가에 실패하였습니다.");
                    break;
                case "no_category":
                    alert("error : 선택 된 카데고리가 없습니다. (no category)");
                    break;
                case "no_select_count":
                    alert("error : 사이즈, 색상 값이 올바르지 못합니다. (no select count)");
                    break;
                default:
                    alert("알 수 없는 오류가 발생하였습니다.");
                    break;
            }
        };

        const fallback = (status) => {
            alert('예상치 못한 오류가 발생하였습니다. 관리자에게 문의해주십시오.');
        };

        if(editForm['category'].options[editForm['category'].selectedIndex].value === ""){
            alert("카테고리 값이 올바르지 않습니다. 관리자에게 문의해주십시오.");
            return false;
        }

        if(Edit.sizes === ""){
            alert("사이즈 값이 올바르지 않습니다. 관리자에게 문의해주십시오.");
            return false;
        }

        if(Edit.colors === []){
            alert("색상 값이 올바르지 않습니다. 관리자에게 문의해주십시오.");
            return false;
        }

        if(JSON.stringify(Edit.optionDetail) === []){
            alert("색상 값이 올바르지 않습니다. 관리자에게 문의해주십시오.");
            return false;
        }


        let formData = new FormData();
        formData.append("category", editForm['category'].options[editForm['category'].selectedIndex].value);

        if(editForm["subCategory"].options[editForm['subCategory'].selectedIndex] !== undefined){
            formData.append("subCategory", editForm["subCategory"].options[editForm['subCategory'].selectedIndex].value);
        }

        formData.append("productName", editForm['productName'].value);
        formData.append("productPrice", editForm['productPrice'].value);

        if(editForm['thumbnail'].value !== ""){
            formData.append("thumbnail", editForm['thumbnail'].files[0]);
        }

        formData.append("sizes", JSON.stringify(Edit.sizes));
        formData.append("colors", JSON.stringify(Edit.colors));

        formData.append("optionDetail", JSON.stringify(Edit.optionDetail));
        formData.append("productContent", editForm['productContent'].value);

        //let url = document.location.href.split("/");
        //url[url.length-1]
        Ajax.request('POST', document.location.href, callback, fallback, formData);
        return false;
    };

});