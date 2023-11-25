package manga_hub.manga_hub.models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

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
    @OneToMany
    private List <Genero> genero = new ArrayList<>();
    private TipoProduto tipo_produto; 
    private String isbn; 

    @ManyToOne
    @JoinColumn(name = "id_vendedor")
    private UserModel id_vendedor; 
}
