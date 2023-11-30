package manga_hub.manga_hub.models;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import manga_hub.manga_hub.Enums.Genero;
import manga_hub.manga_hub.Enums.TipoProduto;

@Entity(name = "produto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private Double preco;
    private int estoque;

    @ElementCollection(targetClass = Genero.class)
    @CollectionTable(name = "produto_genero", joinColumns = @JoinColumn(name = "produto_id"))
    @Enumerated(EnumType.STRING)
    private List<Genero> genero = new ArrayList<>();
    
    private TipoProduto tipo_produto;
    private String isbn;
    private String imagem;

    @ManyToOne
    @JoinColumn(name = "id_vendedor")
    private UserModel id_vendedor;
}
