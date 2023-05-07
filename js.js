function alert(status){
    document.getElementById('alert').style.display = status;
}

const addToDoBtn = document.getElementById('addToDoBtn');
const toDoContainer = document.getElementById('toDoContainer');
const inputField = document.getElementById('inputField');

addToDoBtn.addEventListener('click', function () {
    const paragraph = document.createElement('p');
    const del = document.createElement('p')
    paragraph.innerText = inputField.value ;
    del.innerText = "❌";
    toDoContainer.appendChild(paragraph);
    toDoContainer.appendChild(del);
    inputField.value = "";
    del.addEventListener('click', function () {
        toDoContainer.removeChild(paragraph);
        toDoContainer.removeChild(del);
    })
})

const xbtn1 = document.getElementById('xbtn1');
const board_name1 = document.querySelector('.board_name1')

xbtn1.addEventListener("click", function(){
    board_name1.removeChild(board1);
    board_name1.removeChild(xbtn1);
})

const xbtn2 = document.getElementById('xbtn2');
const board_name2 = document.querySelector('.board_name2')

xbtn2.addEventListener("click", function(){
    board_name2.removeChild(board2);
    board_name2.removeChild(xbtn2);
})

const xbtn3 = document.getElementById('xbtn3');
const board_name3 = document.querySelector('.board_name3')

xbtn3.addEventListener("click", function(){
    board_name3.removeChild(board3);
    board_name3.removeChild(xbtn3);
})