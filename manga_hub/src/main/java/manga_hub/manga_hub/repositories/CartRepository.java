package manga_hub.manga_hub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import manga_hub.manga_hub.models.CartModel;
import manga_hub.manga_hub.models.UserModel;


public interface CartRepository extends JpaRepository<CartModel, Long>  {

    CartModel findByUsuario(UserModel user);
    
}
