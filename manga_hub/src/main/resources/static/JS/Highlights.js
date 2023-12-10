    const currentPage = 0;

    async function searchProductsHighlights(search) {
      
        try {
        const response = await fetch(`http://localhost:8080/product/search/${search}?page=${currentPage}`);
        if (!response.ok) {
            throw new Error('Erro ao obter dados da API');
        }
    
        const page = await response.json();
    
        const containerMain = document.getElementById('container-main');
        const resultsContainer = document.getElementById('results-container-home');
    
        // Esconde container-main e mostra results-container
        if (containerMain) {
            containerMain.style.display = 'none';
        }
    
        if (resultsContainer) {
            resultsContainer.style.display = 'flex';
    
            // Limpa o conteúdo atual dentro de resultsContainer
            resultsContainer.innerHTML = '';
    
            // Adiciona o h2 e o p diretamente dentro do resultsContainer
            const titleSearch = document.createElement('h2');
            titleSearch.classList.add('search-h2');
            titleSearch.textContent = 'Resultados da pesquisa:';
            resultsContainer.appendChild(titleSearch);
    
            const descriptionElement = document.createElement('p');
            descriptionElement.classList.add('search-p');
            descriptionElement.textContent = `Resultados para: ${search}`;
            resultsContainer.appendChild(descriptionElement);
    
            // Acessa a div com a classe "product-list" dentro do resultsContainer
            const productListContainer = document.createElement('div');
            productListContainer.classList.add('product-list');
            resultsContainer.appendChild(productListContainer);
    
            // Verifica se há produtos na resposta
            if (page.content.length === 0) {
            alert('Nenhum produto encontrado.');
            return; // Para a execução da função
            }
    
            // Cria novos elementos (cards de produtos) com base nos dados recebidos
            page.content.forEach(product => {
            const card = document.createElement('div');
            card.classList.add('card-product');
            card.innerHTML = `
                <img src="${product.imagem}" alt="${product.nome}">
                <div class="content">
                <h3 class="product-name">${product.nome}</h3>
                <p>Preço: R$ ${product.preco}</p>
                <p>Vendedor: ${product.usuario.nome}</p>
                </div>
                <button class="add-to-cart" data-product-id="${product.id}">Adicionar ao Carrinho</button>
            `;
    
            // Adiciona evento de clique à div do card
            card.addEventListener('click', () => {
                const productId = product.id;
                console.log('Clique na div do card. ID do produto:', productId);
                // Adicione aqui a lógica desejada ao clicar na div do card
            });
    
            // Adiciona evento de clique ao botão "Adicionar ao Carrinho"
            card.querySelector('.add-to-cart').addEventListener('click', (event) => {
                event.stopPropagation(); // Impede a propagação do evento para a div do card
                const productId = card.querySelector('.add-to-cart').dataset.productId;
                console.log('Produto adicionado ao carrinho. ID do produto:', productId);
                // Adicione aqui a lógica para adicionar ao carrinho
            });
    
            // Adiciona o card dentro de productListContainer
            productListContainer.appendChild(card);
            });
        } else {
            console.error('Elemento resultsContainer não encontrado.');
        }
    
        } catch (error) {
        console.error('Erro durante a solicitação:', error.message);
        }
    }