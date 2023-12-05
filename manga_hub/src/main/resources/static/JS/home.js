alert(localStorage.getItem('token'));

const products = [
    {
      id: 1,
      titulo: "One Piece",
      subtitulo: "Mangá de One Piece novo",
      imagem: "./IMGs/olhaEssaPorrakkkkkk - Copia.png",
      preco: 100.00,
      vendedor: "Rafael Picka de Mel",
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
      titulo: "Attack on Titan",
      subtitulo: "Mangá de Attack on Titan",
      imagem: "./IMGs/olhaEssaPorrakkkkkk - Copia.png",
      preco: 10.99,
      vendedor: "Eren Yeager",
      descricao: "Description for Attack on Titan manga.",
      id_vendedor: 789
    },
    {
        id: 4,
        titulo: "One Piece",
        subtitulo: "Mangá de One Piece novo",
        imagem: "./IMGs/olhaEssaPorrakkkkkk - Copia.png",
        preco: 100.00,
        vendedor: "Rafael Picka de Mel",
        descricao: "After being reborn, Zhao Mingxi had no choice but to mooch off of Fu group's young master's luck to avoid death by terminal illness. Although his ears would turn so red as if they're bleeding whenever he sees her, he still keeps up an act of prideful indifference: 'Can you stop being so clingy?' The next day.",
        id_vendedor: 123
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
        id: 6,
        titulo: "Attack on Titan",
        subtitulo: "Mangá de Attack on Titan",
        imagem: "./IMGs/olhaEssaPorrakkkkkk - Copia.png",
        preco: 10.99,
        vendedor: "Eren Yeager",
        descricao: "Description for Attack on Titan manga.",
        id_vendedor: 789
      },
      {
        id: 7,
        titulo: "One Piece",
        subtitulo: "Mangá de One Piece novo",
        imagem: "./IMGs/olhaEssaPorrakkkkkk - Copia.png",
        preco: 100.00,
        vendedor: "Rafael Picka de Mel",
        descricao: "After being reborn, Zhao Mingxi had no choice but to mooch off of Fu group's young master's luck to avoid death by terminal illness. Although his ears would turn so red as if they're bleeding whenever he sees her, he still keeps up an act of prideful indifference: 'Can you stop being so clingy?' The next day.",
        id_vendedor: 123
      },
      {
        id: 8,
        titulo: "Naruto",
        subtitulo: "Mangá de Naruto",
        imagem: "./IMGs/olhaEssaPorrakkkkkk - Copia.png",
        preco: 8.99,
        vendedor: "Sasuke Uchiha",
        descricao: "Description for Naruto manga.",
        id_vendedor: 456
      }
  ];

  const productMangaWrapper = document.querySelector('#manga');

  const productLightWrapper = document.querySelector('#lightNovels');

  // Função para criar um card
  function createProductCard(product) {
    const card = document.createElement('div');
    card.classList.add('card-product');
    card.dataset.productId = product.id; // Adiciona o ID como um atributo de dados
  
    const image = document.createElement('img');
    image.src = product.imagem;
    image.alt = '';
  
    const content = document.createElement('div');
    content.classList.add('product-content');
  
    const title = document.createElement('h5');
    title.classList.add('title');
    title.textContent = product.titulo;
  
    /*const subtitle = document.createElement('p');
    subtitle.classList.add('subtitle');
    subtitle.textContent = product.subtitulo;*/
  
    const price = document.createElement('p');
    price.classList.add('price');
    price.textContent = `RS$ ${product.preco.toFixed(2)}`;
  
    const vendedor = document.createElement('p');
    vendedor.classList.add('vendedor');
    vendedor.textContent = product.vendedor;
  
    /*const desc = document.createElement('p');
    desc.classList.add('desc');
    desc.textContent = product.descricao;*/
  
    content.appendChild(title);
    //content.appendChild(subtitle);
    content.appendChild(price);
    content.appendChild(vendedor);
    //content.appendChild(desc);
  
    card.appendChild(image);
    card.appendChild(content);
  
    // Adiciona um ouvinte de evento de clique ao card
    card.addEventListener('click', function() {
      const productId = this.dataset.productId;
      console.log('ID do produto clicado:', productId);
    });
  
    return card;
  }
  
  //LISTANDO Mangás
  products.forEach(product => {
    const card = createProductCard(product);
    productMangaWrapper.appendChild(card);
  });

   //LISTANDO Light Novels
   products.forEach(product => {
    const card = createProductCard(product);
    productLightWrapper.appendChild(card);
  });