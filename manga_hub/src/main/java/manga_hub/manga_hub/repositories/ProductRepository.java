package manga_hub.manga_hub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import manga_hub.manga_hub.models.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, Long>  {
    ProductModel save(ProductModel product);
    
}
