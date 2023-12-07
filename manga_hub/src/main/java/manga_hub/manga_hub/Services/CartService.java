package manga_hub.manga_hub.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manga_hub.manga_hub.DTO.OrderItemsDTO;
import manga_hub.manga_hub.models.OrderItemsModel;
import manga_hub.manga_hub.models.ProductModel;
import manga_hub.manga_hub.repositories.CartRepository;

@Service
public class CartService {
    @Autowired
    private CartRepository repository;

    private productRepository productRepository;

        public void adicionarProdutoNoCarrinho(OrderItemsDTO orderItemsDTO, Long userId) {
        // Aqui, você pode fazer validações adicionais, se necessário
        ProductModel produto = productRepository.findById(orderItemsDTO.produto().getId())
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));

        OrderItemsModel orderItemsModel = new OrderItemsModel(produto, orderItemsDTO.quantidade, orderItemsDTO.preco);

        CarrinhoModel carrinho = carrinhoRepository.findByUserId(userId)
                .orElseGet(() -> new CarrinhoModel(userId));

        carrinho.adicionarItem(orderItemsModel);

        carrinhoRepository.save(carrinho);
    }
    

}
