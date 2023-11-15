package manga_hub.manga_hub.User;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "usuario")
public class UserModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String name;
    private String cpf;
}
