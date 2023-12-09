document.addEventListener('DOMContentLoaded', () => {
  let productsLast = [];
  let productsMangas = [];
  let productsLightNovels = [];
  let productsActionFigures = [];

  fetch('http://localhost:8080/home/')
      .then(response => response.json())
      .then(data => {
          productsLast = data;
          renderizarProdutos(productsLast, 'last-products');
      })
      .catch(error => console.error('Erro na requisição para últimos produtos:', error));

  fetch('http://localhost:8080/home/')
      .then(response => response.json())
      .then(data => {
          productsMangas = data;
          renderizarProdutos(productsMangas, 'mangas');
      })
      .catch(error => console.error('Erro na requisição para mangás:', error));

  fetch('http://localhost:8080/home/')
      .then(response => response.json())
      .then(data => {
          productsLightNovels = data;
          renderizarProdutos(productsLightNovels, 'lightNovels');
      })
      .catch(error => console.error('Erro na requisição para light novels:', error));

  fetch('http://localhost:8080/home/')
      .then(response => response.json())
      .then(data => {
          productsActionFigures = data;
          renderizarProdutos(productsActionFigures, 'acts');
      })
      .catch(error => console.error('Erro na requisição para action figures:', error));

  function renderizarProdutos(products, sectionId) {
      const secao = document.getElementById(sectionId);
      const divLista = secao.querySelector('.product-list, .manga-list, .light-list, .act-list');

      products.forEach((product) => {
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
            // Adicione aqui a lógica para adicionar ao carrinho, se necessário
        });

          divLista.appendChild(card);
      });
  }
});

// Obtém a referência para o SVG
const searchSvg = document.getElementById('search-svg');
// Adiciona um ouvinte de evento de clique ao SVG
searchSvg.addEventListener('click', function () {
  searchProducts();
});

// PESQUISA
let currentPage = 0;
// Função para pesquisar produtos paginados
// Função para pesquisar produtos paginados
async function searchProducts() {
    const searchTerm = document.getElementById('SearchProduct').value;
    alert("salve");
    // Faz a solicitação ao backend com o número da página
    try {
      const response = await fetch(`http://localhost:8080/product/search/${searchTerm}?page=${currentPage}`);
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
        resultsContainer.style.display = 'block';
      } else {
        console.error('Elemento resultsContainer não encontrado.');
      }
  
      // Acessa a div com a classe "product-list" dentro do resultsContainer
      const productListContainer = resultsContainer.querySelector('.product-list');
  
      // Limpa o conteúdo atual dentro de productListContainer
      productListContainer.innerHTML = '';
  
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
  
    } catch (error) {
      console.error('Erro durante a solicitação:', error.message);
    }
  }