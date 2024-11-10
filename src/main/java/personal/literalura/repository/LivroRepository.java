package personal.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import personal.literalura.model.Autor;
import personal.literalura.model.Livro;

import java.util.List;

// Criar outro repositorio???
public interface LivroRepository extends JpaRepository<Livro, Long> {

    //List<Livro> find

    //@Query("SELECT a FROM Livro l JOIN l.autor a DISTINCT")

//    @Query("SELECT a FROM ")
//    List<Autor> findAutoresVivosEmAno(Integer ano);

    @Query("SEELECT l FROM Livro l WHERE l.idioma = :idioma")
    List<Livro> findLivrosEmIdioma(String idioma);
}
