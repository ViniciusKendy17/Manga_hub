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

document.forms["login"].addEventListener('submit', function(event) {
    console.log("entrou");
    event.preventDefault(); // Impede o envio do formulário padrão
  
    // Recupera os valores dos campos de entrada
    const login = document.forms["login"]["email"].value;
    const senha = document.forms["login"]["password"].value;
  
    //requisição usando fetch:
    fetch('http://localhost:8080/auth/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({login, senha}),
    })
    .then(response => response.json())
    .then(data => {
      //Manipular a resposta do servidor
      const token = data.token;
      console.log('Token recebido:', token);
      
      // window.location.href = '/outra-pagina';
    })
    .catch(error => {
      console.error('Erro:', error);
    });
  });