document.getElementById('botao').addEventListener('click', Fazerlogout);

function Fazerlogout (){
    
    var token = localStorage.removeItem('token'); // Substitua 'seuToken' pelo nome do seu token
    window.location.href = 'login.html'; 
}

