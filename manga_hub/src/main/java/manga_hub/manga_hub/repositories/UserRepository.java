package manga_hub.manga_hub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import manga_hub.manga_hub.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserDetails findByLogin(String login);
    UserModel save(UserModel userModel);
}
