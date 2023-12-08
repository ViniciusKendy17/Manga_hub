package manga_hub.manga_hub.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import manga_hub.manga_hub.DTO.CartDTO;
import manga_hub.manga_hub.DTO.OrderItemsDTO;
import manga_hub.manga_hub.Security.TokenService;
import manga_hub.manga_hub.exceptions.NotFoundProductException;
import manga_hub.manga_hub.models.CartModel;
import manga_hub.manga_hub.models.OrderItemsModel;
import manga_hub.manga_hub.models.ProductModel;
import manga_hub.manga_hub.models.UserModel;
import manga_hub.manga_hub.repositories.CartRepository;
import manga_hub.manga_hub.repositories.ProductRepository;
import manga_hub.manga_hub.repositories.UserRepository;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    UserRepository uRepository;
    @Autowired
    TokenService service;

    //criar produto no carrinho
    public void addProduct(OrderItemsDTO orderItemsDTO, String Token) {
        UserModel user = getUserFromToken(Token);
        ProductModel produto = productRepository.findById(orderItemsDTO.id())
                .orElseThrow(() -> new NotFoundProductException("Produto não encontrado"));

        OrderItemsModel orderItemsModel = new OrderItemsModel(produto, orderItemsDTO.quantidade(), orderItemsDTO.preco() * orderItemsDTO.quantidade());

        CartModel carrinho = cartRepository.findById(user.getId())
                .orElseGet(() -> new CartModel(user.getId()));
        carrinho.setUsuario(user);
        carrinho.getItens().add(orderItemsModel);
        cartRepository.save(carrinho);
    }

    //deletar produto do carrinho
    public void deleteProduct(Long productId, String Token) {
        UserModel user = getUserFromToken(Token);
        CartModel carrinho = cartRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundProductException("Carrinho não encontrado"));
        OrderItemsModel orderItemsModel = carrinho.getItens().stream()
                .filter(oi -> oi.getProduto().getId() == productId)
                .findFirst()
                .orElseThrow(() -> new NotFoundProductException("Produto não encontrado no carrinho"));
        carrinho.getItens().remove(orderItemsModel);
        cartRepository.save(carrinho);
    }

    //listar produtos do carrinho
    public List<OrderItemsModel> listProductsInCart(String Token) {
        UserModel user = getUserFromToken(Token);
        CartModel carrinho = cartRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundProductException("Carrinho não encontrado"));
        return carrinho.getItens();
    }

    public UserModel getUserFromToken(String token) {
        String login = service.validateToken(token);

        if (!login.isEmpty()) {
            UserModel user = (UserModel) uRepository.findByLogin(login);
            return user;
        }

        throw new BadCredentialsException("Token inválido");
    }
}
