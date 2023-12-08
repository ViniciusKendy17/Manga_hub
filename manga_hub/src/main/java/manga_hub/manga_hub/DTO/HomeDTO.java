package manga_hub.manga_hub.DTO;

import manga_hub.manga_hub.models.ProductModel;
import manga_hub.manga_hub.models.UserModel;

public record HomeDTO(Long id, String nome, Double preco, String imagem, HomeUserDTO usuario) {}
