

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
      localStorage.setItem('token', token);
      console.log(response.status);
      alert(response.status());
      
      window.location.href = "index.html";
    })
    .catch(error => {
      console.error('Erro:', error);
    });
  });