package personal.literalura.model;


public class Livro {
    private Long id;
    private String titulo;
    private Autor autor;
    private String idioma; // Fazer Enum?
    private Integer numeroDeDownloads;

    public Livro(){}

    public Livro(DadosLivro dadosLivro){
        this.id = dadosLivro.id();
        this.titulo = dadosLivro.titulo();
        this.autor = new Autor(dadosLivro.autor().get(0));
        this.idioma = dadosLivro.idioma().get(0);
        this.numeroDeDownloads = dadosLivro.numeroDeDownloads();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        return "Código: " + id + "\n"
                + titulo + ", de " + autor
                + "\nIdioma: " + idioma
                + " - Nº de downloads: "
            + numeroDeDownloads + "\n";
    }
}
