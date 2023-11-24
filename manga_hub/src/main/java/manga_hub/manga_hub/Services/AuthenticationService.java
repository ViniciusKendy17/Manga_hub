package manga_hub.manga_hub.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import manga_hub.manga_hub.DTO.UserRegDTO;
import manga_hub.manga_hub.models.UserModel;
import manga_hub.manga_hub.repositories.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    public void save(UserRegDTO userRegDTO) {
        // Converter UserDTO para UserModel
        UserModel userModel = new UserModel();
        userModel.setName(userRegDTO.nome());
        userModel.setLogin(userRegDTO.login());
        userModel.setSenha(userRegDTO.senha());
        userModel.setCpf(userRegDTO.cpf());
        userModel.setTelefone(userRegDTO.telefone());
        userModel = repository.save(userModel);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByLogin(email);
    }

}
