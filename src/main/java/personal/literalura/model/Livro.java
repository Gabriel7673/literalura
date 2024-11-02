package personal.literalura.model;


public class Livro {
    private String titulo;
    private Autor autor;
    private String idioma; // Fazer Enum?
    private Integer numeroDeDownloads;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDeDownloads() {
        return numeroDeDownloads;
    }

    public void setNumeroDeDownloads(Integer numeroDeDownloads) {
        this.numeroDeDownloads = numeroDeDownloads;
    }

    @Override
    public String toString() {
        return titulo + ", de " + autor
                + "\nIdioma: " + idioma
                + " - Nº de downloads:" + numeroDeDownloads;
    }
}
