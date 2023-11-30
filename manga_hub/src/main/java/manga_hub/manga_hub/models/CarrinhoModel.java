package manga_hub.manga_hub.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="carrinho")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarrinhoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "id_produto")
    private Long idProduto;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "total_produto")
    private double totalProduto;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UserModel usuario;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private ProductModel produto;

}
