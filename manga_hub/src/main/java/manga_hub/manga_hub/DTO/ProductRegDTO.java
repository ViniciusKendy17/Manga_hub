package manga_hub.manga_hub.DTO;

import java.util.List;

public record ProductRegDTO(String nome, Double preco, int estoque, String genero, String tipo, String isbn, String imagem) {}
