const form = document.querySelector('form');
const nome = document.getElementById('nome');
const email = document.getElementById('email');
const senha = document.getElementById('senha');
const cpf = document.getElementById('cpf');


form.addEventListener('submit',function(event){
   event.preventDefault(); 

   Cadastrar();

});
function Cadastrar() {
    fetch("http://localhost:8080/auth/register"),
{
    headers:{
        "Accept":'aplication/json',
        "Content-Type": 'aplication/json'
    },

    method:"POST",
    body: JSON.stringify({
        nome: nome.value,
        email: email.value,
        senha: senha.value,
        cpf: cpf.value
    })
.then(function (res) {console.log(res)})
.cacht(function(res){console.log(res)})

 }
}














