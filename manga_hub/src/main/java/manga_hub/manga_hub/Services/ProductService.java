package manga_hub.manga_hub.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import manga_hub.manga_hub.DTO.HomeDTO;
import manga_hub.manga_hub.DTO.HomeUserDTO;
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

    public List<ProductModel> ListProducts(){
       return repository.findAll();
    }

    public HomeDTO getProduct(Long id){
        Optional<ProductModel> produtoOptional = repository.findById(id);
        if (produtoOptional.isPresent()) {
            //EXTRAINDO PRODUTO O OPTIONAL
            ProductModel produto = produtoOptional.get();
            //ATRIBUINDO OBEJTO USUARIO DENTRO DE PRODUTO
            UserModel usuario = produto.getId_vendedor();

            HomeDTO homeDTO = new HomeDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getImagem(),
                new HomeUserDTO(usuario.getId(), 
                usuario.getName(), 
                usuario.getTelefone())
            );
            
            return homeDTO;
        } else return null;  
    }

    //PESQUISA PAGINADA
    public Page<HomeDTO> searchProductsPageable(String nome, Pageable pageable) {
    Page<ProductModel> result = repository.findByNome(nome, pageable); 
    List<HomeDTO> listproductsDTOs = result.getContent().stream()
            .map(produto -> new HomeDTO(
            produto.getId(), 
            produto.getNome(), 
            produto.getPreco(), 
            produto.getImagem(),
            new HomeUserDTO(
                produto.getId_vendedor().getId(),
                produto.getId_vendedor().getName(),
                produto.getId_vendedor().getTelefone()
            )
            ))
            .collect(Collectors.toList());
    
        return new PageImpl<>(listproductsDTOs, pageable, result.getTotalElements());
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
