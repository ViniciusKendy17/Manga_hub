package manga_hub.manga_hub.Enums;

public enum Genero {

    SHOUNEN ("Shounen"),
    SEINEN("Seinen"),
    SHOUJO("Shoujo"),
    JOSEI("Josei"),
    ISEKAI("Isekai"),
    SLICE_OF_LIFE("Slice of Life"),
    SCI_FI("Sci-Fi"),
    MISTERY("Mistery"),
    COMEDY("Comedy"),
    ROMANCE("Romance"),
    ACTION("Action");

    private String genero;

    Genero(String genero){
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    
}
