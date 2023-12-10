package manga_hub.manga_hub.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import manga_hub.manga_hub.DTO.UserRegDTO;

@Entity(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String name;
    @Column(name = "email")
    private String login;
    private String senha;
    private String cep;
    @Column(name = "telefone", length = 15)
    private String telefone;

    @JsonManagedReference
    @OneToMany(mappedBy = "id_vendedor")
    private List<ProductModel> produtos; 

//    @JsonManagedReference
  //  @OneToMany(mappedBy = "id_cliente")
    //private List<OrderModel> pedidos; 

    public UserModel(String name, String login, String senha, String cep, String telefone) {
        this.name = name;
        this.login = login;
        this.senha = senha;
        this.cep = cep;
        this.telefone = telefone;
    }

    public UserModel(UserRegDTO userRegDTO) {
        this.name = userRegDTO.nome();
        this.login = userRegDTO.login();
        this.senha = userRegDTO.senha();
        this.cep = userRegDTO.cep();
        this.telefone = userRegDTO.telefone();
    }

	@Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    // IGNORAR DAQUI PRA BAIXO POR HORA, OBRIGADO A IMPLEMENTAR
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
