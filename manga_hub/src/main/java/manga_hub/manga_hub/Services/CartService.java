package manga_hub.manga_hub.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import manga_hub.manga_hub.DTO.CartDTO;
import manga_hub.manga_hub.DTO.HomeDTO;
import manga_hub.manga_hub.DTO.HomeUserDTO;
import manga_hub.manga_hub.DTO.OrderItemsDTO;
import manga_hub.manga_hub.Security.TokenService;
import manga_hub.manga_hub.exceptions.NotFoundException;
import manga_hub.manga_hub.exceptions.NotFoundException;
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
                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));

        OrderItemsModel orderItemsModel = new OrderItemsModel(produto, orderItemsDTO.quantidade(), orderItemsDTO.preco() * orderItemsDTO.quantidade());

        CartModel carrinho = cartRepository.findById(user.getId())
                .orElseGet(() -> new CartModel());
        carrinho.setUsuario(user);
        carrinho.getItens().add(orderItemsModel);
        cartRepository.save(carrinho);
    }

    //deletar produto do carrinho
    public void deleteProduct(Long productId, String Token) {
        UserModel user = getUserFromToken(Token);
        CartModel carrinho = cartRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundException("Carrinho não encontrado"));
        OrderItemsModel orderItemsModel = carrinho.getItens().stream()
                .filter(oi -> oi.getProduto().getId() == productId)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Produto não encontrado no carrinho"));
        carrinho.getItens().remove(orderItemsModel);
        cartRepository.save(carrinho);
    }

    //LISTAR DTO
    public List<HomeDTO> listCartProducts(String token) {
        UserModel user = getUserFromToken(token);
        CartModel cart = cartRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundException("Carrinho não encontrado"));    
        List<HomeDTO> listProductsDTO = new ArrayList<>();
        for (OrderItemsModel orderItem : cart.getItens()) {
            ProductModel product = orderItem.getProduto();
            HomeDTO homeDTO = new HomeDTO(
                    product.getId(),
                    product.getNome(),
                    product.getPreco(),
                    product.getImagem(),
                    new HomeUserDTO(user.getId(), user.getName(), user.getTelefone())
            );
            listProductsDTO.add(homeDTO);
        }
        return listProductsDTO;
    }

    //total do carrinho
    // public Double calculateCartTotal(String token) {
    //     UserModel user = getUserFromToken(token);
    //     CartModel cart = cartRepository.findById(user.getId())
    //             .orElseThrow(() -> new NotFoundException("Carrinho não encontrado"));

    //     double total = 0.0;
    //     for (OrderItemsModel orderItem : cart.getItens()) {
    //         total += orderItem.getTotal();
    //     }

    //     return total;
    // }

    public UserModel getUserFromToken(String token) {
        String login = service.validateToken(token);

        if (!login.isEmpty()) {
            UserModel user = (UserModel) uRepository.findByLogin(login);
            return user;
        }

        throw new BadCredentialsException("Token inválido");
    }
}
