package personal.literalura.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@UniqueConstraint()
    @Column(unique = true)
    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<Livro> livros;  // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    private List<Livro> livros = new ArrayList<>();  // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public Autor(){}

    //public Autor(DadosAutor dadosAutor, Livro livro){
    public Autor(DadosAutor dadosAutor){
        this.nome = dadosAutor.nome();
        this.anoNascimento = dadosAutor.anoNascimento();
        this.anoFalecimento = dadosAutor.anoFalecimento();
        //this.livros.add(livro)
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }

    public void addLivro(Livro livro){
        this.livros.add(livro);
//        if (livro.getAutor() != this){
//            livro.setAutor(this);
//        }
    }

    @Override
    public String toString() {
        return  nome +
                " (" + anoNascimento +
                "-" + anoFalecimento +
                ") ";
    }
}
