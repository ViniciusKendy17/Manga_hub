package manga_hub.manga_hub.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "carrinho")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @ManyToOne
    // @JoinColumn(name = "id_produto")
    // private ProductModel produto;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UserModel usuario;

    // @Column(name="quantidade")
    // private int quantidade;

    // @Column(name="total_produto")
    // private double total;

    @ElementCollection
    /*
    @AttributeOverrides({
        @AttributeOverride(name = "produto", column = @Column(name = "id_produto")),
        @AttributeOverride(name = "quantidade", column = @Column(name = "quantidade")),
        @AttributeOverride(name = "total", column = @Column(name = "total_produto"))
    })
     */
    private List<OrderItemsModel> itens = new ArrayList<>();

}
