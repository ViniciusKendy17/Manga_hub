package manga_hub.manga_hub.DTO;

public record ProductRegDTO(String nome, String descricao, Double preco, int estoque, String genero, String tipo, String isbn,
        String imagem) {
}
