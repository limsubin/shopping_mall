class Create{
    static init() {
        //TODO 쉼표가 문자에 포함되어 있는지 확인 안되어 있고 이상한 문자가 들어있으면 못 들어오게 하기 : (?:,|$)
        Create.sizes = [];
        Create.colors = [];
        Create.optionDetail = [];
        Create.tdAdd = 0;
        // Create.optionArrayMax = 0;
        // Create.optionArrayMin = 0;
        Create.rowspan_dynamic_number = 0
    }
}

window.addEventListener('DOMContentLoaded', function (){
    Create.init();

    const createForm = window.document.getElementById("create-form");
    const uploadWrap = window.document.getElementById("upload-wrap");
    const inputImage = window.document.getElementById("input-image");
    const optionCreateButton = window.document.getElementById("option-create");
    // const productSizes = window.document.getElementById("product_sizes");
    // const productColors = window.document.getElementById("product_colors");
    const optionTitle = window.document.querySelector(".option-title");
    const optionList = window.document.querySelector(".option-list");
    const rowspanDynamic = window.document.getElementById("rowspan-dynamic");
    const ROWSPAN_DEFAULT = 6;

    inputImage.addEventListener("change", function(e) {
        //let input = e.target; //이벤트버블링의 가장 마지막에 위치한 최하위의 요소를 반환
        if(inputImage.files) { // 인풋 태그에 파일이 있는 경우
            // 이미지 파일인지 검사 (생략)
            const fileArr = Array.from(inputImage.files);
            fileArr.forEach((file, index) => {
                // FileReader 인스턴스 생성
                const reader = new FileReader();
                const newImg = window.document.createElement("img");
                // 이미지가 로드가 된 경우
                reader.onload = function (e) {
                    //console.log(e.target.result);
                    uploadWrap.append(newImg);
                    newImg.setAttribute("src", e.target.result);
                    newImg.setAttribute("width", 100);
                    newImg.setAttribute("class", "pre_img");
                    //previewImage.src = e.target.result
                };
                // reader가 이미지 읽도록 하기
                reader.readAsDataURL(file);
                //previewImage.classList.add('visible');
            });
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

        //TODO 이런 정규 표현식은 어디서 만드는건가? (바로 밑에 코드로 하면 됨)
        //TODO regex 조건 1) 맨 마지막에 콤마가 적혀있는지, 2) 공백 없애기 (그냥 글자 콤마 글자 콤마 글자 이런식으로 잘 들어오는지 확인하고 싶음)
        //참고 : 문자열에서 마지막 콤마 제거
        // https://hianna.tistory.com/506
        // const isValidSpecial = new RegExp("^([`~!@#$%^&*()\\-_=+\\[{\\]}\\\\|;:'<.>/?])$");
        // if(isValidSpecial.test(createForm["sizes"].value)){
        //     alert('상품 사이즈의 입력값이 잘못되었습니다. 다시 한번 확인해주십시오.');
        //     createForm["sizes"].focus();
        //     return false;
        // }
        //
        // if(isValidSpecial.test(createForm["colors"].value)){
        //     alert('상품 색상의 입력값이 잘못되었습니다. 다시 한번 확인해주십시오.');
        //     createForm["colors"].focus();
        //     return false;
        // }

        Create.sizes = createForm['sizes'].value.replace(/ /gi, "").split(',');
        Create.colors = createForm['colors'].value.replace(/ /gi, "").split(',');
        Create.optionArrayMax = Math.max(Create.sizes.length, Create.colors.length);
        Create.optionArrayMin = Math.min(Create.sizes.length, Create.colors.length);
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
        //TODO (1) rowsapn 코드 중복 (하나로 못 빼나?)
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
            //TODO (2) rowsapn 코드 중복 (하나로 못 빼나?)
            Create.tdAdd = window.document.querySelectorAll(".td-add");
            Create.rowspan_dynamic_number = ROWSPAN_DEFAULT + Create.tdAdd.length;
            rowspanDynamic.setAttribute("rowspan", Create.rowspan_dynamic_number);
        }
    });

    createForm.onsubmit = function (){
        if(createForm['category'].value === ""){
            alert('카테고리를 선택해주세요.');
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

        //TODO 목록생성 버튼을 클릭을 했는지 확인
        if(Create.sizes.length === 0 && typeof Create.sizes !== 'object' && Create.colors.length === 0 && typeof Create.colors !== 'object'){
            alert('상품선택옵션에서 목록을 생성해주세요.');
            optionCreateButton.focus();
            return false;
        }

        //TODO alert 나오는거 조절하기 kedit에 입력한걸로 에러 메시지 띄우기
        if(createForm['productContent'].value === ""){
            alert('상품 내용을 입력해주세요.');
            createForm['productContent'].focus();
            return false;
        }

        for(let i = 0; i < Create.sizes.length; i++) {
            for (let j = 0; j < Create.colors.length; j++) {
                let options = {};
                //TODO 여기에 값이 null이 들어감
                options["size"] = Create.sizes[i];
                options["color"] = Create.colors[j];
                options["premium"] = createForm["premiums"][i].value;
                options["stock"] = createForm["stocks"][i].value;
                Create.optionDetail.push(options);
            }
        }
        console.log(Create.optionDetail);
        const callback = (resp) =>{
            //TODO swtich로 서버에서 받은걸로 페이지 이동하기
            alert("상품이 추가되었습니다.");
        };
        const fallback = (status) =>{
            alert('예상치 못한 오류가 발생하였습니다. 관리자에게 문의해주십시오.');
        };

        const formData = new FormData();
        formData.append("category", createForm['category'].options[createForm['category'].selectedIndex].value);
        formData.append("productName", createForm['productName'].value);
        formData.append("productPrice", createForm['productPrice'].value);
        //TODO 이미지가 여러개 선택되었을때 for문 처리 하기
        formData.append("thumbnail", createForm['thumbnail'].files[0]);
        formData.append("sizes", JSON.stringify(Create.sizes));
        formData.append("colors", JSON.stringify(Create.colors));
        formData.append("optionDetail", JSON.stringify(Create.optionDetail));
        formData.append("productContent", createForm['productContent'].value);

        Ajax.request('POST', '/manager/board/create', callback, fallback, formData);
        return false;
    };

    /* ckeditor5 online builder : https://ckeditor.com/ckeditor-5/online-builder/ */
    ClassicEditor.create(createForm['productContent'], {
        simpleUpload:{
            uploadUrl:'/manager/board/add-image'  //이미지가 올라갈 주소
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

});