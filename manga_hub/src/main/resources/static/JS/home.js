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

  // Função para renderizar os produtos
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
        card.querySelector('.add-to-cart').addEventListener('click', async (event) => {
            event.stopPropagation(); // Impede a propagação do evento para a div do card
            const productId = card.querySelector('.add-to-cart').dataset.productId;
            // Chama a função para adicionar o produto ao carrinho
            await adicionarProdutoAoCarrinho(productId);
            // Adicione aqui a lógica para adicionar ao carrinho, se necessário
            console.log('Produto adicionado ao carrinho. ID do produto:', productId);
        });

        divLista.appendChild(card);
    });
}

  // Função para buscar produtos destacados
  async function searchProductsHighlights(search) {
      const destaque = search.toUpperCase();
      try {
          const response = await fetch(`http://localhost:8080/product/search/${destaque}?page=${currentPage}`);
          if (!response.ok) {
              throw new Error('Erro ao obter dados da API');
          }

        const page = await response.json();

        const containerMain = document.getElementById('container-main');
        const resultsContainer = document.getElementById('results-container-home');

        if (containerMain) {
            containerMain.style.display = 'none';
        }

        if (resultsContainer) {
            resultsContainer.style.display = 'flex';
        }

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

          // Renderiza os produtos encontrados
          renderizarProdutos(page.content, 'results-container-home');
      } catch (error) {
          console.error('Erro durante a solicitação:', error.message);
      }
  }

  // Selecione todos os elementos com a classe 'card-dest'
  const cardDestElements = document.querySelectorAll('.card-dest');
 

  // Adicione um ouvinte de evento de clique a cada elemento
  let searchTerm;  // Declare searchTerm fora do escopo do evento de clique

  cardDestElements.forEach(cardElement => {
      cardElement.addEventListener('click', function () {
          // Obtenha o valor do atributo data-search-term
          searchTerm = this.dataset.searchTerm;
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
      console.log("aqui:"+searchTerm);
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
        }

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

          // Renderiza os produtos encontrados
          renderizarProdutos(page.content, 'results-container-home');
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
        const botaoCarrinho = document.getElementById('cartModal');

        botaoCarrinho.addEventListener('click', async () => {
            // Chama a função para adicionar o produto ao carrinho
            await adicionarProdutoAoCarrinho(productId);
            // Adicione aqui a lógica para adicionar ao carrinho, se necessário
            console.log('Produto adicionado ao carrinho. ID do produto:', productId);
        });

        modalImage.src = productDetails.imagem;
        modalName.textContent = productDetails.nome;
        modalPrice.textContent = `Preço: R$ ${productDetails.preco}`;
        modalUser.textContent = `Vendedor: ${productDetails.usuario.nome}`;
        modalUserTel.textContent = `Telefone: ${productDetails.telefone || 'N/A'}`;

        // Exiba o modal
        modal.style.display = 'block';
    } catch (error) {
        console.error('Erro ao abrir o modal:', error.message);
    }
}

  const closeButton = document.getElementById('close');
  if (closeButton) {
      closeButton.addEventListener('click', closeProductModal);
  }

  // Função para fechar o modal
  function closeProductModal() {
      const modal = document.getElementById('product-modal');
      modal.style.display = 'none';
  }

  // Função para adicionar o produto ao carrinho
  async function adicionarProdutoAoCarrinho(productId) {
      const token = localStorage.getItem('token');
      try {
          const response = await fetch('http://localhost:8080/cart/', {
              method: 'POST',
              headers: {
                  'Content-Type': 'application/json',
                  "Authorization": `Bearer ${token}`
              },
              body: JSON.stringify({
                  id: productId,
                  quantidade: 1, // ou a quantidade desejada
              }),
          });

          if (!response.ok) {
              throw new Error('Erro ao adicionar o produto ao carrinho');
          }
          console.log('Produto adicionado ao carrinho com sucesso!');
      } catch (error) {
          console.error('Erro ao adicionar o produto ao carrinho:', error.message);
      }
  }

    const cartIcon = document.getElementById('cart-out-icon');
      if (cartIcon) {
          cartIcon.addEventListener('click', openCartModal);
      }

      // Adiciona o evento de clique ao botão de fechar no modal
      const closeCartButton = document.getElementById('close-cart-modal');
      if (closeCartButton) {
          closeCartButton.addEventListener('click', closeCartModal);
      }

    // Função para abrir o modal do carrinho
    async function openCartModal() {
    const cartModal = document.getElementById('cart-modal');
    const cartItemTable = document.getElementById('cart-item-table');
    const cartTotalElement = document.getElementById('cart-total');
    const token = localStorage.getItem('token');

    try {
        // Realiza um fetch para obter os itens do carrinho
        const response = await fetch('http://localhost:8080/cart/', {
            method: 'GET',
            headers: {
                "Authorization": `Bearer ${token}`
            },
        });

        if (!response.ok) {
            throw new Error('Erro ao obter os itens do carrinho');
        }

        const cartItems = await response.json();

        // Limpa a tabela antes de preenchê-la
        cartItemTable.innerHTML = '';

        // Adiciona a linha de cabeçalho à tabela
        const headerRow = cartItemTable.insertRow(0);
        const headerCells = ['Produto', 'Imagem', 'Preço', 'Quantidade', 'Total', 'Ação'];

        headerCells.forEach((cellText, index) => {
            const cell = headerRow.insertCell(index);
            cell.textContent = cellText;
        });

        // Preenche a tabela de itens e calcula o total do carrinho
        let cartTotal = 0;
        cartItems.forEach(item => {
            const row = cartItemTable.insertRow(-1);

            row.innerHTML = `
                <td>${item.produto.nome}</td>
                <td><img src="${item.produto.imagem}" alt="${item.produto.nome}" class="img-cart"></td>
                <td>R$ ${item.produto.preco.toFixed(2)}</td>
                <td>${item.quantidade}</td>
                <td>R$ ${item.total.toFixed(2)}</td>
                <td><button class="remove-item-btn" data-item-id="${item.id}">Remover Item</button></td>
            `;

            // Adiciona evento de clique ao botão de remover item
            const removeItemButton = row.querySelector('.remove-item-btn');
            if (removeItemButton) {
                removeItemButton.addEventListener('click', async () => {
                    // Chama o endpoint de exclusão do item
                    await removeItemFromCart(item.id);
                    // Atualiza o modal após a remoção do item
                    openCartModal();
                });
            }

            // Atualiza o total do carrinho
            cartTotal += item.total;
        });

        // Exibe o total do carrinho
        cartTotalElement.textContent = `Total do Carrinho: R$ ${cartTotal.toFixed(2)}`;

        // Exibe o modal
        if (cartModal) {
            cartModal.style.display = 'block';
        }
    } catch (error) {
        console.error('Erro ao obter os itens do carrinho:', error.message);
    }
    }   

    async function removeItemFromCart(itemId) {
        const token = localStorage.getItem('token');
    
        try {
            const response = await fetch(`http://localhost:8080/cart/${itemId}`, {
                method: 'DELETE',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
    
            if (!response.ok) {
                throw new Error('Erro ao remover o item do carrinho');
            }
        } catch (error) {
            console.error('Erro ao remover o item do carrinho:', error.message);
        }
    }

    // Função para fechar o modal do carrinho
    function closeCartModal() {
      const cartModal = document.getElementById('cart-modal');
      if (cartModal) {
          cartModal.style.display = 'none';
      }
    }
});

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
        card.querySelector('.add-to-cart').addEventListener('click', async (event) => {
            event.stopPropagation(); // Impede a propagação do evento para a div do card
            const productId = card.querySelector('.add-to-cart').dataset.productId;
            // Chama a função para adicionar o produto ao carrinho
            await adicionarProdutoAoCarrinho(productId);
            // Adicione aqui a lógica para adicionar ao carrinho, se necessário
            console.log('Produto adicionado ao carrinho. ID do produto:', productId);
        });

        divLista.appendChild(card);
    });
}



async function searchProductsSection(Search) {
  let currentPage = 0;

const destaqueSection = Search.toUpperCase();

console.log("aaaaa" + destaqueSection);
try {
    const response = await fetch(`http://localhost:8080/product/search/${destaqueSection}?page=${currentPage}`);
    if (!response.ok) {
        throw new Error('Erro ao obter dados da API');
    }

  const page = await response.json();

  const containerMain = document.getElementById('container-main');
  const resultsContainer = document.getElementById('results-container-home');

  if (containerMain) {
      containerMain.style.display = 'none';
  }

  if (resultsContainer) {
      resultsContainer.style.display = 'flex';
  }

    // Limpa o conteúdo atual dentro de resultsContainer
    resultsContainer.innerHTML = '';

    // Adiciona o h2 e o p diretamente dentro do resultsContainer
    const titleSearch = document.createElement('h2');
    titleSearch.classList.add('search-h2');
    titleSearch.textContent = 'Resultados da pesquisa:';
    resultsContainer.appendChild(titleSearch);

    const descriptionElement = document.createElement('p');
    descriptionElement.classList.add('search-p');
    descriptionElement.textContent = `Resultados para: ${destaqueSection}`;
    resultsContainer.appendChild(descriptionElement);

    // Acessa a div com a classe "product-list" dentro do resultsContainer
    const productListContainer = document.createElement('div');
    productListContainer.classList.add('product-list');
    resultsContainer.appendChild(productListContainer);

    // Renderiza os produtos encontrados
    renderizarProdutos(page.content, 'results-container-home');
} catch (error) {
    console.error('Erro durante a solicitação:', error.message);
}
}

// Selecione todos os elementos com a classe 'card-dest'
const sectionproducts = document.getElementById('Between-section-product');


// Adicione um ouvinte de evento de clique a cada elemento
let searchTermSection;  // Declare searchTerm fora do escopo do evento de clique

sectionproducts.forEach(cardElement => {
cardElement.addEventListener('click', function () {
    // Obtenha o valor do atributo data-search-term
    searchTermSection = this.dataset.searchTermSection;
    // Verifique se searchTerm não é nulo ou indefinido
    if (searchTermSection) {
        searchProducts(searchTermSection);
    }
});
});

// Obtém a referência para o SVG

const clickSection = document.getElementById('out-Between-section');


// Adiciona um ouvinte de evento de clique ao SVG
clickSection.addEventListener('click', function () {
searchProducts();
});

// Adiciona um ouvinte de evento de tecla pressionada (keydown) à janela inteira
window.addEventListener('keydown', function (event) {
// Verifica se a tecla pressionada é a tecla Enter (código 13)
if (event.key === 'Enter') {
    searchProducts();
}
})

