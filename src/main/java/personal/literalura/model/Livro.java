package personal.literalura.model;

import jakarta.persistence.*;
import lombok.Getter;
import personal.literalura.repository.AutorRepository;

import java.util.Optional;

@Getter
@Entity
@Table(name = "livros")
public class Livro {
    @Id
    private Long id;
    private String titulo;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @Enumerated(EnumType.STRING)
    private Idioma idioma;
    private Integer numeroDeDownloads;

    public Livro(){}

    public Livro(DadosLivro dadosLivro, AutorRepository autorRepository){
        this.id = dadosLivro.id();
        this.titulo = dadosLivro.titulo();


        try{
            Optional<Autor> autorExistente = autorRepository.findByNome(dadosLivro.autor().get(0).nome());
            if (autorExistente.isPresent()) {
                this.autor = autorExistente.get();
                //this.autor.addLivro(dadosLivro);
            } else {
                this.autor = new Autor(dadosLivro.autor().get(0));
            }
        } catch (IndexOutOfBoundsException e) {
            this.autor = new Autor("Sem nome", 0, 0);
        }


        if (dadosLivro.idioma().get(0).length() == 2){
            this.idioma = Idioma.fromAbreviacao(dadosLivro.idioma().get(0));
        }else {
            this.idioma = Idioma.fromIdioma(dadosLivro.idioma().get(0));
        }


        this.numeroDeDownloads = dadosLivro.numeroDeDownloads();
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
