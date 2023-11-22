package manga_hub.manga_hub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import manga_hub.manga_hub.models.UserModel;

public interface UserRespository extends JpaRepository<UserModel, Long> {

}
