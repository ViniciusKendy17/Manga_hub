function MostrarSenha(){
    var senha = document.getElementById('senha');
    var botao = document.getElementById('botao');
    if(senha.type === 'password'){
        senha.setAttribute('type','text');
        botao.classList.replace('bi-eye-fill','bi-eye-slash-fill');
    }
    else{
        senha.setAttribute('type','text');
        botao.classList.replace('bi-eye-fill','bi-eye-slash-fill');
    }
}