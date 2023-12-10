package manga_hub.manga_hub.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import javassist.NotFoundException;
import manga_hub.manga_hub.DTO.OrderItemsDTO;
import manga_hub.manga_hub.Security.TokenService;
import manga_hub.manga_hub.models.CartItemModel;
import manga_hub.manga_hub.models.CartModel;
import manga_hub.manga_hub.models.ProductModel;
import manga_hub.manga_hub.models.UserModel;
import manga_hub.manga_hub.repositories.CartRepository;
import manga_hub.manga_hub.repositories.UserRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    public CartItemModel adicionarItemAoCarrinho(OrderItemsDTO orderItemsDTO, String token) throws NotFoundException {
        // Recuperar o usuário a partir do token (você precisará implementar isso)
        UserModel usuario = getUserFromToken(token);
    
        Long idProduto = Long.parseLong(orderItemsDTO.id());
    
        // Recuperar o produto pelo ID
        ProductModel produto = productService.getById(idProduto)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));
    
        // Criar um novo item do pedido
        CartItemModel novoItem = new CartItemModel();
        novoItem.setProduto(produto);
        novoItem.setQuantidade(orderItemsDTO.quantidade());
        novoItem.setTotal(orderItemsDTO.quantidade() * produto.getPreco());
    
        // Verificar se o usuário já possui um carrinho
        CartModel carrinho = getCarrinhoFromToken(token);
        if (carrinho == null) {
            carrinho = new CartModel();
            carrinho.setUsuario(usuario);
            carrinho.setItens(new ArrayList<>()); // Inicializar a lista de itens se for nula
        }
        // SETANDO CARRINHO EM ITEM
        novoItem.setCarrinho(carrinho);
    
        // Adicionar o novo item ao carrinho
        carrinho.addItem(novoItem);
    
        // Atualizar o total do carrinho
        carrinho.setTotal(calcularTotalCarrinho(carrinho));
    
        cartRepository.saveAndFlush(carrinho);
    
        return novoItem;
    }

    public List<CartItemModel> listItens(String token) throws NotFoundException {
        // Obter o carrinho do usuário
        CartModel carrinho = getCarrinhoFromToken(token);
    
        // Verificar se o usuário tem um carrinho
        if (carrinho == null) {
            throw new NotFoundException("Carrinho não encontrado para o usuário.");
        }
    
        // Retornar a lista de itens do carrinho
        return carrinho.getItens();
    }

    private Double calcularTotalCarrinho(CartModel carrinho) {
        return carrinho.getItens().stream()
                .mapToDouble(CartItemModel::getTotal)
                .sum();
    }

    private UserModel getUserFromToken(String token) {
        String login = tokenService.validateToken(token);

        if (!login.isEmpty()) {
            UserModel user = (UserModel) userRepository.findByLogin(login);

            if (user != null) {
                return user;
            }
        }

        throw new BadCredentialsException("Token inválido ou usuário não encontrado");
    }

    private CartModel getCarrinhoFromToken(String token) {
        UserModel user = getUserFromToken(token);
        return getCartFromUser(user);
    }

    public CartModel getCartFromUser(UserModel user) {
        return cartRepository.findByUsuario(user);
    }
}

