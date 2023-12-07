package manga_hub.manga_hub.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import manga_hub.manga_hub.DTO.OrderItemsDTO;

@Embeddable
@Data
public class OrderItemsModel {

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private ProductModel produto;

    private int quantidade;
    private double preco;

}
