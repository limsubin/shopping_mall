class Create{
    static init() {
        Create.sizes = [];
        Create.colors = [];
        Create.optionDetail = [];
        Create.tdAdd = 0;
        Create.rowspan_dynamic_number = 0;
    }
}

window.addEventListener('DOMContentLoaded', function (){
    Create.init();

    const createForm = window.document.getElementById("create-form");
    const uploadWrap = window.document.getElementById("upload-wrap");
    const createInputImage = window.document.getElementById("create-image");
    const optionCreateButton = window.document.getElementById("option-create");
    const optionTitle = window.document.querySelector(".option-title");
    const optionList = window.document.querySelector(".option-list");
    const rowspanDynamic = window.document.getElementById("rowspan-dynamic");
    const ROWSPAN_DEFAULT = 6;

    /*상품 이미지 미리보기*/
    createForm['thumbnail'].addEventListener("change", function(e) {
        let input = e.target; //이벤트버블링의 가장 마지막에 위치한 최하위의 요소를 반환
        if(input.files && input.files[0]) { // 인풋 태그에 파일이 있는 경우

            // FileReader 인스턴스 생성
            const reader = new FileReader();
            const newImg = window.document.createElement("img");

            // 이미지가 로드가 된 경우
            reader.onload = function (e) {
                const previewImage = document.getElementById("preview-image");
                previewImage.src = e.target.result;
            };
            // reader가 이미지 읽도록 하기
            reader.readAsDataURL(input.files[0])
        }
    });

    optionCreateButton.addEventListener('click', function (){
        if(createForm["sizes"].value === ""){
            alert('상품 사이즈를 입력해주십시오.');
            createForm["sizes"].focus();
            return false;
        }

        if(createForm["colors"].value === ""){
            alert('상품 색상을 입력해주십시오.');
            createForm["colors"].focus();
            return false;
        }

        const isValidSpecial = new RegExp("^([ㄱ-ㅎ|가-힣|a-z|A-Z|0-9]{1,},)*([ㄱ-ㅎ|가-힣|a-z|A-Z|0-9]{1,})$");
        let sizeValue = createForm['sizes'].value.replace(/ /gi, "");
        let colorValue = createForm['colors'].value.replace(/ /gi, "");
        if(!isValidSpecial.test(sizeValue)){
            alert('상품 사이즈의 입력값이 잘못되었습니다. 다시 한번 확인해주십시오.');
            createForm["sizes"].focus();
            return false;
        }

        if(!isValidSpecial.test(colorValue)){
            alert('상품 색상의 입력값이 잘못되었습니다. 다시 한번 확인해주십시오.');
            createForm["colors"].focus();
            return false;
        }

        Create.sizes = sizeValue.split(',');
        Create.colors = colorValue.split(',');
        console.log("Create.sizes: "+Create.sizes);
        console.log("Create.colors: "+Create.colors);
        /*사이즈 중복 검사*/
        let size = window.document.querySelectorAll(".td-add");
        for(let i = 0; i<size.length; i++){
            for (let j = 0; j<Create.sizes.length; j++){
                if(size[i].children[1].innerText.split(">")[1].trim().indexOf(Create.sizes[j]) === 0){
                    alert(`상품 사이즈값 "${Create.sizes[j]}"이 중복됩니다.`);
                    return false;
                }
            }
        }

        /*색상 중복 검사*/
        let color = window.document.querySelectorAll(".td-add");
        for(let i = 0; i<color.length; i++){
            for (let j = 0; j<Create.colors.length; j++) {
                if (color[i].children[1].innerText.split(">")[0].trim().indexOf(Create.colors[i]) === 0) {
                    alert(`상품 색상값이 "${Create.colors[i]}"이 중복됩니다.`);
                    return false;
                }
            }
        }

        // Create.optionArrayMax = Math.max(Create.sizes.length, Create.colors.length);
        // Create.optionArrayMin = Math.min(Create.sizes.length, Create.colors.length);
        for(let i = 0; i < Create.sizes.length; i++){
            for (let j = 0; j < Create.colors.length; j++){
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

                tdColorSizeElement.innerText = Create.colors[j] +" > "+Create.sizes[i]

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
                trContentElement.classList.add("td-add");

                optionList.before(trContentElement);
            }
        }
        Create.tdAdd = window.document.querySelectorAll(".td-add");
        Create.rowspan_dynamic_number = ROWSPAN_DEFAULT + Create.tdAdd.length;
        rowspanDynamic.setAttribute("rowspan", Create.rowspan_dynamic_number);

        createForm["sizes"].value = "";
        createForm["colors"].value = "";
        optionTitle.classList.add('visible');
        optionList.classList.add('visible');
    });

    createForm['option-delete'].addEventListener("click", function (){
        //const select = createForm['checkOption-delete'];
        const checked = document.querySelectorAll(".td-add input[type='checkbox']:checked");
        if(confirm(`${checked.length}개의 옵션이 선택되었습니다. 삭제하시겠습니까?`)){
            for (let i = 0; i < checked.length; i++){
                //if(checked[i].checked){
                    // 부모 요소 찾기 참고 : https://itun.tistory.com/501
                    checked[i].parentNode.parentNode.remove()
                //}
            }
            Create.tdAdd = window.document.querySelectorAll(".td-add");
            Create.rowspan_dynamic_number = ROWSPAN_DEFAULT + Create.tdAdd.length;
            rowspanDynamic.setAttribute("rowspan", Create.rowspan_dynamic_number);
        }
    });

    /* ckeditor5 online builder : https://ckeditor.com/ckeditor-5/online-builder/ */
    ClassicEditor.create(createForm['productContent'], {
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

    createForm.onsubmit = function (){
        if(createForm['category'].value === ""){
            alert('카테고리를 선택해주세요.');
            createForm['category'].focus();
            return false;
        }

        if(createForm['subCategory'].length > 1 && createForm['subCategory'].value === ""){
            alert('서브 카테고리를 선택해주세요.');
            createForm['category'].focus();
            return false;
        }

        if(createForm['productName'].value === ""){
            alert('상품명을 입력해주세요.');
            createForm['productName'].focus();
            return false;
        }

        if(createForm['productPrice'].value === ""){
            alert('상품 가격을 입력해주세요.');
            createForm['productPrice'].focus();
            return false;
        }

        if(createForm['thumbnail'].files[0] === undefined){
            alert('상품 이미지를 1개 이상 선택해주세요.');
            createForm['thumbnail'].focus();
            return false;
        }

        if(Create.sizes.length === 0 && Create.colors.length === 0){
            alert('상품선택옵션에서 목록을 생성해주세요.');
            optionCreateButton.focus();
            return false;
        }

        //let productContent = document.querySelector('.ck-content');
        if(createForm['productContent'].value === ""){
            alert('상품 내용을 입력해주세요.');
            createForm['productContent'].focus();
            return false;
        }

        Create.optionDetail = [];
        let tdAddElement = window.document.querySelectorAll(".td-add");
        for (let i = 0; i < tdAddElement.length; i++){
            let options = {};
            options["size"] = tdAddElement[i].children[1].innerText.split(">")[1].trim();
            options["color"] = tdAddElement[i].children[1].innerText.split(">")[0].trim();

            if(createForm["premiums"].length === undefined){
                options["premium"] = createForm["premiums"].value;
            }else{
                options["premium"] = createForm["premiums"][i].value;
            }

            if(createForm["stocks"].length === undefined){
                options["stock"] = createForm["stocks"].value;
            }else{
                options["stock"] = createForm["stocks"][i].value;
            }

            if(options["stock"] === "0"){
                alert("재고 수량을 1개 이상 입력해주십시오.");
                return false;
            }

            Create.optionDetail.push(options);
        }

        console.log(Create.optionDetail);
        const callback = (resp) =>{
            const respJson = JSON.parse(resp);
            console.log("respJson: "+respJson["result"]);
            switch (respJson["result"]){
                case "success":
                    alert("상품이 추가되었습니다.");
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
        const fallback = (status) =>{
            alert('예상치 못한 오류가 발생하였습니다. 관리자에게 문의해주십시오.');
        };

        const formData = new FormData();
        formData.append("category", createForm['category'].options[createForm['category'].selectedIndex].value);

        if(createForm["subCategory"].options[createForm['subCategory'].selectedIndex] !== undefined){
            formData.append("subCategory", createForm["subCategory"].options[createForm['subCategory'].selectedIndex].value);
        }

        formData.append("productName", createForm['productName'].value);
        formData.append("productPrice", createForm['productPrice'].value);
        formData.append("thumbnail", createForm['thumbnail'].files[0]);
        formData.append("sizes", JSON.stringify(Create.sizes));
        formData.append("colors", JSON.stringify(Create.colors));
        formData.append("optionDetail", JSON.stringify(Create.optionDetail));
        formData.append("productContent", createForm['productContent'].value);

        Ajax.request('POST', '/shop/manager/create', callback, fallback, formData);
        return false;
    };
});