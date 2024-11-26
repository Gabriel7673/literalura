package personal.literalura.model;

public enum Idioma {
    PT("pt", "Português"),
    EN("en", "Inglês"),
    FR("fr", "Francês"),
    IT("it", "Italiano"),
    ES("es", "Espanhol"),
    N0("nr", "Não Registrado");

    private String idiomaAbreviado;
    private String idioma;

    Idioma(String idiomaAbreviado, String idioma) {
        this.idiomaAbreviado = idiomaAbreviado;
        this.idioma = idioma;
    }

    public static Idioma fromAbreviacao(String text){
        try {
            for (Idioma idioma : Idioma.values()) {
                if (idioma.idiomaAbreviado.equalsIgnoreCase(text)) {
                    return idioma;
                }
            }
        }catch (IllegalArgumentException e){
            return Idioma.N0;
        }
        return Idioma.N0;
    }

    public static Idioma fromIdioma(String text){
        try {
            for (Idioma idioma : Idioma.values()) {
                if (idioma.idioma.equalsIgnoreCase(text)) {
                    return idioma;
                }
            }
        }catch (IllegalArgumentException e){
            return Idioma.N0;
        }
        return Idioma.N0;
    }
}
