package manga_hub.manga_hub.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import manga_hub.manga_hub.models.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, Long>  {
    ProductModel save(ProductModel product);
    @Query("SELECT c FROM produto c WHERE c.nome LIKE :nome%")
    Page<ProductModel> findByNome(@Param("nome") String nome, Pageable pageable);
}
