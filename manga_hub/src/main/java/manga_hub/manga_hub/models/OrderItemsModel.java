package manga_hub.manga_hub.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import manga_hub.manga_hub.DTO.OrderItemsDTO;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "carrinho")
public class OrderItemsModel {

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private ProductModel produto;
    //@Column(name="quantidade")
    private int quantidade;
    //@Column(name="total_produto")
    private double total;
}
