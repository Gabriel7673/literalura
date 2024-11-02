package personal.literalura.model;

public class Autor {
    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;

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

    @Override
    public String toString() {
        return  "Autor: " + nome + '\'' +
                ", Nascimento=" + anoNascimento +
                ", Falecimento=" + anoFalecimento;
    }
}
