document.getElementById('SearchProduct').addEventListener('keydown', function (event) {
    if (event.key === 'Enter') {
        event.preventDefault();
        ProcurarProduto();
    }
});

function ProcurarProduto() {
    const nome = document.getElementById('SearchProduct').value;

    fetch(`http://localhost:8080/product/search/${nome}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        },
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`Erro na requisição: ${response.statusText}`);
        }
        return response.json();
    })
    .then(data => {
        displayResults(data);
    })
    .catch(error => {
        console.error('Erro ao buscar produtos:', error);
        displayError('Erro ao buscar produtos. Tente novamente mais tarde.');
    });
}

function displayResults(results) {
    var resultsContainer = document.getElementById('resultsContainer');
    resultsContainer.innerHTML = '';

    console.log(results)


    if (results.length >= 0) {
        results.forEach(product => {
            const card = document.createElement('div');
            card.classList.add('card-product');
            card.dataset.productId = product.id;
            
            // Adicione aqui outros detalhes do produto que você deseja exibir no card
            resultsContainer.appendChild(card);
        });
    } else {
        alert("Nenhum resultado encontrado");
    }
}

function displayError(errorMessage) {
    var resultsContainer = document.getElementById('resultsContainer');
    resultsContainer.innerHTML = `<p>${errorMessage}</p>`;
}
