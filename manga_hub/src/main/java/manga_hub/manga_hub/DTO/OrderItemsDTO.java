package manga_hub.manga_hub.DTO;

import manga_hub.manga_hub.models.ProductModel;

public record OrderItemsDTO(ProductModel produto, int quantidade, int preco){}
