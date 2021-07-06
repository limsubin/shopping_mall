window.addEventListener('DOMContentLoaded', function () {
    let dateElement = window.document.querySelectorAll(".date");

    let dateArray = [];
    dateElement.forEach(element =>{
        let date = new Date(element.dataset.date);
        dateArray.push(date);
    });

    /*같은 날짜 중복 체크 (중복 되는 날짜 하나면 표시되도록)*/
    for(let i = 0; i<dateArray.length; i++){
        let dateCurrElem = dateArray[i];
        //dateCurrElem.setMonth(dateCurrElem.getMonth() + 1);

        /*console.log("dateCurrElem: "+dateCurrElem);
        console.log("year: "+dateCurrElem.getFullYear());
        console.log("month: "+dateCurrElem.getMonth());
        console.log("date: "+dateCurrElem.getDate());
        console.log("-----------------------------------");*/

        for(let a = i+1; a<dateArray.length; a++){
            if(dateCurrElem.getFullYear() === dateArray[a].getFullYear()
                && dateCurrElem.getMonth() === dateArray[a].getMonth()
                && dateCurrElem.getDate() === dateArray[a].getDate()){
                dateElement[a].parentNode.remove();
            }
        }
    }
});