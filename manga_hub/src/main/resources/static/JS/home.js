const products = [
        {
          id: 1,
          titulo: "One Piece",
          subtitulo: "Mangá de One Piece novo",
          imagem: "./IMGs/olhaEssaPorrakkkkkk - Copia.png",
          preco: 100.00,
          vendedor: "Rafael Correa",
          descricao: "After being reborn, Zhao Mingxi had no choice but to mooch off of Fu group's young master's luck to avoid death by terminal illness. Although his ears would turn so red as if they're bleeding whenever he sees her, he still keeps up an act of prideful indifference: 'Can you stop being so clingy?' The next day.",
          id_vendedor: 123
        },
        {
          id: 2,
          titulo: "Naruto",
          subtitulo: "Mangá de Naruto",
          imagem: "./IMGs/olhaEssaPorrakkkkkk - Copia.png",
          preco: 8.99,
          vendedor: "Sasuke Uchiha",
          descricao: "Description for Naruto manga.",
          id_vendedor: 456
        },
        {
          id: 3,
          titulo: "Naruto",
          subtitulo: "Mangá de Naruto",
          imagem: "./IMGs/olhaEssaPorrakkkkkk - Copia.png",
          preco: 8.99,
          vendedor: "Sasuke Uchiha",
          descricao: "Description for Naruto manga.",
          id_vendedor: 456
        },
        {
          id: 4,
          titulo: "Naruto",
          subtitulo: "Mangá de Naruto",
          imagem: "./IMGs/olhaEssaPorrakkkkkk - Copia.png",
          preco: 8.99,
          vendedor: "Sasuke Uchiha",
          descricao: "Description for Naruto manga.",
          id_vendedor: 456
        },
        {
          id: 5,
          titulo: "Naruto",
          subtitulo: "Mangá de Naruto",
          imagem: "./IMGs/olhaEssaPorrakkkkkk - Copia.png",
          preco: 8.99,
          vendedor: "Sasuke Uchiha",
          descricao: "Description for Naruto manga.",
          id_vendedor: 456
        },
        {
          id: 5,
          titulo: "Naruto",
          subtitulo: "Mangá de Naruto",
          imagem: "./IMGs/olhaEssaPorrakkkkkk - Copia.png",
          preco: 8.99,
          vendedor: "Sasuke Uchiha",
          descricao: "Description for Naruto manga.",
          id_vendedor: 456
        } ,
        {
          id: 5,
          titulo: "Naruto",
          subtitulo: "Mangá de Naruto",
          imagem: "./IMGs/olhaEssaPorrakkkkkk - Copia.png",
          preco: 8.99,
          vendedor: "Sasuke Uchiha",
          descricao: "Description for Naruto manga.",
          id_vendedor: 456
        },
        {
          id: 5,
          titulo: "Naruto",
          subtitulo: "Mangá de Naruto",
          imagem: "./IMGs/olhaEssaPorrakkkkkk - Copia.png",
          preco: 8.99,
          vendedor: "Sasuke Uchiha",
          descricao: "Description for Naruto manga.",
          id_vendedor: 456
        } 
      ];
    
     // Função para criar um card de produto
     function createProductCard(product) {
      const card = document.createElement('div');
      card.classList.add('card-product');
      card.dataset.productId = product.id;

      const imgDiv = document.createElement('div');
      imgDiv.classList.add('image');
      const img = document.createElement('img');
      img.src = product.imagem;
      img.alt = 'Product Image';
      imgDiv.appendChild(img);
    
      const content = document.createElement('div');
      content.classList.add('content');
    
      const title = document.createElement('h3');
      title.classList.add('product-name');
      title.textContent = product.titulo;
    
      const price = document.createElement('p');
      price.classList.add('price');
      price.textContent = `RS$ ${product.preco ? product.preco.toFixed(2) : 'N/A'}`;
    
      const vendedor = document.createElement('p');
      vendedor.classList.add('vendedor');
      vendedor.textContent = `Vendedor: ${product.vendedor}`;
    
      const addButton = document.createElement('button');
      addButton.classList.add('add-to-cart-button');
      addButton.textContent = 'Adicionar ao Carrinho';
    
      addButton.addEventListener('click', function () {
        const productId = card.dataset.productId;
        console.log('Botão de adicionar clicado para o produto ID:', productId);
        // Adicione aqui a lógica para adicionar ao carrinho
      });
    
      content.appendChild(title);
      content.appendChild(price);
      content.appendChild(vendedor);
      content.appendChild(addButton);
    
      card.appendChild(imgDiv);
      card.appendChild(content);
    
      // Adiciona um ouvinte de evento de clique ao card
      card.addEventListener('click', function () {
        const productId = this.dataset.productId;
        console.log('ID do produto clicado:', productId);
      });
    
      return card;
    }

// Adiciona os cards à lista
const cardList = document.querySelector('.product-list');
products.forEach((product) => {
  const card = createProductCard(product);
  cardList.appendChild(card);
});

const mangaList = document.querySelector('.manga-list');
products.forEach((product) => {
  const card = createProductCard(product);
  mangaList.appendChild(card);
});

const lightList = document.querySelector('.light-list');
products.forEach((product) => {
  const card = createProductCard(product);
  lightList.appendChild(card);
});

const actList = document.querySelector('.act-list');
products.forEach((product) => {
  const card = createProductCard(product);
  actList.appendChild(card);
});

