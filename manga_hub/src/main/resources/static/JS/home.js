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
            openProductModal(product.id);
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
  async function searchProductsHighlights(search) {
    console.log("Aqui");
    const destaque = search.toUpperCase();
    console.log(destaque);

    try {
    const response = await fetch(`http://localhost:8080/product/search/${destaque}?page=${currentPage}`);
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
        descriptionElement.textContent = `Resultados para: ${destaque}`;
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
            openProductModal(product.id);
          });

        // Adiciona evento de clique ao botão "Adicionar ao Carrinho"
          card.querySelector('.add-to-cart').addEventListener('click', (event) => {
          event.stopPropagation(); // Impede a propagação do evento para a div do card
          const productId = card.querySelector('.add-to-cart').dataset.productId;
          console.log('Produto adicionado ao carrinho. ID do produto:', productId);
          // Adicione aqui a lógica para adicionar ao carrinho
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

 // Selecione todos os elementos com a classe 'card-dest'
const cardDestElements = document.querySelectorAll('.card-dest');

// Adicione um ouvinte de evento de clique a cada elemento
cardDestElements.forEach(cardElement => {
  cardElement.addEventListener('click', function() {
    // Obtenha o valor do atributo data-search-term
    const searchTerm = this.dataset.searchTerm;
    // Verifique se searchTerm não é nulo ou indefinido
    if (searchTerm) {
      searchProductsHighlights(searchTerm);
    }
  });
});

// Obtém a referência para o SVG
const searchSvg = document.getElementById('search-svg');

// Adiciona um ouvinte de evento de clique ao SVG
searchSvg.addEventListener('click', function () {
  searchProducts();
});

// Adiciona um ouvinte de evento de tecla pressionada (keydown) à janela inteira
window.addEventListener('keydown', function (event) {
  // Verifica se a tecla pressionada é a tecla Enter (código 13)
  if (event.key === 'Enter') {
    searchProducts();
  }
});


// PESQUISA
let currentPage = 0;

// Função para pesquisar produtos paginados
async function searchProducts() {
  const searchTerm = document.getElementById('SearchProduct');

  const searchTermUpperCase = searchTerm.value.toUpperCase();
  try {
    const response = await fetch(`http://localhost:8080/product/search/${searchTermUpperCase}?page=${currentPage}`);
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
      descriptionElement.textContent = `Resultados para: ${searchTermUpperCase}`;
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
            openProductModal(product.id);
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

    //MODAL
    async function openProductModal(productId) {
        try {
        // Faça a requisição ao servidor para obter os detalhes do produto
        const response = await fetch(`http://localhost:8080/product/${productId}`);
        
        if (!response.ok) {
            throw new Error('Erro ao obter detalhes do produto');
        }
    
        const productDetails = await response.json();
    
        // Preencha os elementos do modal com os detalhes do produto
        const modal = document.getElementById('product-modal');
        const modalImage = document.getElementById('modal-product-image');
        const modalName = document.getElementById('modal-product-name');
        const modalPrice = document.getElementById('modal-product-price');
        const modalUser = document.getElementById('modal-product-user');
        const modalUserTel = document.getElementById('modal-product-user-tel');
    
        modalImage.src = productDetails.imagem;
        modalName.textContent = productDetails.nome;
        modalPrice.textContent = `Preço: R$ ${productDetails.preco}`;
        modalUser.textContent = productDetails.usuario.nome;
        modalUserTel.textContent = "31996494415";//productDetails.telefone;
    
        // Exiba o modal
        modal.style.display = 'block';
        } catch (error) {
        console.error('Erro ao abrir o modal:', error.message);
        }
    }
  
    // Função para fechar o modal
    function closeProductModal() {
        const modal = document.getElementById('product-modal');
        modal.style.display = 'none';
    }