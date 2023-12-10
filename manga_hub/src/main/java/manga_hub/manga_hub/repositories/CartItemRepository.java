package manga_hub.manga_hub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import manga_hub.manga_hub.models.CartItemModel;
import manga_hub.manga_hub.models.CartModel;

public interface CartItemRepository extends JpaRepository<CartItemModel, Long>  {

    void save(CartModel carrinho);
    
}
