package manga_hub.manga_hub.DTO;

import manga_hub.manga_hub.models.ProductModel;

public record OrderItemsDTO(Long id, String nome, int quantidade, int preco){}
