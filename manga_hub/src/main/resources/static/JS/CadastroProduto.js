

document.getElementById('botao-reg').addEventListener('click', cadastrarp);

function cadastrarp(){
    // Recuperar o token do localStorage
    const token = localStorage.getItem('token');
    
    event.preventDefault();
    let nome1 = document.getElementById('nome');
    const nome = nome1.value.toUpperCase(); 

    const descricao = document.getElementById('descricao').value;
    const preco = document.getElementById('preco').value;
    const estoque = document.getElementById('estoque').value;
    const genero = document.getElementById('genero').value;

    var selected = document.getElementById('tipo_produto');
    const tipo = selected.value;

    const isbn = document.getElementById('isbn').value;
    const imagem = document.getElementById('imagem').value;

    const data = {
        nome: nome,
        descricao: descricao,
        preco: preco,
        estoque: estoque,
        genero: genero,
        tipo: tipo,
        isbn: isbn,
        imagem: imagem
    };
    
    fetch("http://localhost:8080/product/register", {
        method: "POST",
        
        headers:{
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify(data)
    })
    
    .then(response => {
        alert("Produto Registrado com sucesso !!");
        location.reload();
        
        
        // Lógica adicional de tratamento de resposta aqui
    })
    .catch(error => {
    console.log(error);
    alert("Erro ao cadastrar");
    });

}
