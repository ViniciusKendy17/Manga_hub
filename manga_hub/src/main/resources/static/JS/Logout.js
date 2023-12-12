document.getElementById('botaoout').addEventListener('click', Fazerlogout);

function Fazerlogout (){
    var token = localStorage.removeItem('token');
    window.location.href = 'login.html'; 
}

