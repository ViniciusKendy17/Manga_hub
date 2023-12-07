package manga_hub.manga_hub.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class OrderItemsModel {

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private ProductModel produto;

    private int quantidade;
    private double preco;
    
}
