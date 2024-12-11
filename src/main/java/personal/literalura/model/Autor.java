package personal.literalura.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    private Integer anoNascimento;

    private Integer anoFalecimento;

    public Autor(){}

    public Autor(DadosAutor dadosAutor){
        this.nome = dadosAutor.nome();
        this.anoNascimento = dadosAutor.anoNascimento();
        this.anoFalecimento = dadosAutor.anoFalecimento();
    }

    public Autor(String nome, int anoNascimento, int anoFalecimento) {
        this.nome = nome;
        this.anoNascimento = anoNascimento;
        this.anoFalecimento = anoFalecimento;
    }

    @Override
    public String toString() {

        return  nome +
                " (" + anoNascimento +
                "-" + anoFalecimento +
                ") ";
    }
}
