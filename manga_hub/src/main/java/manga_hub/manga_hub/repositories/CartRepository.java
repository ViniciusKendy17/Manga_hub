package manga_hub.manga_hub.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import manga_hub.manga_hub.models.CartModel;

public interface CartRepository extends JpaRepository<CartModel, Long>{
    // CartModel save(CartModel cart);
    // @Query("SELECT c FROM carrinho c WHERE c.id_usuario = :userId")
    // CartModel findByUserId(@Param("userId") Long userId);

}
