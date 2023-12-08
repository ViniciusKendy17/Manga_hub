package manga_hub.manga_hub.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manga_hub.manga_hub.DTO.OrderItemsDTO;
import manga_hub.manga_hub.exceptions.NotFoundProductException;
import manga_hub.manga_hub.models.CartModel;
import manga_hub.manga_hub.models.OrderItemsModel;
import manga_hub.manga_hub.models.ProductModel;
import manga_hub.manga_hub.repositories.CartRepository;
import manga_hub.manga_hub.repositories.ProductRepository;

@Service
public class CartService {
    @Autowired
    private CartRepository carrinhoRepository;
    @Autowired
    private ProductRepository productRepository;

        public void adicionarProdutoNoCarrinho(OrderItemsDTO orderItemsDTO, Long userId) {
        // Aqui, você pode fazer validações adicionais, se necessário
        ProductModel produto = productRepository.findById(orderItemsDTO.id())
                .orElseThrow(() -> new NotFoundProductException("Produto não encontrado"));

        OrderItemsModel orderItemsModel = new OrderItemsModel(produto, orderItemsDTO.quantidade(), orderItemsDTO.preco());

        CartModel carrinho = carrinhoRepository.findByUserId(userId)
                .orElseGet(() -> new CartModel(userId));

        carrinho.adicionarItem(orderItemsModel);

        carrinhoRepository.save(carrinho);
    }
    

}
