package manga_hub.manga_hub.repositories;

import java.util.List;

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

    @Query("SELECT p FROM produto p WHERE p.tipo_produto = :tipoProduto")
    List<ProductModel> findTop20ByTipoProduto(@Param("tipoProduto") String tipoProduto);

    @Query("SELECT p FROM produto p ORDER BY p.id DESC")
    List<ProductModel> findAllOrderedByIdDesc();
}
