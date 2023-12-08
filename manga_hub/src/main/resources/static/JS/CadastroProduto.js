

document.getElementById('botao-reg').addEventListener('click', cadastrarp);

function cadastrarp(){
    // Recuperar o token do localStorage
    

    event.preventDefault();
    const nome = document.getElementById('nome').value;
    const preco = document.getElementById('preco').value;
    const estoque = document.getElementById('estoque').value;
    const genero = document.getElementById('genero').value;
    const tipo = document.getElementById('tipo_produto').value;
    const isbn = document.getElementById('isbn').value;
    const imagem = document.getElementById('imagem').value;

    console.log(nome);
    console.log(preco);
    console.log(estoque);
    console.log(genero);
    console.log(tipo);
    console.log(isbn);
    console.log(imagem);

    const data = {
        nome: nome,
        preco: preco,
        estoque: estoque,
        genero: genero,
        tipo: tipo,
        isbn: isbn,
        imagem: imagem
    };
    
    const token = localStorage.getItem('token');
    fetch("http://localhost:8080/product/register", {
        method: "POST",
        
        headers:{
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify(data)
    })
    
    .then(response => {
        console.log(response.status);
        alert("Produto Registrado com sucesso !!")
        
        // Lógica adicional de tratamento de resposta aqui
    })
    .catch(error => {
    alert("Cadastro Inválido");
    });





}
