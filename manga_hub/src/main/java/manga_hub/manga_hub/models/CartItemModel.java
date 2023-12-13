package manga_hub.manga_hub.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "order_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private ProductModel produto;

    private int quantidade;
    private Double total;

    @ManyToOne
    @JoinColumn(name = "id_carrinho")
    @JsonIgnore 
    private CartModel carrinho;

    @Override
    public String toString() {
        return "CartItemModel{" +
            "id=" + id +
            ", quantidade=" + quantidade +
            ", total=" + total +
            ", produto=" + "produto (id: " + (produto != null ? produto.getId() : null) + ")" + // Evita a impressão completa do produto
            ", carrinho=" + "carrinho (id: " + (carrinho != null ? carrinho.getId() : null) + ")" + // Evita a impressão completa do carrinho
            '}';
}
}