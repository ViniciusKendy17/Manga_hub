package manga_hub.manga_hub.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import manga_hub.manga_hub.models.ProductModel;
import manga_hub.manga_hub.models.UserModel;

public interface ProductRepository extends JpaRepository<ProductModel, Long>  {
    ProductModel save(ProductModel product);
    
}
