package manga_hub.manga_hub.models;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String tipo_produto; 
    private String isbn; 

    @ManyToOne
    @JoinColumn(name = "id_vendedor") // Adicionando o nome da coluna na tabela ProductModel
    private UserModel id_vendedor; 
}
