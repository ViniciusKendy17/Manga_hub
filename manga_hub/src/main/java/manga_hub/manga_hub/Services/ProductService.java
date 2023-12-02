package manga_hub.manga_hub.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import manga_hub.manga_hub.DTO.ProductRegDTO;
import manga_hub.manga_hub.Security.TokenService;
import manga_hub.manga_hub.models.ProductModel;
import manga_hub.manga_hub.models.UserModel;
import manga_hub.manga_hub.repositories.ProductRepository;
import manga_hub.manga_hub.repositories.UserRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    @Autowired
    UserRepository uRepository;

    @Autowired
    TokenService service;

    public void saveProduct(ProductRegDTO produtoDTO, String Token) {
        UserModel user = getUserFromToken(Token);
        System.out.println("Poduruto name=" + user.getName());
        ProductModel produto = new ProductModel(produtoDTO);
        System.out.println("cUserName=" + produto.getNome());
        produto.setId_vendedor(user);
        repository.save(produto);
    }

    public List<ProductModel> ListProducts() {
        return repository.findAll();
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
