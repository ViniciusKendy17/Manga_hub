package manga_hub.manga_hub.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manga_hub.manga_hub.DTO.HomeDTO;
import manga_hub.manga_hub.DTO.HomeUserDTO;
import manga_hub.manga_hub.models.ProductModel;
import manga_hub.manga_hub.models.UserModel;
import manga_hub.manga_hub.repositories.ProductRepository;

@Service
public class HomeService {
    
    @Autowired
    ProductRepository repository;

    @Autowired
    ProductService service;

    public List<HomeDTO> listLast(){ 

        List<ProductModel> listaBanco = repository.findAllOrderedByIdDesc();
        List<HomeDTO> listResponse = new ArrayList<>();

        for (ProductModel productModel : listaBanco) {
            //RECUPERANDO O USUARIO PRESENTE EM PRODUTO
            UserModel user = productModel.getId_vendedor();
            //USANDO O OBJETO RECUPERADO PARA INSTANCIAR UM DTO

            //MONTANDO O DTO DE PRODUTO E USUARIO
            HomeUserDTO userDTO = new HomeUserDTO(user.getId(), user.getName(), user.getTelefone());
            listResponse.add(new HomeDTO(
            productModel.getId(), 
            productModel.getNome(), 
            productModel.getPreco(), 
            productModel.getImagem(), 
            userDTO));
        }
        return listResponse;
    }

    public List<HomeDTO> listItensPerSection(String tipo){
        List<ProductModel> mangas = repository.findTop20ByTipoProduto(tipo);
        return mangas.stream().map(this::mapToHomeDTO).collect(Collectors.toList());
    }

    private HomeDTO mapToHomeDTO(ProductModel product) {
        
        HomeUserDTO vendedor = new HomeUserDTO(product.getId_vendedor().getId(), product.getId_vendedor().getName(), product.getId_vendedor().getTelefone());

        HomeDTO produtoDTO = new HomeDTO(product.getId(), product.getNome(), product.getPreco(), product.getImagem(), vendedor);

        return produtoDTO;
    }
}
