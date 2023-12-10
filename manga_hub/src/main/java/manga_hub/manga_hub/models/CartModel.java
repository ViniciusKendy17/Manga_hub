package manga_hub.manga_hub.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "carrinho")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UserModel usuario;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL)
    @JsonIgnore // Adicione esta anotação para evitar referências circulares
    private List<CartItemModel> itens;

    private double total;

    @Override
    public String toString() {
        return "CartModel{" +
            "id=" + id +
            ", total=" + total +
            ", usuario=" + usuario +
            ", itens=" + "itens (size: " + (itens != null ? itens.size() : 0) + ")" + // Evita a impressão completa dos itens
            '}';
    }

    public void addItem(CartItemModel item) {
        this.itens.add(item);
        item.setCarrinho(this);
    }
}
