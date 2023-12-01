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
import manga_hub.manga_hub.DTO.ProductRegDTO;

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

    private List<String> genero;
    
    private String tipo_produto;
    private String isbn;
    private String imagem;

    @ManyToOne
    @JoinColumn(name = "id_vendedor")
    private UserModel id_vendedor;

    public ProductModel(ProductRegDTO productRegDTO){
        nome = productRegDTO.nome();
        preco = productRegDTO.preco();
        estoque = productRegDTO.estoque();
        genero = productRegDTO.genero();
        tipo_produto = productRegDTO.tipo();
        isbn = productRegDTO.isbn();
        imagem = productRegDTO.imagem();
    }
}
