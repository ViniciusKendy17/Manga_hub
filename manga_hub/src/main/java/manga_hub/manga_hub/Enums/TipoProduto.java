package manga_hub.manga_hub.Enums;

public enum TipoProduto {
    
    MANGA("Manga"),
    LIGHT_NOVEL("Light Novel"),
    ACTION("Action");


    private String tipo;

    TipoProduto(String tipo){
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
