package manga_hub.manga_hub.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import manga_hub.manga_hub.models.CartModel;

public interface CartRepository extends JpaRepository<CartModel, Long>{
}
