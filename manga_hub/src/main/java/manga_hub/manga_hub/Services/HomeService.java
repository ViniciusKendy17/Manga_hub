package manga_hub.manga_hub.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manga_hub.manga_hub.models.ProductModel;
import manga_hub.manga_hub.repositories.ProductRepository;

@Service
public class HomeService {
    
    @Autowired
    ProductRepository repository;

    @Autowired
    ProductService service;

    public List<ProductModel> listAll(){
        return service.ListProducts();
    }
}
