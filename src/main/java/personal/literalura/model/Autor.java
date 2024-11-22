package personal.literalura.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
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

    //@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)

    public Autor(){}

    public Autor(DadosAutor dadosAutor){
    //public Autor(DadosAutor dadosAutor){
        this.nome = dadosAutor.nome();
        this.anoNascimento = dadosAutor.anoNascimento();
        this.anoFalecimento = dadosAutor.anoFalecimento();


    }
//
//    public void addLivro(Livro livro){
//        this.livros.add(livro);
//    }

    @Override
    public String toString() {
        String lista = "";// = String.join("; ", livros);

        return  nome +
                " (" + anoNascimento +
                "-" + anoFalecimento +
                ") \n" + "Livros: " +
                lista;
    }
}
