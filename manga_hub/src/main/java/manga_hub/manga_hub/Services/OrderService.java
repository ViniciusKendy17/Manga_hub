package manga_hub.manga_hub.Services;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import manga_hub.manga_hub.models.CartModel;
import manga_hub.manga_hub.models.OrderModel;
import manga_hub.manga_hub.models.UserModel;
import manga_hub.manga_hub.repositories.CartRepository;
import manga_hub.manga_hub.repositories.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService; 

    public OrderModel fecharPedido(String token) throws NotFoundException {
        // Obter o usuário a partir do token
        UserModel usuario = productService.getUserFromToken(token);

        // Obter o carrinho do usuário
        CartModel carrinho = cartService.getCarrinhoFromToken(token);

        // Verificar se o usuário tem um carrinho e se o carrinho não está vazio
        if (carrinho == null || carrinho.getItens().isEmpty()) {
            throw new NotFoundException("Não é possível fechar um pedido com carrinho vazio.");
        }

        // Criar um novo pedido
        OrderModel pedido = new OrderModel();
        pedido.setCliente(usuario);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setTotal(carrinho.getTotal());
        pedido.setStatus("pendente");

        // Salvar o pedido no banco de dados
        orderRepository.save(pedido);

        // Limpar o carrinho do usuário
            // Limpar a lista de itens do carrinho
            carrinho.getItens().clear();
        
            // Atualizar o total do carrinho para zero
            carrinho.setTotal(0);
        
            // Salvar o carrinho atualizado no banco de dados
            cartRepository.save(carrinho);

        return pedido;
    }
}
