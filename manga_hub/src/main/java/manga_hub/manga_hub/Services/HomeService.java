package manga_hub.manga_hub.Services;

import java.util.ArrayList;
import java.util.List;

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

    public List<HomeDTO> listAll(){ 

        List<ProductModel> listaBanco = repository.findAll();
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
}
