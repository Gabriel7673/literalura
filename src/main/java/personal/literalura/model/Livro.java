package personal.literalura.model;

import jakarta.persistence.*;
import personal.literalura.repository.AutorRepository;

import java.util.Optional;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    private Long id;
    private String titulo;

    //@OneToOne(mappedBy = "autores", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Autor autor;
    private String idioma; // Fazer Enum?
    private Integer numeroDeDownloads;

    public Livro(){}

    public Livro(DadosLivro dadosLivro){
        this.id = dadosLivro.id();
        this.titulo = dadosLivro.titulo();
        // CHECAR SE O AUTOR JÁ ESTÁ NA TABELA !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        AutorRepository autorRepository = null;
        Optional<Autor> autorExistente = autorRepository.findByNome(dadosLivro.autor().get(0).nome());

        //Optional.ofNullable(this.autor) = autorExistente;
        this.autor = autorExistente.orElseGet(() -> new Autor(dadosLivro.autor().get(0)));
//        if (autorExistente.isPresent()) {
//            //Optional.ofNullable(this.autor) = autorExistente;
//            this.autor = autorExistente.get();
//        } else {
//            this.autor = new Autor(dadosLivro.autor().get(0));
//        }


        //this.autor = new Autor(dadosLivro.autor().get(0));
        //this.autor = new Autor(dadosLivro.autor().get(0), this);
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
                + titulo + ", de " + autor.getNome()
                + "\nIdioma: " + idioma
                + " - Nº de downloads: "
            + numeroDeDownloads + "\n";
    }
}
