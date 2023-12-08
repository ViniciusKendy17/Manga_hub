package manga_hub.manga_hub.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import manga_hub.manga_hub.DTO.OrderItemsDTO;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemsModel {

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private ProductModel produto;

    private int quantidade;
    private double subTotalProduto;
}
