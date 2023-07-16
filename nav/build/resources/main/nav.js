const navbarMenu = document.querySelector('.navbar__menu');
const navbarToggleBtn = document.querySelector('.navbar__toggle-btn');
navbarToggleBtn.addEventListener('click', () => {
    navbarMenu.classList.toggle('open');
});

const word1 = document.getElementById('word1');
const word2 = document.getElementById('word2');
const word3 = document.getElementById('word3');

word_btn.addEventListener('click', () => {
    changeWords(word1, word2, word3);
});

const changeWords = (word1, word2, word3) => {
    fetch('https://random-word-api.herokuapp.com/word?number=3')
        .then(response => response.json())
        .then(data => {
            console.log(data);
            word1.innerText = data[0];
            word2.innerText = data[1];
            word3.innerText = data[2];
        })
        .catch(error => {
            console.log(error);
        });
};