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