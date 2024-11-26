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

    //@OneToOne(mappedBy = "autores", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "autor_id")
    private Autor autor;
    private String idioma; // Fazer Enum?
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




        this.idioma = dadosLivro.idioma().get(0);
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
